package seedu.happypills.controller.commands.visitcommands;

import seedu.happypills.controller.commands.Command;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.model.exception.HappyPillsException;

public abstract class VisitCommand implements Command {
    public String execute(VisitMap visitMap) throws HappyPillsException {
        return null;
    }
}
