package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.Chore;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddChoreCommandTest {

    @Test
    void addChore() {
        ArrayList<Chore> choreList = new ArrayList<>();
        new AddChoreCommand("clean the toilet", "Saturday 5pm").addChore(choreList);
        assertEquals(1, choreList.size());
    }
}