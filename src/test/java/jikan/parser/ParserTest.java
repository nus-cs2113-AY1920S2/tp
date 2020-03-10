package jikan.parser;

import jikan.activity.ActivityList;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    Parser parser = new Parser();

    ActivityList activityList = new ActivityList();

    @Test
    void parseEnd() {
        parser.startTime = null;
        parser.parseEnd(activityList);
        assertTrue(activityList.activities.isEmpty());
        parser.tokenizedInputs = new String[]{"start", "studying"};
        parser.startTime = LocalDateTime.parse("2020-01-01T08:00:00");
        activityList.storage = new Storage("data/Parser_test.txt");
        parser.parseEnd(activityList);
        assertFalse(activityList.activities.isEmpty());
    }
}