package tms_maven.homework33_ex2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("text/html");
            resp.getWriter().write("<h3>Ошибка: ID обязателен</h3>");
            resp.getWriter().write("<a href='index.html'>На главную</a>");
            return;
        }

        try {
            long id = Long.parseLong(idParam);
            userDAO.delete(id);

            resp.setContentType("text/html");
                resp.getWriter().write("<h3>Пользователь с ID " + id + " успешно удален!</h3>");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("<h3>Неверный формат ID</h3>");
        }
        resp.getWriter().write("<br><a href='index.html'>На главную</a>");
    }
}
