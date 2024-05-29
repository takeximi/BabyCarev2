<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet"> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    
    <style>
        body {  
            background: #ffecf2;
            width: 100%;
        }
        
        #box {  
            border: 1px solid rgb(200, 200, 200);   
            box-shadow: rgba(0, 0, 0, 0.1) 0px 5px 5px 2px; 
            background: rgba(200, 200, 200, 0.1);   
            border-radius: 4px; 
            top: 150px;
            right: 160px;
        }
        .logo{
            top : 180px;
            right: -200px;
        }
        
        h2 {    
            text-align: center;  
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container-fluid"> 
        <div class="row-fluid"> 
            <div class="logo col-md-4">
                <img class="img-fluid" src="images/logo.png" alt="">
                <h1 class="navbar-brand text-primary display-6 " style="font-size: 46px; color: #ff4880">
                    Baby<span class="text-secondary" style="color: #4d65f9">Care</span>
                </h1>
            </div>
            <div class="col-md-offset-4 col-md-4" id="box"> 
                <form class="form-horizontal" action="login" method="post" id="login_form"> 
                    <fieldset> 
                        <div class="form-group" style="margin-top: 20px"> 
                            <div class="col-md-12"> 
                                <div class="input-group"> 
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user" ></i></span> 
                                    <input name="username" placeholder="Username" autocomplete="on" class="form-control" type="text" value="${cookie.username.value}"> 
                                </div> 
                            </div> 
                        </div> 
                        <div class="form-group"> 
                            <div class="col-md-12"> 
                                <div class="input-group"> 
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                                    <input name="password" type="password" autocomplete="off" placeholder="Password" class="form-control" type="text" value="${cookie.password.value}"> 
                                </div> 
                            </div> 
                        </div> 
                        <h4 class="bg-warning  text-danger " style="text-align: center;background-color: #fa6342;padding: 2px 2px; font-size: 16px;margin:2px 0px "> ${thongbao}</h4>
                        <div class="form-group"> 
                            <div class="col-md-12"> 
                                <button type="submit" value="Login" class="btn btn-md btn-danger center-block">Đăng nhập</button>        
                                <a href="forgotpassword.jsp" class="text-center center-block" style="margin-top: 12px; display: block;">Quên mật khẩu?</a>   
                            </div> 
                            
                        </div> 
                    </fieldset> 
                </form> 
                <form action="register.jsp" method="get">
                    <button id="registerBtn" type="submit" class="btn btn-md btn-danger center-block" style="background: blue; margin-bottom:  20px;">Tạo tài khoản mới</button>
                </form>
            </div> 
        </div>
    </div>
    
</body>
</html>
