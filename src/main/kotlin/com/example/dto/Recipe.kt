package com.example.dto

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Recipe( val tableContents: String,val textInfo: String,val imgInf: String)

object Recipes : Table() {
    val idRecipe = integer("id_recipe").autoIncrement()
    val descRecipe = varchar("description_recipe",1024)
    val imgRecipe = varchar("img_dish", 1024)


    override val primaryKey = PrimaryKey(idRecipe)

}