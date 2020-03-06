package seedu.duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BudgetTest {
    private Budget testBudget;

    @BeforeEach
    public void setUp() {
        testBudget = new Budget(50);
    }

    @Test
    public void getRemaining_zeroTotalCost_remainingEqualsBudget() {
        double totalCost = 0.0;
        assertEquals(testBudget.getRemaining(totalCost), testBudget);
    }

    @Test
    public void getRemaining_totalCostExceedsBudget_remainingIsNegative() {
        double totalCost = 60;
        double expectedResult = -10;
        assertEquals(testBudget.getRemaining(totalCost), expectedResult);
    }
}
