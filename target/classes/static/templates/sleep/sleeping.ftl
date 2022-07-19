<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include "../head-config.ftl"/>
<body>
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
        </div>
    </nav>
    <div class="container" style="padding-top: 40px">
        <div align="center">
            <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                <b> Сегодня </b>
            </p>
            <p style="font-size: 20px;font-family: 'Arial',SansSerif;">
                22 июня </p>
        </div>
        <div align="center" style="padding-top: 20px">
            <main class="form-signin w-100" >
                <form style="max-width: 100%;">
                    <div class="row">
                        <div class="col-4" align="center">
                            <button type="button" class="btn btn-secondary btn-lg" style="vertical-align: center;" >
                                Предыдущий</button>
                        </div>
                        <div class="col-4"align="center">
                            <button type="button" class="btn btn-secondary btn-lg" style="vertical-align: center" >
                                Начать сон</button>
                        </div>
                        <div class="col-4" align="center">
                            <button type="button" class="btn btn-secondary btn-lg" style="vertical-align: center" >
                                Следующий</button>
                        </div>
                    </div>
                </form>
            </main>


            <div class="container" style="margin-top: 650px">
                <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>