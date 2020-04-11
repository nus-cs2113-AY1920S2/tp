package commands;

public class HelpCommand {
    /**
     * Prints all the commands.
     */
    public static void execute() {
        System.out.println("These are the commands supported by the system:");
        addCommands();
        deleteCommands();
        listCommands();
        searchCommands();
        salesCommands();
        clearCommands();
        quitCommand();
    }

    /**
     * Helper method for add commands.
     */
    private static void addCommands() {
        System.out.println("Add Commands:");
        System.out.println("Add Dish: add dish; n/NAME; p/PRICE; [i/INGREDIENT1, INGREDIENT2, ...];");
        System.out.println("Add Stock: add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;");
        System.out.println("Add Reservation: add reservation; p/CONTACT_PERSON_NAME;"
                + " d/DATE; n/NUMBER_OF_GUESTS; c/CONTACT; [m/COMMENTS];");
        System.out.println("");
    }

    /**
     * Helper method for delete commands.
     */
    private static void deleteCommands() {
        System.out.println("Delete Commands:");
        System.out.println("Delete Dish: delete dish; n/NAME;");
        System.out.println("Delete Stock (with quantity): delete stock; i/INGREDIENT; q/QUANTITY;");
        System.out.println("Delete Stock (without quantity): delete stock; i/INGREDIENT;");
        System.out.println("Void Reservation: delete reservation; r/NUMBER_OF_RESERVATION;");
        System.out.println("Mark Reservation as Served: mark reservation; r/NUMBER_OF_RESERVATION;");
        System.out.println("");
    }

    /**
     * Helper method for list commands.
     */
    private static void listCommands() {
        System.out.println("List Commands:");
        System.out.println("List Menu: list dish;");
        System.out.println("List Stock: list stock;");
        System.out.println("List Reservation: list reservation;");
        System.out.println("List Served Reservation: list served reservation;");
        System.out.println("List Unserved Reservation: list unserved reservation;");
        System.out.println("");
    }

    /**
     * Helper method for search commands.
     */
    private static void searchCommands() {
        System.out.println("Search Commands:");
        System.out.println("Search dish: search dish; k/KEYWORD;");
        System.out.println("Search Stock: search stock; k/keyword;");
        System.out.println("Search Reservation: search reservation; {r/NUMBER_OF_RESERVATION; | d/DATE;}");
        System.out.println("");
    }

    /**
     * Helper method for sales commands.
     */
    private static void salesCommands() {
        System.out.println("Sales Commands:");
        System.out.println("Sell dish: sell dish; d/DISH; q/QUANTITY;");
        System.out.println("Calculate profit: profit");
        System.out.println("Find most popular dish: popular");
        System.out.println("");
    }

    /**
     * Helper methods for clear commands.
     */
    private static void clearCommands() {
        System.out.println("Clear Commands:");
        System.out.println("Clear all reservations: clear reservation;");
        System.out.println("Clear all stock: clear stock;");
        System.out.println("");
    }

    /**
     * Helper method for quitting the application.
     */
    private static void quitCommand() {
        System.out.println("To exit the program enter: bye");
        System.out.println("");
    }
}
