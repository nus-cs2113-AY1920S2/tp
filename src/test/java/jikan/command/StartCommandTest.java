package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.InvalidTimeFrameException;
import jikan.parser.Parser;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StartCommandTest {
    ActivityList activities = new ActivityList();
    HashSet<String> tags = new HashSet<>();

    void populateActivityList() throws InvalidTimeFrameException {
        activities.storage = new Storage("data/activityList_test.txt");
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
        Activity activity1 = new Activity("Activity1", startTime, endTime, duration, tags);
        Activity activity2 = new Activity("Activity2", startTime, endTime, duration, tags);
        Activity activity3 = new Activity("Activity3", startTime, endTime, duration, tags);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
    }

    @Test
    void executeStart() {
        try {
            populateActivityList();
        } catch (InvalidTimeFrameException e) {
            System.out.println("Invalid time frame.");
        }
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
    }

    @Test
    void executeStartContinued() {
        try {
            populateActivityList();
        } catch (InvalidTimeFrameException e) {
            System.out.println("Invalid time frame.");
        }
        String data = "Yes";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String parameters = "Activity1";
        Command command = new StartCommand(parameters, scanner);
        command.executeCommand(activities);
        assertEquals(Parser.activityName, "Activity1");
        assertNotNull(Parser.startTime);
    }

    @Test
    void executeStartNotContinued() {
        try {
            populateActivityList();
        } catch (InvalidTimeFrameException e) {
            System.out.println("Invalid time frame.");
        }
        String data = "No";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String parameters = "Activity1";
        Command command = new StartCommand(parameters, scanner);
        command.executeCommand(activities);
        assertNull(Parser.startTime);
        assertNull(Parser.activityName);
    }
}