package controller;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "HomeFilter",urlPatterns = {
        "/admin",
    "/admin.jsp",
        "/ShowProductDetails",
        "/ProductServlet",
        "/SearchProductServlet"
})
public class AuthorizationFilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo bộ lọc, nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
         if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null && user.getUserId().startsWith("A")) {
                // User is an admin, allow access
                chain.doFilter(request, response);
                return;
            }
        }

        // Người dùng không đăng nhập hoặc không phải quản trị viên, chuyển hướng đến trang đăng nhập
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
    }

    @Override
    public void destroy() {
        // Hủy bộ lọc, nếu cần
    }
}
