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
                ArrayList<TeamMember> teamMemberList = myTeamMemberList.getTeamMemberList();
                // Shift mainUser to index 0
                for (int j = 0; j < teamMemberList.size(); j++) {
                    if (teamMemberList.get(j).isMainUser()) {
                        TeamMember toSwap = teamMemberList.get(0);
                        teamMemberList.set(0, teamMemberList.get(j));
                        teamMemberList.set(j, toSwap);
                        break;
                    }
                }
            }
            assert getMainUser() != null;
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

    void botResponse(String[] userInputWords) throws MoException, DateTimeParseException, NumberFormatException {
        Integer startDay = null;
        Integer endDay = null;
        String userCommand = userInputWords[0];

        //To adapt user input of format <name> <NUSMODS link> to fit into the following switch statements to allow
        // for both link and manual input.
        // TODO member's name can only be 1 word at the moment.
        if (userInputWords.length == 2 && userInputWords[1].contains("http")) {
            userCommand = "add using link";
        }
        switch (userCommand) {
        case "add using link": //add new contact with NUSMODS link. <Contact name> <NUSMODS link>
            //eg. xz https://nusmods.com/timetable/sem-2/share?CFG1002=LEC:06&CG2023=PLEC:02,LAB:03,PTUT:02&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2113T=LEC:C01&GES1020=TUT:2,LEC:1&SPH2101=LEC:1,TUT:6
            TeamMember newMember;
            newMember = CommandHandler.addContact(myTeamMemberList, userInputWords, startDay, endDay);
            if (checkMainUser(newMember)) {
                mainUser = newMember;
                newMember.setMainUser();
            }
            myTeamMemberList.add(newMember);
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
                botResponse(userInputWords);
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

    private Boolean checkMainUser(TeamMember newMember) {
        return myTeamMemberList.getSize() == 0;
    }

    private TeamMember getMainUser() {
        return mainUser;
    }

    private TeamMemberList getMyTeamMemberList() {
        return myTeamMemberList;
    }

    private MeetingList getMyMeetingList() {
        return myMeetingList;
    }



}

