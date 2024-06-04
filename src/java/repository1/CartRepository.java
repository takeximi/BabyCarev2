package repository1;

import config.DBConnect;
import entity.Cart;
import entity.Items;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartRepository {

 // Phương thức lưu giỏ hàng và1o cơ sở dữ liệu
    public static void saveCart(Cart cart) {
        try (Connection conn = DBConnect.getConnection()) {
           

            // Thêm các mục giỏ hàng mới
             String insertCartItem = "INSERT INTO tblCart_items (userId, productId, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertCartItem)) {
                for (Items item : cart.getCart()) {
                    ps.setString(1, cart.getUserId());
                    ps.setString(2, item.getProduct().getProductId());
                    ps.setInt(3, item.getAmmout());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCartItemQuantity(String userId, String productId, int quantity) {
    try (Connection conn = DBConnect.getConnection()) {
        String updateCartItemQuantity = "UPDATE tblCart_items SET quantity = ? WHERE userId = ? AND productId = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateCartItemQuantity)) {
            ps.setInt(1, quantity);
            ps.setString(2, userId);
            ps.setString(3, productId);
            ps.executeUpdate();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Phương thức tải giỏ hàng từ cơ sở dữ liệu theo userId
    public static Cart loadCartByUserId(String userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);

        try (Connection conn = DBConnect.getConnection()) {
            String selectCartItems = "SELECT productId, quantity FROM tblCart_items WHERE userId = ?";
            try (PreparedStatement ps = conn.prepareStatement(selectCartItems)) {
                ps.setString(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String productId = rs.getString("productId");
                    int ammout = rs.getInt("quantity");
                    Product product = ProductRepository.getProductById(productId);
                    Items item = new Items(product, ammout);
                    cart.addItems(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }
    
    public static void main(String[] args) {
        // Tạo đối tượng giỏ hàng
        Cart cart = new Cart();
        cart.setUserId("U8516"); // Thay đổi userId thành ID người dùng thích hợp

        // Thêm các mục vào giỏ hàng
        Items item1 = new Items();
        
        Product p = ProductRepository.getProductById("P7290");
        item1.setProduct(p);
        item1.setAmmout(2); // Số lượng sản phẩm



        // Thêm các mục vào giỏ hàng
        cart.addItems(item1);

        // Gọi phương thức lưu giỏ hàng vào cơ sở dữ liệu
        CartRepository.saveCart(cart);
    }
}
