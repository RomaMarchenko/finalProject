package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Hotel;

import java.util.ArrayList;

public class HotelRepository extends GeneralRepository {
    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt";

    private static HotelRepository hotelRepository = new HotelRepository(path);

    public HotelRepository(String path) {
        super(path);
    }

    public static String getPath() {
        return path;
    }

    public Hotel addHotel(Hotel hotel) throws Exception {
        if (hotel == null || hotel.getCity() == null || hotel.getCity().isEmpty() || hotel.getCountry() == null || hotel.getCountry().isEmpty() || hotel.getName() == null || hotel.getName().isEmpty() || hotel.getStreet() == null || hotel.getStreet().isEmpty()) {
            throw new BadRequestException("All fields of hotel must be filled, please check your input again");
        }
        for (Hotel hl : getHotels()) {
            if (hl.getName().equals(hotel.getName()))
                throw new BadRequestException("Hotel with name " + hotel.getName() + " is already exist");
        }
        write(hotel);
        return hotel;
    }

    public void deleteHotel(long hotelId) {
        deleteObject(hotelId);
    }

    public Hotel findHotelByName(String name) throws Exception {
         for (Hotel hotel : getHotels()) {
             if (hotel.getName().equals(name))
                 return hotel;
         }
        throw new BadRequestException("Hotel with name " + name + " was not found");
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        ArrayList<Hotel> hotels = new ArrayList<>();
        for (Hotel hotel : getHotels()) {
            if (hotel.getCity().equals(city)) {
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    public static Hotel getHotelById(long id) throws Exception {
        for (Hotel hotel : hotelRepository.getHotels()) {
            if (hotel.getId() == id)
                return hotel;
        }
        throw  new BadRequestException("Hotel with id: " + id + "was not found");
    }

    private ArrayList<Hotel> getHotels() throws Exception {
        return new ArrayList<>(getObjects());
    }

    @Override
    Hotel mapObject(String object) {
        String[] objectParameters = object.split(", ");
        Hotel hotel = new Hotel();
        hotel.setId(Long.parseLong(objectParameters[0]));
        hotel.setName(objectParameters[1]);
        hotel.setCountry(objectParameters[2]);
        hotel.setCity(objectParameters[3]);
        hotel.setStreet(objectParameters[4]);

        return  hotel;
    }
}
