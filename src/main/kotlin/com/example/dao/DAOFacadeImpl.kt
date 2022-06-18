package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dto.Election
import com.example.dto.Elections
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToElection(row: ResultRow) = Election(
        id = row[Elections.id],
        nameElection = row[Elections.nameElection],
        dateBeginElection = row[Elections.dateBeginElection],
    )

    override suspend fun allElections(): List<Election> = dbQuery {
        Elections.selectAll().map(::resultRowToElection)
    }

    override suspend fun election(id: Int): Election? = dbQuery {
        Elections
            .select {Elections.id eq id }
            .map(:: resultRowToElection)
            .singleOrNull()
    }

    override suspend fun addNewElection(nameElection: String, dataBeginElection: LocalDateTime): Election? = dbQuery{
        val insertStatement = Elections.insert {
            it[Elections.nameElection] = nameElection
            it[Elections.dateBeginElection] = dataBeginElection
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToElection)
    }

    override suspend fun editElection(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean = dbQuery{
        Elections.update({Elections.id eq id }) {
            it[Elections.nameElection] = nameElection
            it[Elections.dateBeginElection] = dataBeginElection
        } > 0
    }

    override suspend fun deleteElection(id: Int): Boolean = dbQuery{
        Elections.deleteWhere { Elections.id eq id } > 0
    }


}