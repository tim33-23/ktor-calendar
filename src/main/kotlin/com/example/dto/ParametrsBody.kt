package com.example.dto

import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import java.util.*

data class ParametersBody(val idBody: Int, val idChild: Int, val childHeightFact: Float?, val childWeightFact: Float?, val dateofAffixingCh: LocalDate)

object ParametersBodys: Table("parameters_bodys") {
    val idBody = integer("id_body").autoIncrement()
    val idChild = integer("id_child")
    val childHeightFact = float("height_childer_fuct").nullable()
    val childWeightFact = float("weight_childer_fuct").nullable()
    val dateOfAffixingCh = date("date_of_affixing_of_the_child")

    override val primaryKey = PrimaryKey(idBody)

}