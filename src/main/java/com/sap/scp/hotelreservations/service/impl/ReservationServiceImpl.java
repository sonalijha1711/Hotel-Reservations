package com.sap.scp.hotelreservations.service.impl;

import com.sap.scp.hotelreservations.exception.ValidationException;
import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.ReservationService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.sap.scp.hotelreservations.util.Validation.validateDates;
import static com.sap.scp.hotelreservations.util.Validation.validateRoomSizePlannedDays;

public class ReservationServiceImpl implements ReservationService {

    private Integer roomSize;
    private Integer plannedDays;
    private Map<String, Booking> bookingsMap;
    private Map<Integer, Map<Integer, String>> roomBookings;

    public ReservationServiceImpl(Integer roomSize, Integer plannedDays) throws ValidationException {
        validateRoomSizePlannedDays(roomSize, plannedDays);
        this.roomSize = roomSize;
        this.plannedDays = plannedDays;
        this.bookingsMap = new HashMap<>();
        this.roomBookings = new HashMap<>();
    }

    /**
     *
     * @param start start date of the user for booking
     * @param end end date of the user for booking
     * @return Booking - details of the booking
     */
    @Override
    public Booking createReservation(Integer start, Integer end) throws ValidationException {

        validateDates(start, end);
        Booking booking = null;
        for (int roomNo = 1; roomNo <= this.roomSize; roomNo++) {
            if (this.roomBookings.get(roomNo) == null) {
                Map<Integer, String> bookedDates = new HashMap<>();
                booking = resrveDates(start, end, roomNo, bookedDates);
                this.roomBookings.put(roomNo, bookedDates);
                break;
            } else {
                Map<Integer, String> bookedDates = this.roomBookings.get(roomNo);
                boolean accepted = IntStream.range(start, end + 1).allMatch(k -> bookedDates.get(k) == null);
                if (accepted) {
                    booking = resrveDates(start, end, roomNo, bookedDates);
                    break;
                }
            }
        }
        return booking;
    }

    private Booking resrveDates(Integer start, Integer end, int roomNo, Map<Integer, String> bookedDates) {
        String bookingid = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingid, start, end, roomNo, 1);
        this.bookingsMap.put(bookingid , booking);
        IntStream.range(start, end + 1).forEach(j -> bookedDates.put(j, bookingid));
        return booking;
    }

}
