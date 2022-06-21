package com.example.dao

import com.example.dto.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction



object DatabaseFactory {
    fun init() {
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val jdbcURL = "jdbc:mysql://localhost:3306/plan"
        val user = "root"
        val password = "175293"
        val database = Database.connect(jdbcURL, driverClassName, user, password)
        transaction(database) {
            SchemaUtils.create(Describeds)
            SchemaUtils.create(Elections)
            SchemaUtils.create(Events)
            //SchemaUtils.create(EventsWithSectionsAndLaws)
            SchemaUtils.create(Laws)
            SchemaUtils.create(NextEvents)
            SchemaUtils.create(Participants)
            SchemaUtils.create(Responsibles)
            SchemaUtils.create(Roles)
            SchemaUtils.create(Sections)

        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}