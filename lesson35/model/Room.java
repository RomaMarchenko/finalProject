package lesson35.model;

import java.util.Date;

public class Room extends Parameters {
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

    public Room() {
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

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Override
    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    @Override
    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    @Override
    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    @Override
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @Override
    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    @Override
    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
