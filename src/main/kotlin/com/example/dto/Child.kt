package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Child(val idChild: Int, val idParents: Int, val name: String,val surname: String,val middlename: String,val dateOfBirth: LocalDateTime, val gender: Int)

object Childs : Table() {
    val idChild = integer("id_child").autoIncrement()
    val idParents = integer("id_parents")
    val name = varchar("name_child", 50)
    val surname = varchar("surname_child", 50)
    val middlename = varchar("middle_child", 50)
    val dateOfBirth =  datetime("date_of_birth")
    val gender = integer("gender_child")

    override val primaryKey = PrimaryKey(idChild)

}