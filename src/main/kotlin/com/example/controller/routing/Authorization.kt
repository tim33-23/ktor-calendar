package com.example.plugins.routing

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


fun Application.configureAuthorization() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

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
                if(session.role == "ЦИК") {
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
            if(email == "tim33-23@mail.ru" && password == "1234"){
                call.sessions.set(UserSession(email = email, role = "ЦИК", name = "Андрей"))
                call.respondRedirect("/")
            }
            else{
                val participant = dao.participant(email, password)
                if (participant!=null) {
                    val role = dao.role(participant.idRole)
                    if (role!=null) {
                        call.sessions.set(participant.name?.let { it1 -> UserSession(email = email, role = role.nameRole, name = it1) })
                        call.respondRedirect("/")
                    }
                    else{
                        val message = "Ошибка"
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", mapOf("message" to message)))

                    }
                } else {
                    val message = "Ошибка"
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", mapOf("message" to message)))
                }
            }

        }


        get("/logout") {
            call.sessions.clear<UserSession>()
            call.respondRedirect("/")
        }

        authenticate("auth-session") {
            get("/") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "index.ftl",
                        mapOf("name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }
        }
    }


}