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
    private TeamMember mainUser;

    public MeetingOrganizer() {
        //declare objects here
        myMeetingList = new MeetingList();
        try {
            storage = new Storage("data/meeting_list.txt");
            myMeetingList = new MeetingList(storage.loadMeetingListFromDisk());
            myTeamMemberList = new TeamMemberList(storage.loadMemberListFromDisk());
            TextUI.introMsg();
            TextUI.teamMemberListMsg(myTeamMemberList.getTeamMemberList());
            if (myTeamMemberList.getSize() > 0) {
                mainUser = myTeamMemberList.getTeamMemberList().get(0);
            }
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
        // TODO member's name can only be 1 word at the moment.
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
                if (myTeamMemberList.getSize() == 0) {
                    mainUser = member;
                }
                myTeamMemberList.add(member);
                TextUI.showAddedMember(member.getName());
            } catch (InvalidUrlException | UnformattedModuleException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "add": // add blockOutReason/memberName startDay startTime endDay endTime (eg. add tuition 2 22:00 2 23:00)
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
        case "display": // display memberNumber1 memberNumber2 (eg. display 1 3)
            ArrayList<TeamMember> myScheduleList = new ArrayList<TeamMember>();
            for (int i = 1; i < userInputWords.length; i++) {
                int memberNumber = Integer.parseInt(userInputWords[i]);
                member = myTeamMemberList.getTeamMemberList().get(memberNumber);
                myScheduleList.add(member);
            }

            //Automatically add main user's timetable into scheduler.
            if (mainUser != null && !myScheduleList.contains(mainUser)) {
                myScheduleList.add(mainUser);
            }
            ScheduleHandler myScheduleHandler = new ScheduleHandler(myScheduleList);

            Boolean[][] myMasterSchedule;
            myMasterSchedule = myScheduleHandler.getMasterSchedule();
            System.out.println("Combined Schedule of you and your selected team member/s.");
            TextUI.printTimetable(myMasterSchedule);
            break;
        case "schedule": //schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time> (eg. schedule meeting 3 17:00 3 19:00)

            String meetingName = userInputWords[1];
            startDay = Integer.parseInt(userInputWords[2]);
            LocalTime startTime = LocalTime.parse(userInputWords[3]);
            endDay = Integer.parseInt(userInputWords[4]);
            LocalTime endTime = LocalTime.parse(userInputWords[5]);

            try {
                if (ScheduleHandler.isValidMeeting(mainUser, startDay, startTime, endDay, endTime)) {
                    Meeting myMeeting = new Meeting(meetingName, startDay, startTime, endDay, endTime);
                    myMeetingList.add(myMeeting);
                    mainUser.addBusyBlocks(meetingName, startDay, userInputWords[3], endDay, userInputWords[5]);
                    TextUI.meetingListSizeMsg(myMeetingList);
                } else {
                    System.out.println("Schedule is blocked at that timeslot");
                }
            } catch (MoException e) {
                System.out.println(e.getMessage() + ", try again.");
            }
            // Replace main user's timetable with updated meeting blocks into TeamMemberList for storage purposes.
            myTeamMemberList.set(0, mainUser);
            break;
        case "delete":
            int index = Integer.parseInt(userInputWords[1]) - 1;
            try {
                Meeting meetingToDelete = myMeetingList.getMeetingList().get(index);
                String meetingNameToDelete = meetingToDelete.getMeetingName();
                mainUser.deleteBusyBlocks(meetingNameToDelete);
                myMeetingList.delete(index);
                myTeamMemberList.set(0, mainUser);
            } catch (IndexOutOfBoundsException e) {
                TextUI.displayInvalidDeleteTarget();
            }
            break;
        case "meetings": //list all current meeting slots
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

    /**
     * Main entry-point for the application.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        TextUI.menuMsg(myTeamMemberList.getSize());
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
                TextUI.menuMsg(myTeamMemberList.getSize());
            }
        }
        storage.updateMemberListToDisk(myTeamMemberList.getTeamMemberList());
        TextUI.exitMsg();
    }

}

