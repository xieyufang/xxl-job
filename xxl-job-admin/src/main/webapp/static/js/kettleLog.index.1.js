$(function () {

    // 过滤时间
    $('#filterTime').daterangepicker({
        autoApply:false,
        singleDatePicker:false,
        showDropdowns:false,        // 是否显示年月选择条件
        timePicker: true, 			// 是否显示小时和分钟选择条件
        timePickerIncrement: 10, 	// 时间的增量，单位为分钟
        timePicker24Hour : true,
        opens : 'left', //日期选择框的弹出位置
        ranges: {
            '最近1小时': [moment().subtract(1, 'hours'), moment()],
            '今日': [moment().startOf('day'), moment().endOf('day')],
            '昨日': [moment().subtract(1, 'days').startOf('day'), moment().subtract(1, 'days').endOf('day')],
            '最近7日': [moment().subtract(6, 'days'), moment()],
            '最近30日': [moment().subtract(29, 'days'), moment()],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        locale : {
            format: 'YYYY-MM-DD HH:mm:ss',
            separator : ' - ',
            customRangeLabel : '自定义',
            applyLabel : '确定',
            cancelLabel : '取消',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
            firstDay : 1,
            startDate: moment().startOf('day'),
            endDate: moment().endOf('day')
        }
    });

    // init date tables
    var kettleLogTable = $("#kettleLog_list").DataTable({
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        "ajax": {
            url: base_url + "/kettleLog/pageList",
            type: "post",
            data: function (d) {
                var obj = {};
                obj.name = $('#kettleName').val();
                obj.type = $('#kettleType').val();
                obj.status = $('#kettleLogStatus').val();
                obj.filterTime = $('#filterTime').val();
                obj.start = d.start;
                obj.length = d.length;
                return obj;
            }
        },
        "searching": false,
        "ordering": false,
        //"scrollX": true,	// X轴滚动条，取消自适应
        "columns": [
            {"data": 'id', "visible": false},
            {"data": 'kettleInfo.id', "visible": false},
            {"data": 'kettleInfo.name', "visible": true, "width": "20%"},
            {"data": 'kettleInfo.description', "visible": true, "width": "30%"},
            {"data": 'errors', "visible": true, "width": "10%"},
            {"data": 'status', "visible": true, "width": "10%"},
            {
                "data": 'startDate',
                "visible": true,
                "width": "10%",
                "render": function (data, type, row) {
                    return data ? moment(new Date(data)).format("YYYY-MM-DD HH:mm:ss") : "";
                }
            },
            {
                "data": 'endDate',
                "visible": true,
                "width": "10%",
                "render": function (data, type, row) {
                    return data ? moment(new Date(data)).format("YYYY-MM-DD HH:mm:ss") : "";
                }
            },
            {
                "data": 'handleMsg', "visible": true,
                "width": "10%",
                "render": function (data, type, row) {
                    // better support expression or string, not function
                    return function () {
                        return '<a href="javascript:;" class="logField" _id="' + row.id + '">日志详情</a>';
                    }
                }
            }
        ],
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 条记录",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_ 条记录 )",
            "sInfoEmpty": "无记录",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });

    $("#kettleType").on("change",function () {
        kettleLogTable.fnDraw();
    });

    $("#kettleLogStatus").on("change",function () {
        kettleLogTable.fnDraw();
    });

    $("#searchBtn").on("click",function () {
        kettleLogTable.fnDraw();
    });

});