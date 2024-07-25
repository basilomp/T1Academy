package org.basilomp.productapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.productapp.entity.User;
import org.basilomp.productapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//
    public User getUserById(final int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }
//
//    @Transactional
//    public void saveUser(User dto) {
////        if (userRepository.existsByUsername(dto.getUsername()).getUsername()))
//        userRepository.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public void updateUser(final User user) {
//        if
//        userDAO.updateUser(user);
//    }
//
//    public void deleteUser(final User user) {
//        userDAO.deleteUser(user);
//    }
}
