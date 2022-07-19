<!doctype html>
<html lang="en">
<#include "templates/head-config.ftl"/>
<body >
<div class="container" style="background-image: linear-gradient(to right, rgba(255,255,255,0.4) 0 100%), url('static/fon3.jpg'); height: 100%">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" style="margin-left: 5px" href="#">
                Мой малыш
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form class="d-flex" role="search">
            <button class="btn btn-secondary login" type="submit" style="margin-right: 5px">Вход</button>
            <button class="btn btn-secondary registration" type="submit">Регистрация</button>
        </form>
    </nav>
    <div class="row" align="center" style="margin-top: 250px">
        <div class="col-3">
        </div>
        <div class="col-3" align="right">
            <div align="left">
                <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                    <b> Время начала </b>
                </p>
            </div>
        </div>
        <div class="col-6" align="center">
        </div>
    </div>
    <div class="row" align="center">
        <div class="col-3">
        </div>
        <div class="col-3" align="right">
            <div class="form-floating">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">E-mail</label>
            </div>
        </div>
        <div class="col-3" align="center">
            <div class="form-floating">
                <div class="form-floating">
                    <input type="date" class="form-control" id="confirmationPassword" placeholder="dateOfBirth">
                </div>
            </div>
        </div>
        <div class="col-2" align="left" >
        </div>
        <div class="col-2">
        </div>
    </div>

    <div class="container" style="padding-top: 10px">
        <div class="row" align="center">
            <div class="col-3">
            </div>
            <div class="col-3" align="right">
                <div align="left">
                    <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                        <b> Время окончания </b>
                    </p>
                </div>
            </div>
            <div class="col-6" align="center">
            </div>
        </div>
    </div>
    <div class="row" align="center">
        <div class="col-3">
        </div>
        <div class="col-3" align="right">
            <div class="form-floating">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">E-mail</label>
            </div>
        </div>
        <div class="col-3" align="center">
            <div class="form-floating">
                <div class="form-floating">
                    <input type="date" class="form-control" id="confirmationPassword" placeholder="dateOfBirth">
                </div>
            </div>
        </div>
        <div class="col-2" align="left" >
        </div>
        <div class="col-2">
        </div>
    </div>

    <div class="row" align="center" style="margin-top: 10px">
    <div class="col-3">
    </div>
    <div class="col-3" align="left" style="font-size:20px">
        <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
            <label class="form-check-label" for="flexRadioDefault1">
                День
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Ночь
            </label>
        </div>
    </div>
    <div class="col-3" align="left" style="font-size:20px">
    </div>
    <div class="col-2" align="left" >
    </div>
    <div class="col-2">
    </div>
</div>




                <button class="w-25 btn btn-lg btn-secondary" type="submit" style="margin-top: 30px;margin-left: 500px" >Сохранить</button>
                <div class="container" align="center" style="margin-top: 350px">
                    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
</div>
</body>
</html>