import static java.lang.System.out;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * TESTING SUMMARY DOC.
 */
public class MeetingOrganizer {


    public MeetingOrganizer() {
        //declare objects here
    }

    public static void main(String[] args) {
        new MeetingOrganizer().run();
    }

    void botResponse(String userInput) throws MoException {

        TeamMember member1 = new TeamMember("john");
        TeamMember member2 = new TeamMember("mary");
        switch (userInput) {
        case "1":
            out.println("You have selected new scheduled meeting");
            // Test printTimetable
            boolean[][] mySchedule = new boolean[7][48];
            TextUI.printTimetable(mySchedule);

            member1.addBusyBlocks("test1", 0, "10:00", 2, "12:00");
            printSchedule("member 1 schedule", member1.getSchedule()); //check

            member2.addBusyBlocks("test2", 2, "14:00", 2, "18:00");
            printSchedule("member 2 schedule", member2.getSchedule()); //check

            ArrayList<TeamMember> myScheduleList = new ArrayList<>();
            myScheduleList.add(member1);
            myScheduleList.add(member2);
            ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);
            Boolean[][] myMasterSchedule = myScheduleHandler.getMasterSchedule();
            printSchedule("master schedule", myMasterSchedule); //check
            myScheduleHandler.printFreeTimings();

            break;
        case "2":
            out.println("Which meeting slot do you want to delete?");
            member1.deleteBusyBlocks("TESTMEETING");
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 48; j++) {
                    out.print(member1.getSchedule()[i][j]);
                }
                out.println();
            }
            break;
        case "3":
            out.println("Which meeting slot do you want to edit?");
            break;
        case "4":
            out.println("Here are all your meeting slots.");
            break;
        default:
            throw new MoException("Unknown command, please try again.");
        }
    }

    private void printSchedule(String scheduleName, Boolean[][] s) {
        out.println(scheduleName);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (s[i][j]) {
                    out.print("1");
                } else {
                    out.print("0");
                }
            }
            out.println();
        }
        out.println();
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        TextUI.introMsg();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("5")) {
            try {
                botResponse(userInput);
            } catch (MoException e) {
                TextUI.errorMsg(e);
            } finally {
                userInput = in.nextLine();
            }
        }
        TextUI.exitMsg();
    }

}

