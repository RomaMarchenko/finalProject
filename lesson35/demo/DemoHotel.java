package lesson35.demo;

import lesson35.controller.HotelController;
import lesson35.controller.UserController;
import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;
import lesson35.repository.UserRepository;

public class DemoHotel {
    public static void main(String[] args) throws Exception {
        Hotel hotel1 = new Hotel(0, "Hotel 1", "Ukraine", "Kiev", "Kioto");
        Hotel hotel2 = new Hotel(0, "Hotel 2", "Ukraine", "Kiev", "Kioto");
        Hotel hotel3 = new Hotel(0, "Hotel 3", "Ukraine", "Kiev", "Kioto");
        UserController.login("Name 2", "****");
        System.out.println(UserRepository.getLoggedUser());
        //System.out.println(HotelController.addHotel(hotel1));
        //HotelController.deleteHotel(4024);
        //HotelController.addHotel(hotel1);
        //HotelController.addHotel(hotel2);
        System.out.println(HotelController.addHotel(hotel3));
        //System.out.println(HotelController.findHotelByName("Hotel 1"));
        //System.out.println(HotelController.findHotelByCity("Kiev"));
    }
}
