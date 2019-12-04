package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Hotel;

import java.io.*;
import java.util.ArrayList;

public class HotelRepository extends GeneralRepository {
    public static Hotel addHotel(Hotel hotel, String path) throws Exception {
        if (checkHotel(hotel) && checkHotelName(hotel.getName(), path)) {
            write(hotel, path);
        }
        return hotel;
    }

    public static void deleteHotel(long hotelId, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path)); BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            String id = String.valueOf(hotelId);
            String hotel;
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
        }
    }

    public static Hotel findHotelByName(String name, String path) throws BadRequestException {
         for (Hotel hotel : getHotels(path)) {
             if (hotel.getName().equals(name))
                 return hotel;
         }
        throw new BadRequestException("Hotel with name " + name + " was not found");
    }

    public static ArrayList<Hotel> findHotelByCity(String city, String path) throws BadRequestException {
        ArrayList<Hotel> hotels = new ArrayList<>();
        for (Hotel hotel : getHotels(path)) {
            if (hotel.getCity().equals(city)) {
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    private static boolean checkHotel(Hotel hotel) throws BadRequestException {
        if (hotel == null || hotel.getCity() == null || hotel.getCity().isEmpty() || hotel.getCountry() == null || hotel.getCountry().isEmpty() || hotel.getName() == null || hotel.getName().isEmpty() || hotel.getStreet() == null || hotel.getStreet().isEmpty())
            throw new BadRequestException("All fields of user must be filled, please check your input again");
        return true;
    }

    private static boolean checkHotelName(String name, String path) throws Exception {
        for (Hotel hotel : getHotels(path)) {
            if (name.equals(hotel.getName()))
                throw new BadRequestException("Hotel with name " + name + " is already exist");
        }
        return true;
    }

    private static ArrayList<Hotel> getHotels(String path) throws BadRequestException {
        ArrayList<Hotel> hotels = new ArrayList<>();
        for (String hotelDraft : GeneralRepository.getObjects(path)) {
            Hotel hotel = new Hotel();
            String[] hotelParameters = hotelDraft.split(", ");
            hotel.setId(Long.parseLong(hotelParameters[0]));
            hotel.setName(hotelParameters[1]);
            hotel.setCountry(hotelParameters[2]);
            hotel.setCity(hotelParameters[3]);
            hotel.setStreet(hotelParameters[4]);

            hotels.add(hotel);
        }
        return hotels;
    }
}
