<#macro categoryedit>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Редактирование категории</h2>

                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <br />
                <form class="form-horizontal form-label-left" method="post" action="/manager/categoryedit">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="Id" value="${caid.id}">
                    <#if message??>
                        ${message}
                    </#if>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Родительская категория</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <select class="form-control" name="CategoryFather">
                                <option selected value=""></option>
                                <#if caid.father!=0>
                                    <#list cat as ca>
                                        <#if ca.lvl == 0>
                                            <#if ca.id = caid.father>
                                                <option selected value="${ca.id}">${ca.name}</option>
                                            <#else >
                                                <option value="${ca.id}">${ca.name}</option>
                                            </#if>
                                        </#if>
                                    </#list>
                                    <#else >
                                        <#list cat as ca>
                                            <#if ca.lvl == 0>
                                                <#if ca.id != caid.id>
                                        <option value="${ca.id}">${ca.name}</option>
                                                </#if>
                                            </#if>
                                        </#list>
                                </#if>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Наименование категории</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input type="text" class="form-control" name="CategoryName" value="${caid.name}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Процент</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input type="text" class="form-control" name="CategoryPercent" value="${caid.percent}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Описание категории <span class="required">*</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <textarea class="form-control" name="CategoryDescription" rows="3" >${caid.discription}</textarea>
                        </div>
                    </div>
                    <div class="ln_solid"></div>

                    <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                            <button type="submit" class="btn btn-success">Изменить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</#macro>