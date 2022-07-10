package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class ChildWeightNormal(val idWeightNorm: Int, val monthWeightRate: Int, val genderWeightNorm: Int,val averageWeight: Float,val lowerWeight: Float,val upperWeight: Float)

object ChildWeightNormals: Table() {
    val idWeightNorm = integer("id_of_the_weight_norm").autoIncrement()
    val monthWeightRate = integer("month_for_weight_rate")
    val genderWeightNorm = integer("gender_in_the_normal_weight")
    val averageWeight = float("average_weight")
    val lowerWeight = float("lower_weight_limit")
    val upperWeight = float("upper_weight_limit")


    override val primaryKey = PrimaryKey(idWeightNorm)

}