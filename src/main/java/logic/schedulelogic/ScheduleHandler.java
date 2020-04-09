package logic.schedulelogic;

import model.contact.Contact;

import java.util.ArrayList;



/**
 * This class contains information on generating a combined schedule with given contacts.
 * It includes a method for an external class to access the combined schedule.
 */

public class ScheduleHandler {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;

    private static Boolean[][][] combinedSchedule = new Boolean[13][7][48];


    /** Generates a combined schedule for given contacts.
     * Used in the display timetable function.
     *
     * @param contactList   A list of contacts to generate the combined schedule for.
     */
    public ScheduleHandler(ArrayList<Contact> contactList) {
        assert contactList.size() <= 1 : "Only 1 member in contact list passed to ScheduleHandler";
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    combinedSchedule[i][j][k] = MYSCHEDULEFREE; // fill every index with 0 initially
                }
            }
        }
        for (Contact t : contactList) {
            Boolean[][][] memberSchedule = t.getSchedule();
            fillCombinedSchedule(memberSchedule);
        }
    }

    /** Add busy blocks of a contact's schedule into the combined schedule.
     *
     * @param s   The contact's schedule to be added into the combined schedule
     */
    private void fillCombinedSchedule(Boolean[][][] s) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    if (s[i][j][k] == MYSCHEDULEBLOCKED) {
                        this.combinedSchedule[i][j][k] = MYSCHEDULEBLOCKED;
                    }
                }
            }
        }
    }

    public Boolean[][][] getCombinedSchedule() {
        return combinedSchedule;
    }
}