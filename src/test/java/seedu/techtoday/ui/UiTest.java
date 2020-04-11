package seedu.techtoday.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {



    @Test
    void getCommand_DeleteArticle1_success() {
        String input = "delete article 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = Ui.getCommand();
        assertEquals("delete article 1", actualOutput);
    }

    @Test
    void getCommand_DeleteWithSpaces_success() {
        String input = "delete                   ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = Ui.getCommand();
        assertEquals("delete", actualOutput);
    }
}