<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<div align="center" style="padding-top: 150px">
    <main class="form-signin w-100" >
        <form style="max-width: 25%;" action="/createElection" method="Post">
            <img class="mb-4" src="/static/flag.jpg" alt="" width="72" height="57">
            <h1 class="h3 mb-3 fw-normal">Заполните поля ниже</h1>
            <div class="mb-2">
                <textarea type="text" class="form-control" id="nameElection" name="nameElection" placeholder="Название календарного плана" rows="4"></textarea>
            </div>
            <div class="form-floating">
                <input type="datetime-local" class="form-control" id="dateBegin" name="dateBegin" placeholder="">
                <label for="dateBegin">Дата начала голосования</label>
            </div>
            <#if elections?has_content>
                <div class="row mt-3"><p>Выбрать шаблон для календарного плана</p></div>
                <div class="row mt-1">
                    <div class="col-1"></div>
                    <div class="form-check form-switch col-3 align-content-center">
                        <div>
                            <input class="form-check-input" role="switch" type="checkbox" id="checkOnSelectionElection" name="checkOnSelectionElection">
                        </div>
                    </div>
                    <div class="col-8 container">
                        <div class="form-floating">
                            <div class="themed-grid-col">
                                <select id="idElection" name="idElection" style="max-width: 100%">
                                    <#list elections as election>
                                        <option value="${election.id}">${election.nameElection}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </#if>
            <button class="w-100 mt-2 btn btn-lg btn-primary" type="submit">Создать</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
        </form>
    </main>

</div>

</body>
</html>
