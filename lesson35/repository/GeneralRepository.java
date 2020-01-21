package lesson35.repository;

import lesson35.model.idEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class GeneralRepository {

    public static <T extends idEntity> void write(T t, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            Random id = new Random();
            t.setId(Math.abs(id.nextLong()));
            bw.append(t.toString());
            bw.append("\n");
        }
    }

    public <T> ArrayList<T> getObjects(String path) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<T> objects = new ArrayList<>();
            String object;
            while ((object = br.readLine()) != null && !object.isEmpty()) {
                objects.add(mapObject(object));
            }
            return objects;
        }
    }

    public static void deleteObject(long objectId, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String id = String.valueOf(objectId);
            String object;
            StringBuffer objects = new StringBuffer();
            while ((object = br.readLine()) != null) {
                if (!object.split(", ")[0].equals(id)) {
                    objects.append(object);
                    objects.append("\n");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            bw.append(objects);
            bw.close();
        } catch (IOException e) {
            System.err.println("Repository does not exist");
        }
    }

    abstract <T> T mapObject(String object) throws Exception;

}
