package tms_maven.homework36.service;

import lombok.NonNull;
import tms_maven.homework36.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {
    private static UserService instance;
    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private UserService() {
        // Инициализация тестовыми данными
        save(new User(null, "ivanov", "ivanov@gmail.com", "Иван", "Иванов"));
        save(new User(null, "petrov", "petrov@gmail.com", "Петр", "Петров"));
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        users.put(user.getId(), user);
        return user;
    }

    public void delete(Long id) {
        users.remove(id);
    }
}
