package jikan.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ExceptionTest {

    Parser parser = new Parser();

    @Test
    public void testEmptyNameException() {
        Assertions.assertThrows(EmptyNameException.class, () -> {
            parser.tokenizedInputs = new String[]{"start", ""};
            parser.parseStart();
        });
    }

}


