package com.example.plugins

import com.example.services.ElectionsService
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {


    routing {
        // Static plugin. Try to access `/static/index.ftl`
        static("/static") {
            resources("static/img")
        }
        get("/elections"){
            val electionsService = ElectionsService()
            call.respond(FreeMarkerContent("templates/elections.ftl", mapOf("elections" to electionsService.getElectionsForRegistration()), ""))
        }
        post("/election"){
            val formParameters = call.receiveParameters()
            val election = formParameters["election"].toString()
            val events = ElectionsService().getEvents(election)
            call.respond(FreeMarkerContent("templates/election.ftl", mapOf("events" to events, "election" to election), ""))
        }
    }
}
