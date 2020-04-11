package seedu.performance;

import org.junit.jupiter.api.Test;
import seedu.exception.PacException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PerformanceListTest {

    ArrayList<Performance> emptyTestList = new ArrayList<>();
    PerformanceList emptyTestUnit = new PerformanceList();

    @Test
    void getPerformanceList() {
        assertEquals(emptyTestList, emptyTestUnit.getPerformanceList());
    }

    @Test
    void getPerformance() throws PacException {
        Performance performanceTestUnit = new Performance("John", "100");
        emptyTestUnit.addToList(performanceTestUnit, "event");
        assertEquals(performanceTestUnit, emptyTestUnit.getPerformance("John"));
    }

    @Test
    void isEmpty() {
        assertTrue(emptyTestList.isEmpty());
    }
}