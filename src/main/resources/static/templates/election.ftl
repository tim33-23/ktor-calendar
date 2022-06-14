<!doctype html>
<html lang="en">
<head>
    <#include "../templates/head-config.ftl"/>
    <style>
        .brd {
            border: 2px solid black; /* Параметры границы */
            background: #f0f0f0; /* Цвет фона */
            padding: 0px; /* Поля вокруг текста */
        }
        .brdr {
            border-top: 2px solid black;
            border-bottom: 2px solid black;
            border-left: 2px solid black;
            border-right: 1px solid black; /* Параметры границы */
            background: #f0f0f0; /* Цвет фона */
            padding-top: 10px; /* Поля вокруг текста */
        }
        .brdl {
            border-top: 2px solid black;
            border-bottom: 2px solid black;
            border-left: 1px solid black;
            border-right: 2px solid black; /* Параметры границы */
            background: #f0f0f0; /* Цвет фона */
            padding: 0px; /* Поля вокруг текста */
        }
        .brdrl {
            border-top: 2px solid black;
            border-bottom: 2px solid black;
            border-left: 1px solid black;
            border-right: 1px solid black; /* Параметры границы */
            background: #f0f0f0; /* Цвет фона */
            padding: 0px; /* Поля вокруг текста */
        }
        .mycol{
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
<#include "../templates/header.ftl"/>
<#assign section=""/>
<main>
    <div class="container" style="margin-top: 10px;">
        <div class="container">
            <h2 align="center">
                <br>${election}
            </h2>
        </div>
        <div class="row mb-0">
            <table class="row mb-0">
                <tr>
                    <td>
                        <div class="mycol col-md-6 themed-grid-col brdr">
                            <h1>Содержание мероприятия</h1>
                        </div>
                    </td>
                    <td>
                        <div class="mycol col-md-3 themed-grid-col brdrl">
                            <h1>Срок исполнения</h1>
                        </div>
                    </td>
                    <td>
                        <div class="mycol col-md-3 themed-grid-col brdl">
                            <h1>Исполнители</h1>
                        </div>
                    </td>
                </tr>
                <tr>

                </tr>
            </table>
        </div>
        <#list events as event>
            <#if section!=event.section>
                <#assign section=event.section/>
                <div class="row mb-0 brd" align="center" style="margin-bottom: 0;">
                    <h4><b>${section}</b></h4>
                </div>
            </#if>
            <div class="row mb-0" style="margin-bottom: 0;">
                <div class="col-md-6 themed-grid-col brdr">
                    <div>
                        <p style="font-size: 18px">${event.description}</p>
                    </div>
                    <div>
                    <#list event.laws as law>
                        <p>
                            ${law.article} ${law.law}
                        </p>
                    </#list>
                    </div>
                </div>
                <div class="col-md-3 themed-grid-col brdrl">
                    <p style="font-size: 18px">${event.dataAndTimeBegin}-${event.dataAndTimeEnd}</p>
                </div>
                <div class="col-md-3 themed-grid-col brdl">
                    <#list event.roles as role>
                        <p style="font-size: 18px">${role}</p>
                    </#list>
                </div>
            </div>
        </#list>
    </div>
</main>
</body>
</html>