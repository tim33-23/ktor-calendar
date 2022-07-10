package com.example.plugins

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
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
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

fun Application.configureRouting() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }


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
                        null
                    )
                )
            }

            get("/elections") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        null
                    )
                )
            }

            get("/createElection") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/createElection.ftl",
                       null
                    )
                )
            }


        }


    }
}
