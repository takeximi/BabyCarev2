package controller;

import entity.Cart;
import entity.Items;
import entity.User;
import repository1.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repository1.CartRepository;

@WebServlet(name = "MakeOrderServlet", value = "/makeorder")
public class MakeOrderServlet extends HttpServlet {

    private static final double BASE_SHIPPING_FEE = 30000; // Phí vận chuyển cơ bản

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");    
        Cart cart = (Cart) session.getAttribute("cart");
        String userID = user.getUserId();
        if (cart == null || cart.getCart().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        if (user != null) {
            Map<String, List<Items>> orderItemsMap = new HashMap<>();
            List<String> orderIds = new ArrayList<>();
            boolean allOrdersCreated = true;

            // Split cart into orders by CTV
            Map<String, List<Items>> itemsByCTV = cart.splitByCTV();

            for (Map.Entry<String, List<Items>> entry : itemsByCTV.entrySet()) {
                String sellerId = entry.getKey();
                List<Items> ctvItems = entry.getValue();
                System.out.println("Creating order for sellerId: " + sellerId + " with items: " + ctvItems);

                String orderId = OrderRepository.createOrder(ctvItems, user, sellerId, cart.getDiscountCode(), cart.getPaymentType());

                if (orderId != null) {
                    orderIds.add(orderId);
                    System.out.println("Order created with ID: " + orderId);

                    // Retrieve the cart items for the current order
                    List<Items> orderItems = OrderRepository.getOrdersByBillId(orderId);
                    if (orderItems != null && !orderItems.isEmpty()) {
                        orderItemsMap.put(orderId, orderItems);
                        System.out.println("Items for order " + orderId + ": " + orderItems);
                    } else {
                        orderItemsMap.put(orderId, new ArrayList<>());
                        System.out.println("No items found for order " + orderId);
                    }

                    // Tính tổng số tiền cho đơn hàng
                    double totalPrice = calculateTotalPrice(ctvItems);
                    request.setAttribute("totalPrice", totalPrice);
                    request.setAttribute("orderStatus", OrderRepository.getOrderStatus(orderId));
                    request.setAttribute("orderItems", orderItems);
                } else {
                    allOrdersCreated = false;
                    System.out.println("Failed to create order for sellerId: " + sellerId);
                    break;
                }
            }

            if (allOrdersCreated) {
                cart.setDiscountCode(null);
                cart.removeAll();
                double totalShippingFee = calculateTotalShippingFee(orderIds.size());
                double totalPriceAll = calculateTotalPriceAll(orderItemsMap, totalShippingFee);

                request.setAttribute("orderItemsMap", orderItemsMap);
                request.setAttribute("orderIds", orderIds);
                request.setAttribute("totalShippingFee", totalShippingFee);
                request.setAttribute("totalPriceAll", totalPriceAll);
                CartRepository.removeAllCartItems(userID);
                System.out.println("Order creation successful. orderItemsMap: " + orderItemsMap + ", orderIds: " + orderIds);
                request.getRequestDispatcher("ordered.jsp").forward(request, response);
            } else {
                System.out.println("Failed to create one or more orders.");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create one or more orders.");
            }
        } else {
            System.out.println("User not logged in.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
        }
    }

    private double calculateTotalPrice(List<Items> items) {
        double totalPrice = 0;
        for (Items item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    private double calculateTotalShippingFee(int numberOfOrders) {
        return BASE_SHIPPING_FEE * numberOfOrders;
    }

    private double calculateTotalPriceAll(Map<String, List<Items>> orderItemsMap, double totalShippingFee) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice + totalShippingFee;
    }
}
