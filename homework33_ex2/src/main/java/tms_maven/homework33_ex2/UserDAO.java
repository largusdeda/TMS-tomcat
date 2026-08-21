package tms_maven.homework33_ex2;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User create(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    User update(User user);
    void delete(Long id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
