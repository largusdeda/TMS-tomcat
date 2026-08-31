package tms_maven.homework36.controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms_maven.homework36.controller.Command;
import tms_maven.homework36.dto.UserDTO;
import tms_maven.homework36.facade.UserFacade;

public class SaveUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDTO userDTO = new UserDTO();

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            userDTO.setId(Long.parseLong(idParam));
        }

        userDTO.setUsername(request.getParameter("username"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setFullName(request.getParameter("fullName"));

        UserFacade facade = new UserFacade();
        facade.saveUser(userDTO);

        return "redirect:controller?action=list";
    }
}
