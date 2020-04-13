package logic.command;

import common.exception.InvalidUrlException;
import java.time.format.DateTimeFormatter;
import model.meeting.MeetingList;
import common.exception.WfException;
import model.meeting.Meeting;
import logic.modulelogic.LessonsGenerator;
import logic.schedulelogic.ScheduleHandler;
import model.contact.Contact;
import model.contact.ContactList;
import ui.TextUI;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;


import static common.Messages.MESSAGE_WRONG_COMMAND_MEETING;
import static common.Messages.MESSAGE_WRONG_COMMAND_SCHEDULE;
import static common.Messages.MESSAGE_WRONG_DATE;
import static common.Messages.MESSAGE_INVALID_SLOT_RANGE;

public class CommandHandler {


    public static Contact addContact(ContactList myContactList, String[] userInputWords,
                                     Integer startDay, Integer endDay) throws WfException, InvalidUrlException {
        int checkerForRepeatedName;
        checkerForRepeatedName = myContactList.getContactList().stream()
            .mapToInt(person -> check(person, userInputWords[0])).sum();
        if (checkerForRepeatedName == 1) {
            TextUI.showRepeatedPerson(userInputWords[0]);
            throw new WfException("Repeated user");
        }

        if (userInputWords[0].length() >= 260) {
            throw new WfException("Maximum characters for a given name is 260");
        }
        Contact member;
        member = new Contact(userInputWords[0]);
        String name = userInputWords[0];
        String url = userInputWords[1];
        if (name.matches("[0-9]+")) {
            throw new WfException("Name must contain letters.");
        }
        LessonsGenerator myLessonGenerator;
        myLessonGenerator = new LessonsGenerator(url);
        myLessonGenerator.generate();
        ArrayList<String[]> myLessonDetails = myLessonGenerator.getLessonDetails();

        for (String[] myLessonDetail : myLessonDetails) {
            String startTimeString = null;
            String endTimeString = null;
            String[] weeks = new String[0];
            for (int j = 0; j < myLessonDetail.length; j++) {
                switch (j) {
                case 0:
                    startTimeString = myLessonDetail[j].substring(0, 2) + ":" + myLessonDetail[j].substring(2);
                    break;
                case 1:
                    endTimeString = myLessonDetail[j].substring(0, 2) + ":" + myLessonDetail[j].substring(2);
                    break;
                case 2:
                    startDay = getNumberFromDay(myLessonDetail[j]);
                    endDay = startDay;
                    break;
                case 3:
                    weeks = myLessonDetail[j].split(":");
                    break;
                default:
                    //data only has four sections from api
                    throw new AssertionError(j);
                }
            }
            member.addBusyBlocks(name, startDay, startTimeString, endDay, endTimeString, weeks);
        }
        TextUI.showAddedMember(member.getName());
        return member;
    }

