package com.example.dao

import com.example.dto.Election
import kotlinx.datetime.LocalDateTime

interface DAOFacade {
    suspend fun allElections(): List<Election>
    suspend fun election(id: Int): Election?
    suspend fun addNewElection(nameElection: String, dataBeginElection: LocalDateTime): Election?
    suspend fun editElection(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean
    suspend fun deleteElection(id: Int): Boolean

}