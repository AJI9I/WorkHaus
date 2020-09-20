<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Упаравление работниками | Персонал</title>
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

        <#if message??>
            ${message}
        </#if>

        <#if !pps??>
            <form method="get">
                <p> <strong>Введите номер телефона для поиска </strong></p>
                <input type="text" name="Phone" placeholder="Введите номер">
                <p><button type="submit">Поиск</button></p>
            </form>
            <#else >
                <div>
                    <form method="post" action="/personfind">
                        <p><strong>Редактирование данных работника: </strong></p>
                        <input type="hidden" name="PersonId" value="${pps.id}"  >
                        <p> <strong>Имя: </strong></p>
                        <input type="text" name="Name" value="${pps.name}">
                        <p><strong>Фамилия: </strong></p>
                        <input  type="text" name="Family" value="${pps.family}" >
                        <p><strong>Отчество: </strong></p>
                        <input type="text" name="Surname" value="${pps.surname}">
                        <p><strong>Телефон: </strong></p>
                        <input type="text" name="Phone" class="form-control tel" value="${pps.phone}">

                        <p><strong>Баланс: </strong></p>
                        <input type="text" name="Balance" value="${pps.balance}">

                        <p><strong>Телеграмм ID: </strong></p>
                        <input type="text" name="TeleId" value="${pps.telegramId}">
                        <p><button type="submit">Добавить</button></p>
                    </form>
                </div>
                <div>
                    <#if pi??>
                    <#list pi as p>

                        <form method="post" action="/dellcategoryperson">
                            ${p.name}
                            <input type="hidden" value="${p.id}" name="CategoryId">
                            <input type="hidden" value="${pps.id}" name="PersonId">
                            <button type="submit">Del</button>
                        </form>

                    </#list>
                    </#if>
                </div>

                <div>
                    <#list ccat as ca>
                        <#if ca.lvl == 0>
                            <strong>${ca.name} ${ca.percent} ${ca.discription} </strong>
                            </br>
                            <#list ccat as cc>
                                <#if cc.father == ca.id>

                    <form method="post" action="/addcategoryperson">
                        <strong>${cc.name}</strong>
                                            <input type="hidden" value="${pps.id}" name="PersonId" >
                                            <input type="hidden" class="form-control tel" value="${pps.phone}" name="Phone" >
                                            <input type="hidden" value="${cc.id}" name="CategoryId" >
                                            <button type="submit">Add</button>
                                    </form>
                                </#if>
                            </#list>
                        </#if>
                    </#list>
                </div>
        </#if>


</div>
============================================ -->
<script>
    window.addEventListener("DOMContentLoaded", function() {
        [].forEach.call( document.querySelectorAll('.tel'), function(input) {
            var keyCode;
            function mask(event) {
                event.keyCode && (keyCode = event.keyCode);
                var pos = this.selectionStart;
                if (pos < 3) event.preventDefault();
                var matrix = "+7 (___) ___ ____",
                    i = 0,
                    def = matrix.replace(/\D/g, ""),
                    val = this.value.replace(/\D/g, ""),
                    new_value = matrix.replace(/[_\d]/g, function(a) {
                        return i < val.length ? val.charAt(i++) || def.charAt(i) : a
                    });
                i = new_value.indexOf("_");
                if (i != -1) {
                    i < 5 && (i = 3);
                    new_value = new_value.slice(0, i)
                }
                var reg = matrix.substr(0, this.value.length).replace(/_+/g,
                    function(a) {
                        return "\\d{1," + a.length + "}"
                    }).replace(/[+()]/g, "\\$&");
                reg = new RegExp("^" + reg + "$");
                if (!reg.test(this.value) || this.value.length < 5 || keyCode > 47 && keyCode < 58) this.value = new_value;
                if (event.type == "blur" && this.value.length < 5)  this.value = ""
            }

            input.addEventListener("input", mask, false);
            input.addEventListener("focus", mask, false);
            input.addEventListener("blur", mask, false);
            input.addEventListener("keydown", mask, false)

        });

    });
</script>
====================================================================================
</body>
</html>