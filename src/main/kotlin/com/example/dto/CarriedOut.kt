package com.example.dto

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

data class CarriedOut(val idVaccination: Int,val monthVaccination: Int)

object CarriedOuts : Table() {
    val monthLure = integer("month_of_food")
    val idRecipe = integer("id_recipe")


}