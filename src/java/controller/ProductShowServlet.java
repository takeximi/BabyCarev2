package controller;
import entity.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository1.ProductRepository;


import java.io.IOException;
import java.util.ArrayList;

/*
CHUA PHAT TRIEN :D
 */
@WebServlet(name = "productShowServlet", value = "/product")
public class ProductShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        ArrayList<Product> listProduct = ProductRepository.getListProduct();
 
   

        request.setAttribute("listProduct", listProduct);

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
