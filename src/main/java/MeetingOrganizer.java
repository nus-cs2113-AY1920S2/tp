import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MeetingOrganizer {
    public MeetingOrganizer() {
        //declare objects here
    }
    void botResponse(String userInput) throws MoException {
        switch (userInput) {
            case "1":
                System.out.println("You have selected new scheduled meeting");
                TeamMember member1 = new TeamMember("john");
                member1.addBusyBlocks("testmeeting1", 0, "10:30", 2, "12:00");
                for (int i = 0; i < 7; i++) {
                    for (int j=0; j < 48; j++) {
                        if(member1.getSchedule()[i][j]) {
                            System.out.print("1");
                        } else {
                            System.out.print("0");
                        }
                    }
                    System.out.println();
                }
                TeamMember member2 = new TeamMember("mary");
                member2.addBusyBlocks("testmeeting2", 2, "11:00", 2, "18:00");
                for (int i = 0; i < 7; i++) {
                    for (int j=0; j < 48; j++) {
                        if(member2.getSchedule()[i][j]) {
                            System.out.print("1");
                        } else {
                            System.out.print("0");
                        }
                    }
                    System.out.println();
                }

                /** XZ's schedule handler**/

                ArrayList<TeamMember> myScheduleList = new ArrayList<TeamMember>();
                myScheduleList.add(member1);
                myScheduleList.add(member2);
                ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);
                ArrayList<ArrayList<Integer>> freeTimings = myScheduleHandler.calculateFreeTime();
                for (int i = 0; i < freeTimings.size(); i++){
                        getTimeFromBlock(freeTimings[i][2],"START");

                }

                System.out.println("You have selected new scheduled meeting");

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

    public LocalTime getTimeFromBlock(int blockNum, String startOrEnd) throws MoException {
        int minuteTime = 0;
        int hourTime = 0;

        switch (startOrEnd) {
            case "START":
                hourTime = blockNum / 2;
                if (blockNum % 2 == 1) {
                    minuteTime = 30;
                }
                break;
            case "END":
                if (blockNum % 2 == 1) {
                    hourTime = blockNum / 2 + 1;
                }
                if (blockNum % 2 == 0) {
                    minuteTime = 30;
                }
                break;
        }

        LocalTime myTime = LocalTime.of(hourTime, minuteTime);
        return myTime;
    }
}

