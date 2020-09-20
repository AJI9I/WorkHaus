<#import "parts/commn.ftlh" as c>
<#import "parts/personedit.ftlh" as v>
<#import "parts/personcategorylistdel.ftlh" as a>
<#import "parts/personcategorylistadd.ftlh" as b>
<#import "parts/paymentshistory.ftlh" as d>

<@c.page>
    <@v.personedit/>
    <@a.personcategorylistdel/>
    <@b.personcategorylistadd/>
    <@d.paymentshistory/>
</@c.page>