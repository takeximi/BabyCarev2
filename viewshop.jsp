<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }

    .product-image img {
        max-width: 120px; /* Điều chỉnh kích thước hình ảnh sản phẩm */
        border-radius: 8px;
    }

    .store-info {
        display: flex;
        align-items: center;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }

    .store-info img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        margin-right: 20px;
    }

    .store-info h1 {
        margin: 0;
        font-size: 24px;
    }

    .store-info .stats {
        margin: 5px 0;
    }

    .product-list {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(120px, 1fr)); /* Tối đa 6 cột trên một hàng */
        gap: 20px; /* Khoảng cách giữa các sản phẩm */
        list-style: none;
        padding: 0;
    }

    .product {
        background-color: #fff;
        padding: 10px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
    }

    .product-details {
        margin-top: 10px;
    }

    .product-title {
        font-size: 14px; /* Điều chỉnh kích thước tiêu đề sản phẩm */
        margin-bottom: 5px;
        color: #333;
    }

    .product-price {
        font-size: 12px; /* Điều chỉnh kích thước giá sản phẩm */
        color: #f57224; /* Màu cam của Shopee */
    }

    .product-item-link {
        text-decoration: none;
    }

    .product-item-link .product-item {
        border: 1px solid #ddd;
        transition: transform 0.3s ease, border-color 0.3s ease;
        height: 250px; /* Cố định chiều cao của sản phẩm */
        display: flex;
        flex-direction: column;
    }

    .product-item-link:hover .product-item,
    .product-item-link.active .product-item {
        transform: scale(1.05);
        border-color: #ff4880; /* Màu viền khi active */
    }

    .product-image,
    .product-details {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .product-image {
        border-bottom: 1px solid #ddd; /* Đường viền ngăn cách */
    }

    .product-image img {
        width: 100%; /* Đặt chiều rộng của hình ảnh để chiếm hết chiều rộng của khung */
        height: 120px; /* Đặt chiều cao của hình ảnh để chiếm hết chiều cao của khung */
        object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
    }

    .product-details {
        position: relative;
        display: block;
        text-align: center;
        padding: 10px;
    }

    .product-name {
        font-size: 12px;
        text-align: left;
    }

    .product-price {
        position: absolute;
        top: 95px;
        font-size: 12px;
    }
</style>

</head>
<body>

<div class="container-fluid d-none d-lg-block pt-5">
    <div class="store-info">
        <img src="img/brand/${brand.brandLogo}" alt="Store Logo">
        <div>
            <h1>${brand.brandName}</h1>
            <p class="stats">Đánh Giá: 125</p>
            <p class="stats">Sản Phẩm: 87</p>
            <p class="stats">Tỉ Lệ Phản Hồi: 100%</p>
            <p class="stats">Thời Gian Phản Hồi: trong vài giờ</p>
            <p class="stats">Tham Gia: 48 ngày trước</p>
            <p class="stats">Người Theo Dõi: 688</p>
        </div>
    </div>
    <div class="container">
        <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
            <h6 class="text-primary text-uppercase">Sản Phẩm</h6>
            <form action="SearchControl?index=1" method="post">
                <input class="searchBox" id="myInput" type="text" name="txtSearch" size="15" required>
                <input class="serachButton" type="submit" name="btnGo" value="Go">
            </form>
        </div>
        <div class="product-list" id="productContainer">
            <c:forEach var="product" items="${listProduct}">
                <a href="getProductDetail?id=${product.productId}" class="product-item-link">
                    <div class="product-item position-relative bg-white d-flex flex-column text-center">
                        <div class="product-image">
                            <img class="img-fluid" src="img/${product.img}" alt="">
                        </div>
                        <div class="product-details">
                            <h6 class="product-name">${product.productName}</h6>
                            <h5 class="text-primary mb-0 product-price">₫ ${product.getPriceString()}</h5>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
        <div id="pagination" class="pagination d-flex justify-content-center mt-4">
            <!-- Pagination buttons will be added here -->
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const products = Array.from(document.querySelectorAll(".product-item-link"));
        const productsPerPage = 30;
        const pagination = document.getElementById("pagination");
        let currentPage = 1;
        const totalPages = Math.ceil(products.length / productsPerPage);

        function showPage(page) {
            products.forEach((product, index) => {
                if (index >= (page - 1) * productsPerPage && index < page * productsPerPage) {
                    product.style.display = "block";
                } else {
                    product.style.display = "none";
                }
            });
        }

        function createPagination() {
            pagination.innerHTML = "";
            for (let i = 1; i <= totalPages; i++) {
                const button = document.createElement("button");
                button.textContent = i;
                button.classList.add("page-btn");
                if (i === currentPage)
                    button.classList.add("active");
                button.addEventListener("click", function () {
                    currentPage = i;
                    showPage(currentPage);
                    document.querySelector(".pagination .active").classList.remove("active");
                    button.classList.add("active");
                });
                pagination.appendChild(button);
            }
        }

        showPage(currentPage);
        createPagination();
    });
</script>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#productContainer .product-item-link").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
            });
        });

        // Thêm lớp active khi click vào sản phẩm
        $(".product-item-link").on("click", function () {
            $(".product-item-link").removeClass("active");
            $(this).addClass("active");
        });
    });
</script>
<p>Shop Address: 123 Main Street</p>
        <p>Shop Hours: 9 AM - 9 PM</p>
    </div>
</body>
</html>