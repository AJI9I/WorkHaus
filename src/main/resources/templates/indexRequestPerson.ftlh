<#import "parts/indexCommon.ftlh" as e>
<@e.page>
    <!-- Main Wrapper Start -->
    <div class="main-wrapper">
        <!-- header-area start -->
        <header class="header header-sticky">
            <!-- header-top start -->
            <div id="main-menu" class="header-top inner-header">
                <div class="container">
                    <div class="row header-top-inner">
                        <div class="col-lg-4">
                            <div class="logo">
                                <a href="/"><img src="/assets/images/logo/logo-2.png" alt="Мастер на час"></a>
                                </br>
                                <a href="tel:+79085824394">
                                    Тел:+79085824394
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="main-menu">
                                <nav class="main-navigation">
                                    <ul>
                                        <li><a href="/">На главную</a></li>
                                        <#--                                    <li class="smooth-scroll"><a href="/#about">О нас</a></li>-->
                                        <#--                                    <li><a href="/#feature">Работы</a></li>-->
                                        <#--                                    <li><a href="/#contact">Контакты</a></li>-->
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div class="col">
                            <!-- mobile-menu start -->
                            <div class="mobile-menu d-block d-lg-none"></div>
                            <!-- mobile-menu end -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header-top end -->
        </header>
        <!-- Header-area end -->

        <!-- Contact Area Start -->
        <div id="contact" class="contact-area-two section-ptb">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="contact-info-container">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="contact-title">
                                        <h2>Оставить анкету</h2>
                                        <p>Если вы ответственный и на хорошем уровне владеете какой нибудь специализацией, оставьте анкету для прохождения интервью и возможности получения заказов для выполнения работ. Наша компания является клиентоориентированной и мы тщательно подходим к выбору специалистов.</p>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <form class="contact-form-area" action="/personworcinterview" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <div class="row contact-form">
                                            <#if message??>
                                                ${message}
                                            </#if>

                                            <div class="form-group col-md-12">

                                                <select class="form-control" name="cid">
                                                    <option selected value="">Выберите город</option>
                                                    <#if citys??>
                                                        <#list citys as c>

                                                            <option value="${c.id}">${c.name}</option>

                                                        </#list>
                                                    </#if>

                                                </select>
                                            </div>

                                            <div class="form-group col-md-12">
                                                <input name="name" class="form-control" placeholder="Как к Вам обращаться?" type="text" id="name">
                                            </div>

                                            <div class="form-group col-md-12">
                                                <input name="phone" class="form-control tel" placeholder="Номер телефона" type="phone" id="email">
                                            </div>

                                            <div class="form-group col-md-12">
                                                <textarea name="message" class="yourmessage form-control" placeholder="Напишите что нибудь о себе, чем вы увлекаетесь, какой рабочей специальностью обладаете, какие работы можете выполнять из разряда мастер на час, полный перечень приведен на главной странице"></textarea>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <div class="g-recaptcha" data-sitekey="6LdM6_oUAAAAANL0laVivLecT2wB9w6lHiqYcowY"></div>
                                            </div>
                                            <div class="submit-form form-group col-sm-12">
                                                <button class="button submit-btn" type="submit"><span>Отправить</span></button>
                                                <p class="form-messege"></p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Contact Area End -->

    </div>
    <!-- Main Wrapper End -->

    <!-- JS
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
</@e.page>