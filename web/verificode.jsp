
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    
    <style>
        .verificode-form {
            display: hidden;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
            z-index: 9999;
        }
    </style>
</head>
<body>
<%@include file="include/header.jsp" %>
<%@include file="include/register-form.jsp" %>


<div class="verificode-form" id="verificodeForm">
        <span>Hệ thống đã gửi ma kích hoạt đen Email cua ban. </span>
        <span>Xin vui lòng kiem tra Email đe lay ma kich hoat tai khoan cua ban. </span>
            <form action="VerificodeServlet" method="post" class="Log-reg-block sky-form">
        <div class="input-group">
        <input type="text" name="authcode" class="form-control margin-top-20" >
        </div>
        <input type="submit" value="Kich hoat" class="btn-u btn-u-sea-shop margin-top-20">
            </form>
        <h3 class="text-danger">${thongbao}</h3>
    </div>
    
    <script>
        document.getElementById('registerBtn').addEventListener('click', function() {
            document.getElementById('overlay').style.display = 'block'; // Hiển thị overlay
            document.getElementById('verificodeForm').style.display = 'block'; // Hiển thị form đăng ký
        });
    </script>
    
    
</body>
</html>