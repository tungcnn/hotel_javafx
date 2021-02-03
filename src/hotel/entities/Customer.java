package hotel.entities;

import java.time.LocalDate;

public class Customer {
    private String name;
    private String dateOfBirth;
    private int id;
    private String roomType;
    private LocalDate startDate;

    public Customer(String name, String dateOfBirth, int id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    @Override
    public String toString() {
        return "Khách trọ - "
                + "tên: " + name
                + ", ngày sinh: " + dateOfBirth
                + ", số CMND: " + id;
    }
}
