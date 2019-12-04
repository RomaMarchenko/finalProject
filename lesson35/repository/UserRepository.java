package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.User;
import lesson35.model.UserType;

import java.util.ArrayList;

public class UserRepository extends GeneralRepository{
    //считывание данных - считывание файла
    //обработка данных - маппинг данных

    private static User loggedUser;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static User registerUser(User user, String path) throws Exception {
        //save user to db
        if (checkUser(user) && checkUserName(user.getUserName(), path)) {
            write(user, path);
        }
        return user;
    }

    public static void login(String userName, String password, String path) throws Exception {
        User user = findUserByName(userName, path);
        if (password.equals(user.getPassword())) {
            loggedUser = user;
        }
    }

    public static void logout() {
        loggedUser = null;
    }

    private static boolean checkUserName(String name, String path) throws Exception {
        for (User user : getUsers(path)) {
            if (user.getUserName().equals(name))
                throw new BadRequestException("User with name " + name + " is already exist, please choose another name");
        }
        return true;
    }

    private static boolean checkUser(User user) throws BadRequestException {
        if (user == null || user.getCountry() == null || user.getCountry().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty() || user.getUserName() == null || user.getUserName().isEmpty() || user.getUserType() == null)
            throw new BadRequestException("All fields of user must be filled, please check your input again");
        return true;
    }

    private static User findUserByName(String userName, String path) throws BadRequestException {
        for (User user : getUsers(path)) {
            if (user.getUserName().equals(userName))
                return user;
        }
        throw new BadRequestException("User with name " + userName + " was not found");
    }

    private static ArrayList<User> getUsers(String path) throws BadRequestException {
        ArrayList<User> users = new ArrayList<>();

        for (String userDraft : GeneralRepository.getObjects(path)) {
            User user = new User();
            String[] userParameters = userDraft.split(", ");
            user.setId(Long.parseLong(userParameters[0]));
            user.setUserName(userParameters[1]);
            user.setPassword(userParameters[2]);
            user.setCountry(userParameters[3]);
            if (userParameters[4].equals("USER"))
                user.setUserType(UserType.USER);
            else user.setUserType(UserType.ADMIN);

            users.add(user);
        }
        return users;
    }
}