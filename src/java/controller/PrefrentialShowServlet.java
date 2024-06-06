package controller;

import entity.Preferential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import repository1.PreferentialRepository;

@WebServlet(name = "PrefrentialShowServlet", urlPatterns = "/preferential")
public class PrefrentialShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        ArrayList<Preferential> listPreferential = PreferentialRepository.getListPreferential();
        ArrayList<Preferential> subListPreferential = new ArrayList<>();

        // Ensure not to exceed list size
        int size = Math.min(4, listPreferential.size());
        for (int i = 0; i < size; i++) {
            subListPreferential.add(listPreferential.get(i));
        }

        request.setAttribute("preferentialList", subListPreferential);
        request.getRequestDispatcher("/preferential.jsp").forward(request, response);
    }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
