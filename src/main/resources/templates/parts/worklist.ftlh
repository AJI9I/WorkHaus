<#macro worclist>
    <#if worcsphone??>
    <div class="col-md-12 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
<#--                <small>найденные по номеру телефона</small>-->
                <h2>Заявки имеющиеся в системе</h2>
                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">

                <table class="table">
                    <thead>
                    <tr>

                        <th>Ид</th>
                        <th>ФИО</th>
                        <th>Телефон</th>
                        <th>Наименование</th>
                        <th>Цена</th>
                        <th>Статус</th>
                        <th>Дата</th>
                        <th>Перейти</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list worcsphone as w>
                        <tr>
                            <td>${w.id} </td>
                            <td>${w.fio}</td>
                            <td>${w.phone}</td>
                            <td>${w.name}</td>
                            <td>${w.price}</td>
                            <td><#if w.statusname??>${w.statusname}<#else >не известно</#if></td>
                            <td><#if w.localdate??>${w.localdate}<#else > Нет</#if></td>
                            <td>
                                <form method="post" action="/manager/workfind">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="hidden" value="${w.id}" name="wid" >
                                    <button type="submit" class="btn btn-warning">Вью</button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    </#if>
</#macro>

<#macro worclistworc>
    <div class="col-md-12 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Заявки имеющиеся в системе<small>найденные по номеру телефона</small></h2>
                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">

                <table class="table">
                    <thead>
                    <tr>

                        <th>Ид</th>
                        <th>ФИО</th>
                        <th>Телефон</th>
                        <th>Наименование</th>
                        <th>Цена</th>
                        <th>Статус</th>
                        <th>Дата</th>
                        <th>Перейти</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list worcs as w>
                        <tr>
                            <td>${w.id} </td>
                            <td>${w.fio}</td>
                            <td>${w.phone}</td>
                            <td>${w.name}</td>
                            <td>${w.price}</td>
                            <td><#if w.statusname??>${w.statusname}<#else >Не известно</#if></td>
                            <td><#if w.localdate??>${w.localdate}<#else > Нет</#if></td>
                            <td>
                                <form method="post" action="/manager/workfind">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="hidden" value="${w.id}" name="wid" >
                                    <button type="submit" class="btn btn-warning">Вью</button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</#macro>