package logic.schedulelogic;


import exception.MoException;
import model.contact.Contact;
import model.meeting.Meeting;
import java.time.LocalTime;
import java.util.ArrayList;

import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_INVALID_MEETING;
import static common.Messages.MESSAGE_INVALID_MEETING_RANGE;



/**
 * TESTING SUMMARY DOC.
 */

public class ScheduleHandler {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;
    private static Boolean[][][] masterSchedule = new Boolean[13][7][48];
    // ArrayList of free slots in Integer type {startDay, startBlock, endDay, endBlock}
    private static ArrayList<ArrayList<Integer>> freeBlocks = new ArrayList<ArrayList<Integer>>();


    public ScheduleHandler(ArrayList<Contact> contactList) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    masterSchedule[i][j][k] = MYSCHEDULEFREE; // fill every 48 index of 7 days of 13 weeks with 0 initially
                }
            }
        }
        for (Contact t : contactList) {
            Boolean[][][] memberSchedule = t.getSchedule();
            fillMasterSchedule(memberSchedule);
        }
        //updateFreeBlocks();
    }

    public static ArrayList<Integer> makeSlot(int startDay, int startBlock, int endDay, int endBlock) {
        ArrayList<Integer> freeSlot = new ArrayList<Integer>();
        freeSlot.add(startDay);
        freeSlot.add(startBlock);
        freeSlot.add(endDay);
        freeSlot.add(endBlock);
        return freeSlot;
    }

    public static String getDayFromNumber(int dayNum) {
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

    public static LocalTime getTimeFromBlock(int blockNum, String startOrEnd) {
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
            if (blockNum == 47) {
                hourTime = 0;
            } else {
                hourTime = (blockNum + 1) / 2;
            }

            if (blockNum % 2 == 0) {
                minuteTime = 30;
            }
            break;
        default:
            minuteTime = 0;
            hourTime = 0;
        }

        LocalTime myTime = LocalTime.of(hourTime, minuteTime);
        return myTime;
    }

    public Boolean[][][] getMasterSchedule() {
        return masterSchedule;
    }

    private void fillMasterSchedule(Boolean[][][] s) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    if (s[i][j][k] == MYSCHEDULEBLOCKED) {
                        this.masterSchedule[i][j][k] = MYSCHEDULEBLOCKED;
                    }
                }
            }
        }
    }

    private void updateFreeBlocks() {
        for (int k = 0; k < 13; k++) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 48; j++) {
                    if (masterSchedule[k][i][j] == MYSCHEDULEFREE) {
                        boolean change = false;
                        boolean end = false;
                        final int startDay = i;
                        final int startBlock = j;
                        while (masterSchedule[k][i][j] == MYSCHEDULEFREE) {
                            if (change) {
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
                            endBlock = 47;
                        }
                        if (end) {
                            endBlock = 47;
                            endDay = 0;
                        }
                        ArrayList<Integer> freeSlot = makeSlot(startDay, startBlock, endDay, endBlock);
                        this.freeBlocks.add(freeSlot);
                    }
                }
            }
        }
        correctFirstAndLastSlotContinuation();
    }

    private void correctFirstAndLastSlotContinuation() {
        int size = this.freeBlocks.size();
        if (size > 1) {
            Boolean isSunday = this.freeBlocks.get(0).get(0).equals(this.freeBlocks.get(size - 1).get(2));
            Boolean isMidnight = this.freeBlocks.get(0).get(1).equals(0) && this.freeBlocks.get(size - 1).get(3).equals(47);
            Boolean isSundayMidnightOverlap = isSunday && isMidnight;
            if (isSundayMidnightOverlap) {
                Integer newStartDay = this.freeBlocks.get(size - 1).get(0);
                Integer newStartBlock = this.freeBlocks.get(size - 1).get(1);
                Integer newEndDay = this.freeBlocks.get(0).get(2);
                Integer newEndBlock = this.freeBlocks.get(0).get(3);
                ArrayList<Integer> newFreeSlot = makeSlot(newStartDay, newStartBlock, newEndDay, newEndBlock);
                this.freeBlocks.set(0, newFreeSlot);
                this.freeBlocks.remove(size - 1);
            }
        }
    }

    public void printFreeTimings() {
        System.out.println("Here are your free slots:");
        for (int i = 0; i < this.freeBlocks.size(); i++) {
            String startDay = getDayFromNumber(this.freeBlocks.get(i).get(0).intValue());
            LocalTime startTime = getTimeFromBlock(this.freeBlocks.get(i).get(1).intValue(), "START");
            String endDay = getDayFromNumber(this.freeBlocks.get(i).get(2).intValue());
            LocalTime endTime = getTimeFromBlock(this.freeBlocks.get(i).get(3).intValue(), "END");
            System.out.println(startDay + " " + startTime + " to " + endDay + " " + endTime);
        }
    }

    public ArrayList<ArrayList<Integer>> getFreeBlocks() {
        return this.freeBlocks;
    }

    public static Integer getBlocksFromStartTime(LocalTime startTime) throws MoException {
        int minuteBlocks = -1;
        int hourBlocks = -1;
        switch (startTime.getMinute()) {
        case 0:
            minuteBlocks = 0;
            break;
        case 30:
            minuteBlocks = 1;
            break;
        default:
            throw new MoException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
        }
        hourBlocks = startTime.getHour() * 2;
        return minuteBlocks + hourBlocks;
    }

    public static Integer getBlocksFromEndTime(LocalTime endTime) throws MoException {
        int minuteBlocks = -1;
        int hourBlocks = -1;
        switch (endTime.getMinute()) {
        case 0:
            minuteBlocks = 0;
            break;
        case 30:
            minuteBlocks = 1;
            break;
        default:
            throw new MoException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
        }
        hourBlocks = endTime.getHour() * 2;
        return minuteBlocks + hourBlocks - 1;
    }

    public static boolean isValidMeeting(Contact mainUser, Integer startDay,
                                         LocalTime startTime, Integer endDay, LocalTime endTime,
                                         int currentWeekNumber) throws MoException {
        if (!(startDay >= 0 && startDay <= 13) || !(endDay >= 0 && endDay <= 13)) {
            throw new MoException(MESSAGE_STARTENDDAY_OUT_OF_RANGE);
        }
        int startWeekNumber = currentWeekNumber;
        int endWeekNumber = currentWeekNumber;

        if (startDay > 6) {
            startDay -= 7;
            startWeekNumber++;
        }
        if (endDay > 6) {
            endDay -= 7;
            endWeekNumber++;
        }

        if ((startTime.getMinute() != 0 && startTime.getMinute() != 30) || (endTime.getMinute() != 0 && endTime.getMinute() != 30)) {
            throw new MoException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
        }

        Integer startBlock = getBlocksFromStartTime(startTime);
        Integer endBlock = -1;
        if (endTime == LocalTime.parse("00:00")) {
            endBlock = 47;
            if (endDay == 0) {
                endDay = 6;
            } else {
                endDay = endDay - 1;
            }
        } else {
            endBlock = getBlocksFromEndTime(endTime);
        }

        Boolean[][][] mainUserSchedule = mainUser.getSchedule();
        if (startDay.equals(endDay)) {
            if (startBlock.equals(endBlock)) {
                if (mainUserSchedule[startWeekNumber][startDay][startBlock] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            } else if (startBlock < endBlock) {
                for (int i = startBlock; i <= endBlock; ++i) {
                    if (mainUserSchedule[startWeekNumber][startDay][i] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            } else if (startBlock > endBlock) {
                throw new MoException(MESSAGE_INVALID_MEETING_RANGE);
            }
        }

        if (startDay < endDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber][startDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }
            for (int i = startDay + 1; i <= endDay - 1; ++i) {
                for (int j = 0; j < 48; ++j) {
                    if (mainUserSchedule[startWeekNumber][i][j] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber][endDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }

        }

        if (endDay < startDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber][startDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }

            for (int i = startDay + 1; i <= 6; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[startWeekNumber][i][j] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            }

            for (int i = 0; i <= endDay - 1; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[endWeekNumber][i][j] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber][endDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }
        }
        return true;
    }

}