package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String dirPath;
    private File file;

    /**
     * represents a class used to save and restore info.
     * @param filePath the path of file used to save user info.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = filePath.substring(0,filePath.indexOf("/"));
        this.file = new File(this.filePath);
    }

    /**
     * restore person information from hard disk.
     * @param personList a list used to maintain all users' info.
     * @throws FileNotFoundException haven't found file from the given path.
     */
    public void loadFileData(PersonList personList) throws FileNotFoundException {
        checkDirectory();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String record = s.nextLine();
            restorePersonInfo(record,personList);
        }
    }

    private void checkDirectory() {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * restore original person information according to lines recorded in the txt file.
     * @param record original record in the txt file, plain text.
     * @param personList a list used to maintain all users' info.
     */
    public void restorePersonInfo(String record,PersonList personList) {
        Person currentPerson = null;
        DailyFoodRecord currentRecord = null;
        int curPersonIndex = personList.getLength() - 1;
        if (curPersonIndex >= 0) {
            currentPerson = personList.getOnePerson(curPersonIndex);
            int curDayIndex = currentPerson.getDays() - 1;
            currentRecord = currentPerson.getRecordOfDay(curDayIndex);
        }
        String[] info = record.split(" ");
        if (info[0].equals("breakfast") || info[0].equals("lunch") || info[0].equals("dinner")) {
            ArrayList<Food> foodList = new ArrayList<Food>();
            for (String foodName:info) {
                if (foodName.equals(info[0])) {
                    continue;
                }
                foodList.add(new Food(foodName));
            }
            if (currentPerson != null && currentRecord!=null) {
                currentPerson.setRecordOfDay(currentRecord,info[0],foodList);
            }
        } else if (info[0].equals("user")) {
            String name = info[1];
            int age = Integer.parseInt(info[2]);
            String gender = info[3];
            double height = Double.parseDouble(info[4]);
            double weight = Double.parseDouble(info[5]);
            double weightGoal = Double.parseDouble(info[6]);

            Person person = new Person(name,age,gender,height,weight,weightGoal);
            personList.append(person);
        } else if (info[0].equals("date")) {
            if (currentPerson != null) {
                currentPerson.addNewRecord(info[1]);
            }
        }
    }

    /**
     * save all changes of personList to hard disk.
     * @param personList a list used to maintain all users' info.
     */
    public void saveToFile(PersonList personList) {
        try {
            rewriteFile(personList);
        } catch (IOException e) {
            System.out.println("Something wrong while saving changes to file!!!");
        }
    }

    private void rewriteFile(PersonList personList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < personList.getLength(); i++) {
            Person currentPerson = personList.getOnePerson(i);
            String[] personInfo = currentPerson.getPersonInfo();
            for (String s : personInfo) {
                fw.write(s + System.lineSeparator());
            }
        }
        fw.close();
    }
}

