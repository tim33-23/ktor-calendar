package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Sleep( val idSleep: Int,val idChild: Int,val dateTimeSlStarted:  LocalDateTime,val dateTimeSlEnded: LocalDateTime)

object Sleeps : Table() {
    val idSleep = integer("id_sleep").autoIncrement()
    val idChild = integer("id_child")
    val dateTimeSlStarted = datetime("date_and_time_the_sleep_started")
    val dateTimeSlEnded = datetime("date_and_time_the_sleep_started")


    override val primaryKey = PrimaryKey(idSleep)

}