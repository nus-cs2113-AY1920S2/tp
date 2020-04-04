package model.contact;

import exception.MoException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_RETURN_SUCCESS;
import static common.Messages.MESSAGE_WEEK_RANGE_EMPTY;
import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;



/**
 * This class contains information of a member's schedule in blocks of 30mins interval,
 * as well as get methods to access it private members. It also include methods to convert from
 * user input date to blocks from 0-47, inserted into mySchedule.
 */

public class Contact {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;
    private String memberName;
    private Boolean[][][] mySchedule; //String[13][7][48]; 13 weeks, 7 days, separated into 30mins within 24 hours period.
    private String[][][] myScheduleName;
    private boolean isMainUser = false;

    public Contact(String name) {
        if (name.contains("_main") && !name.replaceFirst("_main","").contains("_main")) {
            isMainUser = true;
            name = name.replace("_main", "");
        }
        this.memberName = name;
        this.mySchedule = new Boolean[13][7][48];
        this.myScheduleName = new String[13][7][48];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    mySchedule[i][j][k] = MYSCHEDULEFREE;
                    myScheduleName[i][j][k] = null;
                }
            }
        }
    }

    public void addBusyBlocks(String meetingName, Integer startDay, String startTime, Integer endDay, String endTime,
                              String[] onWeeks) throws MoException {
        editBlocks(MYSCHEDULEBLOCKED, meetingName, startDay, startTime, endDay, endTime, onWeeks);
    }

    public void addFreeBlocks(String meetingName, Integer startDay, String startTime, Integer endDay, String endTime,
                              String[] onWeeks) throws MoException {
        editBlocks(MYSCHEDULEFREE, meetingName, startDay, startTime, endDay, endTime, onWeeks);
    }

    /** Adds scheduled model.meeting in LocalTime into schedule[][][] data structure.
     *
     * @param meetingName name of the scheduled model.meeting to be added.
     * @param startDay    start day of the model.meeting in integer.
     * @param startTime   start time of the model.meeting in LocalTime format. For eg, 11:30, 14:30, 00:00
     * @param endDay      end day of the model.meeting in integer.
     * @param endTime     end time of the model.meeting in LocalTime format: For eg, 11:30, 14:30, 00:00
     * @param onWeeks     weeks that are suppose to be made busy.
     * @return returns String of error message, else returns "Success" if schedule is successfully edited.
     */
    public String editBlocks(Boolean blockedorfree, String meetingName, Integer startDay,
                             String startTime, Integer endDay, String endTime, String[] onWeeks) throws MoException {
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

        if (onWeeks.length == 0) {
            return MESSAGE_WEEK_RANGE_EMPTY;
        }

        editBlocksLogic(blockedorfree, startBlock, endBlock, startDay, endDay, meetingName, onWeeks);
        return MESSAGE_RETURN_SUCCESS;
    }

    /**
     * Defines the logic of converting from blocks of time and day of model.meeting to data structure.
     * Used in addBusyBlocks().
     */
    private void editBlocksLogic(Boolean blockedorfree, Integer startBlock, Integer endBlock, Integer startDay, Integer endDay,
                                    String meetingName, String[] onWeeks) throws MoException {

        String[] startOnWeeks = onWeeks.clone();
        String[] endOnWeeks = onWeeks.clone();

        Boolean myScheduleStatus = blockedorfree;
        String myScheduleNameStatus = "null";
        if (blockedorfree == MYSCHEDULEBLOCKED) {
            myScheduleNameStatus = meetingName;
        }

        if (startDay > 6 && endDay > 6) {
            startDay -= 7;
            endDay -= 7;
            startOnWeeks[0] = Integer.toString(Integer.parseInt(startOnWeeks[0]) + 1);
            endOnWeeks[0] = Integer.toString(Integer.parseInt(endOnWeeks[0]) + 1);
        } else if (startDay < 7 && endDay > 6) {
            endDay -= 7;
            endOnWeeks[0] = Integer.toString(Integer.parseInt(endOnWeeks[0]) + 1);
        } else if (startDay > 6 && endDay < 7) {
            throw new MoException("Meeting ends before it starts?");
        }

        if (!startDay.equals(endDay)) {
            for (int j = 0; j < startOnWeeks.length; j++) {
                int startDayCopy = startDay; // prevent modifying param arguments
                for (int i = startBlock; i < 48; i++) {
                    mySchedule[Integer.parseInt(startOnWeeks[j]) - 1][startDayCopy][i] = myScheduleStatus;
                    this.myScheduleName[Integer.parseInt(startOnWeeks[j]) - 1][startDayCopy][i] = myScheduleNameStatus;
                }
                startDayCopy++;

                if (startDayCopy > 6) {
                    startDayCopy = 0;
                }
                while (startDayCopy != endDay) {
                    for (int i = 0; i < 48; i++) {
                        mySchedule[Integer.parseInt(startOnWeeks[j]) - 1][startDayCopy][i] = myScheduleStatus;
                        this.myScheduleName[Integer.parseInt(startOnWeeks[j]) - 1][startDayCopy][i] = myScheduleNameStatus;
                    }
                    startDayCopy++;
                    if (startDayCopy > 6) {
                        startDayCopy = 0;
                    }

                }
                for (int i = 0; i < endBlock; i++) {
                    mySchedule[Integer.parseInt(endOnWeeks[j]) - 1][startDayCopy][i] = myScheduleStatus;
                    this.myScheduleName[Integer.parseInt(endOnWeeks[j]) - 1][startDayCopy][i] = myScheduleNameStatus;
                }
            }
        } else {
            for (int j = 0; j < startOnWeeks.length; j++) {
                for (int i = startBlock; i < endBlock; i++) {
                    mySchedule[Integer.parseInt(endOnWeeks[j]) - 1][startDay][i] = myScheduleStatus;
                    this.myScheduleName[Integer.parseInt(endOnWeeks[j]) - 1][startDay][i] = myScheduleNameStatus;
                }
            }
        }
    }

    boolean checkDay(Integer day) {
        return day >= 0 && day <= 13;
    }


    /** Delete a scheduled model.meeting by changing mySchedule[][] via myScheduleName[][].
     * @param meetingName name of model.meeting previously added to be deleted.
     */
    public void deleteBlocksWithName(String meetingName) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    if (myScheduleName[i][j][k] != null && myScheduleName[i][j][k].equals(meetingName)) {
                        mySchedule[i][j][k] = MYSCHEDULEFREE;
                        myScheduleName[i][j][k] = null;
                    }
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

    public Boolean[][][] getSchedule() {
        return this.mySchedule;
    }

    public String[][][] getMyScheduleName() {
        return this.myScheduleName;
    }

    public void setMyScheduleName(String[][][] myScheduleName) {
        this.myScheduleName = myScheduleName;
    }

    /**
     * To be used for model.storage purposes.
     * Sets mySchedule[][] to true/false depending on myScheduleName from disk.
     */
    public void setMyScheduleFromScheduleName() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    if (myScheduleName[i][j][k].equals("null")) {
                        mySchedule[i][j][k] = false;
                    } else {
                        mySchedule[i][j][k] = true;
                    }
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