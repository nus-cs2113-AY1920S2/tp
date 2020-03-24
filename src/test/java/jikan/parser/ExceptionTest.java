package jikan.parser;

import jikan.activity.ActivityList;
import jikan.command.AbortCommand;
import jikan.command.Command;
import jikan.command.EndCommand;
import jikan.exception.EmptyNameException;
import jikan.exception.NoSuchActivityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class ExceptionTest {

    Scanner scanner = new Scanner(System.in);
    ActivityList activityList = new ActivityList();

    @Test
    public void testEmptyNameException() {
        Assertions.assertThrows(EmptyNameException.class, () -> {
            Parser.tokenizedInputs = new String[]{"start", ""};
            Parser.parseStart(activityList, scanner);
        });
    }

    @Test
    public void testNoSuchActivityException() {
        Assertions.assertThrows(NoSuchActivityException.class, () -> {
            Parser.startTime = null;
            Command endCommand = new EndCommand(null);
            endCommand.executeCommand(activityList);
            Command abortCommand = new AbortCommand(null);
            abortCommand.executeCommand(activityList);
        });

    }


}


