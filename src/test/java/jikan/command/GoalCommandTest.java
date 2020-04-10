package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.log.Log;
import jikan.storage.Storage;
import jikan.storage.StorageHandler;
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

import static org.junit.jupiter.api.Assertions.*;

class GoalCommandTest {
    Storage storage = new Storage("data/activityList_test.txt");
    ActivityList activities = new ActivityList(storage);
    HashSet<String> tags = new HashSet<>();
    Storage tagStorage = new Storage("data/tag_test.txt");
    private static final String TAG_TEST_FILEPATH = "data/tag_test.txt";
    StorageHandler tagStorageHandler = new StorageHandler(tagStorage);
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

    /**
     * Check that tag exists in the tag list.
     * @param tagName the tag name.
     * @return index the index of the tag in the tag list.
     * @throws IOException when there is an error loading/creating the file.
     */
    public static int checkIfExists(String tagName, String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int index = 0;
        int status = 0;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String[] name;
            while (line != null) {
                name = line.split(",");
                if (name[0].equals(tagName)) {
                    status = 1;
                    break;
                }
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
                index++;
            }
        } finally {
            br.close();
        }
        if (status == 0) {
            index = -1;
        }
        return index;
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
        boolean found = false;
        Command command = new GoalCommand(parameters, scanner, tagStorage);
        try {
            command.executeCommand(activities);
            if (checkIfExists(tagName, TAG_TEST_FILEPATH) != -1) {
                found = true;
            } else {
                found = false;
            }
            assertTrue(found);
        } catch (EmptyNameException | ExtraParametersException e) {
            System.out.println("Field error.");
        }
    }
}