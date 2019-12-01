package lesson35.controller;

import lesson35.exceptions.AccessException;
import lesson35.model.Hotel;
import lesson35.service.HotelService;

import java.util.ArrayList;

public class HotelController {
    public static Hotel addHotel(Hotel hotel, String path) throws Exception {
        return HotelService.addHotel(hotel, path);
    }

    public static void deleteHotel(long hotelId, String path) throws AccessException {
        HotelService.deleteHotel(hotelId, path);
    }

    public static Hotel findHotelByName(String name, String path) throws Exception {
        return HotelService.findHotelByName(name, path);
    }

    public static ArrayList<Hotel> findHotelByCity(String city, String path) throws Exception {
        return HotelService.findHotelByCity(city, path);
    }
}
