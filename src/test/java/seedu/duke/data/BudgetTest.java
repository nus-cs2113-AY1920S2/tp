package seedu.duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BudgetTest {

    private Budget testBudget;

    @BeforeEach
    public void setUp() {
        testBudget = new Budget(0);
    }

    @Test
    public void setBudget_negativeInput_setToZero() {
        double testAmount = -100;
        double expectedResult = 0;
        testBudget.setBudget(testAmount);
        assertEquals(testBudget.getAmount(),expectedResult);
    }

    @Test
    public void setBudget_tooLargeInput_setToMax() {
        double testAmount = 6000;
        double expectedResult = 5000;
        testBudget.setBudget(testAmount);
        assertEquals(testBudget.getAmount(),expectedResult);
    }
}
