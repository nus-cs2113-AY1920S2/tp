import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalTime;

public class ScheduleHandler {
    private final Boolean mySchedule_BLOCKED = true;
    private final Boolean mySchedule_FREE = false;
    private Boolean[][] masterSchedule = new Boolean[7][48];
    private ArrayList<ArrayList<Integer>> freeBlocks = new ArrayList<ArrayList<Integer>>(); // ArrayList of free slots in Integer type {startDay, startBlock, endDay, endBlock}

    public ScheduleHandler(ArrayList<TeamMember> teamMemberList) {
        for (int i = 0; i < 7; i++) {
            Arrays.fill(masterSchedule[i], mySchedule_FREE); // fill every 48 index of the 7 days with 0 initially
        }
        for (TeamMember t : teamMemberList) {
            Boolean[][] memberSchedule = t.getSchedule();
            fillMasterSchedule(memberSchedule);
        }
        updateFreeBlocks();
    }

    public Boolean[][] getMasterSchedule(){
        return masterSchedule;
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


    public void updateFreeBlocks() {
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
                    int endBlock = j - 1;
                    if (change) {
                        endDay = i - 1;
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
                    this.freeBlocks.add(freeSlot);
                }
            }
        }
    }


    public void printFreeTimings() {
        for (int i = 0; i < this.freeBlocks.size(); i++) {
            String startDay = getDayFromNumber(this.freeBlocks.get(i).get(0).intValue());
            LocalTime startTime = getTimeFromBlock(this.freeBlocks.get(i).get(1).intValue(), "START");
            String endDay = getDayFromNumber(this.freeBlocks.get(i).get(2).intValue());
            LocalTime endTime = getTimeFromBlock(this.freeBlocks.get(i).get(3).intValue(), "END");
            System.out.println(startDay + " " + startTime + " to " + endDay + " " + endTime);
        }
    }

    public String getDayFromNumber(int dayNum) {
        String day;
        switch (dayNum) {
            case 0:
                day = "Sunday";
                break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
                break;
            default:
                day = "";
        }
        return day;
    }

    public LocalTime getTimeFromBlock(int blockNum, String startOrEnd) {
        int minuteTime = 0;
        int hourTime = 0;

        switch (startOrEnd) {
            case "START":
                hourTime = blockNum / 2;
                if (blockNum % 2 == 1) {
                    minuteTime = 30;
                }
                break;
            case "END":
                if (blockNum == 47){
                    hourTime = 0;
                } else {
                    hourTime = (blockNum+1) / 2;
                }

                if (blockNum % 2 == 0) {
                    minuteTime = 30;
                }

                break;
        }

        LocalTime myTime = LocalTime.of(hourTime, minuteTime);
        return myTime;
    }

    public ArrayList<ArrayList<Integer>> getFreeBlocks() {
        return this.freeBlocks;
    }

}