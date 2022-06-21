
<div class="row container mt-2" align="center">
    <div class="col-10 container">
        <label class="form-check">
            <b>Выбрать существующий раздел</b>
            <input style="max-width: 100%" class="form-check" type="checkbox" id="checkOnSelectionSection" name="checkOnSelectionSection">
        </label>
    </div>
    <div class="col-2">

    </div>

</div>
<div class="form-floating row" style="margin-top: 20px">
    <div class="themed-grid-col">
        <label>Выберите раздел: </label>
        <select id="idSection" name="idSection" style="max-width: 100%">
            <#assign newSection = "" />
            <#list sections as section>
                <#if newSection!=section>
                    <#assign newSection = section.nameSection/>
                    <option value="${section.id}">${section.nameSection}</option>
                </#if>
            </#list>
        </select>
    </div>
</div>