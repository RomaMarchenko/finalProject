package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Hotel;
import lesson35.model.Parameters;
import lesson35.model.User;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class HotelRepository extends GeneralRepository {
    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt";

    public static Hotel addHotel(Hotel hotel) throws Exception {
        if (checkHotel(hotel) && checkHotelName(hotel.getName(), path)) {
            write(hotel, path);
        }
        return hotel;
    }

    public static void deleteHotel(long hotelId) {
        GeneralRepository.deleteObject(hotelId, path);
    }

    public static Hotel findHotelByName(String name) throws Exception {
         for (Hotel hotel : getHotels()) {
             if (hotel.getName().equals(name))
                 return hotel;
         }
        throw new BadRequestException("Hotel with name " + name + " was not found");
    }

    public static ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        ArrayList<Hotel> hotels = new ArrayList<>();
        for (Hotel hotel : getHotels()) {
            if (hotel.getCity().equals(city)) {
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    public static Hotel getHotelById(long id) throws Exception {
        for (Hotel hotel : getHotels()) {
            if (hotel.getId() == id)
                return hotel;
        }
        throw  new BadRequestException("Hotel with id: " + id + "was not found");
    }

    private static boolean checkHotel(Hotel hotel) throws BadRequestException {
        if (hotel == null || hotel.getCity() == null || hotel.getCity().isEmpty() || hotel.getCountry() == null || hotel.getCountry().isEmpty() || hotel.getName() == null || hotel.getName().isEmpty() || hotel.getStreet() == null || hotel.getStreet().isEmpty())
            throw new BadRequestException("All fields of user must be filled, please check your input again");
        return true;
    }

    private static boolean checkHotelName(String name, String path) throws Exception {
        for (Hotel hotel : getHotels()) {
            if (name.equals(hotel.getName()))
                throw new BadRequestException("Hotel with name " + name + " is already exist");
        }
        return true;
    }

    //TODO change access modifier
    public static ArrayList<Hotel> getHotels() throws Exception {
        return ArrayList<Hotel>(GeneralRepository.getObjects(path));
    }

    @Override
    Hotel mapObject(String object) {
        String[] objectParameters = object.split(", ");
        Hotel hotel = new Hotel();
        hotel.setId(Long.parseLong(objectParameters[0]));
        hotel.setName(objectParameters[1]);
        hotel.setCountry(objectParameters[2]);
        hotel.setCity(objectParameters[3]);
        hotel.setStreet(objectParameters[4]);

        return  hotel;
    }
}
