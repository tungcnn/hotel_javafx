package hotel.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private String roomType;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isAvailable = true;
    public ArrayList<Customer> customers = new ArrayList<>();

    public Room() {
    }

    public Room(int roomNumber, String roomType, int price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }    

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public int calculateFee() {
        long day = ChronoUnit.DAYS.between(startDate, endDate);
        return (int) day*price;
    }

    @Override
    public String toString() {
        return "Phòng - " +
                " số phòng: " + roomNumber +
                ", loại phòng: " + roomType +
                ", giá phòng: " + price
                ;
    }
}
