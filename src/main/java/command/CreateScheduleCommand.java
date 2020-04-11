package command;

import exception.command.MisuseOfScheduleCommandException;
import exception.command.NumberOfTasksToBeScheduledNotAnIntegerException;
import exception.command.NumberOfTasksToBeScheduledNotPositiveException;
import task.TaskList;
import task.TaskScheduler;
import ui.Ui;

//@@author GanapathySanathBalaji
/**
 * This command is used to create schedule based on user input if its feasible.
 */
public class CreateScheduleCommand extends Command {

    public static final String TASKS_SUCCESSFULLY_SCHEDULED = "Tasks successfully scheduled.";
    public static final String TASKS_COULDN_T_BE_SCHEDULED = "Tasks couldn't be scheduled.";
    TaskScheduler taskScheduler;

    /**
     * Constructor for the CreateScheduleCommand Object.
     * Checks if the user given input follows the correct format and creates a new object.
     *
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @param commandSplit Contains the information regarding the number of tasks to be scheduled.
     * @throws Exception If the wrong format is used.
     */
    public CreateScheduleCommand(boolean isOneWordCommand, String[] commandSplit) throws Exception {
        if (isOneWordCommand) {
            throw new MisuseOfScheduleCommandException();
        }
        try {
            int numberOfTasksToSchedule = Integer.parseInt(commandSplit[1]);
            if (numberOfTasksToSchedule <= 0) {
                throw new NumberOfTasksToBeScheduledNotPositiveException();
            }
            taskScheduler = new TaskScheduler(numberOfTasksToSchedule);
        } catch (NumberFormatException e) {
            throw new NumberOfTasksToBeScheduledNotAnIntegerException();
        }
    }


    @Override
    public void executeCommand(TaskList taskList, Ui ui) {
        taskScheduler.getTasks(ui);
        if (taskScheduler.scheduleTasks(ui)) {
            ui.printMessage(TASKS_SUCCESSFULLY_SCHEDULED);
            taskScheduler.addScheduleToList(taskList, ui);
        } else {
            ui.printMessage(TASKS_COULDN_T_BE_SCHEDULED);
        }
        ui.printLine();
    }
}