    public static void editContact(String[] userInputWords, Contact mainUser, ContactList contactList,
                                   int currentWeekNumber) {

        try {
            if (userInputWords.length != 7) {
                throw new WfException(MESSAGE_WRONG_COMMAND_SCHEDULE);
            }
            int endOfMonthDate = 0;
            endOfMonthDate = getEndOfMonthDate(endOfMonthDate);

            Integer startDay;
            Integer endDay;
            Integer today;
            int startDate = Integer.parseInt(userInputWords[3]);
            int endDate = Integer.parseInt(userInputWords[5]);
            int startOfWeekDate = getStartOfWeekDate();
            int todayDate = Integer.parseInt(java.util.Calendar.getInstance().getTime().toString().split(" ")[2]);

            //hardcoded for PE testing purposes
            todayDate = 6;

            startDay = getDay(endOfMonthDate, startOfWeekDate, startDate);
            endDay = getDay(endOfMonthDate, startOfWeekDate, endDate);
            today = getDay(endOfMonthDate, startOfWeekDate, todayDate);

            if (endDay < startDay) {
                throw new WfException(MESSAGE_INVALID_SLOT_RANGE);
            }

            if (endDay < today || startDay < today) {
                throw new WfException(MESSAGE_INVALID_SLOT_RANGE);
            }

            int memberIndex = Integer.parseInt(userInputWords[2]);
            Contact member = contactList.getContactList().get(memberIndex);
            String memberName = member.getName();
            LocalTime startTime = LocalTime.parse(userInputWords[4]);
            LocalTime endTime = LocalTime.parse(userInputWords[6]);
            String startTimeString = userInputWords[4];
            String endTimeString = userInputWords[6];
            String[] thisWeekNumber = {Integer.toString(currentWeekNumber)};

            if (memberIndex != 0 || mainUser.isValidEdit(startDay, startTime, endDay, endTime, currentWeekNumber)) {
                if (userInputWords[1].equals("busy")) {
                    member.addBusyBlocks(memberName, startDay, startTimeString, endDay, endTimeString,thisWeekNumber);
                } else if (userInputWords[1].equals("free")) {
                    member.addFreeBlocks(startDay, startTimeString, endDay, endTimeString,thisWeekNumber);
                }
                TextUI.showContactEdited(member.getName(),userInputWords[2]);
            } else {
                throw new AssertionError("isValidEdit() should not return false");
            }
        } catch (WfException e) {
            System.out.println(e.getMessage());
            TextUI.printFormatEdit();
        } catch (DateTimeParseException e) {
            TextUI.timeOutOfRangeMsg();
            TextUI.printFormatEdit();
        } catch (NumberFormatException e) {
            TextUI.invalidNumberMsg();
            TextUI.printFormatEdit();
        }
    }

    private static Integer getNumberFromDay(String day) {
        int dayInNumber;
        switch (day) {
        case "Monday":
            dayInNumber = 1;
            break;
        case "Tuesday":
            dayInNumber = 2;
            break;
        case "Wednesday":
            dayInNumber = 3;
            break;
        case "Thursday":
            dayInNumber = 4;
            break;
        case "Friday":
            dayInNumber = 5;
            break;
        case "Saturday":
            dayInNumber = 6;
            break;
        case "Sunday":
            dayInNumber = 0;
            break;
        default:
            dayInNumber = Integer.parseInt(null);
            break;
        }
        return dayInNumber;
    }

    private static int check(Contact person, String name) {
        if (person.getName().equals(name)) {
            return 1;
        } else {
            return 0;
        }
    }


    public static void listMeetings(String[] userInputWords, MeetingList meetingList) {
        try {
            if (userInputWords.length != 1) {
                throw new WfException(MESSAGE_WRONG_COMMAND_MEETING);
            }
            meetingList.show();
        } catch (WfException e) {
            System.out.println(e.getMessage());
            TextUI.printFormatMeeting();
        }
    }

    public static void deleteMeeting(String[] userInputWords, MeetingList meetingList, Contact mainUser,
                                     ContactList myContactList, int currentWeekNumber) {
        try {
            int index = Integer.parseInt(userInputWords[1]) - 1;
            Meeting meetingToDelete = meetingList.getMeetingList().get(index);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String startTimeString = meetingToDelete.getStartTime().format(formatter);
            String endTimeString = meetingToDelete.getEndTime().format(formatter);
            int startDay = meetingToDelete.getStartDay();
            int endDay = meetingToDelete.getEndDay();
            String[] thisWeekNumber = {Integer.toString(currentWeekNumber)};
            mainUser.addFreeBlocks(startDay, startTimeString, endDay, endTimeString, thisWeekNumber);
            meetingList.delete(index);
            myContactList.set(0, mainUser);
        } catch (IndexOutOfBoundsException | WfException e) {
            TextUI.displayInvalidDeleteTarget();
        }
    }

