<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
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
            <button class="btn btn-secondary logout" type="submit" style="margin-right: 5px">Выход</button>
        </form>
    </nav>
    <div class="container" style="margin-top: 80px">
        <div class="col-12 mt-5" >
            <p class="text-center" style="">
            <p class="text-center" style="font-size: 25px;font-family: 'Arial',SansSerif">
                <b>
                    Добро пожаловать!
                </b>
            </p>
            <p class="text-center" style="font-size: 20px;font-family: 'Arial',SansSerif">
                Пожалуйста, добавьте информацию по вашему малышу
            </p>
        </div>
    </div>
    <div align="center" style="padding-top: 20px">
        <main class="form-signin w-100" >
            <form style="max-width: 25%;" method="post" action="/createChild">
                <img class="mb-4" src="/static/baby2.png" alt="" width="310" height="220">

                <div class="form-floating" style="margin-top: 5px">
                    <input required type="name" name="name" class="form-control" id="floatingInput" placeholder="Имя">
                    <label for="floatingInput">Имя малыша</label>
                </div>
                <div align="left">
                    <div class="form-check" style="margin-top: 10px">
                        <input class="form-check-input" type="radio" name="gender" id="RadioBoy" value="boy" checked>
                        <label class="form-check-label" for="RadioBoy">
                            Мальчик
                        </label>
                    </div>
                    <div class="form-check" style="margin-top: 5px">
                        <input class="form-check-input" type="radio" name="gender" id="RadioGirl" value="girl">
                        <label class="form-check-label" for="RadioGirl">
                            Девочка
                        </label>
                    </div>
                </div>
                <div class="form-floating" style="margin-top: 10px">
                    <input required type="date" name="dateOfBirth" class="form-control" id="confirmationPassword" placeholder="dateOfBirth">
                </div>
                <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Добавить</button>
                <p class="text-center" style="font-size: 15px;font-family: 'Arial',SansSerif;margin-top: 5px">
                    Обратите внимание, добавить еще <br> одного ребенка вы сможете<br> в разделе "Малыши".
                </p>
                <div class="container" style="margin-top: 100px">
                    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
            </form>
        </main>

    </div>
</div>
</body>
</html>