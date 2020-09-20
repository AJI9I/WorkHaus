<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Упаравление категориями | Категории работ</title>
</head>
<body>
<!-- Yandex.Metrika counter -->
<script type="text/javascript" >
    (function(m,e,t,r,i,k,a){m[i]=m[i]||function(){(m[i].a=m[i].a||[]).push(arguments)};
        m[i].l=1*new Date();k=e.createElement(t),a=e.getElementsByTagName(t)[0],k.async=1,k.src=r,a.parentNode.insertBefore(k,a)})
    (window, document, "script", "https://mc.yandex.ru/metrika/tag.js", "ym");

    ym(64717183, "init", {
        clickmap:true,
        trackLinks:true,
        accurateTrackBounce:true,
        webvisor:true
    });
</script>
<noscript><div><img src="https://mc.yandex.ru/watch/64717183" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->
<div>
    <form method="post">
        <input type="hidden" name="Id" value="${caid.id}">
        <#if message??>
            ${message}
        </#if>
        <p><strong>Родительская категория: </strong></p>
        <select name="CategoryFather">
            <#list cat as ca>
                <#if ca.lvl == 0>
                    <#if ca.id = caid.father>
                        <option selected value="${ca.id}">${ca.name}</option>
                        <#else >
                            <option value="${ca.id}">${ca.name}</option>
                    </#if>
                </#if>
            </#list>
        </select>
        <p> <strong>Наименование категории: </strong></p>
        <input type="text" name="CategoryName" value="${caid.name}">
        <p><strong>Процент с категории: </strong></p>
        <input type="text" name="CategoryPercent" value="${caid.percent}">
        <p><strong>Описание категории: </strong></p>
        <textarea  name="CategoryDescription" cols="40" rows="3">${caid.discription}</textarea>

        <p><button type="submit">Применить</button></p>
    </form>
</div>
<#list cat as ca>
    <#if ca.lvl == 0>
        <strong>${ca.name} ${ca.percent} ${ca.discription} </strong>
        <#list cat as cc>
            <#if cc.father == ca.id>
                <form method="get" action="/categoryedit">
                    <strong>${cc.name}<input type="hidden" value="${cc.id}" name="CategoryId" ></strong>
                    <button type="submit">Edi</button>
                </form>
            </#if>
        </#list>
    </#if>
</#list>
</body>
</html>