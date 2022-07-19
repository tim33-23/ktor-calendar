package com.example.controller.routing.Services

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.Parent
import com.example.dto.UserSession
import com.example.model.ChildForTemplate
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


class ChildrenPage {
    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun getModelForChildrenPage(parent: Parent): Map<Any, Any?>?{
        val children = dao.children(idParent = parent.id)
        return if ((children != null) && children.isNotEmpty()){
            var childrenForTemplates = listOf<ChildForTemplate>()
            for(child in children){
                if (child!=null){
                    val nowDate = java.time.LocalDate.now()
                    val birthDate = child.dateOfBirth
                    val period = Period.between(birthDate.toJavaLocalDate(), nowDate)
                    val dateBirthString = DateFormat().format(birthDate)
                    val newChild = ChildForTemplate(child.idChild, parent.id, child.name, dateBirthString, child.gender, period)
                    childrenForTemplates = childrenForTemplates + newChild
                }
            }
            mapOf("children" to childrenForTemplates)
        } else{
            null
        }
    }

    suspend fun getModelForMainPage(parent: Parent, idChild: Int): Map<Any, Any?>?{
        val children = dao.children(idParent = parent.id)
        return if ((children != null) && children.isNotEmpty()){
            var child: Child? = null
            for(chil in children) {
                if (chil?.idChild == idChild) {
                    child = chil
                }
            }
            val nowDate = java.time.LocalDate.now()
            val birthDate = child?.dateOfBirth
            val period = Period.between(birthDate?.toJavaLocalDate(), nowDate)
            val dateBirthString = DateFormat().format(birthDate)
            period.months
            mapOf("child" to child, "period" to period, "birth" to dateBirthString, "idChild" to child?.idChild)
        } else{
            null
        }
    }

}