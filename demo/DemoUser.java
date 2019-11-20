package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;
import lesson35.model.UserType;

public class DemoUser {
    public static void main(String[] args) throws Exception {
        User user1 = new User(0, "Name 1", "****", "Ukraine", UserType.USER);
        System.out.println(UserController.registerUser(user1));
    }
}
