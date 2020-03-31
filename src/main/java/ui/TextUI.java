package ui;

import exception.MoException;
import model.meeting.Meeting;
import model.meeting.MeetingList;
import model.contact.Contact;

import java.util.ArrayList;
import java.util.Calendar;

import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_INDEX_OUT_OF_BOUNDS;
import static common.Messages.MESSAGE_INVALID_NUMBER;
import static java.lang.System.out;

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
        out.println(logo);
    }

    public static void menuMsg(int memberList) {
        out.println("__________________________________________________________"
                + "______________________________________________________________________");
        out.println("[contacts] List all contacts.");
        out.println("[timetable] Display combined timetable of selected contacts.");
        out.println("[schedule] Schedule a new model.meeting.");
        out.println("[delete] Delete a scheduled model.meeting.");
        out.println("[meetings] List all scheduled meetings.");
        out.println("[exit] Exit application.");
        out.print(System.lineSeparator());
        if (memberList > 0) {
            out.println("Insert your member's timetable by following: <name of new member> <nusmods link>");
        } else {
            out.println("Insert your own timetable by following: <name of new member> <nusmods link>");
        }
        out.println("__________________________________________________________"
                + "______________________________________________________________________");
    }

    public static void exitMsg() {
        out.println("Thank you for using MeetingOrganizer, goodbye!");
    }

    public static void errorMsg(MoException e) {
        out.println("OOPS!! " + e.getMessage());
    }


    public static void printTimetable(Boolean[][][] mySchedule, int weeksMoreToView, int weekNumber) {
        out.print("Date:  ");
        String[] tempDate = java.util.Calendar.getInstance().getTime().toString().split(" ");
        String dayString = tempDate[0];
        int day = 0;
        Calendar cal = Calendar.getInstance();
        switch (dayString) {
        case "Sun":
            cal.add(Calendar.DATE, -1);
            break;
        case "Mon":
            cal.add(Calendar.DATE, -2);
            break;
        case "Tue":
            cal.add(Calendar.DATE, -3);
            break;
        case "Wed":
            cal.add(Calendar.DATE, -4);
            break;
        case "Thu":
            cal.add(Calendar.DATE, -5);
            break;
        case "Fri":
            cal.add(Calendar.DATE, -6);
            break;
        case "Sat":
            cal.add(Calendar.DATE, -7);
            break;
        default:
            cal.add(Calendar.DATE, -1);
            break;
        }
        int date = Integer.parseInt(tempDate[2]);
        int weekViewLimit;
        if (weeksMoreToView == 1) {
            weekViewLimit = 14;
        } else {
            weekViewLimit = 7;
        }
        for (int i = 0; i < weekViewLimit; i++) {
            cal.add(Calendar.DATE, 1);
            String[] dateArray = cal.getTime().toString().split(" ");
            int datePrint = Integer.parseInt(dateArray[2]);
            String lastChar = dateArray[2].substring(dateArray[2].length() - 1);
            String datePostFix;
            if (lastChar.equals("1") && !dateArray[2].substring(dateArray[2].length() - 2, dateArray[2].length() - 1).equals("1")) {
                datePostFix = "st";
            } else if (lastChar.equals("2")) {
                datePostFix = "nd";
            } else if (lastChar.equals("3")) {
                datePostFix = "rd";
            } else {
                datePostFix = "th";
            }
            String output = datePrint + datePostFix;
            System.out.print((output.length() == 3) ? output + "   " : output + "  ");
            if (i == 6 && weeksMoreToView == 1) {
                out.print("  ");
            }
        }
        out.println();

        out.print("       SUN   MON   TUE   WED   THU   FRI   SAT  ");
        if (weeksMoreToView == 1) {
            out.print("   SUN   MON   TUE   WED   THU   FRI   SAT");
        }
        out.println();

        for (int i = 0; i < 24; ++i) {
            out.print(String.format("%04d", (0000 + 100 * i)) + " +-----+-----+-----+-----+-----+-----+-----+");
            if (weeksMoreToView == 1) {
                out.print(" +-----+-----+-----+-----+-----+-----+-----+");
            }
            out.println();

            out.print("     |");
            for (int z = 0; z <= weeksMoreToView; z++) {
                for (int j = 0; j < 7; ++j) {
                    out.print("  " + (mySchedule[weekNumber + z - 1][j][2 * i] ? "X" : " ") + "  |");
                }
                if (z < weeksMoreToView) {
                    out.print(" |");
                }
            }
            out.println();

            out.print("     +-----+-----+-----+-----+-----+-----+-----+");
            if (weeksMoreToView == 1) {
                out.print(" +-----+-----+-----+-----+-----+-----+-----+");
            }
            out.println();

            out.print("     |");
            for (int z = 0; z <= weeksMoreToView; z++) {
                for (int j = 0; j < 7; ++j) {
                    out.print("  " + (mySchedule[weekNumber + z - 1][j][2 * i + 1] ? "X" : " ") + "  |");
                }


                if (z < weeksMoreToView) {
                    out.print(" |");
                }
            }
            out.println();
        }
        out.print("0000" + " +-----+-----+-----+-----+-----+-----+-----+");
        if (weeksMoreToView == 1) {
            out.print(" +-----+-----+-----+-----+-----+-----+-----+");
        }
        out.println();
    }

    public static void scheduleMeetingMsg() {
        out.println("You have selected new scheduled model.meeting");
    }


    public static void meetingDetailsMsg() {
        out.println("Enter model.meeting details: <Meeting.Meeting Name> <Start Day> <Start Time> <End Day> <End Time>."
                + "Type \"exit\" to go back to menu.");
    }

    public static void meetingListSizeMsg(MeetingList myMeetingList) {
        out.println("You now have " + myMeetingList.getMeetingListSize() + " model/meeting(s) in the list.");
    }

    public static void listMeetings() {
        out.println("Here are all your model.meeting slots.");
    }

    public static void timeOutOfRangeMsg() {
        out.println(MESSAGE_STARTENDTIME_OUT_OF_RANGE);
    }

    public static void invalidNumberMsg() {
        out.println(MESSAGE_INVALID_NUMBER);
    }

    public static void indexOutOfBoundsMsg() {
        out.println(MESSAGE_INDEX_OUT_OF_BOUNDS);
    }

    public static void listAllScheduledMeetings(ArrayList<Meeting> meetingList) {
        System.out.println("The current scheduled model.meeting(s):");
        for (int i = 0; i < meetingList.size(); i++) {
            String startDay = getDayFromNumber(meetingList.get(i).getStartDay());
            String endDay = getDayFromNumber(meetingList.get(i).getEndDay());
            String meetingName = meetingList.get(i).getMeetingName();

            String startDate = Integer.toString(meetingList.get(i).getStartDate());
            String endDate = Integer.toString(meetingList.get(i).getEndDate());

            System.out.println((i + 1) + ". " + startDay + "(" + startDate + getPostFix(startDate) + ") "
                    + meetingList.get(i).getStartTime() + " to " + endDay + "(" + endDate + getPostFix(endDate) + ") "
                    + meetingList.get(i).getEndTime() + " (" + meetingName + ")");
        }
    }

    private static String getPostFix(String date) {
        String lastChar = date.substring(date.length() - 1);
        String datePostFix;
        if (lastChar.equals("1") && !date.substring(date.length() - 2, date.length() - 1).equals("1")) {
            datePostFix = "st";
        } else if (lastChar.equals("2")) {
            datePostFix = "nd";
        } else if (lastChar.equals("3")) {
            datePostFix = "rd";
        } else {
            datePostFix = "th";
        }
        return datePostFix;
    }

    public static String getDayFromNumber(int dayNum) {
        String day;
        if (dayNum > 6) {
            dayNum %= 7;
        }
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
            day = "Out of range";
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
                + " to " + endDay + " " + meetingList.get(index).getEndTime() + " " + meetingList.get(index).getMeetingName());
    }

    public static void displayInvalidDeleteTarget() {
        System.out.println("Item does not exist.");
    }

    public static void showLoadingError() {
        System.out.println("There are no previous records, let's create a new one!");
    }


    public static void teamMemberListMsg(ArrayList<Contact> contactList) {
        System.out.println("____________________________________________________________\n"
                + "Here are your stored contacts:");
        int i = 0;
        for (Contact contact : contactList) {
            out.print("\t " + i + ") " + contact.getName());
            if (i == 0) {
                System.out.println(" (main user)");
            } else {
                System.out.println();
            }
            i++;
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void showAddedMember(String memberName) {
        out.println("Added " + memberName);
    }

    public static void showRepeatedPerson(String userInputWord) {
        out.println(userInputWord + " already exists!");
    }

    public static void printFormatTimetable() {
        out.println("\nTo display timetable:\ntimetable\ntimetable <Member Number 1>"
                + "\ntimetable <Member Number 1> <Member Number 2>");
    }

    public static void printFormatSchedule() {
        out.println("\nTo schedule a model.meeting:\nschedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>");
    }

    public static void printFormatDelete() {
        out.println("\nTo delete contact:\ndelete <Member Number>");
    }

    public static void printFormatMeeting() {
        out.println("\nTo list meetings:\nmodel.meeting");
    }
}


