<#import "parts/commn.ftlh" as c>
    <#import "parts/paymentshistory.ftlh" as fg>

<@c.page>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Обновление баланса работника: зачисление или списание</h2>

                    <div class="clearfix"></div>
                </div>
                <#if message??>
                    ${message}
                </#if>
<#--                <#if !pps??>-->
<#--                    <div class="x_content">-->
<#--                        <div class="row">-->
<#--                            <br />-->
<#--                            <form class="form-horizontal form-label-left" method="get">-->
<#--                                <div class="form-group">-->
<#--                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Телефон</label>-->
<#--                                    <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                                        <input type="text" name="Phone" placeholder="Введите номер" class="form-control" data-inputmask="'mask' : '(999) 999-9999'">-->
<#--                                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                                <div class="ln_solid"></div>-->

<#--                                <div class="form-group">-->
<#--                                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">-->
<#--                                        <button type="submit" class="btn btn-success">Поиск</button>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </form>-->
<#--                        </div>-->
<#--                    </div>-->


<#--                    <#else>-->
                        <div class="x_content">
                            <div class="row">
                                <br />
<#--                                -->
                                <form id="example" class="form-horizontal form-label-left" >
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="hidden" name="PersonId" value="${pps.id}"  >
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">ФИО</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control" name="Family" value="${pps.family} ${pps.name} ${pps.surname}" readonly="readonly" placeholder="Read-Only Input">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Телефон</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" name="Phone" value="${pps.phone}" class="form-control tel" >
                                            <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Баланс</label>
                                    <div class="col-md-2 col-sm-12 col-xs-12 form-group">
                                        <input type="text" placeholder="" value="<#if pps.cashaccounts??>${pps.cashaccounts.cash}<#else >0</#if>" class="form-control" readonly="readonly" placeholder="Read-Only Input">
                                    </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Введите сумму</label>
                                        <div class="col-md-2 col-sm-12 col-xs-12 form-group">
                                            <input type="text" name="Balanceupdate" placeholder="" class="form-control">
                                        </div>
                                        <div class="col-md-2 col-sm-12 col-xs-12 form-group">

                                            <button type="submit" form="example" formaction="/manager/addsumm" formmethod="post"  class="btn btn-success">Зачислить</button>

                                        </div>
                                        <div class="col-md-2 col-sm-12 col-xs-12 form-group">

                                            <button type="submit"  form="example" formaction="/manager/dellsumm" formmethod="post" class="btn btn-warning">Списать</button>
                                        </div>

                                    </div>

                                    <div class="ln_solid"></div>

                                </form>
                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                                    <button type="submit"  form="example" formaction="/manager/personview" formmethod="post" class="btn btn-warning">Карточка специалиста</button>
                                </div>
                            </div>
                        </div>
<#--                </#if>-->

            </div>
        </div>
            </div>
    <#if paymenthistorys??>
        <@fg.paymentshistory/>
    </#if>

</@c.page>