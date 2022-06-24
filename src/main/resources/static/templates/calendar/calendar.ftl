<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>

</head>


<body>


<#include "../header.ftl"/>
<main class="mt-5">
    <#if role?has_content && role=="ЦИК">
    <#elseif role?has_content && role!="ЦИК">

    <#else>
    </#if>
    <div class="container">
        <div class="container">
            <table width="100%">
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
                                                style="background-color: #adb5bd"
                                            </#if>
                                    >
                                        <#if (countDay gt 0) && (countDay lte maxDays)>
                                            <h1><b>${countDay}</b></h1>
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

</main>
</body>
</html>