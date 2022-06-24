package com.example.plugins.routing

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Participant
import com.example.dto.UserSession
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.coroutines.runBlocking


fun Application.configureRegistration() {


    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    routing {

        get("/electionsForRegistration"){
            val elections = dao.allElections()
            call.respond(
                FreeMarkerContent(
                "templates/registration/electionsForRegistration.ftl",
                    mapOf("elections" to elections)
                )
            )
        }

        post("/registrationWithElection"){
            val formParameters = call.receiveParameters()
            val idElection = formParameters["election"].toString().toInt()
            val election = dao.election(idElection)
            val roles = election?.let { it1 -> dao.rolesForElection(it1.id) }
            call.respond(
                FreeMarkerContent("templates/registration/registrationWithElection.ftl",
                    mapOf("roles" to roles,
                        "election" to election)
                )
            )
        }

        post("/registration"){
            val formParameters = call.receiveParameters()
            val surname = formParameters["surname"].toString()
            val middleName = formParameters["middleName"].toString()
            val name = formParameters["name"].toString()
            val email = formParameters["email"].toString()
            var tel = formParameters["tel"].toString()
            var idRole = formParameters["role"]?.toIntOrNull()
            var password = formParameters["password"].toString()
            var conf = formParameters["passwordConf"].toString()

            var par :Participant? = null
            if (idRole != null) {
                par = dao.addNewParticipant(surname, name, middleName, email, tel, password, idRole)
            }

            val role = par?.idRole?.let { it1 -> dao.role(it1) }


            if(role!=null && par!=null){
                call.sessions.set(UserSession(email = email, role = role.nameRole, name = role.nameRole))
                call.respond(
                    FreeMarkerContent(
                        "index.ftl",
                        mapOf("roles" to null,
                            "election" to null)
                    )
                )
            }
            else{
                val idElection = formParameters["election"].toString().toInt()
                val election = dao.election(idElection)
                val roles = election?.let { it1 -> dao.rolesForElection(it1.id) }
                call.respond(
                    FreeMarkerContent("templates/registration/registrationWithElection.ftl",
                        mapOf("roles" to roles,
                            "election" to election)
                    )
                )
            }
        }
    }
}