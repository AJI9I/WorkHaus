<#import "parts/commn.ftlh" as c>
<#import "parts/worcview.ftlh" as v>


<@c.page>
    <#if manager??>

    </#if>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Менеджер </h2>

                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="row">
<#--                    <br />-->

<#--                    <p>Имя: ${manager.firstname}</p>-->
<#--                    <p>Отчество: ${manager.lastname}</p>-->
<#--                    <p>Фамилия: ${manager.familyname}</p>-->
<#--                    <p>Телефон: ${manager.phone}</p>-->
<#--                    <p>Логин: ${user.username}</p>-->
<#--                    <p>Пароль: ${user.key}</p>-->

<#--                    <br />-->

                    <form class="form-horizontal form-label-left" method="post" action="/administration/manageredit">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="mid" value="${manager.id}">
                        <input type="hidden" name="cid" value="${cid}">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Имя</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="firstname" value="${manager.firstname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Фамилия</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="familyname" value="${manager.familyname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Отчество</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="lastname" value="${manager.lastname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Телефон</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="tel" name="phone" class="form-control tel" value="${manager.phone}">
                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                            </div>
                        </div>

<#--                        <#if citys??>-->
<#--                        &lt;#&ndash;                 начало       Вставка города&ndash;&gt;-->
<#--                            <div class="form-group">-->
<#--                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Город</label>-->
<#--                                <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                                    <select class="form-control" name="cid">-->
<#--                                        <option selected value=""></option>-->
<#--                                        <#list citys as c>-->

<#--                                            <option value="${c.id}">${c.name}</option>-->

<#--                                        </#list>-->
<#--                                    </select>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        &lt;#&ndash;                 конец       Вставка города&ndash;&gt;-->
<#--                        </#if>-->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграмЧатId</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="TeleId" value="<#if manager.telegramuser??>${manager.telegramuser.chatid}<#else > Не указан</#if>">
                                <#if manager.telegramuser??> <button type="submit"  form="example" formaction="/administration/telegrammdel" formmethod="post" class="btn btn-danger btn-xs">Отвязать телеграмм ID</button></#if>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                <button type="submit" class="btn btn-success">Обновить</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</@c.page>