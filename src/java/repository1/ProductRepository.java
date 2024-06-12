package repository1;

import config.DBConnect;
import entity.Brand;
import entity.CommentProduct;
import entity.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                Product product = new Product(productId, productName, productType, origin, productPrice, productAmount, ctvId, productStatus, productImg, productDescription);
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

                Product product = new Product(productId, productName, productType, ProductOrigin, productPrice, productAmount, productImg, productStatus, CTVID, productDescription);
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

    public static boolean addComment(CommentProduct comment) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            conn = DBConnect.getConnection(); // Thay đổi để lấy kết nối đến cơ sở dữ liệu của bạn

            String sql = "INSERT INTO tblProductComment (CommentID, ProductID, Comment, UserID, CommentImg, Created_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, comment.getCommentID());
            stmt.setString(2, comment.getProductID());
            stmt.setString(3, comment.getComment());
            stmt.setString(4, comment.getUserID());
            stmt.setString(5, comment.getCommentImg());
            stmt.setDate(6, comment.getCreatedAt());

            int rowsAffected = stmt.executeUpdate();
            success = (rowsAffected > 0);
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    // Method to list all comments for a specific product
    public static List<CommentProduct> listCommentsByProductId(String productId) throws SQLException, ClassNotFoundException {
        List<CommentProduct> comments = new ArrayList<>();
        String sql = "SELECT * FROM tblProductComment WHERE ProductID = ?";
        try (Connection conn = DBConnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String commentId = rs.getString("CommentID");
                String productID = rs.getString("ProductID"); // Thêm dòng này để lấy ID của sản phẩm từ cơ sở dữ liệu
                String comment = rs.getString("Comment");
                String userId = rs.getString("UserID");
                String commentImg = rs.getString("CommentImg");
                Date createdAt = rs.getDate("Created_at");

                CommentProduct commentProduct = new CommentProduct(commentId, productID, comment, userId, commentImg, createdAt);
                comments.add(commentProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    public int count(String txtSearch) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            // SQL query to count products
            String query = "SELECT COUNT(*) FROM tblProduct WHERE ProductName LIKE ?";
            // Get database connection
            connection = DBConnect.getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query);
            // Set the search parameter
            statement.setString(1, "%" + txtSearch + "%");
            // Execute the query
            resultSet = statement.executeQuery();
            // Get the count result
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
        public List<Product> search(String txtSearch, int index, int size) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            String query = "with x as (select ROW_NUMBER () over (order by ProductName) as r, * from tblProduct where ProductName like ?) "
                    + "select * from x where r between (? * 3 - 2) and (? * 3)";
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + txtSearch + "%");
            statement.setInt(2, index);
            statement.setInt(3, size);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductType(resultSet.getString("ProductType"));
                product.setProductPrice(resultSet.getDouble("ProductPrice"));
                product.setImg(resultSet.getString("ProductImage"));
                product.setProductAmount(resultSet.getInt("Amount"));
                product.setStatus(resultSet.getInt("StatusProduct"));
                product.setCTVID(resultSet.getString("CTVID"));
                product.setOrigin(resultSet.getString("ProductOrigin"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
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
//        String CTVID = "C3117"; 
//        Brand brand = ProductRepository.getBrandByCTVId(CTVID);
//        if (brand != null) {
//            System.out.println(brand);
//        } else {
//            System.out.println("Brand not found!");
//        }
//        CommentProduct comment = new CommentProduct();
//        comment.setCommentID("Cm124");
//        comment.setProductID("P9003");
//        comment.setComment("This is a test comment.");
//        comment.setUserID("U8516");
//        comment.setCommentImg("image.jpg");
//        comment.setCreatedAt(new Date(System.currentTimeMillis()));
//
//        // Gọi phương thức addComment từ ProductRepository
//        ProductRepository.addComment(comment);

        // In ra thông báo khi thêm bình luận thành công
//        System.out.println("Comment added successfully.");

//try {
//        // Gọi phương thức listCommentsByProductId từ ProductRepository
//        List<CommentProduct> comments = ProductRepository.listCommentsByProductId("P9003");
//
//        // In ra danh sách các bình luận
//        for (CommentProduct comment : comments) {
//            System.out.println(comment);
//        }
//    } catch (SQLException | ClassNotFoundException e) {
//        e.printStackTrace();
//    }

         ProductRepository productRepository = new ProductRepository();
        
        // Thay thế "txtSearch" bằng từ khóa tìm kiếm thực tế của bạn
        String txtSearch = "";
        int index = 1; // Trang đầu tiên
        int size = 10; // Số lượng sản phẩm mỗi trang

        List<Product> products = productRepository.search(txtSearch, index, size);
        
        // In kết quả ra màn hình
        for (Product product : products) {
            System.out.println("ProductID: " + product.getProductId());
            System.out.println("ProductName: " + product.getProductName());
            System.out.println("ProductType: " + product.getProductType());
            System.out.println("ProductPrice: " + product.getProductPrice());
            System.out.println("ProductImage: " + product.getImg());
            System.out.println("Amount: " + product.getProductAmount());
            System.out.println("StatusProduct: " + product.getStatus());
            System.out.println("CTVID: " + product.getCTVID());
            System.out.println("ProductOrigin: " + product.getOrigin());
            System.out.println("ProductDescription: " + product.getProductDescription());
            System.out.println("----------------------------------------");
        }
    
    }
}
