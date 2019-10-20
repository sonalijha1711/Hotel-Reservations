package com.sap.scp.hotelreservations.manager;

import com.sap.scp.hotelreservations.exception.ValidationException;
import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.ReservationService;
import com.sap.scp.hotelreservations.service.impl.ReservationServiceImpl;

import java.util.Scanner;

public class ReservationManager {

    /**
     * @return instance of ReservationService to create booking
     */
    private ReservationService createResevationService(Scanner scanner) {
        ReservationService bookingService;
        int roomSize;
        int plannedDays;
        while (true) {
            try {
                System.out.println("********* Please Create a Hotel : Enter hotelsize plannedDays e.g 3 20 *********");
                String[] inputs = scanner.nextLine().split(" ");
                roomSize = Integer.parseInt(inputs[0]);
                plannedDays = Integer.parseInt(inputs[1]);
                bookingService = new ReservationServiceImpl(roomSize, plannedDays);
                System.out.println("********* Hotel Created!! Welcome to the Hotel Reservation System *********\n");
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid input. Enter the input in this format : hotelsize plannedDays e.g 3 365");
            } catch (ValidationException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return bookingService;
    }

    /**
     * Takes the input from user and book the reservation
     */
    public void startReservation() {
        int start;
        int end;
        String[] dates;
        Booking booking;
        Scanner scanner = new Scanner(System.in);
        ReservationService bookingService = createResevationService(scanner);
        String choice = "y";
        try {
            while (choice.equalsIgnoreCase("y")) {
                System.out.println("********* Please Enter StartDate, EndDate for Booking  e.g  0 3 *********");
                dates = scanner.nextLine().split(" ");
                start = Integer.parseInt(dates[0]);
                end = Integer.parseInt(dates[1]);
                booking = bookingService.createReservation(start, end);

                if (booking != null)
                    System.out.println("********* Congrats Room Number allocated : " + booking.getRoomNo() + " *********\n");
                else
                    System.out.println("********* Sorry Request Declined!! Room is not available *********\n");

                System.out.println("********* Want Another Booking  Y/N *********");
                choice = scanner.nextLine();

            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Invalid input. Enter the input in this format : start end e.g 0 3\n");
        } catch (ValidationException ex) {
            System.out.println("********* Sorry Request Declined!! *********\n");
            System.out.println(ex.getMessage());
        } finally {
            scanner.close();
        }

    }

}
