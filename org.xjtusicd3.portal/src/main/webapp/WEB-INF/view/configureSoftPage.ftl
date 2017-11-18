<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>软件资源库</title> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <!-- Data Tables --> 
  <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
  <!-- BEGIN GLOBAL MANDATORY STYLES --> 
  <link href="media/css/style.css" rel="stylesheet" type="text/css" /> 
 </head> 
 <body class="gray-bg"> 
  <div class="wrapper wrapper-content animated fadeIn"> 
   <div class="row" style="height: 100%"> 
    <div class="col-sm-6" style="width: 100%; height: 100%"> 
     <div class="tabs-container"> 
      <div class="tab-content"> 
       <div id="tab-1" class="tab-pane active"> 
        <meta charset="utf-8" /> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
        <!--360浏览器优先以webkit内核解析--> 
        <div class="wrapper wrapper-content animated fadeIn"> 
         <div class="row"> 
          <div class="col-sm-6" style="width: 100%"> 
           <div class="tabs-container"> 
            <ul class="nav nav-tabs"> 
             <li class="active"><a data-toggle="tab" href="#tab-31" aria-expanded="true">软件【${softSize }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-32" aria-expanded="false">驱动【${pordinaryUserCounts }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-33" aria-expanded="false">补丁【${ITUserCounts }】</a></li> 
            </ul> 
            <div class="tab-content"> 
             <!-- 软件信息 --> 
             <div id="tab-31" class="tab-pane active"> 
              <div class="ibox-title"> 
               <h5>软件信息</h5> 
               <div class="ibox-tools"> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;"></th> 
                  <th style="text-align: center;">名称</th> 
                  <th style="text-align: center;">简介</th>
                  <th style="text-align: center;">生厂商</th> 
                  <th style="text-align: center;">更新时间</th>
                  <th style="text-align: center;">评分</th>
                  <th style="text-align: center;">文件大小</th> 
                  <th style="text-align: center;">下载次数</th>                   
                  <th style="text-align: center;">更多详情</th> 
                  <th style="text-align: center;">下载/配置</th> 
                 </tr> 
                </thead> 
                
                <tbody>    
                 <#list softList as a>
                 <tr class="gradeX" id="${a.CONFIGUREID }"> 
                  <td style="text-align: center;width: 3%"><input type="checkbox" class="i-checks" name="input[]" value="${a.CONFIGUREID }"></td>
                  <td style="width: 12%"><img class="answerImg" src="${a.LOGO }" style="width: 20px;height: 20px">${a.CONFIGURENAME }</td> 
                  <td style="width: 35%">${a.DESCRIPTION }</td>
                  <td style="text-align: center;width: 12%">${a.PRODUCER }</td> 
                  <td style="text-align: center;width: 10%">${a.CONFIGURETIME }</td>
                  <td style="text-align: center;width: 5%">${a.SCORE }</td>
                  <td style="text-align: center;width: 5%">${a.FILESIZE }</td> 
                  <td style="text-align: center;width: 5%">${a.DOWNLOADTIMES }</td> 
                  <td style="text-align: center;width: 5%" id = "">
                  	<button class="btn btn-white btn-sm" type="button" id="${a.CONFIGUREID }" title="更多详情" onclick="lookMoreSoftInfo(this.id)" data-toggle="modal" data-target="#myModalSoft"><i class="fa fa-eye"></i></button>
                  </td> 
                  <td style="text-align: center;width: 8%" id = "">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="下载" id="${a.USERID }" onclick="noAudit(this.id)"><a href="${a.URL }"><i class="fa fa-download"></i>                  	
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="添加至标准配置" id="${a.USERID }" onclick="noAudit(this.id)"><i class="fa fa-plus"></i>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
               </table> 
              </div> 
             </div> 
             <!--普通用户信息 --> 
             <div id="tab-32" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>普通用户信息</h5> 
               <div class="ibox-tools"> 
                <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
                <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#"> <i class="fa fa-wrench"></i> </a> 
                <ul class="dropdown-menu dropdown-user"> 
                 <li><a href="table_data_tables.html#">选项1</a> </li> 
                 <li><a href="table_data_tables.html#">选项2</a> </li> 
                </ul> 
                <a class="close-link"> <i class="fa fa-times"></i> </a> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;"><input type="checkbox" class="i-checks" onclick="swapCheck()" /></th> 
                  <th style="text-align: center;">序号</th> 
                  <th style="text-align: center;">名称</th> 
                  <th style="text-align: center;">文件大小</th> 
                  <th style="text-align: center;">URL</th> 
                  <th style="text-align: center;">下载次数</th> 
                  <th style="text-align: center;">生厂商</th> 
                  <th style="text-align: center;">更新时间</th> 
                  <th style="text-align: center;">软件评分</th> 
                  <th style="text-align: center;">软件ID???</th> 
                  <th style="text-align: center;">LOGO</th> 
                  <th style="text-align: center;">详情</th> 
                  <th style="text-align: center;"></th> 
                 </tr> 
                </thead> 
                <tbody> 
                 <tr class="gradeX" id="${a.USERID }"> 
                  <td style="text-align: center;"><input type="checkbox" class="i-checks" name="input[]" value="1111" /></td> 
                  <td style="text-align: center;">11</td> 
                  <td style="text-align: center;"><img class="answerImg" src="111" style="width: 50px;height: 50px" /></td> 
                  <td>111</td> 
                  <td class="center">111</td> 
                  <td style="text-align: center;">111</td> 
                  <td>11</td> 
                  <td>11</td> 
                  <td>11</td> 
                  <td class="center">11</td> 
                  <td class="center">11</td> 
                  <td style="text-align: center;" id=""> <button class="" type="button" id="${a.USERID }" onclick="throughAudit(this.id)" data-toggle="modal" data-target="#myModalEdit"><i class="fa fa-check text-navy"></i></button> </td> 
                  <td style="text-align: center;" id=""> <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="未通过审核" id="${a.USERID }" onclick="noAudit(this.id)"><i class="fa fa-trash-o"></i> </button></td> 
                 </tr> 
                </tbody> 
               </table> 
              </div> 
             </div> 
             <!-- 运维人员信息 --> 
             <div id="tab-33" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>运维人员信息</h5> 
               <div class="ibox-tools"> 
                <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
                <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#"> <i class="fa fa-wrench"></i> </a> 
                <ul class="dropdown-menu dropdown-user"> 
                 <li><a href="table_data_tables.html#">选项1</a> </li> 
                 <li><a href="table_data_tables.html#">选项2</a> </li> 
                </ul> 
                <a class="close-link"> <i class="fa fa-times"></i> </a> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;">序号</th> 
                  <th style="text-align: center;">头像</th> 
                  <th style="text-align: center;">用户名</th> 
                  <th style="text-align: center;">邮箱</th> 
                  <th style="text-align: center;">性别</th> 
                  <th style="text-align: center;">生日</th> 
                  <th style="text-align: center;">地址</th> 
                  <th style="text-align: center;">签名</th> 
                  <th style="text-align: center;">注册时间</th> 
                  <th style="text-align: center;">操作</th> 
                  <th style="text-align: center;">操作</th> 
                 </tr> 
                </thead> 
                <tbody>
                 <tr class="gradeX" id="${a.USERID }"> 
                  <td style="text-align: center;">${a_index+1 }</td> 
                  <td style="text-align: center;"><img class="answerImg" src="${a.AVATAR }" style="width: 50px;height: 50px" /></td> 
                  <td>${a.USERNAME }</td> 
                  <td class="center">${a.USEREMAIL }</td> 
                  <td style="text-align: center;">${a.GENDER }</td> 
                  <td>${a.USERBIRTHDAY }</td> 
                  <td>${a.USERADDRESS }</td> 
                  <td>${a.USERSIGNATURE }</td> 
                  <td class="center">${a.CREATETIME }</td> 
                  <td style="text-align: center;" id=""> <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="注销该运维人员" id="${a.USERID }" onclick="noAudit(this.id)"><i class="fa fa-trash-o"></i> </button></td> 
                  <td style="text-align: center;" id=""> <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="查看该用户详情"><a class="userinfo" href="/org.xjtusicd3.portal/showUserInfo.html?u=${a.USERID}"><i class="fa fa-eye"></i></a></button> </td> 
                 </tr> 
                </tbody> 
               </table> 
              </div> 
             </div> 
             
             
             <!-- 查看软件更多信息_模态框（Modal） --> 
             <div class="modal fade" id="myModalSoft" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
              <div class="modal-dialog"> 
               <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
                <div class="modal-header"> 
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
                 <h4 class="modal-title" id="myModalLabel"> 更多软件信息 </h4> 
                </div> 
                <form class="form-horizontal m-t" id=""> 
                 <div class="modal-body"> 
                  <div class="form-group" style="display: none;"> 
                   <div class="col-sm-8"> 
                    <input id="editPermissionId" name="permissionId" minlength="2" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">更多描述：</label> 
                   <div class="col-sm-8"> 
                    <input id="editLogicName" name="logicName" minlength="2" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">版本类型：</label> 
                   <div class="col-sm-8"> 
                    <input id="editPhysicalName" name="logicName" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                 
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">版本号：</label> 
                   <div class="col-sm-8"> 
                    <input id="editPhysicalName" name="logicName" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">软件类型：</label> 
                   <div class="col-sm-8"> 
                    <input id="editPhysicalName" name="logicName" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">网址：</label> 
                   <div class="col-sm-8"> 
                    <input id="editPhysicalName" name="logicName" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                 
                 </div> 
                 <div class="modal-footer"> 
                  <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
                  <button type="button" class="btn btn-primary" onclick="update()"> 提交 </button> 
                 </div> 
                </form> 
               </div> 
               <!-- /.modal-content --> 
              </div> 
              <!-- /.modal-dialog --> 
             </div> 
             <!-- /.modal --> 
            </div> 
           </div> 
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
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script> 
    <!-- Data Tables --> 
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script> 
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script> 
    <!-- 自定义js --> 
    <script src="js/content.js?v=1.0.0"></script> 
    <!-- Page-Level Scripts --> 
    <script>
        $(document).ready(function () {
            $('.dataTables-example').dataTable();

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable('../example_ajax.php', {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1]);
                },
                "submitdata": function (value, settings) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition(this)[2]
                    };
                },

                "width": "90%",
                "height": "100%"
            });


        });

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData([
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row"]);

        }
    </script> 
    
    <script>
    /* 查看软件更多信息 */
    function lookMoreSoftInfo(id) {
    	//获取权限ID
    	var configureId = document.getElementById(id).id;
    	//alert(permissionId);
    	
    	$.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/lookMoreSoftInfo.html",
            data: {
                "configureId":configureId
            },
            dataType: "json",
            success: function(data) {
            	
            	var moreSoftInfo = data.moreSoftInfo; //获取后台角色待获取权限
            	
            	$("#editPermissionId").val(moreSoftInfo.iNTRODUCTION); 
            	$("#editLogicName").val(moreSoftInfo.vERSIONTYPE);
            	$("#editPhysicalName").val(editPhysicalName);
            }
           
        }) 	 
    	
    	
     }
    
    </script>
    
   </div> 
  </div>  
 </body>
</html>