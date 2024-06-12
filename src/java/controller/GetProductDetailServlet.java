package controller;

import entity.Brand;
import entity.CommentProduct;
import entity.Product;
import entity.User;
import repository1.ProductRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import service.MyRandom;

@WebServlet(name = "GetProductDetailServlet", value = "/getProductDetail")
public class GetProductDetailServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetProductDetailServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            Product product = ProductRepository.getProductById(id);
            if (product == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            List<CommentProduct> comments = ProductRepository.listCommentsByProductId(id);
            Brand brand = ProductRepository.getBrandByCTVId(product.getCTVID());

            request.setAttribute("listComments", comments);
            request.setAttribute("product", product);
            request.setAttribute("brand", brand);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error occurred while getting product details: ", ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    
    String userID = user.getUserId();
    String productID = request.getParameter("id");
    String commentText = request.getParameter("comment");
    String commentID = MyRandom.getRandomCommentID();
    Part part = request.getPart("commentImg");
    String filename = null;

    try {
        if (part == null || part.getSize() == 0) {
            request.setAttribute("thongbao", "Vui lòng chọn hình ảnh sản phẩm để bình luận.");
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
            return;
        }
        filename = part.getSubmittedFileName();
        String commentImg = filename;

        CommentProduct comment = new CommentProduct();
        comment.setCommentID(commentID);
        comment.setProductID(productID);
        comment.setComment(commentText);
        comment.setUserID(userID);
        comment.setCommentImg(commentImg);
        comment.setCreatedAt(new Date(System.currentTimeMillis()));

        // Thêm bình luận vào cơ sở dữ liệu
        boolean commentAdded = ProductRepository.addComment(comment);
        if (!commentAdded) {
            // Xử lý nếu không thêm được bình luận vào cơ sở dữ liệu
            request.setAttribute("errorMessage", "Không thể thêm bình luận. Vui lòng thử lại sau.");
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
            return;
        }

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
                    throw new IOException("Failed to upload file");
                }
            }
        }

        response.sendRedirect("getProductDetail?id=" + productID);
        return; // Đảm bảo không có xử lý tiếp theo sau khi thêm bình luận thành công

    } catch (ServletException | IOException e) {
        logger.log(Level.SEVERE, "Error occurred while processing comment: ", e);
        request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình xử lý bình luận. Vui lòng thử lại.");
        request.getRequestDispatcher("product-detail.jsp").forward(request, response);
    } catch (Exception e) {
        logger.log(Level.SEVERE, "Unexpected error: ", e);
        request.setAttribute("errorMessage", "Đã xảy ra lỗi không mong muốn. Vui lòng thử lại.");
        request.getRequestDispatcher("product-detail.jsp").forward(request, response);
    }
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
            logger.log(Level.SEVERE, "Error occurred while uploading file: ", e);
        }
        return test;
    }
}
