<div class="mb-2">
    <textarea type="text" class="form-control" id="createRole" name="createRole" placeholder="Добавить роль" rows="4"></textarea>
</div>
<#if roles?has_content>
    <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="checkOnSelectionRole" name="checkOnSelectionRole">
        <label class="form-check-label" for="checkOnSelectionRole">
            Выбрать роль
        </label>
    </div>
    <div class="form-floating" style="margin-top: 20px">
        <div class="themed-grid-col">
            <label>Выберите роль: </label>
            <select id="idRole" name="idRole" style="margin-left: 50px">
                <#list roles as role>
                    <option value="${role.id}">${role.name}</option>
                </#list>
            </select>
        </div>
    </div>
</#if>