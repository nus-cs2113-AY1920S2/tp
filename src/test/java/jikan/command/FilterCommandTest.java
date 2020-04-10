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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FilterCommandTest {
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

    void populateExpected1() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
    }

    void populateExpected2() {
        expected.clear();
        expected.add(activity3);
    }

    void populateExpected3() {
        expected.clear();
        expected.add(activity1);
        expected.add(activity2);
        expected.add(activity3);
    }

    void populateExpected4() {
        expected.clear();
        expected.add(activity2);
    }

    @Test
    void executeCommand() {
        try {
            populateActivityList();
            String parameters1 = "tagA";
            String parameters2 = "tagC";
            String parameters3 = "tagA tagC tagD";
            String findParameters = "subject2 / subject3";
            String parameters4 = "tag A tag B";

            Command command1 = new FilterCommand(parameters1);
            command1.executeCommand(activities);
            populateExpected1();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command2 = new FilterCommand(parameters2);
            command2.executeCommand(activities);
            populateExpected2();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command command3 = new FilterCommand(parameters3);
            command3.executeCommand(activities);
            populateExpected3();
            assertEquals(Jikan.lastShownList.activities, expected);

            Command find = new FindCommand(findParameters);
            Command command4 = new FilterCommand(parameters4);
            find.executeCommand(activities);
            command4.executeCommand(activities);
            populateExpected4();
            assertEquals(Jikan.lastShownList.activities, expected);

        } catch (InvalidTimeFrameException | EmptyNameException | ExtraParametersException | NameTooLongException e) {
            System.out.println("Field error.");
        }
    }
}