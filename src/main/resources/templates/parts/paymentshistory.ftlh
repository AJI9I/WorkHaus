<#macro paymentshistory>
    <div class="col-md-12 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>История операций  <small>со счетом специалиста</small></h2>
                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">

                <table class="table">
                    <thead>
                    <tr>

<#--                        paymenthistorys-->
                        <th>Сумма</th>
                        <th>Списание</th>
                        <th>Зачисление</th>
                        <th>Дата</th>
                        <th>Операция</th>
                    </tr>
                    </thead>
                    <tbody>



                    <#list paymenthistorys as k>
                        <tr>
                            <th>${k.cash}</th>
                            <td><#if k.downcash==0>-<#else >${k.downcash}</#if></td>
                            <td><#if k.upcash==0>-<#else >${k.upcash}</#if></td>
                            <td>${k.localdate}</td>
                            <td>${k.operation}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</#macro>