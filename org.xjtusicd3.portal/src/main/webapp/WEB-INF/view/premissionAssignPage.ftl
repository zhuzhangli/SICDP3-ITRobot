<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title> 权限管理</title> 
  <meta name="keywords" content="" /> 
  <meta name="description" content="" /> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <!-- Data Tables --> 
  <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
  
  <style type="text/css">  
	* {  
	    margin: 0;  
	    padding: 0;  
	    list-style-type: none;  
	}  
	  
	a,img {  
	    border: 0;  
	}  
	  
	body {  
	    font: 12px/180% Arial, Helvetica, sans-serif, "新宋体";  
	}  
	  
	.selectbox {  
	    width: 500px;  
	    height: 220px;  
	    margin: 40px auto 0 auto;  
	}  
	  
	.selectbox div {  
	    float: left;  
	}  
	  
	.selectbox .select-bar {  
	    padding: 0 20px;  
	}  
	  
	.selectbox .select-bar select {  
	    width: 300px;  
	    height: 400px;  
	    border: 4px #A0A0A4 outset;  
	    padding: 4px; 

	}  
	  
	.selectbox .btn {  
	    width: 50px;  
	    height: 30px;  
	    margin-top: 10px;  
	    cursor: pointer;  
	}  
	.bottom-btn{  
	    width: 500px;  
	    height: 220px;  
	    margin: 40px auto 0 auto;  
	    margin-left: 40%;  
	}  
</style>  
 </head> 
 <body class="gray-bg"> 
  <div class="wrapper wrapper-content animated fadeIn"> 
   <div class="row"> 
    <div class="col-sm-12"> 
     <div class="ibox float-e-margins"> 
      <div class="ibox-title" style="height: 50px"> 
       <h5>权限分配</h5> 
        <div class="ibox-tools" style="width: 20%">
            <div class="form-group" >
                 <label class="col-sm-2 control-label" style="width: 30%">选择角色：</label>

                 <div class="col-sm-10" style="width: 70%;margin-top: -5px;">
                     <select class="form-control m-b" name="account" style="width: 70%;height: 32px" id="account">
                        <#list roleList as a>  
                          <option value="${a.ROLEID }">${a.ROLENAME}</option>  
                    	</#list>
                     </select>
                 </div>
             </div>
        </div>
      </div> 
      <div class="ibox-content"> 
        <div class="selectbox">
				<div class="select-bar">  
                <span id="sp1">${groupOneName}</span><br/>  
                <select multiple="multiple" id="select1" style="margin-left: -400px; ">  
                    <#list roleList as a>  
                        <option value="${a.ROLEID }">${a.ROLENAME}</option>  
                    </#list>  
                </select>  
            </div>  
            
            <div class="btn-bar"><br/>
           <span id="add"><input type="button" class="btn" value=">" /></span><br /> 
           <span id="add_all"><input type="button" class="btn" value=">>" /></span><br />
           <span id="remove"><input type="button" class="btn" value="<"></input></span><br /> 
            <span id="remove_all"><input type="button" class="btn" value="<<"></input></span> 
            </div>
            
            <div class="select-bar">  
                <span id="sp2">${groupTwoName}</span><br/>  
                <select multiple="multiple" id="select2" style="margin-left: 70px; ">  
                    <c:forEach items="${groupTwoList}" var="item">  
                        <option value="${item.stunumber}">${item.stuname}</option>  
                    </c:forEach>  
                </select>  
            </div>  

        </div>
        
        <div class="bottom-btn" style="margin-left: 33%;margin-top: 20%">  
            <input type="button" class="btn" value="返回" onClick="toGroupingPage()"/>  
            <input type="button" class="btn" value="保存" onClick="reGrouping()"/>  
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
    
 </body>
</html>