package model.meeting;

import ui.TextUI;

import java.util.ArrayList;

/**
 * Stores meetings in a meeting list.
 * Contains an ArrayList object as the meeting list.
 * Has constructor and getter methods for the meeting list.
 *
 * @see Meeting
 */
public class MeetingList {
    private ArrayList<Meeting> meetingList;

    public MeetingList(ArrayList<Meeting> dataOnDisk) {
        meetingList = dataOnDisk;
    }

    public MeetingList() {
        meetingList = new ArrayList<>();
    }

    public void add(Meeting meeting) {
        meetingList.add(meeting);
    }

    public void delete(int index) throws IndexOutOfBoundsException {
        TextUI.displayRemovedMeeting(meetingList,index);
        meetingList.remove(index);
    }

    public void show() {
        if (meetingList.size() != 0) {
            TextUI.listAllScheduledMeetings(this.meetingList);
        } else {
            TextUI.displayNoMeetings();
        }
    }

    public ArrayList<Meeting> getMeetingList() {
        return this.meetingList;
    }

    public int getMeetingListSize() {
        return meetingList.size();
    }

    public Boolean isDone() {
        return true;
    }
}
