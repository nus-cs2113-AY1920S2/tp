package commands;

public class HelpCommand {

    public static void execute() {
        System.out.println("These are the commands supported by the system:");
        addCommands();
        deleteCommands();
        listCommands();
        searchCommands();
    }

    private static void addCommands() {
        System.out.println("Add Commands:");
        System.out.println("Add Dish: add dish; n/NAME; p/PRICE; [i/INGREDIENT1, INGREDIENT2, ...];");
        System.out.println("Add Stock: add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;");
        System.out.println("Add Reservation: add reservation; p/CONTACT_PERSON_NAME;"
                + " d/DATE; n/NUMBER_OF_GUESTS; c/CONTACT; [m/COMMENTS];");
        System.out.println("");
    }

    private static void deleteCommands() {
        System.out.println("Delete Commands:");
        System.out.println("Delete Dish: delete dish; n/NAME;");
        System.out.println("Delete Stock (with quantity): delete stock; i/INGREDIENT; q/QUANTITY;");
        System.out.println("Delete Stock (without quantity): delete stock; i/INGREDIENT;");
        System.out.println("Void Reservation: delete reservation; r/NUMBER_OF_RESERVATION;");
        System.out.println("");
    }

    private static void listCommands() {
        System.out.println("List Commands:");
        System.out.println("List Menu: list dish");
        System.out.println("List Stock: list stock");
        System.out.println("List Reservation: list reservation");
        System.out.println("");
    }

    private static void searchCommands() {
        System.out.println("Search Commands:");
        System.out.println("Search Stock: search stock; keyword");
    }
}
