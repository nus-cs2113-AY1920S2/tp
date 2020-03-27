package sales;

import Sales.Sales;
import commands.AddDishCommand;
import exceptions.EmptyStockException;
import exceptions.IngredientMissingException;
import exceptions.NegativeProfitException;
import ingredient.Ingredient;
import menu.Menu;
import org.junit.Test;
import stock.Stock;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SalesTest {

    public void stockA() {
        Stock stockA = new Stock();
        stockA.addIngredient(new Ingredient("cheese", Optional.of(1), Optional.of(0.30)));
        stockA.addIngredient(new Ingredient("bacon", Optional.of(1), Optional.of(0.90)));
        stockA.addIngredient(new Ingredient("chicken", Optional.of(1), Optional.of(1.00)));
        stockA.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stockA.addIngredient(new Ingredient("chili", Optional.of(1), Optional.of(0.20)));
        stockA.addIngredient(new Ingredient("pasta", Optional.of(1), Optional.of(0.40)));
        stockA.addIngredient(new Ingredient("sauce", Optional.of(1), Optional.of(0.20)));
        stockA.addIngredient(new Ingredient("flatbread", Optional.of(1), Optional.of(0.30)));
    }

    @Test
    public void TestAddSale1() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        String d2 = "n/bacon; i/bacon; p/3.00;";
        AddDishCommand.addDish(d2);
        String d3 = "n/pasta; i/spaghetti, bacon; p/10.00;";
        AddDishCommand.addDish(d3);
        Sales s = new Sales();
        String s1 = "d/bacon pizza; q/5;";
        String s2 = "d/bacon; q/10";
        String s3 = "d/pasta; q/20";
        s.addSale(s1);
        s.addSale(s2);
        s.addSale(s3);
        assertNotNull(s.getSalesMap());
        assertTrue(s.getSalesMap().get(m.getDishMap().get("bacon pizza")).equals(5));
        assertTrue(s.getSalesMap().get(m.getDishMap().get("bacon")).equals(10));
        assertTrue(s.getSalesMap().get(m.getDishMap().get("pasta")).equals(20));

    }

    @Test
    public void testAddSale2() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        String d2 = "n/bacon; i/bacon; p/3.00;";
        AddDishCommand.addDish(d2);

        Sales s = new Sales();
        String s1 = "d/bacon pizza; q/5;";
        String s2 = "d/bacon; q/10";
        String s3 = "d/pasta; q/20";
        s.addSale(s1);
        s.addSale(s2);
        s.addSale(s3);
        assertNotNull(s.getSalesMap());
        assertTrue(s.getSalesMap().get(m.getDishMap().get("bacon pizza")).equals(5));
        assertTrue(s.getSalesMap().get(m.getDishMap().get("bacon")).equals(10));
        assertNull(s.getSalesMap().get(m.getDishMap().get("pasta")));
    }

    @Test
    public void testTotalProfit() throws NegativeProfitException, EmptyStockException, IngredientMissingException {
        stockA();
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);

        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);

        String d3 = "i/chicken, pasta, sauce; n/pasta with chicken; p/7.00;";
        AddDishCommand.addDish(d3);

        String d4 = "i/flatbread, bacon, cheese; p/3.00; n/bacon flatbread;";
        AddDishCommand.addDish(d4);

        Sales s = new Sales();
        s.addSale("d/bacon pizza; q/15;");
        s.addSale("d/chicken biryani; q/12;");
        s.addSale("d/bacon flatbread; q/5;");
        s.addSale("d/pasta with chicken; q/20;");
        assertTrue(s.calculateProfit() == 272.1);
    }

    @Test
    public void testPopularDishAndProfit() {
        stockA();
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);

        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);

        String d3 = "i/chicken, pasta, sauce; n/pasta with chicken; p/7.00;";
        AddDishCommand.addDish(d3);

        String d4 = "i/flatbread, bacon, cheese; p/3.00; n/bacon flatbread;";
        AddDishCommand.addDish(d4);

        Sales s = new Sales();
        s.addSale("d/bacon pizza; q/15;");
        s.addSale("d/chicken biryani; q/12;");
        s.addSale("d/bacon flatbread; q/5;");
        s.addSale("d/pasta with chicken; q/20;");

        assertTrue(s.calculateProfit() == 272.1);
        assertTrue(s.mostPopularDish().equals("pasta with chicken"));
    }

    @Test
    public void testNoDish() {
        Sales s = new Sales();
        assertTrue(s.calculateProfit() == 0.0);
        assertTrue(s.getSalesMap().isEmpty());
        assertNull(s.mostPopularDish());
    }
}
