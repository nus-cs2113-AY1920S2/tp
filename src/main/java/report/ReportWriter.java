package report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dish.Dish;
import menu.Menu;
import reservation.Reservation;
import reservation.ReservationList;
import stock.Stock;
import utils.Pair;

public class ReportWriter {
    
    protected Menu menu;
    protected Stock stock;
    protected ReservationList reservations;
    
    private final String ls = System.lineSeparator();
    private final String indent = "                                                              "
            + "       ";
    
    /**
     * A convenience constructor of ReportWriter.
     *
     */
    public ReportWriter(Stock stock, ReservationList reservations, Menu menu) {
        this.stock = stock;
        this.reservations = reservations;
        this.menu = menu;
    }
    
    /**
     * Save all information to a text file.
     */
    public void writeToFile() throws IOException {
        // Written in menu.Menu > Stock > Reservation Order
        FileWriter fw = new FileWriter("report.txt");

        try {
            String menuTitle = String.format("Menu Items" 
                    + ls 
                    + ls);
            fw.write(menuTitle);
            HashMap<String, Dish>  menuItems = menu.getDishMap();
            int counter = 1;
            for (String name: menuItems.keySet()) {
                String ingredientList = "";
                for (String str: menuItems.get(name).getIngredients()) {
                    ingredientList += str 
                            + ", ";
                }
                ingredientList = ingredientList.substring(0, ingredientList.length() - 2);
                double price = menuItems.get(name).getPrice();
                String writtenString = String.format("%d. Name: %-10s \t Price: $%-10.2f \t Ingredients: %-10s "
                        + ls, counter, name, price, ingredientList);
                fw.write(writtenString);
                counter += 1;
            }

            String stockTitle = String.format(ls 
                    + "Current Stock"
                    + ls
                    + ls);
            fw.write(stockTitle);
            counter = 1;
            
            List<Entry<String, Pair<Integer, Double>>> sortedStock = sortStockByDescendingQuantity(stock);
            for (Map.Entry<String, Pair<Integer,Double>> ingredient : sortedStock) {
                String ingredientName = ingredient.getKey();
                String spaceBetween = indent.substring(0, indent.length() - ingredientName.length());
                String writtenString = String.format(
                        "*%d. %s %s Price:$%.2f Quantity:%d "
                        + ls,
                        counter, 
                        ingredientName,
                        spaceBetween,
                        ingredient.getValue().second(), 
                        ingredient.getValue().first());
                fw.write(writtenString);
                counter += 1;
            }
            counter = 1;
            String reserveTitle = String.format(ls 
                    + "Reservations"
                    + ls
                    + ls);
            fw.write(reserveTitle);
            for (int i = 1; i <= reservations.getSize(); i++) {
                Reservation reserved = reservations.getReservation(i);
                String writtenString = String.format("%s" + ls, reserved.toString());
                fw.write(writtenString);
            }
        } catch (IOException e) {
            System.out.println("Invalid Path");
        }
        fw.close();
    }
    
    /** Sorts the stock in descending quantity before saving into report.txt. */
    public List<Entry<String, Pair<Integer, Double>>> sortStockByDescendingQuantity(Stock stock) {
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.getStock().entrySet());
        
        Collections.sort(tempList, new Comparator<Entry<String, Pair<Integer, Double>>>() {
            @Override
            public int compare(Entry<String, Pair<Integer, Double>> firstEntry, 
                    Entry<String, Pair<Integer, Double>> secondEntry) {
                
                int firstEntryQuantity = firstEntry.getValue().first();
                int secondEntryQuantity = secondEntry.getValue().first();
                return secondEntryQuantity - firstEntryQuantity;
            }
        }); 
        
        return tempList;
    }
}
