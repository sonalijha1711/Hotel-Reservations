package com.sap.scp.hotelreservations.util;

import com.sap.scp.hotelreservations.exception.ValidationException;

public class Validation {

    private static int plannedDaysLimit = 365;
    /**
     * @param start
     * @param end
     * @return true if dates are valid, otherwise false.
     */
    public static boolean validateDates(int start, int end) throws ValidationException {
        boolean isValid = true;
        StringBuilder exceptionMsg = new StringBuilder();
        if (start > end) {
            exceptionMsg.append("The start date should be before the end date" + "\n");
            isValid = false;
        }
        if (start < 0|| start > plannedDaysLimit) {
            exceptionMsg.append("The start date should be Integer value from 0 to 364 \n");
            isValid = false;
        }
        if (end < 0 || end > plannedDaysLimit) {
            exceptionMsg.append("The end date should be Integer value from 0 to 364 \n");
            isValid = false;
        }
        if (!isValid) {
            throw new ValidationException(exceptionMsg.toString());
        }
        return true;
    }

    /**
     * @param roomSize
     * @return
     */
    public static boolean validateRoomSizePlannedDays(int roomSize, int plannedDays) throws ValidationException {
        boolean isValid = true;
        StringBuilder exceptionMsg = new StringBuilder();
        if (roomSize > 1000 || roomSize < 1) {
            exceptionMsg.append("The room size should be an Integer value between 1 to 1000" + "\n");
            isValid = false;
        }
        if (plannedDays >= plannedDaysLimit || plannedDays < 0) {
            exceptionMsg.append("The plannedDays of hotel should be an Integer value between 0 to 364" + "\n");
            isValid = false;
        }
        if (!isValid) {
            throw new ValidationException(exceptionMsg.toString());
        }
        return true;
    }

}
