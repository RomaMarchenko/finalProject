package lesson35.controller;

import lesson35.model.User;
import lesson35.service.UserService;

public class UserController {


    public static User registerUser(User user) throws Exception {
        return UserService.registerUser(user);
    }
}
