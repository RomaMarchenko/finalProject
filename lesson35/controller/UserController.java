package lesson35.controller;

import lesson35.model.User;
import lesson35.service.UserService;

public class UserController {
    public static User registerUser(User user, String path) throws Exception {
        return UserService.registerUser(user, path);
    }

    public static void login(String userName, String password, String path) throws Exception {
        UserService.login(userName, password, path);
    }

    public static void logout() {
        UserService.logout();
    }
}
