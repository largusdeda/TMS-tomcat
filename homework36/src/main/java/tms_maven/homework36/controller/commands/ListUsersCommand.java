package tms_maven.homework36.controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms_maven.homework36.controller.Command;
import tms_maven.homework36.facade.UserFacade;

import java.util.List;

public class ListUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserFacade facade = new UserFacade();
        List<?> users = facade.getAllUsers();
        request.setAttribute("users", users);
        return "user-list.jsp";
    }
}