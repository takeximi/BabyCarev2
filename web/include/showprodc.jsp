<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
s}
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
                        
/* Responsive design */
@media (max-width: 768px) {
    .row {
        flex-direction: column; /* Stack các cột khi màn hình nhỏ */
    }
}
    </style>
<body>
    <div class="container">
        <!-- Thông tin sản phẩm -->
        <div class="product-container">
            <div class="row">
                <div class="col-md-6 product-image">
                    <img src="img/sale.png" alt="Sản phẩm">
                </div>
                <div class="col-md-6">
                    <h2><strong>Thùng sữa tươi tiệt trùng Vinamilk Có đường hộp 110ml</strong></h2>
                    <p class="price" style="color: red; font-size: 24px;">231.000đ</p>
                    <p class="product-rating">Đánh giá: ★★★★★ (1 Đánh Giá)</p>
                    <p>Số lượng còn lại: 4 sản phẩm</p>
                    <div class="quantity">
                        <label for="quantity">Số lượng:</label>
                        <input type="number" id="quantity" value="1" min="1" max="4">
                    </div>
                    <div class="buy-buttons">
                        <button class="button">Thêm Vào Giỏ Hàng</button>
                        <button class="button">Mua Ngay</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Thông tin gian hàng -->
        <div class="store-info">
            <div class="row">
                <div class="col-md-1">
                    <img src="img/sale.png" alt="Store Logo">
                </div>
                <div class="col-md-9">
                    <h2>delicacy18.vn</h2>
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
            <p>Danh Mục: Shopee > Giày Dép Nam > Xăng-dan và Dép > Dép Mát-xa</p>
            <p>Chất liệu: Khác, Cao su, Sợi tổng hợp</p>
            <p>Xuất xứ: Trung Quốc</p>
            <p>Kho hàng: 89954</p>
            <p>Gửi từ: Nước ngoài</p>
        </div>
        <!-- Mô tả sản phẩm -->
        <div class="product-details">
            <h3>MÔ TẢ SẢN PHẨM</h3>
            <p>Đẹp đi trong nhà dành cho học sinh đi ra ngoài hợp lệ để đi khắp không trượt trợt. Dép đi trong nhà và đi trong nhà.</p>
            <p>Kích thước: 36-37 [Một kích thước nhỏ hơn]</p>
            <p>Chất liệu trên: PVC</p>
            <p>Phong cách: Giản dị</p>
            <p>Vật liệu duy nhất: PVC</p>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
