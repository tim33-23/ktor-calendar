package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Election(val id: Int, val nameElection: String, val dataBeginElection: LocalDateTime)

object Elections : Table() {
    val id = integer("id_election").autoIncrement()
    val nameElection = varchar("name_election", 1024)
    val dataBeginElection =  datetime("date_begin_election")

    override val primaryKey = PrimaryKey(id)

}