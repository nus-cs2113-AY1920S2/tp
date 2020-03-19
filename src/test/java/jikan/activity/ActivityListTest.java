package jikan.activity;

import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityListTest {

    @Test
    void getIndex() {
        ActivityList activities = new ActivityList();
        activities.storage = new Storage("data/activityList_test.txt");
        HashSet<String> tags = new HashSet<String>();
        tags.add("tag1");
        tags.add("tag2");
        Activity activity1 = new Activity("Activity1", LocalDateTime.parse("2020-01-01T08:00:00"),
                LocalDateTime.parse("2020-01-01T10:00:00"), tags);
        Activity activity2 = new Activity("Activity2", LocalDateTime.parse("2020-01-01T10:00:00"),
                LocalDateTime.parse("2020-01-01T11:00:00"), tags);
        Activity activity3 = new Activity("Activity3", LocalDateTime.parse("2020-01-01T05:00:00"),
                LocalDateTime.parse("2020-01-01T07:00:00"), tags);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);

        assertEquals(activity2, activities.get(1));

        activities.storage.dataFile.delete();
    }
}