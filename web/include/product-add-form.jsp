<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="container-fluid pt-5 ">

    <div id="page-wrapper">
        <div class="container">
            <form class="needs-validation mt-3 col s12" novalidate action="addfood" method="post" enctype="multipart/form-data">
                <div class="col-12" style="">
                    <h2>Thêm sản phẩm mới</h2>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        
                        <div class="input-group">
                            <input pattern="^.{1,50}$" type="text" class="form-control" id="foodName" placeholder="Tên sản phẩm" name="foodName" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Tối đa từ 1-50 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^\d{1,8}(.\d{1,2})?$" class="form-control" id="foodPrice" placeholder="Giá sản phẩm" name="foodPrice" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Giá tiền chỉ bao gồm chữ số
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^.{1,30}$" class="form-control" id="foodOrigin" placeholder="Loại sản phẩm" name="foodType" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Trường này là bắt buộc, độ dài tối đa 30 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^.{1,30}$" class="form-control" id="foodOrigin" placeholder="Số lượng sản phẩm" name="foodType" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Trường này là bắt buộc, độ dài tối đa 30 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                       
                        <div class="input-group">
                            <input type="text" pattern="^.{1,30}$" class="form-control" id="foodOrigin" placeholder="Xuất xứ sản phẩm" name="foodType" aria-describedby="inputGroupPrepend" required>
                            <div class="invalid-feedback">
                                Trường này là bắt buộc, độ dài tối đa 30 ký tự
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <label for="productImg">Đường dẫn ảnh</label>
                        <div class="input-group">
                            <input id="avatar" type="file" class="form-control" name="productImg" value="" required>
                            <div class="invalid-feedback">
                                Vui lòng chọn một tệp hình ảnh
                            </div>
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
    </div>
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
