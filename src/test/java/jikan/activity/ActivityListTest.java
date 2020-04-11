package jikan.activity;

import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityListTest {

    @Test
    void getIndex() throws InvalidTimeFrameException, NameTooLongException {
        ActivityList activities = new ActivityList();
        activities.storage = new Storage("data/activityList_test.txt");
        HashSet<String> tags = new HashSet<String>();
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

        assertEquals(activity2, activities.get(1));

        activities.storage.dataFile.delete();
    }
}