/* 忽略未处理事件 */	    
function ignore(id) {
	
	var userQuestionId = document.getElementById(id).parentElement.id;
	alert(userQuestionId);
	 
     $.ajax({
         type: "POST",
         url: "/org.xjtusicd3.portal/ignoreUserQuestion.html",
         data: {
             "userQuestionId":userQuestionId                
         },
         dataType: "json",
         success: function(data) {
         	alert("忽略该问题成功");
         	window.location.reload();
         }
        
     }) 
    return true;

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
				if(data.eventResolvedView==""){
					document.getElementById("ibox-content").innerHTML='<p class="notattend">你还没有收藏任何原创知识，快去<a href="faq.html" class="red" target="_blank">发表知识</a>吧</p>';
				}else{
					for(var i in data.eventResolvedView){
						if(document.getElementById("tab-32").getElementsByClassName("ibox-title")[0]==null){
							document.getElementById("tab-32").innerHTML='<div class="ibox-title"><div class="ibox-tools"></div></div>';
						}
						if(document.getElementById("resolvedEvent"+data.eventResolvedView[i].uSERQUESTIONID)==null){
							var html = document.getElementById("tab-32").getElementsByClassName("ibox-title")[0].innerHTML;
							document.getElementById("tab-32").getElementsByClassName("ibox-title")[0].innerHTML=html + '<table class="table table-striped table-bordered table-hover dataTables-example">'
							+'<thead> '
							+'<tr>'
								+'<th style="text-align: center;">序号</th>'
								+'<th style="text-align: center;">问题名称</th>'
								+'<th style="text-align: center;">提问用户</th>'
								+'<th style="text-align: center;">问题时间</th>'																		
								+'<th style="text-align: center;">操作</th>'
							+'</tr>'
							+'</thead>'
			                
							+'<tbody id = "option1"> '   
							+'<#list eventResolved as eventResolved>'
								+'<tr class="" id = "${eventResolved.USERQUESTIONID}">'
									+'<td style="width: 5%;text-align: center;">${eventResolved_index+1}</td>'
									+'<td style="width: 35%;">${eventResolved.QUESTIONTITLE}</td>		'																		
									+'<td style="width: 10%;text-align: center;">${eventResolved.USERNAME}</td>'
									+'<td style="width: 10%;text-align: center;">${eventResolved.QUESTIONTIME}</td>'
									+'<td style="width: 7%;text-align: center;">'
									+'<a class="questioninfo" href="/org.xjtusicd3.portal/showResolvedEvent.html?q=${eventResolved.USERQUESTIONID}">查看详情</a>'
									+'</td>'
									+'</tr>'
									+'</#list>  ' 
									+' </tbody> '
									+' </table> '}
					}
					/*if(document.getElementById("getMoreCollection")==null){
						if(data.faqView[0].isMore=="1"){
							var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
							document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCollection"> <span class="js-dynamicLoad " onclick="getMoreCollectFaq()">点击显示更多</span> </p>';
						}
					}*/
				}
			}
		}
	})
}