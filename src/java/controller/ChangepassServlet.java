package controller;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.UserRepository;

import java.io.IOException;

@WebServlet(name = "ChangePassServlet", value = "/changepass")
public class ChangepassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if there's a notification to display
        String notification = request.getParameter("thongbao2");
        if (notification != null) {
            if ("1".equals(notification)) {
                request.setAttribute("thongbao2", "Cập nhật mật khẩu thành công");
            } else {
                request.setAttribute("thongbao2", "Mật khẩu cũ không đúng vui lòng nhập lại");
            }
        }

        // Forward to the change password JSP page
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userID = user.getUserId();
        int thongbao2 = 1;

        // Check old password and update if correct
        if (UserRepository.checkOldPass(userID, oldPass)) {
            UserRepository.changePass(userID, newPass);
        } else {
            thongbao2 = 0;
        }

        // Redirect to GET method with notification parameter
        response.sendRedirect("changepass?thongbao2=" + thongbao2);
    }
}