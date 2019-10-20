package com.sap.scp.hotelreservations.service.impl;

import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.ReservationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ReservationServiceImpl implements ReservationService {

    private Integer roomSize;
    private Integer plannedDays;
    private Map<Integer, Booking> bookingsMap;
    private Map<Integer, Map<Integer, Integer>> roomBookings;

    public ReservationServiceImpl(Integer roomSize, Integer plannedDays) {
        this.roomSize = roomSize;
        this.plannedDays = plannedDays;
        this.bookingsMap = new HashMap<>();
        this.roomBookings = new HashMap<>();
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    @Override
    public Booking createBooking(Integer start, Integer end) {
        Booking booking = null;
        for (int roomNo = 1; roomNo <= this.roomSize; roomNo++) {
            if (this.roomBookings.get(roomNo) == null) {
                Map<Integer, Integer> bookedDates = new HashMap<>();
                booking = createBookingObj(start, end, roomNo, bookedDates);
                this.roomBookings.put(roomNo, bookedDates);
                break;
            } else {
                Map<Integer, Integer> bookedDates = this.roomBookings.get(roomNo);
                boolean accepted = IntStream.range(start, end + 1).allMatch(k -> bookedDates.get(k) == null);
                if (accepted) {
                    booking = createBookingObj(start, end, roomNo, bookedDates);
                    break;
                }
            }
        }
        return booking;
    }

    private Booking createBookingObj(Integer start, Integer end, int roomNo, Map<Integer, Integer> bookedDates) {

        Booking booking = new Booking(1, start, end, roomNo, 1);
        this.bookingsMap.put(1, booking);
        IntStream.range(start, end + 1).forEach(j -> bookedDates.put(j, 1));
        return booking;
    }

}
