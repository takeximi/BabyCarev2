<%-- 
    Document   : header
    Created on : May 14, 2024, 2:43:43 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>BabyCare - Daycare Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
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

    </head>
    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
        <div class="container-fluid border-bottom bg-light wow fadeIn" data-wow-delay="0.1s">
            <div class="container topbar bg-primary d-none d-lg-block py-2" style="border-radius: 0 40px">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>
                    </div>
                    <div class="top-link pe-2">
                        <a href="" class="btn btn-light btn-sm-square rounded-circle"><i class="fab fa-facebook-f text-secondary"></i></a>
                        <a href="" class="btn btn-light btn-sm-square rounded-circle"><i class="fab fa-twitter text-secondary"></i></a>
                        <a href="" class="btn btn-light btn-sm-square rounded-circle"><i class="fab fa-instagram text-secondary"></i></a>
                        <a href="" class="btn btn-light btn-sm-square rounded-circle me-0"><i class="fab fa-linkedin-in text-secondary"></i></a>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light navbar-expand-xl py-3">
                    <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">Baby<span class="text-secondary">Care</span></h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="index.html" class="nav-item nav-link active">Home</a>
                            <a href="about.html" class="nav-item nav-link">About</a>
                            <a href="service.html" class="nav-item nav-link">Services</a>
                            <a href="product" class="nav-item nav-link">Sản Phẩm</a>
                            <a href="event.html" class="nav-item nav-link">Events</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="blog.html" class="dropdown-item">Our Blog</a>
                                    <a href="team.html" class="dropdown-item">Our Team</a>
                                    <a href="#" class="dropdown-item">Trang bán hàng</a>
                                    <a href="404.html" class="dropdown-item">404 Page</a>
                                </div>
                            </div>
                            <c:if test="${not sessionScope.user.userId.startsWith('E') and not sessionScope.user.userId.startsWith('C') and not sessionScope.user.userId.startsWith('A')}">
                                <a href="addBrand" class="nav-item nav-link">Đăng kí CTV</a>
                            </c:if>
                            <c:if test="${not sessionScope.user.userId.startsWith('E') and not sessionScope.user.userId.startsWith('A')}">
                                <a href="cart.jsp" class="nav-item nav-link">Giỏ Hàng</a>
                            </c:if>
                            
                        </div>
                        <div class="d-flex me-4">
                            <c:if test="${sessionScope.user==null}">
                                <a href="login.jsp" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Đăng nhập<i
                                        class="bi bi-arrow-right"></i></a>
                            </c:if>
                            <c:if test="${sessionScope.user!=null}">
                                
                                <div class="nav-item dropdown" >
                                    <div class=" d-flex align-items-center" data-bs-toggle="dropdown">
                                        <img class="img-avatar text-white ms-lg-5" style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;" src="img/${sessionScope.user.avatar}" alt="">
                                        <a href="#" class="nav-link bg-primary text-white px-5 ms-lg-3">
                                            ${sessionScope.user.firstname} ${sessionScope.user.lastname}
                                        </a>
                                    </div>           
                                        <div class="dropdown-menu m-0" style="left: 131px;">
                                        <a href="profile" class="dropdown-item">Quản lí thông tin cá nhân</a>
                                        <a href="changepass" class="dropdown-item">Đổi mật khẩu</a>
                                        <a href="cart.jsp" class="dropdown-item">Giỏ hàng</a>

                                        <c:if test="${sessionScope.user.userId.startsWith('E')}">
                                            <a href="order-list-manager" class="dropdown-item">Quản lí dịch vụ</a>                                          
                                            <a href="listdiscount"  class="dropdown-item">Quản lí mã giảm giá</a>
                                        </c:if>
                                            <c:if test="${sessionScope.user.userId.startsWith('C')}">
                                                <a href="product-list-manager" class="dropdown-item">Quản lí sản phẩm</a>
                                            <a href="order-list-manager" class="dropdown-item">Quản lí đơn hàng</a>                                          
                                            <a href="listdiscount"  class="dropdown-item">Quản lí mã giảm giá</a>
                                            
                                        </c:if>
                                        
                                        <c:if test="${sessionScope.user.userId.startsWith('A')}">                           
                                            <a href="manage-emp-account" class="dropdown-item">Quản lí tài khoản nhân viên</a>
                                            <a href="manage-cus-account" class="dropdown-item">Quản lí tài khoản khách hàng</a>
                                            <a href="statistics"  class="dropdown-item">Thống kê bán hàng</a>
                                        </c:if>

                                        <a href="logout" class="dropdown-item text-danger">Đăng xuất</a>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->


        <!-- Modal Search Start -->
        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body d-flex align-items-center">
                        <div class="input-group w-75 mx-auto d-flex">
                            <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                            <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</html>
