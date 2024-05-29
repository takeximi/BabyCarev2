<%-- 
    Document   : forgotPass
    Created on : May 17, 2024, 12:25:06 AM
    Author     : ADMIN
--%>
<!--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="forgotPasswordModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Quên mật khẩu</h4>
                </div>
                <div class="modal-body">
                    <form action="forgotPassword" method="post">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email của bạn" required>
                        </div>
                        <h3 class="text-danger">${thongbao}</h3>
                        <button type="submit" class="btn btn-primary">Gửi</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
    </body>
</html>-->

<%-- 
    Document   : forgotPass
    Created on : May 17, 2024, 12:25:06 AM
    Author     : ADMIN
--%>

<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color:#ffecf2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
/*                background: linear-gradient(45deg,#003cff,#00e1ff);*/
            }
            .modal-dialog {
                max-width: 450px;
                width: 100%;
            }
            .modal-header {
                background-color: #007bff;
                color: white;
                border-bottom: none;
                padding: 1.5rem 2rem;
                border-top-left-radius: 0.5rem;
                border-top-right-radius: 0.5rem;
            }
            .modal-title {
                margin: 0 auto;
                font-weight: 500;
                font-size: 1.5rem;
            }
            .modal-body {
                padding: 2rem;
                background-color: #fff;
                border-bottom-left-radius: 0.5rem;
                border-bottom-right-radius: 0.5rem;
            }
            .form-control {
                border-radius: 0.375rem;
                padding: 0.75rem;
                font-size: 1rem;
                margin-bottom: 1rem;
            }
            .btn-primary {
                background-color: #007bff;
                border: none;
                border-radius: 0.375rem;
                font-size: 1rem;
                padding: 0.75rem;
                transition: background-color 0.3s ease;
                width: 100%;
                font-weight: 500;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
            .text-danger {
                text-align: center;
                margin-top: 1rem;
                font-weight: 500;
            }
            .close {
                color: white;
                opacity: 1;
                font-size: 1.25rem;
            }
            .close:hover {
                color: #ccc;
            }
            .modal-content {
                border-radius: 0.5rem;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
            }
            .modal-backdrop {
                background-color: rgba(0, 0, 0, 0.5);
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
        <div id="forgotPasswordModal" class="modal fade show" role="dialog" style="display: block;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Quên mật khẩu</h4>
                    </div>
                    <div class="modal-body">
                        <form action="forgotPassword" method="post">
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email của bạn" required>
                            </div>
                            <h3 class="text-danger">${thongbao}</h3>
                            <button type="submit" class="btn btn-primary">Gửi</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $('#forgotPasswordModal').modal('show');
            });
        </script>
    </body>
</html>-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #ffecf2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .modal-dialog {
                max-width: 450px;
                width: 100%;
            }
            .modal-header {
                background-color: #007bff;
                color: white;
                border-bottom: none;
                padding: 1.5rem 2rem;
                border-top-left-radius: 0.5rem;
                border-top-right-radius: 0.5rem;
            }
            .modal-title {
                margin: 0 auto;
                font-weight: 500;
                font-size: 1.5rem;
            }
            .modal-body {
                padding: 2rem;
                background-color: #ffecf2;
                border-bottom-left-radius: 0.5rem;
                border-bottom-right-radius: 0.5rem;
            }
            .form-control {
                border-radius: 0.375rem;
                padding: 0.75rem;
                font-size: 1rem;
                margin-bottom: 1rem;
            }
            .btn-primary {
                background-color: #007bff;
                border: none;
                border-radius: 0.375rem;
                font-size: 1rem;
                padding: 0.75rem;
                transition: background-color 0.3s ease;
                width: 100%;
                font-weight: 500;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
            .text-danger {
                text-align: center;
                margin-top: 1rem;
                font-weight: 500;
            }
            .close {
                color: white;
                opacity: 1;
                font-size: 1.25rem;
            }
            .close:hover {
                color: #ccc;
            }
            .modal-content {
                border-radius: 0.5rem;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                background-color: #4d65f9; /* Match the background color of the page */
            }
            .modal-backdrop {
                background-color: rgba(0, 0, 0, 0.5);
            }
            /*#ffecf2*/
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
                <div id="forgotPasswordModal" class="modal fade show" role="dialog" style="display: block;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Quên mật khẩu</h4>
                            </div>
                            <div class="modal-body">
                                <form action="forgotPassword" method="post">
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email của bạn" required>
                                    </div>
                                    <h3 class="text-danger">${thongbao}</h3>
                                    <button type="submit" class="btn btn-primary">Gửi</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $('#forgotPasswordModal').modal('show');
            });
        </script>
    </body>
</html>
