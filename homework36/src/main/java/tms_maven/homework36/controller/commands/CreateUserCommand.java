package tms_maven.homework36.controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms_maven.homework36.controller.Command;

public class CreateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("user", null);
        return "user-form.jsp";
    }
}