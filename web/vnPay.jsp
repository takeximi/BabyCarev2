    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="include/header.jsp" %>
    <html lang="en">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="description" content="">
            <meta name="author" content="">
            <title>Tạo mới đơn hàng</title>
            <link href="assets/bootstrap.min.css" rel="stylesheet"/>
            <link href="assets/jumbotron-narrow.css" rel="stylesheet">      
            <script src="assets/jquery-1.11.3.min.js"></script>
            <style>/* Thiết lập font chữ và margin cho toàn bộ trang */
body {
    font-family: Arial, sans-serif;
    margin: 20px;
}

/* Định dạng header */
.header {
    text-align: center;
    margin-bottom: 20px;
}

.header h3 {
    font-size: 24px;
}

/* Định dạng container chính */
.container {
    width: 80%;
    margin: 0 auto;
}

/* Định dạng các input và form */
.form-group {
    margin-bottom: 20px;
}

/* Định dạng các radio và label */
.form-group h5 {
    font-size: 16px;
    margin-bottom: 10px;
}

input[type=radio] {
    margin-right: 10px;
}

/* Định dạng footer */
.footer {
    text-align: center;
    margin-top: 50px;
}

.footer p {
    font-size: 14px;
    color: #888;
}

/* Định dạng button thanh toán */
.btn-default {
    background-color: #007bff;
    color: #fff;
    border-color: #007bff;
    padding: 10px 20px;
    font-size: 18px;
}

.btn-default:hover {
    background-color: #0056b3;
    border-color: #0056b3;
    color: #fff;
}
</style>
        </head>
        
        <body>
            <div class="container">
                <div class="header clearfix">
                    <h3 class="text-muted"></h3>
                </div>
                <h1 class="display-3 mb-4" style=" font-size: 26px">Dịch vụ: ${serviceName}</h1>
                
                <div class="table-responsive">
                    <form action="submitUrl" id="frmCreateOrder" method="post">        
                        <div class="form-group">
                            <label for="amount">Số tiền</label>
                            <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="number" value="${servicePrice}" readonly />
                        </div>
                        <h4>Chọn phương thức thanh toán</h4>
                        <div class="form-group">
                            <h5>Cách 1: Chuyển hướng sang Cổng VNPAY chọn phương thức thanh toán</h5>
                            <input type="radio" checked="true" id="bankCode" name="bankCode" value="">
                            <label for="bankCode">Cổng thanh toán VNPAYQR</label><br>
                            <h5>Cách 2: Tách phương thức tại site của đơn vị kết nối</h5>
                            <input type="radio" id="bankCode" name="bankCode" value="VNPAYQR">
                            <label for="bankCode">Thanh toán bằng ứng dụng hỗ trợ VNPAYQR</label><br>
                            <input type="radio" id="bankCode" name="bankCode" value="VNBANK">
                            <label for="bankCode">Thanh toán qua thẻ ATM/Tài khoản nội địa</label><br>
                            <input type="radio" id="bankCode" name="bankCode" value="INTCARD">
                            <label for="bankCode">Thanh toán qua thẻ quốc tế</label><br>
                        </div>
                        <div class="form-group">
                            <h5>Chọn ngôn ngữ giao diện thanh toán:</h5>
                            <input type="radio" id="language" checked="true" name="language" value="vn">
                            <label for="language">Tiếng việt</label><br>
                            <input type="radio" id="language" name="language" value="en">
                            <label for="language">Tiếng anh</label><br>
                        </div>
                        <button type="submit" class="btn btn-default" href="#">Thanh toán</button>
                    </form>
                </div>
                <p>&nbsp;</p>
                <footer class="footer">
                    <p>&copy; VNPAY 2020</p>
                </footer>
            </div>
            <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
            <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
            <script type="text/javascript">
                $("#frmCreateOrder").submit(function () {
                    var postData = $("#frmCreateOrder").serialize();
                    var submitUrl = $("#frmCreateOrder").attr("action");
                    $.ajax({
                        type: "POST",
                        url: submitUrl,
                        data: postData,
                        dataType: 'JSON',
                        success: function (x) {
                            if (x.code === '00') {
                                if (window.vnpay) {
                                    vnpay.open({width: 768, height: 600, url: x.data});
                                } else {
                                    location.href = x.data;
                                }
                                return false;
                            } else {
                                alert(x.Message);
                            }
                        }
                    });
                    return false;
                });
            </script>       
        </body>
    </html>
   <%@include file="include/footer.jsp" %>