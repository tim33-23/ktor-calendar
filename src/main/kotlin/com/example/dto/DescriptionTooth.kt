package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class DescriptionTooth(val numTooth: Int, val nameTooth: String,val descTooth: String)

object DescriptionTooths : Table() {
    val numTooth = integer("table_of_contents").autoIncrement()
    val nameTooth = varchar("name_tooth", 50)
    val descTooth = varchar("description_tooth", 512)

    override val primaryKey = PrimaryKey(numTooth)

}