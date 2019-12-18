package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Hotel;
import lesson35.model.Room;
import lesson35.model.User;
import lesson35.model.UserType;

import java.util.ArrayList;

public class RoomRepository extends GeneralRepository {
    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\RoomDb.txt";

    public static Room getRoomById(long id) throws Exception {
        for (Room room : getRooms()) {
            if (room.getId() == id)
                return room;
        }
        throw new BadRequestException("Room with id " + id + " was not found");
    }

    private static ArrayList<Room> getRooms() throws Exception {
        Room room = new Room();
        return new ArrayList<>(GeneralRepository.getObjects(path, room));
    }
}
