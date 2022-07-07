package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Lure( val monthLure: Int)

object Lures : Table() {
    val monthLure = integer("month_of_food")

    override val primaryKey = PrimaryKey(monthLure)

}