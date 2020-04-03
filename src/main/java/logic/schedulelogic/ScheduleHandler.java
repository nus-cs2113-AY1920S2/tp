package logic.schedulelogic;

import model.contact.Contact;
import java.util.ArrayList;



/**
 * TESTING SUMMARY DOC.
 */

public class ScheduleHandler {
    private static final Boolean MYSCHEDULEBLOCKED = true;
    private static final Boolean MYSCHEDULEFREE = false;
    private static Boolean[][][] masterSchedule = new Boolean[13][7][48];

    public ScheduleHandler(ArrayList<Contact> contactList) {
        assert contactList.size() <= 1 : "Only 1 member in contact list passed to ScheduleHandler";
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    masterSchedule[i][j][k] = MYSCHEDULEFREE; // fill every index with 0 initially
                }
            }
        }
        for (Contact t : contactList) {
            Boolean[][][] memberSchedule = t.getSchedule();
            fillMasterSchedule(memberSchedule);
        }
    }

    private void fillMasterSchedule(Boolean[][][] s) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 48; k++) {
                    if (s[i][j][k] == MYSCHEDULEBLOCKED) {
                        this.masterSchedule[i][j][k] = MYSCHEDULEBLOCKED;
                    }
                }
            }
        }
    }

    public static String getDayFromNumber(int dayNum) {
        String day;
        switch (dayNum) {
        case 0:
            day = "Sunday";
            break;
        case 1:
            day = "Monday";
            break;
        case 2:
            day = "Tuesday";
            break;
        case 3:
            day = "Wednesday";
            break;
        case 4:
            day = "Thursday";
            break;
        case 5:
            day = "Friday";
            break;
        case 6:
            day = "Saturday";
            break;
        default:
            throw new AssertionError(dayNum);
        }
        return day;
    }

    public Boolean[][][] getMasterSchedule() {
        return masterSchedule;
    }
}