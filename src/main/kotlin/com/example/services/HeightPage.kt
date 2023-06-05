package com.example.services

import com.example.controller.routing.Services.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.model.ChildForTemplate
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.toJavaLocalDate
import java.time.Period


class HeightPage {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun getModelForHeightPage(idChild: Int): Map<Any, Any?>?{
        val child = dao.child(idChild)
        return if (child != null){
                    val nowDate = java.time.LocalDate.now()
                    val birthDate = child.dateOfBirth
                    val period = Period.between(birthDate.toJavaLocalDate(), nowDate)
                    val dateBirthString = DateFormat().format(birthDate)
                    val newChild = ChildForTemplate(child.idChild, child.idParents, child.name, dateBirthString, child.gender, period)
                    newChild
            mapOf("child" to newChild)
        } else{
            null
        }
    }

    suspend fun getHeights(idChild: Int): Map<Any, Any?>?{
        val child = dao.child(idChild)
        val parametrs = dao.allParametersBody(idChild)
        val date = ArrayList<Period>()
        parametrs?.forEach { parametr ->
            date.add( Period.between(child?.dateOfBirth?.toJavaLocalDate(), parametr?.dateofAffixingCh?.toJavaLocalDate()))

        }

    }

}