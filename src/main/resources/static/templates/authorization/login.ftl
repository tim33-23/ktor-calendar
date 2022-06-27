<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<div align="center" style="padding-top: 250px">
    <main class="form-signin w-100" >
        <form style="max-width: 25%;" action="/login" method="Post">
            <img class="mb-4" src="/static/flag.jpg" alt="" width="72" height="57">
            <h1 class="h3 mb-3 fw-normal">Вход</h1>

            <div class="form-floating">
                <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
                <label for="email">email</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                <label for="password">password</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Войти</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
        </form>
    </main>
</div>



</body>
</html>
