package model.contact;

import common.exception.WfException;
import org.junit.jupiter.api.Test;

import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_RETURN_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {
    Contact myMember;

    @Test
    public void editBlocks_outOfRangeTime() throws WfException {
        int validStartDay = 1;
        int validEndDay = 3;
        String[] validWeek = {"1"};
        String validStartTime = "08:30";
        String invalidOutOfRangeEndTime = "24:00";
        String meetingName = "TEST_MEETING";

        myMember = new Contact("MEMBER");
        String invalidOutOfRangeEndTimeMessage = myMember.editBlocks(true, meetingName,
                validStartDay, validStartTime, validEndDay, invalidOutOfRangeEndTime, validWeek);
        assertEquals(invalidOutOfRangeEndTimeMessage, MESSAGE_STARTENDTIME_OUT_OF_RANGE);
    }

    @Test
    public void editBlocks_outOfRangeDay() throws WfException {
        int validStartDay = 1;
        int invalidOutOfRangeEndDay = 20;
        String validStartTime = "08:30";
        String validEndTime = "12:00";
        String meetingName = "TEST_MEETING";
        String[] validWeek = {"1"};
        myMember = new Contact("MEMBER");
        String invalidOutOfRangeEndDayMessage = myMember.editBlocks(true, meetingName,
                validStartDay, validStartTime, invalidOutOfRangeEndDay, validEndTime, validWeek);
        assertEquals(invalidOutOfRangeEndDayMessage, MESSAGE_STARTENDDAY_OUT_OF_RANGE);

        myMember = new Contact("MEMBER");
        int validEndDay = 3;
        int invalidOutOfRangeStartDay = -1;
        String invalidOutOfRangeStartDayMessage = myMember.editBlocks(true, meetingName,
                invalidOutOfRangeStartDay, validStartTime, validEndDay, validEndTime, validWeek);
        assertEquals(invalidOutOfRangeStartDayMessage, MESSAGE_STARTENDDAY_OUT_OF_RANGE);
    }


    @Test
    public void editBlocks_timeNotInBlocks() throws WfException {
        String meetingName = "TEST_MEETING";
        int validStartDay = 1;
        int validEndDay = 3;
        String[] validWeek = {"1"};
        String invalidFormatStartTime = "08:35";
        String validEndTime = "12:00";

        myMember = new Contact("MEMBER");
        String invalidFormatStartTimeMessage = myMember.editBlocks(true, meetingName,
                validStartDay, invalidFormatStartTime, validEndDay, validEndTime, validWeek);
        assertEquals(invalidFormatStartTimeMessage, MESSAGE_STARTENDTIME_WRONG_FORMAT);
    }


    @Test
    public void editBlocks_correctParams() throws WfException {
        myMember = new Contact("MEMBER");
        String[] validWeek = {"1"};
        String correctMessage = myMember.editBlocks(true, "TEST MEETING", 1, "11:30", 2, "14:30", validWeek);
        assertEquals(correctMessage, MESSAGE_RETURN_SUCCESS);
    }
}
