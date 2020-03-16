import java.time.LocalTime;
import java.io.FileNotFoundException;

import static common.Messages.MESSAGE_STARTENDTIME_OUT_OF_RANGE;
import static common.Messages.MESSAGE_INVALID_NUMBER;
import static java.lang.System.out;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TESTING SUMMARY DOC.
 */
public class MeetingOrganizer {
    public static Storage storage;

    public MeetingOrganizer() {
        try {
            storage = new Storage("data/meeting_list.txt");
            myMeetingList = new MeetingList(storage.loadListFromDisk());
        } catch (FileNotFoundException e) {
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
        }
    }

    private MeetingList myMeetingList;
    private ArrayList<TeamMember> myScheduleList;
    private ScheduleHandler myScheduleHandler;
    private Boolean[][] myMasterSchedule;

    void botResponse(String userInput) throws MoException, DateTimeParseException, NumberFormatException {
        Scanner in = new Scanner(System.in);

        switch (userInput) {
        case "1":
            TextUI.scheduleMeetingMsg();
            TextUI.printTimetable(myMasterSchedule);
            // Test add meeting
            TextUI.meetingNameMsg();
            String meetingName = in.nextLine(); // eg. CS2113 Meeting
            TextUI.meetingDetailsMsg();
            String input = in.nextLine(); // eg. 1 19:00 2 12:30
            String[] meetingDetails = input.split(" ", 4);

            Integer startDay = Integer.parseInt(meetingDetails[0]);
            LocalTime startTime = LocalTime.parse(meetingDetails[1]);
            Integer endDay = Integer.parseInt(meetingDetails[2]);
            LocalTime endTime = LocalTime.parse(meetingDetails[3]);

            if (myScheduleHandler.isValidMeeting(startDay, startTime, endDay, endTime)) {
                myMeetingList.add(new Meeting(meetingName, startDay, startTime, endDay, endTime));
                myScheduleHandler.updateMasterSchedule(startDay, startTime, endDay, endTime);
                myMasterSchedule = myScheduleHandler.getMasterSchedule();
                TextUI.printTimetable(myMasterSchedule);
                TextUI.meetingListSizeMsg(myMeetingList);
            }
            break;

        case "2":
            TextUI.editMeetingMsg();//previously was delete
            /*
            out.println("Which meeting slot do you want to ...?"); //previously was delete
            member1.deleteBusyBlocks("TESTMEETING");
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 48; j++) {
                    out.print(member1.getSchedule()[i][j]);
                }
                out.println();
            }
             */
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
        Integer membersN = Integer.parseInt(in.nextLine());
        TextUI.enterScheduleMsg();

        myScheduleList = new ArrayList<>();
        for (int i = 0; i < membersN; ++i) { // HANDLE EXCEPTION
            TeamMember member = new TeamMember(String.valueOf(i));
            String input = in.nextLine(); // eg. LessonA 1 19:00 2 12:30
            String[] scheduleDetails = input.split(" ", 5);

            String scheduleName = scheduleDetails[0];
            Integer startDay = Integer.parseInt(scheduleDetails[1]);
            String startTime = scheduleDetails[2];
            Integer endDay = Integer.parseInt(scheduleDetails[3]);
            String endTime = scheduleDetails[4];
            member.addBusyBlocks(scheduleName, startDay, startTime, endDay, endTime);
            myScheduleList.add(member);
        }
        myScheduleHandler = new ScheduleHandler(myScheduleList);
        myMasterSchedule = myScheduleHandler.getMasterSchedule();
        TextUI.printTimetable(myMasterSchedule);
        myScheduleHandler.printFreeTimings();

        TextUI.menuMsg();
    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        TextUI.introMsg();
        Scanner in = new Scanner(System.in);
        setMembersSchedule(in);
        String userInput = in.nextLine();

        while (!userInput.equals("5")) {
            try {
                botResponse(userInput);
                storage.updateListToDisk(myMeetingList.getMeetingList());
            } catch (MoException e) {
                TextUI.errorMsg(e);
            } catch (DateTimeParseException e) {
                TextUI.timeOutOfRangeMsg();
            } catch (NumberFormatException e) {
                TextUI.invalidNumberMsg();
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

