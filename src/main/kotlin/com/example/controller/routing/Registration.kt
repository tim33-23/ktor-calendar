package com.example.plugins.routing

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
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

        post("/registration"){
            val formParameters = call.receiveParameters()
            val email = formParameters["email"].toString()
            val password = formParameters["password"].toString()
            val confirm = formParameters["confirmationPassword"]
            if(email!="" && password!="" && confirm!=""){
                if(password == confirm){
                    val parent =
                        try {
                            dao.addParent(email, password)
                        }
                        catch (ex: Exception){
                            null
                        }
                    if(parent!=null){
                        call.sessions.set(UserSession(email = email, null))
                        val children = dao.children(idParent = parent.id)
                        if((children != null) && children.isNotEmpty())
                            call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to children.first())))
                        else
                            call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                    }
                    else{
                        call.respond(FreeMarkerContent("templates/registration/registration.ftl", mapOf("message" to "Такой пользователь уже зарегистрирован!")))
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/registration/registration.ftl", mapOf("message" to "Пароли не совпадают!")))
                }
            }
            else{
                call.respond(FreeMarkerContent("templates/registration/registration.ftl", mapOf("message" to "Все поля должны быть заполнены!")))
            }
        }

        get("/registration"){
            call.respond(FreeMarkerContent("templates/registration/registration.ftl", null))
        }

        authenticate("auth-session") {
            get("/registration"){
                val userSession = call.principal<UserSession>()
                if(userSession!=null){
                    val email = userSession.email
                    val parent = dao.parent(email)
                    val children = parent?.let { it1 -> dao.children(it1.id) }
                    val child = children?.firstOrNull()
                    if(child == null){
                        call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                    }
                    else{
                        call.respond(FreeMarkerContent("templates/main/main.ftl", mapOf("child" to child)))
                    }
                }
                else{
                    call.respond(FreeMarkerContent("templates/index.ftl", null))
                }

            }
        }
    }
}