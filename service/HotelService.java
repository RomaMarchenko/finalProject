package lesson35.service;

import lesson35.exceptions.AccessException;
import lesson35.model.Hotel;
import lesson35.model.UserType;
import lesson35.repository.HotelRepository;
import lesson35.repository.UserRepository;


public class HotelService {
    public static Hotel addHotel(Hotel hotel, String path) throws Exception {
        if (checkAdminRights()) {
            return HotelRepository.addHotel(hotel, path);
        }
        throw new AccessException("You don't have permission to perform this operation");
    }

    public static void deleteHotel(long hotelId, String path) throws AccessException {
        if (checkAdminRights()) {
            HotelRepository.deleteHotel(hotelId, path);
        } else {
            throw new AccessException("You don't have permission to perform this operation");
        }
    }

    public static Hotel findHotelByName(String name, String path) throws Exception {
        if (isUserLoggedIn())
            return HotelRepository.findHotelByName(name, path);
        else throw new AccessException("You have to be logged in to perform this operation");
    }

    private static boolean checkAdminRights() {
        if (isUserLoggedIn()) {
            return UserRepository.getLoggedUser().getUserType().equals(UserType.ADMIN);
        }
        return false;
    }

    private static boolean isUserLoggedIn() {
        return UserRepository.getLoggedUser() != null;
    }
}
