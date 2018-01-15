<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 注册</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="css/login/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/login/style.css?v=4.1.0" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎注册 H+</h3>
            <p>创建一个H+新账户</p>
            <form class="m-t" role="form">
                <div class="form-group">
                    <input type="text" class="form-control" id="me" placeholder="请输入用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" placeholder="请输入密码" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="repassword" placeholder="请再次输入密码" required="">
                </div>
                <div class="form-group text-left">
                    <div class="checkbox i-checks">
                        <label class="no-padding">
                            <input type="checkbox"><i></i> 我同意注册协议</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b" id="register">注 册</button>

                <p class="text-muted text-center"><small>已经有账户了？</small><a href="login.html">点此登录</a>
                </p>

            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/login/bootstrap.min.js?v=3.3.6"></script>
    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
        
        $("#register").click(function(){
        	//var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
        	var us = /^\w{2,10}$/;
        	var pw = /^\w{6,16}$/;
        	var rpw = $("#repassword").val();
        	if(us.test($("#me").val())&&pw.test($("#password").val())&&(rpw==($("#password").val()))){
        		$.ajax({
        			type:"POST",
        			url:"/org.xjtusicd3.partner/saveRegister.html",
        			data:{
        				"name":$("#me").val(),
        				"password":$("#password").val()
        			},
        			dataType:"json",
        			success:function(data){
        				if(data=="1"){
        					$(".spa1").text('该用户名已被注册');
        				}else if(data=="2"){
        					$(".spa1").text('该邮箱还未验证');
        				}else{
        					document.getElementById("modal_bg").style.display="block";
        					document.getElementById("myModal").style.visibility = "visible";
        				}
        			}
        		})

        		return true;
        	}else{
        		if($("#me").val()==""){
        			$(".spa1").text('请填写注册的用户名');
        		}
        		if($("#user").val()==""){
        			$(".spa2").text('请填写用户名');
        		}
        		if($("#password").val()==""){
        			$(".spa3").text('请填写密码');
        		}
        		if($("#repassword").val()==""){
        			$(".spa4").text('请再次填写密码');
        		}
        		return false;
        	}
        })
    </script>

    
    

</body>

</html>
