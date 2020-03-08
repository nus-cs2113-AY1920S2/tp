package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleTest {

    @Test
    void testStringConversion() {
        assertEquals("ID: CS2113 | Semester: Sem2",new Module("id", "CS2113", "Sem2").toString());
    }
}
