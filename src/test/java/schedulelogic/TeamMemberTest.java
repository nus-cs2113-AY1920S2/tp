package schedulelogic;// CHECKSTYLE:OFF

import org.junit.jupiter.api.Test;

import static common.Messages.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamMemberTest {
    TeamMember myMember;

    @Test
    public void addBusyBlocks_outOfRangeTime() {
        int validStartDay = 1;
        int validEndDay = 3;
        String validStartTime = "08:30";
        String invalidOutOfRangeEndTime = "24:00";
        String meetingName = "TEST_MEETING";

        myMember = new TeamMember("MEMBER");
        String invalidOutOfRangeEndTime_Message = myMember.addBusyBlocks(meetingName, validStartDay, validStartTime, validEndDay, invalidOutOfRangeEndTime);
        assertEquals(invalidOutOfRangeEndTime_Message, MESSAGE_STARTENDTIME_OUT_OF_RANGE);
    }

    @Test
    public void addBusyBlocks_outOfRangeDay() {
        int invalidOutOfRangeStartDay = -1;
        int validStartDay = 1;
        int invalidOutOfRangeEndDay = 9;
        int validEndDay = 3;
        String validStartTime = "08:30";
        String validEndTime = "12:00";
        String meetingName = "TEST_MEETING";

        myMember = new TeamMember("MEMBER");
        String invalidOutOfRangeEndDay_Message = myMember.addBusyBlocks(meetingName, validStartDay, validStartTime, invalidOutOfRangeEndDay, validEndTime);
        assertEquals(invalidOutOfRangeEndDay_Message, MESSAGE_STARTENDDAY_OUT_OF_RANGE);

        myMember = new TeamMember("MEMBER");
        String invalidOutOfRangeStartDay_Message = myMember.addBusyBlocks(meetingName, invalidOutOfRangeStartDay, validStartTime, validEndDay, validEndTime);
        assertEquals(invalidOutOfRangeStartDay_Message, MESSAGE_STARTENDDAY_OUT_OF_RANGE);
    }

    @Test
    public void addBusyBlocks_timeNotInBlocks() {
        String meetingName = "TEST_MEETING";
        int validStartDay = 1;
        int validEndDay = 3;
        String invalidFormatStartTime = "08:35";
        String validEndTime = "12:00";

        myMember = new TeamMember("MEMBER");
        String invalidFormatStartTime_Message = myMember.addBusyBlocks(meetingName, validStartDay, invalidFormatStartTime, validEndDay, validEndTime);
        assertEquals(invalidFormatStartTime_Message, MESSAGE_STARTENDTIME_WRONG_FORMAT);
    }

    @Test
    public void addBusyBlocks_correctParams() {
        myMember = new TeamMember("MEMBER");
        String correctMessage = myMember.addBusyBlocks("TEST MEETING", 1, "11:30", 2, "14:30");
        assertEquals(correctMessage, MESSAGE_RETURN_SUCCESS);
    }
}
