import static common.Messages.MESSAGE_INVALID_NUMBER;
import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static java.lang.System.out;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * TESTING SUMMARY DOC.
 */

public class TextUI {
    public static void introMsg() {
        String logo = "  ___        _____                 __  .__                 ________                            "
            + ".__                       ___     \n"
            + " / _ \\_/\\   /     \\   ____   _____/  |_|__| ____    ____   \\_____  \\_______  _________    ___"
            + "_ |__|_______ ___________  / _ \\_/\\ \n"
            + " \\/ \\___/  /  \\ /  \\_/ __ \\_/ __ \\   __\\  |/    \\  / ___\\   /   |   \\"
            + "_  __ \\/ ___\\__  \\  /    \\|  \\___   // __ \\_  __ \\ \\/ \\___/ \n"
            + "          /    Y    \\  ___/\\  ___/|  | |  |   |  \\/ /_/  > /    |    \\  "
            + "| \\/ /_/  > __ \\|   |  \\  |/    /\\  ___/|  | \\/          \n"
            + "          \\____|__  /\\___  >\\___  >__| |__|___|  /\\___  /  \\_______  /__ "
            + "|  \\___  (____  /___|  /__/_____ \\\\___  >__|             \n"
            + "                  \\/     \\/     \\/             \\//_____/           \\/     "
            + "/_____/     \\/     \\/         \\/    \\/                 ";
        out.println("Welcome to....\n" + logo);
    }

    public static void menuMsg() {
        out.println("__________________________________________________________"
                + "______________________________________________________________________");
        out.println("1) Schedule a new meeting.");
        out.println("2) Edit a scheduled meeting."); //previous was delete
        out.println("3) Delete a scheduled meeting.");
        out.println("4) List all scheduled meetings.");
        out.println("5) Exit application.");
        out.println("__________________________________________________________"
                + "______________________________________________________________________");
    }

    public static void exitMsg() {
        out.println("Thank you for using MeetingOrganizer, goodbye!");
    }

    public static void errorMsg(MoException e) {
        out.println("OOPS!! " + e.getMessage());
    }


    public static void printTimetable(Boolean[][] mySchedule) {
        out.println("Here are your common free slots:\n");
        out.println("      SUN MON TUE WED THU FRI SAT");

        for (int i = 0; i < 24; ++i) {
            out.println(String.format("%04d", (0000 + 100 * i)) + " +---+---+---+---+---+---+---+");

            out.print("     |");
            for (int j = 0; j < 7; ++j) {
                out.print(" " + (mySchedule[j][2 * i] ? "X" : " ") + " |");
            }
            out.println();
            out.println("     +---+---+---+---+---+---+---+");

            out.print("     |");
            for (int j = 0; j < 7; ++j) {
                out.print(" " + (mySchedule[j][2 * i + 1] ? "X" : " ") + " |");
            }
            out.println();
        }
        out.println("0000" + " +---+---+---+---+---+---+---+");
    }

    public static void scheduleMeetingMsg() {
        out.println("You have selected new scheduled meeting");
    }

    public static void meetingNameMsg() {
        out.println("What do you want to name your meeting?");
    }

    public static void meetingDetailsMsg() {
        out.println("Enter meeting details: <Start Day> <Start Time> <End Day> <End Time>.\nType \"exit\" to exit setting a meeting");
    }

    public static void meetingListSizeMsg(MeetingList myMeetingList) {
        out.println("You now have " + myMeetingList.getMeetingListSize() + " meeting/s in the list.");
    }

    public static void deleteMeetingMsg() {
        out.println("Which meeting slot do you want to delete?");
    }

    public static void editMeetingMsg() {
        out.println("Which meeting slot do you want to edit?");
    }

    public static void listMeetings() {
        out.println("Here are all your meeting slots.");
    }

    public static void membersMsg() {
        out.println("How many members are there in your team?");
    }

    public static void enterScheduleMsg(String memberName) {
        out.println("Enter schedule of member " + memberName + ": <Schedule Name> <Start Day> <Start Time> <End Day> <End Time>");
    }

    public static void timeOutOfRangeMsg() {
        out.println(MESSAGE_STARTENDTIME_OUT_OF_RANGE);
    }

    public static void invalidNumberMsg() {
        out.println(MESSAGE_INVALID_NUMBER);
    }

    public static void listAllScheduledMeetings(ArrayList<Meeting> meetingList) {
        System.out.println("The current scheduled meeting(s):");
        for (int i = 0; i < meetingList.size(); i++) {
            String startDay = getDayFromNumber(meetingList.get(i).getStartDay());
            String endDay = getDayFromNumber(meetingList.get(i).getEndDay());
            System.out.println((i + 1) + ". " + startDay + " " + meetingList.get(i).getStartTime()
                    + " to " + endDay + " " + meetingList.get(i).getEndTime());
        }
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

    public static void displayNoMeetings() {
        System.out.println("There is no scheduled meetings so far.");
    }

    public static void displayRemovedMeeting(ArrayList<Meeting> meetingList, int index) throws IndexOutOfBoundsException {
        String startDay = getDayFromNumber(meetingList.get(index).getStartDay());
        String endDay = getDayFromNumber(meetingList.get(index).getEndDay());
        System.out.println("I have removed:");
        System.out.println((index + 1) + ". " + startDay + " " + meetingList.get(index).getStartTime()
                + " to " + endDay + " " + meetingList.get(index).getEndTime());
    }

    public static void displayInvalidDeleteTarget() {
        System.out.println("Item does not exist.");
    }

    public static void showLoadingError() {
        System.out.println("There are no previous records of meetings, let's create a new one!");
    }
}

