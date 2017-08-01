$(function () {
    // init date tables
    var kettleTable = $("#kettle_list").DataTable({
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        "ajax": {
            url: base_url + "/kettleInfo/pageList",
            type: "post",
            data: function (d) {
                var obj = {};
                obj.name = $('#kettleName').val();
                obj.type = $('#kettleType').val();
                obj.status = $('#kettleStatus').val();
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
            {"data": 'fileName', "visible":true,"width":"15%"},
            {"data": 'path', "visible": true,"width":"15%"},
            {"data": 'description', "visible": true,"width":"30%"},
            {"data": 'kettleStatus',
                "visible": true,
                "width":"10%",
                "render": function (data, type, row) {
                    if ('KETTLE_PRODUCT' == row.status) {
                        return "产品";
                    } else if ('KETTLE_DRAFT' == row.status) {
                        return "草案";
                    }
                    return "草案";
                }
            },
            {"data": 'kettleType', "visible": false},
            {"data": 'createdDate', "visible": false},
            {"data": 'modifiedDate', "visible": false},
            {"data":"操作",
                "width":"15%",
                "render": function (data, type, row) {
                    return function () {
                        // log url
                        var logUrl = base_url + '/kettleLog?id=' + row.id;
                        var html = '<p id="' + row.id + '" >' +
                            '<button class="btn btn-primary btn-xs" type="job_del" type="button" onclick="javascript:window.open(\'' + logUrl + '\')" >日志</button> ' +
                            '<button class="btn btn-warning btn-xs update" type="button">编辑</button> '+
                            '<button class="btn btn-danger  btn-xs job_operate" _type="kettle_del" type="button">删除</button>' +
                            '</p>';

                        return html;
                    };
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
        kettleTable.ajax.reload();
    });

    $("#kettleStatus").on("change",function () {
        kettleTable.ajax.reload();
    });
    
    $("#searchBtn").on("click",function () {
        kettleTable.ajax.reload();
    })

    $(".add").on("click",function () {
        $('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
    });

    $("#input-file").fileinput({
        rtl: true,
        language:'zh',
        allowedFileExtensions: ["ktr", "kjb"],
        uploadUrl:base_url+"/kettleInfo/upload",
        uploadAsync: true
    }).on("fileuploaded",function (event, data, id, index) {

        if(data.response.code == 200){


            $("#file-name-temp").val(data.response.content);

            var file = $("#input-file").val();
            $("#file-name").val(file.substring(file.lastIndexOf("\\") + 1));
            if($("#file-name").val().indexOf("ktr")>0){
                $("#kettle-type").val("KETTLE_TRANS")
            }else if($("#file-name").val().indexOf("kjb")>0){
                $("#kettle-type").val("KETTLE_JOB")
            }else{
                $("#file-name").val("");
            }
        }

    }).on("filebatchselected", function(event, files){
        $("#input-file").fileinput("upload");
    });


    var addModalValidate = $("#addModal .form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: true,
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: "上传文件"
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
            $.post(base_url + "/kettleInfo/add", $("#addModal .form").serialize(), function (data, status) {
                if (data.code == "200") {
                    $('#addModal').modal('hide');
                    layer.open({
                        title: '系统提示',
                        content: '新增Kettle成功',
                        icon: '1',
                        end: function (layero, index) {
                            kettleTable.ajax.reload();
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

    });
});
