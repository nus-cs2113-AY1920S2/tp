import java.util.Scanner;

public class MeetingOrganizer {
    public MeetingOrganizer() {
        //declare objects here
    }
    void botResponse(String userInput) throws MoException {
        switch (userInput) {
            case "1":
                System.out.println("You have selected new scheduled meeting");
                
                // Test printTimetable
                boolean[][] mySchedule = new boolean[7][48];
                TextUI.printTimetable(mySchedule);
                //

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
}

