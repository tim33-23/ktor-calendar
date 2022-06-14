package com.example.plugins.routing

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureAuthorization() {


    data class DataRegistration(val roles: List<String>, val election: String)

    routing {
/*        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }*/
    }

    routing {
        get("/login") {
            call.respond(FreeMarkerContent("templates/login.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
        }

    }


}