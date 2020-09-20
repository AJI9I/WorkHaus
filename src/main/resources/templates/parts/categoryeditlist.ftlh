<#macro categoryeditlist>
<#--    <div class="row">-->
<#--     <div class="col-md-12 col-sm-12 col-xs-12">-->
<#--                <div class="x_panel">-->
<#--                    <div class="x_title">-->
<#--                        <h2>Имеющиеся категории работ</h2>-->
<#--                       <div class="clearfix"></div>-->
<#--                    </div>-->
<#--                    <div class="x_content">-->
<#--                        <p class="text-muted font-13 m-b-30">-->
<#--                            Здесь вы можете посмотреть имеющиеся категории выполняемых работ а так же внести корректировки в добавленные Вами категории работ-->
<#--                        </p>-->
<#--                        <table id="datatable" class="table table-striped table-bordered">-->
<#--                            <thead>-->
<#--                            <tr>-->
<#--                                <th>Наименование</th>-->
<#--&lt;#&ndash;                                <th>Уровень</th>&ndash;&gt;-->
<#--                                <th>Описание категории</th>-->
<#--                                <th>Процент</th>-->
<#--                                <th></th>-->
<#--                            </tr>-->
<#--                            </thead>-->


<#--                            <tbody>-->
<#--                            <#list cat as ca>-->
<#--                                    <#if ca.lvl == 0>-->
<#--                                        <strong><tr>-->
<#--                                        <td>${ca.name}</td>-->
<#--&lt;#&ndash;                                        <td>${ca.lvl}</td>&ndash;&gt;-->
<#--&lt;#&ndash;                                        <td>${ca.discription}</td>&ndash;&gt;-->
<#--&lt;#&ndash;                                        <td>${ca.percent}</td>&ndash;&gt;-->
<#--&lt;#&ndash;                                        <td></td>&ndash;&gt;-->
<#--                                </tr></strong>-->
<#--                                        <#list cat as cc>-->
<#--                                            <#if cc.father == ca.id>-->
<#--                                <tr>-->
<#--                                                <td>${cc.name}</td>-->
<#--&lt;#&ndash;                                                <td>${cc.lvl}</td>&ndash;&gt;-->
<#--                                                <td>${cc.discription}</td>-->
<#--                                                <td>${cc.percent}</td>-->
<#--                                <td>-->
<#--                                                <form method="get" action="/manager/categoryedit">-->
<#--                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--                                                    <input type="hidden" value="${cc.id}" name="CategoryId" >-->
<#--                                                    <button type="submit" class="btn btn-warning">Ред</button>-->
<#--                                                </form>-->
<#--                                </td>-->
<#--                                </tr>-->
<#--                                            </#if>-->
<#--                                        </#list>-->
<#--                                    </#if>-->
<#--                            </#list>-->

<#--                            </tbody>-->
<#--                        </table>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--    </div>-->

<#--    //////////////////////////////////////////////////////////////////-->
    <div class="col-md-12 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Категории работ<small>добавленные в систему</small></h2>
                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">

                <table class="table">
                    <thead>
                    <tr>

                        <th>Наименование</th>
                        <th>Описание</th>
                        <th>Процент</th>
                        <th>Перейти</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#list cat as ca>
                        <#if ca.lvl == 0>
                    <tr>
                        <th> <strong><b>${ca.name}</b></strong></th>
                        <th> <i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i></th>
                        <th> <i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-down"></i></i></th>
                        <th>
                            <form method="get" action="/manager/categoryedit">
                                <#--                                                <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                                <input type="hidden" value="${ca.id}" name="CategoryId" >
                                <button type="submit" class="btn btn-warning">Ред</button>
                            </form>
                        </th>

                    </tr>
                            <#list cat as cc>
                                <#if cc.father == ca.id>
                                    <tr>
                                        <th>${cc.name}</th>
                                        <th>${cc.discription}</th>
                                        <th>${cc.percent}</th>
                                        <th>
                                            <form method="get" action="/manager/categoryedit">
                                                <#--                                                <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                                                <input type="hidden" value="${cc.id}" name="CategoryId" >
                                                <button type="submit" class="btn btn-warning">Ред</button>
                                            </form>
                                        </th>
                                    </tr>
                                </#if>
                            </#list>
                        </#if>
                    </#list>
<#--                    <#list worcsphone as w>-->
<#--                        <tr>-->
<#--                            <td>${w.id} </td>-->
<#--                            <td>${w.fio}</td>-->
<#--                            <td>${w.phone}</td>-->
<#--                            <td>${w.name}</td>-->
<#--                            <td>${w.price}</td>-->
<#--                            <td><#if w.status==true>В работе<#else >Закрыта</#if></td>-->
<#--                            <td><#if w.localdate??>${w.localdate}<#else > Нет</#if></td>-->
<#--                            <td>-->
<#--                                <form method="post" action="/manager/workfind">-->
<#--                                    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--                                    <input type="hidden" value="${w.id}" name="wid" >-->
<#--                                    <button type="submit" class="btn btn-warning">Вью</button>-->
<#--                                </form>-->
<#--                            </td>-->
<#--                        </tr>-->
<#--                    </#list>-->
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</#macro>