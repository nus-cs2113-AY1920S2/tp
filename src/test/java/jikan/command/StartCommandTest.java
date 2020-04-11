package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.log.Log;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StartCommandTest {
    ActivityList activities = new ActivityList();
    HashSet<String> tags = new HashSet<>();

    void populateActivityList() throws InvalidTimeFrameException, NameTooLongException {
        activities.storage = new Storage("data/activityList_test.txt");
        activities.activities.clear();
        try {
            activities.storage.clearFile();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
        tags.add("tag1");
        tags.add("tag2");
        LocalDateTime startTime = LocalDateTime.parse("2020-01-01T08:00:00");
        LocalDateTime endTime =  LocalDateTime.parse("2020-01-01T10:00:00");
        Duration duration = Duration.between(startTime, endTime);
        Duration allocatedTime = Duration.parse("PT0S");
        Activity activity1 = new Activity("Activity1", startTime, endTime, duration, tags, allocatedTime);
        Activity activity2 = new Activity("Activity2", startTime, endTime, duration, tags, allocatedTime);
        Activity activity3 = new Activity("Activity3", startTime, endTime, duration, tags, allocatedTime);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
    }

    private void resetFields() {
        Parser.startTime = null;
        Parser.tags.clear();
    }

    @Test
    void executeStart() {
        try {
            populateActivityList();
            Scanner scanner = new Scanner(System.in);
            String parameters = "Activity 3 /t tag tag1";
            Command command = new StartCommand(parameters, scanner);

            HashSet<String> activity3Tags = new HashSet<>();
            activity3Tags.add("tag");
            activity3Tags.add("tag1");

            command.executeCommand(activities);
            assertNotNull(Parser.startTime);
            assertEquals(Parser.activityName, "Activity 3");
            assertEquals(activity3Tags, Parser.tags);

            resetFields();
            // end started activity to test continue feature
            command = new EndCommand(null);
            command.executeCommand(activities);
        } catch (EmptyNameException | InvalidTimeFrameException e) {
            System.out.println("Field error.");
        } catch (NameTooLongException e) {
            Log.makeInfoLog("Activity name longer than 25 characters");
            Ui.printDivider("Error: activity name is longer than 25 characters.");
        } catch (ExtraParametersException e) {
            Ui.printDivider("Field error.");
        }
    }

    @Test
    void executeStartContinued() {
        try {
            populateActivityList();
            String data = "Yes";
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            String parameters = "Activity1";
            Command command = new StartCommand(parameters, scanner);
            System.out.println(Parser.tags);
            command.executeCommand(activities);
            assertEquals(Parser.activityName, "Activity1");
            assertNotNull(Parser.startTime);
        } catch (InvalidTimeFrameException | EmptyNameException | ExtraParametersException e) {
            System.out.println("Field error.");
        } catch (NameTooLongException e) {
            Log.makeInfoLog("Activity name longer than 25 characters");
            Ui.printDivider("Error: activity name is longer than 25 characters.");
        }
        resetFields();
    }

    @Test
    void executeStartNotContinued() {
        try {
            populateActivityList();
            String data = "No";
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            String parameters = "Activity1";
            Command command = new StartCommand(parameters, scanner);
            command.executeCommand(activities);
            assertNull(Parser.startTime);
            assertNull(Parser.activityName);
        } catch (InvalidTimeFrameException | EmptyNameException | ExtraParametersException
                | NameTooLongException e) {
            System.out.println("Field error.");
        }
        resetFields();
    }
}