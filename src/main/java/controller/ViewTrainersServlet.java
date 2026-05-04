package controller;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.util.List;
import dao.TrainerDao;
import model.Trainer;

public class ViewTrainersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TrainerDao dao = new TrainerDao();
            List<Trainer> trainers = dao.getAllTrainers();
            request.setAttribute("trainers", trainers);
            request.getRequestDispatcher("viewTrainers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp");
        }
    }
}
