# User Guide

## Professor Assistant Console (PAC)

![alt text](images/PAC.png "PAC ver1")

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
* `event list`			List all events
* `event add n/CS1010` 	Add an event named “CS1010”
* `student add`         Create a new student list
* `bye`				    Exits the app

Refer to [“Features”](#Features) for details of each command.

##
## Features 

### 4.1 Event
#### 4.1.1 Add new event: `event add`
Add a new event to the event list. 

Format: `event add n/NAME [t/TIME d/DATE] [v/VENUE]`

* Name and venue accepts input with spaces.
* Date and Time must either be provided together, or not provided at all. 
* Flags can be arranged in any order.
 
Examples: 

    event add n/dinner with collegue
    event add n/World Cup d/2022-11-21 t/0000
    event add n/soccer match v/Kallang d/2020-01-23 t/1900
    
Expected outcome:

    New Event: dinner with collegue was added successfully to your Event list.
    New Event: World Cup was added successfully to your Event list.
    New Event: soccer match was added successfully to your Event list.

#### 4.1.2 List all events: `event list`
List all events.

Format: `event list`

Expected outcome:

    Here are all the events in your list.
    1. Event: dinner with collegue, time: (current date and time of your computer)
    2. Event: World Cup, time: Nov 21 2022 0000
    3. Event: soccer match, time: Jan 23 2020 1900, venue: Kallang

#### 4.1.3 Delete event: `event delete`
Delete an existing event from the event list.

Format: `event delete i/INDEX`

Examples:

    event delete i/3
    event delete i/4
    
Expected outcome:

    Event: soccer match was deleted successfully from your Event list.
    Index not found
    
#### 4.1.4 Edit event name: `event editName`
Change the name of an existing event.

Format: `event editname i/INDEX n/NEW_NAME`
* alphabet cases for the command is not important

Examples:

    event editname i/1 n/lunch with colleague
    
Expected outcome:

    Your Event name was changed from |dinner with collegue| to |lunch with colleague|.
    
#### 4.1.5 Edit event datetime: `event editDateTime`
Change the date and time of an existing event.

Format: `event editdatetime i/INDEX d/NEW_DATE t/NEW_TIME`
* alphabet cases for the command is not important

Examples:

    event editdatetime i/1 d/2020-03-23 t/1200
    
Expected outcome:

    Your Event date and time was changed from |yyyy-MM-dd HHmm| to |2020-03-23 1200|.
    Event: lunch with colleague, time: Mar 23 2020 1200
    
#### 4.1.6 Edit event venue: `event editVenue`
Change the venue of an existing event.

Format: `event editvenue i/INDEX v/NEW_VENUE`
* alphabet cases for the command is not important

Examples:

    event editvenue i/1 v/Marina Bay Sands
    
Expected outcome:

    Your Event venue was changed from || to |Marina Bay Sands|.
    Event: lunch with colleague, time: Mar 23 2020 1200, venue: Marina Bay Sands

#### 4.1.7 Edit event: `event editEvent`
Change an existing event.

Format: `event editevent i/INDEX n/NEW_NAME [t/NEW_TIME d/NEW_DATE] [v/NEW_VENUE]`
* alphabet cases for the command is not important

Examples:

    event editevent i/1 n/lunch by myself v/home
    
Expected outcome:

    Your Event was edited from |Event: lunch with colleague, time: Mar 23 2020 1800, venue: Marina Bay Sands| to |Event: lunch by myself, venue: home|.

### 4.2 Attendance
#### 4.2.1 Add students’ attendance to event: attendance `attendance add`
Mark a student’s or students’ attendance in the attendance sheet.
Input “yes” or “no” for  p/PRESENT. You may not need to state  p/PRESENT, by default it would be a “yes”.  
You can take multiple students’ attendance at a time, separate the names using “|”.

Format: `Insert format here`

Command: 

    attendance add
    
Examples: 
    
    Insert Example here

### 4.3 Performance
#### 4.3.1 Add students’ performance: performance `performance add`
Add a student’s grade or mark to the grade list. 
Grade refers to an alphabetical grade while mark refers to a numerical mark given to the student. 
Only one of g/GRADE m/MARK should be provided. 

Format: `Insert format here`

Command: 

    performance add
    
Examples: 

    Insert example here


### 4.4 Student
#### 4.4.1 Create a student list `student add`
Creates a student list and adds it to the collection of student list 
that can be used for adding attendance and performance

Format: `Insert format here`

Command: 

    student add
    
Examples: 

    Insert example here
    
    
### 4.5 Calendar 
#### 4.3.1 Add students’ performance: performance `performance add`
#### 4.5.1 View events under a particular time : `calendar s/2 ay/19-20`  
View existing events under a particular semester and academic year.
Semester refers to a numerical number, 1 or 2 while academic year refers 
to any 2 consecutive years with a hyphen separating them. 


Format: `calendar s/SEMESTER ay/YEAR_ONE-YEAR_TWO `

Command: 

    calendar s/2 ay/19-20
    
Examples: 

     _______________________________________________________________________ 
                          SEMESTER 2 AY 19/20 
     _______________________________________________________________________ 
    | JUL       | AUG       | SEP       | OCT       | NOV       | DEC       |
    |___________|___________|___________|___________|___________|___________|
    |           | talk      | slideshow |           |           |           |
    |___________|___________|___________|___________|___________|___________|
    |           | interview |           |           |           |           |
    |___________|___________|___________|___________|___________|___________| 
    
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add event `event add n/NAME d/DATE t/TIME v/VENUE`
* list events `event list`
* Edit name of existing event `event editname i/INDEX n/NAME`
* Edit date and time of existing event `event editdatetime i/INDEX d/DATE t/TIME`
* Edit venue of existing event `event editvenue i/INDEX v/VENUE`
* Delete event `event delete i/INDEX`
* View calendar `calendar s/SEMESTER ay/YEAR_ONE-YEAR_TWO`
