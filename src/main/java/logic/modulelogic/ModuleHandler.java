package logic.modulelogic;

import common.exception.UnformattedModuleException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Set;

import static common.BlacklistedModules.blacklistModule;
import static common.Messages.MESSAGE_MODULECODE_IN_BLACKLIST;
import static common.Messages.MESSAGE_RETURN_SUCCESS;
import static common.Messages.MESSAGE_EMPTY_MODULE;
/**
 * Contains private members storing the module information.
 * The class members are an ArrayList of size 2(two sem) of ArrayList except member "weeks".
 * Semester 1                       : Semester 2
 * ArrayList of available classes   : ArrayList of available classes
 * <br>
 * Index of the private members matches one another.
 * For eg, classNumber.get(semester).get(0) belongs to lessonType.get(semester).get(0)
 * held on day.get(semester).get(0) at startTime.get(semester).get(0) and endTime.get(semester).get(0)
 * <br>
 * "weeks" is an ArrayList of size 2(two sem) of a 2d ArrayList:
 * For eg, week[0].get(2) = [1, 3, 4, 5], this means
 * semester 1, classNo:2 has lessons in week 1, 3, 4, 5.
 */

public class ModuleHandler {
    JsonArray semesterData;
    private ArrayList<ArrayList<String>> classNumber; // can be repeated.
    private ArrayList<ArrayList<String>> startTime;
    private ArrayList<ArrayList<String>> endTime;
    private ArrayList<ArrayList<String>> lessonType; // tutorial, lecture etc
    private ArrayList<ArrayList<String>> day; // one day per classNumber
    private ArrayList<ArrayList<ArrayList<String>>> weeks;
    private String moduleName;
    private Set<String> unformattedModules;

    public ModuleHandler(String moduleName) {
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
        this.unformattedModules = blacklistModule;
    }

    /**
     * Format the JsonArray object returned from ModuleApiParser.parse into easy to use data structure.
     */
    public String generateModule() {
        ModuleApiParser myModuleApiParser = new ModuleApiParser(moduleName);
        semesterData = myModuleApiParser.parse();
        if (semesterData.size() == 0) {
            //TODO SET UP FAKE DATA HERE IF UNABLE TO ACCESS API
            return MESSAGE_EMPTY_MODULE;
        }
        assert semesterData != null;
        try {
            checkModuleFormat();
        } catch (UnformattedModuleException e) {
            System.out.println(this.moduleName + MESSAGE_MODULECODE_IN_BLACKLIST);
            return MESSAGE_MODULECODE_IN_BLACKLIST;
        }
        for (int i = 0; i < semesterData.size(); i++) {
            JsonObject semesterDataObj = semesterData.get(i).getAsJsonObject();
            // get semester number from json
            Integer semester = Integer.parseInt(semesterDataObj.get("semester").toString()) - 1;
            if (!(semester == 0 || semester == 1)) {
                continue;
            }
            JsonArray timetable = (JsonArray) semesterDataObj.get("timetable");
            for (int k = 0; k < timetable.size(); k++) { // For each classes in a module.
                JsonObject lesson = timetable.get(k).getAsJsonObject();
                moduleInfoAdder(semester, lesson);
            }

        }
        return MESSAGE_RETURN_SUCCESS;
    }


    /**
     * Generates a 2d ArrayList of String module information data structure as follows:
     * classNumber, lessonType, startTime, endTime, day, weeks.
     * To be used within the loop of generateModules().
     */
    private void moduleInfoAdder(Integer semester, JsonObject lesson) {
        // replaceAll() trims the quotes left behind by json parsing via regex
        this.classNumber.get(semester).add(lesson.get("classNo").toString().replaceAll("^.|.$", ""));
        this.lessonType.get(semester).add(lesson.get("lessonType").toString().replaceAll("^.|.$", ""));
        this.startTime.get(semester).add(lesson.get("startTime").toString().replaceAll("^.|.$", ""));
        this.endTime.get(semester).add(lesson.get("endTime").toString().replaceAll("^.|.$", ""));
        this.day.get(semester).add(lesson.get("day").toString().replaceAll("^.|.$", ""));
        JsonArray weeksJsonArray = (JsonArray) lesson.get("weeks");
        ArrayList<String> weeksData = new ArrayList<>();
        for (int j = 0; j < weeksJsonArray.size(); j++) {
            weeksData.add(weeksJsonArray.get(j).toString());
        }
        this.weeks.get(semester).add(weeksData);
    }

    /** Checks if module is within the blacklisted UnformattedModules file.
     * @throws UnformattedModuleException Throws error if user's module is within the file UnformattedModules,
     *                                    which contains all modules which can't be parsed due to ill-formatting by NUSMOD API side.
     *                                    method to update the list of modules in UnformattedModules is in static main()
     */
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
