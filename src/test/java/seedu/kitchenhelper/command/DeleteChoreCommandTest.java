package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.Chore;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteChoreCommandTest {

    @Test
    void deleteChore() {
        ArrayList<Chore> choreList = new ArrayList<>();
        choreList.add(new Chore("buy groceries", "Tuesday 12pm"));

        DeleteChoreCommand deleteNegativeIndex = new DeleteChoreCommand(-5);
        assertEquals("Please choose an index in the chore list!",
                deleteNegativeIndex.deleteChore(choreList));

        DeleteChoreCommand deleteValidIndex = new DeleteChoreCommand(1);
        deleteValidIndex.deleteChore(choreList);
        assertEquals(0, choreList.size());

        DeleteChoreCommand deleteInvalidIndex = new DeleteChoreCommand(1);
        assertEquals("Please choose an index in the chore list!",
                deleteInvalidIndex.deleteChore(choreList));
    }
}