package com.example.plugins.routing

import com.example.converts.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import kotlin.time.Duration.Companion.days

fun Application.configureRouting() {


    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun editeDateNextEvent(nextEvents: List<NextEvent>, dateEndPreviewEvent: LocalDate): Boolean {
        if (nextEvents.isEmpty()) {
            return true
        } else {
            for (nextEvent in nextEvents) {
                val event = dao.event(nextEvent.id)
                var addCheck: Boolean
                if (event != null) {
                    addCheck = dao.editEvent(
                        event.id,
                        event.idElection,
                        event.idSection,
                        event.description,
                        dateEndPreviewEvent,
                        event.duration
                    )
                }
                val dateEndEvent = event?.let { dateEndPreviewEvent.plus(it.duration, DateTimeUnit.DAY) }
                addCheck = dateEndEvent?.let { editeDateNextEvent(dao.listNextEvents(nextEvent.id), it) } == true
                if (!addCheck)
                    return false
            }
            return true;
        }

    }


    fun getElectionForTemlates(election: Election): ElectionForFremarker {
        return ElectionForFremarker(election.id, election.nameElection, DateFormat().format(election.dateBeginElection))
    }


    routing {
        authenticate("auth-session") {

            post("/addEvent") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idElection = formParameters.getOrFail("idElection").toInt()
                    val election = dao.election(idElection)
                    if (formParameters.names().size == 1) {
                        var sections: List<Section> = listOf()
                        var roles: List<Role> = listOf()
                        var laws: List<Law> = listOf()
                        var events: List<Event> = listOf()
                        if (election != null) {
                            sections = dao.sectionsForElection(election)
                            roles = dao.rolesForElection(election.id)
                            laws = dao.allLaws()
                            events = dao.eventsForElection(election.id)
                        }
                        call.respond(
                            FreeMarkerContent(
                                "templates/event/addEvent.ftl",
                                mapOf(
                                    "election" to election,
                                    "sections" to sections,
                                    "roles" to roles,
                                    "laws" to laws,
                                    "events" to events,
                                    "name" to userSession.name,
                                    "role" to userSession.role
                                )
                            )
                        )
                    }
                    val checkOnSelectionSection = formParameters["checkOnSelectionSection"]
                    val section: Section?
                    if (checkOnSelectionSection != null) {
                        val idSeciton = formParameters["idSection"].toString().toInt()
                        section = dao.section(idSeciton)
                    } else {
                        val nameSection = formParameters["nameSection"].toString()
                        section = Section(-1, nameSection)
                    }

                    val checkOnSelectionLaw = formParameters["checkOnSelectionLaw"]
                    var law: Law? = null
                    if (checkOnSelectionLaw != null) {
                        val idLaw = formParameters["idLaw"].toString().toInt()
                        law = dao.law(idLaw)
                    } else {
                        val article = formParameters["article"]?.toIntOrNull()
                        val paragraph = formParameters["paragraph"]?.toFloatOrNull()
                        val part = formParameters["part"]?.toFloatOrNull()

                        val scope = formParameters["scope"].toString()
                        law = article?.let { it1 -> Law(-1, it1, paragraph, part, scope) }
                    }

                    val checkOnSelectionRole = formParameters["checkOnSelectionRole"]
                    var role: Role? = null
                    if (checkOnSelectionRole != null && formParameters["idRole"] != null) {
                        val idRole = formParameters["idRole"].toString().toInt()
                        role = dao.role(idRole)
                    } else {
                        val nameRole = formParameters["createRole"].toString()
                        role = Role(-1, nameRole, null)
                    }

                    var message: String?
                    if (election != null && section != null && law != null && role != null) {
                        val description = formParameters["descriptionEvent"].toString()
                        val dataBeginEvent = formParameters["dateBeginEvent"]?.toLocalDate()
                        val durationEvent = formParameters["durationEvent"].toString().toInt()
                        val checkOnPreviewEvent = formParameters["checkOnPreviewEvent"]
                        var idPreviewEvent: Int? = null
                        if (checkOnPreviewEvent != null) {
                            idPreviewEvent = formParameters["idEvent"].toString().toInt()
                        }
                        val check = dataBeginEvent?.let { it1 ->
                            dao.addNewEventWithSectionAndLow(
                                election,
                                section,
                                law!!,
                                role!!,
                                description,
                                it1,
                                durationEvent,
                                idPreviewEvent
                            )
                        }
                        if (check == true)
                            message = "Событие добавлено"
                    }
                    var sections: List<Section> = listOf()
                    var roles: List<Role> = listOf()
                    var laws: List<Law> = listOf()
                    var events: List<Event> = listOf()
                    if (election != null) {
                        sections = dao.sectionsForElection(election)
                        roles = dao.rolesForElection(election.id)
                        laws = dao.allLaws()
                        events = dao.eventsForElection(election.id)
                    }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/addEvent.ftl",
                            mapOf(
                                "election" to election,
                                "sections" to sections,
                                "roles" to roles,
                                "laws" to laws,
                                "events" to events,
                                "name" to userSession.name,
                                "role" to userSession.role
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }

            get("/addEvent") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val idElection = call.request.queryParameters["idElection"]?.toIntOrNull()
                    if (idElection != null) {
                        val election = dao.election(idElection)
                        var sections: List<Section> = listOf()
                        var roles: List<Role> = listOf()
                        var laws: List<Law> = listOf()
                        var events: List<Event> = listOf()
                        if (election != null) {
                            sections = dao.sectionsForElection(election)
                            roles = dao.rolesForElection(election.id)
                            laws = dao.allLaws()
                            events = dao.eventsForElection(election.id)
                        }
                        call.respond(
                            FreeMarkerContent(
                                "templates/event/addEvent.ftl",
                                mapOf(
                                    "election" to election,
                                    "sections" to sections,
                                    "roles" to roles,
                                    "laws" to laws,
                                    "events" to events,
                                    "name" to userSession.name,
                                    "role" to userSession.role
                                )
                            )
                        )
                    }
                }
            }


            get("/selectEventForEdit") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val idElection = call.request.queryParameters["idElection"]?.toIntOrNull()
                    if (idElection != null) {
                        val election = dao.election(idElection)
                        val events = election?.let { it1 -> dao.eventsWithSectionAndLows(it1) }
                        var eventsForFreeMarker: List<EventsWithSectionsAndLawAndDateInString> = mutableListOf()
                        if (events != null) {
                            for (event in events) {
                                val eventForFreeMarker = EventsWithSectionsAndLawAndDateInString(
                                    event.idEvent,
                                    event.nameSection,
                                    event.description,
                                    DateFormat().format(event.dateBeginEvent),
                                    DateFormat().format(event.dateBeginEvent, event.duration),
                                    event.laws,
                                    event.roles
                                )
                                eventsForFreeMarker += eventForFreeMarker
                            }
                        }
                        call.respond(
                            FreeMarkerContent(
                                "templates/event/selectEventForEdited.ftl",
                                mapOf(
                                    "events" to eventsForFreeMarker,
                                    "name" to userSession.name,
                                    "role" to userSession.role
                                )
                            )
                        )
                    }
                }
            }


            post("/editEvent") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role
                            )
                        )
                    )
                }
                call.respond(
                    FreeMarkerContent(
                        "index.ftl",
                        mapOf("name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }



            post("/addRole") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    val checkOnSelectionRole = formParameters["checkOnSelectionRole"]
                    var role: Role? = null
                    var message: String? = null
                    var check: Boolean
                    if (checkOnSelectionRole != null && formParameters["idRole"] != null) {
                        val idRole = formParameters["idRole"].toString().toInt()
                        role = dao.role(idRole)
                        check = dao.addRoleForEvent(idRole, idEvent)
                    } else {
                        val nameRole = formParameters["createRole"].toString()
                        val eventsForRole = dao.rolesForEvent(idEvent)
                        var checkWhatEventHaveRole: Boolean = false
                        for (ev in eventsForRole) {
                            if (ev.nameRole == nameRole) {
                                checkWhatEventHaveRole = true
                            }
                        }
                        if (!checkWhatEventHaveRole && nameRole != "")
                            check = dao.addNewRoleForEvent(nameRole, idEvent)
                        else
                            check = false
                    }
                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }

                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    if (check)
                        message = "Роль добавлена"
                    else
                        message = "Проверьте правильность задания параметров для добавления роли"
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }

            }

            post("/deletedRole") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    val idRole = formParameters.getOrFail("idRole").toInt()
                    val message = dao.deleteRoleForEvent(idRole, idEvent)
                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }

            post("/addLaw") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    var message: String?
                    val checkOnSelectionLaw = formParameters["checkOnSelectionLaw"]

                    message = if (checkOnSelectionLaw != null) {
                        val idLaw = formParameters["idLaw"].toString().toInt()
                        dao.addLawForEvent(idLaw, idEvent)
                    } else {
                        val article = formParameters["article"]?.toIntOrNull()
                        val paragraph = formParameters["paragraph"]?.toFloatOrNull()
                        val part = formParameters["part"]?.toFloatOrNull()
                        val scope = formParameters["scope"].toString()
                        if (article == null && paragraph == null && part == null && scope.isEmpty()) {
                            "Все поля пустые"
                        } else {
                            val law = Law(-1, article, paragraph, part, scope)
                            dao.addNewLawForEvent(law, idEvent)
                        }
                    }

                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }


            post("/deletedLaw") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    val idLaw = formParameters.getOrFail("idLaw").toInt()
                    var message: String?

                    message = dao.deletedLawForEvent(idLaw, idEvent)

                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }

            post("/editSection") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    var message: String?
                    val checkOnSelectionSection = formParameters["checkOnSelectionSection"]

                    message = if (checkOnSelectionSection != null) {
                        val idSection = formParameters["idSection"].toString().toInt()
                        dao.editedSectionForEvent(idSection, idEvent)
                    } else {
                        if (formParameters["nameSection"].toString() != "") {
                            val nameSection = formParameters["nameSection"].toString()
                            dao.editedNewSectionForEvent(nameSection, idEvent)
                        } else {
                            "Вы пытаетесь создать пустой раздел"
                        }
                    }

                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }

            post("/editDescriptionEvent") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    var message: String?
                    val descriptionEvent = formParameters["descriptionEvent"].toString()

                    message = if (descriptionEvent == "") {
                        "Ошибка. Отсутствует описание мероприятия"
                    } else {
                        val event = dao.event(idEvent)
                        if (event != null) {
                            val check = dao.editEvent(
                                event.id,
                                event.idElection,
                                event.idSection,
                                descriptionEvent,
                                event.dateBeginEvent,
                                event.duration
                            )
                            if (check) {
                                "Описание изменилось"
                            }

                            "Ошибка при изменении описания мероприятия"
                        } else {
                            "Ошибка при изменении описания мероприятия"
                        }
                    }
                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }


            post("/editDateEvent") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    var message: String?
                    var dateBeginEvent = formParameters["dateBeginEvent"]?.toLocalDate()
                    var duration = formParameters["durationEvent"]?.toInt()
                    var addCheck = true
                    var event = dao.event(idEvent)
                    if (event != null) {
                        if (dateBeginEvent == null)
                            dateBeginEvent = event.dateBeginEvent
                        if (duration == null)
                            duration = event.duration
                        addCheck = dao.editEvent(
                            event.id,
                            event.idElection,
                            event.idSection,
                            event.description,
                            dateBeginEvent,
                            duration
                        )
                        var nextEvents = dao.listNextEvents(event.id)

                        if (nextEvents.isNotEmpty()) {
                            val dateEndEvent = event.dateBeginEvent.plus(event.duration, DateTimeUnit.DAY)
                            addCheck = editeDateNextEvent(nextEvents, dateEndEvent)
                        }
                    }
                    if (addCheck) {
                        message = "Даты поменялись"
                    } else {
                        message = "Даты не удалось изменить"
                    }

                    event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    val sections = election?.let { it1 -> dao.sectionsForElection(it1) }
                    val roles = event?.let { it1 -> dao.rolesForElection(it1.idElection) }
                    val rolesForEvent = dao.rolesForEvent(idEvent)
                    val laws = dao.allLaws()
                    val lawsForEvent = dao.lawsForEvent(idEvent)
                    val electionForFremarker = election?.let { it1 -> getElectionForTemlates(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/editEvent.ftl",
                            mapOf(
                                "election" to electionForFremarker,
                                "sections" to sections,
                                "roles" to roles,
                                "rolesForEvent" to rolesForEvent,
                                "lawsForEvent" to lawsForEvent,
                                "laws" to laws,
                                "event" to event,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "message" to message
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }


            post("deletedEvent") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idEvent = formParameters.getOrFail("idEvent").toInt()
                    val event = dao.event(idEvent)
                    val election = event?.let { it1 -> dao.election(it1.idElection) }
                    dao.deleteEvent(idEvent)

                    val events = election?.let { it1 -> dao.eventsWithSectionAndLows(it1) }
                    var eventsForFreeMarker: List<EventsWithSectionsAndLawAndDateInString> = mutableListOf()
                    if (events != null) {
                        for (event in events) {
                            val eventForFreeMarker = EventsWithSectionsAndLawAndDateInString(
                                event.idEvent,
                                event.nameSection,
                                event.description,
                                DateFormat().format(event.dateBeginEvent),
                                DateFormat().format(event.dateBeginEvent, event.duration),
                                event.laws,
                                event.roles
                            )
                            eventsForFreeMarker += eventForFreeMarker
                        }
                    }
                    call.respond(
                        FreeMarkerContent(
                            "templates/event/selectEventForEdited.ftl",
                            mapOf(
                                "events" to eventsForFreeMarker,
                                "name" to userSession.name,
                                "role" to userSession.role
                            )
                        )
                    )
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }

        }
    }
}