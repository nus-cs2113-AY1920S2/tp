package seedu.duke;

import seedu.cards.CardList;
import seedu.commands.Command;
import seedu.exception.EscException;
import seedu.parser.Parser;
import seedu.subjects.SubjectList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static UI ui = new UI();
    private Storage storage = new Storage();
    private CardList cards;
    private SubjectList subjectList = new SubjectList();

    /**
     * Reads the user's commands and executes them until the user issues the exit command.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                //cards = new CardList(storage.loadCards());
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(subjectList);
                //storage.saveCards(cards.getCards());
                isExit = c.isExit();
            } catch (EscException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        //assert false : "dummy to fail";
        new Duke().run();
    }
}