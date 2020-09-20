<#import "parts/commn.ftlh" as c>
<#import "parts/personfind.ftlh" as v>
<#import "parts/personlist.ftlh" as a>

<@c.page>
    <@v.personfind/>
    <#if persons??>
        <@a.personlist/>
    </#if>
</@c.page>