<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>

</head>


<body>
<#assign section=""/>
<#include "../header.ftl"/>
<main class="mt-5">
    <#if role?has_content && role=="ЦИК">
    <#elseif role?has_content && role!="ЦИК">

    <#else>
    </#if>
    <div class="container">
        <div class="container">
            <table width="100%" style="cursor: default">
                <tr style="width: 100%">
                    <td align="center">
                        <div class="row">
                            <div class="col-1">

                            </div>
                            <div class="col-2">
                                <form action="/calendar" method="Post">
                                    <div  class="form-floating" style="display: none">
                                        <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="number" class="form-control" id="mouth" name="mouth" placeholder="" value="${previewMouth}">
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="text" class="form-control" id="year" name="year" placeholder="" value="${year?int}">
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="text" class="form-control" id="newRole" name="newRole" placeholder="" value="${newRole.id}">
                                    </div>
                                    <button class="w-100 btn btn-lg btn-primary" type="submit">Предыдущий</button>
                                </form>
                            </div>
                            <div class="col-6">
                                <h1 align="center">${mouth} ${year}</h1>
                            </div>
                            <div class="col-2">
                                <form action="/calendar" method="Post">
                                    <div  class="form-floating" style="display: none">
                                        <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="number" class="form-control" id="mouth" name="mouth" placeholder="" value="${nextMouth}">
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="text" class="form-control" id="year" name="year" placeholder="" value="${year}">
                                    </div>
                                    <div  class="form-floating" style="display: none">
                                        <input type="text" class="form-control" id="newRole" name="newRole" placeholder="" value="${newRole.id}">
                                    </div>
                                    <button class="w-100 btn btn-lg btn-primary" type="submit">Слeдующий</button>
                                </form>
                            </div>
                            <div class="col-1">

                            </div>
                        </div>
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td align="center">
                        <div class="row" style="font-size: 25px">
                            <div class="col">
                                <b>Понeдельник</b>
                            </div>
                            <div class="col">
                                <b>Вторник</b>
                            </div>
                            <div class="col">
                                <b>Среда</b>
                            </div>
                            <div class="col">
                                <b>Четверг</b>
                            </div>
                            <div class="col">
                                <b>Пятница</b>
                            </div>
                            <div class="col">
                                <b>Суббота</b>
                            </div>
                            <div class="col">
                                <b>Воскресенье</b>
                            </div>
                        </div>
                    </td>
                </tr>
                <#list 1..6 as k>
                    <tr style="width: 100%">
                        <td style="width: 100%">
                            <div class="row">
                                <#list 1..7 as i>
                                    <#assign countDay = countDay+1/>
                                    <div class="col" align="center"
                                            <#if actionDays?has_content && actionDays?seq_contains(countDay)>
                                                style="background-color: rgba(${titles[countDay?string].color.red},
                                                ${titles[countDay?string].color.green},
                                                ${titles[countDay?string].color.blue});
                                                        --bs-modal-title-line-height: 22px"

                                                title="${titles[countDay?string].title}"
                                            </#if>
                                    >
                                        <#if (countDay gt 0) && (countDay lte maxDays)>
                                            <h1 >
                                                <b>${countDay}</b>
                                            </h1>
                                        <#else>
                                        </#if>
                                    </div>
                                </#list>
                            </div>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>


    <div class="row mt-3">
        <div class="col-4"></div>
        <div class="col-4">
            <h1 align="center">Исполнитель:<br>${newRole.nameRole}</h1>
        </div>
        <div class="col-4"></div>
    </div>

    <#if role?has_content && role=="ЦИК">
        <div class="row mt-3" align="center">
            <div class="col-4"><h1></h1></div>
            <div class="col-4">
                <form action="/calendar" method="Post" >
                    <div>
                        <div class="form-floating">
                            <div class="themed-grid-col">
                                <select id="newRole" name="newRole"  style="max-width: 100%">
                                    <#list roles as role>
                                        <option   value="${role.id}">${role.nameRole}</option>
                                    </#list>
                                </select>
                            </div>
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                            </div>
                            <div  class="form-floating" style="display: none">
                                <input type="number" class="form-control" id="mouth" name="mouth" placeholder="" value="${previewMouth+1}">
                            </div>
                            <div  class="form-floating" style="display: none">
                                <input type="text" class="form-control" id="year" name="year" placeholder="" value="${year?int}">
                            </div>
                            <button class="w-100 btn btn-lg btn-primary" type="submit">Поменять исполнителя</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-4"><h1></h1></div>
        </div>
    </#if>

    <#if events?has_content>
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
                    <td class="mycol col-md-1" style="border: 2px solid black">
                        <div align="center"><b>№</b></div>
                    </td>
                    <td class="mycol col-md-6 themed-grid-col" style="border: 2px solid black">
                        <div align="center">
                            <h1 style="font-size: 25px"><b>Содержание мероприятия</b></h1>
                        </div>
                    </td>
                    <td class="col-md-2 themed-grid-col" style="border: 2px solid black">
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
                    <#assign countEvent = 1>
                    <#list events as event>
                        <#if section!=event.nameSection>
                            <#assign section=event.nameSection/>
                            <tr style="background-color: #f0f0f0">
                                <td colspan="4" style="border: 2px solid black" >
                                    <div class="row mb-0" align="center" style="margin-bottom: 0;">
                                        <h4><b>${section}</b></h4>
                                    </div>
                                </td>
                            </tr>
                        </#if>
                        <tr style="background-color: #f0f0f0">
                            <td class="col-md-1" style="border: 2px solid black">
                                <div align="center">
                                    <b>${countEvent}</b>
                                    <#assign countEvent = countEvent + 1/>
                                </div>
                            </td>
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
                            <td class="col-md-2 themed-grid-col" style="border: 2px solid black">
                                <div>
                                    <p style="font-size: 18px">
                                        <#if event.dateBeginEvent==event.duration>
                                            Не позднее ${event.dateBeginEvent}
                                        <#else>
                                            С ${event.dateBeginEvent}<br>по ${event.duration}
                                        </#if>
                                    </p>
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
    </#if>
</main>
</body>
</html>