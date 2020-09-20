<#macro personview>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Карточка специалиста</h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">

                    <br />
                    <form id="example" class="form-horizontal form-label-left">
<#--                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                        <input type="hidden" name="Phone" class="form-control tel" value="${pers.phone}">

<#--                        <div class="form-group">-->
<#--                            <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграммЧатId</label>-->
<#--                            <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                                <input type="text" name="TeleIdChat" class="form-control" placeholder="В формате, 316464237, либо пустым">-->

<#--                            </div>-->
<#--                        </div>-->
                        <H5>
                        <p>Фамилия: ${pers.family}</p>
                        <p>Имя: ${pers.name}</p>
                        <p>Отчество: ${pers.surname}</p>

                        <p>Телефон: ${pers.phone}</p>
                        <p>ТелеграммId: <#if pers.telegramuser??>${pers.telegramuser.chatid}<#else >нету</#if></p>
                        <p>Баланс: ${cash}</p></H5>

                        <div class="ln_solid"></div>
                    </form>
                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                        <button type="submit"  form="example" formaction="/manager/balanceupdate" formmethod="get" class="btn btn-warning">Управление балансом специалиста</button>
                    </div>
                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                        <button type="submit"  form="example" formaction="/manager/personedit" formmethod="get" class="btn btn-warning">Редактировать профиль специалиста</button>
                    </div>
                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                        <button type="submit"  form="example" formaction="/manager/personworc" formmethod="get" class="btn btn-warning">Заявки специалиста</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>