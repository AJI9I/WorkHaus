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
                    <form id="example" class="form-horizontal form-label-left">
                        <#--                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--                        <input type="hidden" name="Phone" class="form-control tel" value="${pers.phone}">-->
                        <input type="hidden" name="mid" class="form-control tel" value="${manager.id}">

                    <p>Имя: ${manager.firstname}</p>
                    <p>Отчество: ${manager.lastname}</p>
                    <p>Фамилия: ${manager.familyname}</p>
                    <p>Телефон: ${manager.phone}</p>
                    <p>Логин: ${user.username}</p>
                    <p>Пароль: ${user.key}</p>

                    </form>
                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                        <button type="submit"  form="example" formaction="/administration/manageredit" formmethod="get" class="btn btn-warning">Редактировать профиль менеджера</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>