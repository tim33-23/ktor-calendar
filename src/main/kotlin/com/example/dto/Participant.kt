package com.example.dto


import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

data class Participant(val id: Int,
                       val idRole: Int,
                       val phone: String?,
                       val surname: String?,
                       val name: String?,
                       val middleName: String?,
                       val email: String,
                       val password: String)

object Participants : Table() {
    val id = integer("id_participant").autoIncrement()
    val idRole = integer("id_role").references(Roles.id)
    val phone = varchar("number_phone_participant", 11).nullable()
    val surname = varchar("surname", 100).nullable()
    val name = varchar("name", 100).nullable()
    val middleName = varchar("middle_name", 100).nullable()
    val email = varchar("email", 100)
    val password = varchar("password", 255)

    override val primaryKey = PrimaryKey(id)
}