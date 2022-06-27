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

    <div align="center" style="padding-top: 150px">
        <main class="form-signin w-100" >
            <form style="max-width: 50%;">
                <img class="mb-4" src="/static/baby2.png" alt="" width="310" height="220">
                <tr>
                    <td>
                        <div class="form-floating">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput">E-mail</label>
                        </div>
                    </td>
                    <td>
                        <div class="form-floating">
                            <div class="form-floating" style="margin-top: 10px">
                                <input type="date" class="form-control" id="confirmationPassword" placeholder="dateOfBirth">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-floating">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput">E-mail</label>
                        </div>
                    </td>
                    <td>
                        <div class="form-floating">
                            <div class="form-floating" style="margin-top: 10px">
                                <input type="date" class="form-control" id="confirmationPassword" placeholder="dateOfBirth">
                            </div>
                        </div>
                    </td>
                </tr>


                <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Сохранить</button>
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