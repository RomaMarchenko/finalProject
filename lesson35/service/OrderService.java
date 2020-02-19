package lesson35.service;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Order;
import lesson35.repository.HotelRepository;
import lesson35.repository.OrderRepository;
import lesson35.repository.UserRepository;

import java.util.Date;

public class OrderService {
    private static OrderRepository orderRepository = new OrderRepository(OrderRepository.getPath());

    public static Order bookRoom(long roomId, long userId, long hotelId, Date dateFrom, Date dateTo) throws Exception {
        if (isUserLoggedIn()) {
            return orderRepository.bookRoom(roomId, userId, hotelId, dateFrom, dateTo);
        }
        throw new BadRequestException("You have to be logged in to perform this operation");
    }

    public static void cancelReservation(long roomId, long userId) throws Exception {
        if (isUserLoggedIn()) {
            orderRepository.cancelReservation(roomId, userId);
        } else
            throw new BadRequestException("You have to be logged in to perform this operation");
    }

    private static boolean isUserLoggedIn() {
        return UserRepository.getLoggedUser() != null;
    }
}
