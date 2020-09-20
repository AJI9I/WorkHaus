<#import "topnav.ftlh" as s>
<#import "leftnaw.ftlh" as a>
<#macro page>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Work  | Haus </title>

        <!-- Bootstrap core CSS -->

        <link href="/static/css/bootstrap.min.css" rel="stylesheet">

        <link href="/static/fonts/css/font-awesome.min.css" rel="stylesheet">
        <link href="/static/css/animate.min.css" rel="stylesheet">

        <!-- Custom styling plus plugins -->
        <link href="/static/css/custom.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="/static/css/maps/jquery-jvectormap-2.0.3.css" />
        <link href="/static/css/icheck/flat/green.css" rel="stylesheet" />
        <link href="/static/css/floatexamples.css" rel="stylesheet" type="text/css" />

        <script src="/static/js/jquery.min.js"></script>
        <script src="/static/js/nprogress.js"></script>

        <!--[if lt IE 9]>
        <script src="../../assets/js/ie8-responsive-file-warning.js"></script>
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


                                <#nested >


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

        <script src="/static/js/bootstrap.min.js"></script>

<#--    ============================================ &ndash;&gt;-->
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
<#--    ====================================================================================-->

        <!-- gauge js -->
        <script src="/static/js/datatables/jquery.dataTables.min.js"></script>
        <script src="/static/js/datatables/dataTables.bootstrap.js"></script>
        <script src="/static/js/datatables/dataTables.buttons.min.js"></script>
        <script src="/static/js/datatables/buttons.bootstrap.min.js"></script>
        <script src="/static/js/datatables/jszip.min.js"></script>
        <script src="/static/js/datatables/pdfmake.min.js"></script>
        <script src="/static/js/datatables/vfs_fonts.js"></script>
        <script src="/static/js/datatables/buttons.html5.min.js"></script>
        <script src="/static/js/datatables/buttons.print.min.js"></script>
        <script src="/static/js/datatables/dataTables.fixedHeader.min.js"></script>
        <script src="/static/js/datatables/dataTables.keyTable.min.js"></script>
        <script src="/static/js/datatables/dataTables.responsive.min.js"></script>
        <script src="/static/js/datatables/responsive.bootstrap.min.js"></script>
        <script src="/static/js/datatables/dataTables.scroller.min.js"></script>
        <!-- bootstrap progress js -->
        <script src="/static/js/progressbar/bootstrap-progressbar.min.js"></script>
        <script src="/static/js/nicescroll/jquery.nicescroll.min.js"></script>
        <!-- icheck -->
        <script src="/static/js/icheck/icheck.min.js"></script>

        <script src="/static/js/custom.js"></script>
        <!-- input mask -->
        <script src="/static/js/input_mask/jquery.inputmask.js"></script>


        <script type="text/javascript" src="/static/js/gauge/gauge.min.js"></script>
        <script type="text/javascript" src="/static/js/gauge/gauge_demo.js"></script>
        <!-- bootstrap progress js -->
        <script src="/static/js/progressbar/bootstrap-progressbar.min.js"></script>
        <script src="/static/js/nicescroll/jquery.nicescroll.min.js"></script>
        <!-- icheck -->
        <script src="/static/js/icheck/icheck.min.js"></script>
        <!-- daterangepicker -->
        <script type="text/javascript" src="/static/js/moment/moment.min.js"></script>
        <script type="text/javascript" src="/static/js/datepicker/daterangepicker.js"></script>
        <!-- chart js -->
        <script src="/static/js/chartjs/chart.min.js"></script>
        <!-- form wizard -->
        <script type="text/javascript" src="/static/js/wizard/jquery.smartWizard.js"></script>

        <!-- flot js -->
        <!--[if lte IE 8]><script type="text/javascript" src="/static/js/excanvas.min.js"></script><![endif]-->
        <script type="text/javascript" src="/static/js/flot/jquery.flot.js"></script>
        <script type="text/javascript" src="/static/js/flot/jquery.flot.pie.js"></script>
        <script type="text/javascript" src="/static/js/flot/jquery.flot.orderBars.js"></script>
        <script type="text/javascript" src="/static/js/flot/jquery.flot.time.min.js"></script>
        <script type="text/javascript" src="/static/js/flot/date.js"></script>
        <script type="text/javascript" src="/static/js/flot/jquery.flot.spline.js"></script>
        <script type="text/javascript" src="/static/js/flot/jquery.flot.stack.js"></script>
        <script type="text/javascript" src="/static/js/flot/curvedLines.js"></script>
        <script type="text/javascript" src="/static/js/flot/jquery.flot.resize.js"></script>
