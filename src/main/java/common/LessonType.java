package common;

import java.util.Map;

/**
 * Referenced from https://github.com/raynoldng/nusmods-planner/blob/master/nusmodsplanner/definitions.py
 */
public class LessonType {
    public static final Map<String, String> lessonType = Map.ofEntries(
        Map.entry("Packaged Lecture", "PLEC"),
        Map.entry("Packaged Tutorial", "PTUT"),
        Map.entry("Design Lecture", "DLEC"),
        Map.entry("Laboratory", "LAB"),
        Map.entry("Lecture", "LEC"),
        Map.entry("Recitation", "REC"),
        Map.entry("Sectional Teaching", "SEC"),
        Map.entry("Seminar-style Module Class", "SEM"),
        Map.entry("Tutorial", "TUT"),
        Map.entry("Tutorial Type 2", "TUT2")
    );
}
