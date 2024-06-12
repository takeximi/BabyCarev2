<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Target Material Design Bootstrap Admin Template</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@600;700&family=Montserrat:wght@200;400;600&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

            <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

            <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection" />
        <!-- Bootstrap Styles-->
        <link href="css/bootstrap.css" rel="stylesheet" />
        <!-- FontAwesome Styles-->
        <link href="css/font-awesome.css" rel="stylesheet" />
        <!-- Morris Chart Styles-->
        <link href="js/morris/morris-0.4.3.min.css" rel="stylesheet" />
        <!-- Custom Styles-->
        <link href="css/custom-styles.css" rel="stylesheet" />
        <!-- Google Fonts-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="js/Lightweight-Chart/cssCharts.css"> 
    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default top-navbar" style="display: flex; justify-content: flex-start" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand waves-effect waves-dark" href="index.html"><i class="large material-icons">track_changes</i> <strong>target</strong></a>

                    <div id="sideNav" href=""><i class="material-icons dp48">toc</i></div>
                </div>

               
            </nav>

            <!--/. NAV TOP  -->
            <!--/. NAV TOP  -->
            <nav class="navbar-default navbar-side" role="navigation">
                <div class="sidebar-collapse">
                    <c:if test="${sessionScope.user!=null}">


                        <div class=" d-flex align-items-center" >
                            <img class="img-avatar text-white ms-lg-5" style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;" src="img/${sessionScope.user.avatar}" alt="">
                                <a href="#" class="nav-link bg-primary text-white px-5 ms-lg-3">
                                    ${sessionScope.user.firstname} ${sessionScope.user.lastname}
                                </a>
                        </div>           

                    </c:if>
                    <ul class="nav" id="main-menu">

                        <li>
                            <a href="statistics"  class="dropdown-item">Thống kê bán hàng</a>       
                        </li>                            
                        <li>

                            <a href="" class="dropdown-item">Quản lí tài khoản</a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="manage-emp-account">quản lý tài khoản nhân viên</a>
                                </li>
                                <li>
                                    <a href="manage-ctv-account">quản lý tài khoản cộng tác viên</a>
                                </li>
                                <li>
                                    <a href="#">quản lý tài khoản khách hàng</a>
                                </li>

                            </ul>
                        </li>

                    </ul>

                </div>

            </nav>
        </div>
    </body>
    </html>
