<#import "parts/commn.ftlh" as c>
<#import "parts/worcsfind.ftlh" as v>
    <#import "parts/worklist.ftlh" as f>

<@c.page>
    <#if worcsphone??>
        <@f.worclist/>
        <#else >
            <@v.worcsfind/>
            <@f.worclistworc/>
    </#if>
</@c.page>