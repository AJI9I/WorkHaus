<#macro personcategorylistdel>
    <div class="col-md-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Категории работ исполнителя</h2>

                <div class="clearfix"></div>
            </div>
            <div class="x_content bs-example-popovers">

                <#if pi??>
                    <#list pi as p>

                            <form class="col-auto form-group" method="post" action="/manager/dellcategoryperson">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">

                                <strong>${p.name}</strong>
                                <input type="hidden" value="${p.id}" name="CategoryId">
                                <input type="hidden" value="${pps.id}" name="PersonId">
                                <input type="hidden" class="form-control tel" value="${pps.phone}" name="Phone">
                                <button type="submit" class="btn btn-danger btn-xs">Удалить</button>


                            </form>

                    </#list>
                </#if>

            </div>
        </div>
    </div>
</#macro>