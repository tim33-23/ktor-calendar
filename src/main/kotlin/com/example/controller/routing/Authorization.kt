package com.example.controller.routing

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import com.example.controller.routing.Services.MainPage

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
                    session
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


        post("/login") {
            val formParameters = call.receiveParameters()
            val email = formParameters.getOrFail("email")
            val password = formParameters.getOrFail("password")
            val parent = dao.parent(email)
            if((parent != null) && (parent.password == password)){
                val model = MainPage().getModelForMainPage(parent)
                call.sessions.set(UserSession(email = email, model?.get("idChild") as Int?))
                model?.minus("idChild")
                if(model!=null){
                    call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                }
                else{
                    call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                }
            }
            else{
                call.respond(FreeMarkerContent("index.ftl", mapOf("message" to "Проверьте правильность заполненых полей")))
            }
        }

        authenticate("auth-session") {
            get("/") {
                val userSession = call.principal<UserSession>()
                if(userSession!=null) {
                    val parent = dao.parent(userSession.email)
                    if(parent!=null){
                        val model = MainPage().getModelForMainPage(parent)
                        call.sessions.set(UserSession(userSession.email, model?.get("idChild") as Int?))
                        model?.minus("idChild")
                        if(model!=null){
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
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
                    call.respond(FreeMarkerContent("index.ftl", null))
                }
            }

            get("/login") {
                val userSession = call.principal<UserSession>()
                if(userSession!=null) {
                    val parent = dao.parent(userSession.email)
                    if(parent!=null){
                        val model = MainPage().getModelForMainPage(parent)
                        call.sessions.set(UserSession(userSession.email, model?.get("idChild") as Int?))
                        model?.minus("idChild")
                        if(model!=null){
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
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
        }

        get("/logout") {
            call.sessions.clear<UserSession>()
            call.respondRedirect("/")
        }
    }


}