<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>问题管理</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	
	<link rel="shortcut icon" href="favicon.ico">
	<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
	<link href="css/animate.css" rel="stylesheet">
	<link href="css/style.css?v=4.1.0" rel="stylesheet">
	<!-- jqgrid-->
	<link href="css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	
	<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	
	<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
	
	<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	
	<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />
	
	<link href="media/css/style.css" rel="stylesheet" type="text/css" />
	
	<link href="media/css/style-responsive.css" rel="stylesheet" type="text/css" />
	
	<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css" />
	
	<!-- END GLOBAL MANDATORY STYLES -->
	
	<!-- BEGIN PAGE LEVEL STYLES -->
	
	<link rel="stylesheet" type="text/css" href="media/css/select2_metro.css" />
	
	<link rel="stylesheet" href="media/css/DT_bootstrap.css" />
	
	<!-- END PAGE LEVEL STYLES -->
	
	<link rel="shortcut icon" href="media/image/favicon.ico" />
	
	<!-- echarts JS -->
	<script src="media/js/echarts.js"></script>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row" style="height: 100%">
			<div class="col-sm-6" style="width: 100%; height: 100%">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">问题管理</a></li>
					</ul>
					<div class="tab-content">
						<div id="tab-1" class="tab-pane active">
							<!DOCTYPE html>
							<html>

							<head>
							
							<meta charset="utf-8">
							<meta name="viewport" content="width=device-width, initial-scale=1.0">
							
							<!--360浏览器优先以webkit内核解析-->
							
							
							<title>问题首页</title>
							
							<link rel="shortcut icon" href="favicon.ico">
							<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
							<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
							<link href="css/animate.css" rel="stylesheet">
							<link href="css/style.css?v=4.1.0" rel="stylesheet">
							
							</head>

							<body class="gray-bg">
								<div class="wrapper wrapper-content animated fadeIn">

									<div class="row">
										<div class="col-sm-6" style="width: 100%">
											<div class="tabs-container">
												<ul class="nav nav-tabs">
													<li class="active"><a data-toggle="tab" href="#tab-31"
														aria-expanded="true">待处理问题【${ unResolvedCounts}】</a></li>
													<li class=""><a data-toggle="tab" href="#tab-32"
														aria-expanded="false">已处理问题【${resolvedCounts }】</a></li>

												</ul>
												<div class="tab-content">

													<div id="tab-31" class="tab-pane active">
														<div class="ibox-content">

															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">
																	<div class="caption">
																		<i class="icon-edit"></i>待处理问题列表
																	</div>

																</div>

																<div class="portlet-body">
																	<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
																		<thead>
																			<tr>
																				<th>问题编号</th>
																				<th>问题标题</th>
																				<th>内容</th>
																				<th>问题分类</th>
																				<th>提问用户</th>
																				<th>提问时间</th>
																				<th>查看详情</th>																				
																			</tr>
																		</thead>

																		<tbody>
																			<#list problemUnresolved as a>
																			<tr class="" id = "${a.problemId}">
																				<td style="width: 6%;">${a_index+1}</td>
																				<td style="width: 27%;">${a.problemTitle}</td>
																				<td style="width: 27%;">${a.problemContent}</td>
																				<td style="width: 10%;" class="center">${a.problemClassifyName}</td>
																				<td style="width: 10%;" class="center">${a.problemUserName}</td>														
																				<td style="width: 10%;">${a.problemTime}</td>
																				<td style="width: 10%;"><a class="faq"
																					href="/org.xjtusicd3.portal/showUnResolvedProblem.html?p=${a.problemId}">查看问题详情</a></td>
																			</tr>
																			</#list>
																		</tbody>
																	</table>
																</div>
															</div>
															<!-- END EXAMPLE TABLE PORTLET-->
														</div>
													</div>

													<div id="tab-32" class="tab-pane">
														<div class="ibox-content">
															<!-- BEGIN EXAMPLE TABLE PORTLET-->
															<div class="portlet box blue">
																<div class="portlet-title">
																	<div class="caption">
																		<i class="icon-edit"></i>已处理问题列表
																	</div>
																</div>

																<div class="portlet-body">
																	<table class="table table-striped table-hover table-bordered" id="sample_editable_2">
																		<thead>
																			<tr>
																				<th>序号</th>
																				<th>问题标题</th>
																				<th>问题内容</th>
																				<th>分类名</th>
																				<th>提问者</th>
																				<th>提问时间</th>
																				<th>回复者</th>																				
																				<th>回复内容</th>
																				<th>查看详情</th>
																			</tr>
																		</thead>

																		<tbody>
																			<#list problemResolved as b>
																			<tr class="" id = "${b.problemId}">
																				<td style="width: 5%;">${b_index+1}</td>
																				<td style="width: 20%;">${b.problemTitle}</td>
																				<td style="width: 20%;">${b.problemContent}</td>
																				<td style="width: 7%;" class="center">${b.problemClassifyName}</td>
																				<td style="width: 7%;" class="center">${b.problemUserName}</td>
																				<td style="width: 7%;" class="center">${b.problemTime}</td>
																				<td style="width: 7%;" class="center">${b.answerUserName}</td>
																				<td style="width: 20%;">${b.answerContent }</td>
																				<td style="width: 7%;"><a class="faq" 
																					href="/org.xjtusicd3.portal/showResolvedProblem.html?p=${b.problemId}">查看问题详情</a></td>
																			</tr>
																			</#list>
																		</tbody>
																	</table>
																</div>
															</div>
															<!-- END EXAMPLE TABLE PORTLET-->
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- 全局js -->
										<script src="js/jquery.min.js?v=2.1.4"></script>
										<script src="js/bootstrap.min.js?v=3.3.6"></script>

										<!-- 自定义js -->
										<script src="js/content.js?v=1.0.0"></script>
									</body>
										
</html>

</div>


</div>
</div>
</div>
</div>
</div>
<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>

<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>

<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>


<!-- BEGIN CORE PLUGINS -->

<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="media/js/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="media/js/jquery-ui-1.10.1.custom.min.js"
	type="text/javascript"></script>

<script src="media/js/bootstrap.min.js" type="text/javascript"></script>

<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="media/js/select2.min.js"></script>

<script type="text/javascript" src="media/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="media/js/DT_bootstrap.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="media/js/app.js"></script>

<script src="media/js/table-editable.js"></script>
<script src="media/js/table-editable2.js"></script>
<script>
	jQuery(document).ready(function() {

		App.init();

		TableEditable.init();
		TableEditable2.init();

	});
</script>

<script type="text/javascript">
	function addProblemtoFAQ() {
		var problemID = event.target.parentNode.parentNode.id;
		alert(problemID)
		var present_row = event.target.parentNode.parentNode;
		if (confirm("确认添加至FAQ？")) {
			$.ajax({
				type : "post",
				url : "/org.xjtusicd3.portal/addProblemToFAQ.html",
				data : {
					"problemID" : problemID
				},
				dataType : "json",
				success : function(data) {
					alert("添加成功");
					present_row.remove();
				}
			});
		} else {
			return;
		}

	}
</script>


</body>

</html>
