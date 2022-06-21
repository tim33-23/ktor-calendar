package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dto.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.kotlin.datetime.Day
import kotlin.time.Duration.Companion.days

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToElection(row: ResultRow) = Election(
        id = row[Elections.id],
        nameElection = row[Elections.nameElection],
        dateBeginElection = row[Elections.dateBeginElection],
    )

    private fun resultRowToParticipant(row: ResultRow) = Participant(
        id = row[Participants.id],
        idRole = row[Participants.idRole],
        phone = row[Participants.phone],
        surname = row[Participants.surname],
        name = row[Participants.name],
        middleName = row[Participants.middleName],
        email = row[Participants.email],
        password = row[Participants.password]
    )

    private fun resultRowToRole(row: ResultRow) = Role(
        id = row[Roles.id],
        nameRole = row[Roles.nameRole],
        documents = row[Roles.documents],
    )

    private fun resultRowToEvent(row: ResultRow) = Event(
        id = row[Events.id],
        idSection = row[Events.idSection],
        idElection = row[Events.idElection],
        description = row[Events.description],
        dateBeginEvent = row[Events.dateBeginEvent],
        duration = row[Events.duration],
    )

    private fun resultRowToEventsWithSectionAndLaw(row: ResultRow) = EventsWithSectionsAndLaw(
        idEvent = row[Events.id],
        nameSection = row[Sections.nameSection],
        description = row[Events.description],
        dateBeginEvent = row[Events.dateBeginEvent],
        duration = row[Events.duration],
        laws = null,
        roles = null
    )

    private fun resultRowSection(row: ResultRow) = Section(
        id = row[Sections.id],
        nameSection = row[Sections.nameSection]
    )

    private fun resultRowToLaw(row: ResultRow) = Law(
        id = row[Laws.id],
        article = row[Laws.article],
        paragraph = row[Laws.paragraph],
        part = row[Laws.part],
        scopeLegislation = row[Laws.scopeLegislation]
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
            it[dateBeginElection] = dataBeginElection
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToElection)
    }

    override suspend fun editElection(id: Int, nameElection: String, dataBeginElection: LocalDateTime): Boolean = dbQuery{
        Elections.update({Elections.id eq id }) {
            it[Elections.nameElection] = nameElection
            it[dateBeginElection] = dataBeginElection
        } > 0
    }

    override suspend fun deleteElection(id: Int): Boolean = dbQuery{
        Elections.deleteWhere { Elections.id eq id } > 0
    }

    override suspend fun allEvents(): List<Event> = dbQuery{
        Elections.selectAll().map(::resultRowToEvent)
    }

    override suspend fun eventsForElection(idElection: Int): List<Event> = dbQuery{
        Events.select{Events.idElection eq idElection }.map(:: resultRowToEvent)
    }

    override suspend fun event(id: Int): Event? = dbQuery {
        Events
            .select {Events.id eq id }
            .map(:: resultRowToEvent)
            .singleOrNull()
    }

    override suspend fun addNewEvent(
        idElection: Int,
        idSection: Int,
        description: String,
        dataBeginEvent: LocalDateTime,
        duration: Int
    ): Event? {
        TODO("Not yet implemented")
    }

    override suspend fun editEvent(
        id: Int,
        idElection: Int,
        idSection: Int,
        description: String,
        dateBeginEvent: LocalDateTime,
        duration: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun participant(email: String, password: String): Participant? = dbQuery{
        Participants.
        select{Participants.email.eq(email) and Participants.password.eq(password)}
            .map(::resultRowToParticipant).first()
    }

    override suspend fun addNewParticipant(
        surname: String?,
        name: String,
        middleName: String?,
        email: String,
        password: String
    ): Participant? {
        TODO("Not yet implemented")
    }

    override suspend fun editParticipant(
        surname: String?,
        name: String,
        middleName: String?,
        email: String,
        password: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteParticipant(id: Int): Boolean {
        TODO("Not yet implemented")
    }


    override suspend fun role(id: Int): Role? = dbQuery{
        Roles.select { Roles.id eq id }
            .map(::resultRowToRole)
            .singleOrNull()
    }

    override suspend fun rolesForElection(idElection: Int): List<Role> = dbQuery{
        val roles = Events.join(Responsibles, JoinType.INNER, Responsibles.idEvent, Events.id)
            .join(Roles, JoinType.INNER, Roles.id, Responsibles.idRole)
            .select { Events.idElection eq idElection}.map(::resultRowToRole)
        roles.toSet().toList()
    }

    override suspend fun rolesForEvent(idEvent: Int): List<Role> = dbQuery{
        val roles = Events.join(Responsibles, JoinType.INNER, Responsibles.idEvent, Events.id)
            .join(Roles, JoinType.INNER, Roles.id, Responsibles.idRole)
            .select { Events.id eq idEvent}.map(::resultRowToRole)
        roles.toSet().toList()
    }

    override suspend fun section(idSection: Int): Section? = dbQuery{
        Sections
            .select {Sections.id eq idSection}
            .map(:: resultRowSection)
            .singleOrNull()
    }

    override suspend fun sectionsForEvents(events: List<Event>): List<Section> = dbQuery{
        val sections : MutableList<Section> = mutableListOf()
        for(event in events){
            val newSection = Sections.select {Sections.id eq event.idSection}
                .map(::resultRowSection)
                .singleOrNull()
            if(!sections.contains(newSection) && newSection!=null){
                sections += newSection
            }
        }
        return@dbQuery sections
    }

    override suspend fun sectionsForElection(election: Election): List<Section> = dbQuery{
        val events = eventsForElection(election.id)
        sectionsForEvents(events)
    }

    override suspend fun law(id: Int): Law? = dbQuery{
        Laws
            .select {Laws.id eq id}
            .map(:: resultRowToLaw)
            .singleOrNull()
    }

    override suspend fun allLaws(): List<Law> = dbQuery{
        Laws.selectAll().map(::resultRowToLaw )

    }

    override suspend fun lawsForEvent(idEvent: Int): List<Law> = dbQuery{

    }

    override suspend fun eventsWithSectionAndLows(election: Election): List<EventsWithSectionsAndLaw>? = dbQuery{
        val events = Events.join(Sections, JoinType.INNER, Events.idSection, Sections.id)
            .join(Describeds, JoinType.INNER, Events.id, Describeds.idEvent)
            .join(Laws, JoinType.INNER, Describeds.idLaw, Laws.id)
            .join(Responsibles, JoinType.INNER, Responsibles.idEvent, Events.id)
            .join(Roles, JoinType.INNER, Roles.id, Responsibles.idRole)

        val eventsWithRolesAndLaws = events
            .select{Events.idElection eq election.id}
            .orderBy(Sections.nameSection).map(::resultRowToEventsWithSectionAndLaw)

        for(event in eventsWithRolesAndLaws){
            var roles = mutableListOf<String>()
            val r = events.select{
                (Events.idElection eq election.id) and (Events.id eq event.idEvent)}
                .map(::resultRowToRole)
            if(r.isNotEmpty())
            for(role in r){
                roles+=role.nameRole
            }
            event.roles = roles
        }

        for(event in eventsWithRolesAndLaws){
            var laws = mutableListOf<Law>()
            val listLaws = events.select{
                (Events.idElection eq election.id) and (Events.id eq event.idEvent)}
                .map(::resultRowToLaw)
            if(listLaws.isNotEmpty())
                for(law in listLaws){
                    laws+=listLaws
                }
            event.laws = laws
        }
        eventsWithRolesAndLaws
    }

    override suspend fun addNewEventWithSectionAndLow(
        election: Election,
        section: Section,
        law: Law,
        role: Role,
        description: String,
        dataBeginEvent: LocalDate,
        duration: Int,
        idPreviewEvent: Int?
    ): Boolean = dbQuery{
        val newSection : Section?
         if(section.id<0){
            val insertStatement = Sections.insert {
                it[nameSection] = section.nameSection
            }
            newSection = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowSection)
        }
        else{
             newSection =
                 Sections.select {Sections.id eq section.id}
                 .map(:: resultRowSection)
                 .singleOrNull()
        }

        val newRole : Role?
        if(role.id <0){
            val insertStatement = Roles.insert {
                it[nameRole] = role.nameRole
                it[documents] = role.documents
            }
            newRole = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRole)
        }
        else{
            newRole =
            Roles.select {Roles.id eq role.id}
                .map(:: resultRowToRole)
                .singleOrNull()
        }

        val newLaw : Law?
        if(law.id < 0){
            val insertStatement = Laws.insert {
                it[Laws.part] = law.part
                it[Laws.article] = law.article
                it[Laws.paragraph] = law.paragraph
                it[Laws.scopeLegislation] = law.scopeLegislation
            }
            newLaw = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToLaw)
        }
        else{
            newLaw =
                Laws.select {Laws.id eq law.id}
                    .map(:: resultRowToLaw)
                    .singleOrNull()
        }

        if(newLaw==null || newRole==null || newSection==null)
           return@dbQuery false

        var newEvent: Event? = null

        if(idPreviewEvent!=null){
            val oldEvent = event(idPreviewEvent)
            if(oldEvent!=null) {
                val insertStatement = Events.insert {
                    it[idSection] = newSection.id
                    it[idElection] = election.id
                    it[Events.description] = description
                    it[dateBeginEvent] = oldEvent.dateBeginEvent.plus(oldEvent.duration, DateTimeUnit.DAY)
                    it[Events.duration] = duration
                }
                newEvent = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToEvent)
            }
        }
        else{
            val insertStatement = Events.insert {
                it[idSection] = newSection.id
                it[idElection] = election.id
                it[Events.description] = description
                it[dateBeginEvent] = dataBeginEvent
                it[Events.duration] = duration
            }
            newEvent = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToEvent)
        }


        if(newEvent!=null){
            Describeds.insert {
                it[Describeds.idEvent] = newEvent.id
                it[Describeds.idLaw] = newLaw.id
            }
            Responsibles.insert {
                it[idRole] = newRole.id
                it[idEvent] = newEvent.id
            }
            if(idPreviewEvent!=null){
                NextEvents.insert {
                    it[id] = newEvent.id
                    it[NextEvents.idPreviewEvent] = idPreviewEvent
                }
            }
            return@dbQuery true
        }



       false
    }

}