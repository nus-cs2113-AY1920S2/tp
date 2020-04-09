package logic;

import static common.Messages.MESSAGE_WRONG_COMMAND_DELETE;


import common.exception.InvalidUrlException;
import common.exception.MoException;
import java.util.ArrayList;
import logic.command.CommandHandler;
import model.contact.Contact;
import model.contact.ContactList;
import model.meeting.MeetingList;
import ui.TextUI;

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

    public void addContact(String[] userInputWords) throws MoException {
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

    public void viewTimetable(String[] userInputWords, int currentWeekNumber) throws MoException {
        int weeksMoreToView = 0;
        CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyContactList(), currentWeekNumber, weeksMoreToView);
    }

    public void viewMoreTimetable(String prevUserInputWord, String[] userInputWords, int currentWeekNumber) throws MoException {
        if (prevUserInputWord.equals("")) {
            throw new MoException("Nothing to see more of.");
        } else if (prevUserInputWord.contains("timetable")) {
            int weeksMoreToView = 1;
            CommandHandler.displayTimetable(userInputWords, getMainUser(),
                getMyContactList(), currentWeekNumber, weeksMoreToView);
        } else if (prevUserInputWord.equals("more")) {
            throw new MoException("No more :o");
        } else {
            throw new MoException("more does not work with this command.");
        }
    }

    public void scheduleMeeting(String[] userInputWords, int currentWeekNumber) {
        CommandHandler.scheduleMeeting(userInputWords, getMyMeetingList(),
            getMainUser(), getMyContactList(), currentWeekNumber);
    }

    public void deleteMeeting(String[] userInputWords) {
        try {
            if (userInputWords.length != 2) {
                throw new MoException(MESSAGE_WRONG_COMMAND_DELETE);
            }
            CommandHandler.deleteMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyContactList());
        } catch (NumberFormatException e) {
            myContactList.remove(userInputWords[1]);
        } catch (MoException e) {
            System.out.println(e.getMessage());
            TextUI.printFormatDeleteMember();
            TextUI.printFormatDeleteMeeting();
        }
    }

    public void listMeetings(String[] userInputWords) {
        CommandHandler.listMeetings(userInputWords, getMyMeetingList());
    }

    public void editSchedule(String[] userInputWords, int currentWeekNumber) throws MoException {
        CommandHandler.editContact(userInputWords, getMainUser(), getMyContactList(), currentWeekNumber);
    }

    public void listContacts() throws MoException {
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


