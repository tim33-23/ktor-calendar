<div class="container" align="center">
    <h1>
       <#if name?has_content>${name}. </#if>Добро пожаловать!
    </h1>
    <#if doc?has_content && doc!="null">
        Документы для вас находятся по адресу: <a href="${doc}">тут</a>
    </#if>
</div>