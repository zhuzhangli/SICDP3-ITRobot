<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 百度Web Uploader</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="css/demo/webuploader-demo.css">
   
   
    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
   
    <!-- 基本图库 -->
    <link href="js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
 
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>上传图片</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_file_upload.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_file_upload.html#">选项1</a>
                                </li>
                                <li><a href="form_file_upload.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="page-container">
                            <p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮，来体验此demo.</p>
                            <div id="uploader" class="wu-example">
                                <div class="queueList">
                                    <div id="dndArea" class="placeholder">
                                        <div id="filePicker"></div>
                                        <p>或将照片拖到这里，单次最多可选300张</p>
                                    </div>
                                </div>
                                <div class="statusBar" style="display:none;">
                                    <div class="progress">
                                        <span class="text">0%</span>
                                        <span class="percentage"></span>
                                    </div>
                                    <div class="info"></div>
                                    <div class="btns">
                                        <div id="filePicker2"></div>
                                        <div class="uploadBtn">开始上传</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

		<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加图片标题</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_data_tables.html#">选项1</a>
                                </li>
                                <li><a href="table_data_tables.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    
                    <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover " id="editable">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>缩略图</th>
                                    <th>标题</th>
                                    <th>修改标题</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list faqPicList as a>
                                <tr class="gradeX">
                                    <td style="text-align: center;width: 5%">${a_index+1 }</td>
                                    <td style="text-align: center;width: 20%"  valign="middle">
	                                    <a class="fancybox" href="${a.IMGPATH}" title="${a.DESCRIPTION}">
	                            			<img alt="image" src="${a.IMGPATH}"   />
	                       				 </a>
   									</td>
                                    <td style="width: 65%" id="faqPicTitle${a.FAQPICTUREID}">${a.DESCRIPTION}</td>
                                    <td style="text-align: center;width: 5%">
                                    	<button class="btn btn-white btn-sm" type="button" id="${a.FAQPICTUREID}" onclick="editTitle(this.id)" title="修改标题" data-toggle="modal" data-target="#myModalEdit"><i class="glyphicon glyphicon-pencil"></i></button> 
                                    <td style="text-align: center;width: 5%">
                                    	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="删除" id = "${a.FAQPICTUREID}" onclick="deletePic(this.id)"><i class="fa fa-trash-o"></i>
                                    </td>
                                </tr> 
                    			</#list>
                            </tbody>                         
                        </table>
                    </div>
                </div>
               </div>
             </div>
                
 	  <!-- 修改标题_模态框（Modal） --> 
      <div class="modal fade" id="myModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
       <div class="modal-dialog"> 
        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
         <div class="modal-header"> 
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button> 
          <h4 class="modal-title" id="myModalLabel"> 请输入图片标题 </h4> 
         </div> 
         <form class="form-horizontal m-t" id=""> 
          
          <div class="modal-body"> 
           <div class="form-group" style="display: none;"> 
            <div class="col-sm-8"> 
             <input id="faqPicId" name="faqPicId" minlength="2" type="text" class="form-control" required="" aria-required="true"></input>
            </div> 
           </div> 
          
           <div class="form-group"> 
            <label class="col-sm-3 control-label">标题：</label> 
            <div class="col-sm-8"> 
             <input id="picTitle" name="picTitle" minlength="2" type="text" class="form-control" required="" aria-required="true"></input>
            </div> 
           </div> 

          </div>
          <div class="modal-footer"> 
           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
           <button type="button" class="btn btn-primary" onclick="updateTitle()"> 提交 </button> 
          </div> 
          
         </form> 
        </div>
        <!-- /.modal-content --> 
       </div>
       <!-- /.modal-dialog --> 
      </div>
      <!-- /.modal -->
    </div>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

	<!-- Data Tables --> 
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script> 
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script> 

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

 	<!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Fancy box -->
    <script src="js/plugins/fancybox/jquery.fancybox.js"></script>


    <script>
        $(document).ready(function () {
            $('.fancybox').fancybox({
                openEffect: 'none',
                closeEffect: 'none'
            });
        });
    </script>

	<!-- Page-Level Scripts -->
    <script>
   

    function fnClickAddRow() {
        $('#editable').dataTable().fnAddData([
            "Custom row",
            "New row",
            "New row",
            "New row",
            "New row"]);
    }
      
        //提交成功
        function codefans(){
        	var box=document.getElementById("success");
        	box.style.display="none"; 
        }
        
        /* 忽略未处理事件 */	    
        function deletePic(id) {	
        	var faqPicId = document.getElementById(id).id;

        	if(confirm("确认删除？")){
       		  $.ajax({
       		         type: "POST",
       		         url: "/org.xjtusicd3.portal/deletePic.html",
       		         data: {
       		             "faqPicId":faqPicId                
       		         },
       		         dataType: "json",
       		         success: function(data) {
       		        	 if(data.value=="0"){
       		 				self.location='login.html';
       		 			}else if(data.value=="1"){
       		 				setTimeout("location.reload()",1000)
       						document.getElementById('success').style.display='block';
       						setTimeout("codefans()",3000);
       		         	}
       		         }     		        
       		     })  
        	 }else {
     			return;
     		}   
            return true;
         }
        
        /* 获取待编辑faq图片信息 */
        function editTitle(id) {
        	//获取faq图片ID
        	var faqPicId = document.getElementById(id).id;	
        	//获取faq图片标题
        	var faqPicTitle = document.getElementById("faqPicTitle"+faqPicId).innerText;	

        	$("#faqPicId").val(faqPicId); 
        	$("#picTitle").val(faqPicTitle);
        }
        
        /* 提交标题修改  */ 
        function updateTitle() {  
            //获取模态框数据  
            var faqPicId = $('#faqPicId').val();  
            var picTitle = $('#picTitle').val(); 
            if(faqPicId!=null){
            $.ajax({
                type: "POST",
                url: "/org.xjtusicd3.portal/updateTitle.html",
                data: {
                    "faqPicId":faqPicId,
                    "picTitle":picTitle,
                },
                dataType: "json",
                success: function(data) {
                	if(data.value=="0"){
        				self.location='login.html';
        			}else {
        				setTimeout("location.reload()",1000)
        				document.getElementById('myModalEdit').style.display='none';
						$(".modal-backdrop").remove();
   						document.getElementById('success').style.display='block';
   						setTimeout("codefans()",3000);
        			}  	
                }      
            })
            }
        }
    </script>

    <!-- Web Uploader -->
    <script type="text/javascript">
        // 添加全局站点信息
        var BASE_URL = 'js/plugins/webuploader';
    </script>
    <script src="js/webuploader.js"></script>

    <script src="js/demo.js"></script>

    
    <div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">操作成功</h2></div></div>

</body>

</html>
