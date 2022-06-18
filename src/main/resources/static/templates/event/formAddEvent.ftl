<form style="max-width: 25%; margin-bottom: 150px" action="/addEvent" method="Post">
    <h1 class="h3 mb-3 fw-normal">Добавление мероприятия</h1>
    <div class="mb-2">
        <textarea type="text" class="form-control" id="descriptionEvent" name="descriptionEvent" placeholder="Описание мероприятия" rows="4"></textarea>
    </div>
    <div>
        <div class="mb-2">
            <textarea type="text" class="form-control" id="createSelection" name="createSelection" placeholder="Создать раздел" rows="4"></textarea>
        </div>
        <#if sections?has_content>
            <#include "../section/selectSection.ftl"/>
        </#if>
    </div>

    <div class="form-floating">
        <input type="date" class="form-control" id="dateBeginEvent" name="dateBeginEvent" placeholder="Дата начала мероприятия">
        <label for="dateBeginEvent">Дата начала мироприятия</label>
    </div>
    <div class="form-floating">
        <input type="number" class="form-control" id="durationEvent" name="durationEvent" placeholder="Длительность в днях">
        <label for="durationEvent">Длительность в днях</label>
    </div>

    <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
</form>