<!doctype html>
<html lang="en">
<head>
    <#include "../templates/head-config.ftl"/>
</head>
<body>
<#include "../templates/header.ftl"/>
<div align="center" style="padding-top: 250px">
    <main class="form-signin w-100" >
        <form style="max-width: 25%;" action="/login" method="post">
            <img class="mb-4" src="/static/flag.jpg" alt="" width="72" height="57">
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Email address</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="Password" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Войти</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
        </form>
    </main>

</div>



</body>
</html>
