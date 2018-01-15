 /* 给角色分配权限 */
  var isCheckAll = false;

  function permissionCheck() {

      if (isCheckAll) {
          $("input[name='selectToObtain[]']").each(function() {
              this.checked = false;
          });
          isCheckAll = false;
      } else {
          $("input[name='selectToObtain[]']").each(function() {
              this.checked = true;
          });
          isCheckAll = true;
      }
  }

  /* 取消角色权限 */
  var isCheckAll2 = false;

  function permissionUnCheck() {

      if (isCheckAll2) {
          $("input[name='input1[]']").each(function() {
              this.checked = false;
          });
          isCheckAll2 = false;
      } else {
          $("input[name='input1[]']").each(function() {
              this.checked = true;
          });
          isCheckAll2 = true;
      }
  } 

/* 根据不同角色获取对应的权限列表 */
function selectRole() {
    var element = document.getElementById("roleId");
    var roleId = element.options[element.selectedIndex].value;
    
    $.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/selectRolePermission.html",
        data: {
        	"roleId":roleId
        },
        dataType: "json",
        success: function(data) {
        	var permissionList = data.notObtainRolePermission; //获取后台角色待获取权限
        	var obtainPermission = data.obtainPermission;  //获取后台角色已获取权限
        	       	
        	if(permissionList.length == 0){
				$("#tbody1").html("");
        	}else{
        		var permissionHtml = "";
				for (var i = 0; i < permissionList.length; i++) {
					 permissionHtml = permissionHtml
					+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='selectToObtain[]' value='"+permissionList[i].pERMISSIONID+"'></td>"
					+ "<td style='text-align: center;width: 87%' id='editLogicName${p.PERMISSIONID }'>"
					+ permissionList[i].pERMISSIONLOGICNAME
					+"</td></tr>"
	
					$("#tbody1")[0].innerHTML = permissionHtml;    
				}
				
        	}
        	
        	if(obtainPermission.length == 0){
			 $("#tbody2").html("");
    		}else{
    		var permissionHtml2 = "";
			for (var i = 0; i < obtainPermission.length; i++) {
				 permissionHtml2 = permissionHtml2
				+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='input1[]' value='"+obtainPermission[i].pERMISSIONID+"'></td>"
				+ "<td style='text-align: center;width: 87%' id='editLogicName_obtainPermission[i].pERMISSIONID'>"
				+ obtainPermission[i].pERMISSIONLOGICNAME
				+"</td></tr>"

				$("#tbody2")[0].innerHTML = permissionHtml2;    
    				} 
	            }
	            }
	        }) 	     
        }

/* 将左边未获取权限插入右边已获取*/
$("#b1").click(function(){
	var element = document.getElementById("roleId");
    var roleId = element.options[element.selectedIndex].value; 
	
	var checkedSubject=document.getElementsByName('selectToObtain[]');//获取到复选框的名称  
	var checkedIds="";

	//因为获得的是数组，所以要循环 为每一个checked赋值  
    for(var i=0;i<checkedSubject.length;i++){  
        if(checkedSubject[i].checked == true){  
        	checkedIds += checkedSubject[i].value+",";  
        } 
     } 
		    	 
     $.ajax({
      type: "POST",
      url: "/org.xjtusicd3.portal/permissionChecked.html",
      data: {
    	  roleId:roleId,
    	  checkedIds:checkedIds                
      },
      dataType: "json",
      success: function(data) {             	
      	var permissionList = data.notObtainRolePermission; //获取后台角色待获取权限
    	var obtainPermission = data.obtainPermission;  //获取后台角色已获取权限
    	
    	
    	if(permissionList.length == 0){
			$("#tbody1").html("");
    	}else{
    		var permissionHtml = "";
			for (var i = 0; i < permissionList.length; i++) {
				 permissionHtml = permissionHtml
					+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='selectToObtain[]' value='"+permissionList[i].pERMISSIONID+"'></td>"
					+ "<td style='text-align: center;width: 87%' id='editLogicName${p.PERMISSIONID }'>"
					+ permissionList[i].pERMISSIONLOGICNAME
					+"</td></tr>"
									
					$("#tbody1")[0].innerHTML = permissionHtml;    
			}
			
    	}
    	
    	if(obtainPermission.length == 0){
		 $("#tbody2").html("");
		}else{
		var permissionHtml2 = "";
		for (var i = 0; i < obtainPermission.length; i++) {
			 permissionHtml2 = permissionHtml2
				+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='input1[]' value='"+obtainPermission[i].pERMISSIONID+"'></td>"
				+ "<td style='text-align: center;width: 87%' id='editLogicName_obtainPermission[i].pERMISSIONID'>"
				+ obtainPermission[i].pERMISSIONLOGICNAME
				+"</td></tr>"

				$("#tbody2")[0].innerHTML = permissionHtml2;    
		} 
    }
      	
      }
     
  }) 

});


/* 将角色已获取权限移除*/
$("#b2").click(function(){
	var element = document.getElementById("roleId");
    var roleId = element.options[element.selectedIndex].value; 
	
	var checkedSubject=document.getElementsByName('input1[]');//获取到复选框的名称  
	var checkedIds="";

	//因为获得的是数组，所以要循环 为每一个checked赋值  
    for(var i=0;i<checkedSubject.length;i++){  
        if(checkedSubject[i].checked == true){  
        	checkedIds += checkedSubject[i].value+",";  
        } 
    } 

    $.ajax({
      type: "POST",
      url: "/org.xjtusicd3.portal/permissionUnChecked.html",
      data: {
    	  roleId:roleId,
    	  checkedIds:checkedIds                
      },
      dataType: "json",
      success: function(data) {
      	var permissionList = data.notObtainRolePermission; //获取后台角色待获取权限
    	var obtainPermission = data.obtainPermission;  //获取后台角色已获取权限
    	   	
    	if(permissionList.length == 0){
			$("#tbody1").html("");
    	}else{
    		var permissionHtml = "";
			for (var i = 0; i < permissionList.length; i++) {
				 permissionHtml = permissionHtml
					+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='selectToObtain[]' value='"+permissionList[i].pERMISSIONID+"'></td>"
					+ "<td style='text-align: center;width: 87%' id='editLogicName${p.PERMISSIONID }'>"
					+ permissionList[i].pERMISSIONLOGICNAME
					+"</td></tr>"
								
					$("#tbody1")[0].innerHTML = permissionHtml;    
			}			
    	}
    	
    	if(obtainPermission.length == 0){
		 $("#tbody2").html("");
		}else{
		var permissionHtml2 = "";
		for (var i = 0; i < obtainPermission.length; i++) {
			 permissionHtml2 = permissionHtml2
				+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='input1[]' value='"+obtainPermission[i].pERMISSIONID+"'></td>"
				+ "<td style='text-align: center;width: 87%' id='editLogicName_obtainPermission[i].pERMISSIONID'>"
				+ obtainPermission[i].pERMISSIONLOGICNAME
				+"</td></tr>"

				$("#tbody2")[0].innerHTML = permissionHtml2;    
		} 
    }
      }
     
   })   

});