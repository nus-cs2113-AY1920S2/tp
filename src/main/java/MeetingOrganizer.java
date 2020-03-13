import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MeetingOrganizer {
    private final Boolean mySchedule_BLOCKED = true;
    private final Boolean mySchedule_FREE = false;


    public MeetingOrganizer() {
        //declare objects here
    }
    void botResponse(String userInput) throws MoException {
        switch (userInput) {
            case "1":
                System.out.println("You have selected new scheduled meeting");
                TeamMember member1 = new TeamMember("john");
                member1.addBusyBlocks("test1", 0, "10:00", 2, "12:00");
                printSchedule("member 1 schedule", member1.getSchedule()); //check
                TeamMember member2 = new TeamMember("mary");
                member2.addBusyBlocks("test2", 2, "14:00", 2, "18:00");
                printSchedule("member 2 schedule", member2.getSchedule()); //check

                /** XZ's schedule handler**/

                ArrayList<TeamMember> myScheduleList = new ArrayList<TeamMember>();
                myScheduleList.add(member1);
                myScheduleList.add(member2);
                ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);
                Boolean[][] myMasterSchedule = myScheduleHandler.getMasterSchedule();
                printSchedule("master schedule", myMasterSchedule); //check

                myScheduleHandler.printFreeTimings();



                break;
            case "2":
                System.out.println("Which meeting slot do you want to delete?");
                break;
            case "3":
                System.out.println("Which meeting slot do you want to edit?");
                break;
            case "4":
                System.out.println("Here are all your meeting slots.");
                break;
            default:
                throw new MoException("Unknown command, please try again.");
        }
    }

    private void printSchedule(String scheduleName, Boolean[][] s) {
        System.out.println(scheduleName);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 48; j++) {
                if (s[i][j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println();
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

    public static void main(String[] args) {
        new MeetingOrganizer().run();
    }

}

