package com.example.plugins

import com.example.plugins.routing.IndexData
import com.example.services.ElectionsService
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureSecurity() {

    data class MySession(val count: Int = 0)
    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    data class DataRegistration(val roles: List<String>, val election: String)

    routing {
        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }
    }

    routing {
        get("/login") {
            call.respond(FreeMarkerContent("templates/login.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
        }

        get("/electionsForRegistration"){
            val electionsService = ElectionsService()
            call.respond(FreeMarkerContent("templates/electionsForRegistration.ftl", mapOf("elections" to electionsService.getElectionsForRegistration()), ""))
        }

        post("/registrationWithElection"){
            val formParameters = call.receiveParameters()
            val election = formParameters["election"].toString()
            val roles = ElectionsService().getRoleForElection()
            val dataRegistration = DataRegistration(roles, election)
            call.respond(FreeMarkerContent("templates/registrationWithElection.ftl", mapOf("data" to dataRegistration)))
        }

        post("/registration"){
            val formParameters = call.receiveParameters()
            val election = "election"
            val roles = listOf<String>("Кандидат", "СМИ")
            val dataRegistration = DataRegistration(roles, election)
            call.respond(FreeMarkerContent("templates/registrationWithElection.ftl", mapOf("data" to dataRegistration)))
        }
    }


}
