package schedulelogic;

import exception.MoException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;

/**
 * This class contains information of a member's schedule in blocks of 30mins interval,
 * as well as get methods to access it private members. It also include methods to convert from
 * user input date to blocks from 0-47, inserted into mySchedule.
 */

public class TeamMember {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;
    private String memberName;
    private Boolean[][] mySchedule; //String[7][48]; 7 days, separated into 30mins within 24 hours period.
    private String[][] myScheduleName;
    private boolean isMainUser = false;

    public TeamMember(String name) {
        if (name.contains("_main")) {
            isMainUser = true;
            name = name.replace("_main", "");
        }
        this.memberName = name;
        this.mySchedule = new Boolean[7][48];
        this.myScheduleName = new String[7][48];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(mySchedule[i], MYSCHEDULEFREE);
            Arrays.fill(myScheduleName[i], null);

        }
    }

    /**
     * Public method to add
     *
     * @param meetingName name of the scheduled meeting to be added.
     * @param startDay    start day of the meeting in integer.
     * @param startTime   start time of the meeting in LocalTime format. For eg, 11:30, 14:30, 00:00
     * @param endDay      end day of the meeting in integer.
     * @param endTime     end time of the meeting in LocalTime format: For eg, 11:30, 14:30, 00:00
     * @return returns String of error message, else returns "Success" if schedule is successfully edited.
     */
    public String addBusyBlocks(String meetingName, Integer startDay,
                                String startTime, Integer endDay, String endTime) {
        LocalTime localTimeStart;
        LocalTime localTimeEnd;
        try {
            localTimeStart = LocalTime.parse(startTime);
            localTimeEnd = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            System.out.println(MESSAGE_STARTENDTIME_OUT_OF_RANGE);
            return MESSAGE_STARTENDTIME_OUT_OF_RANGE;
        }
        Integer startBlock = 0;
        Integer endBlock = 0;
        try {
            startBlock = getBlocksFromTime(localTimeStart);
            endBlock = getBlocksFromTime(localTimeEnd);
        } catch (MoException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

        if (!checkDay(startDay) || !checkDay(endDay)) {
            return MESSAGE_STARTENDDAY_OUT_OF_RANGE;
        }

        addBusyBlocksLogic(startBlock, endBlock, startDay, endDay, meetingName);
        return "SUCCESS";
    }

    /**
     * Defines the logic of converting from blocks of time and day of meeting to data structure.
     * Used in addBusyBlocks().
     */
    private void addBusyBlocksLogic(Integer startBlock, Integer endBlock, Integer startDay, Integer endDay,
                                    String meetingName) {
        if (!startDay.equals(endDay)) {
            int startDayCopy = startDay; // prevent modifying param arguments
            for (int i = startBlock; i < 48; i++) {
                mySchedule[startDayCopy][i] = MYSCHEDULEBLOCKED;
                this.myScheduleName[startDayCopy][i] = meetingName;
            }
            startDayCopy++;
            while (startDayCopy != endDay) {
                for (int i = 0; i < 48; i++) {
                    mySchedule[startDayCopy][i] = MYSCHEDULEBLOCKED;
                    this.myScheduleName[startDayCopy][i] = meetingName;
                }
                startDayCopy++;
            }
            for (int i = 0; i < endBlock; i++) {
                mySchedule[startDayCopy][i] = MYSCHEDULEBLOCKED;
                this.myScheduleName[startDayCopy][i] = meetingName;
            }
        } else {
            for (int i = startBlock; i < endBlock; i++) {
                mySchedule[startDay][i] = MYSCHEDULEBLOCKED;
                this.myScheduleName[startDay][i] = meetingName;
            }
        }
    }

    boolean checkDay(Integer day) {
        return day >= 0 && day <= 6;
    }


    /**
     * Delete a scheduled meeting by changing mySchedule[][] via myScheduleName[][]
     *
     * @param meetingName name of meeting previously added to be deleted.
     */
    public void deleteBlocksWithName(String meetingName) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (myScheduleName[i][j] != null && myScheduleName[i][j].equals(meetingName)) {
                    mySchedule[i][j] = MYSCHEDULEFREE;
                    myScheduleName[i][j] = null;

                }
            }
        }
    }

    /**
     * Converts LocalTime object into blocks of time to be inserted into data structure mySchedule[][].
     *
     * @param myTime LocalTime to be converted.
     * @return one-based indexing of the block from LocalTime.
     * @throws MoException Throws error if LocalTime isn't in multiples of 30minutes.
     */
    private Integer getBlocksFromTime(LocalTime myTime) throws MoException {
        int minuteBlocks = -1;
        int hourBlocks = -1;
        switch (myTime.getMinute()) {
            case 0:
                minuteBlocks = 0;
                break;
            case 30:
                minuteBlocks = 1;
                break;
            default:
                throw new MoException(MESSAGE_STARTENDTIME_WRONG_FORMAT);

        }
        hourBlocks = myTime.getHour() * 2;
        return minuteBlocks + hourBlocks;
    }

    public String getName() {
        return this.memberName;
    }

    public Boolean[][] getSchedule() {
        return this.mySchedule;
    }

    public String[][] getMyScheduleName() {
        return this.myScheduleName;
    }

    public void setMyScheduleName(String[][] myScheduleName) {
        this.myScheduleName = myScheduleName;
    }

    /**
     * To be used for storage purposes.
     * Sets mySchedule[][] to true/false depending on myScheduleName from disk.
     */
    public void setMyScheduleFromScheduleName() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (myScheduleName[i][j].equals("null")) {
                    mySchedule[i][j] = false;
                } else {
                    mySchedule[i][j] = true;
                }
            }
        }
    }

    public void setMainUser() {
        this.isMainUser = true;
    }

    public boolean isMainUser() {
        return isMainUser;
    }
}
