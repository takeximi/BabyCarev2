<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            line-height: 1.6;
            padding: 20px;
        }

        .product-container, .store-info, .product-details {
            border: 1px solid #ddd;
            background-color: #fff;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        .product-image img, .product-details img {
            width: 100%;
            height: auto;
            border-radius: 4px;
        }

        .store-info img {
            width: 60px;
            height: 60px;
            border-radius: 50%;
        }

        .button {
            padding: 10px 20px;
            border: none;
            background-color: #FF4880;
            color: white;
            cursor: pointer;
            font-size: 16px;
            border-radius: 4px;
            margin: 5px 0;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #45a049;
        }

        .stats, h2, h3 {
            margin-bottom: 10px;
        }

        h2, h3 {
            color: #444;
        }

        .row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .col-md-6, .col-md-9, .col-md-1, .col-md-2 {
            flex: 1;
        }

        .price {
            font-weight: bold;
            color: #E63946;
        }

        .product-rating {
            color: #2a9d8f;
        }

        .store-info h2 {
            font-size: 24px;
            color: #333;
            margin: 0;
            padding-right: 20px;
        }
        
        .store-info .stats {
            margin: 5px 10px;
            color: #666;
            font-size: 14px;
        }
        
        .store-info img {
            width: 180px;
            height: 180px;
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

        @media (max-width: 768px) {
            .row {
                flex-direction: column;
            }
        }

        .comment-section, .comment-list {
            border: 1px solid #ddd;
            background-color: #fff;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        .comment-item {
            margin-bottom: 20px;
        }

        .comment-item p {
            margin: 5px 0;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input[type="text"], .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .form-group input[type="submit"] {
            background-color: #FF4880;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
        }

        .form-group input[type="submit"]:hover {
            background-color: #45a049;
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
                            <input aria-label="Number" name="amount" id="qty" type="text" size="1"
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
            <p>Kho hàng: ${product.productAmount}</p>
            <p>Gửi từ: ${brand.brandAddess}</p>
        </div>
        <!-- Mô tả sản phẩm -->
        <div class="product-details">
            <h3>MÔ TẢ SẢN PHẨM</h3>
            <p class="auto-line-break">${product.productDescription}</p>
        </div>
        <!-- Thêm bình luận sản phẩm -->
        
        <!-- Hiển thị bình luận sản phẩm -->
        <div class="comment-list product-details">
            <h3>BÌNH LUẬN SẢN PHẨM</h3>
            <c:forEach var="comment" items="${listComments}">
                <div class="comment-item">
                    <p class="stats">${comment.createdAt}</p>
                    <p>${comment.comment}</p>
                    <!-- Hiển thị ảnh bình luận nếu có -->
                    <c:if test="${not empty comment.commentImg}">
                        <img src="img/${comment.commentImg}" alt="Comment Image" width="200">
                    </c:if>
                    <hr>
                </div>
            </c:forEach>
        </div>
          <!-- Thêm bình luận sản phẩm -->
        <div class="comment-section product-details">
            <h3>THÊM BÌNH LUẬN</h3>
            <form action="getProductDetail" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${product.productId}">
                <div class="form-group">
                    <label for="comment">Bình luận:</label>
                    <textarea id="comment" name="comment" class="form-control" rows="3" required></textarea>
                </div>
                <input type="file" name="commentImg">
                <div class="form-group">
                    <input type="submit" class="button" value="Gửi Bình Luận">
                </div>
            </form>
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
