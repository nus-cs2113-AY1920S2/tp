package seedu.commands;

import seedu.exams.Exam;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Command class for the ScoreCommand.
 */
public class ShowUpcomingCommand extends Command {

    public static final String COMMAND_WORD = "showupcoming";

    public static final String MESSAGE_USAGE = "To show upcoming events, "
            + "type command: showupcoming d/[NUMBER OF DAYS UPCOMING]";

    private int dateRange;

    /**
     * Initialises the parameters for score command.
     */
    public ShowUpcomingCommand(int dateRange) {
        this.dateRange = dateRange;
    }

    /**
     * View all the scores attained for a subject.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        ArrayList<Exam> exams = subjectList.getExamDates();
        ArrayList<Exam> upcomingExams = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Exam exam : exams) {
            LocalDate date = exam.getDate();
            long period = today.until(date, ChronoUnit.DAYS);
            if (period <= dateRange && period >= 0) {
                upcomingExams.add(exam);
            }
        }
        Collections.sort(upcomingExams);
        subjectList.listUpcoming(upcomingExams);
    }

}
