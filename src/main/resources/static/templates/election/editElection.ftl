<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<#assign section=""/>

<main>
    <div class="container" style="margin-top: 10px;">
        <div class="container">
            <h2 align="center">
                <br>${election.nameElection}: ${election.dateBeginElection}
            </h2>
        </div>
        <div class="row mt-5">
            <div class="container row" align="center">
                <div class="col-6">
                    <button class="btn btn-primary col-6" style="width: max-content" type="button" data-bs-toggle="collapse" data-bs-target="#edit" aria-expanded="false" aria-controls="edit">
                        Редактировать название плана и дату начала
                    </button>
                </div>
                <div class="col-6">
                    <button class="btn btn-primary col-6"  type="button" data-bs-toggle="collapse" data-bs-target="#addEvent" aria-expanded="false" aria-controls="addEvent">
                        Добавить мероприятие
                    </button>
                </div>
            </div>
            <div class="collapse mt-5" id="edit" align="center">
                    <form style="max-width: 25%;" action="/editElection" method="Post">
                        <h1 class="h3 mb-3 fw-normal">Редактирование</h1>
                        <div class="mb-2">
                            <textarea type="text" class="form-control" id="nameElection" name="nameElection" placeholder="Название календарного плана" rows="4"></textarea>
                        </div>
                        <div  class="form-floating" style="display: none">
                            <input type="text" class="form-control" id="idElection" name="idElection" placeholder="">
                            <label for="idElection"></label>
                        </div>
                        <div class="form-floating">
                            <input type="datetime-local" class="form-control" id="dateBegin" name="dateBegin" placeholder="">
                            <label for="dateBegin">Дата начала выборов</label>
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Изменить</button>
                        <p class="mt-5 mb-3 text-muted">&copy;2023</p>
                    </form>
            </div>

            <div class="form-signin w-500 mt-5" id="addEvent" align="center">
                <form style="max-width: 25%; margin-bottom: 150px" action="/addEvent" method="Post">
                    <#include "../event/formAddEvent.ftl"/>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>