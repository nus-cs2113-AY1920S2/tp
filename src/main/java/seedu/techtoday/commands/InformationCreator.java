package seedu.techtoday.commands;

import seedu.techtoday.common.Messages;
import seedu.techtoday.creator.ManualArticleCreator;
import seedu.techtoday.creator.ManualJobCreator;
import seedu.techtoday.creator.ManualNoteCreator;

/** Class representing the creation of article, note and job objects. */
public class InformationCreator {

    /**
     * Executes the creation process of information objects.
     * @param userResponse - Input of the user in the command line.
     */
    public static void execute(String userResponse) {
        try {
            String type = userResponse.split(" ")[1];
            switch (type) {
            case "article": {
                ManualArticleCreator.execute();
                Messages.printStraightLine();
                break;
            }
            case "job": {
                ManualJobCreator.execute();
                Messages.printStraightLine();
                break;
            }
            case "note": {
                ManualNoteCreator.execute();
                Messages.printStraightLine();
                break;
            }
            default: {
                System.out.println("Invalid command for create!. You can only create [article / job / note]");
                Messages.printStraightLine();
            }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Create should be of the following form:");
            Messages.printInCenter("6. create [article / job / note]");
            Messages.printStraightLine();
        }
    }
}
