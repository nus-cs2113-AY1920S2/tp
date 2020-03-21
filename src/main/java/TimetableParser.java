import static common.Messages.MESSAGE_INVALID_NUDMOSLINK;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimetableParser {
    String nusmodsLink;
    String semester;
    //Key-value pair: Key = module code, Value = LessonType:Class number <-- delimited by :
    Map<String, ArrayList<String>> modulesMap;
    ArrayList<String> modulesArr;

    public TimetableParser(String nusmodsLink) throws InvalidUrlException {
        this.nusmodsLink = nusmodsLink;
        if (!this.nusmodsLink.matches("https://nusmods\\.com/timetable/sem-./share\\?.*")) {
            throw new InvalidUrlException(MESSAGE_INVALID_NUDMOSLINK);
        }
        this.modulesArr = new ArrayList<>();
    }

    public static void main(String[] args) throws InvalidUrlException {
        TimetableParser myTimetableParser = new TimetableParser("https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:03,PTUT:03&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2107=TUT:09,LEC:1&CS2113T=LEC:C01");
        myTimetableParser.parse();
        System.out.println(myTimetableParser.getSemester());
        System.out.println(myTimetableParser.getModulesArr());
        System.out.println(myTimetableParser.getModulesMap());
    }

    public void parse() {
        int strippedIndex = this.nusmodsLink.indexOf("share?");
        String unparsedSemester = this.nusmodsLink.substring(0, strippedIndex);
        String unparsedModules = this.nusmodsLink.substring(strippedIndex + 6);
        if (unparsedSemester.contains("1")) {
            this.semester = "1";
        } else {
            this.semester = "2";
        }
        this.modulesMap = parseModules(unparsedModules);

    }

    private Map<String, ArrayList<String>> parseModules(String unparsedModules) {
        ArrayList<String> unparsed = new ArrayList<>(Arrays.asList(unparsedModules.split("&")));
        Map<String, ArrayList<String>> myModuleDetails = new HashMap<String, ArrayList<String>>();

        for (String elem : unparsed) {
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
