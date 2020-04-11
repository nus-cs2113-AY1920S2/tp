package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidCommandException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void parseInput() {
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput("profile hi");
        });
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput("set-weight  ");
        });
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput("");
        });
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput(" ");
        });
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput("set-profile");
        });
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput("help-me");
        });
        assertThrows(InvalidCommandException.class, () -> {
            CommandParser.parseInput("0");
        });
        try {
            assertEquals("profile", CommandParser.parseInput("profile").get().getCommand());
            assertEquals("list-food", CommandParser.parseInput("list-food").get().getCommand());
            assertEquals("show-recipe", CommandParser.parseInput("show-recipe").get().getCommand());
            assertEquals("help", CommandParser.parseInput("help").get().getCommand());
            assertEquals("exit", CommandParser.parseInput("exit").get().getCommand());
            assertEquals("set-profile",
                    CommandParser.parseInput("set-profile john 25 Male 175 70 75").get().getCommand());
            assertEquals("set-name",
                    CommandParser.parseInput("set-name john").get().getCommand());
            assertEquals("set-age",
                    CommandParser.parseInput("set-age 25").get().getCommand());
            assertEquals("set-gender",
                    CommandParser.parseInput("set-gender Male").get().getCommand());
            assertEquals("set-height",
                    CommandParser.parseInput("set-height 175").get().getCommand());
            assertEquals("set-weight",
                    CommandParser.parseInput("set-weight 70.5").get().getCommand());
            assertEquals("set-weight-goal",
                    CommandParser.parseInput("set-weight-goal 75.8").get().getCommand());
            assertEquals("delete-weight",
                    CommandParser.parseInput("delete-weight 0").get().getCommand());
        } catch (InvalidCommandException | InvalidFormatException ignored) {
            return;
        }

    }
}