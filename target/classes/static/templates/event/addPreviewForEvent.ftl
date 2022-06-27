<div class="form-check-label mt-2">
    <input class="form-check-input" role="switch" type="checkbox" value="" id="checkOnPreviewEvent" name="checkOnPreviewEvent">
    <label class="form-check-label" for="checkOnPreviewEvent">
        Связать с предыдущим мероприятием
    </label>
</div>
<div class="form-floating mt-2 mb-3">
    <div class="themed-grid-col">
        <select id="idEvent" name="idEvent" style="max-width: 100%">
            <#list events as event>
                <option value="${event.id}">${event.description}</option>
            </#list>
        </select>
    </div>
</div>