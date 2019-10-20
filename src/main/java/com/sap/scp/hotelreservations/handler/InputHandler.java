package com.sap.scp.hotelreservations.handler;

import com.sap.scp.hotelreservations.exception.ValidationException;
import com.sap.scp.hotelreservations.model.Booking;
import com.sap.scp.hotelreservations.service.HotelBookingService;
import com.sap.scp.hotelreservations.service.impl.HotelBookingServiceImpl;

import java.util.Scanner;

public class InputHandler {

    /**
     * @param start
     * @param end
     * @return true if dates are valid, otherwise false.
     */
    public static boolean validateDates(int start, int end) throws ValidationException {
        boolean outputIsValid = true;
        StringBuilder exceptionMessage = new StringBuilder();
        if (start > end) {
            exceptionMessage.append("The start date should be before the end date" + "\n");
            outputIsValid = false;
        }
        if (start < 0) {
            exceptionMessage.append("The start date should be 0 or later" + "\n");
            outputIsValid = false;
        }
        if (end > 364) {
            exceptionMessage.append("The end date should be " + 364 + " or earlier" + "\n");
            outputIsValid = false;
        }
        if (!outputIsValid) {
            throw new ValidationException(exceptionMessage.toString());
        }
        return true;
    }

    /**
     * @param roomSize
     * @return
     */
    private static boolean validateRoomSize(int roomSize) throws ValidationException {
        if (roomSize > 1000 || roomSize < 1) {
            throw new ValidationException("The room size should be value between 1 to 1000");
        }
        return true;
    }

    /**
     * @return instance of HotelBookingService to create booking
     */
    public static HotelBookingService createHotelService() {
        Scanner scanner = new Scanner(System.in);
        HotelBookingService bookingService;
        while (true) {
            System.out.println("************* Please Create a Hotel : Enter hotelsize e.g 5 ***********");
            int roomSize;
            if (scanner.hasNextInt()) {
                roomSize = scanner.nextInt();
            } else {
                System.out.println("Integer value is expected. e.g 5 . Try Again");
                continue;
            }
            try {
                if (validateRoomSize(roomSize)) {
                    bookingService = new HotelBookingServiceImpl(roomSize, 365);
                    System.out.println("************* Hotel Created !! Welcome to the  Hotel Reservation System ***********\n");
                    break;
                }
            } catch (ValidationException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return bookingService;
    }

    /**
     *
     *
     */
    public static void handleInput() {

        int start;
        int end;
        Booking booking = null;
        HotelBookingService bookingService = createHotelService();
        Scanner scanner = new Scanner(System.in);
        String choice = "y";
        do {
            System.out.println("****** Please Enter StartDate, EndDate for Booking  e.g  0 3");
            try {
                String[] dates = scanner.nextLine().split(" ");
                start = Integer.parseInt(dates[0]);
                end = Integer.parseInt(dates[1]);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid input .Enter the input in this format : start end e.g 0 3");
                continue;
            }
            try {
                if (validateDates(start, end)) {
                    booking = bookingService.createBooking(start, end);
                }
            } catch (ValidationException ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            if (booking != null)
                System.out.println("************* Congratulations: Room Number allocated : " + booking.getRoomNo() + " ***********");
            else
                System.out.println("****** Sorry !! Room is not available ***********\n");

            System.out.println("************* Want to Continue Booking  Y/N ***********");
            choice = scanner.nextLine();

        } while (choice.equalsIgnoreCase("y"));

    }

}
