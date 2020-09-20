<#macro personfind>
    <div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
    <div class="x_title">
        <h2>Поиск специалиста в базе</h2>

        <div class="clearfix"></div>
    </div>
        <#if message??>
            ${message}
        </#if>
    <div class="x_content">
        <div class="row">
            <br />
            <form class="form-horizontal form-label-left" method="post" action="/manager/personview">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Телефон</label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" name="Phone" placeholder="Введите номер" class="form-control tel">
                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                    </div>
                </div>
                <div class="ln_solid"></div>

                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-success">Поиск</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>
    </div>
    </div>
</#macro>