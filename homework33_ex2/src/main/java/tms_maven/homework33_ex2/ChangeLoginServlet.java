package tms_maven.homework33_ex2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/change-login")
public class ChangeLoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idParam = req.getParameter("id");
        String newLogin = req.getParameter("login");

//        if (idParam == null || idParam.isEmpty() || newLogin == null || newLogin.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.setContentType("text/html");
//            resp.getWriter().write("<h3>Ошибка: ID и новый логин обязательны</h3>");
//            resp.getWriter().write("<a href='index.html'>На главную</a>");
//            return;
//        }

        try {
            int id = Integer.parseInt(idParam);
            Optional<User> foundUser = userDAO.findById((long) id);

            resp.setContentType("text/html");
            if (foundUser.isPresent()) {
                User userToUpdate = foundUser.get();
                userToUpdate.setUsername(newLogin);
                userDAO.update(userToUpdate);

                resp.getWriter().write("<h3>Логин успешно обновлен!</h3>");
                resp.getWriter().write("<p>ID: " + id + "</p>");
                resp.getWriter().write("<p>Новый логин: " + newLogin + "</p>");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("<h3>Неверный формат ID</h3>");
        }
        resp.getWriter().write("<br><a href='index.html'>На главную</a>");
    }
}