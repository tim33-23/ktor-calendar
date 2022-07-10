<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
<body >
<div class="container" style="background-image: linear-gradient(to right, rgba(255,255,255,0.4) 0 100%), url('static/fon3.jpg'); height: 100%">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" style="margin-left: 5px" href="/login">
                Мой малыш
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form class="d-flex" role="search">
            <button class="btn btn-secondary login" type="submit" style="margin-right: 5px" href="/login">Вход</button>
            <button class="btn btn-secondary registration" type="submit" href="/registration">Регистрация</button>
        </form>
    </nav>

    <div align="center" style="padding-top: 150px">
        <main class="form-signin w-100" >
            <form style="max-width: 25%;" method="post" action="/registration">
                <img class="mb-4" src="/static/baby2.png" alt="" width="310" height="220">

                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" name="email" placeholder="name@example.com">
                    <label for="floatingInput">E-mail</label>
                </div>
                <div class="form-floating" style="margin-top: 10px">
                    <input type="password" class="form-control" id="Password" name="password" placeholder="Password">
                    <label for="floatingPassword">Пароль</label>
                </div>
                <div class="form-floating" style="margin-top: 10px">
                    <input type="confirmationPassword" class="form-control" id="confirmationPassword" name="confirmationPassword" placeholder="СonfirmationPassword">
                    <label for="confirmationPassword">Подтвердите пароль</label>
                </div>
                <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Регистрация</button>
                <div class="container" style="margin-top: 210px">
                    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
            </form>
        </main>

    </div>
</div>
</div>
</body>
</html>