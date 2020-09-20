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
                                <a href="/"><img src="/assets/images/logo/logo-2.png" alt="Мастер на час ВоркХаус онлайн"></a>
                                <br/>
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
        <!-- Hero Slider start -->
        <div class="hero-slider hero-slider-bg-1" id="slider">
            <div class="single-slide slider-mockup">
                <!-- Hero Content One Start -->
                <div class="hero-content-one container">
                    <div class="row align-items-center">
                        <div class="col-lg-6 col-md-8">
                            <div class="slider-text-info">
                                <h1>Сервисная служба мастер на час WorkHAus <br>в городе Миасс</h1>
                                <p>Имеется база профессиональных:</p>

                                <ul class="list-unstyled">

<#--                                    <h5><li class="list-inline-item">Сантехников</li></h5>-->
<#--                                    <h5><li class="list-inline-item">Электриков</li></h5>-->
<#--                                    <h5><li class="list-inline-item">Разнорабочих</li></h5>-->
<#--                                    <h5><li class="list-inline-item">Водителей с личным АМ</li></h5>-->
<#--                                    <h5><li class="list-inline-item">Сварщиков</li></h5>-->
<#--                                        <h5><li class="list-inline-item">Сборщиков мебели</li></h5>-->
<#--                                        <h5><li class="list-inline-item">Дизайнеров</li></h5>-->
<#--                                        <h5><li class="list-inline-item">Мастеров по ремонту бытовой техники</li></h5>-->
<#--                                        <h5><li class="list-inline-item">Сборщиков мебели</li></h5>-->

                                    <li >Сантехников</li>
                                    <li >Электриков</li>
                                    <li >Разнорабочих</li>
                                    <li >Водителей с личным АМ</li>
                                    <li >Сварщиков</li>
                                    <li >Сборщиков мебели</li>
                                    <li >Дизайнеров</li>
                                    <li >Мастеров по ремонту бытовой техники</li>
                                    <li >Сборщиков мебели</li>
                                </ul>
                                Все специалисты имеют большой опыт работы, и могут предложить взвешанное решение поставленной вами задачи
                                </br>
                                <a href="tel:+79085824394">
                                    Телефон для связи: <b>+7 908 5824394</b>
                                </a>
<#--                                <div class="slider-button">-->
<#--                                    <a href="#" class=" slider-btn uppercase"><span><i class="fa fa-apple"></i>Apple store</span></a>-->
<#--                                    <a href="#" class="slider-btn uppercase"><span><i class="fa fa-send-o"></i>Google play</span></a>-->
<#--                                </div>-->

                            </div>
                        </div>
                        <div class="col-lg-3 offset-lg-1 col-md-4 text-center">
                            <div class="slider-inner-imge-2 banner-right">
                                <a href="tel:+79085824394">
                                <img src="assets/images/slider/telMiass.png" alt="Мастер на час миасс">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Hero Content One End -->
            </div>
        </div>
        <!-- Hero Slider end -->

        <!-- Contact Area Start -->
        <div id="contact" class="contact-area-two section-pt section-pb-70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="contact-info-container">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="contact-title">
                                        <h2>Вы можете отправить нам сообщение</h2>
                                        <p>Краткое описание задачи требующей решения специалиста, после обработки вашего сообщения мы с вами свяжемся</p>
                                        <p>Наш многолетний опыт работы со специалистами разных квалификаций, снабдил нас большой базой специалистов которую в свою очередь мы постоянно пополняем и отбираем только лучших и зарекомендовавших себя профессионалов.</p>
                                        <p></p>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <form class="contact-form-area" action="/rquestmiass" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <div class="row contact-form">
                                            <#if message??>
                                                <div class="form-group col-md-12">
                                                    ${message}
                                                </div>
                                            </#if>

<#--                                            <div class="form-group col-md-12">-->

<#--                                                <select class="form-control" name="cid">-->
<#--                                                    <option selected value="">Выберите город</option>-->
<#--                                                    <#if citys??>-->
<#--                                                        <#list citys as c>-->

<#--                                                            <option value="${c.id}">${c.name}</option>-->

<#--                                                        </#list>-->
<#--                                                    </#if>-->

<#--                                                </select>-->
<#--                                            </div>-->

                                            <div class="form-group col-md-12">
                                                <input name="name" class="form-control" placeholder="Как к Вам обращаться?" type="text" id="name"/>
                                            </div>

                                            <div class="form-group col-md-12">
                                                <input name="phone" class="form-control tel" placeholder="Номер телефона" type="phone" id="email"/>
                                            </div>

                                            <div class="form-group col-md-12">
                                                <textarea name="message" class="yourmessage form-control" placeholder="Ваше сообщение"></textarea>
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
        <!-- Service Area Start  -->
        <div  id="feature" class="service-area section-pb">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 ml-auto mr-auto">
                        <div class="section-title">
                            <h2>Присоединяйтесь к нам в соц сетях</h2>
                            <p>.</p>
                        </div>
                    </div>
                </div>
                <div class="row">

