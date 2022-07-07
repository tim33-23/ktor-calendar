package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class InfoAboutComplementary( val tableContents: String,val textInfo: String,val imgInf: String)

object InfoAboutComplementarys : Table() {
    val tableContents = varchar("table_of_contents",128)
    val textInfo = varchar("text_with_information",1024)
    val imgInf = varchar("img_for_info_foods", 1024)


    override val primaryKey = PrimaryKey(tableContents)

}