package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Section(val id: Int, val nameSection: String)

object Sections : Table() {
    val id = integer("id_section").autoIncrement()
    val nameSection = varchar("name_section", 1024)

    override val primaryKey = PrimaryKey(id)

}