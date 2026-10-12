package tms_maven.homework36.facade;

import tms_maven.homework36.dto.UserDTO;
import tms_maven.homework36.model.User;
import tms_maven.homework36.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserFacade {
    private final UserService userService;

    public UserFacade() {
        this.userService = UserService.getInstance();
    }

    public List<UserDTO> getAllUsers() {
        return userService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userService.findById(id);
        return user != null ? convertToDTO(user) : null;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User saved = userService.save(user);
        return convertToDTO(saved);
    }

    public void deleteUser(Long id) {
        userService.delete(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName() + " " + user.getLastName()
        );
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        String[] nameParts = dto.getFullName().split(" ", 2);
        user.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
        user.setLastName(nameParts.length > 1 ? nameParts[1] : "");
        return user;
    }
}
