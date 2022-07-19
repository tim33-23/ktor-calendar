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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link type="text/css" rel="StyleSheet" href="https://bootstraptema.ru/plugins/2016/shieldui/style.css" />
    <script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="https://bootstraptema.ru/plugins/2016/shieldui/script.js"></script>

    <br><br><br>

    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">

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
                                min:0,
                                max: 100,
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
                                max: 24,
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
                                data: [40.7, 52.8, 62.1, 70, 75, 79.4, 82]
                            }, {
                                seriesType: 'line',
                                axis: 1,
                                collectionAlias: "Средняя норма веса",
                                data: [30.7, 36.8, 42.1, 45, 47, 50, 52]
                            }, {
                                seriesType: 'line',
                                axis: 2,
                                collectionAlias: "Нижняя норма веса",
                                data: [20, 26, 32, 35, 38, 40, 42]
                            }]
                        });
                    });
                </script><!-- /.График -->

            </div><!-- /.col-md-8 col-md-offset-2 -->
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
                                    75.0 г
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
                                    75.0 см
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
            </table>
            <div class="container" style="margin-top: 160px">
                <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
            </div>
        </main>
    </div>
</div>
</body>
</html>