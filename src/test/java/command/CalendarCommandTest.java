package command;

import org.junit.jupiter.api.Test;
import task.Event;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author NizarMohd

class CalendarCommandTest {

    @Test
    void checkExistingTasks_assertEquals() throws Exception {

        Task task = new Event("this event", "2020-05-09", "15:00", "16:00",
                "1");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        HashMap<Integer, LinkedList<Task>> map = new HashMap<>();
        map = CalendarCommand.checkExistingTasks(tasks, 5, 2020);
        String actual = map.get(9).get(0).getCalendarTaskDetails();
        actual = actual.trim();
        assertEquals("[E] this event", actual);
    }

}