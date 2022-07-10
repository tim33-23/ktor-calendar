package com.example.plugins.routing

import com.example.services.ElectionsService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Application.configureRegistration() {

    data class DataRegistration(val roles: List<String>, val election: String)

    routing {

        post("/registration"){
            val formParameters = call.receiveParameters()
            val election = "election"
            val roles = listOf<String>("Кандидат", "СМИ")
            val dataRegistration = DataRegistration(roles, election)
            call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("data" to dataRegistration)))
        }

        get("/registration"){
            call.respond(FreeMarkerContent("templates/registration/registration.ftl", null))
        }

        authenticate("auth-session") {
            get("/registration"){
                call.respond(FreeMarkerContent("templates/main/main.ftl", null))
            }
        }
    }


}