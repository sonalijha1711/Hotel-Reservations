package com.sap.scp.hotelreservations.service;

import com.sap.scp.hotelreservations.model.Booking;

public interface HotelBookingService {



    Booking createBooking(Integer start, Integer end);
}
