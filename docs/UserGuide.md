# User Guide

## Introduction

Meeting Organizer is a friendly chatbot that helps you find common free time among you and your friends, using just the NUSMODS links to your school timetable.


## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MeetingOrganizer` from [here](https://github.com/AY1920S2-CS2113T-T12-1/tp/releases).
3. Copy the .jar file to an empty folder.
4. Open Command Prompt and navigate to the folder using ```cd [folder path]```.
5. Run the .jar file using ```java -jar [.jar file name]```.

## Features 

- [Adding a new contact](#adding-a-new-contact)
- [List all contacts](#list-all-contacts-contacts)
- [Display timetable of selected contacts](#display-timetable-of-selected-contacts-timetable)
- [Schedule a new model.meeting](#schedule-a-new-model.meeting-schedule)
- [Delete a scheduled model.meeting](#delete-a-scheduled-model.meeting-delete)
- [Display meetings](#display-meetings-meetings)
- [Exit the application](#exit-the-application-exit)

<br/><br/>

### Adding a new contact
Adds a new person and their class schedule into our list of contacts.

Format: `<name> <NUSMODS link>`

Example of usage:

`
Juan https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:01,PTUT:01&CG2027=TUT:01,LEC:01&CG2028=TUT:01,LAB:02,LEC:01&CS2101=&CS2107=TUT:08,LEC:1&CS2113T=LEC:C01&EG3301R=LAB:G8,LEC:1
`

Expected output:
```
{insert ui}
```
<br/><br/>

### List all contacts: `contacts`
Displays names of all contacts that is stored in program.

Example of usage: `contacts`

Expected output:
```
{insert ui}
```
<br/><br/>

### Display timetable of selected contacts: `timetable`
1) Displays the main user's timetable.
    
    Example of usage: `timetable` 
    
    Expected output:
    ```
    {insert ui}
    ```
2) Displays the timetable of the selected contact.

    Example of usage: `timetable 1` 
    
    Expected output:
    ```
    {insert ui}
    ```

3) Displays the combined timetable of the selected contacts.
   
   Example of usage: `timetable 0 1` 
   
   Expected output:
   ```
   {insert ui}
   ```
   <br/><br/>
   

### Schedule a new model.meeting `schedule`
Schedules a new model.meeting and adds it into the model.meeting list.

Format: `schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>`

Example of usage:

`
schedule model.meeting 3 17:00 3 19:00
`

Expected output:
```
{insert ui}
```
<br/><br/>

### Delete a scheduled model.meeting `delete`
Deletes a model.meeting from the model.meeting list.

Format: `delete <Meeting Index>`

Example of usage:

`
delete 1
`

Expected output:
```
{insert ui}
```
<br/><br/>

### Display meetings: `meetings`
Displays all scheduled meetings stored in program.

Example of usage: `meetings`

Expected output:
```
{insert ui}
```
<br/><br/>

### Exit the application: `exit`
Exits the application and ends the current session.

Example of usage: `exit`

Expected output:
```
Thank you for using MeetingOrganizer, goodbye!
```
<br/><br/>

## FAQ

**Q**: How do I transfer my data to another computer? 

&nbsp;&nbsp;&nbsp;&nbsp;**A**: Copy the `data` file in the root directory and paste into the root directory in the other computer.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add to-do `todo n/TODO_NAME d/DEADLINE`
