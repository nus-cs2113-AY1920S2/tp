//@@author kokjoon97

package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.ShoppingList;
import seedu.duke.data.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    ShoppingList testList = new ShoppingList();

    @BeforeEach
    void setUp() {
        testList.clearList();
        testList.add(new Item("apple",0.0));
    }

    @Test
    void execute_nonMatchingKeyword() {
        Command command = new FindCommand("zzzzzzz");
        command.setData(testList,null);
        command.execute();
        assertEquals("Sorry, no results could be found!", command.feedbackToUser);
    }

    @Test
    void execute_nonMatchingCaseUppercaseKeyword() {
        Command command = new FindCommand("APPLE");
        command.setData(testList,null);
        command.execute();
        assertEquals("", command.feedbackToUser);
    }

    @Test
    void execute_matchingLowercaseKeyword() {
        Command command = new FindCommand("apple");
        command.setData(testList,null);
        command.execute();
        assertEquals("",command.feedbackToUser);
    }

    @Test
    void execute_nonMatchingRandomCaseKeyword() {
        Command command = new FindCommand("aPpLe");
        command.setData(testList,null);
        command.execute();
        assertEquals("", command.feedbackToUser);
    }
}
