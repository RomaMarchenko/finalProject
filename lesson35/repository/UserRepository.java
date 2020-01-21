package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserRepository extends GeneralRepository{

    private static User loggedUser;

    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt";

    private static UserRepository userRepository = new UserRepository();

    public static User getLoggedUser() {
        return loggedUser;
    }

    public User registerUser(User user) throws Exception {
        if (user == null || user.getCountry() == null || user.getCountry().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty() || user.getUserName() == null || user.getUserName().isEmpty() || user.getUserType() == null) {
            throw new BadRequestException("All fields of user must be filled, please check your input again");
        } else if (checkUserName(user.getUserName())) {
            write(user, path);
        }
        return user;
    }

    public void login(String userName, String password) throws Exception {
        User user = findUserByName(userName);
        if (password.equals(user.getPassword())) {
            loggedUser = user;
        }
    }

    public static void logout() {
        loggedUser = null;
    }

    public static User getUserById(long id) throws Exception {
        for (User user : userRepository.getUsers()) {
            if (user.getId() == id)
                return user;
        }
        throw new BadRequestException("User with id " + id + " was not found");
    }

    private boolean checkUserName(String name) throws Exception {
        for (User user : getUsers()) {
            if (user.getUserName().equals(name))
                throw new BadRequestException("User with name " + name + " is already exist, please choose another name");
        }
        return true;
    }

    private User findUserByName(String userName) throws Exception {
        for (User user : getUsers()) {
            if (user.getUserName().equals(userName))
                return user;
        }
        throw new BadRequestException("User with name " + userName + " was not found");
    }

    private ArrayList<User> getUsers() throws Exception {
        return new ArrayList<>(getObjects(path));
    }

    @Override
     User mapObject(String object) {
        String[] objectParameters = object.split(", ");
        User user = new User();
        user.setId(Long.parseLong(objectParameters[0]));
        user.setUserName(objectParameters[1]);
        user.setPassword(objectParameters[2]);
        user.setCountry(objectParameters[3]);
        if (objectParameters[4].equals("USER"))
            user.setUserType(UserType.USER);
        else user.setUserType(UserType.ADMIN);
        return user;
    }
}
