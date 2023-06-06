package com.example.dao

import com.example.dto.Child
import com.example.dto.Dream
import com.example.dto.ParametersBody
import com.example.dto.Parent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


interface DAOFacade {

    suspend fun parent(email: String): Parent?
    suspend fun parent(id: Int): Parent?
    suspend fun addParent(email: String, password: String): Parent?

    suspend fun addChild(idParent: Int, name: String, dateOfBirth: LocalDate, gender: Boolean): Child?
    suspend fun child(id: Int): Child?
    suspend fun children(idParent: Int): List<Child?>?
    suspend fun updateChild(idChild: Int, name: String, dateOfBirth: LocalDate, gender: Boolean): Child?
    suspend fun deletedChild(idChild: Int): Boolean

    suspend fun parametersBody(idBody: Int): ParametersBody?
    suspend fun allParametersBody(idBody: Int): List<ParametersBody>?
    suspend fun parametersBody(idChild: Int, dateParameters: LocalDate): ParametersBody?
    suspend fun insertParametersBody(idChild: Int, height: Float?, weight: Float?, date: LocalDate): ParametersBody?
    suspend fun updateParametersBody(idBody: Int, idChild: Int, height: Float?, weight: Float?, date: LocalDate): Boolean

    suspend fun addBeginSleep(idChild: Int): Boolean
    suspend fun endSleep(idChild: Int): Boolean
    suspend fun dreams(idChild: Int, date: LocalDate): List<Dream>

}