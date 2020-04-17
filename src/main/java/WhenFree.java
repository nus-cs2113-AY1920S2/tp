import logic.LogicManager;
import common.exception.WfException;

import model.meeting.MeetingList;
import model.contact.Contact;
import model.contact.ContactList;
import storage.Storage;
import ui.TextUI;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application containing an instance of LogicManager and storage component.
 */
public class WhenFree {
    public static final int RECESS_WEEK = 14;
    public static final int FREE_WEEK = 15;
    public static Storage storage;
    private LogicManager myLogicManager;
    private int currentWeekNumber;

    public WhenFree() {
        MeetingList myMeetingList;
        ContactList myContactList;
        Contact mainUser = null;
        getWeekNumber();

        try {
            storage = new Storage("data/meeting_list.txt");
            myContactList = new ContactList(storage.loadContactListFromDisk());
            myMeetingList = new MeetingList(storage.loadMeetingListFromDisk());
            TextUI.introMsg();
            if (myContactList.getSize() > 0) {
                for (int i = 0; i < myContactList.getSize(); i++) {
                    if (myContactList.getContactList().get(i).isMainUser()) {
                        mainUser = myContactList.getContactList().get(i);
                    }
                }
                ArrayList<Contact> contactList = myContactList.getContactList();
                // Shift mainUser to index 0
                for (int j = 0; j < contactList.size(); j++) {
                    if (contactList.get(j).isMainUser()) {
                        Contact toSwap = contactList.get(0);
                        contactList.set(0, contactList.get(j));
                        contactList.set(j, toSwap);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            TextUI.introMsg();
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
            myContactList = new ContactList(new ArrayList<>());
        }
        myLogicManager = new LogicManager(myMeetingList, myContactList, mainUser);
    }

    public static void main(String[] args) {
        new WhenFree().run();
    }

    void botResponse(String[] userInputWords, String prevUserInputWord, String[] prevUserInputWords)
        throws WfException, DateTimeParseException, NumberFormatException {
        String userCommand = userInputWords[0];

        if (userInputWords.length == 2 && userInputWords[1].contains("nusmods.com")) {
            myLogicManager.addContact(userInputWords);
        } else {
            if (myLogicManager.checkMainUserDoesNotExists()) {
                throw new WfException("Please enter main user first");
            }
            switch (userCommand) {
            case "more":
                myLogicManager.viewMoreTimetable(prevUserInputWord, prevUserInputWords, currentWeekNumber);
                break;
            case "edit":
                myLogicManager.editSchedule(userInputWords, currentWeekNumber);
                break;
            case "contacts":
                myLogicManager.listContacts();
                break;
            case "timetable":
                myLogicManager.viewTimetable(userInputWords, currentWeekNumber);
                break;
            case "schedule":
                myLogicManager.scheduleMeeting(userInputWords, currentWeekNumber);
                break;
            case "delete":
                myLogicManager.deleteMeeting(userInputWords, currentWeekNumber);
                break;
            case "meetings":
                myLogicManager.listMeetings(userInputWords);
                break;
            default:
                throw new WfException("Please follow the options in the menu.");
            }
        }
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        String prevUserInputWord = "";
        String[] prevUserInputWords = new String[0];
        TextUI.menuMsg(myLogicManager.getMyContactList().getSize());
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            if (userInput.toLowerCase().equals("exit")) {
                break;
            }

            userInput = userInput.replaceAll("\\s+", " ");

            String[] userInputWords = userInput.split(" ");
            try {
                botResponse(userInputWords, prevUserInputWord, prevUserInputWords);
                storage.updateMeetingListToDisk(myLogicManager.getMyMeetingList().getMeetingList());
                storage.updateMemberListToDisk(myLogicManager.getMyContactList().getContactList());
                prevUserInputWord = userInputWords[0];
                prevUserInputWords = userInputWords;
            } catch (WfException e) {
                TextUI.errorMsg(e);
            } catch (DateTimeParseException e) {
                TextUI.timeOutOfRangeMsg();
            } catch (NumberFormatException e) {
                TextUI.invalidNumberMsg();
            } catch (IndexOutOfBoundsException e) {
                TextUI.indexOutOfBoundsMsg();
            } finally {
                TextUI.menuMsg(myLogicManager.getMyContactList().getSize());
            }
        }
        TextUI.exitMsg();
    }


    private void getWeekNumber() {
        String[] tempTime = java.util.Calendar.getInstance().getTime().toString().split(" "); //Format: Thu Mar 26 08:22:02 IST 2015
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
        //hardcoded for PE testing purposes
        currentWeekNumber = 12;
    }

}

