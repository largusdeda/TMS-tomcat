package tms_maven.homework36.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    // Конструкторы
    public User() {}

    public User(Long id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}