<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    #pagination {
        margin-top: 10px;
    }

    .paging-button {
        display: inline-block;
        padding: 5px 10px;
        border: 1px solid #ccc;
        background-color: #f7f7f7;
        cursor: pointer;
        text-decoration: none;
    }

    .paging-button.active {
        background-color: #ccc;
    }

</style>

<div class="container-fluid pt-5 ">

    <div id="page-wrapper">
        <nav class="nav nav-pills nav-justified">
            <a class="nav-item nav-link active" href="#">Danh sách tài khoản nhân viên</a>
            <a class="nav-item nav-link" href="registeremployee.jsp">Thêm tài khoản nhân viên</a>

        </nav>
        <div class="container mt-3">
            <h2>Quản lí danh sách tài khoản nhân viên</h2>
            <p>Admin có thể xem được danh sách tài khoản nhân viên ở đây</p>
            <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
            <br>
            <table class="table table-bordered" id="data-table">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>ID nhân viên</th>
                        <th>Họ</th>
                        <th>Tên</th>
                        <th>Số điện thoại</th>
                        <th>Email</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable">

                <c:forEach var="acc" items="${listEmpAcc}">

            <tr>
                <td>${acc.username}</td>
                <td>${acc.userID}</td>
                <td>${acc.firstname}</td>
                <td>${acc.lastname}</td>
                <td>${acc.phone}</td>
                <td>${acc.email}</td>
                <c:if test="${acc.status==1}">
                    <td>
                        <a href="lockemployee?empID=${acc.userID}" class="btn btn-danger"><i class="fa fa-lock" aria-hidden="true"></i> Khóa</a>
                    </td>
                </c:if>
                <c:if test="${acc.status==0}">
                    <td>
                        <a href="unlockemployee?empID=${acc.userID}" class="btn btn-success"><i class="fa fa-unlock-alt" aria-hidden="true"> Mở</i></a>
                    </td>
                </c:if>

            </tr>

        </c:forEach>
                    

                </tbody>
            </table>

            <div id="pagination" class="text-center"></div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<script>
    var currentPage = 1;
    var recordsPerPage = 10;
    var table = document.getElementById("data-table");
    var rows = table.getElementsByTagName("tr");
    var totalPage = Math.ceil(rows.length / recordsPerPage);

    function showTable(page) {
        var start = (page - 1) * recordsPerPage;
        var end = start + recordsPerPage;

        for (var i = 0; i < rows.length; i++) {
            if (i >= start && i < end) {
                rows[i].style.display = "table-row";
            } else {
                rows[i].style.display = "none";
            }
        }
    }

    function createPagination() {
        var pagination = document.getElementById("pagination");
        pagination.innerHTML = "";

        for (var i = 1; i <= totalPage; i++) {
            var button = document.createElement("a");
            button.href = "#";
            button.innerHTML = i;

            if (i === currentPage) {
                button.classList.add("paging-button", "active");
            } else {
                button.classList.add("paging-button");
            }

            button.addEventListener("click", function () {
                currentPage = parseInt(this.innerHTML);
                showTable(currentPage);

                var currentButton = document.querySelector(".paging-button.active");
                currentButton.classList.remove("active");

                this.classList.add("active");
            });

            pagination.appendChild(button);
        }
    }

    showTable(currentPage);
    createPagination();

</script>

