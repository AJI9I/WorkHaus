<#import "parts/commn.ftlh" as c>
<#import "parts/personedit.ftlh" as v>
<#import "parts/personcategorylistdel.ftlh" as a>
<#import "parts/personcategorylistadd.ftlh" as b>

<@c.page>
    <@v.personedit/>
    <#if pps??>
        <div class="row">
        <@a.personcategorylistdel/>
        <@b.personcategorylistadd/>
        </div>
    </#if>
</@c.page>