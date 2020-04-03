package logic.schedulelogic;


import exception.MoException;
import model.contact.Contact;

import java.time.LocalTime;
import java.util.ArrayList;

import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;


/**
 * TESTING SUMMARY DOC.
 */

public class ScheduleHandler {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;
    private static Boolean[][][] masterSchedule = new Boolean[13][7][48];
    // ArrayList of free slots in Integer type {startDay, startBlock, endDay, endBlock}
    private static ArrayList<ArrayList<Integer>> freeBlocks = new ArrayList<>();


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
        updateFreeBlocks();
    }

    public static ArrayList<Integer> makeSlot(int startDay, int startBlock, int endDay, int endBlock, int startWeek, int endWeek) {
        ArrayList<Integer> freeSlot = new ArrayList<Integer>();
        freeSlot.add(startDay);
        freeSlot.add(startBlock);
        freeSlot.add(endDay);
        freeSlot.add(endBlock);
        freeSlot.add(startWeek);
        freeSlot.add(endWeek);
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
                        final int startWeek = k;
                        while (masterSchedule[k][i][j] == MYSCHEDULEFREE) {
                            if (change) {
                                change = false;
                            }
                            if (i == 6 && j == 47 && k == 12) {
                                end = true;
                                break;
                            }
                            if (j == 47) {
                                change = true;
                                j = 0;
                                i++;
                            }
                            j++;
                            if (i == 6) {
                                i = 0;
                            }
                        }
                        int endWeek = k;
                        int endDay = i;
                        int endBlock = j - 1;
                        if (change) {
                            endBlock = 47;
                        }
                        if (end) {
                            endBlock = 47;
                            endDay = 0;
                            endWeek = 13;
                        }
                        ArrayList<Integer> freeSlot = makeSlot(startDay, startBlock, endDay, endBlock, startWeek, endWeek);
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
                Integer startWeek = this.freeBlocks.get(0).get(4);
                Integer endWeek = this.freeBlocks.get(0).get(5);
                ArrayList<Integer> newFreeSlot = makeSlot(newStartDay, newStartBlock, newEndDay, newEndBlock, startWeek, endWeek);
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


}