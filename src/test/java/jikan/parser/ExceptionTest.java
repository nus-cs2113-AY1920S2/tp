package jikan.parser;

import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.NoSuchActivityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class ExceptionTest {

    Parser parser = new Parser();
    Scanner scanner = new Scanner(System.in);
    ActivityList activityList = new ActivityList();

    @Test
    public void testEmptyNameException() {
        Assertions.assertThrows(EmptyNameException.class, () -> {
            parser.tokenizedInputs = new String[]{"start", ""};
            parser.parseStart(activityList, scanner);
        });
    }

    @Test
    public void testNoSuchActivityException() {
        Assertions.assertThrows(NoSuchActivityException.class, () -> {
            parser.startTime = null;
            parser.parseEnd(activityList);
            parser.parseAbort();
        });

    }


}


