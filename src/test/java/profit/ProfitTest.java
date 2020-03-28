package profit;

import commands.AddDishCommand;
import dish.Profit;
import exceptions.EmptyStockException;
import exceptions.IngredientMissingException;
import exceptions.NegativeProfitException;
import ingredient.Ingredient;
import menu.Menu;
import org.junit.jupiter.api.Test;
import stock.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

public class ProfitTest {

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

    public void stockB() {
        Stock stockB = new Stock();
        stockB.addIngredient(new Ingredient("cheese", Optional.of(1), Optional.of(3.00)));
        stockB.addIngredient(new Ingredient("bacon", Optional.of(1), Optional.of(2.00)));
        stockB.addIngredient(new Ingredient("chicken", Optional.of(1), Optional.of(6.00)));
        stockB.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(3.00)));
        stockB.addIngredient(new Ingredient("chili", Optional.of(1), Optional.of(2.00)));
        stockB.addIngredient(new Ingredient("pasta", Optional.of(1), Optional.of(0.50)));
        stockB.addIngredient(new Ingredient("sauce", Optional.of(1), Optional.of(0.60)));
        stockB.addIngredient(new Ingredient("flatbread", Optional.of(1), Optional.of(0.30)));
    }

    public void stockC() {
        Stock stockC = new Stock();
        stockC.addIngredient(new Ingredient("cheese", Optional.of(0), Optional.of(3.00)));
        stockC.addIngredient(new Ingredient("bacon", Optional.of(5), Optional.of(2.00)));
    }

    @Test
    public void profitTestA() throws NegativeProfitException, EmptyStockException, IngredientMissingException {
        stockA();
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        Profit p1 = new Profit(Menu.getDishMap().get("bacon pizza"));
        assertEquals(1.20, p1.getCost());
        assertEquals(3.80, p1.getProfit());
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        Profit p2 = new Profit(Menu.getDishMap().get("chicken biryani"));
        assertEquals(1.70, p2.getCost());
        assertEquals(8.30, p2.getProfit());
        String d3 = "i/chicken, pasta, sauce; n/pasta with chicken; p/7.00;";
        AddDishCommand.addDish(d3);
        Profit p3 = new Profit(Menu.getDishMap().get("pasta with chicken"));
        assertEquals(1.60, p3.getCost());
        assertEquals(5.40, p3.getProfit());
        String d4 = "i/flatbread, bacon, cheese; p/3.00; n/bacon flatbread;";
        AddDishCommand.addDish(d4);
        Profit p4 = new Profit(Menu.getDishMap().get("bacon flatbread"));
        assertEquals(1.50, p4.getCost());
        assertEquals(1.50, p4.getProfit());
    }

    @Test
    public void profitTestB() throws NegativeProfitException, EmptyStockException, IngredientMissingException {
        stockB();
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        try {
            Profit p1 = new Profit(Menu.getDishMap().get("bacon pizza"));
        } catch (NegativeProfitException e) {
            System.out.println("Profit is <= 0");
        }

        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        try {
            Profit p2 = new Profit(Menu.getDishMap().get("chicken biryani"));
        } catch (NegativeProfitException e) {
            System.out.println("Profit is <= 0");
        }

        String d3 = "i/chicken, pasta, sauce; n/pasta with chicken; p/7.00;";
        AddDishCommand.addDish(d3);
        try {
            Profit p3 = new Profit(Menu.getDishMap().get("pasta with chicken"));
        } catch (NegativeProfitException e) {
            System.out.println("Profit is <= 0");
        }

        String d4 = "i/flatbread, bacon, cheese; p/3.00; n/bacon flatbread;";
        AddDishCommand.addDish(d4);
        try {
            Profit p4 = new Profit(Menu.getDishMap().get("bacon flatbread"));
        } catch (NegativeProfitException e) {
            System.out.println("Profit is <= 0");
        }
    }

    @Test
    public void profitTestC() throws NegativeProfitException, EmptyStockException, IngredientMissingException {
        stockC();
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        try {
            Profit p1 = new Profit(Menu.getDishMap().get("bacon pizza"));
        } catch (EmptyStockException e) {
            System.out.println("Stock for an ingredient is empty");
        }
    }

    @Test
    public void profitTestD() throws NegativeProfitException, EmptyStockException, IngredientMissingException {
        stockC();
        Menu m = new Menu();
        String d4 = "i/flatbread, bacon, cheese; p/3.00; n/bacon flatbread;";
        AddDishCommand.addDish(d4);
        try {
            Profit p1 = new Profit(Menu.getDishMap().get("bacon flatbread"));
        } catch (IngredientMissingException e) {
            System.out.println("Stock doesn't contain at least one ingredient");
        }
    }


}
