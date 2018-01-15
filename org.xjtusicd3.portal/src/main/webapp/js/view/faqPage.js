/* 删除配置 */
function deleteFAQ(id){  
    //获取模态框数据  
    var faqQuestionId = document.getElementById(id).id; 	       

    if (confirm("确认删除？")) {
		$.ajax({
			type : "post",
			url : "/org.xjtusicd3.portal/deleteFAQ.html",
			data : {
				"faqQuestionId" : faqQuestionId
			},
			dataType : "json",
			success : function(data) {
			 if(data.value=="0"){
	 				self.location='login.html';
	 			}else if(data.value=="1"){
					document.getElementById('success').style.display='block';
					setTimeout("codefans()",3000);
					window.location.reload(); 
	         	}	
			}
		});
	} else {
		return;
	} 	        
} 

//提交成功
function codefans(){
	var box=document.getElementById("success");
	box.style.display="none"; 
}

/* 查看更多faq信息 */
function lookMoreFaqInfo(id) {
	//获取权限ID
	var faqQuestionId = document.getElementById(id).id;
	//alert(permissionId);
	
	$.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/lookMoreFaqInfo.html",
        data: {
            "faqQuestionId":faqQuestionId
        },
        dataType: "json",
        success: function(data) {
        	
        	var moreFaqInfo = data.moreFaqInfo; //获取后台json'

        	
        	$("#faqTitle").val(moreFaqInfo.fAQTITLE);
        	$("#faqClassifyName").val(moreFaqInfo.fAQCLASSIFYNAME);
        	//$("#faqContent").val(moreFaqInfo.fAQCONTENT);
        	
        	if(moreFaqInfo.length == 0){
				$("#tbody1").html("");
        	}else{
        		var permissionHtml = "";
				
				permissionHtml = permissionHtml+moreFaqInfo.fAQCONTENT;
						
						
						
					$("#tbody1")[0].innerHTML = permissionHtml;    
				
				
        	}
        }           
    }) 	   	    	
 }

/* 根据不同角色获取对应的权限列表 */
function selectClassify() {
    var element = document.getElementById("classifyId");
    var classifyId = element.options[element.selectedIndex].value;
    
    $.ajax({
        type: "POST",
        url: "/org.xjtusicd3.portal/selectClassify.html",
        data: {
        	"classifyId":classifyId
        },
        dataType: "json",
        success: function(data) {
        	if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				var faqList = data.faqAudited; //faq问题列表
				
				if(faqList.length == 0){
    				$("#option2").html("");
            	}else{
            		var faqHtml = '<thead> '
            		+'<tr>'
            		+'<th style="text-align: center;">序号</th> '
            		+'<th style="text-align: center;">FAQ名称</th>' 
            		+'<th style="text-align: center;">FAQ分类名</th>'
            		+'<th style="text-align: center;">提交用户</th>'
            		+'<th style="text-align: center;">删除</th>'
            		+'<th style="text-align: center;">详情</th>'
            		+' </tr> '
            		+'</thead> ';
    				for (var i = 0; i < faqList.length; i++) {
    					faqHtml = faqHtml
    					+'<tr class="gradeX"> '
    					+'<td style="text-align: center;width: 4%">'+i+'</td>'
    					+'<td style="width: 36%">'+faqList[i].fAQTITLE+'</td> '
    					+'<td style="width: 8%">'+faqList[i].fAQCLASSIFYNAME+'</td>'
    					+'<td style="text-align: center;width: 9%">'+faqList[i].uSERNAME+'</td>'
    					+'<td style="text-align: center;width: 4%"><button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="删除" id="delete_'+faqList[i].fAQQUESTIONID+'" onclick="deleteFAQ(this.id)"><i class="fa fa-trash-o"></i> </button></td>'
    					+'<td style="text-align: center;width: 4%">'
    					+'	<button class="btn btn-white btn-sm" type="button" id="'+faqList[i].fAQQUESTIONID+'" title="更多详情" onclick="lookMoreFaqInfo(this.id)" data-toggle="modal" data-target="#myModal"><i class="fa fa-eye"></i></button>'
    					+'</td>'
    					+'</tr>'
    	
    	
    					$("#option2")[0].innerHTML = faqHtml;    
    				}
    				
            	}
			}
    
	            }
	        }) 	     
        }