package common;

import java.util.HashMap;

/**
 * Maps the lessonType full name into its coded form to used by NUSMODS link.
 * Referenced from https://github.com/raynoldng/nusmods-planner/blob/master/nusmodsplanner/definitions.py
 */
public class LessonType {
    public static final HashMap<String, String> lessonType = new HashMap<>();

    static {
        lessonType.put("Packaged Lecture", "PLEC");
        lessonType.put("Packaged Tutorial", "PTUT");
        lessonType.put("Design Lecture", "DLEC");
        lessonType.put("Laboratory", "LAB");
        lessonType.put("Lecture", "LEC");
        lessonType.put("Recitation", "REC");
        lessonType.put("Sectional Teaching", "SEC");
        lessonType.put("Seminar-style Module Class", "SEM");
        lessonType.put("Tutorial", "TUT");
        lessonType.put("Tutorial Type 2", "TUT2");
    }
}
