package seedu.duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BudgetTest {

    private Budget testBudget = new Budget();

    @Test
    public void getRemaining_zeroTotalCost_remainingEqualsBudget() {
        testBudget.setBudget(50);
        double totalCost = 0.0;
        assertEquals(testBudget.getAmount(),testBudget.getRemainingBudget(totalCost));
    }

    @Test
    public void getRemaining_totalCostExceedsBudget_remainingIsNegative() {
        testBudget.setBudget(50);
        double totalCost = 60;
        double expectedResult = -10;
        assertEquals(expectedResult,testBudget.getRemainingBudget(totalCost));
    }

    @Test
    public void setBudget_negativeInput_setToZero() {
        testBudget.setBudget(0);
        double testAmount = -100;
        double expectedResult = 0;
        testBudget.setBudget(testAmount);
        assertEquals(expectedResult,testBudget.getAmount());
    }

    @Test
    public void setBudget_tooLargeInput_setToMax() {
        testBudget.setBudget(0);
        double testAmount = 6000;
        double expectedResult = 5000;
        testBudget.setBudget(testAmount);
        assertEquals(expectedResult,testBudget.getAmount());
    }

}
