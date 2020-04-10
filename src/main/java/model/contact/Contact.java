package model.contact;

import common.exception.WfException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;


import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_INVALID_SLOT_RANGE;
import static common.Messages.MESSAGE_INVALID_MEETING;
import static common.Messages.MESSAGE_INVALID_EDIT;
import static common.Messages.MESSAGE_WEEK_RANGE_EMPTY;
import static common.Messages.MESSAGE_RETURN_SUCCESS;


/**
 * This class contains information of a contact's schedule in blocks of 30mins interval.
 * It includes methods to access its private members.
 * It includes methods to modify contact's schedules and delete contact.
 * It also includes a private class Slot used exclusively by Contact.java
 */

public class Contact {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;
    private String contactName;
    private Boolean[][][] mySchedule; //String[13][7][48]; 13 weeks, 7 days, separated into 30mins within 24 hours period.
    private String[][][] myScheduleName;
    private boolean isMainUser = false;

    public Contact(String name) {
        if (name.contains("_main") && !name.replaceFirst("_main", "").contains("_main")) {
            isMainUser = true;
            name = name.replace("_main", "");
        }
        this.contactName = name;
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

    /**
     * Converts LocalTime object into blocks of time to be inserted into data structure mySchedule[][].
     *
     * @param myTime LocalTime to be converted.
     * @return one-based indexing of the block from LocalTime.
     * @throws WfException Throws error if LocalTime isn't in multiples of 30minutes.
     */
    private static Integer getBlocksFromTime(LocalTime myTime) throws WfException {
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
            throw new WfException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
        }
        hourBlocks = myTime.getHour() * 2;
        return minuteBlocks + hourBlocks;
    }

    public void addBusyBlocks(String scheduleNameStatus, Integer startDay, String startTime, Integer endDay,
                              String endTime, String[] onWeeks) throws WfException {
        editBlocks(MYSCHEDULEBLOCKED, scheduleNameStatus, startDay, startTime, endDay, endTime, onWeeks);
    }

    public void addFreeBlocks(Integer startDay, String startTime, Integer endDay, String endTime,
                              String[] onWeeks) throws WfException {
        editBlocks(MYSCHEDULEFREE, "null", startDay, startTime, endDay, endTime, onWeeks);
    }

    /**
     * Converts time into 30-min blocks for easy processing of slot by editBlocksLogic().
     * Throws error if slot is not valid.
     *
     * @param blockedOrFree      Boolean variable that represents busy or free, to be inserted into mySchedule[][][].
     * @param scheduleNameStatus String variable that is the name to be inserted into myScheduleName[][][].
     *                           Can be either contactName (edit busy function) or meetingName (schedule function)
     * @param startDay           Start day of the slot in Integer.
     * @param startTime          Start time of the slot in LocalTime format. For eg, 11:30, 14:30, 00:00
     * @param endDay             End day of the slot in Integer.
     * @param endTime            End time of the slot in LocalTime format: For eg, 11:30, 14:30, 00:00
     * @param onWeeks            Weeks that are suppose to be edited.
     * @return Returns String of error message, else returns "Success" if schedule is successfully edited.
     * @throws WfException Throws error if slot is not valid.
     */
    public String editBlocks(Boolean blockedOrFree, String scheduleNameStatus, Integer startDay,
                             String startTime, Integer endDay, String endTime, String[] onWeeks) throws WfException {
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
        } catch (WfException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

        if (!checkDay(startDay) || !checkDay(endDay)) {
            return MESSAGE_STARTENDDAY_OUT_OF_RANGE;
        }

        if (onWeeks.length == 0) {
            return MESSAGE_WEEK_RANGE_EMPTY;
        }

        editBlocksLogic(blockedOrFree, startBlock, endBlock, startDay, endDay, scheduleNameStatus, onWeeks);
        return MESSAGE_RETURN_SUCCESS;
    }

