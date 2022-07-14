package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dto.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToParent(row: ResultRow) = Parent(
        id = row[Parents.id],
        email = row[Parents.email],
        password = row[Parents.password],
    )

    private fun resultRowToChild(row: ResultRow) = Child(
        idChild = row[Childs.idChild],
        idParents = row[Childs.idParents],
        name = row[Childs.name],
        dateOfBirth = row[Childs.dateOfBirth],
        gender = row[Childs.gender],
    )

    override suspend fun parent(email: String): Parent? = dbQuery{
        Parents
            .select {Parents.email eq email }
            .map(:: resultRowToParent)
            .singleOrNull()
    }


    override suspend fun parent(id: Int): Parent? = dbQuery{
        Parents
            .select {Parents.id eq id }
            .map(:: resultRowToParent)
            .singleOrNull()
    }

    override suspend fun addParent(email: String, password: String): Parent? = dbQuery{
        val insertStatement = Parents.insert {
            it[Parents.email] = email
            it[Parents.password] = password
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToParent)
    }

    override suspend fun addChild(idParent: Int, name: String, dateOfBirth: LocalDate, gender: Boolean): Child? = dbQuery{
        val insertStatement = Childs.insert {
            it[Childs.name] = name
            it[Childs.idParents] = idParent
            it[Childs.name] = name
            it[Childs.dateOfBirth] = dateOfBirth
            it[Childs.gender] = gender
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToChild)
    }

    override suspend fun child(id: Int): Child? = dbQuery{
        Childs
            .select { Childs.idChild eq id }
            .map(:: resultRowToChild)
            .singleOrNull()
    }

    override suspend fun children(idParent: Int): List<Child>? = dbQuery{
        val children = Childs
            .select { Childs.idParents eq idParent }
            .map(::resultRowToChild)
        children
    }


}