package lesson35.service;

import lesson35.exceptions.AccessException;
import lesson35.model.Hotel;
import lesson35.model.UserType;
import lesson35.repository.HotelRepository;
import lesson35.repository.UserRepository;

import java.util.ArrayList;


public class HotelService {
    public static Hotel addHotel(Hotel hotel) throws Exception {
        if (checkAdminRights()) {
            HotelRepository hotelRepository = new HotelRepository();
            return hotelRepository.addHotel(hotel);
        }
        throw new AccessException("You don't have permission to perform this operation");
    }

    public static void deleteHotel(long hotelId) throws AccessException {
        if (checkAdminRights()) {
            HotelRepository hotelRepository = new HotelRepository();
            hotelRepository.deleteHotel(hotelId);
        } else {
            throw new AccessException("You don't have permission to perform this operation");
        }
    }

    public static Hotel findHotelByName(String name) throws Exception {
        if (isUserLoggedIn()) {
            HotelRepository hotelRepository = new HotelRepository();
            return hotelRepository.findHotelByName(name);
        }
        else throw new AccessException("You have to be logged in to perform this operation");
    }

    public static ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        if (isUserLoggedIn()) {
            HotelRepository hotelRepository = new HotelRepository();
            return hotelRepository.findHotelByCity(city);
        }
        else throw new AccessException("You have to be logged in to perform this operation");
    }

    private static boolean checkAdminRights() {
        if (isUserLoggedIn()) {
            return UserRepository.getLoggedUser().getUserType().equals(UserType.ADMIN);
        }
        return false;
    }

    private static boolean isUserLoggedIn() { return UserRepository.getLoggedUser() != null;
    }
}