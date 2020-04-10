package logic;

import static common.Messages.MESSAGE_WRONG_COMMAND_DELETE;


import common.exception.InvalidUrlException;
import common.exception.WfException;
import java.util.ArrayList;
import logic.command.CommandHandler;
import model.contact.Contact;
import model.contact.ContactList;
import model.meeting.MeetingList;
import ui.TextUI;

/**
 * Contains the logic required for each command and is the only class exposed to the model component.
 */
public class LogicManager {
    private MeetingList myMeetingList;
    private ContactList myContactList;
    private Contact mainUser;

    public LogicManager(MeetingList myMeetingList, ContactList myContactList, Contact mainUser) {
        this.mainUser = mainUser;
        if (myContactList == null || myMeetingList == null) {
            this.myMeetingList = new MeetingList();
            this.myContactList = new ContactList(new ArrayList<>());
        } else {
            this.myMeetingList = myMeetingList;
            this.myContactList = myContactList;
        }
    }

    /**
     * Adds a new contact and save it into model component.
     * @param userInputWords User input containing name and nusmods URL (space separated).
     */
    public void addContact(String[] userInputWords) throws WfException {
        Contact newMember;

        try {
            newMember = CommandHandler.addContact(myContactList, userInputWords, null, null);
            if (checkMainUserDoesNotExists()) {
                mainUser = newMember;
                newMember.setMainUser();
            }
            myContactList.add(newMember);
        } catch (InvalidUrlException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * View the timetable of selected team members.
     * @param userInputWords User input containing the index of selected team members (space separated)
     * @param currentWeekNumber Assigned to 0 to view only 1 week's worth of timetable.
     */
    public void viewTimetable(String[] userInputWords, int currentWeekNumber) throws WfException {
        int weeksMoreToView = 0;
        CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyContactList(), currentWeekNumber, weeksMoreToView);
    }

    /**
     * View additional timetable from next week as well. This function must follow viewTimetable() command.
     * @param prevUserInputWord Previous command of user. Function executes only is previous command is "timetable".
     * @param userInputWords Selected team members, automatically acquire the selected team members from previous input.
     * @param currentWeekNumber Assigned to 1 to view 2 week's worth of timetable.
     * @throws WfException Thrown if previous command isn't "timetable".
     */
    public void viewMoreTimetable(String prevUserInputWord, String[] userInputWords, int currentWeekNumber) throws WfException {
        if (prevUserInputWord.equals("")) {
            throw new WfException("Nothing to see more of.");
        } else if (prevUserInputWord.contains("timetable")) {
            int weeksMoreToView = 1;
            CommandHandler.displayTimetable(userInputWords, getMainUser(),
                getMyContactList(), currentWeekNumber, weeksMoreToView);
        } else if (prevUserInputWord.equals("more")) {
            throw new WfException("No more :o");
        } else {
            throw new WfException("more does not work with this command.");
        }
    }

    /**
     * Schedule a meeting and save it into model component.
     * @param userInputWords Contains the start/end day, start/end time and meeting name of the meeting.
     * @param currentWeekNumber Option to schedule this week's or next week's timetable.
     */
    public void scheduleMeeting(String[] userInputWords, int currentWeekNumber) {
        CommandHandler.scheduleMeeting(userInputWords, getMyMeetingList(),
            getMainUser(), getMyContactList(), currentWeekNumber);
    }

    /**
     * Delete meeting and update model component.
     * @param userInputWords Index of the meeting to delete.
     */
    public void deleteMeeting(String[] userInputWords, int currentWeekNumber) {
        try {
            if (userInputWords.length != 2) {
                throw new WfException(MESSAGE_WRONG_COMMAND_DELETE);
            }
            CommandHandler.deleteMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyContactList(), currentWeekNumber);
        } catch (NumberFormatException e) {
            myContactList.remove(userInputWords[1]);
        } catch (WfException e) {
            System.out.println(e.getMessage());
            TextUI.printFormatDeleteMember();
            TextUI.printFormatDeleteMeeting();
        }
    }

    public void listMeetings(String[] userInputWords) {
        CommandHandler.listMeetings(userInputWords, getMyMeetingList());
    }

    public void editSchedule(String[] userInputWords, int currentWeekNumber) {
        CommandHandler.editContact(userInputWords, getMainUser(), getMyContactList(), currentWeekNumber);
    }

    public void listContacts() throws WfException {
        CommandHandler.listContacts(getMyContactList());
    }

    public Contact getMainUser() {
        return this.mainUser;
    }

    public ContactList getMyContactList() {
        return this.myContactList;
    }

    public MeetingList getMyMeetingList() {
        return this.myMeetingList;
    }

    public Boolean checkMainUserDoesNotExists() {
        return (myContactList.getSize() == 0);
    }
}


