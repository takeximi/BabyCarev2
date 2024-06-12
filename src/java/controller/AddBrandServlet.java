package controller;

import entity.User;
import java.io.File;
import java.io.FileOutputStream;
import repository1.UserRepository;
import service.MyRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "AddBrandServlet", urlPatterns = {"/addBrand"})
public class AddBrandServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddBrandServlet.class.getName());

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if user is not logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userID = user.getUserId();
        boolean hasPending = UserRepository.hasPendingRegistration(userID);
        System.out.println(hasPending);
        request.setAttribute("hasPending", hasPending);
        request.getRequestDispatcher("registerctv.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check user
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }


        String userID = user.getUserId();
       

        String brandName = request.getParameter("brandName");
        System.out.println(brandName);
        String brandDescription = request.getParameter("brandDescription");
        System.out.println(brandDescription);
//        Part part = request.getPart("brandLogo");
//        String filename = (part != null) ? part.getSubmittedFileName() : null;
//        String brandLogo = (filename != null && !filename.isEmpty()) ? filename : "avatar.png"; // Default logo if none selected
//
//        // Save image if selected
//        if (filename != null && !filename.isEmpty()) {
//            String path = getServletContext().getRealPath("/" + "img" + File.separator + filename);
//            request.setAttribute("imagePath", path);
//            try (InputStream is = part.getInputStream()) {
//                boolean success = uploadFile(is, path);
//                if (success) {
//                    logger.info("Uploaded file successfully: " + filename);
//                } else {
//                    logger.warning("Failed to upload file: " + filename);
//                }
//            } catch (Exception e) {
//                logger.log(Level.SEVERE, "File upload error", e);
//            }
//        }
        String brandAddress = request.getParameter("brandAddress");
        logger.info("Parameters: brandName=" + brandName + ", brandDescription=" + brandDescription +  ", brandAddress=" + brandAddress);

        try {
            if (UserRepository.checkBrandNameExist(brandName)) {
                request.setAttribute("thongbao", "Tên cửa hàng đã tồn tại");
                request.getRequestDispatcher("registerctv.jsp").forward(request, response);
            } else {
                String brandID = MyRandom.getRandomBrandID();

                logger.info("Registering Brand: " + brandID + " " + brandName + " " + brandDescription + " " );
                boolean added = UserRepository.addBrand(brandID, brandName, brandDescription, brandAddress, userID);
                
                if (added == false) {              
                    request.setAttribute("thongbao", "Có lỗi xảy ra khi đăng kí cửa hàng. Vui lòng thử lại.");
                }
                request.getRequestDispatcher("registerctv.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding brand to the database", e);
            request.setAttribute("thongbao", "Có lỗi xảy ra khi đăng kí cửa hàng. Vui lòng thử lại abc.");
//            request.getRequestDispatcher("registerctv.jsp").forward(request, response);
            response.sendRedirect("registerctv.jsp");
        }
    }

//    public boolean uploadFile(InputStream is, String path) {
//        boolean test = false;
//        try {
//            byte[] bytes = new byte[is.available()];
//            is.read(bytes);
//            try (FileOutputStream fos = new FileOutputStream(path)) {
//                fos.write(bytes);
//                fos.flush();
//            }
//            test = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.log(Level.SEVERE, "File upload failed", e);
//        }
//        return test;
//    }
}
