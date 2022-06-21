package com.example.plugins.routing

import com.example.plugins.configureElection
import io.ktor.server.application.*


fun Application.applyAllConfigurations() {
    configureAuthorization()
    configureRegistration()
    configureElection()
    configureRouting()
    configureTemplating()

}