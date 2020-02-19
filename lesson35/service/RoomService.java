package lesson35.service;

import lesson35.exceptions.AccessException;
import lesson35.exceptions.BadRequestException;
import lesson35.model.Filter;
import lesson35.model.Room;
import lesson35.model.UserType;
import lesson35.repository.RoomRepository;
import lesson35.repository.UserRepository;

import java.util.Collection;

public class RoomService {
    private static RoomRepository roomRepository = new RoomRepository(RoomRepository.getPath());

    public static Room addRoom(Room room) throws Exception {
        if (checkAdminRights() && checkRoom(room)) {
            return roomRepository.addRoom(room);
        }
        throw new AccessException("You don't have permission to perform this operation");
    }

    public static void deleteRoom(long roomId) throws AccessException {
        if (checkAdminRights()) {
            roomRepository.deleteRoom(roomId);
        } else {
            throw new AccessException("You don't have permission to perform this operation");
        }
    }

    public static Collection<Room> findRooms(Filter filter) throws Exception {
        if (isUserLoggedIn()) {
            return roomRepository.findRooms(filter);
        } throw new AccessException("You have to be logged in to perform this operation");
    }

    private static boolean checkRoom(Room room) throws BadRequestException {
        if (room.getNumberOfGuests() <= 0 || room.getPrice() <= 0) {
            throw  new BadRequestException("Parameters number of guests and price both must be more than zero");
        }
        return true;
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
