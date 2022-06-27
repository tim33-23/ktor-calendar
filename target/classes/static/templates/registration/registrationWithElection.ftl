<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<main>
    <div class="container" style="padding-top: 80px">
        <h2 align="center">
            Вы регистрируйтесь на <br>${election.nameElection}
        </h2>
    </div>
    <div class="container" style="padding-top: 40px; max-width: 500px;" >
        <div class="row mb-3 container">
            <form class="row mb-3" method="post" action="/registration">
                <div class="form-floating">
                    <input name="surname" type="text" class="form-control" id="surname" placeholder="Иванов">
                    <label for="surname">Фамилия</label>
                </div>
                <div class="form-floating">
                    <input name="name" type="text" class="form-control" id="name" placeholder="Иван">
                    <label for="name">Имя</label>
                </div>
                <div class="form-floating">
                    <input name="middleName" type="text" class="form-control" id="middleName" placeholder="Иванович">
                    <label for="middleName">Отчество</label>
                </div>
                <div class="form-floating" style="margin-top: 20px">
                    <input required name="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Email</label>
                </div>
                <div class="form-floating" style="margin-top: 20px">
                    <input name="tel" type="tel" class="form-control" id="floatingInput" pattern="[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}" placeholder="8-999-999-99-99">
                    <label for="floatingInput">Номер телефона в формате: 8-999-999-99-99</label>
                </div>
                <div class="form-floating" style="margin-top: 20px">
                    <div class="themed-grid-col">
                        <label>Выберите роль: </label>
                        <select id="role" name="role" style="margin-left: 50px">
                            <#list roles as role>
                                <#if role.nameRole != "ТИК" && role.nameRole != "ЦИК">
                                    <option value="${role.id}">${role.nameRole}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-floating" style="margin-top: 20px">
                    <input required name="password" type="password" class="form-control" id="Password" placeholder="Password">
                    <label for="floatingPassword">Пароль</label>
                </div>
                <div class="form-floating"  style="display: none">
                    <input name="election" type="text" class="form-control" id="election" placeholder="Password" value="${election.id}">
                </div>
                <div class="form-floating">
                    <input required name="passwordConf" type="password" class="form-control" id="confirmation" placeholder="confirmation">
                    <label for="confirmation">Подтверждение пароля</label>
                </div>
                <div class="form-floating">
                    <button class="w-100 btn btn-lg btn-primary" type="submit">
                        <div class="col-md themed-grid-col">
                            <h4>Подтвердить</h4>
                        </div>
                    </button>
                </div>
            </form>
        </div>
    </div>
</main>
</body>
</html>