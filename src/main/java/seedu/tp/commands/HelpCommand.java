package seedu.tp.commands;

import seedu.tp.exceptions.HistoryFlashcardException;

/**
 * HelpCommand class to print the help message which shows the list of possible user commands.
 */
public class HelpCommand extends Command {

    private static final String NEWLINE = System.lineSeparator();
    private static String helpMessage = "Please follow the prompts given by the program in each situation." + NEWLINE
        + "(Note: In the command format, parameters are CAPITALISED)" + NEWLINE
        + "(Note: When a date is required, you can enter it in any of these formats:" + NEWLINE
        + "       yyyy, d/m/yyyy, m/yyyy, d-m-yyyy, m-yyyy)" + NEWLINE
        + "Commands for History Flashcard are as follows:" + NEWLINE
        + "FLASHCARD CREATION" + NEWLINE
        + "1. Add an event flashcard: event" + NEWLINE
        + "2. Add a person flashcard: person" + NEWLINE
        + "3. Add an other flashcard: other" + NEWLINE
        + NEWLINE
        + "BASIC OPERATIONS" + NEWLINE
        + "4a. List out all the flashcards: list" + NEWLINE
        + "4b. List all flashcards in order sorted by start/birth date: timeline" + NEWLINE
        + "4c. List all flashcards sorted by start/birth date, within a restricted time period: "
        + "timeline [STARTDATE] [ENDDATE]" + NEWLINE
        + "5. Show all details of an existing flashcard: show INDEX" + NEWLINE
        + "6. Delete a flashcard from the flashcard list: delete INDEX" + NEWLINE
        + "7. Find flashcards matching a keyword: find KEYWORD" + NEWLINE
        + NEWLINE
        + "FLASHCARD STATUS OPERATIONS" + NEWLINE
        + "8a. Set the priority of an existing flashcard: priority INDEX PRIORITY" + NEWLINE
        + "  (Note: There are 4 priority levels: LOW, MEDIUM, HIGH, DEFAULT)" + NEWLINE
        + "8b. List flashcards of a specified priority: list-priority PRIORITY" + NEWLINE
        + "9a. Set an existing flashcard as reviewed: reviewed INDEX" + NEWLINE
        + "9b. List all reviewed flashcards: list-reviewed" + NEWLINE
        + "9c. Reset status of all flashcards to Unreviewed: reset-reviewed" + NEWLINE
        + NEWLINE
        + "GROUPS" + NEWLINE
        + "10a. Set up a new flashcard group: group" + NEWLINE
        + "10b. List all existing groups: show-groups" + NEWLINE
        + "10c. Add a flashcard to an existing group: add" + NEWLINE
        + "10d. List all flashcards in a group: list-group [GROUPNAME/INDEX]" + NEWLINE
        + "10e. Delete an existing flashcard group from group list: delete-group [GROUPNAME/INDEX]" + NEWLINE
        + NEWLINE
        + "STUDY PLANS" + NEWLINE
        + "11a. Create or update study plan for a day: plan" + NEWLINE
        + "11b. Delete a study plan: delete-plan" + NEWLINE
        + "11c. Show all study plans: show-plan" + NEWLINE
        + NEWLINE
        + "RANDOMIZED REVIEW" + NEWLINE
        + "12. Shuffle and display a random flashcard: random" + NEWLINE
        + NEWLINE
        + "OTHER" + NEWLINE
        + "13. Get the list of commands: help" + NEWLINE
        + "14. Exit History Flashcard: bye";

    @Override
    public CommandFeedback execute() throws HistoryFlashcardException {
        LOGGER.info("Showing the help message");
        return new CommandFeedback(helpMessage);
    }
}
