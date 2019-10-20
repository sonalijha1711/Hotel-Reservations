package com.sap.scp.hotelreservations.manager;

import com.sap.scp.hotelreservations.exception.ValidationException;
import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.ReservationService;
import com.sap.scp.hotelreservations.service.impl.ReservationServiceImpl;

import java.util.Scanner;

import static com.sap.scp.hotelreservations.util.Validation.validateDates;
import static com.sap.scp.hotelreservations.util.Validation.validateRoomSizePlannedDays;

public class ReservationManager {

    /**
     * @return instance of ReservationService to create booking
     */
    private ReservationService createResevationService() {
        ReservationService bookingService;
        int roomSize;
        int plannedDays;
        while (true) {
            System.out.println("************* Please Create a Hotel : Enter hotelsize plannedDays e.g 5 31 ***********");
            try (Scanner scanner = new Scanner(System.in)) {
                String[] inputs = scanner.nextLine().split(" ");
                roomSize = Integer.parseInt(inputs[0]);
                plannedDays = Integer.parseInt(inputs[1]);

                if (validateRoomSizePlannedDays(roomSize, plannedDays)) {
                    bookingService = new ReservationServiceImpl(roomSize, plannedDays);
                    System.out.println("************* Hotel Created !! Welcome to the  Hotel Reservation System ***********\n");
                    break;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid input. Enter the input in this format : hotelsize plannedDays e.g 5 31");
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
        Booking booking = null;
        ReservationService bookingService = createResevationService();
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            System.out.println("****** Please Enter StartDate, EndDate for Booking  e.g  0 3");
            try (Scanner scanner = new Scanner(System.in)) {
                String[] dates = scanner.nextLine().split(" ");
                start = Integer.parseInt(dates[0]);
                end = Integer.parseInt(dates[1]);
                if (validateDates(start, end)) {
                    booking = bookingService.createReservation(start, end);
                }

                if (booking != null) {
                    System.out.println("*** Congrats Room Booked from date: " +booking.getStartDate()+" to Date: "+booking.getEndDate()+" ***");
                    System.out.println("******* Room Number allocated : " + booking.getRoomNo() + " ***********");
                }
                else
                    System.out.println("****** Sorry !! Room is not available ***********\n");

                System.out.println("************* Want Another Booking  Y/N ***********");
                choice = scanner.nextLine();

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid input. Enter the input in this format : start end e.g 0 3");
            } catch (ValidationException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
