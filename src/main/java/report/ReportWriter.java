package report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        FileWriter fw = new FileWriter("./report.txt");

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
                String writtenString = String.format("%d. %s \t %s " 
                        + ls, counter, name, ingredientList);
                fw.write(writtenString);
                counter += 1;
            }

            String stockTitle = String.format(ls 
                    + "Current Stock"
                    + ls
                    + ls);
            fw.write(stockTitle);
            counter = 1;
            for (Map.Entry<String, Pair<Integer,Double>> ingredient : stock.getStock().entrySet()) {
                String writtenString = String.format(
                        "%d. %s \t $%.2f %d "
                        + ls,
                        counter, 
                        ingredient.getKey(), 
                        ingredient.getValue().second(), 
                        ingredient.getValue().first());
                fw.write(writtenString);
                counter += 1;
            }
            counter = 1;
            String reserveTitle = String.format("\nReservations\n\n");
            fw.write(reserveTitle);
            for (int i = 0; i < reservations.getSize(); i++) {
                Reservation reserved = reservations.getReservation(i);
                String writtenString = String.format("%s" + ls, reserved.toString());
                fw.write(writtenString);
            }
        } catch (IOException e) {
            System.out.println("Invalid Path");
        }
        fw.close();
    }
}
