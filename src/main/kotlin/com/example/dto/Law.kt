package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Law(val id: Int,
                 val idSection: Int,
                 val idElection: Int,
                 val description: String,
                 val dateBeginEvent: LocalDateTime,
                 val duration: Int)

object Laws : Table() {
    val id = integer("id_law").autoIncrement()
    val article = integer("article")
    val paragraph = float("paragraph")
    val description = varchar("part", 1024)
    val dateBeginEvent = datetime("scope_of_legislation")

    override val primaryKey = PrimaryKey(id)
}