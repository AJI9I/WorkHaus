<#import "parts/commn.ftlh" as c>
<#import "parts/personadd.ftlh" as v>

<@c.page>

    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Добавить менеджера</h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">
                    <br />
                    <form class="form-horizontal form-label-left" method="post">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Фамилия</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="Family" placeholder="Введите фамилию">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Имя</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="Name" placeholder="Введите имя">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Отчество</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="Surname" placeholder="Введите отчество">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Телефон</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="Phone" class="form-control tel" placeholder="В формате, 9517761999">
                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
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

<#--   -----------------Начало--------------- Добавление города-->

    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Добавить город </h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">
                    <br />
                    <form class="form-horizontal form-label-left" method="post" action="/addcity">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="CityName" placeholder="Название города">
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

<#--   -----------------Конец--------------- Добавление города-->

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
                    <form class="form-horizontal form-label-left" method="post" action="/addarea">

                        <#--                 начало       Вставка города-->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="CityName">
                                    <option selected value=""></option>
                                    <#list citys as c>

                                            <option value="${c.id}">${c.name}</option>

                                    </#list>
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
                    <form class="form-horizontal form-label-left" method="post" action="/addsuburbs">

                        <#--                 начало       Вставка города-->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="CityName">
                                    <option selected value=""></option>
                                    <#list citys as c>

                                        <option value="${c.id}">${c.name}</option>

                                    </#list>
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


</@c.page>