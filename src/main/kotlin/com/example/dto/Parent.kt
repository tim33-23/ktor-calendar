package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Parent(val id: Int, val email: String, val password: String)

object Parents : Table() {
    val id = integer("id_parents").autoIncrement()
    val email = varchar("email", 100)
    val password =  varchar("password", 64)

    override val primaryKey = PrimaryKey(id)

}