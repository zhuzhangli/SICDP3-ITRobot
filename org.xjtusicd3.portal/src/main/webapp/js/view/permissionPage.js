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
				setTimeout("location.reload()",1000)
				document.getElementById('myModal').style.display='none';
				$(".modal-backdrop").remove();
				document.getElementById('chongfu').style.display='block';
				setTimeout("codefans2()",3000);
			}else if(data.value=="2"){
				setTimeout("location.reload()",1000)
				document.getElementById('myModal').style.display='none';
				$(".modal-backdrop").remove();
				document.getElementById('chongfu2').style.display='block';
				setTimeout("codefans2()",3000);
			}else {
				setTimeout("location.reload()",1000)
				document.getElementById('myModal').style.display='none';
				$(".modal-backdrop").remove();
				document.getElementById('success').style.display='block';
				setTimeout("codefans()",3000);
			}      	
        }     
    }) 
}   

/* 获取要编辑的权限信息 */
function editPermission(id) {
	//获取权限ID
	var permissionId = document.getElementById(id).id;	
	//获取权限逻辑名
	var editLogicName = document.getElementById("editLogicName"+permissionId).innerText;	
	//获取权限物理名
	var editPhysicalName = document.getElementById("editPhysicalName"+permissionId).innerText;	
	$("#editPermissionId").val(permissionId); 
	$("#editLogicName").val(editLogicName);
	$("#editPhysicalName").val(editPhysicalName);
 }

//提交成功
function codefans(){
	var box=document.getElementById("success");
	box.style.display="none"; 
}

//物理名重复提交
function codefans2(){
	var box=document.getElementById("chongfu");
	box.style.display="none"; 
}

//逻辑名重复提交
function codefans3(){
	var box=document.getElementById("chongfu2");
	box.style.display="none"; 
}

//请勿重复提交
function codefans3(){
	var box=document.getElementById("chongfu3");
	box.style.display="none"; 
}

/* 提交更改  */ 
function update() {  
    //获取模态框数据  
    var permissionId = $('#editPermissionId').val();  
    var logicName = $('#editLogicName').val(); 
    var physicalName = $('#editPhysicalName').val();
    if(permissionId!=null){
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
        	if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				setTimeout("location.reload()",1000)
				document.getElementById('myModalEdit').style.display='none';
				$(".modal-backdrop").remove();
				document.getElementById('success').style.display='block';
				setTimeout("codefans()",3000);
			}else{
				setTimeout("location.reload()",1000)
				document.getElementById('myModalEdit').style.display='none';
				$(".modal-backdrop").remove();
				document.getElementById('chongfu3').style.display='block';
				setTimeout("codefans3()",3000);
			}    	
        }      
    })
    }
}  

/* 删除权限 */
function deletePermission(id){  
    //获取模态框数据  
    var permissionId = document.getElementById(id).id; 	       

    if(permissionId!=null){
    $.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/deletePermission.html",
        data: {
            "permissionId":permissionId
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
    }
} 
 