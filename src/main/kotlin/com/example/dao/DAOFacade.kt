package com.example.dao

import com.example.dto.*
import freemarker.template.Template
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface DAOFacade {


    //Election
    suspend fun allElections(): List<Election>
    suspend fun election(id: Int): Election?
    suspend fun addNewElection(nameElection: String, dataBeginElection: LocalDateTime): Election?
    suspend fun editElection(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean
    suspend fun deleteElection(id: Int): Boolean
    suspend fun addNewElectionWithTemplate(nameElection: String, dataBeginElection: LocalDateTime, idElectionTemplate: Int): Election?

    //Event
    suspend fun allEvents(): List<Event>
    suspend fun eventsForElection(idElection: Int): List<Event>
    suspend fun event(id: Int): Event?
    suspend fun addNewEvent(idElection: Int,
                            idSection: Int,
                            description: String,
                            dataBeginEvent: LocalDate,
                            duration: Int): Event?

    suspend fun editEvent(id: Int,
                          idElection: Int,
                          idSection: Int,
                          description: String,
                          dateBeginEvent: LocalDate,
                          duration: Int): Boolean

    suspend fun deleteEvent(id: Int): Boolean
    suspend fun checkOnNextEvents(idEvent: Int): Boolean
    suspend fun listNextEvents(idEvent: Int): List<NextEvent>
    suspend fun previewEvent(idNextEvent: Int): List<NextEvent>



    //Participant
    suspend fun participant(email: String, password: String): Participant?
    suspend fun addNewParticipant(surname: String?,
                                  name: String,
                                  middleName: String?,
                                  email: String,
                                  phone: String,
                                  password: String,
                                  idRole: Int): Participant?

    suspend fun editParticipant(surname: String?,
                                name: String,
                                middleName: String?,
                                email: String,
                                password: String): Boolean
    suspend fun deleteParticipant(id: Int): Boolean

    suspend fun participantRole(email: String): Participant?

    //Role
    suspend fun role(id: Int): Role?
    suspend fun updateRole(id : Int, nameRole: String, docum: String): Boolean
    suspend fun rolesForElection(idElection: Int): List<Role>
    suspend fun rolesForEvent(idEvent: Int): List<Role>
    suspend fun addRoleForEvent(idRole: Int, idEvent: Int): Boolean
    suspend fun addNewRoleForEvent(nameRole: String, idEvent: Int): Boolean
    suspend fun deleteRoleForEvent(idRole: Int, idEvent: Int): String?
    suspend fun addNewRoleForEvent(nameRole: String, documents: String?, idEvent: Int): Boolean

    //Section
    suspend fun section(idSection: Int): Section?
    suspend fun sectionsForEvents(events: List<Event>): List<Section>
    suspend fun sectionsForElection(election: Election): List<Section>
    suspend fun editedSectionForEvent(idSection: Int, idEvent: Int): String?
    suspend fun editedNewSectionForEvent(nameSection: String, idEvent: Int): String?
    suspend fun addNewSection(nameElection: String): Section?

    //Law
    suspend fun law(id: Int): Law?
    suspend fun allLaws(): List<Law>
    suspend fun lawsForEvent(idEvent: Int): List<Law>
    suspend fun addLawForEvent(idLaw: Int, idEvent: Int): String?
    suspend fun addNewLawForEvent(law: Law, idEvent: Int): String?
    suspend fun deletedLawForEvent(idLaw: Int, idEvent: Int): String?
    suspend fun addNewLaw(paragraph: Float?, article: Int?, part: Float?, scopeLegislation: String): Law?
    suspend fun lawsForElection(idElection: Int): List<Law>
    //table next_event
    suspend fun addInNewEventTable(idNextEvent: Int, idPreviewEvent: Int) : NextEvent?

    //complex
    suspend fun eventsWithSectionAndLows(election: Election): List<EventsWithSectionsAndLaw>?
    suspend fun addNewEventWithSectionAndLow(election: Election,
                                             section: Section,
                                             law: Law,
                                             role: Role,
                                             description: String,
                                             dataBeginEvent: LocalDate,
                                             duration: Int, idPreviewEvent: Int?): Boolean

    suspend fun selectNextEvent(idNextEvent: Int): NextEvent?
}