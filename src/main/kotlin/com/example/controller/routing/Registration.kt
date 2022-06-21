package com.example.plugins.routing

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.coroutines.runBlocking


fun Application.configureRegistration() {


    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    routing {

        get("/electionsForRegistration"){
            val elections = dao.allElections()
            call.respond(
                FreeMarkerContent(
                "templates/registration/electionsForRegistration.ftl",
                    mapOf("elections" to elections)
                )
            )
        }

        post("/registrationWithElection"){
            val formParameters = call.receiveParameters()
            val election = formParameters["election"].toString()
            val roles = dao.rolesForElection(election.toInt())
            call.respond(
                FreeMarkerContent("templates/registration/registrationWithElection.ftl",
                    mapOf("roles" to roles, "election" to election)
                )
            )
        }

        post("/registration"){
            val formParameters = call.receiveParameters()
            val election = "election"
            val roles = listOf("Кандидат", "СМИ")
            call.respond(
                FreeMarkerContent(
                    "templates/registration/registrationWithElection.ftl",
                    mapOf("roles" to roles, "election" to election)
                )
            )
        }
    }


}