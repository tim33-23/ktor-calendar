package com.example.plugins

import com.example.converts.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.toLocalDateTime


fun Application.configureElection() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }


    routing {
        // Static plugin. Try to access `/static/index.ftl`
        static("/static") {
            resources("static/img")
        }

        authenticate("auth-session") {
            get("/elections") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        mapOf("elections" to dao.allElections(), "name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }


            post("/election") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idElection = formParameters["election"].toString().toInt()
                val election = dao.election(idElection)
                val events = dao.allEvents()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/election.ftl",
                        mapOf("events" to events, "election" to election, "name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }

            get("/editElections") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        mapOf("elections" to dao.allElections(), "name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }

            post("/editElection"){

            }

            get("/createElection") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/createElection.ftl",
                        mapOf("name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }



            post("/createElection") {
                val userSession = call.principal<UserSession>()
                if(userSession?.role == "ЦИК" || userSession?.role == "ТИК"){
                    val formParameters = call.receiveParameters()
                    val nameElection = formParameters.getOrFail("nameElection")
                    val dateBegin = formParameters.getOrFail("dateBegin").toLocalDateTime()
                    val election = dao.addNewElection(nameElection, dateBegin)
                    call.respondRedirect("/election/${election?.id}/editElection")
                }
                else{
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }

            }

            get("/election/{id}"){
                val userSession = call.principal<UserSession>()
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/election.ftl",
                        mapOf("election" to dao.election(id),
                            "name" to userSession?.name,
                            "role" to userSession?.role)))
            }

            get ("/election/{id}/editElection"){
                val userSession = call.principal<UserSession>()
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val election = dao.election(id)
                val dateTime = election?.dateBeginElection
                val dateTimeString = DateFormat().format(dateTime)
                call.respond(
                    FreeMarkerContent(
                        "templates/election/editElection.ftl",
                        mapOf("nameElection" to election?.nameElection,
                            "dateBeginElection" to dateTimeString,
                            "name" to userSession?.name,
                            "role" to userSession?.role
                        )
                    )
                )
            }

            post("/election/{id}/editElection"){
                val userSession = call.principal<UserSession>()
                val id = call.parameters.getOrFail<Int>("id").toInt()
                if(userSession?.role == "ЦИК" || userSession?.role == "ТИК"){
                    val election = dao.election(id)
                    val dateTime = election?.dateBeginElection
                    val dateTimeString = DateFormat().format(dateTime)
                    val formParameters = call.receiveParameters()
                    val nameElection = formParameters.getOrFail("nameElection")
                    val dateBegin = formParameters.getOrFail("dateBegin").toLocalDateTime()
                    val chekEdit = dao.editElection(id, nameElection, dateBegin)
                    call.respond(
                        FreeMarkerContent(
                            "templates/election/editElection.ftl",
                            mapOf("nameElection" to election?.nameElection,
                                "dateBeginElection" to dateTimeString,
                                "name" to userSession.name,
                                "role" to userSession.role
                            )
                        )
                    )
                }
                else{
                    call.respondRedirect("/")
                }
            }

        }


    }
}
