<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
<body >
<div class="container" style="background-image: linear-gradient(to right, rgba(255,255,255,0.4) 0 100%), url('static/fon3.jpg'); height: 100%">
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
                        <a class="nav-link active" href="/height">Рост</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="/weight">Вес</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="/sleep">Сон</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="/tooth">Зубы</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="/vaccination">Прививки</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="/recipe">Прикорм</a>
                    </li>
                </ul>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <form class="d-flex" role="search">
                <button class="btn btn-secondary logout" type="submit" style="margin-right: 5px">Выход</button>
            </form>
        </div>
    </nav>

    <div class="container" style="padding-top: 90px">
        <div class="form-floating" style="margin-top: 15px">
            <div class="container">
                <#if children?has_content>
                    <#list children as child>
                        <div class="row" align="left">
                            <div class="col-3">
                                <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                                    <b>
                                        ${child.name}
                                    </b>
                                </p>
                                <p style="font-size: 20px;font-family: 'Arial',SansSerif;">
                                    ${child.period.years}год, ${child.period.months} месяца, ${child.period.days} дня
                                </p>
                                <p style="font-size: 20px;font-family: 'Arial',SansSerif; ">
                                    <#if child.gender==true>
                                        Мальчик
                                        <#else>
                                        Девочка
                                    </#if>
                                </p>
                            </div>
                            <div class="col-4 mt-5" >
                                <div class="row">
                                    <div class="col-4">
                                        <form method="post" action="/editChild">
                                            <button type="submit" class="btn btn-secondary btn-lg" name="idChild" value="${child.idChild}">
                                                Изменить</button>
                                        </form>
                                    </div>
                                    <div  class="col-4 ui-corner-left" >
                                        <form method="post" action="/deleteChild">
                                            <button type="submit" class="btn btn-secondary btn-lg" name="idChild" value="${child.idChild}">
                                                Удалить</button>
                                        </form>
                                    </div>
                                    <div  class="col-4 ui-corner-left" >
                                        <form method="post" action="/child">
                                            <button type="submit" class="btn btn-secondary btn-lg" style="background-color: #20c997" name="idChild" value="${child.idChild}">
                                                Выбрать</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-5" align="left"></div>
                        </div>
                    </#list>
                </#if>
                <div class="row" align="left">
                    <div class="col-3">
                        <form method="get" action="/addChild">
                            <button type="submit" class="btn btn-secondary btn-lg">
                                Добавить ребенка</button>
                        </form>
                    </div>
                    <div class="col-4 mt-5"></div>
                    <div class="col-5" align="left"></div>
                </div>
                <div class="container" style="margin-top: 350px">
                    <p align="center" class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>