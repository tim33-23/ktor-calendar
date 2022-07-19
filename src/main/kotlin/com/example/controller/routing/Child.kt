package com.example.plugins

import com.example.controller.routing.Services.ChildrenPage
import com.example.controller.routing.Services.MainPage
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.UserSession
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.sessions.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.toLocalDate

fun Application.configureRoutingChild() {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }


    routing {
        // Static plugin. Try to access `/static/index.ftl`
        static("/static") {
            resources("static/img")
        }

        authenticate("auth-session") {

            get("/addChild") {
                val userSession = call.principal<UserSession>()
                if (userSession != null) {
                    call.respond(FreeMarkerContent("templates/child/createChild.ftl", null))
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/editChild") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idChild = formParameters["idChild"]?.toInt()
                if (userSession != null) {
                    val email = userSession.email
                    val parent = dao.parent(email)
                    val child = idChild?.let { it1 -> dao.child(it1) }
                    if (parent != null && child != null) {
                        call.sessions.set(UserSession(userSession.email, idChild))
                        call.respond(FreeMarkerContent("templates/child/editeChild.ftl", mapOf("child" to child)))
                    } else {
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/confirmCreateChild") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val name = formParameters["name"].toString()
                val gender = formParameters["gender"].toString()
                val dataBirth = formParameters["dateOfBirth"]?.toLocalDate()
                if (userSession != null) {
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if (parent != null && dataBirth != null && userSession.idChild != null) {
                        if (gender == "boy") {
                            val child = dao.updateChild(userSession.idChild, name, dataBirth, true)
                            val model = child?.let { it1 -> MainPage().getModelForMainPage(parent, it1.idChild) }
                            call.sessions.set(UserSession(userSession.email, userSession.idChild))
                            model?.minus("idChild")
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        } else if (gender == "girl") {
                            val child = dao.updateChild(userSession.idChild, name, dataBirth, false)
                            val model = child?.let { it1 -> MainPage().getModelForMainPage(parent, it1.idChild) }
                            call.sessions.set(UserSession(userSession.email, userSession.idChild))
                            model?.minus("idChild")
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        } else {
                            call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                        }
                    } else {
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/createChild") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val name = formParameters["name"].toString()
                val gender = formParameters["gender"].toString()
                val dataBirth = formParameters["dateOfBirth"]?.toLocalDate()
                if (userSession != null) {
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if (parent != null && dataBirth != null) {
                        if (gender == "boy") {
                            val child = dao.addChild(parent.id, name, dataBirth, true)
                            val model = child?.let { it1 -> MainPage().getModelForMainPage(parent, it1.idChild) }
                            call.sessions.set(UserSession(userSession.email, model?.get("idChild") as Int?))
                            model?.minus("idChild")
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        } else if (gender == "girl") {
                            val child = dao.addChild(parent.id, name, dataBirth, false)
                            val model = child?.let { it1 -> MainPage().getModelForMainPage(parent, it1.idChild) }
                            call.sessions.set(UserSession(userSession.email, model?.get("idChild") as Int?))
                            model?.minus("idChild")
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        } else {
                            call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                        }
                    } else {
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/children") {
                val userSession = call.principal<UserSession>()
                if (userSession != null) {
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if (parent != null) {
                        val model = ChildrenPage().getModelForChildrenPage(parent)
                        call.respond(FreeMarkerContent("templates/child/children.ftl", model))
                    } else {
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/deleteChild") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idChild = formParameters["idChild"]?.toInt()
                if (userSession != null) {
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if (parent != null && idChild != null) {
                        dao.deletedChild(idChild)
                        val children = dao.children(parent.id)
                        if (children != null && children.isNotEmpty()) {
                            val model = MainPage().getModelForMainPage(parent)
                            call.sessions.set(UserSession(userSession.email, model?.get("idChild") as Int?))
                            model?.minus("idChild")
                            call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                        } else {
                            call.respond(FreeMarkerContent("templates/child/createFirstChild.ftl", null))
                        }
                    } else {
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }
            }

            post("/child") {
                val userSession = call.principal<UserSession>()
                val formParameters = call.receiveParameters()
                val idChild = formParameters["idChild"]?.toInt()
                if (userSession != null) {
                    val email = userSession.email
                    val parent = dao.parent(email)
                    if (parent != null && idChild != null) {
                        dao.child(idChild)
                        val model = MainPage().getModelForMainPage(parent, idChild)
                        call.sessions.set(UserSession(userSession.email, model?.get("idChild") as Int?))
                        model?.minus("idChild")
                        call.respond(FreeMarkerContent("templates/main/main.ftl", model))
                    } else {
                        call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                    }
                } else {
                    call.respond(FreeMarkerContent("templates/authorization/login.ftl", null))
                }

            }
        }
    }
}
