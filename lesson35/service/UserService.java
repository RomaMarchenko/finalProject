package lesson35.service;

import lesson35.exceptions.BadRequestException;
import lesson35.repository.UserRepository;
import lesson35.model.User;

public class UserService {
    private static UserRepository userRepository = new UserRepository(UserRepository.getPath());

    public static User registerUser(User user) throws Exception {
        //check business logic
        if (checkCountry(user.getCountry())) {
            return userRepository.registerUser(user);
        }
        throw new BadRequestException("Country field can contain only letters, please check your spelling");
    }

    public static void login(String userName, String password) throws Exception {
        userRepository.login(userName, password);
    }

    public static void logout() {
        UserRepository.logout();
    }

    private static boolean checkCountry(String country) {
        for (Character ch : country.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }
}
