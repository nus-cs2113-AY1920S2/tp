package jikan.command;

import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidTimeFrameException;
import jikan.parser.Parser;
import jikan.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNull;

class AbortCommandTest {
    @Test
    void executeAbort() {
        Parser.startTime = LocalDateTime.now();
        Command command = new AbortCommand(null);
        try {
            command.executeCommand(null);
            assertNull(Parser.startTime);
        } catch (EmptyNameException | ExtraParametersException e) {
            System.out.println("Filed error.");
        }
    }
}