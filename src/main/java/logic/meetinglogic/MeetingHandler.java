package logic.meetinglogic;

import common.Messages;
import exception.MoException;
import model.contact.Contact;

import java.time.LocalTime;

import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;
import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_INVALID_MEETING;
import static common.Messages.MESSAGE_INVALID_EDIT;
import static common.Messages.MESSAGE_INVALID_MEETING_RANGE;

public class MeetingHandler {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final String MEETING = "meeting";

    public static boolean isValidEdit(Contact mainUser, Integer startDay,
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

        String[][][] mainUserSchedule = mainUser.getMyScheduleName();
        if (startDay.equals(endDay)) {
            if (startBlock.equals(endBlock)) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][startBlock].equals(MEETING)) {
                    throw new MoException(MESSAGE_INVALID_EDIT);
                }
            } else if (startBlock < endBlock) {
                for (int i = startBlock; i <= endBlock; ++i) {
                    if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(MEETING)) {
                        throw new MoException(MESSAGE_INVALID_EDIT);
                    }
                }
            } else if (startBlock > endBlock) {
                throw new MoException(MESSAGE_INVALID_MEETING_RANGE);
            }
        }

        if (startDay < endDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(MEETING)) {
                    throw new MoException(MESSAGE_INVALID_EDIT);
                }
            }
            for (int i = startDay + 1; i <= endDay - 1; ++i) {
                for (int j = 0; j < 48; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j].equals(MEETING)) {
                        throw new MoException(MESSAGE_INVALID_EDIT);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i].equals(MEETING)) {
                    throw new MoException(MESSAGE_INVALID_EDIT);
                }
            }

        }

        if (endDay < startDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i].equals(MEETING)) {
                    throw new MoException(MESSAGE_INVALID_EDIT);
                }
            }

            for (int i = startDay + 1; i <= 6; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j].equals(MEETING)) {
                        throw new MoException(MESSAGE_INVALID_EDIT);
                    }
                }
            }

            for (int i = 0; i <= endDay - 1; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[endWeekNumber - 1][i][j].equals(MEETING)) {
                        throw new MoException(MESSAGE_INVALID_EDIT);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i].equals(MEETING)) {
                    throw new MoException(MESSAGE_INVALID_EDIT);
                }
            }
        }
        return true;
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
                if (mainUserSchedule[startWeekNumber - 1][startDay][startBlock] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            } else if (startBlock < endBlock) {
                for (int i = startBlock; i <= endBlock; ++i) {
                    if (mainUserSchedule[startWeekNumber - 1][startDay][i] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            } else if (startBlock > endBlock) {
                throw new MoException(MESSAGE_INVALID_MEETING_RANGE);
            }
        }

        if (startDay < endDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }
            for (int i = startDay + 1; i <= endDay - 1; ++i) {
                for (int j = 0; j < 48; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }

        }

        if (endDay < startDay) {
            for (int i = startBlock; i <= 47; ++i) {
                if (mainUserSchedule[startWeekNumber - 1][startDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }

            for (int i = startDay + 1; i <= 6; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[startWeekNumber - 1][i][j] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            }

            for (int i = 0; i <= endDay - 1; ++i) {
                for (int j = 0; j <= 47; ++j) {
                    if (mainUserSchedule[endWeekNumber - 1][i][j] == MYSCHEDULEBLOCKED) {
                        throw new MoException(MESSAGE_INVALID_MEETING);
                    }
                }
            }

            for (int i = 0; i <= endBlock; ++i) {
                if (mainUserSchedule[endWeekNumber - 1][endDay][i] == MYSCHEDULEBLOCKED) {
                    throw new MoException(MESSAGE_INVALID_MEETING);
                }
            }
        }
        return true;
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
}