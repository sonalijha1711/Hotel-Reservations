package com.sap.scp.hotelreservations.service;

import com.sap.scp.hotelreservations.exception.ValidationException;
import com.sap.scp.hotelreservations.model.Booking;

public interface ReservationService {

    Booking createReservation(Integer start, Integer end) throws ValidationException;
}
