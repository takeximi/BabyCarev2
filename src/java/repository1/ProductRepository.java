package repository1;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {

    public static void addProduct(String productID, String productName, String productType, String productOrigin, double productPrice, int productAmount, String productImg, String CTVID) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO tblProduct (ProductID, ProductName, ProductType, ProductPrice, ProductImage, Amount, StatusProduct, CTVID, ProductOrigin) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
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
        String productID = "P003";
        String productName = "Sample Product";
        String productType = "Electronics";
        String productOrigin = "USA";
        double productPrice = 299.99;
        int productAmount = 50;
        String productImg = "sample_image.jpg";
        String CTVID = "C7344";

        // Check if the product already exists
        if (checkExistProductID(productID)) {
            System.out.println("Product with ID " + productID + " already exists.");
        } else {
            // Attempt to add the product
            addProduct(productID, productName, productType, productOrigin, productPrice, productAmount, productImg, CTVID);
        }
    }

   

}
