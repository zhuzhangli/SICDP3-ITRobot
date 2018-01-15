<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>展示事件信息</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    <script type="text/javascript" src="js/classify.js"></script>

</head>

<body class="gray-bg">
<#list resolvedEventDetail as a>
    <div class="wrapper wrapper-content animated fadeInRight">      
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>事件信息 </h5>                        
                    </div>
                    
                    
					<div class="ibox-content" style="display:block;padding-bottom: 50px" id="subinfo" >
                      <form method="post" class="form-horizontal" >
                       <div class="form-group">
                           <label class="col-sm-2 control-label">问题标题</label>
                           <div class="col-sm-10">
                               <p class="form-control-static" >${a.QUESTIONTITLE}</p>
                           </div>
                       </div>
                       <div class="hr-line-dashed"></div>

                       <div class="form-group">
                           <label class="col-sm-2 control-label">提问用户</label>

                           <div class="col-sm-10">
                               <p class="form-control-static">${a.USERNAME}</p>
                           </div>
                       </div>
                       <div class="hr-line-dashed"></div>
                       
                       <div class="form-group">
                           <label class="col-sm-2 control-label">提问时间</label>

                           <div class="col-sm-10">
                               <p class="form-control-static">${a.QUESTIONTIME}</p>
                           </div>
                       </div>
                       <div class="hr-line-dashed"></div>
                        
                       <div class="form-group">
                           <label class="col-sm-2 control-label">问题答案</label>

                           <div class="col-sm-10">
                               <p class="form-control-static" >${a.FAQANSWER}</p>
                           </div>
                       </div>
                       <div class="hr-line-dashed"></div>

                       </form>
                   		<div class="col-sm-4 col-sm-offset-2">
                       	   <button class="btn btn-primary" onclick="window.history.back(-1)">返回</button>
                           <input class="btn btn-primary" value="添加至知识库" id="update_" type="submit">
                       </div>
                   </div>
                                       
                   <!-- 添加至知识库编辑页 -->                  
                   <div class="ibox-content"  style="display:none;padding-bottom: 50px" id="updateinfo">
                      <form method="" class="form-horizontal"  action="">
                          <div class="form-group" style="display: none;">
                              <div class="col-sm-10">
                                  <input type="text" name="UserName" class="form-control"  required="required" value="${a.USERQUESTIONID}" id="questionId" readonly="readonly">
                              </div>
                          </div>
                          
                          
                          <div class="form-group">
                              <span class="col-sm-2 control-label">问题标题</span>

                              <div class="col-sm-10">
                                  <input type="text"  class="form-control"  aria-required="true"  required="required" value="${a.QUESTIONTITLE}" id="title" style="width: 80%;float: left;">
                              	  <div class="validate_faqadd spa1"></div>
                              </div>
                          </div>
                          <div class="hr-line-dashed"></div>
                   
                          
                          <div class="form-group">
                              <label class="col-sm-2 control-label">关键字</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="keywords"  placeholder="在输入多个关键词时请使用半角逗号间隔" style="width: 80%;float: left;" >
                              	  <div class="validate_faqadd spa3"></div>
                              </div>
                          </div>
                          <div class="hr-line-dashed "></div>
                          
                          <div class="form-group">
                              <label class="col-sm-2 control-label">分类</label>

                              <div class="col-sm-10">
                                  <select class="select" id="specialCategoryId" onchange="selectSecondChild()" style="height: 35px;width: 180px"></select>
                 			 	  <select class="select" id="subspecialCategoryId" name="classifyName" style="height: 35px;width: 180px"></select>
                 			 	  <div class="validate_faqadd spa4"></div>
                              </div>
                          </div>
                          <div class="hr-line-dashed"></div>
                                                   
                          <div class="form-group">
                              <label class="col-sm-2 control-label">摘要说明</label>
                              <div class="col-sm-10">
                                  <textarea class="form-control" id="description" style="width: 80%;height: 60px;float: left;" ></textarea>
                             	  <div class="validate_faqadd spa5"></div>                           
                              </div>
                          </div>
                          <div class="hr-line-dashed"></div>
                                                                                                  
                          <div class="form-group">
                              <label class="col-sm-2 control-label">问题答案</label>

                              <div class="col-sm-10" id="faqcontent">
                                  ${a.FAQANSWER}
                              </div>
                          </div>
                          <div class="hr-line-dashed"></div>    
                      </form>
                      
                      <div class="col-sm-4 col-sm-offset-2" id="${a.USERQUESTIONID }">
                           <button class="btn btn-primary" onclick="window.history.back(-1)">返回</button>
                           <input  class="btn btn-primary" value="完成" id="sub" type="submit">
                       </div>
                  </div>               
               </div>
            </div>
        </div>
    </div>
 </#list>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

	<!-- jQuery Validation plugin javascript-->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/validate/messages_zh.min.js"></script>


    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>   
    <script src="js/view/showResolvedEvent.js"></script>
    <script src="js/validation.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
        
        
    </script>
	<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">操作成功</h2></div></div>
  <div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">重复提交</h2></div></div> 
</body>

</html>
