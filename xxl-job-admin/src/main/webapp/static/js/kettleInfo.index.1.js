$(function () {
    // init date tables
    var kettleTable = $("#kettle_list").dataTable({
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        "ajax": {
            url: base_url + "/jobinfo/pageList",
            type: "post",
            data: function (d) {
                var obj = {};
                obj.kettletype = $('#kettletype').val();
                obj.start = d.start;
                obj.length = d.length;
                return obj;
            }
        },
        "searching": false,
        "ordering": false,
        //"scrollX": true,	// X轴滚动条，取消自适应
        "columns": [
            {"data": 'id', "bSortable": false, "visible": false}
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

    // table data
    var tableData = {};

    // 搜索按钮
    $('#searchBtn').on('click', function () {
        jobTable.fnDraw();
    });

    // job operate
    $("#job_list").on('click', '.job_operate', function () {
        var typeName;
        var url;
        var needFresh = false;

        var type = $(this).attr("_type");
        if ("job_pause" == type) {
            typeName = "暂停";
            url = base_url + "/jobinfo/pause";
            needFresh = true;
        } else if ("job_resume" == type) {
            typeName = "恢复";
            url = base_url + "/jobinfo/resume";
            needFresh = true;
        } else if ("job_del" == type) {
            typeName = "删除";
            url = base_url + "/jobinfo/remove";
            needFresh = true;
        } else if ("job_trigger" == type) {
            typeName = "执行";
            url = base_url + "/jobinfo/trigger";
        } else {
            return;
        }

        var id = $(this).parent('p').attr("id");

        layer.confirm('确认' + typeName + '?', {icon: 3, title: '系统提示'}, function (index) {
            layer.close(index);

            $.ajax({
                type: 'POST',
                url: url,
                data: {
                    "id": id
                },
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {

                        layer.open({
                            title: '系统提示',
                            content: typeName + "成功",
                            icon: '1',
                            end: function (layero, index) {
                                if (needFresh) {
                                    //window.location.reload();
                                    jobTable.fnDraw();
                                }
                            }
                        });
                    } else {
                        layer.open({
                            title: '系统提示',
                            content: (data.msg || typeName + "失败"),
                            icon: '2'
                        });
                    }
                },
            });
        });
    });

    // jquery.validate 自定义校验 “英文字母开头，只含有英文字母、数字和下划线”
    jQuery.validator.addMethod("myValid01", function (value, element) {
        var length = value.length;
        var valid = /^[a-zA-Z][a-zA-Z0-9_]*$/;
        return this.optional(element) || valid.test(value);
    }, "只支持英文字母开头，只含有英文字母、数字和下划线");

    // 新增
    $(".add").click(function () {
        $('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
    });
    var addModalValidate = $("#addModal .form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: true,
        rules: {
            jobDesc: {
                required: true,
                maxlength: 50
            },
            jobCron: {
                required: true
            },
            author: {
                required: true
            }
        },
        messages: {
            jobDesc: {
                required: "请输入“描述”."
            },
            jobCron: {
                required: "请输入“Cron”."
            },
            author: {
                required: "请输入“负责人”."
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (form) {
            $.post(base_url + "/jobinfo/add", $("#addModal .form").serialize(), function (data, status) {
                if (data.code == "200") {
                    $('#addModal').modal('hide');
                    layer.open({
                        title: '系统提示',
                        content: '新增任务成功',
                        icon: '1',
                        end: function (layero, index) {
                            jobTable.fnDraw();
                            //window.location.reload();
                        }
                    });
                } else {
                    layer.open({
                        title: '系统提示',
                        content: (data.msg || "新增失败"),
                        icon: '2'
                    });
                }
            });
        }
    });
    $("#addModal").on('hide.bs.modal', function () {
        $("#addModal .form")[0].reset();
        addModalValidate.resetForm();
        $("#addModal .form .form-group").removeClass("has-error");
        $(".remote_panel").show();	// remote

        $("#addModal .form input[name='executorHandler']").removeAttr("readonly");
    });

    // 更新
    $("#job_list").on('click', '.update', function () {

        var id = $(this).parent('p').attr("id");
        var row = tableData['key' + id];
        if (!row) {
            layer.open({
                title: '系统提示',
                content: ("任务信息加载失败，请刷新页面"),
                icon: '2'
            });
            return;
        }

        // base data
        $("#updateModal .form input[name='id']").val(row.id);
        $('#updateModal .form select[name=jobGroup] option[value=' + row.jobGroup + ']').prop('selected', true);
        $("#updateModal .form input[name='jobDesc']").val(row.jobDesc);
        $("#updateModal .form input[name='jobCron']").val(row.jobCron);
        $("#updateModal .form input[name='author']").val(row.author);
        $("#updateModal .form input[name='alarmEmail']").val(row.alarmEmail);
        $('#updateModal .form select[name=executorRouteStrategy] option[value=' + row.executorRouteStrategy + ']').prop('selected', true);
        $("#updateModal .form input[name='executorHandler']").val(row.executorHandler);
        $("#updateModal .form input[name='executorParam']").val(row.executorParam);
        $("#updateModal .form input[name='childJobKey']").val(row.childJobKey);
        $('#updateModal .form select[name=executorBlockStrategy] option[value=' + row.executorBlockStrategy + ']').prop('selected', true);
        $('#updateModal .form select[name=executorFailStrategy] option[value=' + row.executorFailStrategy + ']').prop('selected', true);
        $('#updateModal .form select[name=glueType] option[value=' + row.glueType + ']').prop('selected', true);

        $("#updateModal .form select[name=glueType]").change();

        // show
        $('#updateModal').modal({backdrop: false, keyboard: false}).modal('show');
    });
    var updateModalValidate = $("#updateModal .form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: true,

        rules: {
            jobDesc: {
                required: true,
                maxlength: 50
            },
            jobCron: {
                required: true
            },
            author: {
                required: true
            }
        },
        messages: {
            jobDesc: {
                required: "请输入“描述”."
            },
            jobCron: {
                required: "请输入“Cron”."
            },
            author: {
                required: "请输入“负责人”."
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (form) {
            // post
            $.post(base_url + "/jobinfo/reschedule", $("#updateModal .form").serialize(), function (data, status) {
                if (data.code == "200") {
                    $('#updateModal').modal('hide');
                    layer.open({
                        title: '系统提示',
                        content: '更新成功',
                        icon: '1',
                        end: function (layero, index) {
                            //window.location.reload();
                            jobTable.fnDraw();
                        }
                    });
                } else {
                    layer.open({
                        title: '系统提示',
                        content: (data.msg || "更新失败"),
                        icon: '2'
                    });
                }
            });
        }
    });
    $("#updateModal").on('hide.bs.modal', function () {
        $("#updateModal .form")[0].reset()
    });

});
