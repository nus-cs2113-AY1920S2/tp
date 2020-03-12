import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalTime;

public class ScheduleHandler {
    private final Boolean mySchedule_BLOCKED = true;
    private final Boolean mySchedule_FREE = false;
    private Boolean[][] masterSchedule = new Boolean[7][48];

    public ScheduleHandler(ArrayList<TeamMember> teamMemberList) {
        for (int i = 0; i < 7; i++) {
            Arrays.fill(masterSchedule[i], mySchedule_FREE); // fill every 48 index of the 7 days with 0 initially
        }
        for (TeamMember t : teamMemberList) {
            Boolean[][] memberSchedule = t.getSchedule();
            fillMasterSchedule(memberSchedule);
        }
    }

    public void fillMasterSchedule(Boolean[][] s) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (s[i][j]) {
                    masterSchedule[i][j] = mySchedule_BLOCKED;
                }
            }
        }
    }


    public ArrayList<ArrayList<Integer>> calculateFreeTime() {
        ArrayList<ArrayList<Integer>> freeTimings = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (masterSchedule[i][j] == mySchedule_FREE) {
                    int startDay = i;
                    int startBlock = j;
                    boolean change = false;
                    boolean end = false;
                    while(masterSchedule[i][j] == mySchedule_FREE){
                        if (change){
                            change = false;
                        }
                        if (i == 6 && j == 47) {
                            end = true;
                            break;
                        }
                        if (j == 47) {
                            change = true;
                            j = 0;
                            i++;
                        }
                        j++;
                    }
                    int endDay = i;
                    int endBlock = j--;
                    if (change) {
                        endDay = i--;
                        endBlock = 47;
                    }
                    if (end) {
                        endBlock = 47;
                    }
                    ArrayList<Integer> freeSlot = new ArrayList<Integer>();
                    freeSlot.add(startDay);
                    freeSlot.add(startBlock);
                    freeSlot.add(endDay);
                    freeSlot.add(endBlock);
                    freeTimings.add(freeSlot);
                }
            }
        }
        return freeTimings;
    }


}