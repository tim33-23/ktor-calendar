package com.example.plugins

import com.example.converts.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import java.util.*


fun Application.configureElection() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }


    fun getElectionForTemlates(election: Election): ElectionForFremarker {
        return ElectionForFremarker(election.id, election.nameElection, DateFormat().format(election.dateBeginElection))
    }


    routing {
        // Static plugin. Try to access `/static/index.ftl`
        static("/static") {
            resources("static/img")
        }

        authenticate("auth-session") {
            get("/elections") {
                val userSession = call.principal<UserSession>()
                val elections = dao.allElections()
                val electionsForFreemarker = mutableListOf<ElectionForFremarker>()
                for (election in elections) {
                    electionsForFreemarker += ElectionForFremarker(
                        election.id,
                        election.nameElection,
                        DateFormat().format(election.dateBeginElection)
                    )
                }
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        mapOf(
                            "elections" to electionsForFreemarker,
                            "name" to userSession?.name,
                            "role" to userSession?.role
                        )
                    )
                )
            }

            post("/election") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idElection = formParameters["election"].toString().toInt()
                val election = dao.election(idElection)
                if (election != null) {
                    val events = dao.eventsWithSectionAndLows(election)
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
                    val electionForFreemarker = ElectionForFremarker(
                        election.id,
                        election.nameElection,
                        DateFormat().format(election.dateBeginElection)
                    )
                    call.respond(
                        FreeMarkerContent(
                            "templates/election/election.ftl",
                            mapOf(
                                "events" to eventsForFreeMarker,
                                "election" to electionForFreemarker,
                                "name" to userSession?.name,
                                "role" to userSession?.role
                            )
                        )
                    )
                } else {
                    val elections = dao.allElections()
                    val electionsForFreemarker = mutableListOf<ElectionForFremarker>()
                    for (oneElection in elections) {
                        electionsForFreemarker += ElectionForFremarker(
                            oneElection.id,
                            oneElection.nameElection,
                            DateFormat().format(oneElection.dateBeginElection)
                        )
                    }
                    call.respond(
                        FreeMarkerContent(
                            "templates/elections.ftl",
                            mapOf(
                                "message" to "Данный календарный план не найдеy",
                                "elections" to electionsForFreemarker,
                                "name" to userSession?.name,
                                "role" to userSession?.role
                            )
                        )
                    )
                }
            }

            get("/editElection") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val idElection = call.request.queryParameters["idElection"]?.toIntOrNull()
                    if (idElection != null) {
                        val election = dao.election(idElection)
                        if (election != null) {
                            val sections = dao.sectionsForElection(election)
                            val roles = dao.rolesForElection(election.id)
                            val laws = dao.allLaws()
                            val events = dao.eventsForElection(election.id)
                            call.respond(
                                FreeMarkerContent(
                                    "templates/election/editElection.ftl",
                                    mapOf(
                                        "sections" to sections,
                                        "roles" to roles,
                                        "laws" to laws,
                                        "events" to events,
                                        "election" to election?.let { it1 -> getElectionForTemlates(it1) },
                                        "name" to userSession.name,
                                        "role" to userSession.role
                                    )
                                )
                            )
                        }
                        call.respond(
                            FreeMarkerContent(
                                "templates/election/elections.ftl",
                                mapOf(
                                    "message" to "Календарный план не найден",
                                    "election" to election?.let { it1 -> getElectionForTemlates(it1) },
                                    "name" to userSession.name,
                                    "role" to userSession.role
                                )
                            )
                        )
                    } else {
                        call.respond(
                            FreeMarkerContent(
                                "index.ftl",
                                mapOf(
                                    "message" to "Календарный план не найден",
                                    "name" to userSession.name,
                                    "role" to userSession.role
                                )
                            )
                        )
                    }
                } else {
                    call.respond(
                        FreeMarkerContent(
                            "index.ftl",
                            mapOf("name" to userSession?.name, "role" to userSession?.role)
                        )
                    )
                }
            }

            post("/editElection") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idElection = formParameters.getOrFail("idElection").toInt()
                    val nameElection = formParameters.getOrFail("nameElection")
                    val dateBegin = formParameters.getOrFail("dateBegin").toLocalDateTime()
                    dao.editElection(idElection, nameElection, dateBegin)
                    val election = dao.election(idElection)
                    val dateTime = election?.dateBeginElection
                    val dateTimeString = DateFormat().format(dateTime)
                    call.respond(
                        FreeMarkerContent(
                            "templates/election/editElection.ftl",
                            mapOf(
                                "nameElection" to election?.nameElection,
                                "dateBeginElection" to dateTimeString,
                                "idElection" to election?.id,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "election" to election?.let { it1 -> getElectionForTemlates(it1) }
                            )
                        )
                    )
                } else {
                    call.respondRedirect("/")
                }
            }


            get("/createElection") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/createElection.ftl",
                        mapOf("name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }

            post("/createElection") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val nameElection = formParameters.getOrFail("nameElection")
                    val dateBegin = formParameters.getOrFail("dateBegin").toLocalDateTime()
                    val election = dao.addNewElection(nameElection, dateBegin)
                    val dateTime = election?.dateBeginElection
                    val dateTimeString = DateFormat().format(dateTime)
                    call.respond(
                        FreeMarkerContent(
                            "templates/election/editElection.ftl",
                            mapOf(
                                "nameElection" to election?.nameElection,
                                "dateBeginElection" to dateTimeString,
                                "idElection" to election?.id,
                                "name" to userSession.name,
                                "role" to userSession.role,
                                "election" to election
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


            get("/addEventsForElection") {
                val userSession = call.principal<UserSession>()
                call.respond(
                    FreeMarkerContent(
                        "templates/election/elections.ftl",
                        mapOf("name" to userSession?.name, "role" to userSession?.role)
                    )
                )
            }


            get("/addDocuments") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val idElection = call.request.queryParameters["idElection"]?.toIntOrNull()
                    val roles = idElection?.let { it1 -> dao.rolesForElection(it1) }
                    val election = idElection?.let { it1 -> dao.election(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/role/addDocuments.ftl",
                            mapOf(
                                "name" to userSession?.name,
                                "roles" to roles,
                                "idElection" to idElection,
                                "election" to election,
                                "role" to userSession?.role
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

            post("/addDocuments") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК" || userSession?.role == "ТИК") {
                    val formParameters = call.receiveParameters()
                    val idRole = formParameters["idRole"].toString().toInt()
                    val documents = formParameters["documents"].toString()
                    val idElection = formParameters.getOrFail("idElection").toInt()
                    val election = dao.election(idElection)
                    val role = dao.role(idRole)

                    val message = if (role != null) {
                        if (dao.updateRole(role.id, role.nameRole, documents)) {
                            "Ссылка на документы для ${role.nameRole} добавлена"
                        } else {
                            "Ошибка при добавлении"
                        }
                    } else "Данный исполнител не найден"

                    val roles = idElection.let { it1 -> dao.rolesForElection(it1) }
                    call.respond(
                        FreeMarkerContent(
                            "templates/role/addDocuments.ftl",
                            mapOf(
                                "name" to userSession.name,
                                "roles" to roles,
                                "election" to election,
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

            get("/calendar") {
                val userSession = call.principal<UserSession>()
                val idElection = call.request.queryParameters["idElection"]?.toIntOrNull()
                val roles = idElection?.let { it1 -> dao.rolesForElection(it1) }
                val election = idElection?.let { it1 -> dao.election(it1) }
                val eventsForRole = election?.let { it1 -> dao.eventsWithSectionAndLows(it1) }
                var listAllDay: List<LocalDate> = listOf<LocalDate>()
                if (eventsForRole != null) {
                    for(election in eventsForRole){
                        if (userSession != null) {
                            if(election.roles?.contains(userSession.role) == true){
                                val count = election.duration
                                for(i in 0..count){
                                    listAllDay += election.dateBeginEvent.plus(i, DateTimeUnit.DAY)
                                }
                            }
                        }
                    }
                }

                var actionDays : List<Int> = listOf()
                if (listAllDay != null) {
                    for (dateDay in listAllDay){
                        if (election != null) {
                            if(dateDay.month.name == election.dateBeginElection.month.name){
                                actionDays += dateDay.dayOfMonth
                            }
                        }
                    }
                }
                var newRole = Role(-1, "ЦИК", null)
                if (roles != null) {
                    for(role1 in roles){
                        if(role1.nameRole == userSession?.role)
                            newRole = role1
                    }
                }

                val year = election?.dateBeginElection?.year
                val mouth = election?.dateBeginElection?.month
                val maxDays = mouth?.length(true)
                val week = (maxDays?.rem(7))?.plus(1)

                val countDay = 1 - (LocalDate(year!!, mouth!!, 1)?.dayOfWeek?.value!!)

                if (mouth != null) {
                    call.respond(
                        FreeMarkerContent(
                            "templates/calendar/calendar.ftl",
                            mapOf(
                                "name" to userSession?.name,
                                "roles" to roles,
                                "idElection" to idElection,
                                "election" to election,
                                "role" to userSession?.role,
                                "maxDays" to maxDays,
                                "countDay" to countDay,
                                "year" to year,
                                "mouth" to mouth.name,
                                "weeks" to week,
                                "actionDays" to actionDays,
                                "previewMouth" to (mouth.value-1),
                                "nextMouth" to (mouth.value+1),
                                "newRole" to newRole
                            )
                        )
                    )
                }
            }


            post("/calendar") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idElection = formParameters["idElection"]?.toIntOrNull()
                var newMouth = formParameters["mouth"]?.toIntOrNull()
                var yearS = formParameters["year"]?.toString()
                yearS = yearS?.replace(" ", "")
                var year = yearS?.toIntOrNull()
                if (newMouth != null) {
                    if(newMouth>12){
                        newMouth = newMouth-12
                        if(year!=null)
                            year++
                    }
                }
                val idRole = formParameters["newRole"]?.toIntOrNull()
                var role = idRole?.let { it1 -> dao.role(it1) }
                if(role==null){
                    role = Role(-1, "ЦИК", null)
                }
                val roles = idElection?.let { it1 -> dao.rolesForElection(it1) }
                val election = idElection?.let { it1 -> dao.election(it1) }
                val eventsForRole = election?.let { it1 -> dao.eventsWithSectionAndLows(it1) }

                var listAllDay: List<LocalDate> = listOf<LocalDate>()
                if (eventsForRole != null) {
                    for(election in eventsForRole){
                        if (userSession != null) {
                            if(election.roles?.contains(role.nameRole) == true){
                                val count = election.duration
                                for(i in 0..count){
                                    listAllDay += election.dateBeginEvent.plus(i, DateTimeUnit.DAY)
                                }
                            }
                        }
                    }
                }

                val actionDays : MutableList<Int> = mutableListOf()
                for (dateDay in listAllDay){
                    if (election != null) {
                        if(dateDay.month.name == newMouth?.let { it1 -> Month(it1).name }){
                            actionDays += dateDay.dayOfMonth
                        }
                    }
                }



                val mouth = newMouth?.let { it1 -> Month(it1) }
                val maxDays = mouth?.length(true)
                val week = (maxDays?.rem(7))?.plus(1)


                val countDay = 1 - (LocalDate(year!!, mouth!!, 1).dayOfWeek.value)

                call.respond(
                    FreeMarkerContent(
                        "templates/calendar/calendar.ftl",
                        mapOf(
                            "name" to userSession?.name,
                            "roles" to roles,
                            "idElection" to idElection,
                            "election" to election,
                            "role" to userSession?.role,
                            "maxDays" to maxDays,
                            "countDay" to countDay,
                            "year" to year,
                            "mouth" to mouth,
                            "weeks" to week,
                            "actionDays" to actionDays,
                            "previewMouth" to (mouth.value-1),
                            "nextMouth" to (mouth.value+1),
                            "newRole" to role
                        )
                    )
                )
            }
        }
    }

}


