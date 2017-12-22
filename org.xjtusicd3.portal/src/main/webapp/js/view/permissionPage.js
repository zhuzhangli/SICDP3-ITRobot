/* 增加权限 */      
function addPermission() {  
    //获取模态框数据  
    var logicName = $('#addLogicName').val();  
    var physicalName = $('#addPhysicalName').val();  
    $.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/addPermission.html",
        data: {
            "logicName":logicName,
            "physicalName":physicalName
        },
        dataType: "json",
        success: function(data) {
        	if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				alert("添加成功");
	        	window.location.reload();
			}      	
        }     
    }) 
}   



/* 获取要编辑的权限信息 */
function editPermission(id) {
	//获取权限ID
	var permissionId = document.getElementById(id).id;
	//alert(permissionId);
	
	//获取权限逻辑名
	var editLogicName = document.getElementById("editLogicName"+permissionId).innerText;
	//alert(editLogicName)
	
	//获取权限物理名
	var editPhysicalName = document.getElementById("editPhysicalName"+permissionId).innerText;
	//alert(editPhysicalName)
	
	$("#editPermissionId").val(permissionId); 
	$("#editLogicName").val(editLogicName);
	$("#editPhysicalName").val(editPhysicalName);
 }



/* 提交更改  */ 
function update() {  
    //获取模态框数据  
    var permissionId = $('#editPermissionId').val();  
    //alert(permissionId);
    var logicName = $('#editLogicName').val(); 
    //alert(logicName);
    var physicalName = $('#editPhysicalName').val();
    //alert(physicalName);

    $.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/updatePermission.html",
        data: {
            "permissionId":permissionId,
            "logicName":logicName,
            "physicalName":physicalName
        },
        dataType: "json",
        success: function(data) {
        	alert("更改成功");
        	window.location.reload();
        }
       
    }) 	        
}  

/* 删除权限 */
function deletePermission(id){  
    //获取模态框数据  
    var permissionId = document.getElementById(id).id; 	       

    $.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/deletePermission.html",
        data: {
            "permissionId":permissionId
        },
        dataType: "json",
        success: function(data) {
        	alert("删除成功");
        	window.location.reload();
        }
       
    }) 	        
}  