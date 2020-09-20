<#macro personcategorylistadd>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Категории работ исполнителя</h2>

                <div class="clearfix"></div>
            </div>
            <div class="x_content bs-example-popovers">
                <form class="form-horizontal form-label-left" method="post" action="/manager/addcategoryperson">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="PersonId" value="${pps.id}">
                    <input type="hidden" class="form-control tel" name="Phone"  value="${pps.phone}">
                <#list ccat as ca>
                    <#if ca.lvl == 0>
                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <strong>${ca.name}</strong>
                                <#list ccat as cc>
                                    <#if cc.father == ca.id>
                                <div class="checkbox">
                                                <label>
<#--                                                    <input type="hidden" value="${pps.id}" name="PersonId" >-->
<#--                                                    <input type="hidden" value="${pps.phone}" name="Phone" >-->
<#--                                                    <input type="hidden" value="${cc.id}" name="CategoryId" >-->
                                                    <input type="checkbox" name="${cc.name}" value="${cc.id}" ${pi?seq_contains(cc.name)?string("checked","")}> ${cc.name}</label>
                                            </div>

                                    </#if>
                                </#list>
                        </div>
                    </div>
                    </#if>
                </#list>


                    <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-10">
                            <button type="submit" class="btn btn-success">Добавить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</#macro>