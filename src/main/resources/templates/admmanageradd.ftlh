<#import "parts/commn.ftlh" as c>
<#import "parts/categoryadd.ftlh" as v>
<#import "parts/categoryeditlist.ftlh" as e>
<@c.page>

<#--    ////Начало/////// Манажер адд/////////////-->
    <#if !manager??>
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
                    <form class="form-horizontal form-label-left" method="post" action="/administration/mmanageradd">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Имя</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="firstname" placeholder="Введите имя Пример 'Андрей'">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Фамилия</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="familyname" placeholder="Введите фамилию Пример 'Ваткулин'">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Отчество</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="lastname" placeholder="Введите отчество Пример 'Исаакович'">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Телефон</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="tel" name="phone" class="form-control tel" placeholder="В формате, 9517761999">
                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                            </div>
                        </div>

                        <#if citys??>
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
                            <#else >
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>
                                    ПЕРЕД ДОБАВЛЕНИЕМ МЕНЕДЖЕРА ДОБАВЬТЕ СООТВЕТСТВУЮЩИЙ ГОРОД
                                </div>
                        </#if>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Логин</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" name="login" class="form-control" placeholder="логин">
                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Пароль</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text"  name="password" class="form-control" placeholder="пароль">
                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                            </div>
                        </div>


<#--                        <div class="form-group">-->
<#--                            <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграммЧатId</label>-->
<#--                            <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                                <input type="tel" pattern="[0-9]{10,11}" name="TeleIdChat" class="form-control" placeholder="В формате, 00364736274, либо пустым">-->

<#--                            </div>-->
<#--                        </div>-->


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
                        <form class="form-horizontal form-label-left" method="post" action="/administration/addcity">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
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
    <#--   -----------------Начало---------------Сетка Города-->
        <#if citys??>
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Города <small>забитые в систему</small> </h2>

                        <div class="clearfix"></div>
                    </div>
                    <#if message??>
                        ${message}
                    </#if>
                    <div class="x_content">
                        <section id="spinner">

                            <div class="row fontawesome-icon-list">

                                <#list citys as с>
                                    <div class="fa-hover col-md-3 col-sm-4 col-xs-12"><a href="#/angle-double-down"><i class="fa fa-angle-double-right"></i> ${с.name}</a>
                                    </div>
                                </#list>

                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </#if>
    <#--   -----------------Конец--------------- Сетка Города-->


                <#else >
                    <div class="col-md-6 col-xs-12">
                    <div class="x_panel">
                    <div class="x_title">
                        <h2>Вами добавлен менеджер </h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="row">
                            <br />

                            <p>Имя: ${manager.firstname}</p>
                            <p>Отчество: ${manager.lastname}</p>
                            <p>Фамилия: ${manager.familyname}</p>
                            <p>Телефон: ${manager.phone}</p>
                            <p>Логин: ${user.username}</p>
                            <p>Пароль: ${user.key}</p>

                            <br />
                        </div>
                    </div>
                    </div>
                    </div>
            </#if>
<#--    /////Конец////// Манажер адд/////////////-->
</@c.page>