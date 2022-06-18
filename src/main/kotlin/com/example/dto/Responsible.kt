package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Responsible(val idRole: Int, val idEvent: Int)

object Responsibles: Table() {
    val idRole = integer("id_role").references(Roles.id)
    val idEvent = integer("id_Event").references(Events.id)

    override val primaryKey = PrimaryKey(idRole)
}