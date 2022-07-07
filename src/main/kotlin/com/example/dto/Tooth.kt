package com.example.dto

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

data class Tooth( val idTooth: Int,val numberTooth: Int,val idChild:  Int,val dateToothGr: Date)

object Tooths : Table() {
    val idTooth = integer("id_tooth").autoIncrement()
    val numberTooth = integer("number_tooth")
    val idChild = integer("id_child")
    val dateToothGr = date("date_of_tooth_growth")


    override val primaryKey = PrimaryKey(idTooth)

}