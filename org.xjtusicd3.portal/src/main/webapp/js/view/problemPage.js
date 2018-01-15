/* 忽略未处理事件 */
function ignore(id) {	
	var questionId = document.getElementById(id).id;
	if(questionId!=null){
		 $.ajax({
	         type: "POST",
	         url: "/org.xjtusicd3.portal/ignoreQuestion.html",
	         data: {
	             "questionId":questionId                
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
	    return true;
	}
    
 }	

//已处理问题
function getResolvedProblem(){
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.portal/getResolvedProblem.html",
		data:{
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				var problemResolvedList = data.problemResolved; //获取已解决问题
				
				if(problemResolvedList==""){
					$("#option1").html("");
				}else{
					var problemResolvedHtml = '<thead>'
						+'<tr>'
						+'<th style="text-align: center;">序号</th>'
						+'<th style="text-align: center;">问题标题</th>'
						+'<th style="text-align: center;">内容</th>'
						+'<th style="text-align: center;">问题分类</th>'
						+'<th style="text-align: center;">提问用户</th>'
						+'<th style="text-align: center;">提问时间</th>'
						+'<th style="text-align: center;">回复者</th>'
						+'<th style="text-align: center;">回复内容</th>'
						+'<th style="text-align: center;">查看详情</th>'
						+'</tr>'
						+'</thead>' 
						
					for (var i = 0; i < problemResolvedList.length; i++) {
						problemResolvedHtml = problemResolvedHtml
						+'<tr class="" id = "'+problemResolvedList[i].problemId+'">'
						+'<td style="width: 5%;">'+i+'</td>'
						+'<td style="width: 20%;">'+problemResolvedList[i].problemTitle+'</td>'
						+'<td style="width: 20%;">'+problemResolvedList[i].problemContent.replace(/<[^>]+>/g,"").substr(0,99)+'</td>'
						+'<td style="width: 7%;" class="center">'+problemResolvedList[i].problemClassifyName+'</td>'
						+'<td style="width: 7%;" class="center">'+problemResolvedList[i].problemUserName+'</td>'														
						+'<td style="width: 7%;">'+problemResolvedList[i].problemTime+'</td>'
						+'<td style="width: 7%;">'+problemResolvedList[i].answerUserName+'</td>'
						+'<td style="width: 20%;text-align: center;">'+problemResolvedList[i].answerContent.replace(/<[^>]+>/g,"").substr(0,99)+'</td>'
						+'<td  style="width: 7%;text-align: center;" >'																				
						+'<a class="questioninfo" href="/org.xjtusicd3.portal/showResolvedProblem.html?p='+problemResolvedList[i].problemId+'">查看详情</a>'
						+'</td>'				
						+'</tr>'
								
						$("#option1")[0].innerHTML = problemResolvedHtml;    
					}
				}
			}
		}
	})
}

//待处理问题
function getUnResolvedProblem(){
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.portal/getUnResolvedProblem.html",
		data:{
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				var problemUnresolvedList = data.problemUnresolved; //获取待解决问题
				
				if(problemUnresolvedList==""){
					$("#option1").html("");
				}else{
					var problemUnResolvedHtml = '<thead>'
						+'<tr>'
						+'<th style="text-align: center;">序号</th>'
						+'<th style="text-align: center;">问题标题</th>'
						+'<th style="text-align: center;">内容</th>'
						+'<th style="text-align: center;">问题分类</th>'
						+'<th style="text-align: center;">提问用户</th>'
						+'<th style="text-align: center;">提问时间</th>'
						+'<th style="text-align: center;">查看</th>'
						+'<th style="text-align: center;">忽略</th>'																		
						+'</tr>'
						+'</thead>' 
						
					for (var i = 0; i < problemUnresolvedList.length; i++) {
	
						problemUnResolvedHtml = problemUnResolvedHtml
						+'<tr class="" id = "'+problemUnresolvedList[i].problemId+'">'
						+'<td style="width: 6%;">'+i+'</td>'
						+'<td style="width: 27%;">'+problemUnresolvedList[i].problemTitle+'</td>'
						+'<td style="width: 27%;">'+problemUnresolvedList[i].problemContent.replace(/<[^>]+>/g,"").substr(0,99)+'</td>'
						+'<td style="width: 10%;" class="center">'+problemUnresolvedList[i].problemClassifyName+'</td>'
						+'<td style="width: 10%;" class="center">'+problemUnresolvedList[i].problemUserName+'</td>'														
						+'<td style="width: 10%;">'+problemUnresolvedList[i].problemTime+'</td>'
						+'<td  style="width: 5%;text-align: center;">'
						+'<a class="questioninfo" href="/org.xjtusicd3.portal/showUnResolvedProblem.html?p='+problemUnresolvedList[i].problemId+'">查看详情</a>'
						+'</td>'
						+'<td  style="width: 5%;text-align: center;" >'																				
						+'<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="忽略此问题" id = "'+problemUnresolvedList[i].problemId+'" onclick="ignore(this.id)"><i class="fa fa-trash-o"></i>'
						+'</td>'				
						+'</tr>'
						
		
						$("#option1")[0].innerHTML = problemUnResolvedHtml;    
					}
				}
			}
		}
	})
}