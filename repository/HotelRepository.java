package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Hotel;

import java.io.*;
import java.util.Random;

public class HotelRepository {
    public static Hotel addHotel(Hotel hotel, String path) throws Exception {
        if (checkHotel(hotel) && checkHotelName(hotel.getName(), path)) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
                Random id = new Random();
                hotel.setId(Math.abs(id.nextLong()));
                bw.append(hotel.toString());
                bw.append("\n");
            } catch (IOException e) {
                System.err.println("Repository does not exist");
            }
        }
        return hotel;
    }

    public static void deleteHotel(long hotelId, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path)); BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            String id = String.valueOf(hotelId);
            String hotel = "";
            StringBuffer hotels = new StringBuffer();
            while ((hotel = br.readLine()) != null) {
                if (!hotel.contains(id)) {
                    hotels.append(hotel);
                    hotels.append("\n");
                }
            }
            bw.append(hotels);
        } catch (IOException e) {
            System.err.println("Repository does not exist");
            ;
        }
    }

    public static Hotel findHotelByName(String name, String path) throws IOException, BadRequestException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String hotelDraft;
            while ((hotelDraft = br.readLine()) != null) {
                String[] hotelParameters = hotelDraft.split(", ");
                if (name.equals(hotelParameters[1])) {
                    Hotel hotel = new Hotel();
                    hotel.setId(Long.parseLong(hotelParameters[0]));
                    hotel.setName(hotelParameters[1]);
                    hotel.setCountry(hotelParameters[2]);
                    hotel.setCity(hotelParameters[3]);
                    hotel.setStreet(hotelParameters[4]);
                    return hotel;
                }
            }
        }
        throw new BadRequestException("Hotel with name " + name + " was not found");
    }

    private static boolean checkHotel(Hotel hotel) throws BadRequestException {
        if (hotel == null || hotel.getCity() == null || hotel.getCity().isEmpty() || hotel.getCountry() == null || hotel.getCountry().isEmpty() || hotel.getName() == null || hotel.getName().isEmpty() || hotel.getStreet() == null || hotel.getStreet().isEmpty())
            throw new BadRequestException("All fields of user must be filled, please check your input again");
        return true;
    }

    private static boolean checkHotelName(String name, String path) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String hotel;
            while ((hotel = br.readLine()) != null) {
                String[] hotelParameters = hotel.split(", ");
                if (name.equals(hotelParameters[1]))
                    throw new BadRequestException("Hotel with name " + name + " is already exist");
            }
        }
        return true;
    }
}
