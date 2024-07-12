package org.basilomp.spring;

import org.basilomp.spring.config.AppConfig;
import org.basilomp.spring.model.User;
import org.basilomp.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        List<User> allUsers = userService.getAllUsers();
        System.out.println("Initial set of users: " + allUsers);

        User userById = userService.getUserById(3);
        System.out.println("Found user: " + userById);

        User newUser = new User();
        newUser.setUsername("p.k.dick");
        userService.saveUser(newUser);
        allUsers = userService.getAllUsers();
        System.out.println("Initial set of users with a new one: " + allUsers);

        User userToUpdate = new User();
        userToUpdate.setId(2);
        userToUpdate.setUsername("l.celine");
        userService.updateUser(userToUpdate);
        allUsers = userService.getAllUsers();
        System.out.println("Initial set of users with an updated new one: " + allUsers);


        User userToDelete = new User();
        userToDelete.setId(2);
        userService.deleteUser(userToDelete);
        allUsers = userService.getAllUsers();
        System.out.println("All remaining users: " + allUsers);

        context.close();
    }
}
