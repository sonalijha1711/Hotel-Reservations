package com.sap.scp.hotelreservations.service;

import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.impl.ReservationServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class ReservationServiceTest {

    private ReservationService reservationService;

    @Test
    public void testCreateReservationSuccess() {
        reservationService= new ReservationServiceImpl(5,5);

        Booking firstBooking = reservationService.createReservation(0,5);
        Assert.assertNotNull(firstBooking);
        Assert.assertEquals(firstBooking.getRoomNo().longValue(),1);
        Assert.assertEquals(firstBooking.getStartDate().longValue(),0);
        Assert.assertEquals(firstBooking.getEndDate().longValue(),5);

        Booking secondBooking = reservationService.createReservation(2,4);
        Assert.assertNotNull(secondBooking);
        Assert.assertEquals(secondBooking.getRoomNo().longValue(),2);
        Assert.assertEquals(secondBooking.getStartDate().longValue(),2);
        Assert.assertEquals(secondBooking.getEndDate().longValue(),4);

        Booking thirdBooking = reservationService.createReservation(5,5);
        Assert.assertNotNull(thirdBooking);
        Assert.assertEquals(thirdBooking.getRoomNo().longValue(),2);
        Assert.assertEquals(thirdBooking.getStartDate().longValue(),5);
        Assert.assertEquals(thirdBooking.getEndDate().longValue(),5);



    }

    @Test
    public void testCreateReservationNotAvailable() {
        reservationService= new ReservationServiceImpl(2,4);

        Booking firstBooking = reservationService.createReservation(0,4);
        Assert.assertEquals(firstBooking.getRoomNo().longValue(),1);

        Booking secondBooking = reservationService.createReservation(0,4);
        Assert.assertEquals(secondBooking.getRoomNo().longValue(),2);

        Booking thirdBooking = reservationService.createReservation(0,4);
        Assert.assertNull("Booking Not Available" , thirdBooking);




    }


}
