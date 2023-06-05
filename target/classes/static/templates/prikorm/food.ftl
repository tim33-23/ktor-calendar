<!doctype html>
<html lang="en">
<#include "../head-config.ftl"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2016/shieldui/script.js"></script>
<script async src="https://cdn.jsdelivr.net/npm/es-module-shims@1/dist/es-module-shims.min.js" crossorigin="anonymous"></script>
<body >
<div class="container d-flex flex-column min-vh-100" style="background-image: linear-gradient(to right, rgba(255,255,255,0.4) 0 100%), url('static/fon3.jpg');" >

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
    <div class="container" style="padding-top: 10px">
        <form class="d-flex" role="search" method="get" action="/recipe">
            <button type="submit" class="btn btn-secondary btn-lg" style="vertical-align: center;margin-left: 20px">
                Рецепты
            </button>
        </form>
    </div>

    <div class="accordion" id="accordionExample" style="margin-top: 50px">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    Для чего нужен прикорм
                </button>
            </h2>
            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    Своевременное введение правильно подобранных продуктов для прикорма способствует укреплению здоровья, улучшению пищевого статуса и физическому развитию грудных детей и детей раннего возраста в период ускоренного роста и поэтому должно находиться в центре внимания системы здравоохранения.

                    В течение всего периода введения прикорма материнское молоко должно оставаться главным видом молока, потребляемого грудным ребенком.

                    Продукты для прикорма следует вводить примерно в возрасте 6 месяцев. Некоторым грудным детям продукты для прикорма могут понадобиться и раньше, но не ранее 4-месячного возраста.

                    Не следует давать немодифицированного коровьего молока до достижения 9-месячного возраста в качестве питья, но его можно использовать в малых количествах при приготовлении пищи для прикорма начиная с 6–9 месяцев. С 9–12 месяцев можно постепенно вводить коровье молоко в рацион питания грудного ребенка и в качестве питья.

                    Продукты для прикорма с низкой энергетической плотностью могут ограничить потребление энергии, поэтому средняя энергетическая плотность обычно должна быть не ниже 4,2 кДж (1 ккал)/г. Эта энергетическая плотность зависит от частоты приема пищи и может быть ниже, если пища принимается чаще. Не следует давать молока с низким содержанием жира примерно до двух лет.

                    Введение прикорма должно представлять собой процесс введения продуктов питания, все более разнообразных по своей консистенции, вкусу, аромату и внешнему виду, при одновременном продолжении кормления грудью.

                    Не следует давать в период введения прикорма сильно соленых продуктов, и не нужно в течение этого периода добавлять в пищу соль.

                </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    ЧТО ТАКОЕ ВВЕДЕНИЕ ПРИКОРМА?
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    Введение прикорма – это кормление грудных детей продуктами и жидкостями в дополнение к грудному молоку. Пищу для прикорма можно разделить на следующие категории:

                    •	пища переходного периода – это продукты для прикорма, специально предназначенные для удовлетворения специфических пищевых или физиологических потребностей грудного ребенка;

                    •	пища с семейного стола, или домашняя пища – это продукты для прикорма, которые даются ребенку раннего возраста и которые в общих чертах являются теми же продуктами, что и продукты, потребляемые остальными членами семьи.

                    В период перехода от исключительно грудного вскармливания к прекращению кормления грудью грудные дети постепенно приучаются есть домашнюю пищу, пока она полностью не заменит грудное молоко (см. рис. 1). Дети физически способны потреблять продукты с семейного стола к возрасту 1 года, после чего эти продукты больше не нужно модифицировать для удовлетворения особых потребностей грудного ребенка.

                    Возраст, в котором вводится пища переходного периода, представляет собой особенно уязвимый период в развитии ребенка. Рацион питания претерпевает свое наиболее коренное изменение – это переход от единственного продукта (грудного молока), где главным источником энергии является жир, ко все более увеличивающемуся разнообразию продуктов, которые требуются для удовлетворения пищевых потребностей. Этот переход связан не только с возрастающими и меняющимися потребностями в пищевых веществах, но также и с быстрым ростом, физиологическим созреванием и развитием ребенка.

                    Плохое питание и неправильные принципы и методы кормления в этот критический период могут увеличить риск нарушений физического развития (истощения и остановки роста) и недостаточности питательных веществ, особенно железа, и могут иметь долговременные отрицательные последствия для здоровья и умственного развития. Поэтому к числу наиболее эффективных с точки зрения затрат мероприятий, которые могут осуществлять и поддерживать медицинские работники,

                    относятся алиментарные вмешательства и улучшение принципов и методов кормления, направленные на грудных детей.
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    Пищеварение и всасывание
                </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    У грудных детей секреция желудочных, кишечных и панкреатических пищеварительных ферментов не развита так, как у взрослых. Тем не менее, грудной ребенок способен полностью и эффективно переваривать и всасывать пищевые вещества, содержащиеся в грудном молоке, а в грудном молоке содержатся ферменты, которые способствуют гидролизу жиров, углеводов и белков в кишечнике.Аналогичным образом, в раннем грудном возрасте секреции солей желчных кислот лишь едва хватает для образования мицеллы, а эффективность всасывания жира ниже, чем у детей более старшего возраста и взрослых. Эту недостаточность может отчасти компенсировать присутствующая в грудном молоке, но отсутствующая в детских питательных смесях промышленного производства липаза, стимулируемая солями желчной кислоты. Примерно к 4 месяцам желудочная кислота помогает желудочному пепсину полностью переваривать белок.

                    Хотя панкреатическая амилаза начинает вносить полноцен- ный вклад в переваривание крахмалов только в конце первого года, большинство прошедших тепловую обработку крахмалов перевариваются и всасываются почти полностью (4). Даже в первый месяц жизни толстая кишка играет жизненно важную роль в окончательном переваривании тех пищевых веществ, которые не полностью всасываются в тонкой кишке. Микрофлора толстой кишки изменяется с возрастом и в зависимости от того, вскармливается ли ребенок грудью или искусственно. Микрофлора ферментирует непереваренные углеводы и способные к сбраживанию пищевые волокна, превращая их в жирные кислоты с короткой цепью, которые всасываются в толстой кишке, благодаря чему обеспечивается максимальная утилизация энергии из углеводов. Этот процесс,

                    216	ГЛАВА 8

                    известный как “извлечение энергии из толстой кишки”, может давать до 10% усвоенной энергии.

                    К тому времени, когда примерно в возрасте 6 месяцев в рацион ребенка вводится адаптированная пища с семейного стола, пищеварительная система уже достаточно созрела для эффективного переваривания крахмала, белков и жиров, содержащихся в немолочной пище. Тем не менее, вместимость желудка у грудных детей невелика (около 30 мл/кг массы тела). Таким образом, если пища слишком объемна и имеет низкую энергетическую плотность, грудные дети иногда бывают неспособны потребить ее в достаточном количестве, чтобы удовлетворить свои потребности в энергии и пищевых веществах. Поэтому продукты для прикорма должны иметь высокую плотность энергии и микронутриентов, и давать их нужно маленькими количествами и часто.
                </div>
            </div>
        </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading4">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse4" aria-expanded="false" aria-controls="collapseThree">
                        Почечная функция
                    </button>
                </h2>
                <div id="collapse4" class="accordion-collapse collapse" aria-labelledby="heading4" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        Нагрузка растворенных веществ на почки означает суммарное количество растворенных веществ, которое должно быть выведено почками. В основном она включает нетрансформируемые в ходе обмена веществ пищевые компоненты, главным образом, электролиты натрий, хлор, калий и фосфор, которые были поглощены сверх потребностей организма, и конечные продукты обмена веществ, наиболее важными из которых являются азотные соединения, образовавшиеся в результате пищеварения и метаболизма белков.
                        Потенциальная нагрузка растворенных веществ на почки означает растворенные вещества пищевого и эндогенного происхождения, которые необходимо будет вывести с мочой, если они не будут использованы в синтезе новой ткани или выведены непочечными путями. Она определяется как сумма четырех электролитов (натрия, хлора, калия и фосфора) плюс растворенные вещества, полученные в результате белкового обмена, на долю которых обычно приходится свыше 50% потенциальной нагрузки растворенных веществ на почки. В таблице 43 показаны значительные различия в потенциальной нагрузке растворенных веществ на почки, которую дают  различные виды молока и детские питательные смеси.
                        Новорожденный ребенок имеет слишком ограниченную пропускную способность почек, чтобы справляться с высокой нагрузкой растворенных веществ и одновременно сохранять жидкости. Осмолярность материнского молока соответствует возможностям организма ребенка, поэтому беспокойство по поводу чрезмерной нагрузки растворенных веществ на почки касается прежде всего детей, которые не кормятся грудью, особенно детей, которых кормят немодифицированным коровьим молоком. Особенно оправдано это беспокойство в период болезни. Примерно к 4 месяцам почечная функция становится значительно более зрелой, и грудные дети способны лучше сохранять воду и справляться с более высокими концентрациями растворенных веществ. Таким образом, рекомендации в отношении введения прикорма обычно не требуют того, чтобы их изменяли для приведения в соответствие со стадией развития почечной системы.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading5">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse5" aria-expanded="false" aria-controls="collapseThree">
                        Защитная система
                    </button>
                </h2>
                <div id="collapse5" class="accordion-collapse collapse" aria-labelledby="heading5" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        Жизненно важным механизмом защиты является развитие и поддержание эффективного барьера слизистой оболочки в кишечнике. У новорожденного барьер слизистой оболочки незрелый, вследствие чего он не защищен от повреждения энтеропатогенными микроорганизмами и чувствителен к действию некоторых антигенов, содержащихся в пище. Грудное молоко содержит большой набор факторов, которых нет в детских питательных смесях промышленного производства и которые стимулируют развитие активных защитных механизмов и помогают подготовить желудочно-кишечный тракт к поглощению пищи переходного периода. К числу неим- мунологических защитных механизмов, помогающих защитить поверхность кишечника от микроорганизмов, токсинов и антигенов, относятся желудочная кислотность, слизистая оболочка, кишечные секреты и перистальтика.

                        Относительно слабые защитные механизмы пищеваритель- ного тракта грудного ребенка в раннем возрасте, а также пониженная желудочная кислотность увеличивают риск повреждения слизистой оболочки чужеродной пищей и микробиологическими белками, которые могут вызывать прямое токсическое или иммунологически опосредованное повреждение. Некоторые продукты содержат белки, которые являются потенциальными антигенами: это соевый белок, клейковина (присутствующая в некоторых зерновых продуктах), белки в коровьем молоке, яйце и рыбе, которые ассоциируют с энтеропатией. Поэтому представляется разумным избегать введения этих продуктов до наступления 6-месячного возраста, особенно когда в семейном анамнезе фигурирует пищевая аллергия.
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading6">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse6" aria-expanded="false" aria-controls="collapseThree">
                        ДЛЯ ЧЕГО НУЖНА ПИЩА ДЛЯ ПРИКОРМА?
                    </button>
                </h2>
                <div id="collapse6" class="accordion-collapse collapse" aria-labelledby="heading6" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        По мере того, как ребенок растет и становится более активным, для полного удовлетворения его пищевых и физиологических потребностей одного грудного молока недостаточно. Для компенсации разницы между количеством энергии, железа и других незаменимых питательных веществ, которое обеспечивается за счет исключительно грудного вскармливания, и суммарными алиментарными потребностями грудного ребенка нужна адаптированная семейная пища (пища переходного периода). С возрастом эта разница увеличивается и требует все большего вклада другой пищи, помимо грудного молока, в поступление энергии и пищевых веществ, особенно железа. Пища для прикорма также играет важную роль в развитии нервно-мышечной координации.

                        Грудные дети не обладают физиологической зрелостью для того, чтобы перейти от исключительно грудного вскармливания прямо к пище с семейного стола. Поэтому для преодоления этого
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div  class="modal-footer align-text-bottom">
        <p></p>
    </div>
</div>
</body>
</html>