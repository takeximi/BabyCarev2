<%@page contentType="text/html" pageEncoding="UTF-8" %>




<div class="container mt-3">
    <form class="needs-validation" novalidate action="updateprofile" method="post" >
        <div class="row">
            <div class="col-md-3" style="background-color: hwb(48 90% 2%); border-radius: 10px; width: 180px; height: 250px; margin-right: 20px;">
                <div>
                    <div class="col-12">
                    <div style=" margin: 15px;">
                        <img style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;"src="${avatar}" class="img-fluid" alt="Ảnh cá nhân">
                    </div>
                        <h2 style=" font-size:15px; font-weight: 550;">${firstname} ${lastname}</h2>
                </div>
                </div>

                <section>
                    
                    <a href="totalprofit" class="icon-link">
                        <i class="glyphicon glyphicon-cloud icon"></i>
                        <span class="text">Change password</span>
                    </a>
                </section>
            </div>
            <div class="col-md-9" style="background-color: hwb(321 87% 1%);
                 border-radius: 10px;
                 padding: 1rem;
                 margin-bottom: 2rem;">
                <div class="row">
                    <div class="col-12" style="border-radius: 10px; text-align: center;">
                        <h2  style="font-weight: 600;color: #664d03;font-size: 23px ;font-family: 'Roboto', sans-serif;" >Profile information</h2>
                    </div>
                    <%--input fistname--%>
                    <div class="col-md-8 mb-3" >
                        <div >
                            <label for="firstname">First Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="firstname" placeholder="Họ" name="firstname"
                                       value="${firstname}"
                                       aria-describedby="inputGroupPrepend"
                                       pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$"
                                       required>
                                <div class="invalid-feedback">
                                    Họ chứa từ 1 đến 50 kí tự, không chứa ký tự đặc biệt và chữ số
                                </div>
                            </div>
                        </div>
                        <%-- input lastname--%>
                        <div >
                            <label for="lastname">Last Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="lastname" placeholder="Tên" name="lastname"
                                       value="${lastname}"
                                       aria-describedby="inputGroupPrepend"
                                       pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$"
                                       required>
                                <div class="invalid-feedback">
                                    Tên chứa từ 1 đến 50 kí tự, không chứa ký tự đặc biệt và chữ số
                                </div>
                            </div>
                        </div>

                        <%--        input address--%>
                        <div >
                            <label for="address">Address</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="address" placeholder="Address" name="address"
                                       value="${address}"
                                       aria-describedby="inputGroupPrepend" pattern="^[^@#$%^&*\\(\\)\\-+=]+{1,100}$" required>
                                <div class="invalid-feedback">
                                    Địa chỉ có độ dài 100 kí tự
                                </div>
                            </div>
                        </div>


                        <%--        input phone--%>
                        <div >
                            <label for="address">Phone</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="phone" placeholder="Phone" name="phone" value="${phone}"
                                       aria-describedby="inputGroupPrepend" pattern="^[0-9]{10}$" required>
                                <div class="invalid-feedback">
                                    Số điện thoại có dộ dài 10 kí tự
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    <div class="col-md-4 mb-3">
                        <div>
                            <div style=" margin: 50px;">
                            <img style="width: 150px; height: 90%; border-radius: 50%; margin-top: 5px;"src="${avatar}" class="img-fluid" alt="Ảnh cá nhân">
                        </div>
                        </div>
                        
                         <div >
                            <label for="address">Avatar</label>
                            <div class="input-group">
                                <br>
                                <input id="avatar" type="text" class="form-control" name="avatar" value="${avatar}"  ></input><br>
                                <br>                              

                            </div>
                                 <div>
                                    <p>Đường dẫn lưu trữ ảnh: <%= request.getAttribute("imagePath") %></p>
                                </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-3 offset-3">
                            <button class="btn btn-primary" type="submit">Edit</button>

                        </div>

                    </div>


    </form>
                </div>
            </div>
        </div> 
</div>



</div>
</div>
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
<script>
    // Lấy đối tượng của 2 trường password
    const passwordField = document.getElementById("password");
    const rePasswordField = document.getElementById("re-password");

    // Thêm sự kiện khi thay đổi giá trị của trường re-password
    rePasswordField.addEventListener("input", function () {
        // Kiểm tra xem giá trị của 2 trường có giống nhau hay không
        if (passwordField.value !== rePasswordField.value) {
            // Nếu không giống nhau, hiển thị thông báo lỗi
            rePasswordField.setCustomValidity("Mật khẩu không khớp");
        } else {
            // Nếu giống nhau, xóa thông báo lỗi đi
            rePasswordField.setCustomValidity("");
        }
    });

    // Thêm sự kiện submit cho form
    document.querySelector("form").addEventListener("submit", function (event) {
        // Kiểm tra xem giá trị của 2 trường có giống nhau hay không
        if (passwordField.value !== rePasswordField.value) {
            // Nếu không giống nhau, ngăn chặn form được submit
            event.preventDefault();
        }
    });

</script>
<style>

</style>