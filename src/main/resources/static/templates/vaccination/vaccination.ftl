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
<form method="get" action="/changeVaccination">
    <div class="container" align="center">
        <button class="w-25 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Добавить прививку</button>
    </div>
</form>


    <div class="row">
        <div class="col-2"></div>
        <div class="col-7" align="center">
            <table style="margin-top: 50px; width: 100%">
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-top: black 2px solid;border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid; border-top: black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>Рекомендованный<br>возраст</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-top: black 2px solid;border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 25px">
                                <b>Дата прививки</b>
                            </p>
                        </div>
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Вирусный гепатит B, <br>первая прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>Первые 24 часа жизни</b>
                            </p>
                        </div>
                    </td >
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">

                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>БЦЖ (прививка<br> от туберкулеза)</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>3-7 сутки жизни</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Вирусный гепатит B, <br>вторая прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>1 месяц</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Пневмококковая инфекция,<br> первая прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>2 месяца</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>АКДС: Первая прививка против<br> дифтерии,коклюша,столбняка,<br>полиомиелита</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>3 месяца</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Гемофильная инфекция типа <br>b, первая прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>3 месяца</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Пневмококковая инфекция,<br> вторая прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>4,5 месяца</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>АКДС: Вторая прививка против<br> дифтерии,коклюша,столбняка,<br>полиомиелита</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>4-5 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Гемофильная инфекция типа <br>b, вторая прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>4-5 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>АКДС: Третья прививка против<br> дифтерии,коклюша,столбняка,<br>полиомиелита</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>6 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Вирусный гепатит B <br>b, третья прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>6 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Гемофильная инфекция типа <br>b, третья прививка</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>6 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Прививка против кори <br>краснухи, эпидемического паротита</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>12 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Ревакцинация против <br>пневмококковой инфекции</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>15 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Первая ревакцинация<br> против полиомиелита</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>18 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Первая ревакцинация <br>против дифтерии,коклюша,<br>столбняка </b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>18 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
                <tr style="width: 100%">
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid ">
                        <div align="center" style="width: 100%">
                            <p  style="margin-bottom: 25px">
                                <b>Вторая ревакцинация <br>против полиомиелита </b>
                            </p>
                        </div>
                    </td>
                    <td style="border-bottom:black 2px solid">
                        <div align="center" style="width: 100%">
                            <p valign="top" style="margin-bottom: 0px">
                                <b>20 месяцев</b>
                            </p>
                        </div>
                    </td>
                    <td style="border-left: black 2px solid;border-bottom:black 2px solid; border-right:black 2px solid">
                    </td>
                </tr>
            </table>
        </div>
        <div class="col-3"></div>
    </div>



    <div class="container" align="center" style="margin-top: 20px">
        <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
    </div>
</div>
</body>
</html>