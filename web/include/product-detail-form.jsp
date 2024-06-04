<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: 'Arial', sans-serif; /* Sử dụng phông chữ dễ đọc */
                background-color: #f4f4f4; /* Màu nền nhẹ nhàng */
                color: #333; /* Màu chữ tối giản */
                line-height: 1.6; /* Khoảng cách giữa các dòng */
                padding: 20px; /* Thêm padding cho body */
            }

            /* Cập nhật cho các container */
            .product-container, .store-info, .product-details {
                border: 1px solid #ddd; /* Màu viền nhẹ hơn */
                background-color: #fff; /* Nền trắng cho các phần */
                padding: 20px;
                margin-top: 20px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1); /* Bóng mềm hơn */
                border-radius: 8px; /* Viền tròn */
            }
            .product-image img, .product-details img {
                width: 100%;
                height: auto;
                border-radius: 4px; /* Viền tròn cho ảnh */
            }

            .store-info img {
                width: 60px;
                height: 60px;
                border-radius: 50%; /* Làm tròn ảnh */
            }

            /* Cập nhật nút */
            .button {
                padding: 10px 20px; /* Đệm rộng hơn */
                border: none;
                background-color: #FF4880; /* Màu nền xanh lá */
                color: white;
                cursor: pointer;
                font-size: 16px; /* Cỡ chữ lớn hơn */
                border-radius: 4px; /* Viền tròn */
                margin: 5px 0; /* Thêm khoảng cách giữa các nút */
                transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
            }

            .button:hover {
                background-color: #45a049; /* Màu khi hover */
            }

            /* Cập nhật cho các phần thống kê và tiêu đề */
            .stats, h2, h3 {
                margin-bottom: 10px; /* Khoảng cách giữa các phần tử */
            }

            h2, h3 {
                color: #444; /* Màu chữ đậm hơn */
            }

            /* Thêm một số cấu trúc rõ ràng hơn cho các thành phần */
            .row {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-bottom: 20px;
            }

            .col-md-6, .col-md-9, .col-md-1, .col-md-2 {
                flex: 1; /* Đảm bảo các cột chiếm đủ không gian */
            }

            /* Đặc biệt cho các thành phần sản phẩm */
            .price {
                font-weight: bold;
                color: #E63946; /* Màu đỏ cho giá */
            }

            .product-rating {
                color: #2a9d8f; /* Màu xanh ngọc cho đánh giá */
            }

            .store-info h2 {
                font-size: 24px; /* Cỡ chữ lớn hơn cho tên cửa hàng */
                color: #333; /* Màu chữ tối */
                margin: 0; /* Bỏ lề mặc định */
                padding-right: 20px; /* Khoảng cách với phần tiếp theo */
            }
            .store-info .stats {
                margin: 5px 10px; /* Khoảng cách giữa các thống kê */
                color: #666; /* Màu chữ cho các thống kê */
                font-size: 14px; /* Cỡ chữ nhỏ cho thống kê */
            }
            .store-info img {
                width: 180px; /* Điều chỉnh kích thước ảnh */
                height: 180px; /* Điều chỉnh kích thước ảnh */
                border-radius: 50%; 
            }
            .shopee-input-quantity {
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: inline-flex;
                -webkit-box-align: center;
                -webkit-align-items: center;
                -moz-box-align: center;
                -ms-flex-align: center;
                align-items: center;
            }

            /* Responsive design */
            @media (max-width: 768px) {
                .row {
                    flex-direction: column; /* Stack các cột khi màn hình nhỏ */
                }
            }
        </style>

    </head>
    <body>
        <div class="container">
            <!-- Thông tin sản phẩm -->
            <div class="product-container">
                <div class="row">
                    <div class="col-md-6 product-image">
                        <img src="img/${product.img}" alt="Sản phẩm">
                    </div>
                    <div class="col-md-6">
                        <h2>${product.productName}</h2>
                        <p class="price" style="color: red; font-size: 24px;">${product.getPriceString()}</p>

                            
                                <form action="additem">

                                    <div class="shopee-input-quantity">
                                        <input type="button" class="add-sl shopee-button-outline" title="Bớt"
                                               onclick="var qty_el = document.getElementById('qty');
                                                           var qty = qty_el.value;
                                                           if (!isNaN(qty) && qty > 1)
                                                               qty_el.value--;
                                                           return false;" value="-">
                                        <input aria-label="Number" name="ammount" id="qty" type="text" size="1"
                                               class="soluong_12 shopee-button-outline shopee-button-outline-mid" value="1"
                                               readonly>
                                        <input type="button" class="sub-sl shopee-button-outline" title="Thêm"
                                               onclick="var qty_el = document.getElementById('qty');
                                                           var qty = qty_el.value;
                                                           if (!isNaN(qty))
                                                               qty_el.value++;
                                                           return false;" value="+">
                                                                <p style="font-size: 16px;margin-bottom: 0px; margin-left: 12px ">${product.productAmount} sản phẩm có sẵn</p>
                                    </div> 
                                    <div class="buttons">
                                        <div>
                                            <input name="id" type="text" hidden="" value="${product.productId}">
                                            <input class="btn btn-primary" type="submit" value="Thêm Vào Giỏ Hàng">
                                            <a href="" class="btn btn-primary">Mua Ngay</a>
                                        </div>
                                        <h3>${message}</h3>
                                    </div>
                                </form>

                    </div>
                </div>
            </div>
            <!-- Thông tin gian hàng -->
            <div class="store-info">
                <div class="row">
                    <div class="col-md-1">
                        <img src="img/${brand.brandLogo}" alt="Store Logo">
                    </div>
                    <div class="col-md-9">
                        <h1>${brand.brandName}</h1>
                        <p class="stats">Đánh Giá: 125</p>
                        <p class="stats">Sản Phẩm: 87</p>
                        <p class="stats">Tỉ Lệ Phản Hồi: 100%</p>
                        <p class="stats">Thời Gian Phản Hồi: trong vài giờ</p>
                        <p class="stats">Tham Gia: 48 ngày trước</p>
                        <p class="stats">Người Theo Dõi: 688</p>
                    </div>
                    <div class="col-md-2 text-center">
                        <button class="button">Xem Shop</button>
                    </div>
                </div>
            </div>
            <!-- Chi tiết sản phẩm -->
            <div class="product-details">
                <h3>CHI TIẾT SẢN PHẨM</h3>
                <p>Xuất xứ: ${product.origin}</p>
                <p>Kho hàng: 89954</p>
                <p>Gửi từ: ${brand.brandAddess}</p>
            </div>
            <!-- Mô tả sản phẩm -->
            <div class="product-details">
                <h3>MÔ TẢ SẢN PHẨM</h3>
                <p class="auto-line-break">${product.productDescription}</p>
                
            </div>
        </div>
                 <script>
        document.addEventListener("DOMContentLoaded", function() {
            const paragraphs = document.querySelectorAll('.auto-line-break');
            paragraphs.forEach(p => {
                p.innerHTML = p.innerHTML.replace(/\./g, '.<br>');
            });
        });
    </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
