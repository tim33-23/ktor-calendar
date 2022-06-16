<!doctype html>
<html lang="en">
<#include "templates/head-config.ftl"/>
<body>
<#include "templates/header.ftl"/>
<main>
    <#if role?has_content && role=="ЦИК">
        <#include "templates/bodyForCEC.ftl"/>
        <#elseif role?has_content && role!="ЦИК">
            <#include "templates/generalBody.ftl"/>
        <#else>
        <div class="container" align="center">
            <h1>
                Добро пожаловать!
            </h1>
        </div>
    </#if>
</main>
</body>
</html>