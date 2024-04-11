<!-- Форма для додавання предметів до кафедри -->
<h2>Додати предмети до кафедри</h2>
<form action="/departments/${department.id}" method="post">
    <select name="subjectIds" multiple>
        <#list allSubjects as subject>
            <option value="${subject.id}">${subject.name}</option>
        </#list>
    </select>
    <button type="submit">Додати вибрані предмети</button>
</form>

<!-- Відображення доданих предметів -->
<h2>Предмети кафедри</h2>
<#if department.subjects??>
    <ul>
        <#list department.subjects as subject>
            <li>${subject.name} - ${subject.description}</li>
        </#list>
    </ul>
<#else>
    <p>Наразі до кафедри не додано жодних предметів.</p>
</#if>

<div>
    <a href="/departments">Повернутись до списку кафедр</a>
</div>
