package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

import static jikan.Jikan.lastShownList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphCommandTest {
    ActivityList activities = new ActivityList();
    HashSet<String> tags1 = new HashSet<>();
    HashSet<String> tags2 = new HashSet<>();

    void populateActivityList() throws InvalidTimeFrameException, NameTooLongException {
        activities.storage = new Storage("data/activityList_test.txt");
        activities.activities.clear();
        try {
            activities.storage.clearFile();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
        tags1.add("tag1");
        tags1.add("tag2");
        tags2.add("tag1");
        tags2.add("tag3");
        LocalDateTime startTime = LocalDateTime.parse("2020-01-01T08:00:00");
        LocalDateTime endTime =  LocalDateTime.parse("2020-01-01T10:00:00");
        Duration duration = Duration.between(startTime, endTime);
        Duration allocatedTime = Duration.parse("PT2H30M");
        Activity activity1 = new Activity("Activity1", startTime, endTime, duration, tags1, allocatedTime);
        Activity activity2 = new Activity("Activity2", startTime, endTime, duration, tags1, allocatedTime);
        Activity activity3 = new Activity("Activity3", startTime, endTime, duration, tags2, allocatedTime);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
    }

    @Test
    void newCommand() {
        ExtraParametersException extraParametersException = assertThrows(ExtraParametersException.class, () -> {
            Command command = new GraphCommand("tags 10 extra");
        });
    }

    public void printTest() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // After this all System.out.println() statements will come to outContent stream.

        // So, you can normally call,
        // print(items); // I will assume items is already initialized properly.

        //Now you have to validate the output. Let's say items had 1 element.
        // With name as FirstElement and number as 1.
        // String expectedOutput  = "Name: FirstElement\nNumber: 1" // Notice the \n for new line.

        // Do the actual assertion.
        // assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void extractTags() {
        HashMap<String, Duration> expected = new HashMap<>();
        expected.put("tag1", Duration.parse("PT6H"));
        expected.put("tag2", Duration.parse("PT4H"));
        expected.put("tag3", Duration.parse("PT2H"));
        try {
            populateActivityList();
            HashMap<String, Duration> tags = new HashMap<>();
            for (Activity activity : activities.activities) {
                GraphCommand.extractTags(tags, activity);
            }
            assertEquals(expected, tags);
        } catch (NameTooLongException | InvalidTimeFrameException e) {
            e.printStackTrace();
        }
    }
}