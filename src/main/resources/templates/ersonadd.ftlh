<!DOCTYPE html>
<html lang="en">
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
    <form method="post">
        <#if message??>
            ${message}
        </#if>

        <p><strong>Добавление работника: </strong></p>

        <p> <strong>Имя: </strong></p>
        <input type="text" name="Name" placeholder="Введите имя">
        <p><strong>Фамилия: </strong></p>
        <input  type="text" name="Family" placeholder="Введите фамилию" >
        <p><strong>Отчество: </strong></p>
        <input type="text" name="Surname" placeholder="Введите фамилию">
        <p><strong>Телефон: </strong></p>
        <input type="text" name="Phone"  class="form-control tel" placeholder="Номер телефона">

        <p><strong>Баланс: </strong></p>
        <input type="text" name="Balance" placeholder="Введите фамилию">

        <p><strong>Телеграмм ID: </strong></p>
        <input type="text" name="TeleId" placeholder="Добавьте телеграмм идентификатор">
        <p><button type="submit">Добавить</button></p>
    </form>
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