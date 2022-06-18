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
        <div class="container">
            <h2 align="center">
                <br>${election.nameElection}
            </h2>
        </div>
        <div class="row mb-0">
            <table class="row mb-0" >
                <tr style="border: 2px solid black; background-color: #f0f0f0">
                    <td class="mycol col-md-6 themed-grid-col" style="border: 2px solid black">
                        <div>
                            <h1 style="font-size: 25px"><b>Содержание мероприятия</b></h1>
                        </div>
                    </td>
                    <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                        <div >
                            <h1 style="font-size: 25px"><b>Срок исполнения</b></h1>
                        </div>
                    </td>
                    <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                        <div>
                            <h1 style="font-size: 25px"><b>Исполнители</b></h1>
                        </div>
                    </td>
                </tr>
                <#list events as event>
                    <#if section!=event.section>
                        <#assign section=event.section/>
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
                                            ${law.article} ${law.law}
                                        </p>
                                    </#list>
                                </div>
                            </div>
                        </td>
                        <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                            <div >
                                <p style="font-size: 18px">${event.dataAndTimeBegin}-${event.dataAndTimeEnd}</p>
                            </div>
                        </td>
                        <td class="col-md-3 themed-grid-col" style="border: 2px solid black">
                            <div >
                                <#list event.roles as role>
                                    <p style="font-size: 18px">${role}</p>
                                </#list>
                            </div>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</main>
</body>
</html>