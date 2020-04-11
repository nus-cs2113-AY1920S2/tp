package jikan.command;

import jikan.Jikan;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.exception.MultipleDelimitersException;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FilterCommandTest {

    private static final String BASIC_TEST = "tagA";
    private static final String NULL_RESULTS_TEST = "tagZ";
    private static final String MULTI_KEYWORD_TEST = "tagA tagC tagD";
    private static final String FIND_CHAINING_TEST = "subject2 / subject3";
    private static final String CHAINING_TEST = "-s tagA tagB";

    ActivityList activities = new ActivityList();
    HashSet<String> tags1 = new HashSet<>();
    HashSet<String> tags2 = new HashSet<>();
    ArrayList<Activity> expected = new ArrayList<>();
    Activity activity1;
    Activity activity2;
    Activity activity3;

    void populateActivityList() throws InvalidTimeFrameException, NameTooLongException {
        activities.storage = new Storage("data/activityList_test.txt");
        activities.activities.clear();
        try {
            activities.storage.clearFile();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
        tags1.add("tagA");
        tags1.add("tagB");
        tags2.add("tagC");
        tags2.add("tagD");
        LocalDateTime startTime = LocalDateTime.parse("2020-01-01T08:00:00");
        LocalDateTime endTime =  LocalDateTime.parse("2020-01-01T10:00:00");
        Duration duration = Duration.between(startTime, endTime);
        Duration allocatedTime = Duration.parse("PT0S");
        activity1 = new Activity("subject1 quiz", startTime, endTime, duration, tags1, allocatedTime);
        activity2 = new Activity("subject2 quiz", startTime, endTime, duration, tags1, allocatedTime);
        activity3 = new Activity("subject3 final", startTime, endTime, duration, tags2, allocatedTime);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
    }

    void populateExpectedBasic() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
    }

    void populateExpectedNull() {
        expected.clear();
    }

    void populateExpectedMultiKeyword() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
        expected.add(activity3);
    }

    void populateExpectedChaining() {
        expected.clear();
        expected.add(activity2);
    }

    @Test
    void executeCommand() {
        try {
            populateActivityList();

            Command basicTest = new FilterCommand(BASIC_TEST);
            basicTest.executeCommand(activities);
            populateExpectedBasic();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command nullResultsTest = new FilterCommand(NULL_RESULTS_TEST);
            nullResultsTest.executeCommand(activities);
            populateExpectedNull();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command multiKeywordTest = new FilterCommand(MULTI_KEYWORD_TEST);
            multiKeywordTest.executeCommand(activities);
            populateExpectedMultiKeyword();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command find = new FindCommand(FIND_CHAINING_TEST);
            Command chainingTest = new FilterCommand(CHAINING_TEST);
            find.executeCommand(activities);
            chainingTest.executeCommand(activities);
            populateExpectedChaining();
            assertEquals(Jikan.lastShownList.activities, expected);

        } catch (InvalidTimeFrameException | EmptyNameException | ExtraParametersException | NameTooLongException e) {
            System.out.println("Field error.");
        } catch (MultipleDelimitersException e) {
            System.out.println("Multiple Delimiters");
        }
    }
}