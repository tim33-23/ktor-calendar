package com.example.controller.routing

import com.example.controller.routing.Services.MainPage
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.ParametersBody
import com.example.dto.UserSession
import com.example.services.SleepPage
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.toLocalDate

fun Application.configureSleep() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    routing {

        authenticate("auth-session") {
            get("/sleep"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        val model = child.let{it1 -> SleepPage().getModelForSleepPage(it1, 0)}
                        call.respond(FreeMarkerContent("templates/sleep/sleeping.ftl", model))
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){
                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }


            post("/sleepPreview"){
                val userSession = call.principal<UserSession>()

                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    val formParameters = call.receiveParameters()
                    var count = formParameters["CountPreview"]?.toInt()
                    if(count==null)
                        count = 0;
                    if(child != null){
                        val model = child.let{it1 -> SleepPage().getModelForSleepPagePreview(it1, count!!)}
                        call.respond(FreeMarkerContent("templates/sleep/sleeping.ftl", model))
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){
                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/sleepNext"){
                val userSession = call.principal<UserSession>()

                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    val formParameters = call.receiveParameters()
                    var count = formParameters["CountNext"]?.toInt()
                    if(count==null)
                        count = 0;
                    if(child != null){
                        val model = child.let{it1 -> SleepPage().getModelForSleepPageNext(it1, count!!)}
                        call.respond(FreeMarkerContent("templates/sleep/sleeping.ftl", model))
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){
                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/sleepOn"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        dao.addBeginSleep(idChild)
                        val model = child.let{it1 -> SleepPage().getModelForSleepPage(it1, 0)}
                        call.respond(FreeMarkerContent("templates/sleep/sleeping.ftl", model))
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){
                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }


            post("/sleepOff"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        val checkUpdate = dao.endSleep(idChild)
                        if(checkUpdate){
                            val model = child.let{it1 -> SleepPage().getModelForSleepPage(it1, 0)}
                            call.respond(FreeMarkerContent("templates/sleep/sleeping.ftl", model))
                        }
                        else {
                            val parent = dao.parent(email)
                            if (parent != null) {
                                val model = idChild.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                                call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                            } else {
                                call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                            }
                        }
                    }
                    else{
                        val parent = dao.parent(email)
                        if(parent!=null){

                            val model = idChild?.let { it1 -> MainPage().getModelForMainPage(parent, it1) }
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        }
                        else{
                            call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                        }
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }


            post("/deleteSleep") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                if(userSession!=null){
                    val idChild = userSession.idChild
                    val weight = formParameters["weight"]?.toFloat()
                    val dateParameters = formParameters["dateParametrs"]?.toLocalDate()
                    var newBody: ParametersBody? = null
                    if(idChild != null && weight != null && dateParameters != null){
                        val body = dao.parametersBody(idChild, dateParameters)
                        if(body==null){
                            newBody = dao.insertParametersBody(idChild, null, weight, dateParameters)
                        }
                        else{
                            if(dao.updateParametersBody(body.idBody, idChild, body.childHeightFact, weight, dateParameters)){
                                newBody = dao.parametersBody(idChild, dateParameters)
                            }
                            else{
                                newBody = null
                            }
                        }
                        call.respond(FreeMarkerContent("templates/parametrs/weight.ftl", null))
                    }
                    else{
                        call.respond(FreeMarkerContent("templates/parametrs/addWeight.ftl", null))
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }
        }
    }
}