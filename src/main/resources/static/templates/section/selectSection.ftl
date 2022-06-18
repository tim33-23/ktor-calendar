<div class="form-check">
    <input class="form-check-input" type="checkbox" value="" id="checkOnSelectionSection" name="checkOnSelectionSection">
    <label class="form-check-label" for="checkOnSelectionSection">
        Выбрать раздел
    </label>
</div>
<div class="form-floating" style="margin-top: 20px">
    <div class="themed-grid-col">
        <label>Выберите раздел: </label>
        <select id="idSection" name="idSection" style="margin-left: 50px">
            <#list sections as section>
                <option value="${section.id}">${section.name}</option>
            </#list>
        </select>
    </div>
</div>