<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>展示用户信息</title> 
  <meta name="keywords" content="" /> 
  <meta name="description" content="" /> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <link href="css/plugins/iCheck/custom.css" rel="stylesheet" /> 
  <link href="css/animate.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
 </head> 
 <body class="gray-bg">
  <#list userInfo as a> 
  <div class="wrapper wrapper-content animated fadeInRight"> 
   <div class="row"> 
    <div class="col-sm-12"> 
     <div class="ibox float-e-margins"> 
      <div class="ibox-title"> 
       <h5>用户信息 </h5> 
      </div> 
      
      <div class="ibox-content"> 
       <form method="get" class="form-horizontal"> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">用户名：</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.USERNAME}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">用户邮箱:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.USEREMAIL}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">性别:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.GENDER}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">出生日期:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.USERBIRTHDAY}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">地址:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.USERADDRESS}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">用户签名:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.USERSIGNATURE}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">注册时间:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.CREATETIME}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <label class="col-sm-2 control-label">角色名称:</label> 
         <div class="col-sm-10"> 
          <p class="form-control-static">${a.ROLENAME}</p> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group"> 
         <div class="col-sm-4 col-sm-offset-2"> 
          <input type="button" name="Submit" class="btn btn-primary" onclick="javascript:history.back(-1);" value="返回">
         </div> 
        </div> 
       </form> 
      </div> 
     </div> 
    </div> 
   </div> 
  </div> 
  </#list>
  
  <!--#list--> 
  <!-- 全局js --> 
  <script src="js/jquery.min.js?v=2.1.4"></script> 
  <script src="js/bootstrap.min.js?v=3.3.6"></script> 
  <!-- 自定义js --> 
  <script src="js/content.js?v=1.0.0"></script> 
  <!-- iCheck --> 
  <script src="js/plugins/iCheck/icheck.min.js"></script> 
  <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>   
 </body>
</html>