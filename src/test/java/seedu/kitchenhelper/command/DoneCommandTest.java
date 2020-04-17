package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.Chore;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {

    @Test
    void markChoreDone() {
        ArrayList<Chore> choreList = new ArrayList<>();
        Chore newChore = new Chore("buy groceries", "Tuesday 12pm");
        choreList.add(newChore);

        DoneCommand checkValidIndex = new DoneCommand(1);
        assertEquals(false, newChore.isDone);
        checkValidIndex.markChoreDone(choreList);
        assertEquals(true, newChore.isDone);
        assertEquals("This chore has already been marked as done.",
                checkValidIndex.markChoreDone(choreList));

        DoneCommand checkInvalidIndex = new DoneCommand(2);
        assertEquals("Please choose an index in the chore list!",
                checkInvalidIndex.markChoreDone(choreList));
    }
}