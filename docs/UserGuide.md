# User Guide

## Professor Assistant Console (PAC)
![alt text][https://github.com/AY1920S2-CS2113T-T12-4/docs/images/PAC.png "PAC ver1"]

![alt text][https://github.com/benchan911/tp/blob/Benjamin-UserGuide/docs/images/PAC.png "PAC ver1"]

## 1. Introduction

### 1.1 What is PAC?

PAC is a professor assistant console which helps you keep track of your 
upcoming events. PAC also allows you to add in attendance to keep track 
of your students and their grades. Being a simple Command Line Interface 
(CLI) application, PAC ensures its usability and suitability regardless 
of your expertise level.

### 1.2 Who are our audience?

Professors, teachers, etc.
    
As long as you need to keep track of upcoming events or class, you are our audience.

##
## 2. Setting Up

### 2.1 Requirements 
1.  Ensure you have [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed in your Computer.
1.  Download the latest .jar. from [here](http://link.to/duke).
1.  Copy the file to the folder you want to use as the home folder for this application.
1.  Type `java -jar pac.jar` to start the application.
    <INSERT SCREENSHOT>
1.  You should see this screen if everything is successful.

### 2.2 Startup using Command Line
1.  Open your terminal.
1.  Navigate to the home folder containing PAC.
1.  cd followed by the file path into the terminal as shown below
    <INSERT IMAGE> 
1.  Type `java -jar` followed by the name of the jar file and press **Enter**.

### 2.3 Startup using JAR
1.  Open home folder containing PAC.
1.  Run the packaged JAR file by double clicking it 
    and a window should appear in a few seconds.
    <INSERT IMAGE>
1. Once opened, type in the command when prompted and press **Enter**.

##
## 3. Quick Start

### 3.1 Using PAC
You can type any command, then press **Enter** to execute it.
	
e.g. typing help then pressing **Enter** will open the help window.

Some example commands to try:
* `listEvent:`			List all events
* `addEvent n/CS1010:` 	Add an event named “CS1010”
* `deleteEvent 2:`		Delete 2nd item shown in the list
* `exit:`				Exits the app

Refer to [“Features”](#Features) for details of each command.

##
## Features 

### 4.1 Adding new event: event  `-Event add`
Adds a new event to the event list.

If the event has time/date/venue, use the following commands respectively. 
If the date input does not specify the year, it is by default the current year. 
If the event is a repeatable event, you may add one of the following command:

1. se r/FREQ[/TIME], when FREQ is “Day”, TIME is in 24hr format.
1. Use r/FREQ[/DAY], where FREQ is “Week”, DAY is “Monday”, “Friday”, etc. 
1. Use r/FREQ, where FREQ is “Year”.

Commands: 

`Event add n/EVENTNAME [t/EVENTTIME] [d/EVENTDATE] [v/EVENTVENUE] [r/FREQ[/TIME or /DAY]]`

Examples: 

`Event add n/Event1 t/2.30pm d/6Jun`

`Event add n/Event2 t/5.30pm r/Week/Monday`

### 4.2 Adding students’ attendance to event: attendance `-Attendance add`
Mark a student’s or students’ attendance in the attendance sheet.
Input “yes” or “no” for  p/PRESENT. You may not need to state  p/PRESENT, by default it would be a “yes”.  
You can take multiple students’ attendance at a time, separate the names using “|”.

Command: 

`Attendance add c/CLASS p/PRESENT n/STUDENT_NAME`

Examples: 

`Attendance add c/CS2113T_Tut1 n/John|Mary|...`

`Attendance add c/CS2113T_Tut1 p/no n/Alice`

### 4.3 Adding students’ performance: performance `-Performance add`
Add a student’s grade or mark to the grade list. 
Grade refers to an alphabetical grade while mark refers to a numerical mark given to the student. 
Only one of g/GRADE m/MARK should be provided. 

Command: 

`Performance add a/Assignment n/STUDENT_NAME [g/GRADE or m/MARK]`

Examples: 

`Performance add a/CS2113T_Assignment1 n/John g/A`

`Performance add a/CS2113T_Assignment2 n/John m/70`



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add to-do `todo n/TODO_NAME d/DEADLINE`
