package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class ChildGrowthRate(val idGrNorm: Int, val monthGrRate: Int, val genderGrNorm: Int,val averageGr: Float,val lowerGr: Float,val upperGr: Float)

object ChildGrowthRates : Table() {
    val idGrNorm = integer("id_of_the_growth_norm").autoIncrement()
    val monthGrRate = integer("month_for_growth_rate")
    val genderGrNorm = integer("gender_in_the_normal_growth")
    val averageGr = float("average_growth")
    val lowerGr = float("lower_growth_limit")
    val upperGr = float("upper_growth_limit")


    override val primaryKey = PrimaryKey(idGrNorm)

}