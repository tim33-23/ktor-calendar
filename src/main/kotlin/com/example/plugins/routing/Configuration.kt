package com.example.plugins.routing

import com.example.plugins.configureRouting
import io.ktor.server.application.*


fun Application.applyAllConfigurations() {
    configureAuthorization()
    configureRegistration()
    configureRouting()
    configureTemplating()
}