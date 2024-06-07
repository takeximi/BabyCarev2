
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="container-fluid about bg-light py-5">
    <div class="container py-5">
        <div class="row g-5 align-items-center">
            <div class="col-lg-5 wow fadeInLeft" data-wow-delay="0.2s">
                <div class="about-img pb-5 ps-5">
                    <img src="${service.listImg}" class="img-fluid rounded w-100" style="object-fit: cover;" alt="Image">

                    <div class="about-experience">15 years experience</div>
                </div>
            </div>
            <div class="col-lg-7 wow fadeInRight" data-wow-delay="0.4s">
                <div class="section-title text-start mb-5">

                    <h1 class="display-3 mb-4">${service.serviceName}</h1>
                    <p class="mb-4">${service.description}</p>
                    <div class="mb-4">
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Tư vấn và thiết kế</p>
                        <p class="mb-4">Tư vấn trực tiếp với khách hàng để hiểu nhu cầu và sở thích của họ.</p>
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Cung cấp và lắp đặt</p>
                        <p class="mb-4">Cung cấp đồ trang trí, nội thất và phụ kiện theo yêu cầu.</p>
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Tối ưu hóa thời gian</p>
                        <p class="mb-4">Hoàn thành dự án hiệu quả và nhanh chóng.</p>
                        <h4 class="sub-title pe-3 mb-0">${service.servicePrice}$</h4>
                    </div>
                    
                    
                </div>
                   <form action="bookingservlet" method="get">
    <c:if test="${sessionScope.user != null && sessionScope.user.userId.startsWith('U')}">
        <div class="buttons">
            <div>
                <input type="hidden" name="serviceID" value="${service.serviceID}">
                <input type="hidden" name="servicePrice" value="${service.servicePrice}">
                <input type="hidden" name="serviceName" value="${service.serviceName}">
                <input class="btn btn-primary" type="submit" value="Book Now">
            </div>
            <h3>${message}</h3>
        </div>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <a href="login.jsp" class="btn btn-primary">Đặt lịch ngay</a>
    </c:if>
</form>


            </div>
        </div>
    </div>
</div>
