package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class RoomRepository extends GeneralRepository {
    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\RoomDb.txt";

    public Room addRoom(Room room) throws BadRequestException, IOException {
        if(checkRoom(room)) {
            write(room, path);
        }
        return room;
    }

    public void deleteRoom(long roomId) {
        GeneralRepository.deleteObject(roomId, path);
    }

    private Room getRoomById(long id) throws Exception {
        for (Room room : getRooms()) {
            if (room.getId() == id)
                return room;
        }
        throw new BadRequestException("Room with id " + id + " was not found");
    }

    private ArrayList<Room> getRooms() throws Exception {
        return new ArrayList<Room>(getObjects(path));
    }

    private boolean checkRoom(Room room) throws BadRequestException {
        if (room == null || room.getDateAvailableFrom() == null || room.getHotel() == null) {
            throw new BadRequestException("All fields of room must be filled, please check your input again");
        } else {
            return true;
        }
    }

    @Override
    Room mapObject(String object) throws Exception {
        String[] objectParameters = object.split(", ");
        Room room = new Room();
        room.setId(Long.parseLong(objectParameters[0]));
        room.setNumberOfGuests(Integer.parseInt(objectParameters[1]));
        room.setPrice(Double.parseDouble(objectParameters[2]));
        room.setBreakfastIncluded(objectParameters[3].equals("true"));
        room.setPetsAllowed(objectParameters[4].equals("true"));
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        room.setDateAvailableFrom(format.parse(objectParameters[5]));
        room.setHotel(HotelRepository.getHotelById(Long.parseLong(objectParameters[6])));
        return room;
    }
}
