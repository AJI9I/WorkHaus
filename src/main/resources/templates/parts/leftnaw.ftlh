<#include "security.ftlh">
<#macro leftnaw>
    <div class="col-md-3 left_col">
        <div class="left_col scroll-view">


            <div class="navbar nav_title" style="border: 0;">
                <a href="/" class="site_title">
                    <i class="fa fa-paw"></i>
                    <span>WorkHAus</span>
                </a>
            </div>
            <div class="clearfix"></div>

<#--            <!-- menu prile quick info &ndash;&gt;-->
<#--            <div class="profile">-->
<#--                <div class="profile_pic">-->
<#--                    <img src="static/images/img.jpg" alt="..." class="img-circle profile_img">-->
<#--                </div>-->
<#--                <div class="profile_info">-->
<#--                    <span>Добро пожаловать</span>-->
<#--                    <h2>Альберт</h2>-->
<#--                </div>-->
<#--            </div>-->
<#--            <!-- /menu prile quick info &ndash;&gt;-->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

<#--                <div class="menu_section">-->
<#--                    <h3>Меню Manager</h3>-->
<#--                    <ul class="nav side-menu">-->
<#--                        <li>-->
<#--                            <a href="/categoryadd">-->
<#--                                <i class="fa fa-home"></i> Добавить категорию-->
<#--                            </a>-->

<#--                        </li>-->
<#--&lt;#&ndash;                        <li>&ndash;&gt;-->
<#--&lt;#&ndash;                            <a href="/personadd">&ndash;&gt;-->
<#--&lt;#&ndash;                                <i class="fa fa-edit"></i> Добавить работника&ndash;&gt;-->
<#--&lt;#&ndash;                            </a>&ndash;&gt;-->

<#--&lt;#&ndash;                        </li>&ndash;&gt;-->


<#--&lt;#&ndash;                        <li>&ndash;&gt;-->
<#--&lt;#&ndash;                            <a href="/personfind">&ndash;&gt;-->
<#--&lt;#&ndash;                                <i class="fa fa-desktop"></i>Поиск работника&ndash;&gt;-->
<#--&lt;#&ndash;                            </a>&ndash;&gt;-->

<#--&lt;#&ndash;                        </li>&ndash;&gt;-->
<#--&lt;#&ndash;                        <li>&ndash;&gt;-->
<#--&lt;#&ndash;                            <a href="/workadd">&ndash;&gt;-->
<#--&lt;#&ndash;                                <i class="fa fa-table"></i>Добавить заказ&ndash;&gt;-->
<#--&lt;#&ndash;                            </a>&ndash;&gt;-->
<#--&lt;#&ndash;                        </li>&ndash;&gt;-->
<#--&lt;#&ndash;                        <li>&ndash;&gt;-->
<#--&lt;#&ndash;                            <a href="/balanceupdate">&ndash;&gt;-->
<#--&lt;#&ndash;                                <i class="fa fa-table"></i> Работа с балансом&ndash;&gt;-->
<#--&lt;#&ndash;                            </a>&ndash;&gt;-->
<#--&lt;#&ndash;                        </li>&ndash;&gt;-->
<#--&lt;#&ndash;                        <li>&ndash;&gt;-->
<#--&lt;#&ndash;                            <a href="/manageradd">&ndash;&gt;-->
<#--&lt;#&ndash;                                <i class="fa fa-table"></i> Добавить менеджера&ndash;&gt;-->
<#--&lt;#&ndash;                            </a>&ndash;&gt;-->
<#--&lt;#&ndash;                        </li>&ndash;&gt;-->

<#--&lt;#&ndash;                        <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>&ndash;&gt;-->
<#--&lt;#&ndash;                            <ul class="nav child_menu" style="display: none">&ndash;&gt;-->
<#--&lt;#&ndash;                                <li><a href="chartjs.html">Chart JS</a>&ndash;&gt;-->
<#--&lt;#&ndash;                                </li>&ndash;&gt;-->
<#--&lt;#&ndash;                                <li><a href="chartjs2.html">Chart JS2</a>&ndash;&gt;-->
<#--&lt;#&ndash;                                </li>&ndash;&gt;-->
<#--&lt;#&ndash;                                <li><a href="morisjs.html">Moris JS</a>&ndash;&gt;-->
<#--&lt;#&ndash;                                </li>&ndash;&gt;-->
<#--&lt;#&ndash;                                <li><a href="echarts.html">ECharts </a>&ndash;&gt;-->
<#--&lt;#&ndash;                                </li>&ndash;&gt;-->
<#--&lt;#&ndash;                                <li><a href="other_charts.html">Other Charts </a>&ndash;&gt;-->
<#--&lt;#&ndash;                                </li>&ndash;&gt;-->

