package lesson35.controller;

import lesson35.exceptions.AccessException;
import lesson35.model.Hotel;
import lesson35.service.HotelService;

import java.util.ArrayList;

public class HotelController {
    public static Hotel addHotel(Hotel hotel) throws Exception {
        return HotelService.addHotel(hotel);
    }

    public static void deleteHotel(long hotelId) throws AccessException {
        HotelService.deleteHotel(hotelId);
    }

    public static Hotel findHotelByName(String name) throws Exception {
        return HotelService.findHotelByName(name);
    }

    public static ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        return HotelService.findHotelByCity(city);
    }
}
