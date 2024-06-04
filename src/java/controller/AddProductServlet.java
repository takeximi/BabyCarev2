package controller;

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


@WebServlet(name = "AddProductServlet", value = "/addproduct")
@MultipartConfig // Annotation để hỗ trợ việc upload file
public class AddProductServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("product-add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String CTVID = user.getUserId();
        String productID = MyRandom.getRandomProductID();
        String productName = request.getParameter("productName");
        double productPrice = 0;
       
        if (request.getParameter("productPrice").isEmpty()) {
        } else {
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
        }
        String productType = request.getParameter("productType");
        String productOrigin = request.getParameter("productOrigin");          

        int productAmount = Integer.parseInt(request.getParameter("productAmount"));
        
        String productDescription = request.getParameter("productDescription");  
//        String productImg = request.getParameter("productImg");
        Part part = request.getPart("productImg");
        logger.info("part: " + part);
        
        if (part == null || part.getSize() == 0) {
            logger.warning("Part 'productImg' is missing or empty.");
            request.setAttribute("thongbao", "Vui lòng chọn hình ảnh sản phẩm.");
            request.getRequestDispatcher("product-add.jsp").forward(request, response);
            return;
        }

        String filename = part.getSubmittedFileName();
        logger.info("filename: " + filename);
        String productImg = filename; // Lưu trữ tên file ảnh vào biến productImg

        File uploadDir = new File(getServletContext().getRealPath("/") + "img");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (filename != null && !filename.isEmpty()) {
            String path = getServletContext().getRealPath("/" + "img" + File.separator + filename);
            request.setAttribute("imagePath", path);
            try (InputStream is = part.getInputStream()) {
                boolean success = uploadFile(is, path);
                if (success) {
                    logger.info("Uploaded file successfully: " + filename);
                } else {
                    logger.warning("Failed to upload file: " + filename);
                }
            }   
        }

        logger.info("Thêm " + productID + " " + productName + " " + productPrice + " " + productType + " " + productImg + " " + productOrigin + " "  + CTVID);
        try {
            ProductRepository.addProduct(productID, productName, productType, productOrigin, productPrice, productAmount, productImg, CTVID,productDescription);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
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

