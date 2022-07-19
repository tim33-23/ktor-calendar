package com.example.dto


import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Child(val idChild: Int, val idParents: Int, val name: String, val dateOfBirth: LocalDate, val gender: Boolean)

object Childs : Table() {
    val idChild = integer("id_child").autoIncrement()
    val idParents = integer("id_parents")
    val name = varchar("name_child", 50)
    val dateOfBirth = date("date_of_birth")
    val gender = bool("gender_child")

    override val primaryKey = PrimaryKey(idChild)

}