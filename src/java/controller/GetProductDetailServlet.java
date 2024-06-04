package controller;


import entity.Brand;
import entity.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository1.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetProductDetailServlet", value = "/getProductDetail")
public class GetProductDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect("index.jsp");
        } else {
            Product p = ProductRepository.getProductById(id);
            Brand br = ProductRepository.getBrandByCTVId(p.getCTVID());
            System.out.println(p);
            request.setAttribute("product", p);
            request.setAttribute("brand", br);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
