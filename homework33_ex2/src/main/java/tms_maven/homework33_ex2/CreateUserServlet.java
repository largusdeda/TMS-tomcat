package tms_maven.homework33_ex2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

//        // Валидация
//        if (login == null || login.isEmpty() || email == null || email.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.setContentType("text/html");
//            resp.getWriter().write("<h3>Ошибка: Логин и Email обязательны</h3>");
//            resp.getWriter().write("<a href='form.html'>Вернуться назад</a>");
//            return;
//        }

        User newUser = new User(login, email, password, firstName, lastName);

        userDAO.create(newUser);

        resp.setContentType("text/html");
            resp.getWriter().write("<h3>Пользователь успешно создан!</h3>");
            resp.getWriter().write("<p>ID: " + newUser.getId() + "</p>");
            resp.getWriter().write("<p>Логин: " + newUser.getUsername() + "</p>");
            resp.getWriter().write("<p>Email: " + newUser.getEmail() + "</p>");

        resp.getWriter().write("<br><a href='index.html'>На главную</a>");
    }
}
