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
    <div class="container" style="margin-top: 80px">
        <div class="col-12 mt-5" >
            <p class="text-center" style="">
            <p class="text-center" style="font-size: 20px;font-family: 'Arial',SansSerif">
                Пожалуйста, измените информацию по вашему малышу
            </p>
        </div>
    </div>
    <div align="center" style="padding-top: 20px">
        <main class="form-signin w-100" >
            <form style="max-width: 25%;" method="post" action="/confirmCreateChild">
                <img class="mb-4" src="/static/baby2.png" alt="" width="310" height="220">

                <div class="form-floating" style="margin-top: 5px">
                    <input required type="name" name="name" class="form-control" id="floatingInput" placeholder="Имя">
                    <label for="floatingInput">${child.name}</label>
                </div>
                <div align="left">
                    <div class="form-check" style="margin-top: 10px">
                        <input class="form-check-input" type="radio" name="gender" id="RadioBoy" value="boy" <#if child.gender==true>checked</#if> >
                        <label class="form-check-label" for="RadioBoy">
                            Мальчик
                        </label>
                    </div>
                    <div class="form-check" style="margin-top: 5px">
                        <input class="form-check-input" type="radio" name="gender" id="RadioGirl" value="girl" <#if child.gender==false>checked</#if> >
                        <label class="form-check-label" for="RadioGirl">
                            Девочка
                        </label>
                    </div>
                    <div class="form-check"  style="margin-top: 5px; display: none">
                        <input class="form-check-input" type="radio" name="idChild" id="RadioGirl" value="${child.idChild}"></label>
                    </div>
                </div>
                <div class="form-floating" style="margin-top: 10px">
                    <input required type="date" name="dateOfBirth" class="form-control" id="confirmationPassword" placeholder="dateOfBirth">
                </div>
                <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Изменить</button>
                <div class="container" style="margin-top: 100px">
                    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
            </form>
        </main>

    </div>
</div>
</body>
</html>