<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
    <style>
        .mycol{
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
<#include "../header.ftl"/>
<#assign section=""/>
<main>
    <div class="container" style="margin-top: 10px;">
        <#if role?has_content && role=="ЦИК">
            <div class="row">
                <div class="col-4">
                    <form action="/editElection" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Редактировать календарь</button>
                    </form>
                </div>
                <div class="col-4">
                    <form action="/addEvent" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить мероприятие</button>
                    </form>
                </div>
                <div class="col-4">
                    <form action="/selectEventForEdit" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Редактировать мероприятия</button>
                    </form>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-4">
                    <form action="/addDocuments" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить документы исполнителю</button>
                    </form>
                </div>
                <div class="col-4">
                    <form action="/editSection" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Редактировать раздел</button>
                    </form>
                </div>
                <div class="col-4">
                    <form action="/calendar" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Посмотреть календарь мероприятий</button>
                    </form>
                </div>
            </div>
            <#elseif role?has_content>
                <div class="col4"></div>
                <div class="col-4">
                    <form action="/calendar" method="get">
                        <div  class="form-floating" style="display: none">
                            <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Посмотреть календарь мероприятий</button>
                    </form>
                </div>
                <div class="col4"></div>
        </#if>
        <div class="container">
            <h2 align="center">
                <br>${election.nameElection}
                <#if dataBeginElection?has_content>
                    <br>${election.dataBeginElection}
                </#if>
            </h2>
        </div>
        <div class="row mb-0">
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

                <#if events?has_content>
                    <#list events as event>
                        <#if section!=event.nameSection>
                            <#assign section=event.nameSection/>
                            <tr style="background-color: #f0f0f0">
                                <td colspan="3" style="border: 2px solid black">
                                    <div class="row mb-0" align="center" style="margin-bottom: 0;">
                                        <h4><b>${section}</b></h4>
                                    </div>
                                </td>
                            </tr>
                        </#if>
                        <tr style="background-color: #f0f0f0">
                            <td class="col-md-6 themed-grid-col" style="border: 2px solid black">
                                <div>
                                    <div>
                                        <p style="font-size: 18px">${event.description}</p>
                                    </div>
                                        <div>
                                            <#list event.laws as law>
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
                                    <p style="font-size: 18px">c ${event.dateBeginEvent} по ${event.duration}</p>
                                </div>
                            </td>
                            <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                                <div align="center">
                                    <#if event.roles?has_content>
                                        <p style="font-size: 18px">
                                            <#list event.roles as role>
                                                ${role}<br>
                                            </#list>
                                        </p>
                                    </#if>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#if>

            </table>
        </div>
    </div>
</main>
</body>
</html>