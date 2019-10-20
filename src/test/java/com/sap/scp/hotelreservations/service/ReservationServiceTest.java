package com.sap.scp.hotelreservations.service;

import com.sap.scp.hotelreservations.exception.ValidationException;
import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.impl.ReservationServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class ReservationServiceTest {

    private ReservationService reservationService;


    //1a Requests outside our planning period are declined (Size=1)
    @Test(expected = ValidationException.class)
    public void testRequestOutsidePlanPeriodDeclineScenario1() {
        reservationService= new ReservationServiceImpl(1,2);
        reservationService.createReservation(-4,2);
    }

    //1b Requests outside our planning period are declined (Size=1)
    @Test(expected = ValidationException.class)
    public void testRequestOutsidePlanPeriodDeclineScenario2() {
        reservationService= new ReservationServiceImpl(1,2);
        reservationService.createReservation(200,400);
    }

    //2: Requests are accepted (Size=3)
    @Test
    public void testRequestedAcceptedSize3() {
        reservationService = new ReservationServiceImpl(3, 15);

        Booking firstBooking = reservationService.createReservation(0, 5);
        Assert.assertNotNull(firstBooking);

        Booking secondBooking = reservationService.createReservation(7, 13);
        Assert.assertNotNull(secondBooking);

        Booking thirdBooking = reservationService.createReservation(3, 9);
        Assert.assertNotNull(thirdBooking);

        Booking fourthBooking = reservationService.createReservation(5, 7);
        Assert.assertNotNull(fourthBooking);

        Booking fifthBooking = reservationService.createReservation(6, 6);
        Assert.assertNotNull(fifthBooking);

        Booking sixthBooking = reservationService.createReservation(0, 4);
        Assert.assertNotNull(sixthBooking);

    }

    // 3: Requests are declined (Size=3)
    @Test
    public void testRequestReservationDeclineSize3() {
        reservationService= new ReservationServiceImpl(3,20);

        Booking firstBooking = reservationService.createReservation(1,3);
        Assert.assertNotNull(firstBooking);

        Booking secondBooking = reservationService.createReservation(2,5);
        Assert.assertNotNull(secondBooking);

        Booking thirdBooking = reservationService.createReservation(1, 9);
        Assert.assertNotNull(thirdBooking);

        Booking fourthBooking = reservationService.createReservation(0,15);
        Assert.assertNull("Booking Declined" , fourthBooking);

    }

    //4: Requests can be accepted after a decline (Size=3)
    @Test
    public void testRequestReservationAcceptedAfterDeclineSize3() {
        reservationService= new ReservationServiceImpl(3,20);

        Booking firstBooking = reservationService.createReservation(1, 3);
        Assert.assertNotNull(firstBooking);

        Booking secondBooking = reservationService.createReservation(0, 15);
        Assert.assertNotNull(secondBooking);

        Booking thirdBooking = reservationService.createReservation(1, 9);
        Assert.assertNotNull(thirdBooking);

        Booking fourthBooking = reservationService.createReservation(2,5);
        Assert.assertNull("Booking Declined" , fourthBooking);

        Booking fifthBooking = reservationService.createReservation(4, 9);
        Assert.assertNotNull(fifthBooking);
    }


    //5: Complex Requests (Size=2)
    @Test
    public void testComplexRequestSize2() {
        reservationService= new ReservationServiceImpl(2,20);

        Booking firstBooking = reservationService.createReservation(1, 3);
        Assert.assertNotNull(firstBooking);

        Booking secondBooking = reservationService.createReservation(0, 4);
        Assert.assertNotNull(secondBooking);

        Booking thirdBooking = reservationService.createReservation(2, 3);
        Assert.assertNull("Booking Declined" , thirdBooking);

        Booking fourthBooking = reservationService.createReservation(5, 5);
        Assert.assertNotNull(fourthBooking);

        Booking fifthBooking = reservationService.createReservation(4, 10);
        Assert.assertNull("Booking Declined" , fifthBooking);

        Booking sixthBooking = reservationService.createReservation(10, 10);
        Assert.assertNotNull(sixthBooking);

        Booking seventBooking = reservationService.createReservation(6, 7);
        Assert.assertNotNull(seventBooking);

        Booking eigthBooking = reservationService.createReservation(8, 10);
        Assert.assertNotNull(eigthBooking);

        Booking ninthBooking = reservationService.createReservation(8, 9);
        Assert.assertNotNull(ninthBooking);

    }

    //extra test cases to test booking info
    @Test
    public void testCreateReservationSuccess() {
        reservationService= new ReservationServiceImpl(5,20);

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


}