    /**
     * Defines the logic of modifying mySchedule[][][] and myScheduleName[][][] with given slot.
     * Modifies mySchedule[][][] to be busy or free for a given time slot.
     * Modifies myScheduleName[][][] to meetingName, contactName or null for a given time slot.
     * Used in edit function and schedule meeting function
     *
     * @param blockedOrFree      Boolean variable that represents busy or free, to be inserted into mySchedule[][][].
     * @param scheduleNameStatus String variable that is the name to be inserted into myScheduleName[][][].
     *                           Can be either contactName (edit busy function) or meetingName (schedule function)
     * @param startDay           Start day of the slot in Integer.
     * @param startBlock         Start time of the slot represented by a 30-min block in Integer.
     * @param endDay             End day of the slot in Integer.
     * @param endBlock           End time of the slot represented by a 30-min block in Integer.
     * @param onWeeks            Weeks that are suppose to be edited.
     * @throws WfException Throws error if slot is not valid.
     */
    private void editBlocksLogic(Boolean blockedOrFree, Integer startBlock, Integer endBlock, Integer startDay, Integer endDay,
                                 String scheduleNameStatus, String[] onWeeks) throws WfException {

        String[] startOnWeeks = onWeeks.clone();
        String[] endOnWeeks = onWeeks.clone();

        Boolean myScheduleStatus = blockedOrFree;
        String myScheduleNameStatus = scheduleNameStatus;

        if (startDay > 6 && endDay > 6) {
            startDay -= 7;
            endDay -= 7;
            startOnWeeks[0] = Integer.toString(Integer.parseInt(startOnWeeks[0]) + 1);
            endOnWeeks[0] = Integer.toString(Integer.parseInt(endOnWeeks[0]) + 1);
        } else if (startDay < 7 && endDay > 6) {
            endDay -= 7;
            endOnWeeks[0] = Integer.toString(Integer.parseInt(endOnWeeks[0]) + 1);
        } else if (startDay > 6 && endDay < 7) {
            throw new WfException("Start Day is later than End Day.");
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

    public String getName() {
        return this.contactName;
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
     * To be used for storage purposes.
     * Sets mySchedule[][] to true/false depending on myScheduleName from disk.
     */
    public void setMyScheduleFromScheduleName() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    mySchedule[i][j][k] = !myScheduleName[i][j][k].equals("null");
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

    /**
     * Used in edit function to check if slot to be edited clashes with meetings in myScheduleName[][][].
     *
     * @param startDay          Start day of the slot in Integer.
     * @param startTime         Start time of the slot in LocalTime format. For eg, 11:30, 14:30, 00:00
     * @param endDay            End day of the slot in Integer.
     * @param endTime           End time of the slot in LocalTime format: For eg, 11:30, 14:30, 00:00
     * @param currentWeekNumber Current week that is supposed to be edited.
     * @return Returns true if the slot does not clash with meetings.
     * @throws WfException Throws error if invalid edit.
     */
    public boolean isValidEdit(Integer startDay,
                               LocalTime startTime, Integer endDay, LocalTime endTime,
                               int currentWeekNumber) throws WfException {



        Slot slot = new Slot(startDay, startTime, endDay, endTime, currentWeekNumber).invoke();
        startDay = slot.getStartDay();
        endDay = slot.getEndDay();
        int startWeekNumber = slot.getStartWeekNumber();
        int endWeekNumber = slot.getEndWeekNumber();
        Integer startBlock = slot.getStartBlock();
        Integer endBlock = slot.getEndBlock();

        isValidLogic(startDay, endDay, startWeekNumber, endWeekNumber, startBlock, endBlock, this.myScheduleName,
            "meeting", MESSAGE_INVALID_EDIT);
        return true;
    }

    /**
     * Used in schedule function to check if slot to be edited is busy in mySchedule[][][].
     *
     * @param startDay          Start day of the slot in Integer.
     * @param startTime         Start time of the slot in LocalTime format. For eg, 11:30, 14:30, 00:00
     * @param endDay            End day of the slot in Integer.
     * @param endTime           End time of the slot in LocalTime format: For eg, 11:30, 14:30, 00:00
     * @param currentWeekNumber Current week that is supposed to be edited.
     * @return Returns true if the slot does not clash with meetings.
     * @throws WfException Throws error if invalid edit.
     */
    public boolean isValidMeeting(Integer startDay,
                                  LocalTime startTime, Integer endDay, LocalTime endTime,
                                  int currentWeekNumber) throws WfException {
        Slot slot = new Slot(startDay, startTime, endDay, endTime, currentWeekNumber).invoke();
        startDay = slot.getStartDay();
        endDay = slot.getEndDay();
        int startWeekNumber = slot.getStartWeekNumber();
        int endWeekNumber = slot.getEndWeekNumber();
        Integer startBlock = slot.getStartBlock();
        Integer endBlock = slot.getEndBlock();

        isValidLogic(startDay, endDay, startWeekNumber, endWeekNumber, startBlock, endBlock, this.mySchedule,
            MYSCHEDULEBLOCKED, MESSAGE_INVALID_MEETING);
        return true;
    }

    /**
     * Defines the logic of checking if a slot is valid for a meeting or for editing.
     *
     * @param startDay            Start day of the slot in Integer.
     * @param startBlock          Start time of the slot represented by a 30-min block in Integer.
     * @param endDay              End day of the slot in Integer.
     * @param endBlock            End time of the slot represented by a 30-min block in Integer.
     * @param startWeekNumber     Start week that is supposed to be edited.
     * @param endWeekNumber       End week that is supposed to be edited.
     * @param mainUserSchedule    Main user's schedule used to check slot.
     *                            Either in String[][][] or Boolean[][][].
     * @param invalidBlock        Status in mainUserSchedule to be detected for invalid slot.
     *                            Either in String format or Boolean format.
     * @param invalidBlockMessage Message to be thrown if slot is invalid in String.
     * @return Returns true if the slot does not clash with meetings.
     * @throws WfException Throws error if invalid edit.
     */
    private <T> void isValidLogic(Integer startDay, Integer endDay, int startWeekNumber, int endWeekNumber,
                                  Integer startBlock, Integer endBlock, T[][][] mainUserSchedule, T invalidBlock,
                                  String invalidBlockMessage) throws WfException {

        if (startDay.equals(endDay)) {
            if (startBlock.equals(endBlock)) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][startBlock].equals(invalidBlock)) {
                    throw new WfException(invalidBlockMessage);
                }
            } else if (startBlock < endBlock) {
                for (int i = startBlock; i <= endBlock; ++i) {
                    if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(invalidBlock)) {
                        throw new WfException(invalidBlockMessage);
                    }
                }
            } else if (startBlock > endBlock) {
                throw new WfException(MESSAGE_INVALID_SLOT_RANGE);
            }
        }

