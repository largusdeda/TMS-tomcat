package tms_maven;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public void demo() {
        //CREATE
        User newUser = new User("john_doe", "johndoe@example.com", "1234", "John", "Doe");
        User createdUser = userDAO.create(newUser);
        System.out.println("Created user: " + createdUser);

        //READ
        Optional<User> foundUser = userDAO.findById(createdUser.getId());
        foundUser.ifPresent(user -> System.out.println("Found user: " + user));

        List<User> allUsers = userDAO.findAll();
        System.out.println("All users: " + allUsers);

        //UPDATE
        if (foundUser.isPresent()) {
            User userToUpdate = foundUser.get();
            userToUpdate.setFirstName("Jane");
            userToUpdate.setEmail("janedoe@examle.com");
            userDAO.update(userToUpdate);
            System.out.println("Updated user: " + userToUpdate);
        }

        //EXISTS
        boolean emailExists = userDAO.existsByEmail("example@example.com");
        System.out.println("Email \"example@example.com\" exists: " + emailExists);

        boolean usernameExists = userDAO.existsByEmail("john_doe");
        System.out.println("Username \"john_doe\" exists: " + usernameExists);

        //DELETE
        userDAO.delete(createdUser.getId());
        System.out.println("User deleted");
    }

    public static void main() {
        UserService userService = new UserService();
        userService.demo();
    }
}
