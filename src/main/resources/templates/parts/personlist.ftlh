<#macro personlist>
    <div class="col-md-12 col-sm-6 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
                <h2>Специалисты имеющиеся в системе</h2>
            <div class="clearfix"></div>
        </div>
            <#if message??>
                ${message}
            </#if>
        <div class="x_content">

            <table class="table">
                <thead>
                <tr>

                    <th>ФИО</th>
                    <th>Телефон</th>
                    <th>Tel Id</th>
                    <th>Категорий</th>
                    <th>Перейти</th>
                </tr>
                </thead>
                <tbody>
                <#if persons??>
                    <#list persons as pp>
                        <tr>
                            <td>${pp.family} ${pp.name} ${pp.surname} </td>
                            <td>${pp.phone}</td>
                            <td><#if pp.telegramuser??>${pp.telegramuser.chatid}<#else >нету</#if></td>

                            <td></td>
                            <td>
                                <form method="post" action="/manager/personview">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="hidden" value="${pp.id}" name="pid" >
                                    <button type="submit" class="btn btn-warning">Вью</button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                </#if>

                </tbody>
            </table>

        </div>
    </div>
    </div>
</#macro>