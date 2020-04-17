package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.Chore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListChoreCommandTest {

    @Test
    void listChore() {
        ArrayList<Chore> choreList = new ArrayList<>();

        ListChoreCommand listEmptylist = new ListChoreCommand();
        assertEquals("Your list of chores is currently empty.", listEmptylist.listChore(choreList));

        choreList.add(new Chore("buy groceries", "Tuesday 12pm"));
        ListChoreCommand listNonEmptyList = new ListChoreCommand();
        assertEquals("Here are the chores in your list:\n1. " + choreList.get(0),
                listNonEmptyList.listChore(choreList));
    }
}