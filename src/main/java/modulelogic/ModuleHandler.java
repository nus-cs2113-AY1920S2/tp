package modulelogic;

import exception.UnformattedModuleException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Each module information is an ArrayList(size: 2. Because 2 semester) of ArrayList except member "weeks".
 * Semester 1                       : Semester 2
 * ArrayList of available classes   : ArrayList of available classes
 * <br>
 * Indexes of the ArrayList matches. For eg, classNumber.get(semester).get(0) belongs to lessonType.get(semester).get(0)
 * held on day.get(semester).get(0) at startTime.get(semester).get(0) and endTime.get(semester).get(0)
 * <br>
 * "weeks" is an ArrayList(size: 2. Because 2 semester) of a 2d ArrayList:
 * <br>
 * Eg: week[0].get(2) = [1, 3, 4, 5] corresponds to:
 * Semester 1, class 2 has lessons in week 1, 3, 4, 5.
 */
public class ModuleHandler {
    JsonArray semesterData;
    private ArrayList<ArrayList<String>> classNumber; // can be repeated.
    private ArrayList<ArrayList<String>> startTime;
    private ArrayList<ArrayList<String>> endTime;
    private ArrayList<ArrayList<String>> lessonType; //tutorial, lecture etc
    private ArrayList<ArrayList<String>> day; // one day per classNumber
    private ArrayList<ArrayList<ArrayList<String>>> weeks;
    private String moduleName;
    private Set<String> unformattedModules;

    public ModuleHandler(String moduleName) throws FileNotFoundException {
        this.moduleName = moduleName;
        this.classNumber = new ArrayList<>();
        this.startTime = new ArrayList<>();
        this.endTime = new ArrayList<>();
        this.lessonType = new ArrayList<>();
        this.weeks = new ArrayList<>();
        this.day = new ArrayList<>();
        for (int i = 0; i < 2; i++) { // number of semesters
            this.classNumber.add(new ArrayList<>());
            this.startTime.add(new ArrayList<>());
            this.endTime.add(new ArrayList<>());
            this.lessonType.add(new ArrayList<>());
            this.day.add(new ArrayList<>());
            this.weeks.add(new ArrayList<>());
        }
        this.unformattedModules = new HashSet<>();
        Scanner reader = new Scanner(new File("UnformattedModules"));
        while (reader.hasNext()) {
            String data = reader.nextLine();
            unformattedModules.add(data);
        }
    }

    public static void main(String[] args) throws IOException {

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

    // TODO split code into smaller methods.

    /**
     * Generate an ArrayList of module information- classNumber, lessonType, startTime, endTime, day, weeks.
     */
    public void generateModule() throws UnformattedModuleException {
        ModuleApiParser myModuleApiParser = new ModuleApiParser(moduleName);
        try {
            semesterData = myModuleApiParser.parse();
        } catch (IOException e) {
            System.out.println("Unable to access api, using auxiliary module data");
        }

        //TODO SET UP FAKE DATA HERE IF UNABLE TO ACCESS API
        assert semesterData != null;
        checkModuleFormat();
        for (int i = 0; i < semesterData.size(); i++) {
            JsonObject semesterDataObj = semesterData.get(i).getAsJsonObject();
            // get semester number from json
            Integer semester = Integer.parseInt(semesterDataObj.get("semester").toString()) - 1;
            if (!(semester == 0 || semester == 1)) {
                continue;
            }
            JsonArray timetable = (JsonArray) semesterDataObj.get("timetable");
            for (int k = 0; k < timetable.size(); k++) { // For each classes
                JsonObject lesson = timetable.get(k).getAsJsonObject();
                // replaceAll() trims the quotes left behind by json parsing via regex
                this.classNumber.get(semester).add(lesson.get("classNo").toString().replaceAll("^.|.$", ""));
                this.lessonType.get(semester).add(lesson.get("lessonType").toString().replaceAll("^.|.$", ""));
                this.startTime.get(semester).add(lesson.get("startTime").toString().replaceAll("^.|.$", ""));
                this.endTime.get(semester).add(lesson.get("endTime").toString().replaceAll("^.|.$", ""));
                this.day.get(semester).add(lesson.get("day").toString().replaceAll("^.|.$", ""));
                assert classNumber != null;
                assert lessonType != null;
                assert startTime != null;
                assert endTime != null;
                assert day != null;
                JsonArray weeksJsonArray = (JsonArray) lesson.get("weeks");
                ArrayList<String> weeksData = new ArrayList<>();
                for (int j = 0; j < weeksJsonArray.size(); j++) {
                    weeksData.add(weeksJsonArray.get(j).toString());
                }
                this.weeks.get(semester).add(weeksData);
                assert weeks != null;
            }

        }
    }

    private void checkModuleFormat() throws UnformattedModuleException {
        if (unformattedModules.contains(moduleName)) {
            throw new UnformattedModuleException("OH NO! " + moduleName + "'s format parsed from NUSMOD API is out-dated."
                + " Please remove it from your timetable and manually add the time-slots.");
        }
    }

    public ArrayList<ArrayList<String>> getClassNumber() {
        return this.classNumber;
    }

    public ArrayList<ArrayList<String>> getStartTime() {
        return this.startTime;
    }

    public ArrayList<ArrayList<String>> getEndTime() {
        return this.endTime;
    }

    public ArrayList<ArrayList<String>> getLessonType() {
        return this.lessonType;
    }

    public ArrayList<ArrayList<String>> getDay() {
        return this.day;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getWeeks() {
        return this.weeks;
    }
}
