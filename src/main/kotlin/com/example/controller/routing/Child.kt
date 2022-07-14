package com.example.plugins

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.UserSession
import com.example.services.ElectionsService
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

fun Application.configureRouting() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }


    routing {
        // Static plugin. Try to access `/static/index.ftl`
        static("/static") {
            resources("static/img")
        }

        authenticate("auth-session") {

            post("/createChild") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val name = formParameters["name"].toString()
                val gender = formParameters["gender"].toString()
                val dataBirth = formParameters["dateOfBirth"]?.toLocalDate()
                if(userSession!=null){
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if(parent!=null && dataBirth!=null){
                        if(gender=="boy"){
                            val child = dao.addChild(parent.id, name, dataBirth, true)
                            call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to child)))
                        }
                        else if(gender=="girl"){
                            val child = dao.addChild(parent.id, name, dataBirth, false)
                            call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to child)))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                        }
                    }
                    else{
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/children") {
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if(parent!=null){
                        val children = dao.children(parent.id)
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                    else{
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }

            }
        }
    }
}
