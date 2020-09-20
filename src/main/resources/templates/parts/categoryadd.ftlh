<#macro categoryadd>
    <div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Добавить категорию</h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">
                    <br />
                    <form class="form-horizontal form-label-left" method="post" action="/manager/categoryadd">


                        <#--                                              _csrf.token-->

<input type="hidden" name="_csrf" value="${_csrf.token}">


                        <#--                                              _csrf.token-->

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Родительская категория</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="CategoryFather">
                                    <option selected value=""></option>
                                    <#list cat as ca>
                                        <#if ca.lvl == 0>
                                            <option value="${ca.id}">${ca.name}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Наименование категории</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="CategoryName" placeholder="Наименование">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Процент</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" class="form-control" name="CategoryPercent" placeholder="Процент с работ по умолчанию" value="20">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Описание категории <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <textarea class="form-control" name="CategoryDescription" rows="3" placeholder="Сюда можно добавить более подробное описание категории"></textarea>
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
    </div>
</#macro>