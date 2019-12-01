package lesson35.repository;

import lesson35.exceptions.BadRequestException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Repository {
    public static ArrayList<String> getObjects(String path) throws BadRequestException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<String> objects = new ArrayList<>();
            String object;
            while((object = br.readLine()) != null) {
                objects.add(object);
            }
            return objects;
        }   catch (IOException e) {
            System.err.println("Repository not found");
        }
        throw new BadRequestException("Repository is empty");
    }
}
