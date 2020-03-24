package Sales;

import dish.Dish;
import utils.Pair;

import java.util.ArrayList;

public class Sales {
    //Stored as dish quantity pair
    private ArrayList<Pair<Dish,Integer>> soldDishes;
    public Sales() {
        this.soldDishes = new ArrayList<Pair<Dish,Integer>>();
    }
    public void addSale(String command) {
        //Format: `sell dish; d/DISH; q/QUANTITY;`
        //TBC
        String dishName = command.substring(command.indexOf("d/")+2, command.indexOf("; q/"));
        int quantity = Integer.parseInt(command.substring(command.indexOf("q/")+2,command.length()-1));

    }
    public void calculateProfit() {
        double profit = 0.0;
        for (Pair<Dish,Integer> item: soldDishes) {
            Dish currentDish = item.first();
            int numberSold = item.second();
            double dishProfit = currentDish.getProfit() * numberSold;
            profit += dishProfit;
        }
        System.out.println("The total profit for the day is: " + profit);
    }
    public void mostPopularDish() {
        Dish popular = soldDishes.get(0).first();
        int currentMaxCount = soldDishes.get(0).second();
        for (Pair<Dish,Integer> item: soldDishes) {
            if (item.second() > currentMaxCount) {
                popular = item.first();
                currentMaxCount = item.second();
            }
        }
        System.out.println("The most popular dish today is: " + popular.toString());
        System.out.println("This has sales of: " + currentMaxCount);
    }
}
