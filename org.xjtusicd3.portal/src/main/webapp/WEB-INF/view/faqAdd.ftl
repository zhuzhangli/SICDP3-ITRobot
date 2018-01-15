<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>增加用户信息</title> 
  <meta name="keywords" content="" /> 
  <meta name="description" content="" /> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <link href="css/plugins/iCheck/custom.css" rel="stylesheet" /> 
  <link href="css/animate.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
  <link rel="stylesheet" type="text/css" href="css/plugins/markdown/bootstrap-markdown.min.css" /> 
  <script type="text/javascript" charset ="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript" charset="utf-8" src="js/ueditor.js"></script>
  <link rel="stylesheet" type="text/css" href="js/normalize.css" />
 
 
 </head> 
 <body class="gray-bg"> 
  <div class="wrapper wrapper-content animated fadeInRight"> 
   <div class="row"> 
    <div class="col-sm-12"> 
     <div class="ibox float-e-margins"> 
      <div class="ibox-title"> 
       <h5>新增FAQ <small> </small></h5> 
       
       <div class="ibox-tools"> 
        <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
        <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#"> <i class="fa fa-wrench"></i> </a> 
        <ul class="dropdown-menu dropdown-user"> 
         <li><a href="form_basic.html#">选项1</a> </li> 
         <li><a href="form_basic.html#">选项2</a> </li> 
        </ul> 
        <a class="close-link"> <i class="fa fa-times"></i> </a> 
       </div> 
     
      </div> 
      
      <div class="form-horizontal"> 
 
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label"><em>*</em>标题：</label> 
         <div class="col-sm-10"> 
          <!-- <input id="title"  name="title" minlength="2" type="text" class="form-control" required="" aria-required="true" placeholder="请输入知识标题" /> --> 
          	<input type="text" class="form-control" id="title" placeholder="请输入知识标题" style="width:  69%;float: left;">
            <div class="validate_faqadd spa1"></div>
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">关键字：</label> 
         <div class="col-sm-10"> 
          <!-- <input type="text" minlength="1" class="form-control" id="keywords"  name="keywords" required="" aria-required="true" placeholder="在输入关键词时请使用半角逗号间隔" />  -->
         <input type="text" class="form-control" id="keywords" placeholder="在输入关键词时请使用半角逗号间隔" style="width:  69%;float: left;">
                            <div class="validate_faqadd spa3"></div>
         
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">分类：</label> 
         <div class="col-sm-10"> 
          <!--获取一级分类  --> 
          <select class="select" id="specialCategoryId" onchange="selectSecondChild()" style="height: 35px;width: 180px"  name="specialCategoryId"></select> 
          <!--获取二级分类  --> 
           <select class="select" id="subspecialCategoryId" name="subspecialCategoryId" style="height: 35px;width: 180px" ></select> 
  			<div class="validate_faqadd spa4" ></div>
         </div> 
        </div> 
  
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label"><em>*</em>摘要说明：</label> 
         <div class="col-sm-10"> 
          <!-- <textarea id="description" name="description" class="form-control" required="" aria-required="true"></textarea>  -->
          <textarea class="form-control" id="description" style="width: 69%;height: 60px;float: left;"></textarea>
                            <div class="validate_faqadd spa5"></div> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group" style="width: 80%;border: none;"> 
         <label class="col-sm-2 control-label"><em>*</em>知识正文：</label> 
         <div class="col-sm-10"> 
          <div class="ibox float-e-margins" style="width: 80%;float: left;border: none;"> 
           <div class="ibox-content" style="width:100%;height:100%;"> 
            <script id="editor" type="text/plain" style="width:100%;height:100%;"></script>
           </div> 
          </div>
          <div class="validate_faqadd spa6"></div>  
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group"> 
         <div class="col-sm-4 col-sm-offset-2"> 
          <button class="btn btn-primary" type="submit" id="sub" data-dialog="somedialog">保存内容</button> 
          <button class="btn btn-primary" type="submit" onclick="window.history.back(-1)">取消</button> 
         </div> 
        </div> 
       
     
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
  <!-- simditor --> 
  <script type="text/javascript" src="js/plugins/markdown/markdown.js"></script> 
  <script type="text/javascript" src="js/plugins/markdown/to-markdown.js"></script> 
  <script type="text/javascript" src="js/plugins/markdown/bootstrap-markdown.js"></script> 
  <script type="text/javascript" src="js/plugins/markdown/bootstrap-markdown.zh.js"></script> 
  <!-- iCheck --> 
  <script src="js/plugins/iCheck/icheck.min.js"></script> 
  <script src="js/classify.js"></script> 
  <div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">操作成功</h2></div></div>
  <div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">重复提交</h2></div></div> 
 </body>
</html>