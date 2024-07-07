package controller;

import entity.Booking;
import entity.User;
import repository1.ServiceRespository;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookingServlet", urlPatterns = {"/bookingservlet"})
public class BookingServlet extends HttpServlet {
    
    private static final DecimalFormat decimalFormat = new DecimalFormat("###0");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String serviceIMG = request.getParameter("serviceIMG");
        String serviceID = request.getParameter("serviceID");
        String servicePrice = request.getParameter("servicePrice");   
        String serviceName = request.getParameter("serviceName");
        request.setAttribute("serviceIMG",serviceIMG );
        request.setAttribute("serviceID", serviceID);
        request.setAttribute("servicePrice", servicePrice);
        request.setAttribute("serviceName", serviceName);
        request.getRequestDispatcher("booking-service.jsp").forward(request, response);
    }

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
    HttpSession session = request.getSession();
    User oldUser = (User) session.getAttribute("user");

    if (oldUser == null) {
        request.setAttribute("errorMessage", "User is not logged in.");
        request.getRequestDispatcher("booking-service.jsp").forward(request, response);
        return;
    }

    String customerID = oldUser.getUserId();
    String serviceID = request.getParameter("serviceID");
    String servicePriceStr = request.getParameter("servicePrice").replace(",", "").replace(".", "");
    String serviceName = request.getParameter("serviceName");

    if (serviceID == null || serviceID.isEmpty()) {
        request.setAttribute("errorMessage", "Missing service ID. Please try again.");
        request.setAttribute("serviceName", serviceName);
        request.getRequestDispatcher("booking-service.jsp").forward(request, response);
        return;
    }

    // Set session attributes
    session.setAttribute("name", request.getParameter("name"));
    session.setAttribute("serviceID", serviceID);
    session.setAttribute("address", request.getParameter("address"));
    session.setAttribute("phone", request.getParameter("phone"));
    session.setAttribute("sex", request.getParameter("sex"));
    session.setAttribute("customerID", customerID);
    session.setAttribute("slot", request.getParameter("slot"));
    session.setAttribute("bookingDate", request.getParameter("bookingDate"));
    session.setAttribute("note", request.getParameter("note"));
    session.setAttribute("servicePrice", decimalFormat.format(Double.parseDouble(servicePriceStr)));
    session.setAttribute("email", request.getParameter("email"));
    session.setAttribute("serviceName", serviceName);

    // Redirect to vnPay.jsp
    response.sendRedirect("vnPay.jsp");
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

