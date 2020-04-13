package seedu.command.performance;

import seedu.student.StudentList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.parser.PerformanceParser;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.pac.Pac.studentListCollection;

public class AddPerformanceList extends Command {
    private UI ui;
    private DisplayList displayList;
    PerformanceList performanceList;
    String eventName;

    /**
     * Constructor for AddPerformanceCommand. Takes PerformanceList, performances of the
     * event to be modified, and String eventName, name of the event that owns the
     * performance list.
     */
    public AddPerformanceList(PerformanceList performanceList, String eventName) {
        this.performanceList = performanceList;
        this.eventName = eventName;
        this.ui = new UI();
        this.displayList = new DisplayList();
    }

    /**
     * Add the performance to performance list.
     * The user is to choose whether to add the performance by importing a list
     * or adding manually.
     */
    private void addToList() throws PacException {
        boolean isByNameList = ui.isImportList();
        if (isByNameList) {
            addByList();
        }
        if (!isByNameList) {
            addManually();
        }
    }

    /**
     * This method gets the user to manually add a performance.
     * The user has to input name of student and grade for each
     * performance to be added. This method tells the user
     * whether the performance is added successfully with a
     * reply message.
     *
     * @throws PacException A PacException is thrown when the
     *                       performance format input is incorrect
     *                       and cannot be added successfully.
     */
    private void addManually() throws PacException {
        int studentNumber = 0;
        String parameter = ui.getPerformanceParameterToAdd();
        while (!parameter.equals("done")) {
            performanceList.addToList(getPerformance(parameter), eventName);
            studentNumber++;
            parameter = ui.getStringInput();
        }
        UI.display("You have successfully added "
                + studentNumber + " result(s) to the performance list.");
    }

    /**
     * This method get the user to select the index of student list to import
     * and return the list.
     * @return The student list selected by user.
     * @throws PacException Throws PacException when there is no student list
     *                       exist in the student list collection.
     */
    private StudentList getList() throws PacException {
        if (studentListCollection.isEmpty()) {
            throw new PacException("There is no existing student list.");
        }
        try {
            int listIndex = displayList.getStudentListIndex();
            return studentListCollection.get(listIndex - 1);
        } catch (Exception e) {
            throw new PacException("Fail to get list");
        }
    }

    /**
     * This method get the user to input student's performance one by one
     * with the student list imported.
     * @throws PacException Throws PacException when there is no student list
     *      *                       exist in the student list collection.
     */
    private void addByList() throws PacException {
        StudentList studentList = getList();
        int studentNumber = 0;
        for (String studentName : studentList.getStudentList()) {
            performanceList.addToList(new Performance(studentName,
                    ui.getResultOfStudent(studentName)), eventName);
            studentNumber++;
        }
        UI.display("You have successfully added "
                + studentNumber + " result(s) to the performance list.");
    }

    /**
     * It process the data input by student and returns
     * a Performance base on the input.
     * @return A Performance of student
     * @throws PacException throws PacException when the user input
     *                       is insufficient or incorrect.
     */
    private Performance getPerformance(String parameter) throws PacException {
        return new PerformanceParser().parsePerformance(parameter);
    }

    @Override
    public void execute() throws PacException {
        addToList();
    }
}