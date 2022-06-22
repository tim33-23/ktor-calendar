<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<#assign section=""/>

<main>
    <div class="row">
        <div class="col-12" align="center">
            <h2 align="center" style="max-width: 80%">
                <br>${election.nameElection} от ${election.dateBeginElection}
                <br><b>Редактирование мероприятия:</b>
            </h2>
        </div>
    </div>

    <div class="role">
        <div class="row mb-0" align="center">
            <table class="row mb-0" >
                <tr style="border: 2px solid black; background-color: #f0f0f0">
                    <td class="mycol col-md-6 themed-grid-col" style="border: 2px solid black">
                        <div align="center">
                            <h1 style="font-size: 25px"><b>Содержание мероприятия</b></h1>
                        </div>
                    </td>
                    <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                        <div align="center">
                            <h1 style="font-size: 25px"><b>Срок исполнения</b></h1>
                        </div>
                    </td>
                    <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                        <div align="center">
                            <h1 style="font-size: 25px"><b>Исполнители</b></h1>
                        </div>
                    </td>
                </tr>
                <#if event?has_content>
                            <tr style="background-color: #f0f0f0">
                                <td class="col-md-6 themed-grid-col" style="border: 2px solid black">
                                    <div>
                                        <div>
                                            <p style="font-size: 18px">${event.description}</p>
                                        </div>
                                        <div>
                                            <#list lawsForEvent as law>
                                                <p>
                                                    <#if law.article?has_content>ст ${law.article}</#if>
                                                    <#if law.paragraph?has_content> п ${law.paragraph}</#if>
                                                    <#if law.part?has_content> ч ${law.part}</#if>
                                                    ${law.scopeLegislation}
                                                </p>
                                            </#list>
                                        </div>
                                    </div>
                                </td>
                                <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                                    <div>
                                        <p style="font-size: 18px">c ${event.dateBeginEvent} длительность ${event.duration}</p>
                                    </div>
                                </td>
                                <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                                    <div align="center">
                                        <#if rolesForEvent?has_content>
                                            <p style="font-size: 18px">
                                                <#list rolesForEvent as role>
                                                    ${role.nameRole}<br>
                                                </#list>
                                            </p>
                                        </#if>
                                    </div>
                                </td>
                            </tr>
                        </#if>
            </table>
        </div>
    </div>


    <div class="row mt-5">
        <div class="col-2" style="margin-left: 20px">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#addRole" role="tab" aria-controls="list-home">Добавить исполнителя</a>
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
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
                            </div>
                            <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Добавить</button>
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
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
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
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
                            </div>
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
                                                        <#if law.article?has_content>ст ${law.article}</#if>
                                                        <#if law.paragraph?has_content> п ${law.paragraph}</#if>
                                                        <#if law.part?has_content> ч ${law.part}</#if>
                                                        <#if law.scopeLegislation?has_content> ${law.scopeLegislation}</#if>
                                                    </option>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
                                    </div>
                                    <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Удалить</button>
                                </#if>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="editSection" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="form-signin w-500" id="deletedLaw" align="center">
                        <form style="margin-bottom: 150px;" action="/editSection" method="Post">
                            <div class="form-floating row">
                                <h1 class="h3 mb-3 fw-normal">Создать новый раздел</h1>
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="nameSection" name="nameSection" placeholder="">
                                    <label for="nameSection">Имя раздела</label>
                                </div>
                                <div class="row container mt-2" align="center">
                                    <div class="col-10 container">
                                        <label class="form-check">
                                            <b>Выбрать существующий раздел</b>
                                            <input style="max-width: 100%" class="form-check" type="checkbox" id="checkOnSelectionSection" name="checkOnSelectionSection">
                                        </label>
                                    </div>
                                    <div class="col-2"></div>
                                </div>
                                <div class="themed-grid-col">
                                    <label>Выберите раздел: </label>
                                    <select id="idSection" name="idSection" style="max-width: 100%">
                                        <#assign newSection = "" />
                                        <#list sections as section>
                                            <#if newSection!=section>
                                                <#assign newSection = section.nameSection/>
                                                <option value="${section.id}">${section.nameSection}</option>
                                            </#if>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
                            </div>
                            <button class="w-100 btn btn-lg btn-primary" type="submit">Изменить</button>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="editDescriptionEvent" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="form-signin w-500" id="editDescriptionEvent" align="center">
                        <form style="margin-bottom: 150px;" action="/editDescriptionEvent" method="Post">
                            <div class="form-floating row">
                                <div class="mt-5" >
                                    <textarea type="text" class="form-control" id="descriptionEvent" name="descriptionEvent" placeholder="Описание мероприятия" rows="4">${event.description}</textarea>
                                </div>
                            </div>
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
                            </div>
                            <button class="w-100 btn btn-lg btn-primary" type="submit">Изменить</button>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="editDateEvent" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="form-signin w-500" id="editDateEvent" align="center">
                        <form style="margin-bottom: 150px;" action="/editDateEvent" method="Post">
                            <div class="form-floating row">
                                <div class="form-floating">
                                    <input type="date" class="form-control" id="dateBeginEvent" name="dateBeginEvent" placeholder="Дата начала мероприятия">
                                    <label for="dateBeginEvent">Дата начала мироприятия</label>
                                </div>
                                <div class="form-floating">
                                    <input required type="number" class="form-control" id="durationEvent" name="durationEvent" placeholder="Длительность в днях">
                                    <label for="durationEvent">Длительность в днях</label>
                                </div>
                                <div  class="form-floating" style="display: none">
                                    <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.id}">
                                </div>
                                <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Изменить</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-2 align-content-center">
            <div class="mt-5" align="center">
                <#if message?has_content>
                    <p class="mt-3 text-danger" style="font-size: 25px">${message}</p>
                </#if>
            </div>
        </div>
    </div>

</main>
</body>
</html>