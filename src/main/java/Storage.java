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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Storage {
    File meetingFile;
    File memberFile;

    /**
     * Constructor specifying file path of meeting list.
     *
     * @param meetingFilePath File path to load and store meeting list.
     */
    public Storage(String meetingFilePath) {
        meetingFile = new File(meetingFilePath); //meetingFilePath = "data/meeting_list.txt"
    }

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
                            + " " + n.getEndTime() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMemberListToDisk(ArrayList<TeamMember> myScheduleList) {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) { //ignored as the error would mean the directory exists, thus no action needed
        }

        //create separate text file for every member containing their schedules
        for (TeamMember member : myScheduleList) {
            String memberPath = "data/" + member.getName() + "_schedule.txt";
            try {
                Files.createFile(Paths.get(memberPath));
            } catch (IOException ignored) { //ignored as the error would mean the file exists, thus no action needed
            }

            try {
                FileWriter fw = new FileWriter(memberPath);
                String[][] schedule = member.getMyScheduleName();
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 48; j++) {
                        try {
                            fw.write((schedule[i][j] == null) ? schedule[i][j] : "null" + " ");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fw.write(System.lineSeparator());
                    }
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads file from hard drive on start of program.
     *
     * @return List from hard drive
     * @throws FileNotFoundException If file is not found
     */
    public ArrayList<Meeting> loadMeetingListFromDisk() throws FileNotFoundException {
        ArrayList<Meeting> list = new ArrayList<>();
        Scanner reader = new Scanner(meetingFile);
        while (reader.hasNext()) {
            String[] data = reader.nextLine().split(" ");
            Meeting entry = new Meeting(data[0], Integer.parseInt(data[1]),
                    LocalTime.parse(data[2]), Integer.parseInt(data[3]), LocalTime.parse(data[4]));
            list.add(entry);
        }
        return list;
    }


    public ArrayList<TeamMember> loadMemberListFromDisk() throws FileNotFoundException {
        ArrayList<TeamMember> list = new ArrayList<>();

        Path path = Paths.get("data");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*_schedule.log")) {
            Iterator<Path> iterator = ds.iterator();
            while (iterator.hasNext()) {
                Scanner reader = new Scanner((File) iterator);
                TeamMember member = new TeamMember(iterator.toString().replaceFirst("_schedule\\.log",""));
                String[][] myScheduleName = new String[7][48];
                int i = 0;
                int j = 0;
                while (reader.hasNext()) {
                    if (j >= 48) {
                        i++;
                        j = 0;
                    }
                    String data = reader.next();
                    myScheduleName[i][j] = (data == "null") ? null : data;
                    j++;
                }
                member.setMyScheduleName(myScheduleName);
                list.add(member);
                iterator.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}