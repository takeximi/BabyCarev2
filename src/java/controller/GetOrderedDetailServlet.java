package controller;

import entity.Cart;
import entity.Items;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository1.OrderRepository;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetOrderedDetail", value = "/getordereddetail")
public class GetOrderedDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        Cart orderedCart = new Cart();
        ArrayList<Items> ordered = OrderRepository.getOrder(orderId);
        if (ordered != null) {
            orderedCart.setCart(ordered);
        } else {
            orderedCart.setCart(new ArrayList<>()); 
        }
        String orderStatus = OrderRepository.getOrderStatus(orderId);
        orderedCart.setOrderedId(orderId);
        orderedCart.setDiscountCode(OrderRepository.getDiscountCodeByOrderID(orderId));
        orderedCart.setDiscountPercent(OrderRepository.getDiscountPercent(orderedCart.getDiscountCode()));
        request.setAttribute("orderId", orderId);
        request.setAttribute("orderStatus", orderStatus);
        request.setAttribute("orderedCart", orderedCart);
        request.getRequestDispatcher("ordered.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request if needed
    }
}   
