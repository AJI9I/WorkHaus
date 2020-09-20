<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Добовление работы | Работы</title>
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


        <p> <strong>Наименование работы: </strong></p>
        <input type="text" name="Name" placeholder="Введите наименование работы">

        <p><strong>Более подробное описание: </strong></p>
        <textarea  name="FullName" placeholder="Подробное описание работы" cols="40" rows="3"></textarea>

        </p>
                <select name="CategoryId">
                    <#--                <input list="Father" name="CategoryFather" placeholder="Выберите родительскую категорию">-->
                    <#--                <datalist id="Father">-->
                    <option selected value=""></option>
                    <#list cat as ca>
                        <#if ca.lvl == 1>
                            <option value="${ca.id}">${ca.name}</option>
                        </#if>
                    </#list>
                </select>
<#--                        </datalist>-->


        <p><strong>Стоимость выполнения работы </strong></p>
        <input type="text" name="Price" placeholder="Стоимость выполнения работы">

        <p><strong>Номер телефона заказчика</strong></p>
        <input type="text" name="Phone" class="form-control tel" placeholder="Номер телефона заказчика">

        <p><strong>Аддрес заказчика</strong></p>
        </br>
        <p><strong>Район расположения заказа </strong></p>
        <input type="text" name="ROn" placeholder="Район выполнения работы">

        <p><strong>Улица</strong></p>
        <input type="text" name="Street" placeholder="Цлица">

        <p><strong>Дом</strong></p>
        <input type="text" name="House" placeholder="Дом">

        <p><strong>Квартира</strong></p>
        <input type="text" name="Apartment" placeholder="Квартира">

        <p><strong>ФИО заказчика</strong></p>
        <input type="text" name="FIO" placeholder="ФИО заказчика">

        <p><button type="submit">Добавить</button></p>
    </form>
</div>
<#list wi as w>
    <p>${w.name}_ ${w.categorys.name}_ ${w.price}</p>
</#list>
</body>
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
</html>