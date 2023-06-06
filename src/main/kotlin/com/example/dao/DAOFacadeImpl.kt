package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dto.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.selects.select
import kotlinx.datetime.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

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

    private fun resultRowToBody(row: ResultRow) = ParametersBody(
        idBody = row[ParametersBodys.idBody],
        idChild = row[ParametersBodys.idChild],
        childHeightFact = row[ParametersBodys.childHeightFact],
        childWeightFact = row[ParametersBodys.childWeightFact],
        dateofAffixingCh = row[ParametersBodys.dateOfAffixingCh],
    )

    private fun resultRowToSleep(row: ResultRow) = Dream(
        idSleep = row[Sleep.idSleep],
        idChild = row[Sleep.idChild],
        dateTimeSlStarted = row[Sleep.dateTimeSlStarted],
        dateTimeSlEnded = row[Sleep.dateTimeSlEnded]
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

    override suspend fun updateChild(idChild: Int, name: String, dateOfBirth: LocalDate, gender: Boolean): Child? =
        dbQuery{
        Childs.update({Childs.idChild eq idChild}) {
            it[Childs.name] = name
            it[Childs.dateOfBirth] = dateOfBirth
            it[Childs.gender] = gender
        }
        child(idChild)
    }

    override suspend fun deletedChild(idChild: Int) :Boolean = dbQuery{
        Childs.deleteWhere { Childs.idChild eq idChild }>0
    }

    override suspend fun parametersBody(idBody: Int): ParametersBody? = dbQuery{
        ParametersBodys
            .select { ParametersBodys.idBody eq idBody }
            .map(:: resultRowToBody)
            .singleOrNull()
    }

    override suspend fun allParametersBody(idBody: Int): List<ParametersBody>? = dbQuery{
        ParametersBodys
            .select { ParametersBodys.idBody eq idBody }
            .map(:: resultRowToBody)
    }

    override suspend fun parametersBody(idChild: Int, dateParameters: LocalDate): ParametersBody? = dbQuery{
        ParametersBodys
            .select { (ParametersBodys.idChild eq idChild) and (ParametersBodys.dateOfAffixingCh eq dateParameters) }
            .map(:: resultRowToBody)
            .singleOrNull()
    }

    override suspend fun insertParametersBody(idChild: Int, height: Float?, weight: Float?, date: LocalDate): ParametersBody? = dbQuery{
        val insertStatement = ParametersBodys.insert {
            it[ParametersBodys.idChild] = idChild
            it[ParametersBodys.childHeightFact] = height
            it[ParametersBodys.childWeightFact] = weight
            it[ParametersBodys.dateOfAffixingCh] = date
        }
        insertStatement.resultedValues?.singleOrNull()?.let(:: resultRowToBody)
    }

    override suspend fun updateParametersBody(
        idBody: Int,
        idChild: Int,
        height: Float?,
        weight: Float?,
        date: LocalDate
    ): Boolean = dbQuery{
        ParametersBodys.update({ParametersBodys.idBody eq idBody}) {
            it[ParametersBodys.idChild] = idChild
            it[ParametersBodys.childHeightFact] = height
            it[ParametersBodys.childWeightFact] = weight
            it[ParametersBodys.dateOfAffixingCh] = date
        }>0
    }

    override suspend fun addBeginSleep(idChild: Int): Boolean = dbQuery{
        var nowDate= java.time.LocalDateTime.now()
        val insertStatment = Sleep.insert {
            it[Sleep.idChild] = idChild
            it[Sleep.dateTimeSlStarted] = nowDate.toKotlinLocalDateTime()
        }
        !insertStatment.resultedValues.isNullOrEmpty()
    }

    override suspend fun endSleep(idChild: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun dreams(idChild: Int, date: LocalDate): List<Dream> = dbQuery{
        val previewDate = date.toJavaLocalDate().minusDays(1).toKotlinLocalDate()
        Sleep
            .select{(Sleep.idChild eq idChild) and (Sleep.dateTimeSlStarted.date() eq date)
            }
            .map(:: resultRowToSleep)

    }

}