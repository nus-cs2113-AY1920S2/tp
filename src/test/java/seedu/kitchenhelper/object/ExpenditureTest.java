package seedu.kitchenhelper.object;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenditureTest {


    @Test
    void saveExpenditureFile() {

    }

    @Test
    void loadExpenditureVariables() {
        Expenditure nullDateExpenditure = Expenditure.getInstance();
        nullDateExpenditure.loadExpenditureVariables(50, 20, null);
        assertEquals(0, nullDateExpenditure.totalExpenditure);
        assertEquals(0, nullDateExpenditure.amountUsedInCooking);
        assertTrue(nullDateExpenditure.lastSavedDate == null);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date pastMonday = calendar.getTime();
        Date today = new Date();
        long daysDifference = (pastMonday.getTime() - today.getTime()) / 1000 / 60 / 60 / 24;
        assertTrue(daysDifference <= 7);

        if (pastMonday.before(today)) {
            Expenditure loadExpenditure = Expenditure.getInstance();
            loadExpenditure.loadExpenditureVariables(200.38, 20.00, today);
            assertEquals(200.38, loadExpenditure.totalExpenditure);
            assertEquals(20, loadExpenditure.amountUsedInCooking);
            assertEquals(today, loadExpenditure.lastSavedDate);
        } else {
            //pastMonday after today
            Expenditure resetDateExpenditure = Expenditure.getInstance();
            resetDateExpenditure.loadExpenditureVariables(200.38, 20.00, today);
            assertEquals(0, resetDateExpenditure.totalExpenditure);
            assertEquals(0, resetDateExpenditure.amountUsedInCooking);
            assertEquals(today, resetDateExpenditure.lastSavedDate);
        }
    }

    @Test
    void resetExpenditureData() {
    }


    @Test
    void addToExpenditure() {
        Expenditure.getInstance().totalExpenditure = 0;
        Expenditure.getInstance().addToExpenditure(0.5, 20);
        assertEquals(10, Expenditure.getInstance().totalExpenditure);
    }

    @Test
    void removeFromExpenditure() {
    }

    @Test
    void addToAmountUsed() {
    }

    @Test
    void addAmountForCooking() {
    }

    @Test
    void editExpenditure() {
    }

    @Test
    void changePrice() {
    }

    @Test
    void promptUser() {
    }

    @Test
    void isValidResponse() {
    }
}