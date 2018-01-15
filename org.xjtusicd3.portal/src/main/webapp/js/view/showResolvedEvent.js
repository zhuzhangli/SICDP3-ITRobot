//一级分类
$(document).ready(
   function(){
         $.ajax({
             type: "GET",
             url: "/org.xjtusicd3.portal/getFirstLevel.html",            
             dataType: "json",
             success: function(data){            
     			 for(var i in data){ 
     			 	 document.getElementById("specialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));					        
			      }                                                                      
             }
         });
    })
    
    
//二级分类			    
function selectSecondChild(){
var element = document.getElementById("specialCategoryId");
var classifyId = element.options[element.selectedIndex].value;
$.ajax({
     type: "GET",
     url: "/org.xjtusicd3.portal/getSecondLevel.html"+"?"+"classifyId="+classifyId,            
     dataType: "json",
     success: function(data){
     			 document.getElementById("subspecialCategoryId").options.length=0;              	
     			 for(var i in data){ 
     			 	 document.getElementById("subspecialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));					        
				      }                                                                      
                  }
     });         
}

//提交成功
function codefans(){
	var box=document.getElementById("success");
	box.style.display="none"; 
}

//重复提交
function codefans2(){
	var box=document.getElementById("chongfu");
	box.style.display="none"; 
}

//提交
$("#sub").click(function(){
$(".spa2").text("");
$(".spa4").text("");
var na = /^\S{2,44}$/   
var kw = /^\S{2,30}$/  
var dp = /^\S{2,100}$/
var ss = /^\S*$/
if(na.test($("#title").val())&&kw.test($("#keywords").val())&&$("#subspecialCategoryId").val()&&$("#questionId").val()&&dp.test($("#description").val())){
		var title = document.getElementById("title").value;
		var keywords = document.getElementById("keywords").value;
		var subspecialCategoryId = document.getElementById("subspecialCategoryId").value;
		var description = document.getElementById("description").value;
		var faqcontent = document.getElementById("faqcontent").innerText;
		var questionId = document.getElementById("questionId").value;
		var from = "event";
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.portal/saveFAQ.html",
			data:{
				"questionId":questionId,
				"title":title,
				"keywords":keywords,
				"subspecialCategoryId":subspecialCategoryId,
				"description":description,
				"faqcontent":faqcontent,
				"from":from
			},
			dataType:"json",
			success:function(data){
				if(data.value=="0"){
					self.location='login.html';
				}else if(data.value=="1"){
					document.getElementById('success').style.display='block';
					setTimeout("codefans()",3000);
					window.location.reload(); 
				}else{
					document.getElementById('chongfu').style.display='block';
					setTimeout("codefans2()",3000);
					window.location.reload();
				}
			}
		})
	return true;
}else{
	if($("#title").val()==""){
		$(".spa1").text('请您填写标题')
	}
	if($('input:radio[name="resource"]:checked').val()==null){
		$(".spa2").text('请您选择类型')
	}
	if($("#keywords").val()==""){
		$(".spa3").text('请您填写关键词')
	}
	if($("#subspecialCategoryId").val()==null){
		$(".spa4").text('请您选择知识分类')
	} 
	if($("#description").val()==""){
		$(".spa5").text('请您填写简单描述')
	}
	return false;
}
})


function windowclose(){
		var url = document.getElementById('lasturl').innerHTML;
		self.location=url;
	}
	
	$("#update_").click(function(){
		$("#subinfo").css('display','none');
		$("#updateinfo").css('display','block');	
	});