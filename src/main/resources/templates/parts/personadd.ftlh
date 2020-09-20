<#macro personaddlist>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Добавить специалиста</h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">
                    <br />
                    <form class="form-horizontal form-label-left" method="post" action="/manager/personadd">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
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
                                <input type="tel" name="Phone" class="form-control tel" placeholder="В формате, 9517761999">
                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграммЧатId</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" name="TeleIdChat" class="form-control" placeholder="В формате, 316464237, либо пустым">

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
</#macro>