<#--                    <div class="col-lg-4 col-md-6">-->
<#--                        <!-- Single Service Start &ndash;&gt;-->
<#--                        <div class="single-service fadeInLeft">-->
<#--                            <div class="service-icon">-->
<#--                                <img src="assets/images/icon/feature-01.png" alt="">-->
<#--                            </div>-->
<#--                            <div class="service-text">-->
<#--                                <h3>Клининговые услуги</h3>-->
<#--                                <p>Оставьте заботу о том кто будет наводить порядок у вас на рабочих местах, нам.</p>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <!-- Single Service End &ndash;&gt;-->
<#--                    </div>-->
<#--                    <div class="col-lg-4 col-md-6">-->
<#--                        <!-- Single Service Start &ndash;&gt;-->
<#--                        <div class="single-service fadeInLeft">-->
<#--                            <div class="service-icon">-->
<#--                                <img src="assets/images/icon/feature-01.png" alt="">-->
<#--                            </div>-->
<#--                            <div class="service-text">-->
<#--                                <h3>Сантехнические работы</h3>-->
<#--                                <p>Квалифицированный персонал будет решать проблемы с сантехникой по требованию либо проводя диагностические работы у вас на производстве.</p>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <!-- Single Service End &ndash;&gt;-->
<#--                    </div>-->
<#--                    <div class="col-lg-4 col-md-6">-->
<#--                        <!-- Single Service Start &ndash;&gt;-->
<#--                        <div class="single-service fadeInLeft">-->
<#--                            <div class="service-icon">-->
<#--                                <img src="assets/images/icon/feature-01.png" alt="">-->
<#--                            </div>-->
<#--                            <div class="service-text">-->
<#--                                <h3>Электрик</h3>-->
<#--                                <p>Одним изосновопологающих не прирывного производства является электроснабжение обслуживаемое профессионалами.</p>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <!-- Single Service End &ndash;&gt;-->
<#--                    </div>-->

                    <script type="text/javascript" src="https://vk.com/js/api/openapi.js?168"></script>

                    <!-- VK Widget -->
                    <div id="vk_groups" style="width: 100%; height: 400px; background: rgba(0, 0, 0, 0) none repeat scroll 0% 0%;"></div>
                    <script type="text/javascript">
                        VK.Widgets.Group("vk_groups", {mode: 3, width: "auto", height: "400"}, 32718884);
                    </script>

                </div>
                <br>
                <div class="row">
                    <div class="col-lg-7 ml-auto mr-auto">
                        <div class="section-title">
                            <p>Мы предложим Вам комплексное решение на индивидуальных условиях</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Service Area End -->

<#--        <!-- Service Area Start  &ndash;&gt;-->
<#--        <div  id="feature" class="service-area-four section-pt section-pb-70">-->
<#--            <div class="container">-->
<#--                <div class="row">-->
<#--                    <div class="col-lg-7 ml-auto mr-auto">-->
<#--                        <div class="section-title">-->
<#--                            <h2>Специалисты</h2>-->
<#--                            <p>Специалисты в городе Миасс готовы приступить к выполнению работ.</p>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--                <div class="feature-area-inner-four">-->
<#--                    <div class="row">-->
<#--                        <div class="col-lg-4 col-md-6">-->
<#--                            <div class="single-feature mb--30">-->
<#--                                <div class="feature-icon">-->
<#--                                    <i class="ion-ios-cloud-download-outline"></i>-->
<#--                                </div>-->
<#--                                <div class="feature-text">-->
<#--                                    <h3>Услуги грузчиков</h3>-->
<#--                                    <p>Сильные и ловкие ребята помогут вам обустроить переезд, разгрузить/загрузить.</p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <div class="col-lg-4 col-md-6">-->
<#--                            <div class="single-feature active mb--30">-->
<#--                                <div class="feature-icon">-->
<#--                                    <i class="ion-ios-lightbulb-outline"></i>-->
<#--                                </div>-->
<#--                                <div class="feature-text">-->
<#--                                    <h3>Услуги водителей на АМ</h3>-->
<#--                                    <p>Прфессиональне водители помогут вам осуществить переезд, перевести крупногабаритный или сыпучий груз.</p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <div class="col-lg-4 col-md-6">-->
<#--                            <div class="single-feature mb--30">-->
<#--                                <div class="feature-icon">-->
<#--                                    <i class="ion-ios-paper-outline"></i>-->
<#--                                </div>-->
<#--                                <div class="feature-text">-->
<#--                                    <h3>Строители</h3>-->
<#--                                    <p>Профессиональные строители помогут вам с ремонтом крыша, фасада или возведении строений на вашем участке.</p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--        <!-- Service Area End &ndash;&gt;-->

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