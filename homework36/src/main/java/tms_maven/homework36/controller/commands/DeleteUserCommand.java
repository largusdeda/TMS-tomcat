package tms_maven.homework36.controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms_maven.homework36.controller.Command;
import tms_maven.homework36.facade.UserFacade;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            UserFacade facade = new UserFacade();
            facade.deleteUser(Long.parseLong(idParam));
        }
        return "redirect:controller?action=list";
    }
}
