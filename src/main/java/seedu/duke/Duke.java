package seedu.duke;
import seedu.cards.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static UI ui = new UI();

    /**
     *  Lists all the cards in the list.
     *   @param cards A list of card to be displayed.
     */
    public static void listCards(ArrayList<Card> cards){
        System.out.println("Here is the list of questions.");
        for(int i= 0; i < cards.size(); i++){
            int j = i+1;
            System.out.println( j + " ." +cards.get(i).getQuestion());
            System.out.println( j + " ." +cards.get(i).getAnswer());
        }
    }

    /**
    * Main method.
    */
    public static void main(String[] args) {
        ui.showWelcome();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}