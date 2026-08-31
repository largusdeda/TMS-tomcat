package tms_maven.homework36.controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms_maven.homework36.controller.Command;
import tms_maven.homework36.facade.UserFacade;

public class EditUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            UserFacade facade = new UserFacade();
            request.setAttribute("user", facade.getUserById(id));
        }
        return "user-form.jsp";
    }
}