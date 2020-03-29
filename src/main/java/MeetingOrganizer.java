import command.CommandHandler;
import exception.MoException;
import inputparser.CliParser;
import meeting.MeetingList;
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
            CommandHandler.listContacts();
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
            commandHandler.addUsingLink(userInputWords, startDay, endDay);
        break;
        case "contacts":  //list all contacts. contacts
            commandHandler.listContacts();
            break;
        case "timetable": //timetable OR timetable <Member Number> OR timetable <Member Number1> <Member Number2>
            //(eg. timetable 0 1 3)
            commandHandler.displayTimetable(userInputWords);
            break;
        case "schedule": //schedule a meeting. schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>
            //(eg. schedule meeting 3 17:00 3 19:00)
            commandHandler.scheduleMeeting(userInputWords);
            break;
        case "delete": //delete a meeting slot. delete <Meeting Number>
            commandHandler.deleteMeeting(userInputWords[1]);
            break;
        case "meetings": //list all scheduled meeting slots. meetings
            commandHandler.listMeetings();
            break;
        default:
            throw new MoException("Unknown command, please try again.");
        }
    }

    private void listMeetings() {
        commandHandler.listMeetings();
    }

    private void deleteMeeting(String userInputWord) {
        commandHandler.deleteMeeting(userInputWord);
    }

    private void scheduleMeeting(String[] userInputWords) {

        // Replace main user's timetable with updated meeting blocks into TeamMember.TeamMemberList for storage purposes.
        commandHandler.scheduleMeeting(userInputWords);
    }

    private void displayTimetable(String[] userInputWords) {
        commandHandler.displayTimetable(userInputWords);
    }

    private void listContacts() {
        commandHandler.listContacts();
    }

    private void addUsingLink(String[] userInputWords, Integer startDay, Integer endDay) {

        commandHandler.addUsingLink(userInputWords, startDay, endDay);
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

}

