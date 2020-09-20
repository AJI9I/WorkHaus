<#macro workaddlist>
    <div class="col-md-12 col-sm-12 col-xs-12" xmlns="http://www.w3.org/1999/html">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Форма добавления заказа</h2>

                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">

                        <!-- Smart Wizard -->
                        <p><#if message??>
                                ${message}
                            </#if></p>

                        <form class="form-horizontal form-label-left" method="post" action="/manager/workadd">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Категория заказа</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="CategoryId" class="select2_group form-control">
                                        <#list cat as ca>
                                            <#if ca.lvl == 0>
                                                <optgroup label="${ca.name}">
                                                <#list cat as cc>
                                                    <#if cc.father == ca.id>
                                                        <option value="${cc.id}">${cc.name}</option>
                                                    </#if>
                                                </#list>
                                                </optgroup>
                                           </#if>
                                        </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Наименование заказа
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Name" placeholder="Введите наименование работы">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Дополнительное описание заказ</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <textarea class="form-control col-md-7 col-xs-12" name="FullName" rows="3" placeholder="Подробное описание работы"></textarea>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Стоимость заказа
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Price" placeholder="Стоимость выполнения работы">
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">ФИО Заказчика
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="FIO" placeholder="ФИО заказчика">
                                </div>
                            </div>

<#--                            <div class="form-group">-->
<#--                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Номер телефона-->
<#--                                </label>-->
<#--                                <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Phone" placeholder="Номер телефона заказчика">-->
<#--                                </div>-->
<#--                            </div>-->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3" for="first-name">Телефон</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="tel"  id="first-name" required="required"  name="Phone" class="form-control tel" placeholder="В формате, 9517761999">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>
                            </div>

<#--                            <div class="form-group">-->
<#--                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Район выполнения работы-->
<#--                                </label>-->
<#--                                <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="ROn" placeholder="Район выполнения работы">-->
<#--                                </div>-->
<#--                            </div>-->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Пригород выполнения</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="SuburbsId" class="select2_group form-control">
                                        <option value=""></option>
                                        <#list suburbs as s>
                                            <option value="${s.id}">${s.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Район выполнения</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="AreaId" class="select2_group form-control">
                                        <option value=""></option>
                                        <#list areas as a>
                                            <option value="${a.id}">${a.areasname}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>



                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Улица
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Street" placeholder="Улица">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Дом
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="House" placeholder="Дом">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Квартира
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Apartment" placeholder="Квартира">
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
                    <!-- End SmartWizard Content -->

                </div>
            </div>

    </div>
</#macro>