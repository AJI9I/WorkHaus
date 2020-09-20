<#import "parts/topnav.ftlh" as s>
<#import "parts/leftnaw.ftlh" as a>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Gentallela Alela! | </title>

  <!-- Bootstrap core CSS -->

  <link href="css/bootstrap.min.css" rel="stylesheet">

  <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
  <link href="css/animate.min.css" rel="stylesheet">

  <!-- Custom styling plus plugins -->
  <link href="css/custom.css" rel="stylesheet">
  <link href="css/icheck/flat/green.css" rel="stylesheet">


  <script src="js/jquery.min.js"></script>

  <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>


<body class="nav-md">
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

  <div class="container body">


    <div class="main_container">

     <@a.leftnaw/>

      <@s.topnav/>

      <!-- page content -->
      <div class="right_col" role="main">

        <div class="">

          <div class="clearfix"></div>

          <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel">
                <div class="x_title">
                  <h2>Форма добавления заявки</h2>

                  <div class="clearfix"></div>
                </div>
                <div class="x_content">


                  <!-- Smart Wizard -->
                  <p>Эта форма служит для добавления нового заказа</p>
                  <#if message??>
                    ${message}
                  </#if>
                  <div id="wizard" class="form_wizard wizard_horizontal">
                    <ul class="wizard_steps">
                      <li>
                        <a href="#step-1">
                          <span class="step_no">1</span>
                          <span class="step_descr">
                                            Заказ<br />
                                            <small>Шаг 1 заказе</small>
                                        </span>
                        </a>
                      </li>
                      <li>
                        <a href="#step-2">
                          <span class="step_no">2</span>
                          <span class="step_descr">
                                            Заказчик<br />
                                            <small>Шаг 2 заказчик</small>
                                        </span>
                        </a>
                      </li>
                      <li>
                        <a href="#step-3">
                          <span class="step_no">3</span>
                          <span class="step_descr">
                                            Подтверждение<br />
                                            <small>Шаг 3 подтверждение</small>
                                        </span>
                        </a>
                      </li>

                    </ul>
                    <form class="form-horizontal form-label-left" method="post" action="/workadd">
                    <div id="step-1">
<#--                      <form class="form-horizontal form-label-left">-->

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Категория заказа</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <select class="form-control col-md-7 col-xs-12" name="CategoryId">
                            <option selected value=""></option>
                            <#list cat as ca>
                              <#if ca.lvl == 1>
                                <option value="${ca.id}">${ca.name}</option>
                              </#if>
                            </#list>
                          </select>
                        </div>
                      </div>


                        <div class="form-group">
                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Наименование заказа
                          </label>
                          <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Name" placeholder="Введите наименование работы">
                          </div>
                        </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Дополнительное описание заказ</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <textarea class="form-control col-md-7 col-xs-12" name="FullName" rows="3" placeholder="Подробное описание работы"></textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Стоимость заказа
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Price" placeholder="Стоимость выполнения работы">
                        </div>
                      </div>


<#--                        <div class="form-group">-->
<#--                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Last Name <span class="required">*</span>-->
<#--                          </label>-->
<#--                          <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                            <input type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">-->
<#--                          </div>-->
<#--                        </div>-->
<#--                        <div class="form-group">-->
<#--                          <label for="middle-name" class="control-label col-md-3 col-sm-3 col-xs-12">Middle Name / Initial</label>-->
<#--                          <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                            <input id="middle-name" class="form-control col-md-7 col-xs-12" type="text" name="middle-name">-->
<#--                          </div>-->
<#--                        </div>-->
<#--                        <div class="form-group">-->
<#--                          <label class="control-label col-md-3 col-sm-3 col-xs-12">Gender</label>-->
<#--                          <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                            <div id="gender" class="btn-group" data-toggle="buttons">-->
<#--                              <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">-->
<#--                                <input type="radio" name="gender" value="male"> &nbsp; Male &nbsp;-->
<#--                              </label>-->
<#--                              <label class="btn btn-primary active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">-->
<#--                                <input type="radio" name="gender" value="female" checked=""> Female-->
<#--                              </label>-->
<#--                            </div>-->
<#--                          </div>-->
<#--                        </div>-->
<#--                        <div class="form-group">-->
<#--                          <label class="control-label col-md-3 col-sm-3 col-xs-12">Date Of Birth <span class="required">*</span>-->
<#--                          </label>-->
<#--                          <div class="col-md-6 col-sm-6 col-xs-12">-->
<#--                            <input id="birthday" class="date-picker form-control col-md-7 col-xs-12" required="required" type="text">-->
<#--                          </div>-->
<#--                        </div>-->

<#--                      </form>-->

                    </div>
                    <div id="step-2">
                      <h2 class="StepTitle">Сведения о заказчике</h2>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">ФИО Заказчика
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="FIO" placeholder="ФИО заказчика">
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Номер телефона
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control tel col-md-7 col-xs-12" name="Phone" placeholder="Номер телефона заказчика">
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Район выполнения работы
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="ROn" placeholder="Район выполнения работы">
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Улица
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Street" placeholder="Цлица">
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Дом
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="House" placeholder="Дом">
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Квартира
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" required="required" class="form-control col-md-7 col-xs-12" name="Apartment" placeholder="Квартира">
                        </div>
                      </div>



                    </div>
                    <div id="step-3">
                      <h2 class="StepTitle">Step 3 Content</h2>
                      <p>
                        Убедитесь что все необходимые данные для оформления заказа введены и нажмити кнопку принять заказ
                      </p>
                      <button type="submit" class="btn btn-success">Добавить</button>
                    </div>
                    </form>
                  </div>
                  <!-- End SmartWizard Content -->
                </div>
              </div>
            </div>

          </div>
        </div>



      </div>
      <!-- /page content -->
    </div>

  </div>

  <div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
  </div>

  <script src="js/bootstrap.min.js"></script>

  <!-- bootstrap progress js -->
  <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
  <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
  <!-- icheck -->
  <script src="js/icheck/icheck.min.js"></script>

  <script src="js/custom.js"></script>
  <!-- form wizard -->
  <script type="text/javascript" src="js/wizard/jquery.smartWizard.js"></script>
  <!-- pace -->
  <script src="js/pace/pace.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      // Smart Wizard
      $('#wizard').smartWizard();

      function onFinishCallback() {
        $('#wizard').smartWizard('showMessage', 'Finish Clicked');
        //alert('Finish Clicked');
      }
    });

    $(document).ready(function() {
      // Smart Wizard
      $('#wizard_verticle').smartWizard({
        transitionEffect: 'slide'
      });

    });
  </script>
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