<#--                    </ul>-->
<#--                </div>-->

                <#--                ---начало--------Admin panel-----------                      -->

                <#if isAdmin>
                    <br>
                    <div class="menu_section">
                        <h3>Меню Админ</h3>
                        <ul class="nav side-menu">
                            <li>
                                <a href="/administration/mmanageradd">
                                    <i class="fa fa-home"></i> Добавить менеджера
                                </a>

                            </li>
                            <li>
                                <a href="/administration/managerlist">
                                    <i class="fa fa-home"></i> Менеджеры
                                </a>

                            </li>
                        </ul>
                    </div>
                </#if>


                <#--                ---конец --------Admin panel-----------                      -->

                <#--                ---начало--------Manager panel-----------                      -->
            <#if isManag>
                <br>
                <div class="menu_section">
                    <h3>Меню Менеджера</h3>
                    <ul class="nav side-menu">
                        <li>
                            <a href="/manager/addareaandsuburbs">
                                <i class="fa fa-home"></i> Добавить район и пригород
                            </a>

                        </li>
                        <li>
                            <a href="/manager/categoryadd">
                                <i class="fa fa-home"></i> Добавить категорию
                            </a>

                        </li>
                        <li>
                            <a href="/manager/personadd">
                                <i class="fa fa-home"></i> Добавить специалиста
                            </a>

                        </li>
                        <li>
                            <a href="/manager/personfind">
                                <i class="fa fa-home"></i> Поиск специалиста
                            </a>

                        </li>
                        <li>
                            <a href="/manager/workadd">
                                <i class="fa fa-home"></i> Создать заявку
                            </a>

                        </li>
                        <li>
                            <a href="/manager/workfind">
                                <i class="fa fa-home"></i> Найти заявку
                            </a>

                        </li>

                        <li>
                            <a href="/manager/worcconformationlist">
                                <i class="fa fa-home"></i> Заявки на подтверждение
                            </a>

                        </li>
                        <li>
                            <a href="/manager/messagesite">
                                <i class="fa fa-home"></i> Сообщения с сайта
                            </a>

                        </li>
                        <li>
                            <a href="/manager/messagesiteperson">
                                <i class="fa fa-home"></i> Анкеты от специалистов
                            </a>

                        </li>

                    </ul>
                </div>
            </#if>


                <#--                ---конец --------Manager panel-----------                      -->
<#--                <div class="menu_section">-->
<#--                    <h3>Live On</h3>-->
<#--                    <ul class="nav side-menu">-->
<#--                        <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>-->
<#--                            <ul class="nav child_menu" style="display: none">-->
<#--                                <li><a href="e_commerce.html">E-commerce</a>-->
<#--                                </li>-->
<#--                                <li><a href="projects.html">Projects</a>-->
<#--                                </li>-->
<#--                                <li><a href="project_detail.html">Project Detail</a>-->
<#--                                </li>-->
<#--                                <li><a href="contacts.html">Contacts</a>-->
<#--                                </li>-->
<#--                                <li><a href="profile.html">Profile</a>-->
<#--                                </li>-->
<#--                            </ul>-->
<#--                        </li>-->
<#--                        <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>-->
<#--                            <ul class="nav child_menu" style="display: none">-->
<#--                                <li><a href="page_404.html">404 Error</a>-->
<#--                                </li>-->
<#--                                <li><a href="page_500.html">500 Error</a>-->
<#--                                </li>-->
<#--                                <li><a href="plain_page.html">Plain Page</a>-->
<#--                                </li>-->
<#--                                <li><a href="login.html">Login Page</a>-->
<#--                                </li>-->
<#--                                <li><a href="pricing_tables.html">Pricing Tables</a>-->
<#--                                </li>-->

<#--                            </ul>-->
<#--                        </li>-->
<#--                        <li><a><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a>-->
<#--                        </li>-->
<#--                    </ul>-->
<#--                </div>-->

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
<#--                <a data-toggle="tooltip" data-placement="top" title="Settings">-->
<#--                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>-->
<#--                </a>-->
<#--                <a data-toggle="tooltip" data-placement="top" title="FullScreen">-->
<#--                    <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>-->
<#--                </a>-->
<#--                <a data-toggle="tooltip" data-placement="top" title="Lock">-->
<#--                    <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>-->
<#--                </a>-->
                <a href="/logout" data-toggle="tooltip" data-placement="top" title="Logout">
                    <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                </a>
            </div>
            <!-- /menu footer buttons -->
        </div>
    </div>

</#macro>