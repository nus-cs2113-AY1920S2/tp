package ui;

import common.exception.WfException;
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
        String logo = " __      __.__                  ___________                      \n"
            + "/  \\    /  \\  |__   ____   ____ \\_   _____/______   ____   ____  \n"
            + "\\   \\/\\/   /  |  \\_/ __ \\ /    \\ |    __) \\_  __ \\_/ __ \\_/ __ \\ \n"
            + " \\        /|   Y  \\  ___/|   |  \\|     \\   |  | \\/\\  ___/\\  ___/ \n"
            + "  \\__/\\  / |___|  /\\___  >___|  /\\___  /   |__|    \\___  >\\___  >\n"
            + "       \\/       \\/     \\/     \\/     \\/                \\/     \\/ ";

        out.println(logo);
    }



    public static void menuMsg(int memberList) {
        out.println("__________________________________________________________"
                + "______________________________________________________________________");
        out.println("[contacts] List all contacts.");
        out.println("[timetable] Display combined timetable of selected contacts.");
        out.println("[schedule] Schedule a new meeting.");
        out.println("[edit] Edit a contact's timetable.");
        out.println("[delete] Delete a scheduled meeting.");
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
        out.println("Thank you for using WhenFree, goodbye!");
    }

    public static void errorMsg(WfException e) {
        out.println("OOPS!! " + e.getMessage());
    }


    public static void printTimetable(Boolean[][][] mySchedule, int weeksMoreToView, int weekNumber) {
        out.print("Date:  ");
        String[] tempDate = java.util.Calendar.getInstance().getTime().toString().split(" ");
        String dayString = tempDate[0];

        //hardcoded for PE testing purposes
        dayString = "Mon";

        Calendar cal = Calendar.getInstance();

        //hardcoded for PE testing purposes
        cal.set(Calendar.DATE, 6);
        cal.set(Calendar.MONTH, 4);

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
            } else if (lastChar.equals("2")
                    && !dateArray[2].substring(dateArray[2].length() - 2, dateArray[2].length() - 1).equals("1")) {
                datePostFix = "nd";
            } else if (lastChar.equals("3")
                    && !dateArray[2].substring(dateArray[2].length() - 2, dateArray[2].length() - 1).equals("1")) {
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

        int earliestScheduledTime = getEarliestTime(mySchedule, weeksMoreToView, weekNumber, 47) / 2;
        int latestScheduledTime = getLatestTime(mySchedule, weeksMoreToView, weekNumber, 0) / 2;

        int counter = 0;
        for (int i = earliestScheduledTime; i < latestScheduledTime + 1; ++i) {
            out.print(String.format("%04d", (100 * i)) + " +-----+-----+-----+-----+-----+-----+-----+");
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
            counter = i;
        }
        counter++;
        out.print(String.format("%04d", (100 * counter)) + " +-----+-----+-----+-----+-----+-----+-----+");
        if (weeksMoreToView == 1) {
            out.print(" +-----+-----+-----+-----+-----+-----+-----+");
        }
        out.println();
        if (weeksMoreToView == 0) {
            out.println("[NOTE] You can type \"more\" for next week's timetable as well!");
        }
    }

    private static int getLatestTime(Boolean[][][] mySchedule, int weeksMoreToView, int weekNumber, int latestScheduledTime) {
        for (int z = 0; z <= weeksMoreToView; z++) {
            for (int j = 0; j < 7; ++j) {
                for (int i = 0; i < 48; ++i) {
                    if (mySchedule[weekNumber + z - 1][j][i] && i > latestScheduledTime) {
                        latestScheduledTime = i;
                    }
                }
            }
        }
        return (latestScheduledTime % 2 == 0) ? latestScheduledTime : latestScheduledTime - 1;
    }

    private static int getEarliestTime(Boolean[][][] mySchedule, int weeksMoreToView, int weekNumber, int earliestScheduledTime) {
        for (int z = 0; z <= weeksMoreToView; z++) {
            for (int j = 0; j < 7; ++j) {
                for (int i = 0; i < 48; ++i) {
                    if (mySchedule[weekNumber + z - 1][j][i] && i < earliestScheduledTime) {
                        earliestScheduledTime = i;
                        break;
                    }
                    if (i > earliestScheduledTime) {
                        break;
                    }
                }
            }
        }
        return earliestScheduledTime;
    }

    public static void scheduleMeetingMsg() {
        out.println("You have selected new scheduled meeting");
    }


    public static void meetingDetailsMsg() {
        out.println("Enter meeting details: <Meeting.Meeting Name> <Start Day> <Start Time> <End Day> <End Time>."
                + "Type \"exit\" to go back to menu.");
    }

    public static void meetingListSizeMsg(MeetingList myMeetingList) {
        out.println("You now have " + myMeetingList.getMeetingListSize() + " meeting(s) in the list.");
    }

    public static void listMeetings() {
        out.println("Here are all your meeting slots.");
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
        System.out.println("The current scheduled meeting(s):");
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
        String startDate = Integer.toString(meetingList.get(index).getStartDate());
        String endDate = Integer.toString(meetingList.get(index).getEndDate());

        System.out.println((index + 1) + ". " + startDay + "(" + startDate + getPostFix(startDate) + ") "
                + meetingList.get(index).getStartTime() + " to " + endDay + "(" + endDate + getPostFix(endDate) + ") "
                + meetingList.get(index).getEndTime() + " (" + meetingList.get(index).getMeetingName() + ")");
    }

    public static void displayInvalidDeleteTarget() {
        System.out.println("Meeting does not exist for indicated index.");
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
        out.println("\nTo schedule a meeting:\nschedule <Meeting Name> <Start Date> <Start Time> <End Date> <End Time>");
    }

    public static void printFormatEdit() {
        out.println("\nTo edit a contact:\nedit busy <Contact Index> <Start Date> <Start Time> <End Date> <End Time>"
                + "\nedit free <Contact Index> <Start Day> <Start Time> <End Day> <End Time>");
    }

    public static void printFormatDeleteMember() {
        out.println("\nTo delete contact:\ndelete <Member Name>");
    }

    public static void printFormatMeeting() {
        out.println("\nTo list meetings:\nmeetings");
    }

    public static void displayRemovedPerson(String name) {
        out.println("I have removed " + name + " from your contact list.");
    }

    public static void displayMainUserDeleteError(String name) {
        out.println("Main user " + name + " cannot be deleted.");
    }

    public static void displayNoMemberFound(String name) {
        out.println(name + " is not found in list of contacts.");
    }

    public static void printFormatDeleteMeeting() {
        out.println("\nTo delete meeting:\ndelete <Meeting Index>");
    }

    public static void showContactEdited(String name, String busyOrFree) {
        out.println(name + "'s timetable has been edited.");
    }

}


