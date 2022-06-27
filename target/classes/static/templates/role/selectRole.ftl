
<div class="row container" align="center">
    <div class="form-check form-switch col-6">
        <label class="form-check-label" for="checkOnSelectionRole">
            Выбрать исполнителя
        </label>
        <input class="form-check-input" role="switch" type="checkbox" id="checkOnSelectionRole" name="checkOnSelectionRole">
    </div>
    <div class="col-6 container">
        <div class="form-floating">
            <div class="themed-grid-col">
                <select id="idRole" name="idRole" style="max-width: 100%">
                    <#list roles as role>
                        <option value="${role.id}">${role.nameRole}</option>
                    </#list>
                </select>
            </div>
        </div>
    </div>
</div>

