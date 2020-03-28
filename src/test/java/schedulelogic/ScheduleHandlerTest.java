package schedulelogic;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleHandlerTest {

    private ScheduleHandler myScheduleHandler;
    private final Boolean myScheduleBlocked = true;
    private final Boolean myScheduleFree = false;

    @Test
    public void testGetDayFromNumber() {
        assertEquals("Sunday", ScheduleHandler.getDayFromNumber(0));
    }

    @Test
    public void testGetTimeFromBlock_EndTime() {
        assertEquals(LocalTime.of(23,30), ScheduleHandler.getTimeFromBlock(46,"END"));
    }

    @Test
    public void testGetTimeFromBlock_EndTime_Midnight() {
        assertEquals(LocalTime.of(0,0), ScheduleHandler.getTimeFromBlock(47,"END"));
    }

    @Test
    public void testGetTimeFromBlock_StartTime() {
        assertEquals(LocalTime.of(23,0), ScheduleHandler.getTimeFromBlock(46,"START"));
    }

    @Test
    public void testScheduleHandler() {
        ArrayList<TeamMember> myScheduleList = new ArrayList<>();
        final TeamMember member1 = new TeamMember("member1");
        final TeamMember member2 = new TeamMember("member2");
        member1.addBusyBlocks("schedule1", 0, "13:00", 0, "19:00");
        member1.addBusyBlocks("schedule2", 4, "16:00", 4, "18:00");
        myScheduleList.add(member1);
        myScheduleList.add(member2);
        myScheduleHandler = new ScheduleHandler(myScheduleList);
        Boolean[][] myMasterSchedule = myScheduleHandler.getMasterSchedule();
        final ArrayList<ArrayList<Integer>> myFreeBlocks = myScheduleHandler.getFreeBlocks();

        Boolean[][] myMasterScheduleAnswer = new Boolean[7][48];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(myMasterScheduleAnswer[i], myScheduleFree); // fill every 48 index of the 7 days with 0 initially
        }
        for (int j = 26; j <= 37; j++) {
            myMasterScheduleAnswer[0][j] = myScheduleBlocked;
        }
        for (int j = 32; j <= 35; j++) {
            myMasterScheduleAnswer[4][j] = myScheduleBlocked;
        }
        ArrayList<ArrayList<Integer>> myFreeBlocksAnswer = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> slot1 = ScheduleHandler.makeSlot(4,36,0,25);
        ArrayList<Integer> slot2 = ScheduleHandler.makeSlot(0,38,4,31);
        myFreeBlocksAnswer.add(slot1);
        myFreeBlocksAnswer.add(slot2);
        assertEquals(myFreeBlocksAnswer, myFreeBlocks);
    }

}

