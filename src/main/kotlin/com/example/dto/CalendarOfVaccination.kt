package com.example.dto

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

data class CalendarOfVaccination( val idVaccination: Int,val monthVaccination: Int,val dayVaccination:  Int,val nameVaccination: String)

object CalendarOfVaccinations : Table() {
    val idVaccination = integer("id_vaccination").autoIncrement()
    val monthVaccination = integer("month_vaccination")
    val dayVaccination = integer("day_vaccination")
    val nameVaccination = varchar("name_vaccination",50)


    override val primaryKey = PrimaryKey(idVaccination)

}