package com.example.controller.routing



import com.example.controller.routing.Services.MainPage
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.ParametersBody
import com.example.dto.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.toLocalDate

fun Application.configureTeeth() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    routing {

        authenticate("auth-session") {
            get("/tooth"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        call.respond(FreeMarkerContent("templates/tooth/tooth.ftl", null))
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){
                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }



            get("/changeTooth"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        call.respond(FreeMarkerContent("templates/tooth/changeTooth.ftl", null))
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){
                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }
        }
    }
}