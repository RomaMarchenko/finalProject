/*package lesson35;

import lesson35.model.UserType;
import lesson35.repository.HotelRepository;
import lesson35.repository.UserRepository;

import java.text.SimpleDateFormat;

public class Temp {
    String[] objectParameters = object.split(", ");
        if(path.contains("Hotel")) {
        t.setId(Long.parseLong(objectParameters[0]));
        t.setName(objectParameters[1]);
        t.setCountry(objectParameters[2]);
        t.setCity(objectParameters[3]);
        t.setStreet(objectParameters[4]);
    }
        if(path.contains("Order")) {
        t.setId(Long.parseLong(objectParameters[0]));
        t.setUser(UserRepository.getUserById(Long.parseLong(objectParameters[1])));
        t.setRoom(RoomRepository.getRoomById(Long.parseLong(objectParameters[2])));
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        t.setDateFrom(format.parse(objectParameters[3]));
        t.setDateTo(format.parse(objectParameters[4]));
        t.setMoneyPaid(Double.parseDouble(objectParameters[5]));
    }
        if(path.contains("Room")) {
        t.setId(Long.parseLong(objectParameters[0]));
        t.setNumberOfGuests(Integer.parseInt(objectParameters[1]));
        t.setPrice(Double.parseDouble(objectParameters[2]));
        t.setBreakfastIncluded(objectParameters[3].equals("true"));
        t.setPetsAllowed(objectParameters[4].equals("true"));
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        t.setDateAvailableFrom(format.parse(objectParameters[5]));
        t.setHotel(HotelRepository.getHotelById(Long.parseLong(objectParameters[6])));
    }
        if(path.contains("User")) {
        t.setId(Long.parseLong(objectParameters[0]));
        t.setUserName(objectParameters[1]);
        t.setPassword(objectParameters[2]);
        t.setCountry(objectParameters[3]);
        if (objectParameters[4].equals("USER"))
            t.setUserType(UserType.USER);
        else t.setUserType(UserType.ADMIN);
    }
        return t;
}
}*/
