import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MeetingOrganizer {
    public MeetingOrganizer() {
        //declare objects here
        meetings = new MeetingList();
    }

    private MeetingList meetings;

    public void botResponse(String userInput) throws MoException {
        switch (userInput) {
            case "1":
                System.out.println("You have selected new scheduled meeting");
                Scanner in = new Scanner(System.in);

                System.out.println("What do you want to name your meeting?");
                String meetingName = in.nextLine(); // eg. CS2113 Meeting
                System.out.println("Enter meeting details: <Date> <Start Time> <End Time> in the format YYYY-MM-DD HH:mm HH:mm>");
                String input = in.nextLine(); // eg. 2020-03-10 14:00 15:00
                String[] meetingDetails = input.split(" ", 3);

                LocalDate meetingDate = LocalDate.parse(meetingDetails[0]);
                LocalTime startTime = LocalTime.parse(meetingDetails[1]);
                LocalTime endTime = LocalTime.parse(meetingDetails[2]);

                meetings.add(new Meeting(meetingName, meetingDate, startTime, endTime));

                System.out.println("You now have " + meetings.getMeetingListSize() + " meetings in the list.");

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

