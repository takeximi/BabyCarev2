

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


 <div class="row">
        <c:forEach var="prefercential" items="${preferentialList}">
            <div style="height: 350px;" class="pb-5 mb-5 mt-5 col-4">
                <div class="product-item owl-item position-relative bg-light d-flex flex-column text-center">
                    <img class="img-fluid mb-4 w-100" src="${preferential.preferentiaImg}" alt="">
                    <h6 class="text-uppercase">${preferential.preferentialName}</h6>
                    <h5 class="text-primary mb-0">${preferential.quantity}</h5> <!-- Assuming 'quantity' is what you meant by getPriceString -->
                    <div class="btn-action d-flex justify-content-center">
                        <a class="btn btn-primary py-2 px-3" href="getpreferentialdetail?id=${preferential.preferential}"><i class="bi bi-cart"></i></a>
                        <a class="btn btn-primary py-2 px-3" href="getpreferentialdetail?id=${preferential.preferential}"><i class="bi bi-eye"></i></a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
<div class="d-flex justify-content-center "><a href="food" style="margin-bottom: 50px" class="btn btn-primary p-2">Xem thêm.</a></div>
    