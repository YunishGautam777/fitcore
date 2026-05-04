package controller;

import jakarta.servlet.http.*;
import java.io.*;
import dao.TrainerDao;

public class DeleteTrainerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                TrainerDao dao = new TrainerDao();
                dao.deleteTrainer(id);
            }
            response.sendRedirect("ViewTrainersServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewTrainersServlet");
        }
    }
}
