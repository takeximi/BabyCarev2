<%--
    Document   : register-brand-form
    Created on : May 23, 2024
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>


   
        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h1 class="display-5 text-uppercase mb-0">Đăng kí cửa hàng</h1>
                </div>
                <div class="row g-5">
                    <div class="col-12">
                        <h1>${message}</h1>

                        <form class="needs-validation" novalidate action="addBrand" method="post">
                            <div class="row">
         
                               

                                <!-- Brand Name -->
                                <div class="col-md-6 offset-3 mb-3">
                                    <label for="brandName">Tên cửa hàng<span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="brandName" placeholder="Tên cửa hàng"
                                               name="brandName"
                                               aria-describedby="inputGroupPrepend" pattern="^.{1,100}$"
                                               required>
                                        <div class="invalid-feedback">
                                            Tên cửa hàng chứa từ 1 đến 100 kí tự
                                        </div>
                                    </div>
                                </div>

                                <!-- Brand Description -->
                                <div class="col-md-6 offset-3 mb-3">
                                    <label for="brandDescription">Mô tả cửa hàng</label>
                                    <div class="input-group">
                                        <textarea class="form-control" id="brandDescription" placeholder="Mô tả cửa hàng"
                                                  name="brandDescription" aria-describedby="inputGroupPrepend"
                                                  maxlength="200"></textarea>
                                        <div class="invalid-feedback">
                                            Mô tả cửa hàng không quá 200 kí tự
                                        </div>
                                    </div>
                                </div>

                                <!-- Brand Logo -->
                                <div class="col-md-6 offset-3 mb-3">
                                    <label for="brandLogo">Logo cửa hàng</label>
                                    <div class="input-group">
                                        <input type="file" class="form-control" id="brandLogo" name="brandLogo"
                                               aria-describedby="inputGroupPrepend">
                                    </div>
                                </div>

                                

                                <!-- Contact Phone -->
                                <div class="col-md-6 offset-3 ol mb-3">
                                    <label for="address">Địa chỉ cửa hàng<span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="address" placeholder="Địa chỉ cửa hàng"
                                               name="brandAddress"
                                               aria-describedby="inputGroupPrepend" pattern="^.{1,100}$"
                                               required>
                                        <div class="invalid-feedback">
                                            Địa chỉ có độ dài tối đa 100 kí tự
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-3 offset-3">
                                        <button class="btn btn-primary" type="submit">Đăng kí</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <h3 class="text-danger">${thongbao}</h3>
                    </div>
                </div>
            </div>
        </div>

<!-- Register Brand End -->

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
