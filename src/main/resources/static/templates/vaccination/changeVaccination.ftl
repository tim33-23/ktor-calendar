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

    <div align="center" style="padding-top: 120px">
        <main class="form-signin w-100" >
            <form style="max-width: 25%;" method="post" action="/vaccination">
                <img src="/static/insertvaccine.png" alt="" width="300" height="300" >

                <div align="center" >
                    <main class="form-signin w-100" >
                        <form style="max-width: 25%;">
                            <div class="form-floating">
                                <select class="form-select" aria-label="Default select example">
                                    <option value="1">
                                        Вирусный гепатит B,
                                        первая прививка
                                    </option>
                                    <option value="2">БЦЖ (прививка
                                        от туберкулеза)
                                    </option>
                                    <option value="3">
                                        Вирусный гепатит B,
                                        вторая прививка
                                    </option>
                                    <option value="3">
                                        Пневмококковая инфекция,
                                        первая прививка
                                    </option>
                                    <option value="3">
                                        АКДС: Первая прививка против
                                        дифтерии,коклюша,столбняка,
                                        полиомиелита
                                    </option>
                                    <option value="3">
                                        Гемофильная инфекция типа
                                        b, первая прививка
                                    </option>
                                </select>
                            </div>
                            <div class="form-floating" style="margin-top: 10px">
                                <input type="date" class="form-control" id="dateofVaccine" placeholder="dateofVaccine">
                            </div>
                            <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Добавить</button>
                        </form>
                    </main>
                </div>
                <div class="container" style="margin-top: 260px">
                    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
                </div>
            </form>
        </main>
    </div>
</div>
</body>
</html>