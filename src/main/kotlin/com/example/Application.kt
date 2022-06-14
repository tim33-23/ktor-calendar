package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.plugins.routing.applyAllConfigurations
import com.example.plugins.routing.configureTemplating

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    applyAllConfigurations()
}
