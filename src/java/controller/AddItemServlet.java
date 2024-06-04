package controller;

import entity.Cart;
import entity.Items;
import entity.Product;
import entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import repository1.CartRepository;

@WebServlet(name = "AddItemServlet", value = "/additem")
public class AddItemServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();

    if (session.getAttribute("user") == null) {
        request.setAttribute("thongbao", "Vui lòng đăng nhập để sử dụng dịch vụ");
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    }
     User user = (User) session.getAttribute("user");
     String userID = user.getUserId();

    try {
        String id = request.getParameter("id");
        String ammout = request.getParameter("ammount");
        Product p = ProductRepository.getProductById(id);

        Cart cart = (Cart) session.getAttribute("cart");
        boolean productExistsInCart = false;

        if (cart != null) {
            for (Items item : cart.getCart()) {
                if (item.getProduct().getProductId().equals(id)) {
                    // If the product already exists in the cart, update the quantity
                    int quantity = item.getAmmout() + Integer.parseInt(ammout);
                    item.setAmmout(quantity);
                    CartRepository.updateCartItemQuantity(userID, id, item.getAmmout());
                    productExistsInCart = true;
                    break;
                }
            }
        } else {
            cart = new Cart();
        }

                // Sau khi cập nhật hoặc thêm sản phẩm vào giỏ hàng
        if (!productExistsInCart) {
            Items item = new Items(p, Integer.parseInt(ammout));
            cart.addItems(item);
            CartRepository.saveCart(cart);
        }



        request.setAttribute("product", p);
        request.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("product-detail.jsp").forward(request, response);

    } catch (Exception e) {
        System.out.println("=============> Loi AdditemServlet <===============");
        e.printStackTrace();
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
