<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<#assign section=""/>

<main>
    <div class="container row">
        <div class="col-12" align="center">
            <h2>
                <br>${election.nameElection}: ${election.dateBeginElection}
            </h2>
        </div>
    </div>

    <div class="row mt-5">
        <div class="col-2" style="margin-left: 20px">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#addRole" role="tab" aria-controls="list-home">Добавить исполнителя</a>
                <a class="list-group-item list-group-item-action" id="list-profile-list" data-bs-toggle="list" href="#editRole" role="tab" aria-controls="list-profile">Изменить исполнителя</a>
                <a class="list-group-item list-group-item-action" id="list-messages-list" data-bs-toggle="list" href="#deletedRole" role="tab" aria-controls="list-messages">Удалить исполнителя</a>

                <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" href="#addLaw" role="tab" aria-controls="list-settings">Добавить регламентирующий закон</a>
                <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" href="#deletedLaw" role="tab" aria-controls="list-settings">Удалить закон</a>

                <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" href="#editSection" role="tab" aria-controls="list-settings">Изменить раздел</a>
                <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" href="#editDescriptionEvent" role="tab" aria-controls="list-settings">Изменить описание</a>
                <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" href="#editDateEvent" role="tab" aria-controls="list-settings">Изменить сроки</a>

            </div>
        </div>
        <div class="col-1"></div>

        <div class="col-5">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="addRole" role="tabpanel" aria-labelledby="list-home-list" align="center">
                    <div class="form-signin w-500" id="addRole" align="center">
                        <form style="" action="/addRole" method="Post">
                            <h1>Добавить нового исполнителя</h1>
                            <#include "../role/createRole.ftl"/>
                            <#include "../role/selectRole.ftl"/>
                            <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Добавить</button>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="editRole" role="tabpanel" aria-labelledby="list-profile-list" align="center">
                    <div class="form-signin w-500" id="editRole" align="center">
                        <form style="" action="/editRole" method="Post">
                            <h1>Изменить исполнителя</h1>
                            <p>Новый исполнтиель</p>
                            <#include "../role/createRole.ftl"/>
                            <div class="col-6 container">
                                <div class="form-floating">
                                    <div class="themed-grid-col">
                                        <select id="idRole" name="idRole" style="max-width: 100%; min-width: 150px">
                                            <#list rolesForEvent as role>
                                                <option value="${role.id}">${role.nameRole}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Изменить</button>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="deletedRole" role="tabpanel" aria-labelledby="list-messages-list">
                    <div class="form-signin w-500" id="deletedRole" align="center">
                        <form style="" action="/deletedRole" method="Post">
                            <h1>Удалить исполнителя</h1>
                            <div class="col-6 container">
                                <div class="form-floating">
                                    <div class="themed-grid-col">
                                        <select id="idRole" name="idRole" style="max-width: 100%; min-width: 200px">
                                            <#list rolesForEvent as role>
                                                <option value="${role.id}">${role.nameRole}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Удалить</button>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="addLaw" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="form-signin w-500" id="addLaw" align="center">
                        <form style="margin-bottom: 150px;" action="/addLaw" method="Post">
                            <h1>Добавить регламентирующий закон</h1>
                            <#include "../law/createLaw.ftl"/>
                            <#include "../law/selectLaw.ftl"/>
                            <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить</button>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="deletedLaw" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="form-signin w-500" id="deletedLaw" align="center">
                        <form style="margin-bottom: 150px;" action="/deletedLaw" method="Post">
                            <h1>Удалить регламентирующий закон</h1>
                                <#if lawsForEvent?has_content>
                                    <div class="col-5">
                                        <div class="themed-grid-col">
                                            <select id="idLaw" name="idLaw" style="max-width: 100%">
                                                <#list lawsForEvent as law>
                                                    <option value="${law.id}">
                                                        ст ${law.article}
                                                        <#if law.paragraph?has_content> п ${law.paragraph}</#if>
                                                        <#if law.part?has_content> ч ${law.part}</#if>
                                                        <#if law.scopeLegislation?has_content> ${law.scopeLegislation}</#if>
                                                    </option>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>
                                </#if>
                            <button class="w-100 btn btn-lg btn-primary" type="submit">Удалить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-4">

        </div>
    </div>

</main>
</body>
</html>