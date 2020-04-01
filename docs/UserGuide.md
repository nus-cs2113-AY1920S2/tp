# User Guide

## Introduction

Meeting Organizer is a friendly chatbot that helps you find common free time among you and your friends, using just the NUSMODS links to your school timetable.


## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the .jar file from the latest release of `MeetingOrganizer` [here](https://github.com/AY1920S2-CS2113T-T12-1/tp/releases).
3. Copy the .jar file to an empty folder.
4. Open Command Prompt and navigate to the folder using ```cd <folder path>```.
5. Run the .jar file using ```java -jar <.jar file name>```.

## Features 

- [Adding a new contact](#adding-a-new-contact)
- [List all contacts](#list-all-contacts-contacts)
- [Display timetable of selected contacts](#display-timetable-of-selected-contacts-timetable)
- [Schedule a new meeting](#schedule-a-new-meeting-schedule)
- [Delete a scheduled meeting](#delete-a-scheduled-meeting-delete)
- [List all scheduled meetings](#list-all-scheduled-meetings-meetings)
- [Exit application](#exit-application-exit)

<br/><br/>

### Adding a new contact
Adds a new person and their class schedule into our list of contacts.

Format: `<name> <NUSMODS link>`

Example of usage:

`
Juan https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:01,PTUT:01&CG2027=TUT:01,LEC:01&CG2028=TUT:01,LAB:02,LEC:01&CS2101=&CS2107=TUT:08,LEC:1&CS2113T=LEC:C01
`

[NOTE] You can add as many links as you want.

![](images/capture.png)
<br/><br/>

### List all contacts: `contacts`
Displays names of all contacts that is stored in program.

Example of usage: `contacts`

![](images/capture2.png)
<br/><br/>

### Display timetable of selected contacts: `timetable`
1) Displays the main user's timetable.
    
    Format: `timetable` 
    
![](images/capture3.png)`

2) Displays the timetable of the selected contact.

    Example of usage: `timetable 1 ` 
    Format: `timetable <Member Index>`
    
    Example of usage: `timetable 1` 
    
![](images/capture4.png)

3) Displays the combined timetable of the selected contacts.
   
   Example of usage: `timetable 0 1 2` 
   Format: `timetable <Member A Index> <Member B index>`
   
   Example of usage: `timetable 0 1` 
   
![](images/capture5.png)
   <br/><br/>
   

### Schedule a new meeting `schedule`
Schedules a new meeting and adds it into the meeting list.

Format: `schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>`

Example of usage:

`
schedule meeting 3 17:00 3 19:00
`

![](images/capture6.png)
<br/><br/>

### Delete a scheduled meeting `delete`
Deletes a scheduled meeting from the meeting list.

Format: `delete <Meeting Index>`

Example of usage:

`
delete 1
`

![](images/capture7.png)
<br/><br/>

### List all scheduled meetings: `meetings`
List all scheduled meetings stored in program.

Example of usage: `meetings`

![](images/capture8.png)
<br/><br/>

### Exit the application: `exit`
Exits the application and ends the current session.

Example of usage: `exit`

![](images/capture9.png)
<br/><br/>

## FAQ

**Q**: How do I transfer my data to another computer? 

&nbsp;&nbsp;&nbsp;&nbsp;**A**: Copy the `data` file in the root directory and paste into the root directory in the other computer.

## Command Summary

- **Adding a new contact**: `<name> <NUSMODS link>`
- **List all contacts**: `contacts`
- **Display timetable of selected contacts**: `timetable` OR `timetable <Member Index>` OR `timetable <Member A Index> <Member B Index>`
- **Schedule a new meeting**: `schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>`
- **Delete a scheduled meeting**: `delete <Meeting Index>`
- **List all scheduled meetings**: `meetings`
- **Exit application**: `exit`
