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
                <label class="form-label" for="nameElection">Название календарного плана</label>
                <textarea type="text" class="form-control" id="nameElection" name="nameElection" placeholder="Название календарного плана" rows="4"></textarea>
            </div>
            <div class="form-floating">
                <input type="datetime-local" class="form-control" id="dataBegin" name="dataBegin" placeholder="">
                <label for="dataBegin">Дата начала выборов</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Создать</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
        </form>
    </main>

</div>

</body>
</html>
