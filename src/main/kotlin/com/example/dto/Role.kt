package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Role(val id: Int, val nameRole: String, val documents: String)

object Roles : Table() {
    val id = integer("id_role")
    val nameRole = varchar("name_role", 1024)
    val documents = varchar("list_of_documents", 1024)
    override val primaryKey = PrimaryKey(id)
}