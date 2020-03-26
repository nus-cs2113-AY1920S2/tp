package dish;

import exceptions.EmptyStockException;
import exceptions.IngredientMissingException;
import exceptions.NegativeProfitException;
import stock.Stock;
import utils.Pair;

import java.util.Map;

public class Profit {

    /**
     * Dish that profit is of
     */
    private Dish dish;

    /**
     * Cost of dish
     */
    private double cost;

    /**
     * Price of dish
     */
    private double price;

    /**
     * Profit of dish
     */
    private double profit;

    /**
     * A profit object, which contains the profit for a specific dish
     * @param d dish to calculate profit for
     * @throws NegativeProfitException exception for if profit is less than 0
     * @throws IngredientMissingException exception for if dish contains ingredient not in stock
     * @throws EmptyStockException exception for if stock of ingredient is empty
     */
    public Profit(Dish d) throws NegativeProfitException, IngredientMissingException, EmptyStockException {
        dish = d;
        cost = (double) Math.round(totalCost(d) * 100) / 100;
        price = d.getPrice();
        if (price - cost > 0) {
            profit = price - cost;
        } else {
            throw new NegativeProfitException();
        }
    }

    /**
     * Sums total cost of ingredient in a dish and checks for several exceptions
     * @param d dish to sum cost of ingredients for
     * @return total cost of ingredients in dish
     * @throws IngredientMissingException ingredient not listed in stock
     * @throws EmptyStockException stock of ingredient is zero
     */
    public double totalCost(Dish d) throws IngredientMissingException, EmptyStockException {
        double totalCost = 0;
        Map<String, Pair<Integer, Double>> stock = Stock.getStock();
        for (String ingredientName: d.getIngredients()) {
            if (stock.containsKey(ingredientName) && stock.get(ingredientName).first() <= 0) {
                throw new EmptyStockException();
            } else if (stock.containsKey(ingredientName) && stock.get(ingredientName).first() > 0){
                totalCost += stock.get(ingredientName).second();
            } else {
                throw new IngredientMissingException(ingredientName);
            }
        }
        return totalCost;
    }

    /**
     * Get dish profit is of
     * @return dish profit is of
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Get total cost of dish
     * @return total cost of dish
     */
    public double getCost() {
        return cost;
    }

    /**
     * Get price of dish
     * @return price of dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get profit of dish
     * @return profit of dish
     */
    public double getProfit() {
        return profit;
    }
}
