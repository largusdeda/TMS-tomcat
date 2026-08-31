package tms_maven.homework36.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms_maven.homework36.controller.commands.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/controller/*")
public class FrontController extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put("list", new ListUsersCommand());
        commands.put("edit", new EditUserCommand());
        commands.put("save", new SaveUserCommand());
        commands.put("delete", new DeleteUserCommand());
        commands.put("create", new CreateUserCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        Command command = commands.get(action);
        if (command == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            String view = command.execute(request, response);
            if (view.startsWith("redirect:")) {
                response.sendRedirect(view.substring(9));
            } else {
                request.getRequestDispatcher("/views/" + view).forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}