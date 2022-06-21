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
                            <h1 style="font-size: 25px"><b>Редактировать</b></h1>
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
                                                ст ${law.article}
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
                                <form action="/editEvent" method="post">
                                        <div  class="form-floating" style="display: none">
                                            <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.idEvent}">
                                        </div>
                                    <button class="w-100 btn btn-lg btn-primary" type="submit">Изменить</button>
                                </form>
                                <form class="mt-2" action="/deletedEvent" method="post">
                                    <div  class="form-floating" style="display: none">
                                        <input type="number" class="form-control" id="idEvent" name="idEvent" placeholder="" value="${event.idEvent}">
                                    </div>
                                    <button class="w-100 btn btn-lg btn-primary" type="submit">Удалить</button>
                                </form>
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