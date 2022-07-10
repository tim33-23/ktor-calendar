package com.example.controller.routing

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

    routing {

        get("/login") {
            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
        }


        post("/login") {
            val formParameters = call.receiveParameters()
            val email = formParameters.getOrFail("email")
            val password = formParameters.getOrFail("password")
            if (checkAccount(email, password)) {
                call.sessions.set(UserSession(email = email))
                call.respond(FreeMarkerContent("index.ftl", null))
            } else {
                call.respondRedirect("/")
            }
        }

        get("/"){
            val userSession = call.principal<UserSession>()
            call.respond(FreeMarkerContent("index.ftl", null))
        }

        authenticate("auth-session") {
            get("/") {
                val userSession = call.principal<UserSession>()
                call.respond(FreeMarkerContent("main/main.ftl", null))
            }
        }


        get("/logout") {
            call.sessions.clear<UserSession>()
            call.respondRedirect("/")
        }
    }


}