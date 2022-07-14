package com.example.controller.routing

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.UserSession
import com.example.services.checkAccount
import com.example.services.getProfile
import com.example.services.getRole
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import java.time.Period


fun Application.configureAuthorization() {
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 600
            cookie.extensions["SameSite"] = "lax"
        }
    }
    install(Authentication) {
        session<UserSession>("auth-session") {
            validate { session ->
                if(session.email != "") {
                    session
                } else {
                    null
                }
            }
            challenge {
                null
            }
        }
    }

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    routing {

        get("/login") {
            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
        }


        post("/login") {
            val formParameters = call.receiveParameters()
            val email = formParameters.getOrFail("email")
            val password = formParameters.getOrFail("password")
            val parent = dao.parent(email)
            if((parent != null) && (parent.password == password)){
                call.sessions.set(UserSession(email = email))
                val children = dao.children(idParent = parent.id)
                if((children != null) && children.isNotEmpty())
                    call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to children.first())))
                else
                    call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
            }
            else{
                call.respond(FreeMarkerContent("index.ftl", mapOf("message" to "Проверьте правильность заполненых полей")))
            }
        }

        get("/"){
            call.respond(FreeMarkerContent("index.ftl", null))
        }

        authenticate("auth-session") {
            get("/") {
                val userSession = call.principal<UserSession>()
                if(userSession!=null) {
                    val parent = dao.parent(userSession.email)
                    if(parent!=null){
                        val children = dao.children(idParent = parent.id)
                        if ((children != null) && children.isNotEmpty()){
                            val child = children.first()
                            val nowDate = java.time.LocalDate.now()
                            val birthDate = child?.dateOfBirth
                            val period = Period.between(birthDate?.toJavaLocalDate(), nowDate)

                            call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to child, "period" to period)))
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

            get("/login") {
                val userSession = call.principal<UserSession>()
                if(userSession!=null) {
                    val parent = dao.parent(userSession.email)
                    if(parent!=null){
                        val children = dao.children(idParent = parent.id)
                        if ((children != null) && children.isNotEmpty())
                            call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to children.first())))
                        else
                            call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
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


        get("/logout") {
            call.sessions.clear<UserSession>()
            call.respondRedirect("/")
        }
    }


}