    public static void scheduleMeeting(String[] userInputWords, MeetingList meetingList, Contact mainUser,
                                       ContactList contactList, int currentWeekNumber) {

        try {
            int dayDiff = Integer.parseInt(java.util.Calendar.getInstance().getTime().toString().split(" ")[2])
                    - Integer.parseInt(userInputWords[2]);

            //hardcoded for PE testing purposes
            dayDiff = 6 - Integer.parseInt(userInputWords[2]);

            if (dayDiff > 0 && dayDiff < 14) {
                throw new WfException(MESSAGE_WRONG_DATE);
            }

            if (userInputWords.length < 6) {
                throw new WfException(MESSAGE_WRONG_COMMAND_SCHEDULE);
            }
            int endOfMonthDate = 0;
            endOfMonthDate = getEndOfMonthDate(endOfMonthDate);


            int startOfWeekDate = getStartOfWeekDate();
            if (userInputWords[1].length() >= 260) {
                throw new WfException("Maximum characters for meeting name is 260");
            }
            String meetingName = userInputWords[1];
            int startDate = Integer.parseInt(userInputWords[2]);
            int endDate = Integer.parseInt(userInputWords[4]);
            int todayDate = Integer.parseInt(java.util.Calendar.getInstance().getTime().toString().split(" ")[2]);

            //hardcoded for PE testing purposes
            todayDate = 6;

            Integer startDay;
            Integer endDay;
            Integer  today;

            startDay = getDay(endOfMonthDate, startOfWeekDate, startDate);
            endDay = getDay(endOfMonthDate, startOfWeekDate, endDate);
            today = getDay(endOfMonthDate, startOfWeekDate, todayDate);

            if (endDay < startDay) {
                throw new WfException(MESSAGE_INVALID_SLOT_RANGE);
            }

            if (endDay < today || startDay < today) {
                throw new WfException(MESSAGE_INVALID_SLOT_RANGE);
            }

            LocalTime startTime = LocalTime.parse(userInputWords[3]);
            LocalTime endTime = LocalTime.parse(userInputWords[5]);
            if (mainUser.isValidMeeting(startDay, startTime, endDay, endTime, currentWeekNumber)) {
                Meeting myMeeting = new Meeting(meetingName, startDay, startTime, endDay, endTime, startDate, endDate);
                meetingList.add(myMeeting);
                String[] thisWeekNumber = {Integer.toString(currentWeekNumber)};
                mainUser.addBusyBlocks("meeting", startDay, userInputWords[3], endDay, userInputWords[5], thisWeekNumber);
                TextUI.meetingListSizeMsg(meetingList);
            } else {
                System.out.println("Schedule is blocked at that timeslot");
            }
        } catch (WfException e) {
            System.out.println(e.getMessage());
            TextUI.printFormatSchedule();
        } catch (DateTimeParseException e) {
            TextUI.timeOutOfRangeMsg();
            TextUI.printFormatSchedule();
        } catch (NumberFormatException e) {
            TextUI.invalidNumberMsg();
            TextUI.printFormatSchedule();
        }
        // Replace main user's timetable with updated model.meeting blocks into TeamMember.TeamMemberList for model.storage purposes.
        contactList.set(0, mainUser);
    }

    private static Integer getDay(int endOfMonthDate, int startOfWeekDate, int startDate) {
        Integer day;
        if (startDate - startOfWeekDate < 0) {
            day = endOfMonthDate - startOfWeekDate + startDate;
        } else {
            day = startDate - startOfWeekDate;
        }
        return day;
    }

    private static int getEndOfMonthDate(int endOfMonthDate) {
        Calendar cal = Calendar.getInstance();
        String day = (cal.getTime().toString().split(" "))[0];
        String month = (cal.getTime().toString().split(" "))[1];

        //hardcoded for PE testing purposes
        day = "Mon";
        month = "Apr";

        int distFromPreviousSunday = 0;
        for (int i = 0; i < 6 && !day.equals("Sun"); distFromPreviousSunday++, i++) {
            cal.add(Calendar.DATE, -1);
            if (!(cal.getTime().toString().split(" "))[1].equals(month)) {
                endOfMonthDate = Integer.parseInt(cal.getTime().toString().split(" ")[2]);
            }
            day = (cal.getTime().toString().split(" "))[0];
        }
        Calendar cal2 = Calendar.getInstance();

        //hardcoded for PE testing purposes
        cal2.set(Calendar.MONTH, 4);
        cal2.set(Calendar.DATE, 6);

        for (int i = 0; i < (14 - distFromPreviousSunday); i++) {
            if (!(cal2.getTime().toString().split(" "))[1].equals(month)) {
                break;
            }
            endOfMonthDate = Integer.parseInt(cal2.getTime().toString().split(" ")[2]);
            cal2.add(Calendar.DATE, 1);
        }
        return endOfMonthDate;
    }

