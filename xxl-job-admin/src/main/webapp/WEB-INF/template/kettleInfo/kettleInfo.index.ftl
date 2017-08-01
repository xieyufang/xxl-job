<!DOCTYPE html>
<html>
<head>
  	<title>任务调度中心</title>
  	<#import "/common/common.macro.ftl" as netCommon>
	<@netCommon.commonStyle />
	<!-- DataTables -->
  	<link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/Select/css/select.bootstrap.min.css">

    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/fileInput/css/fileinput.min.css">
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/fileInput/css/fileinput-rtl.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["adminlte_settings"].value >sidebar-collapse</#if>">
<div class="wrapper">
	<!-- header -->
	<@netCommon.commonHeader />
	<!-- left -->
	<@netCommon.commonLeft "kettleInfo" />
	
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Kettle管理<small>任务调度中心</small></h1>
			<!--
			<ol class="breadcrumb">
				<li><a><i class="fa fa-dashboard"></i>调度管理</a></li>
				<li class="active">调度中心</li>
			</ol>
			-->
		</section>
		
		<!-- Main content -->
	    <section class="content">
	    
	    	<div class="row">
	    		<div class="col-xs-2">
	              	<div class="input-group">
	                	<span class="input-group-addon">类型</span>
                		<select class="form-control" id="kettleType" >
                            <option value="" >全部</option>
							<option value="KETTLE_TRANS" >转换</option>
                            <option value="KETTLE_JOB" >作业</option>
	                  	</select>
	              	</div>
	            </div>

                <div class="col-xs-2">
                    <div class="input-group">
                        <span class="input-group-addon">状态</span>
                        <select class="form-control" id="kettleStatus" >
                            <option value="" >全部</option>
                            <option value="KETTLE_PRODUCT" >产品</option>
                            <option value="KETTLE_DRAFT" >草案</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="kettleName" autocomplete="on" >
                    </div>
                </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-info" id="searchBtn">搜索</button>
	            </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-success add" type="button">+新增</button>
	            </div>
          	</div>
	    	
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
			            <div class="box-header">
			            	<h3 class="box-title">Kettle列表</h3>
			            </div>
			            <div class="box-body" >
			              	<table id="kettle_list" class="table table-bordered table-striped">
				                <thead>
					            	<tr>
					            		<th name="id">id</th>
					                	<th name="name">名称</th>
										<th name="path">路径</th>
					                  	<th name="description">描述</th>
                                        <th name="status" >状态</th>
                                        <th name="type" >类型</th>
					                  	<th name="createdDate" >新增时间</th>
					                  	<th name="modifiedDate" >更新时间</th>
					                  	<th>操作</th>
					                </tr>
				                </thead>
				                <tbody></tbody>
				                <tfoot></tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
	    </section>
	</div>
	
	<!-- footer -->
	<@netCommon.commonFooter />
</div>

<!-- kettle新增.模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"  aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
            	<h4 class="modal-title" >新增任务</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">路径<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="path" placeholder="请输入“文件路径”" maxlength="20" >
                        </div>

                        <label for="lastname" class="col-sm-2 control-label">状态<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="kettleStatus" >
                                <option value="KETTLE_DRAFT" selected>草案</option>
                                <option value="KETTLE_PRODUCT" >产品</option>
                            </select>
						</div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">描述<font color="red">*</font></label>
						<div class="col-sm-10"><textarea rows="3" cols="20" class="form-control" name="description" maxlength="255" ></textarea></div>
                    </div>
					</br>
                    </br>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label for="firstname" class="col-sm-2 control-label">上传文件<font color="red">*</font></label> <hr>
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-sm-offset-1 col-sm-11">

                            <input id ="input-file" type="file" name="file" class="file-loading" data-show-upload="false" data-show-preview="false">
							<input id ="file-name" type="hidden" name="fileName">
                            <input id ="file-name-temp" type="hidden" name="fileNameTemp">
                            <input id ="kettle-type" type="hidden" name="kettleType">
                        </div>
                    </div>
                    <hr>
					<div class="form-group">
						<div class="col-sm-offset-5">
							<button type="submit" class="btn btn-primary"  >保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
					
				</form>
         	</div>

		</div>
	</div>

</div>

<!-- 更新.模态框 -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"  aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
            	<h4 class="modal-title" >更新任务</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">执行器<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="jobGroup" disabled >
							<#list JobGroupList as group>
                                <option value="${group.id}" >${group.title}</option>
							</#list>
                            </select>
                        </div>
                        <label for="lastname" class="col-sm-2 control-label">任务描述<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="jobDesc" placeholder="请输入“描述”" maxlength="50" ></div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">路由策略<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="executorRouteStrategy" >
							<#list ExecutorRouteStrategyEnum as item>
                                <option value="${item}" >${item.title}</option>
							</#list>
                            </select>
                        </div>
                        <label for="lastname" class="col-sm-2 control-label">Cron<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="jobCron" placeholder="请输入“Cron”" maxlength="20" ></div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">运行模式<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <select class="form-control glueType" name="glueType" disabled >
							<#list GlueTypeEnum as item>
                                <option value="${item}" >${item.desc}</option>
							</#list>
                            </select>
                        </div>
                        <label for="firstname" class="col-sm-2 control-label">JobHandler<font color="black">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="executorHandler" placeholder="请输入“JobHandler”" maxlength="100" ></div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">执行参数<font color="black">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="executorParam" placeholder="请输入“执行参数”" maxlength="100" ></div>
                        <label for="lastname" class="col-sm-2 control-label">子任务Key<font color="black">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="childJobKey" placeholder="请输入子任务的任务Key,如存在多个逗号分隔" maxlength="100" ></div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">阻塞处理策略<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="executorBlockStrategy" >
							<#list ExecutorBlockStrategyEnum as item>
                                <option value="${item}" >${item.title}</option>
							</#list>
                            </select>
                        </div>
                        <label for="lastname" class="col-sm-2 control-label">失败处理策略<font color="red">*</font></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="executorFailStrategy" >
							<#list ExecutorFailStrategyEnum as item>
                                <option value="${item}" >${item.title}</option>
							</#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">负责人<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="author" placeholder="请输入“负责人”" maxlength="50" ></div>
                        <label for="lastname" class="col-sm-2 control-label">报警邮件<font color="black">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="alarmEmail" placeholder="请输入“报警邮件”，多个邮件地址逗号分隔" maxlength="100" ></div>
                    </div>

					<hr>
					<div class="form-group">
                        <div class="col-sm-offset-4 col-sm-6">
							<button type="submit" class="btn btn-primary"  >保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <input type="hidden" name="id" >
						</div>
					</div>
				</form>
         	</div>
		</div>
	</div>
</div>

<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/Select/js/dataTables.select.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>

<!-- moment -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/js/kettleInfo.index.1.js"></script>

<#-- file input -->
<script src="${request.contextPath}/static/adminlte/plugins/fileInput/js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/adminlte/plugins/fileInput/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/adminlte/plugins/fileInput/js/fileinput.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/fileInput/js/locales/zh.js"></script>
</body>
</html>
