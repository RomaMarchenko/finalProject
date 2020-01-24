package lesson35.controller;

import lesson35.exceptions.AccessException;
import lesson35.model.Filter;
import lesson35.model.Room;
import lesson35.service.RoomService;

import java.util.Collection;

public class RoomController {
    public static Room addRoom(Room room) throws Exception {
        return RoomService.addRoom(room);
    }

    public static void deleteRoom(long roomId) throws AccessException {
        RoomService.deleteRoom(roomId);
    }

    public static Collection<Room> findRooms(Filter filter) throws Exception {
        return RoomService.findRooms(filter);
    }
}
