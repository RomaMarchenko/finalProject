package lesson35.demo;

import lesson35.controller.RoomController;
import lesson35.controller.UserController;
import lesson35.model.Filter;
import lesson35.model.Hotel;
import lesson35.model.Room;

import java.util.Date;

public class DemoRoom {
    public static void main(String[] args) throws Exception {
        UserController.login("Name 2", "****");
        /*Room room = new Room();
        room.setPrice(450.5);
        room.setBreakfastIncluded(true);
        room.setNumberOfGuests(3);
        room.setPetsAllowed(true);
        Date date = new Date();
        room.setDateAvailableFrom(date);
        Hotel hotel = new Hotel(1882533754088834892L, "Hotel 1", "Ukraine", "Kiev", "Kioto");
        room.setHotel(hotel);
        System.out.println(RoomController.addRoom(room));*/
        //System.out.println(UserRepository.getLoggedUser());
        Filter filter = new Filter.Builder()
                .setCountry("Ukraine")
                .build();
        //System.out.println(RoomController.findRooms(filter));
        System.out.println(filter.getPrice());
        System.out.println(filter.isBreakfastIncluded());
        System.out.println(filter.getCountry());
        System.out.println(filter.getCity());
        System.out.println(filter.getDateAvailableFrom());
    }
}
