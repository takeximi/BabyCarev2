package controller;

import entity.Cart;
import entity.User;
import repository1.OrderRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="PaymentServlet", urlPatterns={"/payment"})
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
            
            // Lưu hình thức thanh toán vào session
            cart.setPaymentType(paymentMethod);
            session.setAttribute("cart", cart);

            if (paymentMethod == 1) { // Nếu chọn phương thức thanh toán là Chuyển khoản
                response.sendRedirect("vnpay_payment"); // Chuyển hướng đến trang thanh toán VNPAY
            } else {
                response.sendRedirect("makeorder"); // Nếu không, chuyển hướng đến trang xác nhận đơn hàng
            }
        } else {
            response.sendRedirect("cart.jsp"); // Chuyển hướng đến trang giỏ hàng nếu không có giỏ hàng trong session
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

