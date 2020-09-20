<#import "parts/commn.ftlh" as c>
<#--<#import "parts/personadd.ftlh" as v>-->
<@c.page>
<#--   -----------------Начало-------------- Добавление района-->
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Добавить район города </h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">
                    <br />
                    <form class="form-horizontal form-label-left" method="post" action="/manager/addarea">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <#--                 начало       Вставка города-->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="CityName">
                                    <option selected value="${citys.id}">${citys.name}</option>
<#--                                    <#list citys as c>-->

<#--                                        <option value="${c.id}">${c.name}</option>-->

<#--                                    </#list>-->
                                </select>
                            </div>
                        </div>
                        <#--                 конец       Вставка города-->

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Район города</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="AreaName" placeholder="Название района">
                            </div>
                        </div>
                        <div class="ln_solid"></div>

                        <div class="form-group">
                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                <button type="submit" class="btn btn-success">Добавить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<#--   -----------------Конец--------------- Добавление района-->

<#--   -----------------Начало---------------Сетка районн-->
    <#if areas??>
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Районы города<small>существующие в системе, по данному городу</small> </h2>

                    <div class="clearfix"></div>
                </div>
                <#if message??>
                    ${message}
                </#if>
                <div class="x_content">
                    <section id="spinner">

                        <div class="row fontawesome-icon-list">

                            <#list areas as a>
                                <div class="fa-hover col-md-3 col-sm-4 col-xs-12"><a href="#/angle-double-down"><i class="fa fa-angle-double-right"></i> ${a.areasname}</a>
                                </div>
                            </#list>

                        </div>
                    </section>
                </div>
            </div>
        </div>
    </#if>
<#--   -----------------Конец--------------- Сетка район-->

<#--   -----------------Конец--------------- Добавление Пригорода-->
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Добавить пригородскую территорию города </h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">
                    <br />
                    <form class="form-horizontal form-label-left" method="post" action="/manager/addsuburbs">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <#--                 начало       Вставка города-->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="CityName">
                                    <option selected value="${citys.id}">${citys.name}</option>
<#--                                    <#list citys as c>-->

<#--                                        <option value="${c.id}">${c.name}</option>-->

<#--                                    </#list>-->
                                </select>
                            </div>
                        </div>
                        <#--                 конец       Вставка города-->

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Пригород города</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="SuburbsName" placeholder="Название пригорода">
                            </div>
                        </div>
                        <div class="ln_solid"></div>

                        <div class="form-group">
                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                <button type="submit" class="btn btn-success">Добавить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<#--   -----------------Конец--------------- Добавление Пригорода-->
<#--   -----------------Начало---------------Сетка пригород-->
    <#if suburbs??>
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Пригородсткие территории<small>существующие в системе, по данному городу</small> </h2>

                    <div class="clearfix"></div>
                </div>
                <#if message??>
                    ${message}
                </#if>
                <div class="x_content">
                    <section id="spinner">

                        <div class="row fontawesome-icon-list">

                            <#list suburbs as s>
                                <div class="fa-hover col-md-3 col-sm-4 col-xs-12"><a><i class="fa fa-angle-double-right"></i> ${s.name}</a>
                                </div>
                            </#list>


                        </div>
                    </section>
                </div>
            </div>
        </div>
    </#if>
<#--   -----------------Конец--------------- Сетка пригород-->


</@c.page>