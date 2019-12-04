package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.IdEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GeneralRepository {

    public static <T extends IdEntity> void write(T t, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            Random id = new Random();
            t.setId(Math.abs(id.nextLong()));
            bw.append(t.toString());
            bw.append("\n");
        } catch (IOException e) {
            System.err.println("Repository does not exist");
        }
    }

    public static ArrayList<String> getObjects(String path) throws BadRequestException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<String> objects = new ArrayList<>();
            String object;
            while ((object = br.readLine()) != null) {
                objects.add(object);
            }
            return objects;
        } catch (IOException e) {
            System.err.println("Repository not found");
        }
        throw new BadRequestException("Repository is empty");
    }
}
