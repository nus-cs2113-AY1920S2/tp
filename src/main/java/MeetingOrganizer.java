import exception.InvalidUrlException;
import exception.MoException;
import command.CommandHandler;
import inputparser.CliParser;
import meeting.MeetingList;
import modulelogic.LessonsGenerator;
import schedulelogic.TeamMember;
import schedulelogic.TeamMemberList;
import storage.Storage;
import ui.TextUI;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TESTING SUMMARY DOC.
 */
public class MeetingOrganizer {
    public static Storage storage;
    private MeetingList myMeetingList;
    private TeamMemberList myTeamMemberList;
    private TeamMember mainUser;

    public MeetingOrganizer() {
        //declare objects here
        myMeetingList = new MeetingList();
        try {
            storage = new Storage("data/meeting_list.txt");
            myMeetingList = new MeetingList(storage.loadMeetingListFromDisk());
            myTeamMemberList = new TeamMemberList(storage.loadMemberListFromDisk());
            TextUI.introMsg();
            if (myTeamMemberList.getSize() > 0) {
                myTeamMemberList.getTeamMemberList().forEach(member -> mainUser = member.isMainUser() ? member : null);
            }
            CommandHandler.listContacts(getMyTeamMemberList(), getMainUser());
        } catch (FileNotFoundException e) {
            TextUI.introMsg();
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
            myTeamMemberList = new TeamMemberList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new MeetingOrganizer().run();
    }

    void botResponse(String[] userInputWords, Scanner in) throws MoException, DateTimeParseException, NumberFormatException {
        Integer startDay = null;
        Integer endDay = null;
        TeamMember member;
        int memberNumber;

        String userCommand = userInputWords[0];

        //To adapt user input of format <name> <NUSMODS link> to fit into the following switch statements to allow
        // for both link and manual input.
        // TODO member's name can only be 1 word at the moment.
        if (userInputWords.length == 2 && userInputWords[1].contains("https")) {
            userCommand = "add using link";
        }

        switch (userCommand) {
        case "add using link": //add new contact with NUSMODS link. <Contact name> <NUSMODS link>
            //eg. xz https://nusmods.com/timetable/sem-2/share?CFG1002=LEC:06&CG2023=PLEC:02,LAB:03,PTUT:02&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2113T=LEC:C01&GES1020=TUT:2,LEC:1&SPH2101=LEC:1,TUT:6
            addContact(userInputWords, startDay, endDay);
            break;
        case "contacts":  //list all contacts. contacts
            CommandHandler.listContacts(getMyTeamMemberList(), getMainUser());
            break;
        case "timetable": //timetable OR timetable <Member Number> OR timetable <Member Number1> <Member Number2>
            //(eg. timetable 0 1 3)
            CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyTeamMemberList());
            break;
        case "schedule": //schedule a meeting. schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>
            //(eg. schedule meeting 3 17:00 3 19:00)
            CommandHandler.scheduleMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyTeamMemberList());
            break;
        case "delete": //delete a meeting slot. delete <Meeting Number>
            CommandHandler.deleteMeeting(userInputWords[1], getMyMeetingList(), getMainUser(), getMyTeamMemberList());
            break;
        case "meetings": //list all scheduled meeting slots. meetings
            CommandHandler.listMeetings(getMyMeetingList());
            break;
        default:
            throw new MoException("Unknown command, please try again.");
        }
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        TextUI.menuMsg(myTeamMemberList.getSize());
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            if (userInput.equals("exit")) {
                break;
            }

            String[] userInputWords = CliParser.splitWords(userInput);
            try {
                botResponse(userInputWords, in);
                storage.updateMeetingListToDisk(myMeetingList.getMeetingList());
            } catch (MoException e) {
                TextUI.errorMsg(e);
            } catch (DateTimeParseException e) {
                TextUI.timeOutOfRangeMsg();
            } catch (NumberFormatException e) {
                TextUI.invalidNumberMsg();
            } catch (IndexOutOfBoundsException e) {
                TextUI.indexOutOfBoundsMsg();
            } finally {
                TextUI.menuMsg(myTeamMemberList.getSize());
            }
        }
        storage.updateMemberListToDisk(myTeamMemberList.getTeamMemberList());
        TextUI.exitMsg();
    }

    public TeamMember getMainUser() {
        return mainUser;
    }

    public TeamMemberList getMyTeamMemberList() {
        return myTeamMemberList;
    }

    public MeetingList getMyMeetingList() {
        return myMeetingList;
    }

    private void addContact(String[] userInputWords, Integer startDay, Integer endDay) {
        TeamMember member;
        int checkerForRepeatedName = 0;
        checkerForRepeatedName = myTeamMemberList.getTeamMemberList().stream()
                .mapToInt(person -> check(person, userInputWords[0])).sum();
        if (checkerForRepeatedName == 1) {
            TextUI.showRepeatedPerson(userInputWords[0]);
            return;
        }

        member = new TeamMember(userInputWords[0]);
        String name = userInputWords[0];
        LessonsGenerator myLessonGenerator;
        try {
            myLessonGenerator = new LessonsGenerator(userInputWords[1]);
            myLessonGenerator.generate();
            ArrayList<String[]> myLessonDetails = myLessonGenerator.getLessonDetails();
            for (int k = 0; k < myLessonDetails.size(); k++) {
                String startTimeString = null;
                String endTimeString = null;
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
                        //future improvement: since myLessonDetails.get(k)[3] contains data on the
                        // week number that this class occurs on, add capability of schedule to reflect
                        // schedule of the current week.
                        break;
                    default:
                        //data only has four sections from api
                        break;
                    }
                }
                member.addBusyBlocks(name, startDay, startTimeString, endDay, endTimeString);
            }
            if (myTeamMemberList.getSize() == 0) {
                mainUser = member;
                member.setMainUser();
            }
            myTeamMemberList.add(member);
            TextUI.showAddedMember(member.getName());
        } catch (InvalidUrlException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    private int check(TeamMember person, String name) {
        if (person.getName().equals(name)) {
            return 1;
        } else {
            return 0;
        }
    }

    private Integer getNumberFromDay(String day) {
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
}

