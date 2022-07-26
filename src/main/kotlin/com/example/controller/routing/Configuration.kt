package com.example.plugins.routing

import com.example.controller.routing.*
import com.example.plugins.configureRoutingChild
import io.ktor.server.application.*


fun Application.applyAllConfigurations() {
    configureAuthorization()
    configureRegistration()
    configureRoutingChild()
    configureTemplating()
    configureHeight()
    configureWeight()
    configureSleep()
    configureTeeth()
    configurePricorm()
    configureVacination()
}