package command;

import exception.InvalidUrlException;
import meeting.MeetingList;
import exception.MoException;
import meeting.Meeting;
import modulelogic.LessonsGenerator;
import schedulelogic.ScheduleHandler;
import schedulelogic.TeamMember;
import schedulelogic.TeamMemberList;
import ui.TextUI;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CommandHandler {

    public static TeamMember addContact(TeamMemberList myTeamMemberList, String[] userInputWords,
                                        Integer startDay, Integer endDay) throws MoException {
        TeamMember member;
        checkRepeatedName(myTeamMemberList, userInputWords);

        String name = userInputWords[0];
        String url = userInputWords[1];

        member = new TeamMember(name);
        LessonsGenerator myLessonGenerator;
        try {
            myLessonGenerator = new LessonsGenerator(url);
            myLessonGenerator.generate();
            ArrayList<String[]> myLessonDetails = myLessonGenerator.getLessonDetails();

            for (int k = 0; k < myLessonDetails.size(); k++) {
                String startTimeString = null;
                String endTimeString = null;
                String[] weeks = new String[0];
                for (int j = 0; j < myLessonDetails.get(k).length; j++) {
                    switch (j) {
                    case 0:
                        startTimeString = myLessonDetails.get(k)[j].substring(0, 2) + ":" + myLessonDetails.get(k)[j].substring(2);
                        break;
                    case 1:
                        endTimeString = myLessonDetails.get(k)[j].substring(0, 2) + ":" + myLessonDetails.get(k)[j].substring(2);
                        break;
                    case 2:
                        startDay = getNumberFromDay(myLessonDetails.get(k)[j]);
                        endDay = startDay;
                        break;
                    case 3:
                        weeks = myLessonDetails.get(k)[j].split(":");
                        System.out.println(Arrays.toString(weeks));
                        //future improvement: since myLessonDetails.get(k)[3] contains data on the
                        // week number that this class occurs on, add capability of schedule to reflect
                        // schedule of the current week.
                        //
                        //0900 1200 Friday 5:7:9:11
                        //1600 1800 Thursday 1:2:3:4:5:6:7:8:9:10:11:12:13
                        //1600 1800 Tuesday 1:2:3:4:5:6:7:8:9:10:11:12:13
                        //0900 1200 Tuesday 1:2:3:4:5:6
                        break;
                    default:
                        //data only has four sections from api
                        break;
                    }
                }
                member.addBusyBlocks(name, startDay, startTimeString, endDay, endTimeString, weeks);
            }
            TextUI.showAddedMember(member.getName());
        } catch (InvalidUrlException e) {
            System.out.println(e.getMessage());
        }
        return member;
    }

    private static void checkRepeatedName(TeamMemberList myTeamMemberList, String[] userInputWords) throws MoException {
        int checkerForRepeatedName;
        checkerForRepeatedName = myTeamMemberList.getTeamMemberList().stream()
                .mapToInt(person -> check(person, userInputWords[0])).sum();
        if (checkerForRepeatedName == 1) {
            TextUI.showRepeatedPerson(userInputWords[0]);
            throw new MoException("Repeated user");
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

    private static int check(TeamMember person, String name) {
        if (person.getName().equals(name)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void listMeetings(MeetingList meetingList) {
        meetingList.show();
    }

    public static void deleteMeeting(String userInputWord, MeetingList meetingList, TeamMember mainUser, TeamMemberList
            teamMemberList) {
        int index = Integer.parseInt(userInputWord) - 1;
        try {
            Meeting meetingToDelete = meetingList.getMeetingList().get(index);
            String meetingNameToDelete = meetingToDelete.getMeetingName();
            mainUser.deleteBlocksWithName(meetingNameToDelete);
            meetingList.delete(index);
            teamMemberList.set(0, mainUser);
        } catch (IndexOutOfBoundsException e) {
            TextUI.displayInvalidDeleteTarget();
        }
    }

    public static void scheduleMeeting(String[] userInputWords, MeetingList meetingList, TeamMember mainUser,
                                       TeamMemberList teamMemberList, int currentWeekNumber) {


        try {
            int endOfMonthDate = 0;
            Calendar cal = Calendar.getInstance();
            String day = (cal.getTime().toString().split(" "))[0];
            String month = (cal.getTime().toString().split(" "))[1];
            int distFromPreviousSunday = 0;
            for (int i = 0; i < 6 && !day.equals("Sun"); distFromPreviousSunday++, i++) {
                cal.add(Calendar.DATE, -1);
                if (!(cal.getTime().toString().split(" "))[1].equals(month)) {
                    endOfMonthDate = Integer.parseInt(cal.getTime().toString().split(" ")[2]);
                }
                day = (cal.getTime().toString().split(" "))[0];
            }
            Calendar cal2 = Calendar.getInstance();
            for (int i = 0; i < (14 - distFromPreviousSunday); i++) {
                if (!(cal2.getTime().toString().split(" "))[1].equals(month)) {
                    break;
                }
                endOfMonthDate = Integer.parseInt(cal2.getTime().toString().split(" ")[2]);
                cal2.add(Calendar.DATE, 1);
            }

            Integer startDay;
            Integer endDay;
            int startOfWeekDate = getStartOfWeekDate();
            String meetingName = userInputWords[1];
            int startDate = Integer.parseInt(userInputWords[2]);
            int endDate = Integer.parseInt(userInputWords[4]);
            if (startDate - startOfWeekDate < 0) {
                startDay = endOfMonthDate - startOfWeekDate + startDate;
            } else {
                startDay = startDate - startOfWeekDate;
            }
            if (endDate - startOfWeekDate < 0) {
                endDay = endOfMonthDate - startOfWeekDate + endDate;
            } else {
                endDay = endDate - startOfWeekDate;
            }


            LocalTime startTime = LocalTime.parse(userInputWords[3]);
            LocalTime endTime = LocalTime.parse(userInputWords[5]);
            if (ScheduleHandler.isValidMeeting(mainUser, startDay, startTime, endDay, endTime, currentWeekNumber)) {
                Meeting myMeeting = new Meeting(meetingName, startDay, startTime, endDay, endTime, startDate, endDate);
                meetingList.add(myMeeting);
                String[] thisWeekNumber = {Integer.toString(currentWeekNumber)};
                mainUser.addBusyBlocks(meetingName, startDay, userInputWords[3], endDay, userInputWords[5], thisWeekNumber);
                TextUI.meetingListSizeMsg(meetingList);
            } else {
                System.out.println("Schedule is blocked at that timeslot");
            }
        } catch (MoException e) {
            System.out.println(e.getMessage() + ", try again.");
        }
        // Replace main user's timetable with updated meeting blocks into TeamMember.TeamMemberList for storage purposes.
        teamMemberList.set(0, mainUser);
    }

    private static int getDateOfPreviousSunday(String[] data) {
        int date;
        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -);

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
        return date;
    }

    private static int getStartOfWeekDate() {
        String[] data = java.util.Calendar.getInstance().getTime().toString().split(" ");
        String day = data[0];
        int date = Integer.parseInt(data[2]);
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

    public static void displayTimetable(String[] userInputWords, TeamMember mainUser, TeamMemberList teamMemberList, int weekNumber, int weeksMoreToView) throws MoException {
        int memberNumber;
        TeamMember member;
        try {
            if (userInputWords.length > 1) {
                ArrayList<TeamMember> myScheduleList = new ArrayList<TeamMember>();
                for (int i = 1; i < userInputWords.length; i++) {
                    memberNumber = Integer.parseInt(userInputWords[i]);
                    member = teamMemberList.getTeamMemberList().get(memberNumber);
                    myScheduleList.add(member);
                }
                ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);
                Boolean[][][] myMasterSchedule;
                myMasterSchedule = myScheduleHandler.getMasterSchedule();
                System.out.println("Timetable of the selected team member/s this week:");
                TextUI.printTimetable(myMasterSchedule, weeksMoreToView, weekNumber);
            } else {
                System.out.println("Your timetable this week:");
                TextUI.printTimetable(mainUser.getSchedule(), weeksMoreToView, weekNumber);
            }
        } catch (IndexOutOfBoundsException e) {
            TextUI.indexOutOfBoundsMsg();
            System.out.println("Index should be within range 0 to " + (teamMemberList.getTeamMemberList().size() - 1) + ".");
            listContacts(teamMemberList, mainUser);
        } catch (NumberFormatException e) {
            TextUI.invalidNumberTimetableMsg();
        }
    }

    public static void listContacts(TeamMemberList teamMemberList, TeamMember mainUser) throws MoException {
        try {
            TextUI.teamMemberListMsg(teamMemberList.getTeamMemberList(), mainUser.getName());
        } catch (NullPointerException e) {
          throw new MoException("You have no contacts.");
        }
    }


}