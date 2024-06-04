package repository1;

import config.DBConnect;
import entity.Brand;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {

    public static void addProduct(String productID, String productName, String productType, String productOrigin, double productPrice, int productAmount, String productImg, String CTVID, String productDescription) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblProduct (ProductID, ProductName, ProductType, ProductPrice, ProductImage, Amount, StatusProduct, CTVID, ProductOrigin,ProductDescription) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

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
            stmt.setString(10, productDescription);

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
                String productDescription = results.getString(10);

                // Tạo đối tượng Product và thêm vào danh sách
                Product product = new Product(productId, productName, productType, origin, productPrice, productAmount, ctvId, productStatus, productImg,productDescription);
                listProduct.add(product);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            System.err.println("Lỗi khi lấy danh sách sản phẩm theo CTVID: " + e.getMessage());
        }
        return listProduct;
    }
    
    public static ArrayList<Product> getListProduct() {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        try {
            String query = "select * from tblProduct";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                String productId = results.getString(1);
                String productName = results.getString(2);
                String productType = results.getString(3);
                double productPrice = results.getDouble(4);
                String productImg = results.getString(5);
                int productAmount = results.getInt(6);
                int productStatus = results.getInt(7);
                String CTVID = results.getString(8);
                String ProductOrigin = results.getString(9);
                String productDescription = results.getString(10);

                Product product = new Product(productId, productName, productType, ProductOrigin, productPrice, productAmount, productImg, productStatus, CTVID,productDescription);
                listProduct.add(product);
            }
        } catch (Exception e) {
            System.err.println("Loi database method listPet class ProductRepository");
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
    
    
    public static Product getProductById(String productId) {
    Product product = null;
    try {
        String query = "SELECT * FROM tblProduct WHERE productId = ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, productId);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            String productName = results.getString(2);
            String productType = results.getString(3);
            double productPrice = results.getDouble(4);
            String productImg = results.getString(5);
            int productAmount = results.getInt(6);
            int productStatus = results.getInt(7);
            String CTVID = results.getString(8);
            String productOrigin = results.getString(9);
            String productDescription = results.getString(10);

            product = new Product(productId, productName, productType, productOrigin, productPrice, productAmount, productImg, productStatus, CTVID, productDescription);
        }
    } catch (Exception e) {
        System.err.println("Error in database method getProductById class ProductRepository: " + e.getMessage());
    }
    return product;
}
     public static Brand getBrandByCTVId(String CTVID) {
        Brand brand = null;
        try {
            String query = "SELECT * FROM tblBrand WHERE CTVID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID); // Thay đổi tham số từ 6 thành 1
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String brandId = rs.getString("BrandID");
                String brandName = rs.getString("BrandName");
                String brandDescription = rs.getString("BrandDescription");
                String brandLogo = rs.getString("BrandLogo");
                String brandAddress = rs.getString("BrandAddress");
                String brandCTVID = rs.getString("CTVID");
                int status = rs.getInt("Status");

                brand = new Brand(brandId, brandName, brandDescription, brandLogo, brandAddress, brandCTVID, status);
            }
        } catch (Exception e) {
            System.err.println("Error in database method getBrandById class ProductRepository: " + e.getMessage());
        }
        return brand;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
//        String productID = "P004";
//        String productName = "Sample Product";
//        String productType = "Electronics";
//        String productOrigin = "USA";
//        double productPrice = 299.99;
//        int productAmount = 50;
//        String productImg = "sample_image.jpg";
//        String CTVID = "C3117";
//        String mota = "abc";
//
//        // Check if the product already exists
//        if (checkExistProductID(productID)) {
//            System.out.println("Product with ID " + productID + " already exists.");
//        } else {
//            // Attempt to add the product
//            addProduct(productID, productName, productType, productOrigin, productPrice, productAmount, productImg, CTVID,mota);
//        }
//
//        ArrayList<Product> productList = ProductRepository.getListProduct();
//
//        // In ra danh sách sản phẩm
//        System.out.println("Danh sách sản phẩm:");
//        for (Product product : productList) {
//            System.out.println(product);
//        }
        
        String CTVID = "C3117"; 
        Brand brand = ProductRepository.getBrandByCTVId(CTVID);
        if (brand != null) {
            System.out.println(brand);
        } else {
            System.out.println("Brand not found!");
        }

    }

}
