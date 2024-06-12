<%@page contentType="text/html" pageEncoding="UTF-8" %>

<nav class="nav nav-pills nav-justified">
    <a class="nav-item nav-link " href="product-list-manager">Danh sách sản phẩm</a>
    <a class="nav-item nav-link active" href="#">Thêm sản phẩm mới</a>
</nav>
        <div class="container">
            
                <form  class="needs-validation" novalidate action="addproduct" method="post" enctype="multipart/form-data">
                <div class="col-12" style="">
                    <h2>Thêm sản phẩm mới</h2>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        
                        <div class="input-group">
                            <input pattern="^.{1,100}$" type="text" class="form-control" id="foodName" placeholder="Tên sản phẩm" name="productName" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Tối đa từ 1-100 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^\d{1,8}(.\d{1,2})?$" class="form-control" id="foodPrice" placeholder="Giá sản phẩm" name="productPrice" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Giá tiền chỉ bao gồm chữ số
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^.{1,30}$" class="form-control" id="foodOrigin" placeholder="Loại sản phẩm" name="productType" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Trường này là bắt buộc, độ dài tối đa 30 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^.{1,30}$" class="form-control" id="foodOrigin" placeholder="Số lượng sản phẩm" name="productAmount" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Trường này là bắt buộc, độ dài tối đa 30 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^.{1,30}$" class="form-control" id="foodOrigin" placeholder="Xuất xứ sản phẩm" name="productOrigin" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Trường này là bắt buộc, độ dài tối đa 30 ký tự
                            </div>
                        </div>
                    </div>
                </div>
             <div class="row">
                <div class="input-field col s6">
                    <div class="input-group">
                        <input type="text" class="form-control" id="productDescription" placeholder="Mô tả sản phẩm" name="productDescription" aria-describedby="inputGroupPrepend">
                    </div>
                </div>
            </div>

                <div class="row">
                    <div class="input-field col s6">
                        <label for="productImg">Đường dẫn ảnh</label>
                        <div class="input-group">
                            <input id="avatar" type="file" class="form-control" name="productImg" value="" required>
<!--                              <input id="avatar" type="text" class="form-control" name="productImg" value="" required>-->
                            
                        </div>
                    </div>
                </div>
          
                <div class="row mt-3">
                    <div class="col-3">
                        <button class="btn btn-primary" type="submit">Thêm</button>
                    </div>
                    <div class="col-6">${thongbao}</div>
                </div>
            </form>
        </div>

<!-- Contact End -->

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
