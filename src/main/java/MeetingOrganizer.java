import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TESTING SUMMARY DOC.
 */
public class MeetingOrganizer {
    public static Storage storage;
    private MeetingList myMeetingList;
    private TeamMemberList myTeamMemberList;

    public MeetingOrganizer() {
        //declare objects here
        myMeetingList = new MeetingList();
        try {
            storage = new Storage("data/meeting_list.txt");
            myMeetingList = new MeetingList(storage.loadMeetingListFromDisk());
            myTeamMemberList = new TeamMemberList(storage.loadMemberListFromDisk());
            TextUI.introMsg();
            TextUI.teamMemberListMsg(myTeamMemberList.getTeamMemberList());
        } catch (FileNotFoundException e) {
            TextUI.introMsg();
            TextUI.showLoadingError();
            myMeetingList = new MeetingList();
            myTeamMemberList = new TeamMemberList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new MeetingOrganizer().run();
    }

    void botResponse(String[] userInputWords, Scanner in) throws MoException, DateTimeParseException, NumberFormatException {
        Integer startDay = null;
        Integer endDay = null;
        TeamMember member;

        String userCommand = userInputWords[0];

        //To adapt user input of format <name> <NUSMODS link> to fit into the following switch statements to allow
        // for both link and manual input.
        if (userInputWords.length == 2 && userInputWords[1].contains("https")) {
            userCommand = "add using link";
        }

        switch (userCommand) {
        case "add using link":
            member = new TeamMember(userInputWords[0]);
            String name = userInputWords[0];
            LessonsGenerator myLessonGenerator;
            try {
                myLessonGenerator = new LessonsGenerator(userInputWords[1]);
                myLessonGenerator.generate();
                ArrayList<String[]> myLessonDetails = myLessonGenerator.getLessonDetails();
                for (int k = 0; k < myLessonDetails.size(); k++) {
                    String startTimeString = null;
                    String endTimeString = null;
                    for (int j = 0; j < myLessonDetails.get(k).length; j++) {
                        switch (j) {
                        case 0:
                            startTimeString = myLessonDetails.get(k)[j].substring(0,2) + ":" + myLessonDetails.get(k)[j].substring(2);
                            break;
                        case 1:
                            endTimeString = myLessonDetails.get(k)[j].substring(0,2) + ":" + myLessonDetails.get(k)[j].substring(2);
                            break;
                        case 2:
                            startDay = getNumberFromDay(myLessonDetails.get(k)[j]);
                            endDay = startDay;
                            break;
                        case 3:
                            //future improvement: since myLessonDetails.get(k)[3] contains data on the
                            // week number that this class occurs on, add capability of schedule to reflect
                            // schedule of the current week.
                            break;
                        default:
                            //data only has four sections from api
                            break;
                        }
                    }
                    member.addBusyBlocks(name, startDay, startTimeString, endDay, endTimeString);
                }
                myTeamMemberList.add(member);
                TextUI.showAddedMember(member.getName());
            } catch (InvalidUrlException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "add": // add memberName startDay startTime endDay endTime (eg. add xizhi 2 02:00 3 14:00)
            member = new TeamMember(userInputWords[1]);
            String memberName = userInputWords[1]; //member name and schedule name are the same
            startDay = Integer.parseInt(userInputWords[2]);
            String startTimeString = userInputWords[3];
            endDay = Integer.parseInt(userInputWords[4]);
            String endTimeString = userInputWords[5];
            member.addBusyBlocks(memberName, startDay, startTimeString, endDay, endTimeString);

            myTeamMemberList.add(member);
            break;
        case "contacts":  // contacts
            TextUI.teamMemberListMsg(myTeamMemberList.getTeamMemberList());
            break;
        case "schedule": // schedule memberNumber1 memberNumber2 (eg. schedule 1 3)
            ArrayList<TeamMember> myScheduleList = new ArrayList<TeamMember>();
            for (int i = 1; i < userInputWords.length; i++) {
                int memberNumber = Integer.parseInt(userInputWords[i]);
                member = myTeamMemberList.getTeamMemberList().get(memberNumber - 1);
                myScheduleList.add(member);

                System.out.println(member.getName() + " schedule: ");
                TextUI.printTimetable(member.getSchedule());
            }

            ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);

            Boolean[][] myMasterSchedule = new Boolean[7][48];
            myMasterSchedule = myScheduleHandler.getMasterSchedule();
            System.out.println("master schedule BEFORE:");
            TextUI.printTimetable(myMasterSchedule);

            myScheduleHandler.printFreeTimings();
            TextUI.meetingDetailsMsg();

            String userInput = in.nextLine();
            userInputWords = CliParser.splitWords(userInput);

            String meetingName = userInputWords[0];
            startDay = Integer.parseInt(userInputWords[1]);
            LocalTime startTime = LocalTime.parse(userInputWords[2]);
            endDay = Integer.parseInt(userInputWords[3]);
            LocalTime endTime = LocalTime.parse(userInputWords[4]);

            try {
                if (myScheduleHandler.isValidMeeting(startDay, startTime, endDay, endTime)) {
                    myMeetingList.add(new Meeting(meetingName, startDay, startTime, endDay, endTime));
                    myScheduleHandler.updateMasterSchedule(startDay, startTime, endDay, endTime);
                    myMasterSchedule = myScheduleHandler.getMasterSchedule();

                    System.out.println("master schedule AFTER:");
                    TextUI.printTimetable(myMasterSchedule);

                    TextUI.meetingListSizeMsg(myMeetingList);
                } else {
                    System.out.println("schedule is blocked at that timeslot");
                }
            } catch (MoException e) {
                System.out.println(e.getMessage() + ", try again.");
            }
            break;
        case "2":
            TextUI.editMeetingMsg();

            break;
        case "3":
            TextUI.deleteMeetingMsg();
            int index = Integer.parseInt(String.valueOf(in.next())) - 1;
            try {
                myMeetingList.delete(index);
            } catch (IndexOutOfBoundsException e) {
                TextUI.displayInvalidDeleteTarget();
            }
            break;
        case "4": //list all current meeting slots
            TextUI.listMeetings();
            myMeetingList.show();
            break;
        default:
            throw new MoException("Unknown command, please try again.");
        }
    }

    private Integer getNumberFromDay(String day) {
        int dayInNumber;
        switch (day) {
        case "Monday":
            dayInNumber = 1;
            break;
        case "Tuesday":
            dayInNumber = 2;
            break;
        case "Wednesday":
            dayInNumber = 3;
            break;
        case "Thursday":
            dayInNumber = 4;
            break;
        case "Friday":
            dayInNumber = 5;
            break;
        case "Saturday":
            dayInNumber = 6;
            break;
        case "Sunday":
            dayInNumber = 0;
            break;
        default:
            dayInNumber = Integer.parseInt(null);
            break;
        }
        return dayInNumber;
    }

    //function for testing purposes
    public void testPrinterToSeeBehaviourOfApiClassesOutput(String nusmodsLink) {
        LessonsGenerator myLessonGenerator;
        try {
            myLessonGenerator = new LessonsGenerator(nusmodsLink);
            myLessonGenerator.generate();
            ArrayList<String[]> myLessonDetails = myLessonGenerator.getLessonDetails();
            for (int k = 0; k < myLessonDetails.size(); k++) {
                for (int j = 0; j < myLessonDetails.get(k).length; j++) {
                    System.out.print(myLessonDetails.get(k)[j] + " ");
                }
                System.out.print("\n");
            }
        } catch (InvalidUrlException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Main entry-point for the application.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        TextUI.menuMsg();
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            if (userInput.equals("exit")) {
                break;
            }

            String[] userInputWords = CliParser.splitWords(userInput);
            try {
                botResponse(userInputWords, in);
                storage.updateMeetingListToDisk(myMeetingList.getMeetingList());
            } catch (MoException e) {
                TextUI.errorMsg(e);
            } catch (DateTimeParseException e) {
                TextUI.timeOutOfRangeMsg();
            } catch (NumberFormatException e) {
                TextUI.invalidNumberMsg();
            } finally {
                TextUI.menuMsg();
            }
        }
        storage.updateMemberListToDisk(myTeamMemberList.getTeamMemberList());
        TextUI.exitMsg();
    }

}

