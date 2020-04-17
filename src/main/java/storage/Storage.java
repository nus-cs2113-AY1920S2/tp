package storage;

import model.meeting.Meeting;
import model.contact.Contact;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File meetingFile;

    /**
     * Constructor specifying file path of model.meeting list.
     *
     * @param meetingFilePath File path to load and store model.meeting list.
     */
    public Storage(String meetingFilePath) {
        meetingFile = new File(meetingFilePath);
    }

    /**
     * Updates meeting list to hard drive on call.
     *
     */
    public void updateMeetingListToDisk(ArrayList<Meeting> meetingList) {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) { //ignored as the error would mean the directory exists, thus no action needed
        }

        try {
            Files.createFile(Paths.get("data/meeting_list.txt"));
        } catch (IOException ignored) { //ignored as the error would mean the file exists, thus no action needed
        }

        try {
            FileWriter fw = new FileWriter("data/meeting_list.txt");
            meetingList.forEach((n) -> {
                try {
                    fw.write(n.getMeetingName() + " " + n.getStartDay() + " " + n.getStartTime() + " " + n.getEndDay()
                            + " " + n.getEndTime() + " " + n.getStartDate() + " " + n.getEndDate() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates member list to hard drive on call.
     *
     */
    public void updateMemberListToDisk(ArrayList<Contact> myContactList) {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) { //ignored as the error would mean the directory exists, thus no action needed
        }

        //create separate text file for every member containing their schedules
        for (Contact member : myContactList) {
            String memberPath = "data/" + ((member.isMainUser()) ? member.getName() + "_main" : member.getName()) + "_schedule.txt";
            try {
                Files.createFile(Paths.get(memberPath));
            } catch (IOException ignored) { //ignored as the error would mean the file exists, thus no action needed
            }

            try {
                FileWriter fw = new FileWriter(memberPath);
                String[][][] schedule = member.getMyScheduleName();
                for (int i = 0; i < 13; i++) {
                    for (int j = 0; j < 7; j++) {
                        for (int k = 0; k < 48; k++) {
                            try {
                                fw.write(schedule[i][j][k] + " ");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        fw.write(System.lineSeparator());
                    }
                    fw.write(System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //remove deleted members' .txt file from hard disk
        File folder = new File("data/");
        for (File f : folder.listFiles()) {
            String fileName = f.getName();
            if (f.getName().equals("meeting_list.txt")) {
                continue;
            }
            for (Contact contact : myContactList) {
                if (f.getName().contains(contact.getName())) {
                    break;
                }
                if (contact == myContactList.get(myContactList.size() - 1)) {
                    f.delete();
                }
            }
        }

    }

    /**
     * Loads meeting list file from hard drive on start of program.
     *
     * @return ContactList from hard drive.
     * @throws FileNotFoundException If file is not found.
     */
    public ArrayList<Meeting> loadMeetingListFromDisk() throws FileNotFoundException {
        ArrayList<Meeting> list = new ArrayList<>();
        Scanner reader = new Scanner(meetingFile);
        while (reader.hasNext()) {
            String[] data = reader.nextLine().split(" ");
            Meeting entry = new Meeting(data[0], Integer.parseInt(data[1]),
                    LocalTime.parse(data[2]), Integer.parseInt(data[3]),
                    LocalTime.parse(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
            list.add(entry);
        }
        return list;
    }


    /**
     * Loads contact list files from hard drive on start of program.
     *
     * @return ContactList from hard drive.
     * @throws FileNotFoundException If file is not found.
     */
    public ArrayList<Contact> loadContactListFromDisk() throws FileNotFoundException {
        ArrayList<Contact> list = new ArrayList<>();

        Path path = Paths.get("data");
        File file = new File("data");
        boolean dirCreated = file.mkdir();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (entry.toString().contains("_schedule.txt")) {
                    String memberName = entry.toString().substring(5);
                    memberName = memberName.replaceAll("_schedule\\.txt", "");

                    String[][][] myScheduleName = new String[13][7][48];
                    Contact member = new Contact(memberName);

                    Scanner reader = new Scanner(entry);
                    while (reader.hasNext()) {
                        for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 7; j++) {
                                for (int k = 0; k < 48; k++) {
                                    myScheduleName[i][j][k] = reader.next();
                                }
                            }
                        }
                    }
                    member.setMyScheduleName(myScheduleName);
                    member.setMyScheduleFromScheduleName();
                    list.add(member);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}