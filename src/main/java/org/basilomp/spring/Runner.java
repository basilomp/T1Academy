package org.basilomp.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;

@ComponentScan("org.basilomp")
public class Runner {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userService", UserService.class);

        List<UserDAO> allUsers = userService.getAllUsers();
        System.out.println("Initial set of users: " + allUsers);

        Optional<UserDAO> userById = userService.getUserById(3);
        userById.ifPresent(u -> System.out.println("Found user: " + u));

        UserDAO newUser = new UserDAO();
        newUser.setUsername("p.k.dick");
        userService.insertUser(newUser);
        allUsers = userService.getAllUsers();
        System.out.println("Initial set of users with a new one: " + allUsers);

        UserDAO userToUpdate = new UserDAO();
        userToUpdate.setId(2);
        userToUpdate.setUsername("l.celine");
        userService.updateUser(userToUpdate);
        allUsers = userService.getAllUsers();
        System.out.println("Initial set of users with an updated new one: " + allUsers);


        UserDAO userToDelete = new UserDAO();
        userToDelete.setId(2);
        userService.deleteUser(userToDelete);
        allUsers = userService.getAllUsers();
            System.out.println("All remaining users: " + allUsers);

        context.close();
    }
}
