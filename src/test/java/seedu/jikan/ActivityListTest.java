package seedu.jikan;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityListTest {

    @Test
    void getIndex() {
        ActivityList activities = new ActivityList();
        String[] tags = {"tag1", "tag2"};
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
    }
}