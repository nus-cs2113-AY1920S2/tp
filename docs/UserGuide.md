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
- [Edit a contact's timetable](#edit-a-contact's timetable-edit)
- [Delete a meeting](#delete-a-meeting-delete--m)
- [Delete a contact](#delete-a-contact-delete--c)
- [List all meetings](#list-all-meetings-meetings)
- [Exit application](#exit-application-exit)

<br/><br/>

### Adding a new contact
Adds a new person and their class schedule into our list of contacts.

Format: 
    
    <name> <NUSMODS link>

Example of usage:


    Juan https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:01,PTUT:01&CG2027=TUT:01,LEC:01&CG2028=TUT:01,LAB:02,LEC:01&CS2101=&CS2107=TUT:08,LEC:1&CS2113T=LEC:C01
![](images/capture.png)

Note:
- You must add the main user's contact before you can use the application. 
- You can add as many contacts as you want thereafter.


<br/><br/>

### List all contacts: `contacts`
Displays index and names of all added contacts stored in the program.

Format 
    
    contacts 

Example of usage: 
    
    contacts

![](images/capture2.png)
<br/><br/>

### Display timetable of selected contacts: `timetable`
1) Displays the main user's timetable.
    
    Format: 
    
        timetable 
        
    Example of usage: 
    
        timetable
    
    ![](images/capture3.png)`

2) Displays the timetable of the selected contact.

    Format: 
    
        timetable <Contact Index>
    
    Example of usage: 
    
        timetable 1
    
    ![](images/capture4.png)
    
    Note:
    
    - Both `timetable` and `timetable 0 ` will display the main user's timetable.

3) Displays the combined timetable of the selected contacts.
   
   Format: 
   
       timetable <Contact A Index> <Contact B index>
   
   Example of usage: 
   
       timetable 0 1 2
   
    ![](images/capture5.png)
    
    Note:
    
    - Remember to include 0 as a contact index, if you want to display your (main user's) timetable combined with other 
    contacts.
   <br/><br/>
   

### Schedule a new meeting `schedule`
Schedules a new meeting at a specified time slot and adds it into the meeting list.

Format: 

    schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>

Example of usage:

    schedule meeting 3 17:00 3 19:00

![](images/capture6.png)
<br/><br/>

Note:
- Scheduling a meeting will modify your (main user's) timetable to be busy at the specified time slot. 
Timetables of other contacts will not be affected.
- You cannot schedule a meeting if your timetable is busy at the specified time slot. When Free will remind you to check
`timetable` if you try to schedule a meeting during a time slot which you are busy for.

    TODO change image
    ![](images/capture6.png)


### Edit a contact's timetable `edit`
1) Edit a contact's timetable to be busy for a specified time slot.

    Format: 
    
        edit busy <Contact Index> <Start Day> <Start Time> <End Day> <End Time>
    
    Example of usage:
    
        edit busy 0 2 22:00 2 23:00
        
    TODO change image
    ![](images/capture6.png)

2) Edit a contact's timetable to be free for a specified time slot.
    
    Format: 
    
        edit busy <Contact Index> <Start Day> <Start Time> <End Day> <End Time>
    
    Example of usage:
    
        edit free 0 2 22:00 2 23:00

    TODO change image
    ![](images/capture6.png)
    
Note:
    
- You can check `<Contact Index>` of the contact whose timetable you wish to edit, by first listing all contacts using `contacts`.

- When editing your (main user's) timetable, be careful not edit over a scheduled meeting's time slot. WhenFree 
will remind you to check `meetings` if you try to edit over a scheduled meeting.

    TODO change image
    ![](images/capture6.png)
    
<br/><br/>



### Delete a meeting `delete -m`
Deletes a scheduled meeting from the meeting list.

Format: 
    
    delete -m <Meeting Index>

Example of usage:

    delete -m 1

TODO change image
![](images/capture7.png)

Note:
- You can check `<Meeting Index>` of the meeting you wish to delete, by first listing all meetings using `meetings`.
- Be careful to include the -m tag in `delete -m` when you wish to delete a meeting. 
- `delete -m` which is used to delete a meeting, should be differentiated from `delete -c` which is used to delete a contact.
<br/><br/>

### Delete a contact `delete -c`
Deletes a contact from the contact list.

Format: 
    
    delete -c <Contact Index>

Example of usage:

    delete -c 1

Note:
- You can check `<Contact Index>` of the contact you wish to delete, by first listing all added contacts using `contacts`.
- Be careful to include the -c tag in `delete -c` when you wish to delete a contact. 
- `delete -c` which is used to delete a contact, should be differentiated from `delete -m` which is used to delete a meeting.
<br/><br/>

    TODO change image
    ![](images/capture7.png)
<br/><br/>

### List all meetings: `meetings`
List all scheduled meetings stored in program.

Format: 
    
    meetings

Example of usage: 

    meetings

![](images/capture8.png)
<br/><br/>

### Exit the application: `exit`
Exits the application and ends the current session.

Format: 
    
    exit

Example of usage: 
    
    exit

![](images/capture9.png)
<br/><br/>

## FAQ
-  **Q**: How do I transfer my data to another computer? 

   **A**: Copy the `data` file in the root directory and paste into the root directory in the other computer.

## Command Summary
| Feature     | Format      | Example usage |
| ----------- | ----------- | --------------|
| Adding a new contact     | `<name> <NUSMODS link>`      | `Juan https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:01,PTUT:01&CG2027=TUT:01,LEC:01&CG2028=TUT:01,LAB:02,LEC:01&CS2101=&CS2107=TUT:08,LEC:1&CS2113T=LEC:C01` |
| List all contacts     | `contacts`      | `contacts` |
| Display timetable of selected contacts     | `timetable` <br><br> `timetable <Member Index>` <br><br> `timetable <Member A Index> <Member B Index>`      | `timetable` <br><br> `timetable 1` <br><br> `timetable 0 1 2`  |
| Schedule a new meeting     | `schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>`      | `schedule meeting 3 17:00 3 19:00` |
| Edit a contact's timetable     | `edit busy <Contact Index> <Start Day> <Start Time> <End Day> <End Time>` <br><br> `edit free <Contact Index> <Start Day> <Start Time> <End Day> <End Time>`      | `edit busy 0 2 22:00 2 23:00` <br><br> `edit busy 0 2 22:00 2 23:00` |
| Delete a meeting     | `delete -m <Meeting Index>`      | `delete -m 1` |
| Delete a contact     | `delete -c <Contact Index>`      | `delete -c 1` |
| List all scheduled meetings     | `meetings`      | `meetings` |
| Exit application     | `exit`      | `exit` |

