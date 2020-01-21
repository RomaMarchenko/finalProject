package lesson35.controller;

import lesson35.exceptions.BadRequestException;
import lesson35.model.Order;
import lesson35.service.OrderService;

import java.util.Date;

public class OrderController {
    public static Order bookRoom(long roomId, long userId, long hotelId, Date dateFrom, Date dateTo) throws Exception {
        return OrderService.bookRoom(roomId, userId, hotelId, dateFrom, dateTo);
    }

    public static void cancelReservation(long roomId, long userId) throws Exception {
        OrderService.cancelReservation(roomId, userId);
    }
}
