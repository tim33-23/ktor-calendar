package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Event(val id: Int,
                 val idSection: Int,
                 val idElection: Int,
                 val description: String,
                 val dateBeginEvent: LocalDateTime,
                 val duration: Int)

object Events : Table() {
    val id = integer("id_event").autoIncrement()
    val idSection = integer("id_section").references(Sections.id)
    val idElection = integer("id_election").references(Elections.id)
    val description = varchar("description_event", 1024)
    val dateBeginEvent = datetime("date_begin_event")
    val duration =  integer("duration")

    override val primaryKey = PrimaryKey(id)

}