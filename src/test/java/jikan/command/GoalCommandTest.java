package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.log.Log;
import jikan.storage.Storage;
import jikan.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoalCommandTest {
    Storage storage = new Storage("data/activityList_test.txt");
    ActivityList activities = new ActivityList(storage);
    HashSet<String> tags = new HashSet<>();
    Scanner scanner;

    void populateActivityList() throws InvalidTimeFrameException, NameTooLongException {
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

    @Test
    void executeGoal() throws IOException {
        try {
            populateActivityList();
        } catch (InvalidTimeFrameException e) {
            System.out.println("Invalid time frame.");
        } catch (NameTooLongException e) {
            Log.makeInfoLog("Activity name longer than 25 characters");
            Ui.printDivider("Error: activity name is longer than 25 characters.");
        }
        String parameters = "tag1 /g 10:10:10";
        String tagName = "tag1";
        Storage tagStorage = new Storage("data/tag_test.txt");
        String testFile = "data/tag_test.txt";
        tagStorage.loadFile();
        assertTrue(tagStorage.dataFile.exists());
        boolean found = false;
        Command command = new GoalCommand(parameters, scanner, tagStorage);
        try {
            command.executeCommand(activities);
            if (GoalCommand.checkIfExists(tagName, testFile) == -1) {
                found = false;
                assertFalse(found);
                tagStorage.dataFile.delete();
            } else {
                found = true;
                assertTrue(found);
                tagStorage.dataFile.delete();
            }
        } catch (EmptyNameException | ExtraParametersException e) {
            System.out.println("Field error.");
        }
    }
}