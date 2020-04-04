import logic.command.CommandHandler;
import exception.MoException;

import model.meeting.MeetingList;
import model.contact.Contact;
import model.contact.ContactList;
import storage.Storage;
import ui.TextUI;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static common.Messages.MESSAGE_WRONG_COMMAND_DELETE;

/**
 * TESTING SUMMARY DOC.
 */
public class MeetingOrganizer {
    public static Storage storage;
    private MeetingList myMeetingList;
    private ContactList myContactList;
    private Contact mainUser;
    private int currentWeekNumber;
    private String day;
    public static final int RECESS_WEEK = 14;
    public static final int FREE_WEEK = 15;



    public MeetingOrganizer() {
        //declare objects here
        myMeetingList = new MeetingList();
        getWeekNumber();

        try {
            storage = new Storage("data/meeting_list.txt");
            myContactList = new ContactList(storage.loadMemberListFromDisk());
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
            assert getMainUser() != null;
            CommandHandler.listContacts(getMyContactList());
        } catch (FileNotFoundException e) {
            TextUI.introMsg();
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
            myContactList = new ContactList(new ArrayList<>());
        } catch (MoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MeetingOrganizer().run();
    }

    void botResponse(String[] userInputWords, String previousUserInput)
            throws MoException, DateTimeParseException, NumberFormatException {
        String userCommand = userInputWords[0];

        // To adapt user input of format <name> <NUSMODS link> to fit into the following switch statements to allow
        // for both link and manual input.
        // TODO member's name can only be 1 word at the moment.
        if (userInputWords.length == 2 && userInputWords[1].contains("http")) {
            //eg. xz https://nusmods.com/timetable/sem-2/share?CFG1002=LEC:06&CG2023=PLEC:02,LAB:03,PTUT:02&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2113T=LEC:C01&GES1020=TUT:2,LEC:1&SPH2101=LEC:1,TUT:6
            Contact newMember;

            newMember = CommandHandler.addContact(myContactList, userInputWords, null, null);
            if (checkMainUserDoesNotExists()) {
                mainUser = newMember;
                newMember.setMainUser();
            }
            myContactList.add(newMember);
        } else {
            if (checkMainUserDoesNotExists()) {
                throw new MoException("Please enter main user first.");
            }
            switch (userCommand) {
            case "more":
                if (previousUserInput.equals("")) {
                    throw new MoException("Nothing to see more of.");
                } else if (previousUserInput.contains("timetable")) {
                    int weeksMoreToView = 1;
                    CommandHandler.displayTimetable(userInputWords, getMainUser(),
                            getMyContactList(), currentWeekNumber, weeksMoreToView);
                } else if (previousUserInput.equals("more")) {
                    throw new MoException("No more :o");
                } else {
                    throw new MoException("more does not work with this command.");
                }
                break;
            case "edit": // edit <Member Number> <busy/free> <startDay> <startTime> <endDay> <endTime>
                // (eg. edit 0 busy 2 22:00 2 23:00)
                CommandHandler.editContact(userInputWords, getMainUser(), getMyContactList(), currentWeekNumber);
                break;
            case "contacts":  //list all contacts. contacts
                CommandHandler.listContacts(getMyContactList());
                break;
            case "timetable": //timetable OR timetable <Member Number> OR timetable <Member Number1> <Member Number2>
                //(eg. timetable 0 1 3)
                int weeksMoreToView = 0;
                CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyContactList(), currentWeekNumber, weeksMoreToView);
                break;
            case "schedule": //schedule a meeting. schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>
                //(eg. schedule meeting 3 17:00 3 19:00)
                CommandHandler.scheduleMeeting(userInputWords, getMyMeetingList(),
                        getMainUser(), getMyContactList(), currentWeekNumber);
                break;
            case "delete": //delete a model.meeting slot. delete <Meeting Number>
                try {
                    if (userInputWords.length != 2) {
                        throw new MoException(MESSAGE_WRONG_COMMAND_DELETE);
                    }
                    int numCheck = Integer.parseInt(userInputWords[1]);
                    CommandHandler.deleteMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyContactList());
                } catch (NumberFormatException e) {
                    myContactList.remove(userInputWords[1]);
                } catch (MoException e) {
                    System.out.println(e.getMessage());
                    TextUI.printFormatDeleteMember();
                    TextUI.printFormatDeleteMeeting();
                }
                break;
            case "meetings": //list all scheduled model.meeting slots. meetings
                CommandHandler.listMeetings(userInputWords, getMyMeetingList());
                break;
            default:
                throw new MoException("Unknown command, please try again.");
            }
        }
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        String previousUserInput = "";
        TextUI.menuMsg(myContactList.getSize());
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            if (userInput.equals("exit")) {
                break;
            }

            String[] userInputWords = userInput.split(" ");
            try {
                botResponse(userInputWords, previousUserInput);
                storage.updateMeetingListToDisk(myMeetingList.getMeetingList());
                storage.updateMemberListToDisk(myContactList.getContactList());
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
                TextUI.menuMsg(myContactList.getSize());
            }
        }
        TextUI.exitMsg();
    }

    private Boolean checkMainUserDoesNotExists() {
        return (myContactList.getSize() == 0);
    }

    private Contact getMainUser() {
        return mainUser;
    }

    private ContactList getMyContactList() {
        return myContactList;
    }

    private MeetingList getMyMeetingList() {
        return myMeetingList;
    }

    private void getWeekNumber() {
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

