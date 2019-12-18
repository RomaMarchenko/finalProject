package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Hotel;
import lesson35.model.Parameters;
import lesson35.model.User;
import lesson35.model.UserType;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class GeneralRepository {

    public static <T extends Parameters> void write(T t, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            Random id = new Random();
            t.setId(Math.abs(id.nextLong()));
            bw.append(t.toString());
            bw.append("\n");
        }
    }

    public static <T extends Parameters> ArrayList<T> getObjects(String path, T t) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<T> objects = new ArrayList<>();
            String object;
            while ((object = br.readLine()) != null) {
                objects.add(mapObject(object, path, t));
            }
            return objects;
        }
    }

    private static <T extends Parameters> T mapObject(String object, String path, T t) throws Exception {
        String[] objectParameters = object.split(", ");
        if(path.contains("Hotel")) {
            t.setId(Long.parseLong(objectParameters[0]));
            t.setName(objectParameters[1]);
            t.setCountry(objectParameters[2]);
            t.setCity(objectParameters[3]);
            t.setStreet(objectParameters[4]);
        }
        if(path.contains("Order")) {
            t.setId(Long.parseLong(objectParameters[0]));
            t.setUser(UserRepository.getUserById(Long.parseLong(objectParameters[1])));
            t.setRoom(RoomRepository.getRoomById(Long.parseLong(objectParameters[2])));
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd-MM-yyyy");
            t.setDateFrom(format.parse(objectParameters[3]));
            t.setDateTo(format.parse(objectParameters[4]));
            t.setMoneyPaid(Double.parseDouble(objectParameters[5]));
        }
        if(path.contains("Room")) {
            t.setId(Long.parseLong(objectParameters[0]));
            t.setNumberOfGuests(Integer.parseInt(objectParameters[1]));
            t.setPrice(Double.parseDouble(objectParameters[2]));
            t.setBreakfastIncluded(objectParameters[3].equals("true"));
            t.setPetsAllowed(objectParameters[4].equals("true"));
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd-MM-yyyy");
            t.setDateAvailableFrom(format.parse(objectParameters[5]));
            t.setHotel(HotelRepository.getHotelById(Long.parseLong(objectParameters[6])));
        }
        if(path.contains("User")) {
            t.setId(Long.parseLong(objectParameters[0]));
            t.setUserName(objectParameters[1]);
            t.setPassword(objectParameters[2]);
            t.setCountry(objectParameters[3]);
            if (objectParameters[4].equals("USER"))
                t.setUserType(UserType.USER);
            else t.setUserType(UserType.ADMIN);
        }
        return t;
    }


}
