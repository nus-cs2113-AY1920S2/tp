package task;

import java.util.Comparator;

//@@author GanapathySanathBalaji

/**
 * Comparator class used to compare object of type ScheduleTask.
 */
public class SchedulableTaskComparator implements Comparator<SchedulableTask> {

    @Override
    public int compare(SchedulableTask schedulableTask1, SchedulableTask schedulableTask2) {
        return  schedulableTask1.numberOfDaysLeft - schedulableTask2.numberOfDaysLeft;
    }
}
