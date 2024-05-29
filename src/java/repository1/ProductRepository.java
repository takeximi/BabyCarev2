package repository1;

import config.DBConnect;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {

    public static void addProduct(String productID, String productName, String productType, String productOrigin, double productPrice, int productAmount, String productImg, String CTVID) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblProduct (ProductID, ProductName, ProductType, ProductPrice, ProductImage, Amount, StatusProduct, CTVID, ProductOrigin) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productID);
            stmt.setString(2, productName);
            stmt.setString(3, productType);
            stmt.setDouble(4, productPrice);
            stmt.setString(5, productImg);
            stmt.setInt(6, productAmount);
            stmt.setInt(7, 1); // Assuming StatusProduct is set to 1 as default
            stmt.setString(8, CTVID);
            stmt.setString(9, productOrigin);

            stmt.executeUpdate();
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            System.out.println("Error in addProduct");
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getListProductByCTVID(String CTVID) {
        ArrayList<Product> listProduct = new ArrayList<>();
        try {
            // Tạo truy vấn SQL để lấy danh sách sản phẩm dựa trên CTVID
            String query = "SELECT * FROM tblProduct WHERE CTVID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            ResultSet results = stmt.executeQuery();

            // Duyệt qua kết quả trả về và thêm sản phẩm vào danh sách
            while (results.next()) {
                String productId = results.getString(1);
                String productName = results.getString(2);
                String productType = results.getString(3);
                double productPrice = results.getDouble(4);
                String ctvId = results.getString(5);
                int productAmount = results.getInt(6);
                int productStatus = results.getInt(7);
                String productImg = results.getString(8);
                String origin = results.getString(9);

                // Tạo đối tượng Product và thêm vào danh sách
                Product product = new Product(productId, productName, productType, origin, productPrice, productAmount, ctvId, productStatus, productImg);
                listProduct.add(product);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            System.err.println("Lỗi khi lấy danh sách sản phẩm theo CTVID: " + e.getMessage());
        }
        return listProduct;
    }

    public static boolean checkExistProductID(String productID) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT ProductID FROM tblProduct WHERE ProductID = ?");
            stmt.setString(1, productID);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("Error in checkExistProductID(String productID)");
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Example product data
//        String productID = "P003";
//        String productName = "Sample Product";
//        String productType = "Electronics";
//        String productOrigin = "USA";
//        double productPrice = 299.99;
//        int productAmount = 50;
//        String productImg = "sample_image.jpg";
//        String CTVID = "C7344";
//
//        // Check if the product already exists
//        if (checkExistProductID(productID)) {
//            System.out.println("Product with ID " + productID + " already exists.");
//        } else {
//            // Attempt to add the product
//            addProduct(productID, productName, productType, productOrigin, productPrice, productAmount, productImg, CTVID);
//        }

        ArrayList<Product> productList = ProductRepository.getListProductByCTVID("C3117");

        // In ra danh sách sản phẩm
        System.out.println("Danh sách sản phẩm:");
        for (Product product : productList) {
            System.out.println(product);
        }

    }

}
