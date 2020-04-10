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

import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {
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

        LocalDateTime startTime1 = LocalDateTime.parse("2020-01-01T08:00:00");
        LocalDateTime endTime1 =  LocalDateTime.parse("2020-01-01T10:00:00");
        LocalDateTime startTime2 = LocalDateTime.parse("2020-01-15T08:00:00");
        LocalDateTime endTime2 =  LocalDateTime.parse("2020-01-15T10:00:00");
        LocalDateTime startTime3 = LocalDateTime.parse("2020-03-01T08:00:00");
        LocalDateTime endTime3 =  LocalDateTime.parse("2020-03-01T10:00:00");
        Duration duration = Duration.between(startTime1, endTime1);
        Duration allocatedTime = Duration.parse("PT0S");
        activity1 = new Activity("subject1 quiz", startTime1, endTime1, duration, tags, allocatedTime);
        activity2 = new Activity("subject2 quiz", startTime1, endTime1, duration, tags, allocatedTime);
        activity3 = new Activity("subject3 final", startTime2, endTime2, duration, tags, allocatedTime);
        activity4 = new Activity("subject4 final", startTime3, endTime3, duration, tags, allocatedTime);
        activity5 = new Activity("subject5 quiz", currentTime, currentTime, duration, tags, allocatedTime);
        activity6 = new Activity("subject6 final", currentTime.plusDays(1), currentTime.plusDays(1), duration, tags, allocatedTime);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
        activities.add(activity4);
        activities.add(activity5);
        activities.add(activity6);

    }

    void populateExpected1() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
        expected.add(activity3);
        expected.add(activity4);
        expected.add(activity5);
        expected.add(activity6);
    }

    void populateExpected2() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
    }

    void populateExpected3() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
        expected.add(activity3);
    }

    void populateExpected4() {
        expected.clear();
        expected.add(activity5);
    }

    void populateExpected5() {
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
            String parameters2a = "01/01/2020";
            String parameters2b = "2020-01-01";
            String parameters3 = "01/01/2020 20/02/2020";
            String parameters4a = "day";
            String parameters4b = "daily";
            String parameters5a = "week";
            String parameters5b = "weekly";

            Command command1 = new ListCommand(null);
            command1.executeCommand(activities);
            populateExpected1();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command2a = new ListCommand(parameters2a);
            command2a.executeCommand(activities);
            populateExpected2();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command2b = new ListCommand(parameters2b);
            command2b.executeCommand(activities);
            populateExpected2();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command3 = new ListCommand(parameters3);
            command3.executeCommand(activities);
            populateExpected3();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command4a = new ListCommand(parameters4a);
            command4a.executeCommand(activities);
            populateExpected4();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command4b = new ListCommand(parameters4b);
            command4b.executeCommand(activities);
            populateExpected4();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command5a = new ListCommand(parameters5a);
            command5a.executeCommand(activities);
            populateExpected5();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command5b = new ListCommand(parameters5b);
            command5b.executeCommand(activities);
            populateExpected5();
            assertEquals(Jikan.lastShownList.activities, expected);

        } catch (InvalidTimeFrameException | EmptyNameException | ExtraParametersException | NameTooLongException e) {
            System.out.println("Field error.");
        }
    }
}