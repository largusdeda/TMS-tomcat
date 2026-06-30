package tms_maven.tomcat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save-request")
public class SaveRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");

        if (name == null || name.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                subject == null || subject.trim().isEmpty() ||
                message == null || message.trim().isEmpty()) {

            req.setAttribute("error", "Все поля обязательны для заполнения");
            req.getRequestDispatcher("/pages/save-request.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("savedName", name);
        req.setAttribute("savedEmail", email);
        req.setAttribute("savedSubject", subject);
        req.setAttribute("savedMessage", message);

        req.getRequestDispatcher("/pages/success.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/save-request.jsp").forward(req, resp);
    }
}