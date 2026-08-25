package tms_maven.homework33_ex2;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final String INSERT_USER = """
            INSERT INTO users (username,  email, password, first_name, last_name, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?) 
            RETURNING id
            """;
    private static final String SELECT_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS =
            "SELECT * FROM users ORDER BY id";
    private static final String UPDATE_USER = """
            UPDATE users 
            SET username = ?, email = ?, password = ?, first_name = ?, last_name = ?, updated_at = ?
            WHERE id = ?
            """;
    private static final String DELETE_USER =
            "DELETE FROM users WHERE id = ?";
    private static final String EXISTS_BY_EMAIL =
            "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String EXISTS_BY_USERNAME =
            "SELECT COUNT(*) FROM users WHERE username = ?";

    @Override
    public User create(User user) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setTimestamp(6, Timestamp.valueOf(user.getCreatedAt()));
            statement.setTimestamp(7, Timestamp.valueOf(user.getUpdatedAt()));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                user.setId(resultSet.getLong(1));

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating user: ", e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return Optional.of(mapResultSetToUser(resultSet));

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by id: ", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all users: ", e);
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {

            user.setUpdatedAt(LocalDateTime.now());

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setTimestamp(6, Timestamp.valueOf(user.getUpdatedAt()));
            statement.setLong(7, user.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new RuntimeException("User not found with id: " + user.getId());

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user: ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {

            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new RuntimeException("User not found with id: " + id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user: ", e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return exists(EXISTS_BY_EMAIL, email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return exists(EXISTS_BY_USERNAME, username);
    }

    private boolean exists(String query, String value) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getInt(1) > 0;

            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error checking existence: ", e);
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName((resultSet.getString("last_name")));

        Timestamp createdAt = resultSet.getTimestamp("created_at");
        if (createdAt != null)
            user.setCreatedAt(createdAt.toLocalDateTime());

        Timestamp updatedAt = resultSet.getTimestamp("updated_at");
        if (updatedAt != null)
            user.setCreatedAt(updatedAt.toLocalDateTime());

        return  user;
    }
}
