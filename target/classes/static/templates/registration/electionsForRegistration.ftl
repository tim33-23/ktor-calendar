<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<main>
    <div class="container" style="padding-top: 80px">
        <h2>
            Для регистрации выберите избирательный процесс в котором вы учавствуете!
        </h2>
    </div>
    <div class="container" style="padding-top: 80px">
        <div class="row mb-3">
            <div class="col-md-8 themed-grid-col"><h2>Наименование</h2></div>
            <div class="col-md-4 themed-grid-col"><h2>Дата проведения</h2></div>
        </div>
        <#list elections as election>
            <div class="row mb-3">
                <form class="row mb-3" method="post" action="/registrationWithElection">
                    <label for="election" style="display: none"></label>
                    <input name="election" id="election" value="${election.id}" style="display: none">
                    <button class="row mb" style="border: none" type="submit">
                        <div class="col-md-8 themed-grid-col">
                            <h4>${election.nameElection}</h4>
                        </div>
                        <div class="col-md-4 themed-grid-col"><h4>${election.dateBeginElection}</h4></div>
                    </button>
                </form>
            </div>
        </#list>
    </div>
</main>
</body>
</html>