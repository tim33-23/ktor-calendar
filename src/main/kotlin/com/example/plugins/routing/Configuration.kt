package com.example.plugins.routing

import com.example.module
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import io.ktor.server.application.*


fun Application.applyAllConfigurations() {
    configureAuthorization()
    configureRegistration()
    configureRouting()
    configureSecurity()
    configureTemplating()
}