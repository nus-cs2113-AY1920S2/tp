package seedu.duke.module;

public class SelectedModule extends Module {
    private boolean isCompleted;
    private double grade;

    /**
     * This is Module's constructor.
     *
     * @param type             the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param semester         the module's semester.
     */
    public SelectedModule(String type, String moduleIdentifier, String semester) {
        super(type, moduleIdentifier, semester);
    }

    public void markAsDone(){
        boolean isCompleted = true;
    }

}
