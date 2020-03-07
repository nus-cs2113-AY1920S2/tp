package seedu.duke;

public class UI {

    /**
     * Prints the greeting message when ESC is started.
     */
    public static void showWelcome() {
        String greeting = "";
        String logo = " ______  _____  _____\n"
                + "|  ____|/ ____|/ ____|\n"
                + "| |__  | (___ | |\n"
                + "|  __|  \\___ \\| |\n"
                + "| |____ ____) | |____\n"
                + "|______|_____/ \\_____|\n";
        greeting += "Hello from\n" + logo;
        greeting += "What is your name?";

        System.out.println(greeting);
    }

    public static void showLine(){
        System.out.println("_________________________________________________");
    }

    /**
     *  Prints a list of commands used in the programme.
     */
    public static void printHelp() {
        String helpMessage = " Welcome to ESC.\n"
                + "Create: ​create n/[CATEGORYNAME] \n"
                + "E.g. ​create n/Biology.\n"
                + "List Category: ​list category\n"
                + "Select Category: ​select [INDEX]\n"
                + "Eg. ​select 1\n"
                + "Delete Category: ​delete category [INDEX]\n"
                + "Eg. ​delete 1\n"
                + "Add Card:​ add q/[QUESTION] a/[ANSWER]\n"
                + "Eg. ​add q/Which year was NUS founded? a/1980\n"
                + "List Card: ​list card\n"
                + "Delete Card: ​delete [INDEX]\n"
                + "Eg. ​delete 1\n"
                + "Quiz: ​quiz\n"
                + "Answer: ​answer​ / ​answer [INDEX]\n"
                + "Eg. ​answer 1\n"
                + "Help: ​help\n"
                + "Exit: ​exit\n";

        System.out.println(helpMessage);
    }


    public static void exitEsc() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
