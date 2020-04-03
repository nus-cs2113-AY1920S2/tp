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

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContinueCommandTest {
    Storage storage = new Storage("data/activityList_test.txt");
    ActivityList activities = new ActivityList(storage);
    HashSet<String> tags = new HashSet<>();

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
    void executeContinue() throws InterruptedException {
        try {
            populateActivityList();
            String parameters = "Activity2";
            Command command = new ContinueCommand(parameters);
            command.executeCommand(activities);

            LocalDateTime startTime = LocalDateTime.now();
            assertEquals(startTime.getMinute(), Parser.startTime.getMinute());
            final Duration initial = activities.get(1).getDuration();
            Thread.sleep(2000);
            // End Activity2
            command = new EndCommand(null);
            command.executeCommand(activities);
            Duration elapsed = initial.plus(Duration.between(startTime, LocalDateTime.now()));
            Duration duration = activities.get(1).getDuration();
            assertEquals(elapsed.toMinutes(), duration.toMinutes());
        } catch (EmptyNameException | InvalidTimeFrameException | ExtraParametersException e) {
            System.out.println("Error.");
        } catch (NameTooLongException e) {
            Log.makeInfoLog("Activity name longer than 25 characters");
            Ui.printDivider("Error: activity name is longer than 25 characters.");
        }
    }

}