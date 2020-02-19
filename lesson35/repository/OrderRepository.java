package lesson35.repository;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Order;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderRepository extends GeneralRepository{
    private static String path = "C:\\Users\\Roma_\\Desktop\\Gromcode Tests\\Final Project\\OrderDb.txt";

    private static RoomRepository roomRepository = new RoomRepository(path);

    public OrderRepository(String path) {
        super(path);
    }

    public static String getPath() {
        return path;
    }

    public Order bookRoom(long roomId, long userId, long hotelId, Date dateFrom, Date dateTo) throws Exception {
        Order order = new Order();
        if (isRoomAvailable(roomId, hotelId)) {
            if (dateFrom.compareTo(dateTo) >= 0)
                throw new BadRequestException("Room can't be booked less than for 1 day");
            order.setUser(UserRepository.getUserById(userId));
            order.setRoom(roomRepository.getRoomById(roomId));
            order.setDateFrom(dateFrom);
            order.setDateTo(dateTo);
            order.setMoneyPaid(countOrderPrice(roomId, dateFrom, dateTo));
            write(order);
            roomRepository.changeDateAvailableFrom(roomId, dateTo);
        }
        return order;
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String id = String.valueOf(roomId);
            String object;
            StringBuffer objects = new StringBuffer();
            while ((object = br.readLine()) != null) {
                if (!object.split(", ")[2].equals(id)) {
                    objects.append(object);
                    objects.append("\n");
                } else {
                    if (!object.split(", ")[1].equals(String.valueOf(userId)))
                        throw new BadRequestException("You can't cancel orders of other users");
                    roomRepository.makeRoomAvailable(roomId);
            }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            bw.append(objects);
            bw.close();
        } catch (IOException e) {
            System.err.println("Repository does not exist");
        }
    }

    private ArrayList<Order> getOrders() throws Exception {
        return new ArrayList<>(getObjects());
    }

    private boolean isRoomAvailable(long roomId, long hotelId) throws Exception {
        for (Order order : getOrders()) {
            if (order.getRoom().getId() == roomId && roomRepository.getRoomById(roomId).getHotel().getId() == hotelId) {
                throw new BadRequestException("This room is already booked");
            }
        }
        return true;
    }

    private double countOrderPrice(long roomId, Date dateFrom, Date dateTo) throws Exception {
        long delt = dateTo.getTime() - dateFrom.getTime();

        return delt / 86400000 * roomRepository.getRoomById(roomId).getPrice();
    }

    @Override
    Order mapObject(String object) throws Exception {
        String[] objectParameters = object.split(", ");
        Order order = new Order();
        order.setId(Long.parseLong(objectParameters[0]));
        order.setUser(UserRepository.getUserById(Long.parseLong(objectParameters[1])));
        order.setRoom(roomRepository.getRoomById(Long.parseLong(objectParameters[2])));
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        order.setDateFrom(format.parse(objectParameters[3]));
        order.setDateTo(format.parse(objectParameters[4]));
        order.setMoneyPaid(Double.parseDouble(objectParameters[5]));
        return order;
    }
}
