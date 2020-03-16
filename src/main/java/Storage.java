import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    File f;

    /**
     * Constructor specifying file path.
     * @param filePath the file path to store on hard drive
     * @throws FileNotFoundException If file is not found
     */
    public Storage (String filePath) throws FileNotFoundException {
        f = new File("data/meeting_list.txt");
    }

    public void updateListToDisk(ArrayList<Meeting> meetingList){
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {//ignored as the error would mean the directory exists, thus no action needed
        }
        try {
            Files.createFile(Paths.get("data/meeting_list.txt"));
        } catch (IOException ignored) {//ignored as the error would mean the file exists, thus no action needed
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

    /**
     * Loads file from hard drive on start of program.
     *
     * @return list from hard drive
     * @throws FileNotFoundException If file is not found
     */
    public ArrayList<Meeting> loadListFromDisk() throws FileNotFoundException {
        ArrayList<Meeting> list = new ArrayList<>();
        Scanner reader = new Scanner(f);
        while (reader.hasNext()) {
            String[] data = reader.nextLine().split(" ");
            list.add(new Meeting(data[0], Integer.parseInt(data[1]),
                    LocalTime.parse(data[2]), Integer.parseInt(data[3]), LocalTime.parse(data[4])));
        }
        return list;
    }


}