package com.example.project;

public class ParticipantsTable {
    private String name;
    private String departLocation;
    private String leavingTime;
    private String address;
    private String driver;
    private String passenger;
    private String emptySpots;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartLocation() {
        return departLocation;
    }

    public void setDepartLocation(String departLocation) {
        this.departLocation = departLocation;
    }

    public String getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(String leavingTime) {
        this.leavingTime = leavingTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getEmptySpots() {
        return emptySpots;
    }

    public void setEmptySpots(String emptySpots) {
        this.emptySpots = emptySpots;
    }



    public ParticipantsTable() {
    }
}
