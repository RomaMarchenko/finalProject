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

    private static RoomRepository roomRepository = new RoomRepository();

    public Room addRoom(Room room) throws BadRequestException, IOException {
        if (room == null || room.getDateAvailableFrom() == null || room.getHotel() == null) {
            throw new BadRequestException("All fields of room must be filled, please check your input again");
        } else {
            write(room, path);
        }
        return room;
    }

    public void deleteRoom(long roomId) {
        deleteObject(roomId, path);
    }

    public static Room getRoomById(long id) throws Exception {
        for (Room room : roomRepository.getRooms()) {
            if (room.getId() == id)
                return room;
        }
        throw new BadRequestException("Room with id " + id + " was not found");
    }

    public static void changeDateAvailableFrom (long roomId, Date dateAvailableFrom) throws Exception {
        String room;
        StringBuffer rooms = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String id = String.valueOf(roomId);
            while ((room = br.readLine()) != null) {
                if (!room.split(", ")[0].equals(id)) {
                    rooms.append(room);
                    rooms.append("\n");
                } else {
                    Room r = RoomRepository.getRoomById(Long.parseLong(room.split(", ")[0]));
                    r.setDateAvailableFrom(dateAvailableFrom);
                    rooms.append(r.toString());
                    rooms.append("\n");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            bw.append(rooms);
            bw.close();
        } catch (IOException e) {
            System.err.println("Repository does not exist");
        }
    }

    public static void makeRoomAvailable (long roomId) throws Exception{
        String room;
        StringBuffer rooms = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String id = String.valueOf(roomId);
            while ((room = br.readLine()) != null) {
                if (!room.split(", ")[0].equals(id)) {
                    rooms.append(room);
                    rooms.append("\n");
                } else {
                    Room r = RoomRepository.getRoomById(Long.parseLong(room.split(", ")[0]));
                    r. setDateAvailableFrom(new Date());
                    rooms.append(r.toString());
                    rooms.append("\n");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            bw.append(rooms);
            bw.close();
        } catch (IOException e) {
            System.err.println("Repository does not exist");
        }
    }

    public Collection<Room> findRooms(Filter filter) throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : getRooms()) {
            if (filter.getNumberOfGuests() != room.getNumberOfGuests() && filter.getNumberOfGuests() != 0)
                continue;
            if (filter.getPrice() != room.getPrice() && filter.getPrice() != 0)
                continue;
            if (filter.isBreakfastIncluded() != room.isBreakfastIncluded())
                continue;
            if (filter.isPetsAllowed() != room.isPetsAllowed())
                continue;
            if (filter.getDateAvailableFrom() != room.getDateAvailableFrom() && filter.getDateAvailableFrom() != null)
                continue;
            if (!filter.getCountry().equals(room.getHotel().getCountry()) && filter.getCountry() != null)
                continue;
            if (!filter.getCity().equals(room.getHotel().getCity()) && filter.getCity() != null)
                continue;
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
