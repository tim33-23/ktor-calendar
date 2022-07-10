<!doctype html>
<html lang="en">
<#include "templates/head-config.ftl"/>
<body >
<div class="container" style="background-image: linear-gradient(to right, rgba(255,255,255,0.4) 0 100%), url('static/fon3.jpg'); height: 100%">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Мой малыш</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" aria-current="page" href="#">Малыши</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="#">Рост</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="#">Вес</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="#">Сон</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="#">Зубы</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="#">Прививки</a>
                    </li>
                    <li class="nav-item" style="font-size: 20px">
                        <a class="nav-link active" href="#">Прикорм</a>
                    </li>
                </ul>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <form class="d-flex" role="search">
                <button class="btn btn-secondary logout" type="submit" style="margin-right: 5px">Выход</button>
            </form>
    </nav>

    <div align="center" style="padding-top: 100px">
        <img class="mb-4" src="/static/baby2.png" alt="" width="310" height="220">
        <div class="container">
            <div class="row" align="center">
                <div class="col-5 mt-2" align="right">
                    <button type="button" class="btn btn-secondary btn-lg" style="vertical-align: center" >
                        <img src="/static/line.png" alt="" width="50" height="50">
                        Рост</button>
                </div>
                <div class="col-2">
                    <p class="text-center" style="font-size: 17px;font-family: 'Arial',SansSerif; width: 100px;height: 100px;">
                        <b> Лиза<br>19.03.2021<br>15 месяцев</b>
                    </p>
                </div>
                <div class="col-5 mt-2" align="left">
                    <button type="button" class="btn btn-secondary btn-lg">
                        <img src="/static/weight.png" alt="" width="50" height="50">
                        Вес</button>
                </div>
            </div>
            <div class="row" align="center">
                <div class="col-2 mt-2" align="right">
                </div>
                <div class="col-8" width="10px" height="10px">
                    <button type="button" class="btn btn-secondary btn-lg" style="vertical-align: center;margin-right: 20px" width="300" height="300" >
                        <img src="/static/sleep.png" alt="" width="55" height="50">
                        Сон</button>
                    <button type="button" class="btn btn-secondary btn-lg">
                        <img src="/static/tooth.png" alt="" width="50" height="50">
                        Зубы</button>
                </div>
                <div class="col-2 mt-2" align="left">
                </div>
            </div>
            <div class="row" align="center" style="margin-top: 20px">
                <div class="col-2 mt-2" align="right">
                </div>
                <div class="col-8" width="10px" height="10px">
                    <button type="button" class="btn btn-secondary btn-lg" style="vertical-align: center;margin-right: 20px" width="300" height="300" >
                        <img src="/static/vaccine.png" alt="" width="50" height="50">
                        Прививки</button>
                    <button type="button" class="btn btn-secondary btn-lg">
                        <img src="/static/meal.png" alt="" width="50" height="50">
                        Прикорм</button>
                </div>
                <div class="col-2 mt-2" align="left">
                </div>
            </div>
        </div>
        <div class="container" style="margin-top: 250px">
            <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
        </div>
    </div>

</div>
</div>
</div>
</body>
</html>