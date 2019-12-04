package lesson35.model;

import java.util.Date;

public class Room extends IdEntity{
    private long id;
    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room(long id, int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return
                id +
                ", " + numberOfGuests +
                ", " + price +
                ", " + breakfastIncluded +
                ", " + petsAllowed +
                ", " + dateAvailableFrom +
                ", " + hotel.getId();
    }
}
