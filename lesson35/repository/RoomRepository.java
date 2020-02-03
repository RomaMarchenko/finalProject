package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class RoomRepository extends GeneralRepository {
    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\RoomDb.txt";

    public Room addRoom(Room room) throws BadRequestException, IOException {
        if (room == null || room.getDateAvailableFrom() == null || room.getHotel() == null) {
            throw new BadRequestException("All fields of room must be filled, please check your input again");
        }
        write(room, path);
        return room;
    }

    public void deleteRoom(long roomId) {
        deleteObject(roomId, path);
    }

    public Room getRoomById(long id) throws Exception {
        for (Room room : getRooms()) {
            if (room.getId() == id)
                return room;
        }
        throw new BadRequestException("Room with id " + id + " was not found");
    }

    public void changeDateAvailableFrom (long roomId, Date dateAvailableFrom) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        updateAll(roomId, dateFormat.format(dateAvailableFrom), 5, path);
    }

    public void makeRoomAvailable (long roomId) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        updateAll(roomId, dateFormat.format(new Date()), 5, path);
    }

    public Collection<Room> findRooms(Filter filter) throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : getRooms()) {
            if (filter.getNumberOfGuests() != room.getNumberOfGuests() && filter.getNumberOfGuests() != 0) {
                System.out.println("1");
                continue;
            }
            if (filter.getPrice() != room.getPrice() && filter.getPrice() != 0) {
                System.out.println("2");
                continue;
            }
            if (filter.isBreakfastIncluded() != null && filter.isBreakfastIncluded() != room.isBreakfastIncluded()) {
                System.out.println("3");
                continue;
            }
            if (filter.isPetsAllowed() != null && filter.isPetsAllowed() != room.isPetsAllowed()) {
                System.out.println("4");
                continue;
            }
            if (filter.getDateAvailableFrom() != room.getDateAvailableFrom() && filter.getDateAvailableFrom() != null) {
                System.out.println("5");
                continue;
            }
            if (filter.getCountry() != null && !filter.getCountry().equals(room.getHotel().getCountry())) {
                System.out.println("6");
                continue;
            }
            if (filter.getCity() != null && !filter.getCity().equals(room.getHotel().getCity())) {
                System.out.println("7");
                continue;
            }
            rooms.add(room);
        }
        return rooms;
    }

    private ArrayList<Room> getRooms() throws Exception {
        return new ArrayList<>(getObjects(path));
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
