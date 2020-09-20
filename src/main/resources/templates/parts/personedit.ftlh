
<#macro personedit>
    <div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Отредактировать <small>информацию о специалисте</small> </h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
                <div class="x_content">
                    <div class="row">
                        <br />
                        <form id="example" class="form-horizontal form-label-left" method="post" action="/manager/personedit">
                            <input type="hidden" name="PersonId" value="${pps.id}"  >
                            <input type="hidden" name="pid" value="${pps.id}"  >
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Фамилия</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" class="form-control" name="Family" value="${pps.family}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Имя</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" class="form-control" name="Name" value="${pps.name}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Отчество</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" class="form-control" name="Surname" value="${pps.surname}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Телефон</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" name="Phone" value="${pps.phone}" class="form-control tel">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграмЧатId</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" class="form-control" name="TeleId" value="<#if pps.telegramuser??>${pps.telegramuser.chatid}<#else > Не указан</#if>">
                                    <#if pps.telegramuser??> <button type="submit"  form="example" formaction="/manager/telegrammdel" formmethod="post" class="btn btn-danger btn-xs">Отвязать телеграмм ID</button></#if>
                                </div>
                            </div>

                            <div class="ln_solid"></div>

                            <div class="form-group">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-8">
                                    <button type="submit" class="btn btn-success">Обновить информацию</button>
                                </div>
                            </div>
                        </form>
                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                            <button type="submit"  form="example" formaction="/manager/personview" formmethod="post" class="btn btn-warning">Карточка специалиста</button>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    </div>
</#macro>