/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static repository1.ProductRepository.getListProductByCTVID;


@WebServlet(name = "ViewShopServlet", urlPatterns = {"/ViewShopServlet"})
public class ViewShopServlet extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
       String CTVID = request.getParameter("CTVID");
        ArrayList<Product> listProduct = getListProductByCTVID(CTVID);
        
        request.setAttribute("listProduct", listProduct);
          request.getRequestDispatcher("viewshop.jsp").forward(request, response);
    }

   

}
