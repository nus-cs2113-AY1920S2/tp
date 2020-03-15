package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String FILE_PATH;
    private String DIR_PATH;
    private File f;

    public Storage(String filePath){
        this.FILE_PATH = filePath;
        this.DIR_PATH = FILE_PATH.substring(0,FILE_PATH.indexOf("/"));
        this.f = new File(FILE_PATH);
    }

    public void loadFileData(PersonList personList) throws FileNotFoundException {
        checkDirectory();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String record = s.nextLine();
            restorePersonInfo(record,personList);
        }
    }

    private void checkDirectory() {
        File dir = new File(DIR_PATH);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    public void restorePersonInfo(String record,PersonList personList){
        int curPersonIndex = personList.getLength()-1;
        Person currentPerson = personList.getOnePerson(curPersonIndex);
        int curDayIndex = currentPerson.getDays()-1;
        DailyFoodRecord currentRecord = currentPerson.getRecordOfDay(curDayIndex);

        String[] info = record.split(" ");
        if(info[0].equals("breakfast") || info[0].equals("lunch") || info[0].equals("dinner")){
            ArrayList<Food> foodList = new ArrayList<Food>();
            for(String foodName:info){
                if(foodName.equals(info[0])) continue;
                foodList.add(new Food(foodName));
            }
            currentPerson.setRecordOfDay(currentRecord,info[0],foodList);
        } else if(info[0].equals("user")){
            String name = info[1];
            int age = Integer.parseInt(info[2]);
            String gender = info[3];
            double height = Double.parseDouble(info[4]);
            double weight = Double.parseDouble(info[5]);
            double weightGoal = Double.parseDouble(info[6]);

            Person person = new Person(name,age,gender,height,weight,weightGoal);
            personList.append(person);
        } else if(info[0].equals("date")){
            currentPerson.addNewRecord(info[1]);
        }
    }

    public void saveToFile(PersonList personList){
        try {
            rewriteFile(personList);
        } catch (IOException e) {
            System.out.println("Something wrong while saving changes to file!!!");
        }
    }

    private void rewriteFile(PersonList personList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < personList.getLength(); i++) {
            Person currentPerson = personList.getOnePerson(i);
            String[] personInfo = currentPerson.getPersonInfo();
            for(int j=0;j<personInfo.length;j++){
                fw.write(personInfo[j]+System.lineSeparator());
            }
        }
        fw.close();
    }
}

