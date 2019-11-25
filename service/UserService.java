package lesson35.service;

import lesson35.exceptions.BadRequestException;
import lesson35.repository.UserRepository;
import lesson35.model.User;

public class UserService {

    public static User registerUser(User user, String path) throws Exception {
        //check business logic
        if (checkCountry(user.getCountry())) {
            return UserRepository.registerUser(user, path);
        } else throw new BadRequestException("Country field can contain only letters, please check your spelling");
    }

    public static void login(String userName, String password, String path) throws Exception {
        UserRepository.login(userName, password, path);
    }

    public static void logout() {
        UserRepository.logout();
    }

    private static boolean checkCountry(String country) throws BadRequestException {
        for (Character ch : country.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }
}
