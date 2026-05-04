package controller;

import jakarta.servlet.http.*;
import java.io.*;
import dao.TrainerDao;
import model.Trainer;

public class AddTrainerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String specialization = request.getParameter("specialization");

        Trainer t = new Trainer(0, name, email, phone, specialization);
        TrainerDao dao = new TrainerDao();

        try {
            dao.addTrainer(t);
            response.sendRedirect("ViewTrainersServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addTrainer.jsp?error=true");
        }
    }
}