        if (startDay < endDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(invalidBlock)) {
                    throw new WfException(invalidBlockMessage);
                }
            }
            for (int i = startDay + 1; i <= endDay - 1; ++i) {
                for (int j = 0; j < 48; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j].equals(invalidBlock)) {
                        throw new WfException(invalidBlockMessage);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i].equals(invalidBlock)) {
                    throw new WfException(invalidBlockMessage);
                }
            }

        }

        if (endDay < startDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(invalidBlock)) {
                    throw new WfException(invalidBlockMessage);
                }
            }

            for (int i = startDay + 1; i <= 6; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j].equals(invalidBlock)) {
                        throw new WfException(invalidBlockMessage);
                    }
                }
            }

            for (int i = 0; i <= endDay - 1; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[endWeekNumber - 1][i][j].equals(invalidBlock)) {
                        throw new WfException(invalidBlockMessage);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i].equals(invalidBlock)) {
                    throw new WfException(invalidBlockMessage);
                }
            }
        }
    }

    /**
     * This private class contains information about a time slot, generated with startDay, startTime, endDay, endTime.
     * It contains the logic that converts startTime and endTime to 30-min startBlock and endBlock.
     * It contains the logic that converts startDay, endDay and currentWeekNumber to startWeekNumber and endWeekNumber.
     */
    private static class Slot {
        private Integer startDay;
        private LocalTime startTime;
        private Integer endDay;
        private LocalTime endTime;
        private int currentWeekNumber;
        private int startWeekNumber;
        private int endWeekNumber;
        private Integer startBlock;
        private Integer endBlock;

        public Slot(Integer startDay, LocalTime startTime, Integer endDay, LocalTime endTime, int currentWeekNumber) {
            this.startDay = startDay;
            this.startTime = startTime;
            this.endDay = endDay;
            this.endTime = endTime;
            this.currentWeekNumber = currentWeekNumber;
        }

        public Integer getStartDay() {
            return startDay;
        }

        public Integer getEndDay() {
            return endDay;
        }

        public int getStartWeekNumber() {
            return startWeekNumber;
        }

        public int getEndWeekNumber() {
            return endWeekNumber;
        }

        public Integer getStartBlock() {
            return startBlock;
        }

        public Integer getEndBlock() {
            return endBlock;
        }

        public Slot invoke() throws WfException {

            if (!(startDay >= 0 && startDay <= 13) || !(endDay >= 0 && endDay <= 13)) {
                throw new WfException(MESSAGE_STARTENDDAY_OUT_OF_RANGE);
            }
            startWeekNumber = currentWeekNumber;
            endWeekNumber = currentWeekNumber;

            if (startDay > 6) {
                startDay -= 7;
                startWeekNumber++;
            }
            if (endDay > 6) {
                endDay -= 7;
                endWeekNumber++;
            }

            if ((startTime.getMinute() != 0 && startTime.getMinute() != 30)
                || (endTime.getMinute() != 0 && endTime.getMinute() != 30)) {
                throw new WfException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
            }

            startBlock = getBlocksFromTime(startTime);
            endBlock = -1;
            if (endTime == LocalTime.parse("00:00")) {
                endBlock = 47;
                if (endDay == 0) {
                    endDay = 6;
                } else {
                    endDay = endDay - 1;
                }
            } else {
                endBlock = getBlocksFromTime(endTime) - 1;
            }
            return this;
        }
    }
}