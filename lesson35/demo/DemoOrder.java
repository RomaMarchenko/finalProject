package lesson35.demo;

import lesson35.controller.OrderController;
import lesson35.controller.UserController;

import java.text.SimpleDateFormat;

public class DemoOrder {
    public static void main(String[] args) throws Exception {
        UserController.login("Name 2", "****");
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        //System.out.println(OrderController.bookRoom(5772756163389406005L, 4823634875991106527L, 1882533754088834892L, format.parse("10-11-2020"), format.parse("15-11-2020")));
        OrderController.cancelReservation(5772756163389406005L, 4823634875991106527L);
    }
}
