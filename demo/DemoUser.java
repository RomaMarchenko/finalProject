package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;
import lesson35.model.UserType;

public class DemoUser {
    public static void main(String[] args) throws Exception {
        User user1 = new User(0, "Name 1", "****", "Ukraine", UserType.USER);
        User user2 = new User(0, "Name 2", "****", "UK", UserType.ADMIN);
        //System.out.println(UserController.registerUser(user2, "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt"));
    }
}
