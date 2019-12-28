package lesson35.controller;

import lesson35.exceptions.AccessException;
import lesson35.model.Room;
import lesson35.service.RoomService;

public class RoomController {
    public static Room addRoom(Room room) throws Exception {
        return RoomService.addRoom(room);
    }

    public static void deleteRoom(long roomId) throws AccessException {
        RoomService.deleteRoom(roomId);
    }
}
