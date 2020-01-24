package lesson35.model;

import java.util.Date;

public class Filter {
    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private String country;
    private String city;

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Filter() {
    }

    public static class Builder {
        private Filter filter = new Filter();

        public Builder setNumberOfGuests(int numberOfGuests) {
            filter.numberOfGuests = numberOfGuests;
            return this;
        }

        public Builder setPrice(double price) {
            filter.price = price;
            return this;
        }

        public Builder setBreakfastIncluded(boolean breakfastIncluded) {
            filter.breakfastIncluded = breakfastIncluded;
            return this;
        }

        public Builder setPetsAllowed(boolean petsAllowed) {
            filter.petsAllowed = petsAllowed;
            return this;
        }

        public Builder setDateAvailableFrom(Date dateAvailableFrom) {
            filter.dateAvailableFrom = dateAvailableFrom;
            return this;
        }

        public Builder setCountry(String country) {
            filter.country = country;
            return this;
        }

        public Builder setCity(String city) {
            filter.city = city;
            return this;
        }

        public Filter build() {
            return filter;
        }
    }
}
