package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kolya", "Ivanov", (byte) 35);
        userService.saveUser("Ivan", "Petrov", (byte) 67);
        userService.saveUser("Nina", "Fil", (byte) 23);
        userService.saveUser("Masha", "Test", (byte) 16);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}