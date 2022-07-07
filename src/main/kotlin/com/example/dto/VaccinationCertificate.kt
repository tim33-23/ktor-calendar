package com.example.dto

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

data class VaccinationCertificate( val idVacChildFuct: Int,val idVaccination: Int,val idChild:  Int,val dateVacFuct: Date)

object VaccinationCertificates : Table() {
    val idVacChildFuct = integer("id_vaccination_child_fuct").autoIncrement()
    val idVaccination = integer("id_vaccination")
    val idChild = integer("id_child")
    val dateVacFuct = date("date_vaccination_fuct")


    override val primaryKey = PrimaryKey(idVacChildFuct)

}