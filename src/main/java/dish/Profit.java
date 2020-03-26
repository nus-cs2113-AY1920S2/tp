package dish;

import exceptions.EmptyStockException;
import exceptions.IngredientMissingException;
import exceptions.NegativeProfitException;
import stock.Stock;
import utils.Pair;

import java.util.Map;

public class Profit {

    private Dish dish;
    private double cost;
    private double price;
    private double profit;

    /**
     * A profit object, which contains the profit for a specific dish
     * @param d dish to calculate profit for
     * @throws NegativeProfitException exception for if profit is less than 0
     * @throws IngredientMissingException exception for if dish contains ingredient not in stock
     */
    public Profit(Dish d) throws NegativeProfitException, IngredientMissingException, EmptyStockException {
        dish = d;
        cost = totalCost(d);
        price = d.getPrice();
        if (price - cost > 0) {
            profit = price - cost;
        } else {
            throw new NegativeProfitException();
        }
    }

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

    public Dish getDish() {
        return dish;
    }

    public double getCost() {
        return cost;
    }

    public double getPrice() {
        return price;
    }

    public double getProfit() {
        return profit;
    }
}
