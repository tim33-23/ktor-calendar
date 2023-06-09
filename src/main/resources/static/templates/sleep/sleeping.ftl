<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include "../head-config.ftl"/>
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
    <div class="container" style="padding-top: 40px">
        <div align="center">
            <#if model.count?number==0>
                <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                    <b> Сегодня </b>
                </p>
                <#else>
                    <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                        <b> Просматриваемая дата </b>
                    </p>
            </#if>

            <p style="font-size: 20px;font-family: 'Arial',SansSerif;">
                ${model.currentDate}</p>
        </div>
        <div align="center" style="">
            <main class="form-signin w-100" >
                    <div class="row">
                        <div class="col-2 mt-2" align="right"></div>
                        <div class="col-2" align="center">
                            <form method="post" action="/sleepPreview">
                                <input name="CountPreview" type="number" value="${model.count?number}" style="display: none">
                                <button type="submit" class="btn btn-secondary btn-lg" style="vertical-align: center;" >
                                    Предыдущий
                                </button>
                            </form>
                        </div>
                        <#if model.checkOnDreams==false>
                            <div class="col-4" align="center">
                                <form method="post" action="sleepOn">
                                    <button type="submit" class="btn btn-secondary btn-lg" style="vertical-align: center" >
                                        Начать сон
                                    </button>
                                </form>
                            </div>
                            <#else>
                                <div class="col-4" align="center">
                                    <form method="post" action="sleepOff">
                                        <button type="submit" class="btn btn-secondary btn-lg" style="vertical-align: center" >
                                            Закончить сон
                                        </button>
                                    </form>
                                </div>
                        </#if>

                        <div class="col-2" align="center">
                            <form method="post" action="/sleepNext">
                                <input name="CountNext" type="number" value="${model.count?number}" style="display: none">
                                <button type="submit" class="btn btn-secondary btn-lg" style="vertical-align: center" >
                                    Следующий
                                </button>
                            </form>
                        </div>
                        <div class="col-2 mt-2" align="right"></div>
                    </div>
            </main>



                <#assign previewDream=""/>
            <div class="row" style="padding-top: 20px; min-height: 20px; min-width: 200px">
                <#list model.dreams as dream>
                    <#if previewDream?has_content>
                <div class="row" >
                            <div class="col-2"></div>
                            <div class="col-2" style="background-color: #e5c7ca;border-left:black 2px solid;border-top:black 2px solid" ></div>
                            <div class="col-4"align="center" style="background-color:#e5c7ca; border-top:black 2px solid">
                                <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                                    <b>Бодрствование:</b>
                                </p>
                                <p style="font-size: 25px;font-family: 'Arial',SansSerif;">

                                    ${(dream.dateTimeSlStarted?datetime("dd.mm.yyyy hh:mm")?long - previewDream?datetime("dd.mm.yyyy hh:mm")?long)?number_to_time}
                                </p>
                            </div>
                            <div class="col-2" style="background-color: #e5c7ca;border-right:black 2px solid;border-top:black 2px solid"></div>
                            <div class="col-2"></div>
                    </div>
                    </#if>
                    <#assign previewDream = dream.dateTimeSlEnded/>
                    <div class="row" >
                        <div class="col-2"></div>
                        <div class="col-2" align="right" style="background-color: #e5c7ca;border-left: black 2px solid;border-bottom:black 2px solid; border-top: black 2px solid">
                            <p style="font-size: 25px;font-family: 'Arial',SansSerif;margin-top: 30px">
                                <b>
                                    ${dream.dateTimeSlStarted?datetime("dd.mm.yyyy hh:mm")?time}
                                </b>
                            </p>
                        </div>
                        <div class="col-4" align="center" style="background-color: #e5c7ca;border-bottom:black 2px solid; border-top: black 2px solid">
                            <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                                <b>
                                    <#if  !dream.dateTimeSlEnded?? && dream.dateTimeSlEnded?has_content>
                                        <#if dream.dateTimeSlStarted?datetime("dd.mm.yyyy hh:mm") == dream.dateTimeSlEnded?datetime("dd.mm.yyyy hh:mm")>
                                            Дневной сон
                                            <#else>
                                                Ночной сон
                                        </#if>
                                    <#else>
                                        <#if dream.dateTimeSlStarted?datetime("dd.mm.yyyy hh:mm")?date==model.now?datetime("dd.mm.yyyy hh:mm")?date>
                                            Дневной сон
                                        <#else>
                                            Ночной сон
                                        </#if>
                                    </#if>
                                </b>
                            </p>
                            <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                                <#if !dream.dateTimeSlEnded?? && dream.dateTimeSlEnded?has_content >
                                    ${(dream.dateTimeSlEnded?datetime("dd.mm.yyyy hh:mm")?long - dream.dateTimeSlStarted?datetime("dd.mm.yyyy hh:mm")?long)?number_to_time}
                                    <#else >
                                        ${(model.now?datetime("dd.mm.yyyy hh:mm")?long - dream.dateTimeSlStarted?datetime("dd.mm.yyyy hh:mm")?long)?number_to_time}
                                </#if>
                            </p>
                        </div>
                        <div class="col-2" align="left" style="background-color: #e5c7ca;border-right: black 2px solid;border-bottom:black 2px solid; border-top: black 2px solid">
                            <p style="font-size: 25px;font-family: 'Arial',SansSerif;margin-top: 30px">
                                <b>
                                    <#if dream.dateTimeSlEnded??>
                                        -
                                    <#else>
                                        ${dream.dateTimeSlEnded?datetime("dd.mm.yyyy hh:mm")}
                                    </#if>
                                </b>
                            </p>
                        </div>
                        <div class="col-2">
                            <button type="button" class="btn btn-lg" style="vertical-align: center;margin-top: 30px" >
                                <img src="/static/pen.png" alt="" width="30" height="30">
                            </button>
                            <button type="button" class="btn btn-lg" style="vertical-align: center;margin-top: 30px" >
                                <img src="/static/trash.png" alt="" width="30" height="30">
                            </button>
                        </div>
                    </div>
                </#list>
            <#if !previewDream?? && previewDream?has_content>
                <div class="row" style="padding-top: 20px;">
                    <div class="col-2"></div>
                    <div class="col-2" style="background-color: #e5c7ca;border-left:black 2px solid;border-top:black 2px solid" ></div>
                    <div class="col-4"align="center" style="background-color:#e5c7ca; border-top:black 2px solid">
                        <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                            <b>Бодрствование:</b>
                        </p>
                        <p style="font-size: 25px;font-family: 'Arial',SansSerif;">
                            ${(previewDream?datetime("dd.mm.yyyy hh:mm")?long - model.now?datetime("dd.mm.yyyy hh:mm")?long)?number_to_time}
                        </p>
                    </div>
                    <div class="col-2" style="background-color: #e5c7ca;border-right:black 2px solid;border-top:black 2px solid"></div>
                    <div class="col-2"></div>
                </div>
            </#if>
            </div>





            <div class="container" style="margin-top: 650px">
                <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>