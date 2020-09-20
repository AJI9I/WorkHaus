<#macro worcview>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Карточка заявки</h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <div class="row">

                    <br />
                    <form id="example" class="form-horizontal form-label-left">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="wid" value="${worcs.id}">


                        <#--                        <div class="form-group">-->
                        <#--                            <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграммЧатId</label>-->
                        <#--                            <div class="col-md-6 col-sm-6 col-xs-12">-->
                        <#--                                <input type="text" name="TeleIdChat" class="form-control" placeholder="В формате, 316464237, либо пустым">-->

                        <#--                            </div>-->
                        <#--                        </div>-->
                        <H5>

                            <p>Стутус: ${worcs.statusname}</p>
                            <br>
                            <p>Категория: ${worcs.categorys.name}</p>
                            <p>Наименование заказа: ${worcs.name}</p>
                            <p>Дополнительное описание: ${worcs.fullname}</p>

                            <br>
                            <p>Стоимость: ${worcs.price}</p>
                            <br>
                            <H5>Контактные данные:</H5>
                            <p>ФИО заказчика: ${worcs.fio}</p>
                            <p>Телефон: ${worcs.phone}</p>
                            <br>
                            <H5>Аддрес:</H5>
                            <p>Пригород: <#if worcs.suburbs??>${worcs.suburbs.name}<#else >Не указанно</#if></p>
                            <p>Район: <#if worcs.areas??>${worcs.areas.areasname}<#else >Не указанно</#if></p>
                            <p>Улица: ${worcs.street}</p>
                            <p>Дом: ${worcs.house}</p>
                            <p>Квартира: ${worcs.apartment}</p>

                        </H5>

                        <div class="ln_solid"></div>
                    </form>
                    <#if worcs.status != false>
                        <div class="col-md-2 col-sm-12 col-xs-12 form-group">

                            <button type="submit"  form="example" formaction="/manager/worcstop" formmethod="post" class="btn btn-danger">Отменить Заявку</button>
                        <#if worcs.persons??>
                            </div>
                        <div class="col-md-2 col-sm-12 col-xs-15 form-group">

                            <button type="submit"  form="example" formaction="/manager/worcstotsellby" formmethod="post" class="btn btn-info">Вернуть средства </button>
                        </div>
                            <#if worcs.confirmation == false>
                                <div class="col-md-2 col-sm-12 col-xs-15 form-group">

                                    <button type="submit"  form="example" formaction="/manager/worcsconformation" formmethod="post" class="btn btn-dark">Подтвердить заявку</button>
                                </div>
                            </#if>
                        </#if>
                    <#else >
                        <div class="col-md-2 col-sm-12 col-xs-12 form-group">

                            <button type="submit"  form="example" formaction="/manager/worcstart" formmethod="post" class="btn btn-success">Запустить заявку</button>
                        </div>
<#--                        <div class="col-md-3 col-sm-12 col-xs-12 form-group">-->

<#--                            <button type="submit"  form="example" formaction="/manager/personfind" formmethod="get" class="btn btn-dark">Перезапустить с возвратом</button>-->
<#--                        </div>-->
                    </#if>

                </div>
            </div>
        </div>
    </div>

<#--   ------------Начало-------------- Блок исполнителя---------------->
    <#if person??>
        <div class="col-md-6 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Исполнитель заявки</h2>

                    <div class="clearfix"></div>
                </div>
                <#if message??>
                    ${message}
                </#if>
                <div class="x_content">
                    <div class="row">

                        <br />
                        <form id="person" class="form-horizontal form-label-left">
<#--                            <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                            <input type="hidden" name="wid" value="${person.id}">


                            <#--                        <div class="form-group">-->
                            <#--                            <label class="control-label col-md-3 col-sm-3 col-xs-12">ТелеграммЧатId</label>-->
                            <#--                            <div class="col-md-6 col-sm-6 col-xs-12">-->
                            <#--                                <input type="text" name="TeleIdChat" class="form-control" placeholder="В формате, 316464237, либо пустым">-->

                            <#--                            </div>-->
                            <#--                        </div>-->
                            <H5>
                                <p>ФИО: ${person.family} ${person.name} ${person.surname}</p>
                                <br>
                                <p>Телефон: ${person.phone}</p>

                            </H5>

                            <div class="ln_solid"></div>
                        </form>
<#--                        <#if worcs.status != false>-->
<#--                            <div class="col-md-2 col-sm-12 col-xs-12 form-group">-->

<#--                            <button type="submit"  form="example" formaction="/manager/worcstop" formmethod="post" class="btn btn-danger">Отменить Заявку</button>-->
<#--                            <#if worcs.persons??>-->
<#--                                </div>-->
<#--                                <div class="col-md-2 col-sm-12 col-xs-15 form-group">-->

<#--                                    <button type="submit"  form="example" formaction="/manager/worcstotsellby" formmethod="post" class="btn btn-info">Вернуть средства и  отменить заявку</button>-->
<#--                                </div>-->
<#--                            </#if>-->
<#--                        <#else >-->
<#--                            <div class="col-md-2 col-sm-12 col-xs-12 form-group">-->

<#--                                <button type="submit"  form="example" formaction="/manager/worcstart" formmethod="post" class="btn btn-success">Запустить заявку</button>-->
<#--                            </div>-->
<#--                        &lt;#&ndash;                        <div class="col-md-3 col-sm-12 col-xs-12 form-group">&ndash;&gt;-->

<#--                        &lt;#&ndash;                            <button type="submit"  form="example" formaction="/manager/personfind" formmethod="get" class="btn btn-dark">Перезапустить с возвратом</button>&ndash;&gt;-->
<#--                        &lt;#&ndash;                        </div>&ndash;&gt;-->
<#--                        </#if>-->

                    </div>
                </div>
            </div>
        </div>
    </#if>
<#--   ------------Конец -------------- Блок исполнителя---------------->


</#macro>