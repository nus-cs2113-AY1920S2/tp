import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TESTING SUMMARY DOC.
 */
public class MeetingOrganizer {
    private MeetingList myMeetingList;
    private ArrayList<TeamMember> myScheduleList;
    private ScheduleHandler myScheduleHandler;
    private Boolean[][] myMasterSchedule;
    public static Storage storage;

    public MeetingOrganizer() {
        //declare objects here
        myScheduleList = new ArrayList<>();
        try {
            storage = new Storage("data/meeting_list.txt");
            myMeetingList = new MeetingList(storage.loadListFromDisk());
        } catch (FileNotFoundException e) {
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
        }
        myMeetingList = new MeetingList();
    }


    void botResponse(String userInput) throws MoException, DateTimeParseException, NumberFormatException {
        Scanner in = new Scanner(System.in);

        switch (userInput) {
        case "1":
            TextUI.scheduleMeetingMsg();
            TextUI.printTimetable(myMasterSchedule);
            // Test add meeting
            TextUI.meetingNameMsg();
            String meetingName = in.nextLine(); // eg. CS2113 Meeting
            Integer startDay;
            LocalTime startTime;
            Integer endDay;
            LocalTime endTime;

            String input = "";
            boolean tryAgain = true;
            while (tryAgain && !input.equals("exit")) {
                TextUI.meetingDetailsMsg();
                input = in.nextLine(); // eg. 1 19:00 2 12:30
                if (input.equals("exit")) {
                    continue;
                }
                String[] meetingDetails = input.split(" ", 4);

                startDay = Integer.parseInt(meetingDetails[0]);
                startTime = LocalTime.parse(meetingDetails[1]);
                endDay = Integer.parseInt(meetingDetails[2]);
                endTime = LocalTime.parse(meetingDetails[3]);
                try {
                    if (myScheduleHandler.isValidMeeting(startDay, startTime, endDay, endTime)) {
                        tryAgain = false;
                        myMeetingList.add(new Meeting(meetingName, startDay, startTime, endDay, endTime));
                        myScheduleHandler.updateMasterSchedule(startDay, startTime, endDay, endTime);
                        myMasterSchedule = myScheduleHandler.getMasterSchedule();
                        TextUI.printTimetable(myMasterSchedule);
                        TextUI.meetingListSizeMsg(myMeetingList);
                    }
                } catch (MoException e) {
                    System.out.println(e.getMessage() + ", try again.");
                    tryAgain = true;
                }
            }
            TextUI.menuMsg();
            break;
        case "2":
            TextUI.deleteMeetingMsg();

            break;
        case "3":
            TextUI.deleteMeetingMsg();
            Scanner scanner = new Scanner(System.in);
            int index = Integer.parseInt(String.valueOf(scanner.next())) - 1;
            try {
                myMeetingList.delete(index);
            } catch (IndexOutOfBoundsException e) {
                TextUI.displayInvalidDeleteTarget();
            }
            break;
        case "4": //list all current meeting slots
            myMeetingList.show();
            break;
        default:
            throw new MoException("Unknown command, please try again.");
        }
    }


    private void setMembersSchedule(Scanner in) {
        TextUI.membersMsg();
        //TODO handle exception if user doesn't input integer or input too many members.
        Integer membersN = Integer.parseInt(in.nextLine());

        for (int i = 0; i < membersN; ++i) {
            TeamMember member = new TeamMember(String.valueOf(i)); //TODO change to member's name.
            String addBlocksSuccessOrNot = "";
            do {
                System.out.println(addBlocksSuccessOrNot);
                TextUI.enterScheduleMsg(String.valueOf(i + 1));
                String input = in.nextLine(); // eg. LessonA 1 19:00 2 12:30
                String[] scheduleDetails = input.split(" ", 5);

                String scheduleName = scheduleDetails[0];
                Integer startDay = Integer.parseInt(scheduleDetails[1]);
                String startTime = scheduleDetails[2];
                Integer endDay = Integer.parseInt(scheduleDetails[3]);
                String endTime = scheduleDetails[4];
                addBlocksSuccessOrNot = member.addBusyBlocks(scheduleName, startDay, startTime, endDay, endTime);
            } while (!addBlocksSuccessOrNot.equals("SUCCESS"));
            myScheduleList.add(member);
        }
        myScheduleHandler = new ScheduleHandler(myScheduleList);
        myMasterSchedule = myScheduleHandler.getMasterSchedule();
        TextUI.printTimetable(myMasterSchedule);
        myScheduleHandler.printFreeTimings();
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        TextUI.introMsg();
        Scanner in = new Scanner(System.in);
        setMembersSchedule(in);
        TextUI.menuMsg();
        String userInput = in.nextLine();

        while (!userInput.equals("5")) {
            try {
                botResponse(userInput);
                storage.updateListToDisk(myMeetingList.getMeetingList());
            } catch (MoException e) {
                TextUI.errorMsg(e);
                TextUI.menuMsg();
            } catch (DateTimeParseException e) {
                TextUI.timeOutOfRangeMsg();
                TextUI.menuMsg();
            } catch (NumberFormatException e) {
                TextUI.invalidNumberMsg();
                TextUI.menuMsg();
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

