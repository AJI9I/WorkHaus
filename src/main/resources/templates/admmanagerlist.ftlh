<#import "parts/commn.ftlh" as c>

<@c.page>
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
                        <th>Город</th>
                        <th>Перейти</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if managers??>
                        <#list managers as m>
                            <tr>
                                <td>${m.firstname} ${m.lastname} ${m.familyname} </td>
                                <td>${m.phone}</td>
                                <td>${m.citys.name}</td>

                                <td>
                                    <form method="get" action="/administration/managerview">
<#--                                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                                        <input type="hidden" value="${m.id}" name="mid" >
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
</@c.page>