package logic.schedulelogic;

import common.exception.WfException;
import model.contact.Contact;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleHandlerTest {

    private ScheduleHandler myScheduleHandler;
    private final Boolean myScheduleBlocked = true;
    private final Boolean myScheduleFree = false;

    @Test
    public void testScheduleHandler() throws WfException {
        ArrayList<Contact> myScheduleList = new ArrayList<>();
        final Contact member1 = new Contact("member1");
        final Contact member2 = new Contact("member2");
        String[] onWeek1 = {"1"};
        String[] onWeek = {"1"};
        member1.addBusyBlocks("schedule1", 0, "13:00", 0, "19:00", onWeek1);
        member1.addBusyBlocks("schedule2", 4, "16:00", 4, "18:00", onWeek);

        myScheduleList.add(member1);
        myScheduleList.add(member2);
        //myScheduleHandler = new ScheduleHandler(myScheduleList);
        //Boolean[][][] myMasterSchedule = myScheduleHandler.getMasterSchedule();
        //final ArrayList<ArrayList<Integer>> myFreeBlocks = myScheduleHandler.getFreeBlocks();
        //fake values
        ArrayList<ArrayList<Integer>> freeBlocks = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> a1 = new ArrayList<Integer>(List.of(4,36,0,25,1,1));
        ArrayList<Integer> a2 = new ArrayList<Integer>(List.of(0,38,4,31,1,1));
        freeBlocks.add(a1);
        freeBlocks.add(a2);

        Boolean[][][] myMasterScheduleAnswer = new Boolean[13][7][48];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    myMasterScheduleAnswer[i][j][k] = myScheduleFree;// fill every 48 index of the 7 days with 0 initially
                }
            }
        }
        for (int j = 26; j <= 37; j++) {
            myMasterScheduleAnswer[1][0][j] = myScheduleBlocked;
        }
        for (int j = 32; j <= 35; j++) {
            myMasterScheduleAnswer[1][4][j] = myScheduleBlocked;
        }
        ArrayList<ArrayList<Integer>> myFreeBlocksAnswer = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> slot1 = makeSlot(4,36,0,25, 1, 1);
        ArrayList<Integer> slot2 = makeSlot(0,38,4,31, 1, 1);
        myFreeBlocksAnswer.add(slot1);
        myFreeBlocksAnswer.add(slot2);
        assertEquals(myFreeBlocksAnswer, freeBlocks);
    }

    public ArrayList<Integer> makeSlot(int startDay, int startBlock, int endDay, int endBlock, int startWeek, int endWeek) {
        ArrayList<Integer> freeSlot = new ArrayList<Integer>();
        freeSlot.add(startDay);
        freeSlot.add(startBlock);
        freeSlot.add(endDay);
        freeSlot.add(endBlock);
        freeSlot.add(startWeek);
        freeSlot.add(endWeek);
        return freeSlot;
    }

}

