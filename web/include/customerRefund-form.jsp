<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    /* General form styles */
    .appointment-form {
        background-color: #ffffff;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        padding: 15px; /* Reduced padding */
        border: 2px solid #007bff; /* Border around the form */
        border-radius: 10px; /* Optional: Rounded corners */
        max-width: 600px; /* Set a maximum width for the form */
        margin: 15px auto; /* Center the form horizontally */
    }

    /* Headings */
    .appointment-form h1 {
        color: #333333;
        font-weight: bold;
        font-size: 1.5rem; /* Reduced font size */
    }

    /* Ensure h5 elements stretch to the left margin */
    .appointment-form h5 {
        font-size: 1rem; /* Reduced font size */
        color: #555555;
        margin: 0; /* Remove default margin */
        padding: 0; /* Remove padding */
        display: block; /* Ensure h5 takes the full width */
        text-align: left; /* Align text to the left */
    }

    /* Input fields */
    .appointment-form .form-control {
        border: 1px solid #cccccc;
        border-radius: 5px;
        padding: 10px; /* Reduced padding */
        font-size: 14px; /* Reduced font size */
        background-color: #f9f9f9;
        color: #333333;
    }

    /* Input focus styles */
    .appointment-form .form-control:focus {
        border-color: #007bff;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }

    /* Button styles */
    .appointment-form .btn {
        background-color: #007bff;
        border-color: #007bff;
        border-radius: 5px;
        font-size: 16px; /* Reduced font size */
        font-weight: bold;
        transition: background-color 0.3s, border-color 0.3s;
        padding: 10px 20px; /* Reduced padding */
    }

    /* Button hover effect */
    .appointment-form .btn:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }

    /* Alert styles */
    .alert {
        border-radius: 5px;
        padding: 10px; /* Reduced padding */
        font-size: 14px; /* Reduced font size */
    }

    /* Placeholder text color */
    ::placeholder {
        color: #999999;
        opacity: 1; /* Override default opacity in Firefox */
    }

    /* Form layout adjustments */
    .appointment-form .row {
        margin-bottom: 10px; /* Reduced margin */
    }

    .appointment-form .col-xl-6 {
        margin-bottom: 10px; /* Reduced margin */
    }

    .appointment-form .col-12 {
        margin-top: 15px; /* Reduced margin */
    }

    /* Invalid feedback */
    .invalid-feedback {
        color: #ff0000;
        font-size: 15px; /* Reduced font size */
    }


</style>
<div class=" wow fadeInRight" data-wow-delay="0.4s">
    <div class="appointment-form rounded p-5">
        <p class="fs-4 text-uppercase text-primary"></p>
        <h1 class="display-5 mb-4">Thông Tin Khách Hàng Hoàn Tiền</h1>
         <h5 class="mb-3"><i class="fa me-2" style="font-size: 24px">Khách hàng:  ${nameUser}</i></h5>
         <h5 class="mb-3"><i class="fa text-primary" style="font-size: 24px">Số tiền hoàn lại là: ${refundAmount}đ</i></h5>
        <% if (request.getAttribute("errorMessage") != null) {%>
        <div class="alert alert-danger" role="alert">
            <%= request.getAttribute("errorMessage")%>
        </div>
        <% } %>

        <% if (request.getAttribute("successMessage") != null) {%>
        <div class="alert alert-success" role="alert">
            <%= request.getAttribute("successMessage")%>
        </div>
        <% }%>

        <form action="CustomerRefund" method="post">
            <div class="row gy-3 gx-4">
                <div class="col-md-6 offset-3 mb-3">
                    <div class="input-group">
                        <input type="hidden" class="form-control"  name="nameUser" value="${nameUser}" aria-describedby="inputGroupPrepend" required>
                        <input type="hidden" class="form-control"  name="refundAmount" value="${refundAmount}" aria-describedby="inputGroupPrepend" required>
                        <input type="hidden" class="form-control"  name="serviceName" value="${serviceName}" aria-describedby="inputGroupPrepend" required>
                        <input type="hidden" class="form-control"  name="bookingID" value="${bookingID}" aria-describedby="inputGroupPrepend" required>
                        <input type="hidden" class="form-control"  name="billID" value="${billID}" aria-describedby="inputGroupPrepend" required>
                        <input type="hidden" class="form-control"  name="customerID" value="${customerID}" aria-describedby="inputGroupPrepend" required>
                    </div>
                 


                </div>
                <div class="col-xl-6">
                    <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Tên Tài Khoản" name="accountName" required>
                </div>
                <div class="col-xl-6">
                    <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Tên Ngân Hàng" name="bankName" required>
                </div>
                <div class="col-xl-6">
                    <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Số Thẻ" name="accountNumber" pattern="\d{10}" maxlength="10" required>
                    <div class="invalid-feedback">Please enter a valid 10-digit account number.</div>
                </div>
                <div class="col-xl-6"></div>
                <div class="col-12">
                    <textarea class="form-control py-3 border-primary bg-transparent text-dark" name="note" placeholder="Lý do hủy" rows="4"></textarea>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary text-white w-100 py-3 px-5">Gửi</button>
                </div>
            </div>
        </form>
    </div>
</div>
