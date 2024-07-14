package org.basilomp.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.spring.dao.UserDao;
import org.basilomp.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDAO;

    public void saveUser(User user) {
        userDAO.insertUser(user);
    }

    public User getUserById(final int id) {
        return userDAO.getUserById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(final User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(final User user) {
        userDAO.deleteUser(user);
    }
}
