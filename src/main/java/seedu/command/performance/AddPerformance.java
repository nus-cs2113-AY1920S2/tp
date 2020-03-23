package seedu.command.performance;

import seedu.StudentList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.parser.PerformanceParser;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.duke.Duke.studentListCollection;

public class AddPerformance extends Command {
    private UI ui;
    private DisplayList displayList;
    boolean isByNameList;
    PerformanceList performances;
    String eventName;

    /**
     * Constructor for AddPerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be added.
     */
    public AddPerformance(PerformanceList performances, String eventName) {
        this.isByNameList = UI.getTypeOfAddPerformance();
        this.performances = performances;
        this.eventName = eventName;
        this.ui = new UI();
        this.displayList = new DisplayList();
    }

    /**
     * Add the performance to performance list.
     * The user can choose to import a current student name list
     * and iterate through that name list to add performance of each
     * student. Also the user can choose to manually add a performance
     * of student.
     */
    public void addToList() throws DukeException {
        if (isByNameList) {
            addByList();
        }
        if (!isByNameList) {
            addManually();
        }
    }

    private void addManually() throws DukeException {
        int studentNumber = 0;
        String parameter = ui.getPerformanceParameter();
        while (!parameter.equals("done")) {
            performances.addToList(getPerformance(parameter), eventName);
            studentNumber++;
            parameter = ui.getStringInput();
        }
        System.out.println("You have successfully added "
                + studentNumber + " result(s) to the performance list.");
    }

    /**
     * This method get the user to select the index of student list to import
     * and return the list.
     * @return The student list selected by user.
     * @throws DukeException Throws DukeException when there is no student list
     *                       exist in the student list collection.
     */
    private StudentList getList() throws DukeException {
        if (studentListCollection.isEmpty()) {
            throw new DukeException("There is no existing student list.");
        }
        int listIndex = displayList.getStudentListIndex();
        return studentListCollection.get(listIndex - 1);
    }

    /**
     * This method get the user to input student's performance one by one
     * with the student list imported.
     * @throws DukeException Throws DukeException when there is no student list
     *      *                       exist in the student list collection.
     */
    private void addByList() throws DukeException {
        StudentList studentList = getList();
        for (String studentName : studentList.getStudentList()) {
            performances.addToList(new Performance(studentName,
                    ui.getResultOfStudent(studentName)), eventName);
        }
    }

    /**
     * It process the data input by student and returns
     * a Performance base on the input.
     * @return A Performance of student
     * @throws DukeException throws DukeException when the user input
     *                       is insufficient or incorrect.
     */
    private Performance getPerformance(String parameter) throws DukeException {
        return new PerformanceParser().parsePerformance(parameter);
    }

    @Override
    public void execute() throws DukeException {
        addToList();
    }
}