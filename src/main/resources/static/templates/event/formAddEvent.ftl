
    <h1 class="h3 mb-3 fw-normal">Добавление мероприятия</h1>
    <div class="mt-5" style="border: black solid 1px">
        <div class="container mb-3">
            <#include "../section/createSection.ftl"/>
        </div>
        <#if sections?has_content>
            <div class="container mb-3">
                <#include "../section/selectSection.ftl"/>
            </div>
        </#if>
    </div>
    <div class="mt-5" style="border: black solid 1px">
        <div class="container mb-3">
            <h1 class="h3 mb-3 fw-normal">Добавить закон регламентирующий мероприятие</h1>
            <#include "../law/createLaw.ftl"/>
        </div>
        <#if laws?has_content>
            <div class="container mb-3">
                <#include "../law/selectLaw.ftl"/>
            </div>
        </#if>
    </div>
    <div class="mt-4" style="border: black solid 1px">
        <div class="container mb-3 mt-1">
            <#include "../role/createRole.ftl"/>
        </div>

        <#if roles?has_content>
            <div class="container mb-3">
                <#include "../role/selectRole.ftl"/>
            </div>
        </#if>
    </div>
    <div class="mt-5" >
        <textarea type="text" class="form-control" id="descriptionEvent" name="descriptionEvent" placeholder="Описание мероприятия" rows="4"></textarea>
    </div>
    <div class="form-floating">
        <input type="date" class="form-control" id="dateBeginEvent" name="dateBeginEvent" placeholder="Дата начала мероприятия">
        <label for="dateBeginEvent">Дата начала мироприятия</label>
    </div>
    <div class="form-floating">
        <input required type="number" class="form-control" id="durationEvent" name="durationEvent" placeholder="Длительность в днях">
        <label for="durationEvent">Длительность в днях</label>
    </div>
    <div class="mt-2">
        <#if events?has_content>
            <#include "../event/addPreviewForEvent.ftl"/>
        </#if>
    </div>
    <div  class="form-floating" style="display: none">
        <input type="text" class="form-control" id="idElection" name="idElection" placeholder="" value="${election.id}">
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2023</p>