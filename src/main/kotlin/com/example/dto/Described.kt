package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Described(val idEvent: Int, val idLaw: Int)

object Describeds: Table() {
    val idEvent = integer("id_event").references(Events.id)
    val idLaw = integer("id_Event").references(Laws.id)

    override val primaryKey = PrimaryKey(arrayOf(idEvent, idLaw))
}