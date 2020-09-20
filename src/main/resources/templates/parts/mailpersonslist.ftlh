<#macro mailpersonslist>
    <div class="col-md-12 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Сообщения <small>заявки от специалистов</small></h2>

                <div class="clearfix"></div>
            </div>
            <#if message??>
                ${message}
            </#if>
            <div class="x_content">
                <ul class="list-unstyled timeline">

                    <#if msgsw??>
                        <#list msgsw as m>
                            <li>
                                <div class="block">
                                    <div class="tags">
                                        <a href="" class="tag">
                                            <span>m</span>
                                        </a>
                                    </div>
                                    <div class="block_content">
                                        <h2 class="title">
                                            Номер телефона: ${m.phone}
                                        </h2>
                                        <div class="byline">
                                            <span>Дата</span> <a><#if m.localdate??> ${m.localdate}<#else >нету</#if></a>
                                        </div>
                                        <p class="excerpt">${m.message}
                                        </p>
                                    </div>
                                    <div>
                                        <form method="post" action="/manager/messagesiteperson">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <input type="hidden" value="${m.id}" name="mpid" >
                                            <button type="submit" class="btn btn-warning">Закрыть</button>
                                        </form>
                                    </div>
                                </div>
                            </li>
                        </#list>
                    </#if>



                    <#--                    <li>-->
                    <#--                        <div class="block">-->
                    <#--                            <div class="tags">-->
                    <#--                                <a href="" class="tag">-->
                    <#--                                    <span>Entertainment</span>-->
                    <#--                                </a>-->
                    <#--                            </div>-->
                    <#--                            <div class="block_content">-->
                    <#--                                <h2 class="title">-->
                    <#--                                    <a>Who Needs Sundance When You’ve Got&nbsp;Crowdfunding?</a>-->
                    <#--                                </h2>-->
                    <#--                                <div class="byline">-->
                    <#--                                    <span>13 hours ago</span> by <a>Jane Smith</a>-->
                    <#--                                </div>-->
                    <#--                                <p class="excerpt">Film festivals used to be do-or-die moments for movie makers. They were where you met the producers that could fund your project, and if the buyers liked your flick, they’d pay to Fast-forward and… <a>Read&nbsp;More</a>-->
                    <#--                                </p>-->
                    <#--                            </div>-->
                    <#--                        </div>-->
                    <#--                    </li>-->
                    <#--                    <li>-->
                    <#--                        <div class="block">-->
                    <#--                            <div class="tags">-->
                    <#--                                <a href="" class="tag">-->
                    <#--                                    <span>Entertainment</span>-->
                    <#--                                </a>-->
                    <#--                            </div>-->
                    <#--                            <div class="block_content">-->
                    <#--                                <h2 class="title">-->
                    <#--                                    <a>Who Needs Sundance When You’ve Got&nbsp;Crowdfunding?</a>-->
                    <#--                                </h2>-->
                    <#--                                <div class="byline">-->
                    <#--                                    <span>13 hours ago</span> by <a>Jane Smith</a>-->
                    <#--                                </div>-->
                    <#--                                <p class="excerpt">Film festivals used to be do-or-die moments for movie makers. They were where you met the producers that could fund your project, and if the buyers liked your flick, they’d pay to Fast-forward and… <a>Read&nbsp;More</a>-->
                    <#--                                </p>-->
                    <#--                            </div>-->
                    <#--                        </div>-->
                    <#--                    </li>-->
                </ul>

            </div>
        </div>
    </div>
</#macro>