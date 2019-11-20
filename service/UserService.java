package lesson35.service;

import lesson35.exceptions.BadRequestException;
import lesson35.repository.UserRepository;
import lesson35.model.User;

public class UserService {

    public static User registerUser(User user) throws Exception {
        //check business logic
        if (checkCountry(user.getCountry())) {
            return UserRepository.registerUser(user);
        } else throw new BadRequestException("Country field can contain only letters, please check your spelling");
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
