package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Dream( val idSleep: Int,val idChild: Int,val dateTimeSlStarted:  LocalDateTime,val dateTimeSlEnded: LocalDateTime?)

object Sleep : Table() {
    val idSleep = integer("id_sleep").autoIncrement()
    val idChild = integer("id_child")
    val dateTimeSlStarted = datetime("date_and_time_the_sleep_started")
    val dateTimeSlEnded = datetime("date_and_time_the_sleep_ended").nullable()


    override val primaryKey = PrimaryKey(idSleep)
}

data class DreamForTemplate(val idSleep: Int,val idChild: Int,val dateTimeSlStarted:  String, val dateTimeSlEnded: String?)

