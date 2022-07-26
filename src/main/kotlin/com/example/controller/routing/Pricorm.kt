package com.example.controller.routing

import com.example.controller.routing.Services.MainPage
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.ParametersBody
import com.example.dto.UserSession
import io.ktor.http.cio.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.toLocalDate

fun Application.configurePricorm() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    routing {

        static("/static") {
            resources("static/img")
        }

        authenticate("auth-session") {
            get("/recipe"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        call.respond(FreeMarkerContent("templates/prikorm/recipes.ftl", null))
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

            get("/food"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val idChild = userSession.idChild
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if(child != null){
                        call.respond(FreeMarkerContent("templates/prikorm/food.ftl", null))
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

            post("/aj") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                if(userSession!=null){
                    val idChild = userSession.idChild
                    val height = formParameters["growth"]?.toFloat()
                    val dateParameters = formParameters["dateParametrs"]?.toLocalDate()
                    var newBody: ParametersBody? = null
                    if(idChild != null && height != null && dateParameters != null){
                        val body = dao.parametersBody(idChild, dateParameters)
                        if(body==null){
                            newBody = dao.insertParametersBody(idChild, height, null, dateParameters)
                        }
                        else{
                            if(dao.updateParametersBody(body.idBody, idChild, height, body.childWeightFact, dateParameters)){
                                newBody = dao.parametersBody(idChild, dateParameters)
                            }
                            else{
                                newBody = null
                            }
                        }
                        call.respond(FreeMarkerContent("templates/parametrs/height.ftl", null))
                    }
                    else{
                        call.respond(FreeMarkerContent("templates/parametrs/addHeight.ftl", null))
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }
        }
    }
}