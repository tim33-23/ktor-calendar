package com.example.dao

import com.example.dto.Election
import com.example.dto.Event
import kotlinx.datetime.LocalDateTime

interface DAOFacade {
    suspend fun allElections(): List<Election>
    suspend fun election(id: Int): Election?
    suspend fun addNewElection(nameElection: String, dataBeginElection: LocalDateTime): Election?
    suspend fun editElection(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean
    suspend fun deleteElection(id: Int): Boolean

    suspend fun allEvents(): List<Event>
    suspend fun eventsForElection(): List<Event>
    suspend fun event(id: Int): Election?
    suspend fun addNewEvent(nameElection: String, dataBeginElection: LocalDateTime): Election?
    suspend fun editEvent(id: Int,
                          idSection: Int,
                          idElection: Int,
                          description: String,
                          dateBeginEvent: LocalDateTime,
                          duration: Int): Boolean
    suspend fun deleteEvent(id: Int): Boolean



}