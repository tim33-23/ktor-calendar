<#if role?has_content>
    <#if role=="ЦИК">
        <nav class="py-2 bg-light border-bottom">
            <div class="container d-flex flex-wrap">
                <ul class="nav me-auto">
                    <li class="nav-item"><a href="/" class="nav-link link-dark px-2 active" aria-current="page">Главная</a></li>
                    <li class="nav-item"><a href="/elections" class="nav-link link-dark px-2">Выборы</a></li>
                    <li class="nav-item"><a href="/createElection" class="nav-link link-dark px-2">Добавить календарный план</a></li>
                    <li class="nav-item"><a href="/about" class="nav-link link-dark px-2">О сайте</a></li>
                </ul>
                <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto" role="search">
                    <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                </form>
                <ul class="nav">
                    <li class="nav-item"><a href="/logout" class="nav-link link-dark px-2">Выйти</a></li>
                </ul>
            </div>
        </nav>
        <#else>
            <nav class="py-2 bg-light border-bottom">
                <div class="container d-flex flex-wrap">
                    <ul class="nav me-auto">
                        <li class="nav-item"><a href="/" class="nav-link link-dark px-2 active" aria-current="page">Главная</a></li>
                        <li class="nav-item"><a href="/elections" class="nav-link link-dark px-2">Все выборы</a></li>
                        <li class="nav-item"><a href="/myElections" class="nav-link link-dark px-2">Мой каледнарный план</a></li>
                        <li class="nav-item"><a href="/about" class="nav-link link-dark px-2">О сайте</a></li>
                    </ul>
                    <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto" role="search">
                        <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                    </form>
                    <ul class="nav">
                        <li class="nav-item"><a href="/electionsForRegistration" class="nav-link link-dark px-2">Выйти</a></li>
                    </ul>
                </div>
            </nav>
    </#if>

    <#else>
        <nav class="py-2 bg-light border-bottom">
            <div class="container d-flex flex-wrap">
                <ul class="nav me-auto">
                    <li class="nav-item"><a href="/" class="nav-link link-dark px-2 active" aria-current="page">Главная</a></li>
                    <li class="nav-item"><a href="/elections" class="nav-link link-dark px-2">Выборы</a></li>
                    <li class="nav-item"><a href="/about" class="nav-link link-dark px-2">О сайте</a></li>
                </ul>
                <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto" role="search">
                    <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                </form>
                <ul class="nav">
                    <li class="nav-item"><a href="/login" class="nav-link link-dark px-2">Вход</a></li>
                    <li class="nav-item"><a href="/electionsForRegistration" class="nav-link link-dark px-2">Регистрация</a></li>
                </ul>
            </div>
        </nav>
</#if>



