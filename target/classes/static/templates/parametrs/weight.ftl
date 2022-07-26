<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2016/shieldui/script.js"></script>
<body>
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

    <div align="center" style="padding-top: 100px">
    <div class="container" align="center">
        <div class="row">
            <div class="col-2"></div>
            <div class="col-8">

                <!-- График --><div id="chart"></div>

                <script>
                    $(function () {
                        $("#chart").shieldChart({
                            theme: "light",
                            primaryHeader: {
                                text: "Вес"
                            },
                            exportOptions: {
                                image: false,
                                print: false
                            },
                            axisY: [{
                                min:2900,
                                max: 10000,
                                title: {
                                    text: 'Вес(г)',
                                    style: {
                                        color: '#4DB0F5'
                                    }
                                },
                                axisTickText: {
                                    style: {
                                        color: '#4DB0F5'
                                    }
                                }
                            }, ],
                            axisX: [{
                                min: 0,
                                max: 12,
                                title: {
                                    text: 'Вес(полных месяцев)',
                                    style: {
                                        color: '#4DB0F5'
                                    }
                                },
                                axisTickText: {
                                    style: {
                                        color: '#4DB0F5'
                                    }
                                }
                            }, ],
                            dataSeries: [{
                                seriesType: 'line',
                                axis: 0,
                                collectionAlias: "Верхняя норма веса",
                                data: [3600, 4600, 5690, 6460, 7000, 7500, 7900, 8300, 8600, 8900, 9200, 9500, 9700]
                            }, {
                                seriesType: 'line',
                                axis: 1,
                                collectionAlias: "Фактический вес",
                                data: [3460, 4300, 5300, 5900, 6500, [], 7300, 7700]
                            }, {
                                seriesType: 'line',
                                axis: 1,
                                collectionAlias: "Фактический вес",
                                data: [[], [], [], [], [], [], [], [], 7900, 8300, 8500, 8800]
                            },{
                                seriesType: 'line',
                                axis: 2,
                                collectionAlias: "Нижняя норма веса",
                                data: [2900, 3800, 4700, 5400, 5900, 6400, 6700, 7000, 7300, 7600, 7810, 8000, 8200]
                            }]
                        });
                    });
                </script><!-- /.График -->

            </div><!-- /.col-md-8 col-md-offset-2 -->
            <div class="col-2"></div>
        </div><!-- /.row -->
    </div><!-- /.container -->
    <div align="center" style="padding-top: 50px">
        <main class="form-signin w-100" >
            <form style="max-width: 40%;" method="get" action="/addWeight">
                <button class="w-100 btn btn-lg btn-secondary" type="submit" style="margin-top: 20px">Добавить новое значение роста</button>
            </form>
            <table class="container" style="margin-top: 10px; max-width: 800px;">
                <tr>
                    <td width="200px">
                        <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                            <b>
                                Вес
                            </b>
                        </p>
                    </td>
                    <td width="200px">
                        <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                            <b>
                                Дата
                            </b>
                        </p>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    3460 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.03.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
                <tr style="border-top: black solid 2px">
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    4300 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.04.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
                <tr style="border-top: black solid 2px">
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    5300 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.05.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
                <tr style="border-top: black solid 2px">
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    5900 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.06.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
                <tr style="border-top: black solid 2px">
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    6500 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.07.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
                <tr style="border-top: black solid 2px">
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    7300 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.09.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
                <tr style="border-top: black solid 2px">
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    7700 г
                                </b>
                            </p>
                        </div>
                    </td>
                    <td width="100px">
                        <div style="padding-top: 10px">
                            <p style="font-size: 20px;font-family: 'Arial',SansSerif">
                                <b>
                                    16.10.2021
                                </b>
                            </p>
                        </div>
                    </td>
                    <td>
                        <form method="get" action="/editWeight">
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Изменить</button>
                        </form>
                    </td>
                    <td align="top">
                        <form method="get" action="/deletedWeight" >
                            <button class="btn btn-secondary" type="submit" style="vertical-align: top">Удалить</button>
                        </form>
                    </td>
                </tr>
            </table>
            <div class="container" style="margin-top: 160px">
                <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
            </div>
        </main>
    </div>
    </div>
</div>
</body>
</html>