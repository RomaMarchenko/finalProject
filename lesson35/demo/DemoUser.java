package lesson35.demo;

import lesson35.model.User;
import lesson35.model.UserType;
import lesson35.repository.UserRepository;

public class DemoUser {
    public static void main(String[] args) throws Exception {
        User user1 = new User(0, "Name 1", "****", "Ukraine", UserType.USER);
        User user2 = new User(0, "Name 2", "****", "UK", UserType.ADMIN);
        //System.out.println(UserController.registerUser(user2));
        UserRepository userRepository = new UserRepository();
    }
}
