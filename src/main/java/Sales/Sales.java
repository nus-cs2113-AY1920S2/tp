package sales;

import dish.Dish;
import dish.Profit;
import exceptions.EmptyStockException;
import exceptions.IngredientMissingException;
import exceptions.NegativeProfitException;
import menu.Menu;

import java.util.HashMap;

public class Sales {

    private HashMap<Dish,Integer> soldDishes;

    public Sales() {
        this.soldDishes = new HashMap<Dish,Integer>();
    }

    public void addSale(String command) {
        //Format: `d/DISH; q/QUANTITY;`
        String[] splitString = command.split(";");
        String dishName = splitString[0].substring(splitString[0].indexOf("d/") + 2);
        int quantity = Integer.parseInt(splitString[1].substring(splitString[1].indexOf("q/") + 2));
        Dish dish = Menu.getDishMap().get(dishName);
        if (dish == null) {
            System.out.println("Dish doesn't exist");
        } else {
            //System.out.print("dish: " + dishName + " quantity: " + quantity );
            soldDishes.put(dish,quantity);
        }
    }

    public double calculateProfit()  {
        double profit = 0.0;
        boolean profitError = false;
        for (Dish item: soldDishes.keySet()) {
            int numberSold = soldDishes.get(item);
            double dishProfit = 0.0;

             try {
                  dishProfit = new Profit(item).getProfit() * numberSold;
             } catch (NegativeProfitException | EmptyStockException | IngredientMissingException e) {
                 System.out.println("There has been an error in calculating profit.");
                 System.out.println("Check if profit is positive, if there is stock and "
                         + "ingredients for this dish:" + item);
                 profitError = true;
                 break;
            }

            profit += dishProfit;
        }
        if (profitError == false) {
            System.out.println("The total profit for the day is: " + profit);
        }
        //Return statement is for testing purposes
        return profit;
    }

    public String mostPopularDish() {
        Dish popular = null;
        int currentMaxCount = 0;
        for (Dish item: soldDishes.keySet()) {
            if (soldDishes.get(item) > currentMaxCount) {
                popular = item;
                currentMaxCount = soldDishes.get(item);
            }
        }
        if (popular == null) {
            System.out.println("There are no dishes");
            return null;
        } else {
            System.out.println("The most popular dish today is: " + popular.getName());
            System.out.println("This has sales of: " + currentMaxCount);
        }

        return popular.getName();
    }

    public HashMap<Dish, Integer> getSalesMap() {
        return this.soldDishes;
    }
}
