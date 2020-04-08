package logic.schedulelogic;

import common.exception.MoException;
import model.contact.Contact;

import java.time.LocalTime;
import java.util.ArrayList;

import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_INVALID_MEETING;
import static common.Messages.MESSAGE_INVALID_EDIT;

/**
 * TESTING SUMMARY DOC.
 */

public class ScheduleHandler {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;

    private static Boolean[][][] masterSchedule = new Boolean[13][7][48];

    public ScheduleHandler(ArrayList<Contact> contactList) {
        assert contactList.size() <= 1 : "Only 1 member in contact list passed to ScheduleHandler";
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    masterSchedule[i][j][k] = MYSCHEDULEFREE; // fill every index with 0 initially
                }
            }
        }
        for (Contact t : contactList) {
            Boolean[][][] memberSchedule = t.getSchedule();
            fillMasterSchedule(memberSchedule);
        }
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

    public static String getDayFromNumber(int dayNum) {
        String day;m
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
            throw new AssertionError(dayNum);
        }
        return day;
    }

    public Boolean[][][] getMasterSchedule() {
        return masterSchedule;
    }



    public static boolean isValidEdit(Contact mainUser, Integer startDay,
                                      LocalTime startTime, Integer endDay, LocalTime endTime,
                                      int currentWeekNumber) throws MoException {

        Slot slot = new Slot(startDay, startTime, endDay, endTime, currentWeekNumber).invoke();
        startDay = slot.getStartDay();
        endDay = slot.getEndDay();
        int startWeekNumber = slot.getStartWeekNumber();
        int endWeekNumber = slot.getEndWeekNumber();
        Integer startBlock = slot.getStartBlock();
        Integer endBlock = slot.getEndBlock();

        String[][][] mainUserSchedule = mainUser.getMyScheduleName();

        isValidLogic(startDay, endDay, startWeekNumber, endWeekNumber, startBlock, endBlock, mainUserSchedule,
                "meeting", MESSAGE_INVALID_EDIT);
        return true;
    }

    public static boolean isValidMeeting(Contact mainUser, Integer startDay,
                                         LocalTime startTime, Integer endDay, LocalTime endTime,
                                         int currentWeekNumber) throws MoException {
        Slot slot = new Slot(startDay, startTime, endDay, endTime, currentWeekNumber).invoke();
        startDay = slot.getStartDay();
        endDay = slot.getEndDay();
        int startWeekNumber = slot.getStartWeekNumber();
        int endWeekNumber = slot.getEndWeekNumber();
        Integer startBlock = slot.getStartBlock();
        Integer endBlock = slot.getEndBlock();

        Boolean[][][] mainUserSchedule = mainUser.getSchedule();
        isValidLogic(startDay, endDay, startWeekNumber, endWeekNumber, startBlock, endBlock, mainUserSchedule,
                MYSCHEDULEBLOCKED, MESSAGE_INVALID_MEETING);
        return true;
    }

    private static <T> void isValidLogic(Integer startDay, Integer endDay, int startWeekNumber, int endWeekNumber,
                                         Integer startBlock, Integer endBlock, T[][][] mainUserSchedule, T invalidBlock,
                                         String invalidBlockMessage) throws MoException {
        if (startDay.equals(endDay)) {
            if (startBlock.equals(endBlock)) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][startBlock].equals(invalidBlock)) {
                    throw new MoException(invalidBlockMessage);
                }
            } else if (startBlock < endBlock) {
                for (int i = startBlock; i <= endBlock; ++i) {
                    if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(invalidBlock)) {
                        throw new MoException(invalidBlockMessage);
                    }
                }
            } else if (startBlock > endBlock) {
                throw new MoException(invalidBlockMessage);
            }
        }

        if (startDay < endDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(invalidBlock)) {
                    throw new MoException(invalidBlockMessage);
                }
            }
            for (int i = startDay + 1; i <= endDay - 1; ++i) {
                for (int j = 0; j < 48; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j].equals(invalidBlock)) {
                        throw new MoException(invalidBlockMessage);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i].equals(invalidBlock)) {
                    throw new MoException(invalidBlockMessage);
                }
            }

        }

        if (endDay < startDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(invalidBlock)) {
                    throw new MoException(invalidBlockMessage);
                }
            }

            for (int i = startDay + 1; i <= 6; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j].equals(invalidBlock)) {
                        throw new MoException(invalidBlockMessage);
                    }
                }
            }

            for (int i = 0; i <= endDay - 1; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[endWeekNumber - 1][i][j].equals(invalidBlock)) {
                        throw new MoException(invalidBlockMessage);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i].equals(invalidBlock)) {
                    throw new MoException(invalidBlockMessage);
                }
            }
        }
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

        public Slot invoke() throws MoException {
            if (!(startDay >= 0 && startDay <= 13) || !(endDay >= 0 && endDay <= 13)) {
                throw new MoException(MESSAGE_STARTENDDAY_OUT_OF_RANGE);
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
                throw new MoException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
            }

            startBlock = getBlocksFromStartTime(startTime);
            endBlock = -1;
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
            return this;
        }
    }
}