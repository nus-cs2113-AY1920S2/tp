import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import common.LessonType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * myLessonDetails contains an ArrayList of lessons in the form: startTime, endTime, day, weeks(delimited by ':').
 */
public class LessonsGenerator {
    private TimetableParser myTimetableParser;
    private ArrayList<String[]> myLessonsDetails;

    public LessonsGenerator(String nusmodsLink) throws InvalidUrlException {
        this.myTimetableParser = new TimetableParser(nusmodsLink);
        this.myLessonsDetails = new ArrayList<>();
    }

    public static void main(String[] args) throws InvalidUrlException {
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

    public void generate() {
        myTimetableParser.parse();
        //Key-value pair: Key = module code, Value = LessonType:Class number(delimited by :)
        Map<String, ArrayList<String>> userLessons = myTimetableParser.getModulesMap();
        ArrayList<String> userModules = myTimetableParser.getModulesArr();
        // Zero based indexing: Semester 1 = 0, Semester 2 = 1
        Integer semester = Integer.parseInt(myTimetableParser.getSemester()) - 1;
        for (String module : userModules) {
            ModuleHandler myModuleHandler = new ModuleHandler(module);
            myModuleHandler.generateModule();
            // the index of the following ArrayList matches - classNumber[0] and lessonType[0] is the same lesson,
            // and it's startTime, endTime = startTime[0], endTime[0]
            ArrayList<String> classNumber = myModuleHandler.getClassNumber().get(semester);
            ArrayList<String> lessonType = myModuleHandler.getLessonType().get(semester);
            ArrayList<String> startTime = myModuleHandler.getStartTime().get(semester);
            ArrayList<String> endTime = myModuleHandler.getEndTime().get(semester);
            ArrayList<String> day = myModuleHandler.getDay().get(semester);
            ArrayList<ArrayList<String>> weeks = myModuleHandler.getWeeks().get(semester);

            ArrayList<String> delimitedWeeks = delimitWeeks(weeks);
            ArrayList<String> userModuleProfile = userLessons.get(module);
            Multimap<String, String[]> allLessonMap = ArrayListMultimap.create();
            for (int i = 0; i < classNumber.size(); i++) {
                String lessonTypeLongFormat = lessonType.get(i);
                String lessonTypeShortFormat = LessonType.lessonType.get(lessonTypeLongFormat);
                allLessonMap.put(lessonTypeShortFormat + ":"
                    + classNumber.get(i), new String[] {startTime.get(i), endTime.get(i), day.get(i), delimitedWeeks.get(i)});
            }
            lessonsChecker(allLessonMap, userModuleProfile);
        }
    }

    private void lessonsChecker(Multimap<String, String[]> allLessonMap, ArrayList<String> userModuleProfile) {
        System.out.println("USER " + userModuleProfile);
        System.out.println("ALL: ");

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
     * Refactor weeks into 1 single ArrayList from a 2d ArrayList delimited with ':'.
     * @param weeks 2D ArrayList weeks: For eg, weeks.get(0) = weeks at classNo 0 = [1, 2, 3, 6, 13]
     * @return weeks Delimited weeks indexed by each lessons.
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
