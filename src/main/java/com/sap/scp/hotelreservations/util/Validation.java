package com.sap.scp.hotelreservations.util;

import com.sap.scp.hotelreservations.exception.ValidationException;

public class Validation {

    /**
     * @param start
     * @param end
     * @return true if dates are valid, otherwise throws exception.
     */
    public static boolean validateDates(int start, int end, int plannedDaysLimit) throws ValidationException {
        boolean isValid = true;
        StringBuilder exceptionMsg = new StringBuilder();
        if (start > end) {
            exceptionMsg.append("InValid Input :The start date should be before the end date");
            isValid = false;
        }
        if (start < 0|| start >= plannedDaysLimit) {
            exceptionMsg.append("InValid Input :The start date should be Integer value from 0 to ").append(plannedDaysLimit-1);
            isValid = false;
        }
        if (end < 0 || end >= plannedDaysLimit) {
            exceptionMsg.append("InValid Input :The end date should be Integer value from 0 to ").append(plannedDaysLimit-1);
            isValid = false;
        }
        if (!isValid) {
            throw new ValidationException(exceptionMsg.toString());
        }
        return true;
    }

    /**
     * @param roomSize : Number Of Rooms in the Hotel
     * @param plannedDays :Number Of Days Planned
     * @return true if inputs are valid
     */
    public static boolean validateRoomSizePlannedDays(int roomSize, int plannedDays) throws ValidationException {
        boolean isValid = true;
        StringBuilder exceptionMsg = new StringBuilder();
        if (roomSize > 1000 || roomSize < 1) {
            exceptionMsg.append("InValid Input :The room size should be an Integer value between 1 to 1000" + "\n");
            isValid = false;
        }
        if (plannedDays >= 365 || plannedDays < 0) {
            exceptionMsg.append("InValid Input :The plannedDays of hotel should be an Integer value between 0 to 364" + "\n");
            isValid = false;
        }
        if (!isValid) {
            throw new ValidationException(exceptionMsg.toString());
        }
        return true;
    }

}
