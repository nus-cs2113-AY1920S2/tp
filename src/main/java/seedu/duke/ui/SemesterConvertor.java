package seedu.duke.ui;

import seedu.duke.data.Person;

public class SemesterConvertor {
    public static String convertSemToStandardFormat(String semester) {
        String standardSemFormat;
        int year = Person.getMatricYear() + (Integer.parseInt(semester) - 1) / 2;
        int sem = (Integer.parseInt(semester) + 1) % 2 + 1;
        standardSemFormat = year + "/" + (year + 1) + " SEM" + sem;
        return standardSemFormat;
    }
}
