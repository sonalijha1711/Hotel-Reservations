package com.sap.scp.hotelreservations.model;

public class Booking {

    private Integer bookingId;
    private Integer startDate;
    private Integer endDate;
    private Integer roomNo;
    private Integer userId;

    public Booking(Integer bookingId, Integer startDate, Integer endDate, Integer roomNo, Integer userId) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomNo = roomNo;
        this.userId = userId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public Integer getUserId() {
        return userId;
    }
}
