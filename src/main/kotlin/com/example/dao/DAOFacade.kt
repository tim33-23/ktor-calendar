package com.example.dao

import com.example.dto.Election
import com.example.dto.Event
import com.example.dto.Participant
import com.example.dto.Participants
import kotlinx.datetime.LocalDateTime
import sun.security.util.Password

interface DAOFacade {

    //Election
    suspend fun allElections(): List<Election>
    suspend fun election(id: Int): Election?
    suspend fun addNewElection(nameElection: String, dataBeginElection: LocalDateTime): Election?
    suspend fun editElection(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean
    suspend fun deleteElection(id: Int): Boolean


    //Event
    suspend fun allEvents(): List<Event>
    suspend fun eventsForElection(): List<Event>
    suspend fun event(id: Int): Event?
    suspend fun addNewEvent(nameElection: String, dataBeginElection: LocalDateTime): Event?
    suspend fun editEvent(id: Int,
                          idSection: Int,
                          idElection: Int,
                          description: String,
                          dateBeginEvent: LocalDateTime,
                          duration: Int): Boolean
    suspend fun deleteEvent(id: Int): Boolean


    //Participant
    suspend fun participant(email: String, password: String): Participant?
    suspend fun addNewParticipant(nameElection: String, dataBeginElection: LocalDateTime): Participant?
    suspend fun editParticipant(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean
    suspend fun deleteParticipant(id: Int): Boolean


}