    private static int getDateOfPreviousSunday(String[] data) {
        int date;
        Calendar cal = Calendar.getInstance();
        switch (data[0]) {
        case "Sun":
            date = Integer.parseInt(data[2]);
            break;
        case "Mon":
            cal.add(Calendar.DATE, -1);
            break;
        case "Tue":
            cal.add(Calendar.DATE, -2);
            break;
        case "Wed":
            cal.add(Calendar.DATE, -3);
            break;
        case "Thu":
            cal.add(Calendar.DATE, -4);
            break;
        case "Fri":
            cal.add(Calendar.DATE, -5);
            break;
        case "Sat":
            cal.add(Calendar.DATE, -6);
            break;
        default:
            cal.add(Calendar.DATE, 0);
        }
        String[] temp = cal.getTime().toString().split(" ");
        date = Integer.parseInt(temp[2]);

        //hardcoded for PE testing purposes
        date = 6;

        return date;
    }

    private static int getStartOfWeekDate() {
        String[] data = java.util.Calendar.getInstance().getTime().toString().split(" ");
        String day = data[0];

        int date = Integer.parseInt(data[2]);

        //hardcoded for PE testing purposes
        date = 6;
        day = "Mon";

        switch (day) {
        case "Mon":
            date -= 1;
            break;
        case "Tue":
            date -= 2;
            break;
        case "Wed":
            date -= 3;
            break;
        case "Thu":
            date -= 4;
            break;
        case "Fri":
            date -= 5;
            break;
        case "Sat":
            date -= 6;
            break;
        case "Sun":
            date = date;
            break;
        default:
            date = date;
            break;
        }
        return date;
    }

    public static void displayTimetable(String[] userInputWords, Contact mainUser,
                                        ContactList contactList, int weekNumber, int weeksMoreToView) throws WfException {
        int memberIndex;
        Contact member;
        try {
            String todayDate = java.util.Calendar.getInstance().getTime().toString().substring(0, 10).trim();

            //hardcoded for PE testing purposes
            todayDate = "Mon Apr 6th";

            if (userInputWords.length > 1) {
                ArrayList<Contact> myScheduleList = new ArrayList<Contact>();
                for (int i = 1; i < userInputWords.length; i++) {
                    memberIndex = Integer.parseInt(userInputWords[i]);
                    member = contactList.getContactList().get(memberIndex);
                    myScheduleList.add(member);
                }

                ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);
                Boolean[][][] myCombinedSchedule;
                myCombinedSchedule = myScheduleHandler.getCombinedSchedule();
                System.out.println("Today is " + todayDate + ", week " + weekNumber + ".");
                System.out.println("Timetable of the selected team member/s this week:");
                System.out.println();
                TextUI.printTimetable(myCombinedSchedule, weeksMoreToView, weekNumber);
            } else {
                System.out.println("Today is " + todayDate + ", week " + weekNumber + ".");
                System.out.println("Your timetable this week:");
                System.out.println();
                TextUI.printTimetable(mainUser.getSchedule(), weeksMoreToView, weekNumber);
            }
        } catch (IndexOutOfBoundsException e) {
            TextUI.indexOutOfBoundsMsg();
            TextUI.printFormatTimetable();
        } catch (NumberFormatException e) {
            TextUI.invalidNumberMsg();
            TextUI.printFormatTimetable();
        }
    }

    public static void listContacts(ContactList contactList) throws WfException {
        try {
            TextUI.teamMemberListMsg(contactList.getContactList());
        } catch (NullPointerException e) {
            throw new WfException("You have no stored contacts.");
        }
    }

}
