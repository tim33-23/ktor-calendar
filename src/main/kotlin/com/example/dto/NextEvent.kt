package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class NextEvent(val id: Int, val idPreviewEvent: Int)

object NextEvents : Table("next_events") {
    val id = integer("id_next_event")
    val idPreviewEvent = integer("id_event").references(Events.id)

    override val primaryKey = PrimaryKey(id)
}