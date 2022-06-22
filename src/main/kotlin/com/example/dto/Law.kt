package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Law(val id: Int,
                 val article: Int?,
                 val paragraph: Float?,
                 val part: Float?,
                 val scopeLegislation: String)

object Laws : Table() {
    val id = integer("id_law").autoIncrement()
    val article = integer("article").nullable()
    val paragraph = float("paragraph").nullable()
    val part = float("part").nullable()
    val scopeLegislation = varchar("scope_of_legislation", 100)

    override val primaryKey = PrimaryKey(id)
}