package jikan.command;

import jikan.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AbortCommandTest {

    @Test
    void executeAbort() {
        Parser.startTime = LocalDateTime.now();
        Command command = new AbortCommand(null);
        command.executeCommand(null);
        assertNull(Parser.startTime);
    }
}