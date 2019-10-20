package com.sap.scp.hotelreservations.service;

import com.sap.scp.hotelreservations.model.Booking;

public interface ReservationService {

    Booking createBooking(Integer start, Integer end);
}
