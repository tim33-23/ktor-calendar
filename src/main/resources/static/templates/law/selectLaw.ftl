<div class="row container mt-3 mb-1" >
        <div class="form-check form-switch col-7" align="center">
            <label class="form-check-label" for="checkOnSelectionLaw">
                <b>Выбрать закон</b>
            </label>
            <input class="form-check-input" type="checkbox" role="switch" align="left" value="" id="checkOnSelectionLaw" name="checkOnSelectionLaw">
        </div>
        <div class="col-5">
            <div class="themed-grid-col">
                <select id="idLaw" name="idLaw" style="max-width: 100%">
                    <#list laws as law>
                        <option value="${law.id}">
                            ст ${law.article}
                            <#if law.paragraph?has_content> п ${law.paragraph}</#if>
                            <#if law.part?has_content> ч ${law.part}</#if>
                            <#if law.scopeLegislation?has_content> ${law.scopeLegislation}</#if>
                        </option>
                    </#list>
                </select>
            </div>
        </div>
</div>



