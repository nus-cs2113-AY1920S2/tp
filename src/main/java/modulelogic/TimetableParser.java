package modulelogic;

import exception.InvalidUrlException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static common.Messages.MESSAGE_INVALID_NUSMODLINK;
import static common.Messages.MESSAGE_RETURN_SUCCESS;

/**
 * This class contains essential methods to filter out the modules and the timeslots the user
 * is taking from the NUSMOD link.
 */
public class TimetableParser {
    String nusmodsLink;
    String semester;
    Map<String, ArrayList<String>> modulesMap;
    ArrayList<String> modulesArr;

    public TimetableParser(String nusmodsLink) throws InvalidUrlException {
        this.nusmodsLink = nusmodsLink;
        if (!this.nusmodsLink.matches("https://nusmods\\.com/timetable/sem-./share\\?.*")) {
            throw new InvalidUrlException(MESSAGE_INVALID_NUSMODLINK);
        }
        this.modulesArr = new ArrayList<>();
    }


    //static main() method for easy in-class behaviour testing
    public static void main(String[] args) throws InvalidUrlException {
        //observe behaviour by substituting field in BackendAPI.LessonsGenerator() with other NUSMODS link
        TimetableParser myTimetableParser = new TimetableParser("https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:03,PTUT:03&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2107=TUT:09,LEC:1&CS2113T=LEC:C01");
        myTimetableParser.parse();
        System.out.println(myTimetableParser.getSemester());
        System.out.println(myTimetableParser.getModulesArr());
        System.out.println(myTimetableParser.getModulesMap());
    }


    public String parse() {
        assert nusmodsLink.contains("share?");
        int strippedIndex = this.nusmodsLink.indexOf("share?");
        String unparsedSemester = this.nusmodsLink.substring(0, strippedIndex);
        String unparsedModules = this.nusmodsLink.substring(strippedIndex + 6);
        assert nusmodsLink.contains("sem-1") || nusmodsLink.contains("sem-2");
        if (unparsedSemester.contains("1")) {
            this.semester = "1";
        } else {
            this.semester = "2";
        }
        this.modulesMap = parseModules(unparsedModules);

        return MESSAGE_RETURN_SUCCESS;
    }

    /**
     * Filter the time-slots and module from weblink and returns a hashMap data structure,
     * and stores modules user is taking(modulesArr) to be used by LessonGenerator class.
     *
     * @param unparsedModules Raw website link with module information.
     * @return  Returns Key-value pair. Key = "moduleCode"-Value = ArrayList of "LessonType:Class number"
     */
    private Map<String, ArrayList<String>> parseModules(String unparsedModules) {
        assert  unparsedModules != null;
        ArrayList<String> unparsed = new ArrayList<>(Arrays.asList(unparsedModules.split("&")));
        Map<String, ArrayList<String>> myModuleDetails = new HashMap<String, ArrayList<String>>();

        for (String elem : unparsed) {
            assert elem != null;
            int tempIndex = elem.indexOf("=");
            String module = elem.substring(0, tempIndex);
            String unparsedLessonTypeAndClass = elem.substring(tempIndex + 1);
            ArrayList<String> parsedLessonTypeAndClass = new ArrayList<>(Arrays.asList(unparsedLessonTypeAndClass.split(",")));
            myModuleDetails.put(module, parsedLessonTypeAndClass);
            modulesArr.add(module);
        }
        return myModuleDetails;
    }

    public Map<String, ArrayList<String>> getModulesMap() {
        return this.modulesMap;
    }

    public String getSemester() {
        return this.semester;
    }

    public ArrayList<String> getModulesArr() {
        return this.modulesArr;
    }
}
