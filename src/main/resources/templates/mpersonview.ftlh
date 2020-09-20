<#import "parts/commn.ftlh" as c>
<#import "parts/personview.ftlh" as v>
<#import "parts/personlist.ftlh" as a>

<@c.page>
    <#if pers??>
    <@v.personview/>
        </#if>
    <#if persons??>

        <@a.personlist/>
    </#if>

</@c.page>