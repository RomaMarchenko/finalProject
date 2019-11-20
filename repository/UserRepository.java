package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.exceptions.InternalServerException;
import lesson35.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class UserRepository {
    //считывание данных - считывание файла
    //обработка данных - маппинг данных

    public static User registerUser(User user) throws Exception {
        //save user to db
        if (checkUserRepository("C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt") && checkUserName(user.getUserName())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt", true))) {
                Random id = new Random();
                user.setId(Math.abs(id.nextLong()));
                if (fileIsEmpty("C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt")) {
                    bw.append(user.toString());
                } else {
                    bw.append("\n");
                    bw.append(user.toString());
                }
            } catch (IOException e) {
                System.err.println("Repository does not exist");
            }
        }

        return user;
    }

    private static boolean fileIsEmpty(String path) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<String> fileContent = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            if (fileContent.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkUserRepository(String path) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            int lineIndex = 1;
            String user;
            while ((user = br.readLine()) != null) {
                String[] userParameters = user.split(", ");
                if (userParameters.length != 5) {
                    throw new InternalServerException("Inappropriate data in user repository at line " + lineIndex);
                } else if (!userParameters[4].equals("ADMIN") && !userParameters[4].equals("USER"))
                    throw new InternalServerException("Inappropriate user type in user repository at line " + lineIndex);
                for (Character ch : userParameters[0].toCharArray()) {
                    if (!Character.isDigit(ch)) {
                        throw new InternalServerException("Inappropriate user ID in user repository at line " + lineIndex);
                    }
                }
                lineIndex++;
            }
        }
        return true;
    }

    private static boolean checkUserName(String name) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt"))) {
            String user;
            while ((user = br.readLine()) != null) {
                String[] userParameters = user.split(", ");
                if (name.equals(userParameters[1]))
                    throw new BadRequestException("User with name " + name + " is already exist, please choose another name");
            }
        }
        return true;
    }
}
