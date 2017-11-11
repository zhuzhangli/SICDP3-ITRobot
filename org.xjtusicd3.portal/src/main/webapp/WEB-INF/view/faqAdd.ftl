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
      
      <div class="ibox-content"> 
       <form method="get" class="form-horizontal" action=""> 
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">标题：</label> 
         <div class="col-sm-10"> 
          <input id="title"  name="title" minlength="2" type="text" class="form-control" required="" aria-required="true" placeholder="请输入知识标题" /> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">关键字：</label> 
         <div class="col-sm-10"> 
          <input type="text" minlength="1" class="form-control" id="keywords"  name="keywords" required="" aria-required="true" placeholder="在输入关键词时请使用半角逗号间隔" /> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">分类：</label> 
         <div class="col-sm-10"> 
          <!--获取一级分类  --> 
          <select class="form-control m-b" id="specialCategoryId" onchange="selectSecondChild()" required="" style="height: 45%"  name="specialCategoryId"></select> 
          <!--获取二级分类  --> 
          <div class="col-sm-4 m-l-n"> 
           <select class="form-control" id="subspecialCategoryId" name="subspecialCategoryId" required="" style="height: 45%" ></select> 
          </div> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">摘要说明：</label> 
         <div class="col-sm-10"> 
          <textarea id="description" name="description" class="form-control" required="" aria-required="true"></textarea> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">知识正文：</label> 
         <div class="col-sm-10"> 
          <div class="ibox float-e-margins"> 
           <div class="ibox-content"> 
            <textarea id="editor" name="faqcontent" data-provide="markdown" rows="10" required="" ></textarea> 
           </div> 
          </div> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group"> 
         <div class="col-sm-4 col-sm-offset-2"> 
          <button class="btn btn-primary" type="submit" id="sub" data-dialog="somedialog">保存内容</button> 
          <button class="btn btn-white" type="submit"><a href="faqPage.html" >取消</a></button> 
         </div> 
        </div> 
       
       </form> 
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
  <script>
    /* 获取一级分类名 */
    $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: "/org.xjtusicd3.portal/getFirstLevel.html",
            dataType: "json",
            success: function(data) {
                for (var i in data) {
                    document.getElementById("specialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));
                }
            }
        });
    })

    /* 获取一级分类名对应的二级分类名 */
    function selectSecondChild() {
        var element = document.getElementById("specialCategoryId");
        var classifyId = element.options[element.selectedIndex].value;
        $.ajax({
            type: "GET",
            url: "/org.xjtusicd3.portal/getSecondLevel.html" + "?" + "classifyId=" + classifyId,
            dataType: "json",
            success: function(data) {
                document.getElementById("subspecialCategoryId").options.length = 0;
                for (var i in data) {
                    document.getElementById("subspecialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));
                }
            }
        });
    }
    

    $("#sub").click(function() {
       var title = document.getElementById("title").value;
       alert(title);
       var keywords = document.getElementById("keywords").value;
       alert(keywords);
       var subspecialCategoryId = document.getElementById("subspecialCategoryId").value;
       alert(subspecialCategoryId);
       var description = document.getElementById("description").value;
       alert(description);
       var faqcontent = document.getElementById("editor").value;
      
       
       
      
       alert(faqcontent);
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/saveFAQ.html",
           data: {
               "title": title,
               "keywords": keywords,
               "subspecialCategoryId": subspecialCategoryId,
               "description": description,
               "faqcontent": faqcontent
           },
           dataType: "json",
           success: function(data) {
               if (data.value == "0") {
                   self.location = 'login.html';
               } else if (data.value == "1") { (function() {
                       var dlgtrigger = document.querySelector('[data-dialog]'),
                       somedialog = document.getElementById(dlgtrigger.getAttribute('data-dialog')),
                       dlg = new DialogFx(somedialog);
                       dlgtrigger.addEventListener('click', dlg.toggle.bind(dlg));
                   })();
                   document.getElementById('lasturl').innerHTML = data.url;
               } else { (function() {
                       var dlgtrigger = document.querySelector('[data-dialog]'),
                       somedialog = document.getElementById(dlgtrigger.getAttribute('data-dialog')),
                       dlg = new DialogFx(somedialog2);
                       dlgtrigger.addEventListener('click', dlg.toggle.bind(dlg));
                   })();
                   document.getElementById('lasturl').innerHTML = data.url;
               }
           }
       }) 
       return true;

    })
    
    
    
    
    function windowclose(){
		var url = document.getElementById('lasturl').innerHTML;
		self.location=url;
	}
    
    
    
    
    
    </script>   
 </body>
</html>