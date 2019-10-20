package com.sap.scp.hotelreservations.service;

import com.sap.scp.hotelreservations.model.Booking;

public interface ReservationService {

    Booking createReservation(Integer start, Integer end);
}
