/*
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
 */
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import jikan.storage.Storage;

/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    /**
     * Parses user commands to relevant functions to carry out the commands.
     * @param scanner scanner object which reads user input
     * @param activityList the list of activities
     */
    public static void parseUserCommands(Scanner scanner, ActivityList activityList) {
        boolean exit = false;
        Activity newActivity;
        LocalDateTime startTime = null;
        String[] tags = null;

        while (exit == false) {
            String userInput = scanner.nextLine();
            String[] tokenizedInputs = userInput.split(" ");
            String instruction = tokenizedInputs[0];

            switch (instruction) {
            case "bye":
                System.out.println("Bye! See you again.\n");
                exit = true;
                break;
            case "start":
                System.out.println("Started " + tokenizedInputs[1] + "\n");
                if (tokenizedInputs.length > 2) {
                    tags = Arrays.copyOfRange(tokenizedInputs, 2, tokenizedInputs.length);
                }
                startTime = LocalDateTime.now();
                break;
            case "end":
                if (startTime == null) {
                    System.out.println("You have not started any activity!");
                } else {
                    System.out.println("Ended " + tokenizedInputs[1] + "\n");
                    newActivity = new Activity(tokenizedInputs[1], startTime, LocalDateTime.now(), tags);
                    activityList.add(newActivity);
                }
                break;
            case "list":
                activityList.printList();
                break;
            case "abort":
                startTime = null;
                System.out.println("You have aborted the current activity!");
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }
}