<#--        <script>-->
<#--            $(document).ready(function() {-->
<#--                // [17, 74, 6, 39, 20, 85, 7]-->
<#--                //[82, 23, 66, 9, 99, 6, 2]-->
<#--                var data1 = [-->
<#--                    [gd(2012, 1, 1), 17],-->
<#--                    [gd(2012, 1, 2), 74],-->
<#--                    [gd(2012, 1, 3), 6],-->
<#--                    [gd(2012, 1, 4), 39],-->
<#--                    [gd(2012, 1, 5), 20],-->
<#--                    [gd(2012, 1, 6), 85],-->
<#--                    [gd(2012, 1, 7), 7]-->
<#--                ];-->

<#--                var data2 = [-->
<#--                    [gd(2012, 1, 1), 82],-->
<#--                    [gd(2012, 1, 2), 23],-->
<#--                    [gd(2012, 1, 3), 66],-->
<#--                    [gd(2012, 1, 4), 9],-->
<#--                    [gd(2012, 1, 5), 119],-->
<#--                    [gd(2012, 1, 6), 6],-->
<#--                    [gd(2012, 1, 7), 9]-->
<#--                ];-->
<#--                $("#canvas_dahs").length && $.plot($("#canvas_dahs"), [-->
<#--                    data1, data2-->
<#--                ], {-->
<#--                    series: {-->
<#--                        lines: {-->
<#--                            show: false,-->
<#--                            fill: true-->
<#--                        },-->
<#--                        splines: {-->
<#--                            show: true,-->
<#--                            tension: 0.4,-->
<#--                            lineWidth: 1,-->
<#--                            fill: 0.4-->
<#--                        },-->
<#--                        points: {-->
<#--                            radius: 0,-->
<#--                            show: true-->
<#--                        },-->
<#--                        shadowSize: 2-->
<#--                    },-->
<#--                    grid: {-->
<#--                        verticalLines: true,-->
<#--                        hoverable: true,-->
<#--                        clickable: true,-->
<#--                        tickColor: "#d5d5d5",-->
<#--                        borderWidth: 1,-->
<#--                        color: '#fff'-->
<#--                    },-->
<#--                    colors: ["rgba(38, 185, 154, 0.38)", "rgba(3, 88, 106, 0.38)"],-->
<#--                    xaxis: {-->
<#--                        tickColor: "rgba(51, 51, 51, 0.06)",-->
<#--                        mode: "time",-->
<#--                        tickSize: [1, "day"],-->
<#--                        //tickLength: 10,-->
<#--                        axisLabel: "Date",-->
<#--                        axisLabelUseCanvas: true,-->
<#--                        axisLabelFontSizePixels: 12,-->
<#--                        axisLabelFontFamily: 'Verdana, Arial',-->
<#--                        axisLabelPadding: 10-->
<#--                        //mode: "time", timeformat: "%m/%d/%y", minTickSize: [1, "day"]-->
<#--                    },-->
<#--                    yaxis: {-->
<#--                        ticks: 8,-->
<#--                        tickColor: "rgba(51, 51, 51, 0.06)",-->
<#--                    },-->
<#--                    tooltip: false-->
<#--                });-->

