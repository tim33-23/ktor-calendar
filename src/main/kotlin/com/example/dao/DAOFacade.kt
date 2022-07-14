package com.example.dao

import com.example.dto.Child
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

}