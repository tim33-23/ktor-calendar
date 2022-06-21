package com.example.dto


import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class EventsWithSectionsAndLaw(
    val idEvent: Int,
    val nameSection: String,
    val description: String,
    val dateBeginEvent: LocalDate,
    val duration: Int,
    var laws: List<Law>?,
    var roles: List<String>?)

data class EventsWithSectionsAndLawAndDateInString(
    val idEvent: Int,
    val nameSection: String,
    val description: String,
    val dateBeginEvent: String,
    val duration: String,
    val laws: List<Law>?,
    var roles: List<String>?)

object EventsWithSectionsAndLaws : Table() {
    val idEvent = integer("id_event")
    val nameSection = varchar("name_section", 1024)
    val description = varchar("description_event", 1024)
    val dateBeginEvent = datetime("date_begin_event")
    val duration = integer("duration")
    val article = integer("article")
    val paragraph = float("paragraph")
    val part = float("part")
    val scopeLegislation = varchar("scope_of_legislation", 100)
    val roles = varchar("name_role", 100)
}