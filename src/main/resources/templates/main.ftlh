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
        <#if message??>
            ${message}
        </#if>

        <p><strong>Родительская категория: </strong></p>
            <select name="CategoryFather">
<#--                <input list="Father" name="CategoryFather" placeholder="Выберите родительскую категорию">-->
<#--                <datalist id="Father">-->
                <option selected value=""></option>
                    <#list cat as ca>
                        <#if ca.lvl == 0>
                    <option value="${ca.id}">${ca.name}</option>
                        </#if>
                    </#list>
                            </select>
<#--                </datalist>-->
        <p> <strong>Наименование категории: </strong></p>
                <input type="text" name="CategoryName" placeholder="Введите название категории работ">
        <p><strong>Процент с категории: </strong></p>
                <input type="text" name="CategoryPercent" placeholder="Процент с работ по умолчанию">
        <p><strong>Описание категории: </strong></p>
                <textarea  name="CategoryDescription" placeholder="Сюда можно добавить более подробное описание категории" cols="40" rows="3"></textarea>
        <p><button type="submit">Добавить</button></p>
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