package com.example.dto

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

data class ParametersBody(val idBody: Int, val idChild: Int, val childHeightFact: Float,val childWeightFact: Float,val dateofAffixingCh: Date)

object ParametersBodys: Table() {
    val idBody = integer("id_body").autoIncrement()
    val idChild = integer("id_child")
    val childHeightFact = float("height_child_fuct")
    val childWeightFact = float("weight_child_fuct")
    val dateofAffixingCh = date("date_of_affixing_of_the_child")

    override val primaryKey = PrimaryKey(idBody)

}