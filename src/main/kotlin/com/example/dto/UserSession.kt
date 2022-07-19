package com.example.dto

import io.ktor.server.auth.*

data class UserSession(val email: String, val idChild: Int?):Principal
