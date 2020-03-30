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
    private int currentWeekNumber;
    private String day;
    final int RECESS_WEEK = 14;
    final int FREE_WEEK = 15;

    public MeetingOrganizer() {
        //declare objects here
        myMeetingList = new MeetingList();
        getWeekNumber();

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
        } catch (FileNotFoundException | MoException e) {
            TextUI.introMsg();
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
            myTeamMemberList = new TeamMemberList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        for (int i = 0; i < 7; i++) {
//            cal.add(Calendar.DATE, 1);
//            out.println(cal.getTime());
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DATE, );
//        calendar.add(Calendar.MONTH, 1);
//        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//        Date nextMonthFirstDay = calendar.getTime();
//        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date nextMonthLastDay = calendar.getTime();

        new MeetingOrganizer().run();
    }

    void botResponse(String[] userInputWords, String previousUserInput) throws MoException, DateTimeParseException, NumberFormatException {
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
        case "more":
            if (previousUserInput.equals("")) {
                throw new MoException("Nothing to see more of.");
            }
            if (previousUserInput.contains("timetable")) {
                int weeksMoreToView = 1;
                CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyTeamMemberList(), currentWeekNumber, weeksMoreToView);
            }
            break;
        case "contacts":  //list all contacts. contacts
            CommandHandler.listContacts(getMyTeamMemberList(), getMainUser());
            break;
        case "timetable": //timetable OR timetable <Member Number> OR timetable <Member Number1> <Member Number2>
            //(eg. timetable 0 1 3)
            int weeksMoreToView = 0;
            CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyTeamMemberList(), currentWeekNumber, weeksMoreToView);
            break;
        case "schedule": //schedule a meeting. schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>
            //(eg. schedule meeting 3 17:00 3 19:00)
            CommandHandler.scheduleMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyTeamMemberList(), currentWeekNumber);
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
        String previousUserInput = "";
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            if (userInput.equals("exit")) {
                break;
            }

            String[] userInputWords = CliParser.splitWords(userInput);
            try {
                botResponse(userInputWords, previousUserInput);
                storage.updateMeetingListToDisk(myMeetingList.getMeetingList());
                previousUserInput = userInput;
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

    private void getWeekNumber() {
        //get week user is in
        String[] tempTime = java.util.Calendar.getInstance().getTime().toString().split(" "); //Thu Mar 26 08:22:02 IST 2015
        this.day = tempTime[0];
        String month = tempTime[1];
        int date = Integer.parseInt(tempTime[2]);
        //week starts on Sunday
        switch (month) {
        case "Jan":
            if (date >= 12 && date <= 18) {
                currentWeekNumber = 1;
            } else if (date >= 19 && date <= 25) {
                currentWeekNumber = 2;
            } else if (date >= 26) {
                currentWeekNumber = 3;
            }
            break;
        case "Feb":
            if (date <= 1) {
                currentWeekNumber = 3;
            } else if (date >= 2 && date <= 8) {
                currentWeekNumber = 4;
            } else if (date >= 9 && date <= 15) {
                currentWeekNumber = 5;
            } else if (date >= 16 && date <= 20) {
                currentWeekNumber = 6;
            } else if (date >= 21) {
                currentWeekNumber = RECESS_WEEK;
            }
            break;
        case "Mar":
            if (date >= 1 && date <= 7) {
                currentWeekNumber = 7;
            } else if (date >= 8 && date <= 14) {
                currentWeekNumber = 8;
            } else if (date >= 15 && date <= 21) {
                currentWeekNumber = 9;
            } else if (date >= 22 && date <= 28) {
                currentWeekNumber = 10;
            } else if (date >= 29) {
                currentWeekNumber = 11;
            }
            break;
        case "Apr":
            if (date <= 4) {
                currentWeekNumber = 11;
            } else if (date >= 5 && date <= 11) {
                currentWeekNumber = 12;
            } else if (date >= 12 && date <= 18) {
                currentWeekNumber = 13;
            } else if (date >= 19) {
                currentWeekNumber = FREE_WEEK;
            }
            break;
        default:
            currentWeekNumber = FREE_WEEK;
            break;
        }
    }

}

