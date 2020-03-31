package model.contact;

import exception.MoException;
import model.contact.Contact;
import org.junit.jupiter.api.Test;

import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_RETURN_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {
    Contact myMember;

    @Test
    public void addBusyBlocks_outOfRangeTime() throws MoException {
        int validStartDay = 1;
        int validEndDay = 3;
        String[] validWeek = {"1"};
        String validStartTime = "08:30";
        String invalidOutOfRangeEndTime = "24:00";
        String meetingName = "TEST_MEETING";

        myMember = new Contact("MEMBER");
        String invalidOutOfRangeEndTimeMessage = myMember.addBusyBlocks(meetingName,
                validStartDay, validStartTime, validEndDay, invalidOutOfRangeEndTime, validWeek);
        assertEquals(invalidOutOfRangeEndTimeMessage, MESSAGE_STARTENDTIME_OUT_OF_RANGE);
    }

    @Test
    public void addBusyBlocks_outOfRangeDay() throws MoException {
        int validStartDay = 1;
        int invalidOutOfRangeEndDay = 20;
        String validStartTime = "08:30";
        String validEndTime = "12:00";
        String meetingName = "TEST_MEETING";
        String[] validWeek = {"1"};
        myMember = new Contact("MEMBER");
        String invalidOutOfRangeEndDayMessage = myMember.addBusyBlocks(meetingName,
                validStartDay, validStartTime, invalidOutOfRangeEndDay, validEndTime, validWeek);
        assertEquals(invalidOutOfRangeEndDayMessage, MESSAGE_STARTENDDAY_OUT_OF_RANGE);

        myMember = new Contact("MEMBER");
        int validEndDay = 3;
        int invalidOutOfRangeStartDay = -1;
        String invalidOutOfRangeStartDayMessage = myMember.addBusyBlocks(meetingName,
                invalidOutOfRangeStartDay, validStartTime, validEndDay, validEndTime, validWeek);
        assertEquals(invalidOutOfRangeStartDayMessage, MESSAGE_STARTENDDAY_OUT_OF_RANGE);
    }

    @Test
    public void addBusyBlocks_timeNotInBlocks() throws MoException {
        String meetingName = "TEST_MEETING";
        int validStartDay = 1;
        int validEndDay = 3;
        String[] validWeek = {"1"};
        String invalidFormatStartTime = "08:35";
        String validEndTime = "12:00";

        myMember = new Contact("MEMBER");
        String invalidFormatStartTimeMessage = myMember.addBusyBlocks(meetingName,
                validStartDay, invalidFormatStartTime, validEndDay, validEndTime, validWeek);
        assertEquals(invalidFormatStartTimeMessage, MESSAGE_STARTENDTIME_WRONG_FORMAT);
    }

    @Test
    public void addBusyBlocks_correctParams() throws MoException {
        myMember = new Contact("MEMBER");
        String[] validWeek = {"1"};
        String correctMessage = myMember.addBusyBlocks("TEST MEETING", 1, "11:30", 2, "14:30", validWeek);
        assertEquals(correctMessage, MESSAGE_RETURN_SUCCESS);
    }
}
