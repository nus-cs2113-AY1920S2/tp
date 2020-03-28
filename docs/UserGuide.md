# User Guide

## Introduction

Meeting Organizer is a friendly chatbot that helps you find common free time among you and your friends, using just the NUSMODS links to your school timetable.


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

- [Adding a new contact](#adding-a-new-contact)
- [Display contacts](#display-contacts-contacts)
- [Display timetable](#display-timetable-timetable)
- [Edit contact's timetable](#edit-contacts-timetable-edit)
- [Schedule a new meeting](#schedule-a-new-meeting-schedule)
- [Delete a scheduled meeting](#delete-a-scheduled-meeting-delete)
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

### Display contacts: `contacts`
Displays names of all contacts that is stored in program.

Example of usage: `contacts`

Expected output:
```
{insert ui}
```
<br/><br/>

### Display timetable: `timetable`
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
   
### Edit contact's timetable `edit`
Edits the timetable of a contact from the list of contacts.

Format: `edit <Member Number> <busy/free> <startDay> <startTime> <endDay> <endTime>`

Example of usage:

`
edit 0 busy 2 22:00 2 23:00
`

Expected output:
```
{insert ui}
```
<br/><br/>

### Schedule a new meeting `schedule`
Schedules a new meeting and adds it into the meeting list.

Format: `schedule <Meeting Name> <Start Day> <Start Time> <End Day> <End Time>`

Example of usage:

`
schedule meeting 3 17:00 3 19:00
`

Expected output:
```
{insert ui}
```
<br/><br/>

### Delete a scheduled meeting `delete`
Deletes a meeting from the meeting list.

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
