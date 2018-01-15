/* 忽略未处理事件 */	    
function ignore(id) {	
	var userQuestionId = document.getElementById(id).id;
	 if(userQuestionId!=null){
		  $.ajax({
		         type: "POST",
		         url: "/org.xjtusicd3.portal/ignoreUserQuestion.html",
		         data: {
		             "userQuestionId":userQuestionId                
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
    return true;
 }	

//提交成功
function codefans(){
	var box=document.getElementById("success");
	box.style.display="none"; 
}

function getResolvedEvent(){
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.portal/getResolvedEvent.html",
		data:{
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				var eventResolvedList = data.eventResolvedView; //获取已解决事件
				
				if(eventResolvedList==""){
					$("#option1").html("");
				}else{
					var eventResolvedHtml = '<thead> <tr><th style="text-align: center;">序号</th><th style="text-align: center;">问题名称</th><th style="text-align: center;">提问用户</th><th style="text-align: center;">问题时间</th><th style="text-align: center;">操作</th></tr></thead>';
										
					for (var i = 0; i < eventResolvedList.length; i++) {
						eventResolvedHtml = eventResolvedHtml
						+'<tr class="" id = "'+eventResolvedList[i].uSERQUESTIONID+'">'
						+'<td style="width: 5%;text-align: center;">'+i+'</td>'
						+'<td style="width: 35%;">'+eventResolvedList[i].qUESTIONTITLE+'</td>'
						+'<td style="width: 10%;text-align: center;">'+eventResolvedList[i].uSERNAME+'</td>'
						+'<td style="width: 10%;text-align: center;">'+eventResolvedList[i].qUESTIONTIME+'</td>'
						+'<td style="width: 7%;text-align: center;">'
						+'<a class="questioninfo" href="/org.xjtusicd3.portal/showResolvedEvent.html?q='+eventResolvedList[i].uSERQUESTIONID+'">查看详情</a>'
						+'</td>'
						+'</tr>'
		
						$("#option1")[0].innerHTML = eventResolvedHtml;    
					}
				}
			}
		}
	})
}

//获取待解决事件
function getUnResolvedEvent(){
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.portal/getUnResolvedEvent.html",
		data:{
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				var eventUnresolvedList = data.eventUnresolved; //获取待解决事件
				
				if(eventUnresolvedList==""){
					$("#option1").html("");
				}else{
					var eventUnresolvedHtml = '<thead> '
						+'<tr>'
						+'<th style="text-align: center;">序号</th>'
						+'<th style="text-align: center;">问题名称</th>'
						+'<th style="text-align: center;">提问用户</th>'
						+'<th style="text-align: center;">问题时间</th>'
						+'<th style="text-align: center;">查看</th>'
						+'<th style="text-align: center;">忽略</th>'
						+'</tr>'
						+'</thead>';
										
					for (var i = 0; i < eventUnresolvedList.length; i++) {
						eventUnresolvedHtml = eventUnresolvedHtml
						+'<tr class="" id = "'+eventUnresolvedList[i].userQuestionId+'">'
						+'<td style="width: 5%;text-align: center;">'+i+'</td>'
						+'<td style="width: 70%;">'+eventUnresolvedList[i].userQuestionTitle+'</td>'
						+'<td style="width: 8%;text-align: center;">'+eventUnresolvedList[i].userName+'</td>'
						+'<td style="width: 12%;text-align: center;">'+eventUnresolvedList[i].userQuestionTime+'</td>'
						+'<td style="width: 7%;text-align: center;">'
						+'<a class="eventinfo" href="/org.xjtusicd3.portal/showUnResolvedEvent.html?q='+eventUnresolvedList[i].userQuestionId+'">查看详情</a>'
						+'</td>'
						+'<td style="width: 5%;text-align: center;" >'
						+'<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="忽略此问题" id = "'+eventUnresolvedList[i].userQuestionId+'" onclick="ignore(this.id)"><i class="fa fa-trash-o"></i>'
						+'</td>'
						+'</tr>'
		
						$("#option1")[0].innerHTML = eventUnresolvedHtml;    
					}
				}
			}
		}
	})
}

