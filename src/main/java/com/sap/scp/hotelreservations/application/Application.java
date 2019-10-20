package com.sap.scp.hotelreservations.application;

import com.sap.scp.hotelreservations.manager.ReservationManager;

public class Application {

    public static void main(String[] args) {
        ReservationManager reservationManager = new ReservationManager();
        reservationManager.startReservation();
        System.out.println("*************Thank You for visiting our Reservation System ***********\n");

    }
}