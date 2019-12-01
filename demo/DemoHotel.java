package lesson35.demo;

import lesson35.controller.HotelController;
import lesson35.controller.UserController;
import lesson35.model.Hotel;
import lesson35.repository.UserRepository;

public class DemoHotel {
    public static void main(String[] args) throws Exception {
        Hotel hotel1 = new Hotel(0, "Hotel 1", "Ukraine", "Kiev", "Kioto");
        Hotel hotel2 = new Hotel(0, "Hotel 2", "Ukraine", "Kiev", "Kioto");
        UserController.login("Name 2", "****","C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\UserDb.txt");
        System.out.println(UserRepository.getLoggedUser());
        //System.out.println(HotelController.addHotel(hotel1, "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt"));
        //HotelController.deleteHotel(hotel1.getId(), "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt");
        //HotelController.addHotel(hotel1, "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt");
        //HotelController.addHotel(hotel2, "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt");
        System.out.println(HotelController.findHotelByName("Hotel 1", "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt"));
        System.out.println(HotelController.findHotelByCity("Kiev", "C:\\Users\\admin\\Desktop\\Gromcode Tests\\Final Project\\HotelDb.txt"));
    }
}
