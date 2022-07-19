<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
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
        </div>
    </nav>

    <div align="center" style="padding-top: 130px">
        <main class="form-signin w-100" >
            <form style="max-width: 25%;">
                <img class="mb-4" src="/static/inserttooth.png" alt="" width="300" height="300" >

                <div class="form-floating" style="margin-top: 15px">
                    <input type="date" class="form-control" id="confirmationDate" placeholder="dateOfBirth">
                </div>
                <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Сохранить</button>
                <div class="container" style="margin-top: 280px">
                    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
            </form>
        </main>
    </div>
</div>
</body>
</html>