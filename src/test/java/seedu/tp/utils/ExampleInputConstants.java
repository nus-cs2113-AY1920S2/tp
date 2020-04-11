package seedu.tp.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class ExampleInputConstants {
    public static final String NEWLINE = System.lineSeparator();
    public static final String BULLET_POINT = "- ";

    public static final String FLASHCARD_NAME = "Example flashcard name";
    public static final String GROUP_NAME_1 = "Example flashcard group name 1";
    public static final String GROUP_NAME_2 = "Example flashcard group name 2";
    public static final String UNIONIZED_GROUP_NAME = "blah blah blah";
    public static final String DESCRIPTION = "Example description";

    public static final String START_DATE = "31 07 1843";
    public static final String END_DATE = "25 12 1892";
    public static final LocalDate START_LOCAL_DATE = LocalDate.of(1843, 7, 31);
    public static final LocalDate END_LOCAL_DATE = LocalDate.of(1892, 12, 25);
    public static final String SUMMARY = "Example summary";
    public static final String DETAIL_1 = "Detail 1";
    public static final String DETAIL_2 = "Detail 2";
    public static final List<String> DETAILS = Arrays.asList(DETAIL_1, DETAIL_2);
    public static final String DETAILS_BULLET_FORM = "* " + DETAIL_1 + System.lineSeparator()
        + "* " + DETAIL_2;

    public static final String INDEX_1 = "2";
    public static final String INDEX_2 = "-1";
    public static final String INDEX_3 = "3";
    public static final String INDEX_4 = "1";
    public static final String INDEXES_1 = "1 2";
    public static final String INDEXES_2 = "1 3";
    public static final String INDEXES_3 = "1 2 3";
    public static final String INVALID_INDEXES = "0 -1 5";

    public static final String DATE_1 = "27 02 2020";
    public static final String DATE_2 = "18 01 2020";
    public static final LocalDate LOCAL_DATE_1 = LocalDate.of(2020, 2, 27);
    public static final LocalDate LOCAL_DATE_2 = LocalDate.of(2020, 1, 18);

    public static final String RESPONSE_1 = "YeS";
    public static final String RESPONSE_2 = "yes";
    public static final String RESPONSE_3 = "nO";

    public static final String FULL_SIMULATED_INPUT = FLASHCARD_NAME + NEWLINE
        + START_DATE + NEWLINE
        + END_DATE + NEWLINE
        + SUMMARY + NEWLINE
        + DETAIL_1 + NEWLINE
        + DETAIL_2 + NEWLINE
        + NEWLINE;
    public static final String PARTIAL_SIMULATED_INPUT = FLASHCARD_NAME + NEWLINE
        + SUMMARY + NEWLINE
        + DETAIL_1 + NEWLINE
        + DETAIL_2 + NEWLINE
        + NEWLINE;

    public static final String SIMULATED_GROUP_COMMAND_INPUT_1 = GROUP_NAME_1 + NEWLINE
        + DESCRIPTION + NEWLINE
        + INDEXES_1 + NEWLINE
        + NEWLINE;
    public static final String SIMULATED_GROUP_COMMAND_INPUT_2 = GROUP_NAME_1 + NEWLINE
        + DESCRIPTION + NEWLINE
        + INVALID_INDEXES + NEWLINE
        + NEWLINE;

    public static final String SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_1 = INDEX_1 + NEWLINE
        + GROUP_NAME_1 + NEWLINE
        + NEWLINE;
    public static final String SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_2 = INDEX_2 + NEWLINE
        + GROUP_NAME_1 + NEWLINE
        + NEWLINE;
    public static final String SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_3 = INDEX_3 + NEWLINE
        + INDEX_4 + NEWLINE
        + NEWLINE;
    public static final String SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_4 = INDEX_1 + NEWLINE
        + UNIONIZED_GROUP_NAME + NEWLINE
        + NEWLINE;
    public static final String SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_5 = INDEX_1 + NEWLINE
        + INDEX_2 + NEWLINE
        + NEWLINE;
    public static final String SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_6 = INDEX_1 + NEWLINE
        + INDEX_3 + NEWLINE
        + NEWLINE;

    public static final String SIMULATED_UPDATE_STUDY_PLAN_INPUT_1 =
        DATE_1 + NEWLINE + INDEXES_1 + NEWLINE;
    public static final String SIMULATED_UPDATE_STUDY_PLAN_INPUT_2 =
        DATE_2 + NEWLINE + INDEX_1 + NEWLINE;

    public static final String SIMULATED_DELETE_STUDY_PLAN_INPUT = DATE_1 + NEWLINE;

    public static final String SIMULATED_RANDOM_COMMAND_INPUT = RESPONSE_1 + NEWLINE
        + RESPONSE_3 + NEWLINE
        + RESPONSE_2 + NEWLINE
        + NEWLINE;
}
