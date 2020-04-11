package seedu.parser;

import seedu.exception.PacException;

public class CalendarParser {
    private static final String SEMESTER_FLAG = "s/";
    private static final String ACADEMIC_YEAR_FLAG = "ay/";
    private static final int SEMESTER_AND_AY = 2;
    private static final int CHARACTERS_IN_SEMESTER = 3;
    private static final int CHARACTERS_IN_AY = 8;
    private static final String INPUT_ERROR_MESSAGE = "Please provide a valid semester and academic year in this format"
            + ": s/1 ay/19-20.\n    * Note that each academic year should be provided as double digits.";
    private static final String INVALID_FLAG_MESSAGE = "Unknown flag. Only s/ and ay/ flags are recognizable.";
    private static final String INVALID_YEAR_NUMBER_FORMAT_MESSAGE = "Please provide consecutive years for ay, eg, "
            + "ay/19-20";


    public CalendarParser() {
    }

    private String[] parseDescription(String parameters) throws PacException {
        String[] tokens = parameters.split(" ");
        if (tokens.length != SEMESTER_AND_AY || tokens[0].length() != CHARACTERS_IN_SEMESTER
                || tokens[1].length() != CHARACTERS_IN_AY) {
            throw new PacException(INPUT_ERROR_MESSAGE);
        }
        return tokens;
    }

    private int parseAcademicYear(String[] academicYear, int semester) throws PacException {
        int calendarYear;
        for (String yr : academicYear) {
            if (yr.length() > 2 || yr.length() < 1) {
                throw new PacException(INPUT_ERROR_MESSAGE);
            }
        }
        try {
            if (semester == 1) {
                calendarYear = Integer.parseInt(academicYear[0]);
            } else {
                calendarYear = Integer.parseInt(academicYear[1]);
            }
        } catch (NumberFormatException e) {
            throw new PacException(INPUT_ERROR_MESSAGE);
        }
        return calendarYear;
    }


    public int getSemester(String description) throws PacException {
        String[] tokens = parseDescription(description);
        int semester;
        if (tokens[0].substring(0,2).equals(SEMESTER_FLAG)) {
            try {
                semester = Integer.parseInt(tokens[0].substring(2));
            } catch (NumberFormatException e) {
                throw new PacException(INPUT_ERROR_MESSAGE);
            }
        } else {
            throw new PacException(INVALID_FLAG_MESSAGE);
        }
        return semester;
    }

    public int getYear(String description, int semester) throws PacException {
        String[] tokens = parseDescription(description);
        int calendarYear;
        if (tokens[1].substring(0,3).equals(ACADEMIC_YEAR_FLAG)) {
            String[] academicYear = tokens[1].substring(3).split("-");
            try {
                if (academicYear.length != 2) {
                    throw new PacException(INPUT_ERROR_MESSAGE);
                } else if (Integer.parseInt(academicYear[1]) - Integer.parseInt(academicYear[0]) != 1) {
                    throw new PacException(INVALID_YEAR_NUMBER_FORMAT_MESSAGE);
                }
            } catch (NumberFormatException e) {
                throw new PacException(INPUT_ERROR_MESSAGE);
            }
            calendarYear = parseAcademicYear(academicYear, semester);
        } else {
            throw new PacException(INVALID_FLAG_MESSAGE);
        }
        return calendarYear;
    }
}
