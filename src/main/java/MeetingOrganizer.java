import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import logic.command.CommandHandler;
import exception.MoException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.meeting.MeetingList;
import logic.modulelogic.ModuleHandler;
import model.contact.Contact;
import model.contact.ContactList;
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
    private ContactList myContactList;
    private Contact mainUser;

    public MeetingOrganizer() {
        //declare objects here
        myMeetingList = new MeetingList();
        try {
            storage = new Storage("data/meeting_list.txt");
            myMeetingList = new MeetingList(storage.loadMeetingListFromDisk());
            myContactList = new ContactList(storage.loadMemberListFromDisk());
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
        }
    }

    public static void runOnce() throws IOException {
        // RUN THIS TO FILTER UNFORMATTED MODULES INTO /UnformmattedModules file
        FileWriter fw = new FileWriter("UnformattedModules", true);
        URL url = new URL("https://api.nusmods.com/v2/2019-2020/moduleList.json");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        //Convert the input stream to a json element
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonArray rootObj = root.getAsJsonArray();
        for (int i = 0; i < rootObj.size(); i++) {
            JsonObject module = rootObj.get(i).getAsJsonObject();
            String moduleCode = module.get("moduleCode").toString().replaceAll("^.|.$", "");
            try {
                ModuleHandler myModuleHandler = new ModuleHandler(moduleCode);
                myModuleHandler.generateModule();
            } catch (Exception e) {
                fw.write(moduleCode + "\n");
            }
        }
        fw.close();
    }

    public static void main(String[] args) {
        try {
            if (!Files.exists(Paths.get("UnformattedModules"))) {
                System.out.println("Application is downloading blacklisted modules, please wait. "
                    + "This would only be run once.");
                runOnce();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
            userCommand = "add using link"; //add new contact with NUSMODS link. <Contact name> <NUSMODS link>
            //eg. xz https://nusmods.com/timetable/sem-2/share?CFG1002=LEC:06&CG2023=PLEC:02,LAB:03,PTUT:02&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2113T=LEC:C01&GES1020=TUT:2,LEC:1&SPH2101=LEC:1,TUT:6
            Contact newMember;
            newMember = CommandHandler.addContact(myContactList, userInputWords, startDay, endDay);
            if (checkMainUser()) {
                mainUser = newMember;
                newMember.setMainUser();
            }
            myContactList.add(newMember);
        } else {
            if (checkMainUser()) {
                throw new MoException("Please enter main user first.");
            }
            switch (userCommand) {
            case "contacts":  //list all contacts. contacts
                CommandHandler.listContacts(getMyContactList());
                break;
            case "timetable": //timetable OR timetable <Member Number> OR timetable <Member Number1> <Member Number2>
                //(eg. timetable 0 1 3)
                CommandHandler.displayTimetable(userInputWords, getMainUser(), getMyContactList());
                break;
            case "schedule": //schedule a model.meeting. schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>
                //(eg. schedule model.meeting 3 17:00 3 19:00)
                CommandHandler.scheduleMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyContactList());
                break;
            case "delete": //delete a model.meeting slot. delete <Meeting Number>
                CommandHandler.deleteMeeting(userInputWords, getMyMeetingList(), getMainUser(), getMyContactList());
                break;
            case "meetings": //list all scheduled model.meeting slots. meetings
                CommandHandler.listMeetings(userInputWords, getMyMeetingList());
                break;
            default:
                throw new MoException("Unknown logic.command, please try again.");
            }
        }
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        TextUI.menuMsg(myContactList.getSize());
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            if (userInput.equals("exit")) {
                break;
            }

            String[] userInputWords = userInput.split(" ");
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
                TextUI.menuMsg(myContactList.getSize());
            }
        }
        storage.updateMemberListToDisk(myContactList.getContactList());
        TextUI.exitMsg();
    }

    private Boolean checkMainUser() {
        return myContactList.getSize() == 0;
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



}