<#--                function gd(year, month, day) {-->
<#--                    return new Date(year, month - 1, day).getTime();-->
<#--                }-->
<#--            });-->
<#--        </script>-->

        <!-- worldmap -->
        <script type="text/javascript" src="/static/js/maps/jquery-jvectormap-2.0.3.min.js"></script>
        <script type="text/javascript" src="/static/js/maps/gdp-data.js"></script>
        <script type="text/javascript" src="/static/js/maps/jquery-jvectormap-world-mill-en.js"></script>
        <script type="text/javascript" src="/static/js/maps/jquery-jvectormap-us-aea-en.js"></script>

        <!-- pace -->
        <script src="/static/js/pace/pace.min.js"></script>
<#--        <script>-->
<#--            $(function() {-->
<#--                $('#world-map-gdp').vectorMap({-->
<#--                    map: 'world_mill_en',-->
<#--                    backgroundColor: 'transparent',-->
<#--                    zoomOnScroll: false,-->
<#--                    series: {-->
<#--                        regions: [{-->
<#--                            values: gdpData,-->
<#--                            scale: ['#E6F2F0', '#149B7E'],-->
<#--                            normalizeFunction: 'polynomial'-->
<#--                        }]-->
<#--                    },-->
<#--                    onRegionTipShow: function(e, el, code) {-->
<#--                        el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');-->
<#--                    }-->
<#--                });-->
<#--            });-->
<#--        </script>-->
        <!-- skycons -->
        <script src="/static/js/skycons/skycons.min.js"></script>
        <script>
            var icons = new Skycons({
                    "color": "#73879C"
                }),
                list = [
                    "clear-day", "clear-night", "partly-cloudy-day",
                    "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
                    "fog"
                ],
                i;

            for (i = list.length; i--;)
                icons.set(list[i], list[i]);

            icons.play();
        </script>
        <!-- pace -->
        <script src="/static/js/pace/pace.min.js"></script>
        <script>
            var handleDataTableButtons = function() {
                    "use strict";
                    0 !== $("#datatable-buttons").length && $("#datatable-buttons").DataTable({
                        dom: "Bfrtip",
                        buttons: [{
                            extend: "copy",
                            className: "btn-sm"
                        }, {
                            extend: "csv",
                            className: "btn-sm"
                        }, {
                            extend: "excel",
                            className: "btn-sm"
                        }, {
                            extend: "pdf",
                            className: "btn-sm"
                        }, {
                            extend: "print",
                            className: "btn-sm"
                        }],
                        responsive: !0
                    })
                },
                TableManageButtons = function() {
                    "use strict";
                    return {
                        init: function() {
                            handleDataTableButtons()
                        }
                    }
                }();
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#datatable').dataTable();
                $('#datatable-keytable').DataTable({
                    keys: true
                });
                $('#datatable-responsive').DataTable();
                $('#datatable-scroller').DataTable({
                    ajax: "static/js/datatables/json/scroller-demo.json",
                    deferRender: true,
                    scrollY: 380,
                    scrollCollapse: true,
                    scroller: true
                });
                var table = $('#datatable-fixed-header').DataTable({
                    fixedHeader: true
                });
            });
            TableManageButtons.init();
        </script>

        <!-- dashbord linegraph -->
        <script>
            Chart.defaults.global.legend = {
                enabled: false
            };

            var data = {
                labels: [
                    "Symbian",
                    "Blackberry",
                    "Other",
                    "Android",
                    "IOS"
                ],
                datasets: [{
                    data: [15, 20, 30, 10, 30],
                    backgroundColor: [
                        "#BDC3C7",
                        "#9B59B6",
                        "#455C73",
                        "#26B99A",
                        "#3498DB"
                    ],
                    hoverBackgroundColor: [
                        "#CFD4D8",
                        "#B370CF",
                        "#34495E",
                        "#36CAAB",
                        "#49A9EA"
                    ]

                }]
            };

            var canvasDoughnut = new Chart(document.getElementById("canvas1"), {
                type: 'doughnut',
                tooltipFillColor: "rgba(51, 51, 51, 0.55)",
                data: data
            });
        </script>
        <!-- /dashbord linegraph -->
        <!-- datepicker -->
        <script type="text/javascript">
            $(document).ready(function() {

                var cb = function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                    //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
                }

                var optionSet1 = {
                    startDate: moment().subtract(29, 'days'),
                    endDate: moment(),
                    minDate: '01/01/2012',
                    maxDate: '12/31/2015',
                    dateLimit: {
                        days: 60
                    },
                    showDropdowns: true,
                    showWeekNumbers: true,
                    timePicker: false,
                    timePickerIncrement: 1,
                    timePicker12Hour: true,
                    ranges: {
                        'Today': [moment(), moment()],
                        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                        'This Month': [moment().startOf('month'), moment().endOf('month')],
                        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    },
                    opens: 'left',
                    buttonClasses: ['btn btn-default'],
                    applyClass: 'btn-small btn-primary',
                    cancelClass: 'btn-small',
                    format: 'MM/DD/YYYY',
                    separator: ' to ',
                    locale: {
                        applyLabel: 'Submit',
                        cancelLabel: 'Clear',
                        fromLabel: 'From',
                        toLabel: 'To',
                        customRangeLabel: 'Custom',
                        daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                        firstDay: 1
                    }
                };
                $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
                $('#reportrange').daterangepicker(optionSet1, cb);
                $('#reportrange').on('show.daterangepicker', function() {
                    console.log("show event fired");
                });
                $('#reportrange').on('hide.daterangepicker', function() {
                    console.log("hide event fired");
                });
                $('#reportrange').on('apply.daterangepicker', function(ev, picker) {
                    console.log("apply event fired, start/end dates are " + picker.startDate.format('MMMM D, YYYY') + " to " + picker.endDate.format('MMMM D, YYYY'));
                });
                $('#reportrange').on('cancel.daterangepicker', function(ev, picker) {
                    console.log("cancel event fired");
                });
                $('#options1').click(function() {
                    $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
                });
                $('#options2').click(function() {
                    $('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
                });
                $('#destroy').click(function() {
                    $('#reportrange').data('daterangepicker').remove();
                });
            });
        </script>
        <script>
            NProgress.done();
        </script>
        <!-- /datepicker -->
        <!-- input_mask -->
        <script>
            $(document).ready(function() {
                $(":input").inputmask();
            });
        </script>
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
        <!-- /input mask -->

