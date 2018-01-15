//一级分类
  $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: "/org.xjtusicd3.portal/getFirstLevel.html",
            dataType: "json",
            success: function(data) {
                for (var i in data) {
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

  $("input").blur(function(){
		$(".validate_faqadd").css("color","#BD362F")
		if($(this).is("#keywords")){            //关键词判断
			var kw= /^\S{2,30}$/
			if($("#userkwone").val()!=""){
			if(!(kw.test($("#keywords").val()))){
				$(".spa3").text("请输入2-30个字符");
				$(this).css("border","1px solid #BD362F")
				return false;
			}else if(kw){
				$(".spa3").text("");
				return true;
			}
			}else{
				$(".spa3").text("");
			}
		}
	})
  
	
$("input").focus(function(){
	if($(this).is("#keywords")){
		$(".spa3").text("输入关键词用逗号隔开").css("color","#aaa")
		$(this).css("border","1px solid #aaa")
	}
})
  
//添加至知识库
$("#sub").click(function(){
	var kw = /^\S{2,30}$/ 
	if($("#subspecialCategoryId").val()&&kw.test($("#keywords").val())){
	var communityQuestionId = document.getElementById("communityQuestionId").value;    		
	var title = document.getElementById("title").innerText;
	var content = document.getElementById("content").innerText;
	var classifyId = document.getElementById("subspecialCategoryId").value;	
	var problemUser = document.getElementById("problemUser").innerText;   		
	var userId = document.getElementById("userId").value;
	var problemTime = document.getElementById("problemTime").innerText;  		
	var answerContent = document.getElementById("answerContent").innerText;
	var answerUser = document.getElementById("answerUser").innerText;
	var answerTime = document.getElementById("answerTime").innerText;
	var keywords = document.getElementById("keywords").value;
 	  $.ajax({
		type:"POST",
		url:"/org.xjtusicd3.portal/saveCommunityQuestionToFAQ.html",
		data:{
			"communityQuestionId":communityQuestionId,
			"title":title,
			"content":content,
			"keywords":keywords,
			"classifyId":classifyId,
			"problemUser":problemUser,
			"problemTime":problemTime,
			"userId":userId,
			"answerContent":answerContent,
			"answerUser":answerUser,
			"answerTime":answerTime
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				alert("添加成功");
				self.location.href = "/org.xjtusicd3.portal/problemPage.html";
			}else{
				alert("重复添加");
				self.location.href = "/org.xjtusicd3.portal/problemPage.html";
			} 
			
		}
	}) 
		return true;
	}else{
		if($("#subspecialCategoryId").val()==null){
			$(".spa4").text('请您选择知识分类')
		} 
		if($("#keywords").val()==""){
			$(".spa3").text('请您填写关键词')
		}
		return false;
	}
});
    