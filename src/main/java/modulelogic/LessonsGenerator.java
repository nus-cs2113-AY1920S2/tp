package modulelogic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import common.LessonType;
import exception.InvalidUrlException;
import exception.UnformattedModuleException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * This class retrieves information from both TimetableParser and ModuleHandler classes to generate a data structure
 * containing all the modules a user is taking and is to be used by the schedulelogic component.
 * The data structure used is an ArrayList<String[4]> called myLessonDetails and
 * it contains an ArrayList of lessons in the form: startTime, endTime, day, weeks(delimited by ':').
 */
public class LessonsGenerator {
    private TimetableParser myTimetableParser;
    private ArrayList<String[]> myLessonsDetails;

    public LessonsGenerator(String nusmodsLink) throws InvalidUrlException {
        this.myTimetableParser = new TimetableParser(nusmodsLink);
        this.myLessonsDetails = new ArrayList<>();
    }


    //static main method for easy in-class behaviour testing
    public static void main(String[] args) throws InvalidUrlException, IOException, UnformattedModuleException {
        //observe behaviour by substituting field in BackendAPI.LessonsGenerator() with other NUSMODS link
        LessonsGenerator mylesson = new LessonsGenerator("https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:03,PTUT:03&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2107=TUT:09,LEC:1&CS2113T=LEC:C01");
        mylesson.generate();
        ArrayList<String[]> myLessonDetails = mylesson.getLessonDetails();
        for (int i = 0; i < myLessonDetails.size(); i++) {
            for (int j = 0; j < myLessonDetails.get(i).length; j++) {
                System.out.print(myLessonDetails.get(i)[j] + " ");
            }
            System.out.print("\n");
        }
    }


    /**
     * Generates user's blocked time-slots based on his/her Nusmods timetable.
     * @throws FileNotFoundException throws error if file UnformattedModules doesn't exist in source
     * @throws UnformattedModuleException throws error if module requested is in UnformattedModules.
     */
    public void generate() throws FileNotFoundException, UnformattedModuleException {
        myTimetableParser.parse();
        //Key-value pair: Key = module code, Value = LessonType:Class number(delimited by :)
        Map<String, ArrayList<String>> userLessons = myTimetableParser.getModulesMap();
        ArrayList<String> userModules = myTimetableParser.getModulesArr();
        // Zero based indexing: Semester 1 = 0, Semester 2 = 1
        Integer semester = Integer.parseInt(myTimetableParser.getSemester()) - 1;
        for (String module : userModules) {
            ModuleHandler myModuleHandler = new ModuleHandler(module);
            myModuleHandler.generateModule();
            // The index of the following ArrayList matches - classNumber[0] and lessonType[0] is the same lesson,
            // and it's startTime, endTime = startTime[0], endTime[0]
            ArrayList<String> classNumber = myModuleHandler.getClassNumber().get(semester);
            ArrayList<String> lessonType = myModuleHandler.getLessonType().get(semester);
            ArrayList<String> startTime = myModuleHandler.getStartTime().get(semester);
            ArrayList<String> endTime = myModuleHandler.getEndTime().get(semester);
            ArrayList<String> day = myModuleHandler.getDay().get(semester);
            ArrayList<ArrayList<String>> weeks = myModuleHandler.getWeeks().get(semester);
            ArrayList<String> delimitedWeeks = delimitWeeks(weeks);

            Multimap<String, String[]> allLessonMap = ArrayListMultimap.create();
            for (int i = 0; i < classNumber.size(); i++) {
                String lessonTypeLongFormat = lessonType.get(i);
                String lessonTypeShortFormat = LessonType.lessonType.get(lessonTypeLongFormat);
                allLessonMap.put(lessonTypeShortFormat + ":"
                        + classNumber.get(i), new String[]{startTime.get(i), endTime.get(i), day.get(i), delimitedWeeks.get(i)});
            }
            ArrayList<String> userModuleProfile = userLessons.get(module);
            lessonsChecker(allLessonMap, userModuleProfile);
        }
    }

    /**
     * Checks if "lessonType:classNo" from userModuleProfile matches Multimap's key. If it does,
     * add the matched value pair containing an array(size 4) of startTime, endTime, day and weeks into mylessonsDetails.
     *
     * @param allLessonMap      All lesson information where Key equals "lessonType:classNo".
     * @param userModuleProfile ArrayList of "lessonType:classNo" that user has taken for a particular module,
     *                          used to do key matching.
     */
    private void lessonsChecker(Multimap<String, String[]> allLessonMap, ArrayList<String> userModuleProfile) {
        for (String s : userModuleProfile) {
            if (allLessonMap.containsKey(s)) {
                Collection<String[]> values = allLessonMap.get(s);
                for (String[] elem : values) {
                    myLessonsDetails.add(elem);
                }
            }
        }

    }

    /**
     * Refactor "weeks" data structure into 1 single ArrayList, originally a 2D ArrayList.
     *
     * @param weeks 2D ArrayList weeks: For eg, weeks.get(0) = weeks at classNo 0 = array of [1, 2, 3, 6, 13].
     * @return Delimited weeks indexed by each lessons,
     * For eg, weeks.get(0) is now a String = "1:2:3:6:13"
     */
    private ArrayList<String> delimitWeeks(ArrayList<ArrayList<String>> weeks) {
        ArrayList<String> delimitedWeeks = new ArrayList<>();
        for (ArrayList<String> elemArr : weeks) {
            String delimitedString = "";
            for (int i = 0; i < elemArr.size() - 1; i++) {
                delimitedString = delimitedString + elemArr.get(i) + ":";
            }
            delimitedString += elemArr.get(elemArr.size() - 1);
            delimitedWeeks.add(delimitedString);
        }
        return delimitedWeeks;
    }

    public ArrayList<String[]> getLessonDetails() {
        return this.myLessonsDetails;
    }
}
