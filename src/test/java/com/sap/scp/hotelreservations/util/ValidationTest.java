package com.sap.scp.hotelreservations.util;

import com.sap.scp.hotelreservations.exception.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testvalidateStartEndDateSuccess() {
        Assert.assertTrue(Validation.validateDates(5, 10,365));
        Assert.assertTrue(Validation.validateDates(0, 5,365));
        Assert.assertTrue(Validation.validateDates(10, 20,365));
        Assert.assertTrue(Validation.validateDates(0, 50,365));
    }

    @Test
    public void testvalidateStartdDateBeforeEndDate() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("The start date should be before the end date");
        Validation.validateDates(9, 5,365);
    }

    @Test
    public void testvalidateEnddDateForMoreDays() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("The end date should be Integer value from 0 to 364");
        Validation.validateDates(0, 366,365);
    }

    @Test
    public void testvalidateNegativeStartDays() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("InValid Input :The start date should be Integer value from 0 to 364");
        Validation.validateDates(-2, 8,365);
    }

    @Test
    public void testvalidateroomSizePlannedDaysSuccess() {
        Assert.assertTrue(Validation.validateRoomSizePlannedDays(5, 10));
        Assert.assertTrue(Validation.validateRoomSizePlannedDays(1000, 364));
        Assert.assertTrue(Validation.validateRoomSizePlannedDays(10, 10));
        Assert.assertTrue(Validation.validateRoomSizePlannedDays(1, 1));
    }

    @Test
    public void testvalidateNegativeRoomSize() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("The room size should be an Integer value between 1 to 1000");
        Validation.validateRoomSizePlannedDays(-2, 8);
    }

    @Test
    public void testvalidateRoomSizeGreaterThan1000() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("The room size should be an Integer value between 1 to 1000");
        Validation.validateRoomSizePlannedDays(20000, 8);
    }

    @Test
    public void testvalidatePlannedDaysGreaterThan365() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("The plannedDays of hotel should be an Integer value between 0 to 364");
        Validation.validateRoomSizePlannedDays(10, 367);
    }

    @Test
    public void testvalidateNegativePlannedDays() {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("The plannedDays of hotel should be an Integer value between 0 to 364");
        Validation.validateRoomSizePlannedDays(5, -8);
    }
}
