package lesson35.model;

import java.util.Date;

public class Order extends Parameters {
    private long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    public Order(long id, User user, Room room, Date dateFrom, Date dateTo, double moneyPaid) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }

    public Order() {
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
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public Date getDateFrom() {
        return dateFrom;
    }

    @Override
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public Date getDateTo() {
        return dateTo;
    }

    @Override
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public double getMoneyPaid() {
        return moneyPaid;
    }

    @Override
    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }
}
