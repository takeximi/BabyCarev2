package controller;

import entity.Product;
import entity.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import repository1.ProductRepository;
import service.MyRandom;
import repository1.UserRepository;

@WebServlet(name = "AddProductServlet", value = "/addproduct")
@MultipartConfig // Annotation để hỗ trợ việc upload file
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chưa phát triển
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String CTVID = user.getUserId();
        String productID = MyRandom.getRandomProductID();
        
        String productName = request.getParameter("productName");
        double productPrice = 0;
        if (!request.getParameter("productPrice").isEmpty()) {
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
        }
        String productType = request.getParameter("productType");
        String productOrigin = request.getParameter("productOrigin");
        int productAmount = Integer.parseInt(request.getParameter("productAmount"));

        Part part = request.getPart("productImg");
        String filename = part.getSubmittedFileName();
        String productImg = filename; // Lưu trữ tên file ảnh vào biến productImg

        if (filename != null && !filename.isEmpty()) {
            String path = getServletContext().getRealPath("/" + "img" + File.separator + filename);
            request.setAttribute("imagePath", path);
            try (InputStream is = part.getInputStream()) {
                boolean success = uploadFile(is, path);
                if (success) {
                    System.out.println("Uploaded file successfully: " + filename);
                } else {
                    System.out.println("Failed to upload file: " + filename);
                }
            }   
        }

        System.out.println("Thêm " + productID + " " + productName + " " + productPrice + " " + productType + " " + productImg + " " + productOrigin);
        try {
           
            ProductRepository.addProduct(productID, productName, productType, productOrigin, productPrice, productAmount, productImg, CTVID);
        } catch (SQLException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("thongbao", "Thêm thành công");
        request.getRequestDispatcher("product-add.jsp").forward(request, response);
    }

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(bytes);
                fos.flush();
            }
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}
