<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>问题信息展示</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
 <#list resolvedProblemDetail as a>
    <div class="wrapper wrapper-content animated fadeInRight">
         
      
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
               
                    <div class="ibox-title">
                        <h5>问题信息</h5>
                         
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                           
                           <div class="form-group" style="display: none;">
                                <div class="col-sm-10" >
                                    <input id="communityQuestionId" value="${a.problemId}"> 
                                </div>
                            </div>
                           
                           
                            <div class="form-group">
                                <label class="col-sm-2 control-label">问题标题</label>

                                <div class="col-sm-10" id="title">
                                    ${a.problemTitle}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提问内容</label>

                                <div class="col-sm-10" id="content">
                                    ${a.problemContent}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group" style="display: none;">
                                <div class="col-sm-10" >
                                   <input id="classifyId" value="${a.problemClassifyId}"> 
                                </div>
                            </div>
                            
                            <div class="form-group" style="display: none;">
                                <div class="col-sm-10" >
                                    <input id="userId" value="${a.problemUserId}">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">问题类别</label>

                                <div class="col-sm-10" id="classifyName">
                                    ${a.problemClassifyName}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提问用户</label>

                                <div class="col-sm-10" id="problemUser">
                                    ${a.problemUserName}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提问时间</label>

                                <div class="col-sm-10" id="problemTime">
                                    ${a.problemTime}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">对应回复</label>

                                <div class="col-sm-10" id="answerContent">
                                    ${a.answerContent}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">回复用户</label>

                                <div class="col-sm-10" id="answerUser">
                                    ${a.answerUserName}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                             <div class="form-group">
                                <label class="col-sm-2 control-label">回复时间</label>

                                <div class="col-sm-10" id="answerTime">
                                    ${a.answerTime}
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" ><a href="/org.xjtusicd3.portal/problemPage.html#tab-32">返回</a></button>
                                    <button class="btn btn-primary" "><a href="javascript:void(0);" onclick="addToFaq()">添加至知识库</a></button> 
                                </div>
                            </div>
                        </form>
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

    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
        
        
        function addToFaq(){
        	
        	alert("进入addToFaq");
        	var communityQuestionId = document.getElementById("communityQuestionId").value;
    		alert(communityQuestionId);
    		
    		var title = document.getElementById("title").innerText;
    		alert(title);
    		var content = document.getElementById("content").innerText;
    		console.log(content);
    		var classifyId = document.getElementById("classifyId").value;
    		alert(classifyId);
    		console.log(classifyId);
    		var problemUser = document.getElementById("problemUser").innerText;
    		console.log(problemUser);
    		
    		var userId = document.getElementById("userId").value;
    		console.log(userId);
    		var problemTime = document.getElementById("problemTime").innerText;
    		console.log(problemTime);
    		
    		var answerContent = document.getElementById("answerContent").innerText;
    		console.log(answerContent);
    		var answerUser = document.getElementById("answerUser").innerText;
    		console.log(answerUser);
    		var answerTime = document.getElementById("answerTime").innerText;
    		console.log(answerTime);
    	 	  $.ajax({
    			type:"POST",
    			url:"/org.xjtusicd3.portal/saveCommunityQuestionToFAQ.html",
    			data:{
    				"communityQuestionId":communityQuestionId,
    				"title":title,
    				"content":content,
    				"classifyId":classifyId,
    				"problemUser":problemUser,
    				"problemTime":problemTime,
    				"userId":userId,
    				"answerContent":answerContent,
    				"answerUser":answerUser,
    				"answerTime":answerTime
    			},
    			dataType:"json",
    			success:function(data){
    				if(data.value=="0"){
    					self.location='login.html';
    				}else if(data.value=="1"){
    					self.location='eventPage.html#tab-32';
    				}else{
    					self.location='index.html';
    				} 
    				alert("更改状态成功");
                 	window.location.reload();
    			}
    		})  
    	}
    </script>

    
     

</body>

</html>
