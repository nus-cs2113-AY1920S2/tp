package seedu.duke;

import java.util.Scanner;

public class UI {
    public static Scanner in = new Scanner(System.in);

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

        System.out.println(greeting);
    }

    public static void showLine() {
        System.out.println("______________________");
    }

    /**
     * Reads the user command.
     * @return User command.
     */
    public static String readCommand() {
        showLine();
        System.out.println("Enter command: ");
        String userInput = in.nextLine();
        System.out.println("[Command entered: " + userInput + "]"); //TODO remove if not necessary
        return userInput;
    }

    /**
     * Reads the user answer.
     * @return User answer.
     */
    public static String readAnswer() {
        System.out.println("Enter answer: ");
        String userInput = in.nextLine();
        System.out.println("[Answer entered: " + userInput + "]");
        return userInput;
    }

    /**
     * Requests the user to indicate if his answer was right or wrong.
     * @return User marking
     */
    public static String checkCorrectness() {
        showLine();
        System.out.println("Please indicate if your answer is correct (Y/N):");
        String userInput = in.nextLine();
        userInput = userInput.toUpperCase();
        while (!userInput.equals("Y") && !userInput.equals("N")) {
            System.out.println("Please enter only (Y/N):");
            userInput = in.nextLine();
            userInput = userInput.toUpperCase();
        }
        return userInput;
    }

    /**
     *  Prints a list of commands used in the programme.
     */
    public static void printHelp() {
        String helpMessage = "Here's a list of things you can do:\n"
                + "\taddsubject s/<SUBJECTNAME>                            Add a subject\n"
                + "\tlistsubject                                           List all subjects\n"
                + "\tdeletesubject s/<INDEX>                               Delete a subject and "
                        + "all its cards\n"
                + "\n"
                + "\taddcard s/<INDEX> q/<QUESTION> a/<ANSWER>             Add a card into the "
                        + "specified subject\n"
                + "\teditcard s/<INDEX> c/<INDEX> q/<QUESTION> a/<ANSWER>  Edit an existing "
                        + "card with new question and answer\n"
                + "\tlistcard s/<INDEX>                                    List all cards "
                        + "in specified subject\n"
                + "\tdeletecard s/<INDEX> c/<INDEX>                        Delete the specified "
                        + "card from the specified subject\n"
                + "\n"
                + "\tquiz s/<INDEX> n/<NUMBER>                             Start a quiz of "
                        + "the specified number of questions from"
                        + " the specified subject\n"
                + "\tscore s/<INDEX>                                       View previous "
                        + "scores of quizzes from specified "
                        + "subject\n"
                + "\n"
                + "\taddevent e/<DESCRIPTION> d/<DATE>                     Add an event\n"
                + "\tlistevent                                             List all events\n"
                + "\tdeleteevent e/<INDEX>                                 Delete an event\n"
                + "\tshowupcoming d/<DAYS>                                 Show upcoming events\n"
                + "\n"
                + "\thelp                                                  Displays the help page\n"
                + "\texit                                                  Exits the program";
        //+ "\t> View Answer: ​       answer [INDEX] e.g. answer 1\n"

        System.out.println(helpMessage);
    }

    /**
     * Exits the ESC program.
     */
    public static void exitEsc() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }
}