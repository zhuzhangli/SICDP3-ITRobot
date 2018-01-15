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

	$("textarea").blur(function(){
		$(".validate_faqadd").css("color","#BD362F")
		if($(this).is("#description")){				//简介的判断
			var dp = /^\S{2,100}$/
			if($("#description").val()!=""){
				if(!(dp.test($("#description").val()))){
					$(".spa5").text("请输入2-100个字符");
					$(this).css("border","1px solid #BD362F")
					return false;
				}else if(dp){
					$(".spa5").text("");
					return true;
				}
			}else{
				$(".spa5").text("");
    			}
    		}
 
    	})

    	$("input").focus(function(){
  
    		if($(this).is("#keywords")){
			$(".spa3").text("输入关键词用逗号隔开").css("color","#aaa")
			$(this).css("border","1px solid #aaa")
		}
	})

	$("textarea").focus(function(){
		if($(this).is("#description")){
			$(".spa5").text("不超过100个字符").css("color","#aaa")
			$(this).css("border","1px solid #aaa")
		}
		
	})

	$("#sub").click(function(){
		$(".spa2").text("");
		$(".spa4").text(""); 
		var kw = /^\S{2,30}$/  
		var dp = /^\S{2,100}$/
		if(kw.test($("#keywords").val())&&dp.test($("#description").val())){
			var questionId = document.getElementById("questionId").value;
            var keywords = document.getElementById("keywords").value;
            var description = document.getElementById("description").value;
            var faqcontent = document.getElementById("editor").value;          
            $.ajax({
                type: "POST",
                url: "/org.xjtusicd3.portal/updateFaq.html",
                data: {
                    "questionId":questionId,
                    "keywords": keywords,
                    "description": description,
                    "faqcontent": faqcontent
                },
                dataType: "json",
                success: function(data) {
                	 if(data.value=="0"){
 		 				self.location='login.html';
 		 			}else if(data.value=="1"){
 						document.getElementById('success').style.display='block';
 						setTimeout("codefans()",3000);
 						self.location.href = "/org.xjtusicd3.portal/faqPage.html";
 		         	}                	
                }
               
            }) 
            return true;
		}else{
			if($("#keywords").val()==""){
				$(".spa3").text('请您填写关键词')
    			}
 
    			if($("#description").val()==""){
				$(".spa5").text('请您填写简单描述')
			}

			return false;
		}
	})
   
	//提交成功
function codefans(){
	var box=document.getElementById("success");
	box.style.display="none"; 
}