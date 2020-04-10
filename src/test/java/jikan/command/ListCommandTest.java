package jikan.command;

import jikan.Jikan;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListCommandTest {

    private static final String DATE_FORMAT_1 = "01/01/2020";
    private static final String DATE_FORMAT_2 = "2020-01-01";
    private static final String DATE_RANGE = "01/01/2020 20/02/2020";
    private static final String DAY_FORMAT_1 = "day";
    private static final String DAY_FORMAT_2 = "daily";
    private static final String WEEK_FORMAT_1 = "week";
    private static final String WEEK_FORMAT_2 = "weekly";

    private static final LocalDateTime START_TIME_1 = LocalDateTime.parse("2020-01-01T08:00:00");
    private static final LocalDateTime END_TIME_1 =  LocalDateTime.parse("2020-01-01T10:00:00");
    private static final LocalDateTime START_TIME_2 = LocalDateTime.parse("2020-01-15T08:00:00");
    private static final LocalDateTime END_TIME_2 =  LocalDateTime.parse("2020-01-15T10:00:00");
    private static final LocalDateTime START_TIME_3 = LocalDateTime.parse("2020-03-01T08:00:00");
    private static final LocalDateTime END_TIME_3 =  LocalDateTime.parse("2020-03-01T10:00:00");


    ActivityList activities = new ActivityList();
    ArrayList<Activity> expected = new ArrayList<>();
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime nextWeek = currentTime.plusWeeks(1);
    Activity activity1;
    Activity activity2;
    Activity activity3;
    Activity activity4;
    Activity activity5;
    Activity activity6;


    void populateActivityList() throws InvalidTimeFrameException, NameTooLongException {
        activities.storage = new Storage("data/activityList_test.txt");
        activities.activities.clear();
        try {
            activities.storage.clearFile();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }

        HashSet<String> tags = new HashSet<>();
        tags.add("tagA");
        tags.add("tagB");

        Duration duration = Duration.between(START_TIME_1, END_TIME_1);
        Duration allocatedTime = Duration.parse("PT0S");
        activity1 = new Activity("subject1 quiz", START_TIME_1, END_TIME_1, duration, tags, allocatedTime);
        activity2 = new Activity("subject2 quiz", START_TIME_1, END_TIME_1, duration, tags, allocatedTime);
        activity3 = new Activity("subject3 final", START_TIME_2, END_TIME_2, duration, tags, allocatedTime);
        activity4 = new Activity("subject4 final", START_TIME_3, END_TIME_3, duration, tags, allocatedTime);
        activity5 = new Activity("subject5 quiz", currentTime, currentTime, duration, tags, allocatedTime);
        activity6 = new Activity("subject6 final", currentTime.plusDays(1),
                currentTime.plusDays(1), duration, tags, allocatedTime);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
        activities.add(activity4);
        activities.add(activity5);
        activities.add(activity6);

    }

    void populateExpectedBasic() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
        expected.add(activity3);
        expected.add(activity4);
        expected.add(activity5);
        expected.add(activity6);
    }

    void populateExpectedSingleDate() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
    }

    void populateExpectedDateRange() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
        expected.add(activity3);
    }

    void populateExpectedDay() {
        expected.clear();
        expected.add(activity5);
    }

    void populateExpectedWeek() {
        expected.clear();
        expected.add(activity5);
        if (activity6.getDate().getDayOfWeek() != DayOfWeek.MONDAY) {
            expected.add(activity6);
        }
    }

    @Test
    void executeCommand() {
        try {
            populateActivityList();

            Command basicTest = new ListCommand(null);
            basicTest.executeCommand(activities);
            populateExpectedBasic();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command dateFormat1Test = new ListCommand(DATE_FORMAT_1);
            dateFormat1Test.executeCommand(activities);
            populateExpectedSingleDate();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command dateFormat2Test = new ListCommand(DATE_FORMAT_2);
            dateFormat2Test.executeCommand(activities);
            populateExpectedSingleDate();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command dateRangeTest = new ListCommand(DATE_RANGE);
            dateRangeTest.executeCommand(activities);
            populateExpectedDateRange();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command dayFormat1Test = new ListCommand(DAY_FORMAT_1);
            dayFormat1Test.executeCommand(activities);
            populateExpectedDay();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command dayFormat2Test = new ListCommand(DAY_FORMAT_2);
            dayFormat2Test.executeCommand(activities);
            populateExpectedDay();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command weekFormat1Test = new ListCommand(WEEK_FORMAT_1);
            weekFormat1Test.executeCommand(activities);
            populateExpectedWeek();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command weekFormat2Test = new ListCommand(WEEK_FORMAT_2);
            weekFormat2Test.executeCommand(activities);
            populateExpectedWeek();
            assertEquals(Jikan.lastShownList.activities, expected);

        } catch (InvalidTimeFrameException | EmptyNameException | ExtraParametersException | NameTooLongException e) {
            System.out.println("Field error.");
        }
    }
}