<#--        <script type="text/javascript">-->
<#--            document.onreadystatechange = function(){-->
<#--                if(document.readyState === 'complete'){-->
<#--                    $.ajax({-->
<#--                        type: 'POST',-->
<#--                        url: '/onloadpage.php', //Страница обработчик ajax запроса, смотри в переменной POST параметры передаваемые в data-->
<#--                        async: false,-->
<#--                        data: {-->
<#--                            iduser: id //Сбда например ИД пользака закидываешь-->
<#--                        },-->
<#--                        success: function(data) {-->

<#--// alert(data);-->
<#--                            datajson = JSON.parse(data); //Получил в ответ строку в формате json и преобразовал её в js в формат массива-->
<#--                            $('#id_pole_1_div').html(datajson[0]); // id_pole_1_div - это id у div, Если у тебя input, то надо использовать вместо html - value(datajson[0]). Можно попробовать text(datajson[0]) Если этто не imput-->
<#--                            $('#id_pole_2_div').html(datajson[1]);-->
<#--                            $('#id_pole_3_div').html(datajson[2]);-->

<#--                        },-->
<#--                        error: function(xhr, str){-->
<#--                            alert('Возникла ошибка: ' + xhr.responseCode);-->
<#--                        }-->
<#--                    });-->
<#--                }-->
<#--            }-->
<#--        </script>-->

        <!-- /footer content -->
    </body>

</html>
</#macro>
