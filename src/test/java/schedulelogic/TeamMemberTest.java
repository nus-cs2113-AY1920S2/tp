package schedulelogic;

import org.junit.jupiter.api.Test;

import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_RETURN_SUCCESS;
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
        String invalidOutOfRangeEndTimeMessage = myMember.addBusyBlocks(meetingName,
                validStartDay, validStartTime, validEndDay, invalidOutOfRangeEndTime);
        assertEquals(invalidOutOfRangeEndTimeMessage, MESSAGE_STARTENDTIME_OUT_OF_RANGE);
    }

    @Test
    public void addBusyBlocks_outOfRangeDay() {
        int validStartDay = 1;
        int invalidOutOfRangeEndDay = 9;
        String validStartTime = "08:30";
        String validEndTime = "12:00";
        String meetingName = "TEST_MEETING";
        myMember = new TeamMember("MEMBER");
        String invalidOutOfRangeEndDayMessage = myMember.addBusyBlocks(meetingName,
                validStartDay, validStartTime, invalidOutOfRangeEndDay, validEndTime);
        assertEquals(invalidOutOfRangeEndDayMessage, MESSAGE_STARTENDDAY_OUT_OF_RANGE);

        myMember = new TeamMember("MEMBER");
        int validEndDay = 3;
        int invalidOutOfRangeStartDay = -1;
        String invalidOutOfRangeStartDayMessage = myMember.addBusyBlocks(meetingName,
                invalidOutOfRangeStartDay, validStartTime, validEndDay, validEndTime);
        assertEquals(invalidOutOfRangeStartDayMessage, MESSAGE_STARTENDDAY_OUT_OF_RANGE);
    }

    @Test
    public void addBusyBlocks_timeNotInBlocks() {
        String meetingName = "TEST_MEETING";
        int validStartDay = 1;
        int validEndDay = 3;
        String invalidFormatStartTime = "08:35";
        String validEndTime = "12:00";

        myMember = new TeamMember("MEMBER");
        String invalidFormatStartTimeMessage = myMember.addBusyBlocks(meetingName,
                validStartDay, invalidFormatStartTime, validEndDay, validEndTime);
        assertEquals(invalidFormatStartTimeMessage, MESSAGE_STARTENDTIME_WRONG_FORMAT);
    }

    @Test
    public void addBusyBlocks_correctParams() {
        myMember = new TeamMember("MEMBER");
        String correctMessage = myMember.addBusyBlocks("TEST MEETING", 1, "11:30", 2, "14:30");
        assertEquals(correctMessage, MESSAGE_RETURN_SUCCESS);
    }
}
