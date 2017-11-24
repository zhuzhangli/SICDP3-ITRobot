function check(name,reg,spanId,okInfo,errorInfo){
            var flag;
            var val = document.getElementsByName(name)[0].value;
            var oSpanNode = document.getElementById(spanId);
            if(reg.test(val)){
                oSpanNode.innerHTML = okInfo.fontcolor("green");
                flag = true;
            }else{
                oSpanNode.innerHTML = errorInfo.fontcolor("red");
                flag = false;
            }
            return flag;
        }
        
      //校验用户名
        function checkUser(){
        //定义正则表达式,a-z，长度为4,忽略大小写
            var reg =  /[^a-zA-Z\u4e00-\u9fa5,]/g;
            check("username",reg,"namespan","正确","错误");
        }