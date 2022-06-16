package com.example.plugins

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

fun Application.configureRouting() {


    routing {
        // Static plugin. Try to access `/static/index.ftl`
        static("/static") {
            resources("static/img")
        }

        authenticate("auth-session") {
            get("/editElections") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        mapOf("elections" to ElectionsService().getAllElections(), "name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }

            get("/elections") {
                val userSession = call.principal<UserSession>()
                val electionsService = ElectionsService()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        mapOf("elections" to electionsService.getElectionsForRegistration(), "name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }

            get("/createElection") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/createElection.ftl",
                        mapOf("elections" to ElectionsService().getAllElections(), "name" to userSession?.name, "role" to userSession?.role),
                        ""
                    )
                )
            }

            post("/createElection") {
                val userSession = call.principal<UserSession>()
                if(userSession?.role == "ЦИК" || userSession?.role == "ТИК"){
                    val formParameters = call.receiveParameters()
                    val email = formParameters.getOrFail("nameElection")
                    val password = formParameters.getOrFail("dateBegin")

                }
                call.respond(
                    FreeMarkerContent(
                        "templates/election/createElection.ftl",
                        mapOf("elections" to ElectionsService().getAllElections(), "name" to userSession?.name, "role" to userSession?.role),
                        ""
                    )
                )
            }

            post("/election") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val election = formParameters["election"].toString()
                val events = ElectionsService().getEvents(election)
                call.respond(
                    FreeMarkerContent(
                        "templates/election/election.ftl",
                        mapOf("events" to events, "election" to election, "name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }


        }









    }
}
