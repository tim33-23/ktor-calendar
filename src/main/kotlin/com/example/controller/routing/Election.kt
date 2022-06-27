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
import java.awt.Color
import java.util.*
import kotlin.collections.HashMap

fun Application.configureElection() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }


    fun getElectionForTemlates(election: Election): ElectionForFremarker {
        return ElectionForFremarker(election.id, election.nameElection, DateFormat().format(election.dateBeginElection))
    }

    data class  CrossingColor(var title: String, var color: Color)


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

            get("/myElections") {
                val userSession = call.principal<UserSession>()
                val elections = dao.allElections()
                var myElection: Election = Election(0,"", LocalDateTime(1,1,1,0,0,0))
                val myRole = userSession?.email?.let { it1 -> dao.participantRole(it1) }?.idRole
                for(election in elections){
                    val roles = dao.rolesForElection(election.id)
                    for(role in roles){
                        if (myRole != null) {
                            if(role.id == myRole){
                                myElection = election
                            }
                        }
                    }
                }

                val electionsForFreemarker = listOf<ElectionForFremarker>(ElectionForFremarker(
                    myElection.id,
                    myElection.nameElection,
                    DateFormat().format(myElection.dateBeginElection)
                ))
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

            post("/electionForRole") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idElection = formParameters["idElection"].toString().toInt()
                val idRole = formParameters["newRole"]?.toIntOrNull()
                var nameRole : String? = null
                if(idRole==null){
                    if (userSession != null) {
                        val roles = dao.rolesForElection(idElection)
                        for(role in roles){
                            if(userSession.role == role.nameRole){
                                nameRole = role.nameRole
                            }
                        }
                    }
                }
                else{
                    nameRole = dao.role(idRole)?.nameRole
                }
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
                            if(eventForFreeMarker.roles?.contains(nameRole) == true){
                                eventsForFreeMarker += eventForFreeMarker
                            }
                        }
                    }
                    var roles: List<Role>? = null
                    if (userSession != null) {
                        if (userSession.role == "ЦИК") {
                            roles = dao.rolesForElection(idElection)
                        }
                        else{
                            val newRole = idRole?.let { it1 -> dao.role(it1) }
                            if(idRole!=null && newRole!=null){
                                roles = listOf(newRole)
                            }

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
                                "role" to userSession?.role,
                                "roles" to roles
                            )
                        )
                    )
                }
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
                    var roles: List<Role>? = null
                    if (userSession != null) {
                        if(userSession.role =="ЦИК"){
                            roles = dao.rolesForElection(election.id)
                        }
                        else{
                            val allroles = dao.rolesForElection(election.id)
                            for(role in allroles!!){
                                if(role.nameRole==userSession.role){
                                    roles = listOf(role)
                                    break
                                }
                            }
                        }
                    }
                    call.respond(
                        FreeMarkerContent(
                            "templates/election/election.ftl",
                            mapOf(
                                "events" to eventsForFreeMarker,
                                "election" to electionForFreemarker,
                                "name" to userSession?.name,
                                "role" to userSession?.role,
                                "roles" to roles
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
                if (userSession?.role == "ЦИК") {
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
                if (userSession?.role == "ЦИК") {
                    call.respond(
                        FreeMarkerContent(
                            "templates/election/createElection.ftl",
                            mapOf(
                                "name" to userSession?.name,
                                "role" to userSession?.role,
                                "elections" to dao.allElections()
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

            post("/createElection") {
                val userSession = call.principal<UserSession>()
                if (userSession?.role == "ЦИК") {
                    val formParameters = call.receiveParameters()
                    val nameElection = formParameters.getOrFail("nameElection")
                    val dateBegin = formParameters.getOrFail("dateBegin").toLocalDateTime()

                    val checkOnSelectionElection = formParameters["checkOnSelectionElection"]
                    if(checkOnSelectionElection!=null && checkOnSelectionElection!=""){
                        val idElection = formParameters.getOrFail("idElection").toIntOrNull()
                        if (idElection != null) {
                            val election = dao.addNewElectionWithTemplate(nameElection, dateBegin, idElection)
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
                        }
                    }
                    else {
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
                val mapTilte = HashMap<String, CrossingColor>()
                var actionDays : List<Int> = listOf()
                var listAllDay: List<LocalDate> = listOf<LocalDate>()
                if (eventsForRole != null) {
                    for(event in eventsForRole){
                        if (userSession != null) {
                            if (event.roles?.contains(userSession.role) == true) {
                                val count = event.duration
                                for (i in 0..count) {
                                    val dateDay = event.dateBeginEvent.plus(i, DateTimeUnit.DAY)
                                    if (dateDay.month.name == election.dateBeginElection.month.name) {
                                        actionDays += dateDay.dayOfMonth
                                        if (mapTilte.containsKey(dateDay.dayOfMonth.toString())) {
                                            val newCrossingColor = mapTilte[dateDay.dayOfMonth.toString()]
                                            val del = 40
                                            if (newCrossingColor != null) {
                                                val color = newCrossingColor.color
                                                if(newCrossingColor.color.red.minus(del)<0 ||  newCrossingColor.color.green.minus(del)<0 || newCrossingColor.color.blue.minus(del)<0){
                                                    newCrossingColor.color = Color(119, 136, 153)
                                                }
                                                newCrossingColor.color = Color(newCrossingColor.color.red.minus(del), newCrossingColor.color.green.minus(del), newCrossingColor.color.blue.minus(del))
                                                newCrossingColor.title += event.description
                                                mapTilte[dateDay.dayOfMonth.toString()] = newCrossingColor
                                            }
                                        } else {
                                            mapTilte[dateDay.dayOfMonth.toString()] = CrossingColor(event.description ,Color(220,220,220))
                                        }
                                    }
                                }
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

                    var eventsForRoleInItMouth : List<EventsWithSectionsAndLaw> = listOf()
                    if (eventsForRole != null) {
                        for(event in eventsForRole){
                            if(event.dateBeginEvent.month<=mouth && mouth<=event.dateBeginEvent.plus(event.duration, DateTimeUnit.DAY).month){
                                eventsForRoleInItMouth += event
                            }
                        }
                    }
                    var eventsForFreeMarker: List<EventsWithSectionsAndLawAndDateInString> = mutableListOf()
                    if (eventsForRoleInItMouth != null) {
                        for (event in eventsForRoleInItMouth) {
                            val eventForFreeMarker = EventsWithSectionsAndLawAndDateInString(
                                event.idEvent,
                                event.nameSection,
                                event.description,
                                DateFormat().format(event.dateBeginEvent),
                                DateFormat().format(event.dateBeginEvent, event.duration),
                                event.laws,
                                event.roles,

                            )
                            if(event.roles?.contains(newRole.nameRole) == true)
                                eventsForFreeMarker += eventForFreeMarker
                        }
                    }

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
                                "newRole" to newRole,
                                "events" to eventsForFreeMarker,
                                "titles" to mapTilte
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
                val mapTilte = HashMap<String, CrossingColor>()
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
                val actionDays : MutableList<Int> = mutableListOf()
                var listAllDay: List<LocalDate> = listOf<LocalDate>()
                if (eventsForRole != null) {
                    for(event in eventsForRole){
                        if (userSession != null) {
                            if(event.roles?.contains(role.nameRole) == true){
                                val count = event.duration
                                for (i in 0..count) {
                                val dateDay = event.dateBeginEvent.plus(i, DateTimeUnit.DAY)
                                if (dateDay.month.name == newMouth?.let { it1 -> Month(it1).name }) {
                                    actionDays += dateDay.dayOfMonth
                                    if (mapTilte.containsKey(dateDay.dayOfMonth.toString())) {
                                        val newCrossingColor = mapTilte[dateDay.dayOfMonth.toString()]
                                        val del = 40
                                        if (newCrossingColor != null) {
                                            val color = newCrossingColor.color
                                            if(newCrossingColor.color.red.minus(del)<0 ||  newCrossingColor.color.green.minus(del)<0 || newCrossingColor.color.blue.minus(del)<0){
                                                newCrossingColor.color = Color(119, 136, 153)
                                            }
                                            newCrossingColor.color = Color(newCrossingColor.color.red.minus(del), newCrossingColor.color.green.minus(del), newCrossingColor.color.blue.minus(del))
                                            newCrossingColor.title += event.description
                                            mapTilte[dateDay.dayOfMonth.toString()] = newCrossingColor
                                        }
                                    } else {
                                        mapTilte[dateDay.dayOfMonth.toString()] = CrossingColor(event.description ,Color(220,220,220))
                                    }
                                }
                            }
                        }
                    }
                }


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

                var eventsForRoleInItMouth : List<EventsWithSectionsAndLaw> = listOf()
                if (eventsForRole != null) {
                    for(event in eventsForRole){
                        if(event.dateBeginEvent.month.value<=mouth.value && mouth.value<=event.dateBeginEvent.plus(event.duration, DateTimeUnit.DAY).month.value){
                            eventsForRoleInItMouth += event
                        }
                    }
                }
                var eventsForFreeMarker: List<EventsWithSectionsAndLawAndDateInString> = mutableListOf()
                if (eventsForRoleInItMouth != null) {
                    for (event in eventsForRoleInItMouth) {
                        val eventForFreeMarker = EventsWithSectionsAndLawAndDateInString(
                            event.idEvent,
                            event.nameSection,
                            event.description,
                            DateFormat().format(event.dateBeginEvent),
                            DateFormat().format(event.dateBeginEvent, event.duration),
                            event.laws,
                            event.roles
                        )
                        if(event.roles?.contains(role.nameRole) == true)
                            eventsForFreeMarker += eventForFreeMarker
                    }
                }

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
                            "newRole" to role,
                            "events" to eventsForFreeMarker,
                            "titles" to mapTilte
                        )
                    )
                )
            }
        }

    }

    }
}