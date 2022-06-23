<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<#assign section=""/>
<main class="container" style="max-width: 600px">
    <div class="row" style="margin-top: 10px;">
        <#if role?has_content && role=="ЦИК">
            <div class="row mt-5">
                <div class="col-12" align="center">
                    <form action="/addDocuments" method="post">
                        <div class="row container">
                            <#if message?has_content><h1>${message}</h1>
                            <#else>
                            <h1>Выберите роль</h1>
                            </#if>
                            <div class="form-floating">
                                <div class="themed-grid-col">
                                    <select id="idRole" name="idRole" style="max-width: 100%">
                                        <#list roles as rol>
                                            <option value="${rol.id}">${rol.nameRole}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-floating mt-2">
                            <input required type="text" class="form-control" id="documents" name="documents" placeholder="Ссылка на документы">
                            <label for="documents">Ссылка на документы</label>
                        </div>
                        <div  class="form-floating mt-2" style="display: none">
                            <input type="text" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
                        </div>
                        <div class="row mt-2">
                            <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </#if>
    </div>
</main>
</body>
</html>