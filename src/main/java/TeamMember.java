import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TeamMember {
    private final Boolean mySchedule_BLOCKED = false;
    private final Boolean mySchedule_FREE = true;
    private String memberName;
    private Boolean[][] mySchedule; //String[7][48]; 7 days, separated into 30mins within 24 hours period.
    private String[][] myScheduleName;

    public TeamMember(String name) {
        this.memberName = name;
        this.mySchedule = new Boolean[7][48];
        this.myScheduleName = new String[7][48];
        for (int i=0; i<7; i++) {
            Arrays.fill(mySchedule[i], mySchedule_FREE); // fill every 48 index of the 7 days with 0 initially
            Arrays.fill(myScheduleName[i], "");
        }
    }

    // Monday = 0, Tues = 1 ..... Sunday = 6
    // TODO BUG when startday and endday is not same.
    public void addBusyBlocks(String meetingName, Integer startDay, String userStart, Integer endDay, String userEnd) {
        LocalTime startTime = LocalTime.parse(userStart);
        LocalTime endTime = LocalTime.parse(userEnd);
        if (startDay != endDay) {
            Integer startDayCopy = startDay;
            try {
                Integer startBlock = getBlocksFromTime(startTime, "START");
                for (int i = startBlock; i < 48; i++) {
                    mySchedule[startDay][i] = mySchedule_BLOCKED;
                    myScheduleName[startDay][i] = meetingName;
                }
                while (startDayCopy != endDay) {
                    for (int i = 0; i < 48; i++) {
                        mySchedule[startDayCopy][i] = mySchedule_BLOCKED;
                        myScheduleName[startDay][i] = meetingName;
                    }
                    startDayCopy++;
                }
                Integer endBlock = getBlocksFromTime(endTime, "END");
                for (int i = 0; i <= endBlock; i++) {
                    mySchedule[startDayCopy][i] = mySchedule_BLOCKED;
                    myScheduleName[startDay][i] = meetingName;
                }
            } catch (MoException e) {
                System.out.println("OOPS: " + e.getMessage());
            }
        } else {
            try {
                Integer startBlock = getBlocksFromTime(startTime, "START");
                Integer endBlock = getBlocksFromTime(endTime, "END");
                for (int i = startBlock; i <= endBlock; i++) {
                    mySchedule[startDay][i] = mySchedule_BLOCKED;
                    myScheduleName[startDay][i] = meetingName;
                }
            } catch (MoException e) {
                System.out.println("OOPS: " + e.getMessage());
            }
        }

    }
    public void deleteBusyBlocks(String meetingName) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (myScheduleName[i][j].equals(meetingName)) {
                    mySchedule[i][j] = mySchedule_FREE;
                    myScheduleName[i][j] = "";
                }
            }
        }
    }
    public Integer getBlocksFromTime(LocalTime myTime, String startOrEnd) throws MoException {
        int minuteBlocks = 0;
        int hourBlocks = 0;
        switch (myTime.getMinute()) {
            case 0:
                minuteBlocks = 0;
                break;
            case 30:
                minuteBlocks = 1;
                break;
            default:
                throw new MoException("Must be within 30 minutes block frame");
        }
        switch (startOrEnd) {
            case "START":
                hourBlocks = myTime.getHour() * 2;
                break;
            case "END":
                if (myTime.getHour() == 0)
                    hourBlocks = 48;
                else
                    hourBlocks = myTime.getHour() * 2 - 1; // For eg, if user types 2300-2330, its equivalent to 1 block: block 46, 2230-2300 : block 45
        }
        return minuteBlocks + hourBlocks - 1; // convert to 0-based indexing
    }
    public String getName() {
        return this.memberName;
    }
    public Boolean[][] getSchedule() {
        return this.mySchedule;
    }

    //TODO create an ASCII ART timetable based on schedule and blocked dates.

}
