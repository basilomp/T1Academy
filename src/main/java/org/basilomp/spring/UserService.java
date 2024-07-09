package org.basilomp.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserService {
    private final DataSource dataSource;

    public void insertUser(final UserDAO user) {
        log.info("Inserting user: {}", user);
        final String insertSQL = "INSERT INTO users (username) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserDAO> getUserById(final int id) {
        log.info("Getting user by id: {}", id);
        final String selectByIdSQL = "SELECT * FROM users WHERE id = ?";
        UserDAO user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectByIdSQL)) {
            preparedStatement.setInt(1, id);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new UserDAO();
                    final int userId = resultSet.getInt("id");
                    final String username = resultSet.getString("username");
                    user.setId(userId);
                    user.setUsername(username);
                    return Optional.of(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.info("User doesn't exist id: {}", id);
        return Optional.empty();
    }

    public List<UserDAO> getAllUsers() {
        log.info("Attempting to retrieve all users from database");
        final String selectAllSQL = "SELECT * FROM users";
        List<UserDAO> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDAO userDAO = new UserDAO();
                final int userId = resultSet.getInt("id");
                final String username = resultSet.getString("username");
                userDAO.setId(userId);
                userDAO.setUsername(username);
                users.add(userDAO);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return users;
    }

    public void updateUser(final UserDAO user) {
        log.info("Updating user: {}", user);
        final String updateSQL = "UPDATE users SET username = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(final UserDAO user) {
        log.info("Deleting user: {}", user);
        final String deleteSQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)){
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
