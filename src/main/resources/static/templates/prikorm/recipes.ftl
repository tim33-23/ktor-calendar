<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
<body >
<div class="container d-flex flex-column min-vh-100" style="background-image: linear-gradient(to right, rgba(255,255,255,0.4) 0 100%), url('static/fon3.jpg'); height: 100%">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Мой малыш</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item" style="font-size: 20px">
                        <form method="post" action="/children">
                            <input name="idChild" type="number" value="1" style="display: none">
                            <button class="btn btn-link nav-link active" type="submit">Малыши</button>
                        </form>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <form method="get" action="/height">
                            <button class="btn btn-link nav-link active" type="submit">Рост</button>
                        </form>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <form method="get" action="/weight">
                            <button class="btn btn-link nav-link active" type="submit">Вес</button>
                        </form>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <form method="get" action="/sleep">
                            <button class="btn btn-link nav-link active" type="submit">Сон</button>
                        </form>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <form method="get" action="/tooth">
                            <button class="btn btn-link nav-link active" type="submit">Зубы</button>
                        </form>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <form method="get" action="/vaccination">
                            <button class="btn btn-link nav-link active" type="submit">Прививки</button>
                        </form>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <form method="get" action="/food">
                            <button class="btn btn-link nav-link active" type="submit">Прикорм</button>
                        </form>
                    </li>
                </ul>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <form class="d-flex" role="search" method="get" action="/logout">
                <button class="btn btn-secondary logout" type="submit" style="margin-right: 5px">Выход</button>
            </form>
        </div>
    </nav>

    <div class="container" style="padding-top: 20px">
        <div class="form-floating" style="margin-top: 15px">
            <div class="container">
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                6-ой Месяц
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <div class="accordion" id="accordionExample1">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="headingOne1">
                                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1">
                                                Пюре из брокколи и моркови
                                            </button>
                                        </h2>
                                        <div id="collapseOne1" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample1">
                                            <div class="accordion-body">
                                                <div class="row">
                                                    <div class="col-4 container">
                                                        <img src="/static/recipe6-1.png" style="max-width: 100%" alt="">
                                                    </div>
                                                    <div class="col-8">
                                                        <p>
                                                            Ингредиенты: брокколи — 3 соцветия, морковь кубиками — 1/2 стакана, молоко или вода — 70 мл, масло растительное — 1 ч.л. <br>
                                                            Приготовление: Готовить овощи на пару 15-20 минут, сделать однородное пюре с помощью блендера.
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="headingOne2">
                                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne2" aria-expanded="true" aria-controls="collapseOne2">
                                                Пюре из кабочка и картофеля
                                            </button>
                                        </h2>
                                        <div id="collapseOne2" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample1">
                                            <div class="accordion-body">
                                                <div class="row">
                                                    <div class="col-4 container">
                                                        <img src="/static/recipe6-1.png" style="max-width: 100%" alt="">
                                                    </div>
                                                    <div class="col-8">
                                                        <p>
                                                            Ингредиенты: кабачок кубиками — 1 стакан, картофель кубиками — 1/5 стакана молоко или вода — 40 мл оливковое масло — пол ч.л.<br>
                                                            Приготовление:  Картошку замочить в холодной воде, чтобы вышел крахмал. Готовим на пару 15 минут. Сначала кладем в пароварку картофель, через 5 минут добавляем кабачок. Спюрировать овощи.
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading8">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse8" aria-expanded="false" aria-controls="collapse8">
                                    8-ой Месяц
                                </button>
                            </h2>
                            <div id="collapse8" class="accordion-collapse collapse" aria-labelledby="heading8" data-bs-parent="#accordionExample8">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample8" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="headingOne8">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse8" aria-expanded="true" aria-controls="collapse8">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapse8" class="accordion-collapse collapse" aria-labelledby="headingOne8" data-bs-parent="#accordionExample8">
                                                <div class="accordion-body">
                                                    <div class="row">
                                                        <div class="col-4 container">
                                                            <img src="/static/recipe6-1.png" style="max-width: 100%" alt="">
                                                        </div>
                                                        <div class="col-8">
                                                            <p>
                                                                Ингредиенты: кабачок кубиками — 1 стакан, картофель кубиками — 1/5 стакана молоко или вода — 40 мл оливковое масло — пол ч.л.<br>
                                                                Приготовление:  Картошку замочить в холодной воде, чтобы вышел крахмал. Готовим на пару 15 минут. Сначала кладем в пароварку картофель, через 5 минут добавляем кабачок. Спюрировать овощи.
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    9-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    10-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    11-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    12-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    13-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    14-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading9">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                    15-ый Месяц
                                </button>
                            </h2>
                            <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9" data-bs-parent="#accordionExample9">
                                <div class="accordion-body">
                                    <div class="accordion" id="accordionExample9" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="heading9">
                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                                                    Пюре из кабочка и картофеля
                                                </button>
                                            </h2>
                                            <div id="collapseOne9" class="accordion-collapse collapse" aria-labelledby="headingOne1" data-bs-parent="#accordionExample9">
                                                <div class="accordion-body">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>