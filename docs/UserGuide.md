# User Guide

## Professor Assistant Console (PAC)

![alt text](images/PAC.png "PAC ver1")  

## Content Page  
1. [Introduction](#1-introduction)  
    1.1. [What is PAC?](#11-what-is-pac)  
    1.2. [Who are our audience?](#12-who-are-our-audience)
2. [Setting Up](#2-setting-up)  
    2.1. [Requirements](#21-requirements)   
    2.2. [Startup using Command Line](#22-startup-using-command-line)  
    2.3. [Startup using JAR](#23-startup-using-jar)   
3. [Quick Start](#3-quick-start)  
    3.1. [Using PAC](#31-using-pac)
4. [Features](#4-features)  
4.1. [Event](#41-event)  
4.1.1. [Add New Event](#411-add-new-event)  
4.1.2. [View Event List](#412-list-events)  
4.1.3. [Delete Existing Event](#413-delete-event)      
4.1.4. [Edit Event -name](#414-edit-event-name)      
4.1.5. [Edit Event -datetime](#415-edit-event-datetime)      
4.1.6. [Edit Event -venue](#416-edit-event-venue)  
4.1.7. [Edit Event](#417-edit-event)    
4.2. [Attendance](#42-attendance)  
4.2.1. [Add New Attendance](#421-add-students-attendance-to-event-attendance-attendance-add)    
4.2.2. [View Attendance List](#422-view-attendance-list-attendance-list)   
4.3. [Performance](#43-performance)     
4.3.1. [Add New Performance](#431-add-performance)  
4.3.2. [Delete Current Performance](#432-delete-performance)   
4.3.3. [View Performance List](#433-view-performance-list)      
4.4. [Student List](#44-student)        
4.4.1. [Add New Student List](#441-add-new-student-list)        
4.4.2. [View Student List](#442-view-all-existing-student-lists-from-the-student-list-collection-student-list)
5. [Possible Console Messages and Reasons](#5-possible-console-messages-and-reasons)           
6. [FAQ](#6-faq)  
7. [Command Summary](#7-command-summary)  
  
## 1. Introduction

### 1.1. What is PAC?

PAC is a professor assistant console which helps you keep track of your 
upcoming events. PAC also allows you to add in attendance to keep track 
of your students and their grades. Being a simple Command Line Interface 
(CLI) application, PAC ensures its usability and suitability regardless 
of your expertise level.

### 1.2. Who are our audience?

Professors, teachers, etc.
    
As long as you need to keep track of upcoming events or class, you are our audience.

## 2. Setting Up

### 2.1. Requirements 
1.  Ensure you have [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 
or above installed in your Computer.
1.  Download the latest PAC.jar from [here](https://github.com/AY1920S2-CS2113T-T12-4/tp/releases).
1.  Copy the file to the folder you want to use as the home folder for this application.
1.  Type `java -jar PAC.jar` to start the application.
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

### 3.1. Using PAC
You can type any command, then press **Enter** to execute it.
	
e.g. typing help then pressing **Enter** will open the help window.

Some example commands to try:
* `event list`			List all events
* `event add n/CS1010` 	Add an event named “CS1010”
* `student add`         Create a new student list
* `bye`				    Exits the app

Refer to [“Features”](#Features) for details of each command.

## 4. Features 
### 4.1. Event
#### 4.1.1. Add New Event
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

#### 4.1.2. List Events
List all events.

Format: `event list`

Expected outcome:

    Here are all the events in your list.
    1. Event: dinner with collegue
    2. Event: World Cup, time: Nov 21 2022 0000
    3. Event: soccer match, time: Jan 23 2020 1900, venue: Kallang

#### 4.1.3. Delete Event
Delete an existing event from the event list.

Format: `event delete i/INDEX`

Examples:

    event delete i/3
    event delete i/4
    
Expected outcome:

    Event: soccer match was deleted successfully from your Event list.
    Index not found
    
#### 4.1.4. Edit Event Name
Change the name of an existing event.


Format: `event editname i/INDEX n/NEW_NAME`
* alphabet cases for the command is not important

Examples:

    event editname i/1 n/lunch with colleague
    
Expected outcome:

    Your Event name was changed from |dinner with collegue| to |lunch with colleague|.
    
#### 4.1.5. Edit Event Datetime
Change the date and time of an existing event.

Format: `event editdatetime i/INDEX d/NEW_DATE t/NEW_TIME`
* alphabet cases for the command is not important

Examples:

    event editdatetime i/1 d/2020-03-23 t/1200
    
Expected outcome:

    Your Event date and time was changed from |yyyy-MM-dd HHmm| to |2020-03-23 1200|.
    Event: lunch with colleague, time: Mar 23 2020 1200
    
#### 4.1.6. Edit Event Venue
Change the venue of an existing event.

Format: `event editvenue i/INDEX v/NEW_VENUE`
* alphabet cases for the command is not important

Examples:

    event editvenue i/1 v/Marina Bay Sands
    
Expected outcome:

    Your Event venue was changed from || to |Marina Bay Sands|.
    Event: lunch with colleague, time: Mar 23 2020 1200, venue: Marina Bay Sands

#### 4.1.7. Edit Event
Change an existing event.

Format: `event editevent i/INDEX n/NEW_NAME [t/NEW_TIME d/NEW_DATE] [v/NEW_VENUE]`
* alphabet cases for the command is not important

Examples:

    event editevent i/1 n/lunch by myself v/home
    
Expected outcome:

    Your Event was edited from |Event: lunch with colleague, time: Mar 23 2020 1800, venue: Marina Bay Sands| to |Event: lunch by myself, venue: home|.

### 4.2 Attendance
#### 4.2.1 Add students’ attendance to event: attendance `attendance add`

Add a student’s attendance to the performance list.  
This is a step by step command and you may follow the instructions given by the console. 
You may wish to use an existing list found in StudentListCollection.

Format: `attendance add`
    
Step by step guide: 

    User:   attendance add
    PAC:    Please key in the name of event that you wish to access to its student's performance. 
    User:   eventName
    
If the event is found, you can choose to add the student's attendance by manually key in each student, or you may choose
the alternative provided by PAC: record using an existing name list located under StudentListCollection. 
Do note that you need to have an existing name list before you are using this shortcut.  
The following will show a success example of *using an existing name list to add attendance*. 

    PAC:    Would you like to import an existing student list? If yes, input 'yes'. Else, input anything.
    User:   yes
    PAC:    Please choose the name list you wish to use by its index. (shows a list of list names)
    User:   1    
    PAC:    Please key in the attendance status for student (student1 name) [Y/N]
    User:   Y
    PAC:    (student name) is (attendance status) for event (event name)
    ...

The following will show a success example of *creating a new attendance list*.

    PAC:    Would you like to import an existing student list? If yes, input 'yes'. Else, input anything.
    User:   no
    PAC:    Please key in student name and result in the following format: n/Student_Name p/Is_Present
    User:   n/John Doe p/Y
    PAC:    Please key in the attendance status for student (student1 name) [Y/N]
    User:   Y
    PAC:    (student name) is (attendance status) for event (event name)
    ...
    
#### 4.2.2 View attendance list: `attendance list`

View the attendance list under a certain event.  

Format: `attendance list`
    
Step by step guide: 

    User:   attendance list
    PAC:    Please key in the name of event.
    User:   eventName
    PAC:    (example of Table format list is shown below)
    _________________________________________________________________________________________________
    | index     |  Name of Student                    |  Result                                     |
    |___________|_____________________________________|_____________________________________________|
    | 1         |  XX                                 |  A                                          |
    |___________|_____________________________________|_____________________________________________|

### 4.3. Performance
#### 4.3.1. Add Performance
Add a student’s result to the performance list.  
This is a step by step command and you may follow the instructions given by the console. 

Format: `performance add`
    
Step by step guide: 

    User:   performance add
    PAC:    Please key in the name of event that you wish to access to its student's performance. 
    User:   event
    
If the event is found, you can choose to add the student's result by manually key in each student, or you may choose
the alternative provided by PAC: record using a current name list. Do note that you have to have an existing name list 
before you are using this short cut.  
The following will show a success example of using a current name list to add performance. 

    PAC:    Would you like to import an existing student list? If yes, input 'yes'. Else, input anything.
    User:   yes
    PAC:    Please choose the name list you wish to use. (shows a list of list names)
    User:   1
    PAC:    Please key in the result for student (student1 name)
    User:   A
    PAC:    The result of student (student name) has been added successfully under event (event name)
    PAC:    Please key in the result for student (student2 name)
    ...

#### 4.3.2. Delete Performance
Delete a student’s result to the performance list.  
This is a step by step command and you may follow the instructions given by the console.  

Format: `performance delete`
    
Step by step guide: 

    User:   performance delete
    PAC:    Please key in the name of event that you wish to access to its student's performance. 
    User:   event
    PAC:    Please key in the name of student that you wish to delete his/her performance 
    User:   name
    PAC:    The result of student (name) has been deleted successfully under event name.

#### 4.3.3. View Performance List
View the list of students' result under a certain event.  
This is a step by step command and you may follow the instructions given by the console. 

Format: `performance list`
    
Step by step guide: 

    User:   performance delete
    PAC:    Please key in the name of event that you wish to access to its student's performance.
    User:   event
    PAC:    (example of Table format list is shown below)
    _________________________________________________________________________________________________
    | index     |  Name of Student                    |  Result                                     |
    |___________|_____________________________________|_____________________________________________|
    | 1         |  XX                                 |  A                                          |
    |___________|_____________________________________|_____________________________________________|

### 4.4. Student
#### 4.4.1. Add New Student List

Creates a student list and adds it to the collection of student list 
that can be used for adding attendance and performance

Command: 

    student add
    
Examples: 

    PAC:    What is the name of your list?
    USER:   CS2113T Tut
    PAC:    Please enter a student Name. If you are finished, enter done
    USER:   John
    PAC:    Please enter a student Name. If you are finished, enter done
    USER:   Jodi
    PAC:    Please enter a student Name. If you are finished, enter done
    USER:   done
    PAC:    Student List created, named : CS2113T Tut
    
#### 4.4.2 View all existing student lists from the student list collection `student list`

View all existing students lists from student list collection.

Command: 

    student list
    
Examples: 

    PAC:    [1] CS2113T Tut
            1. John
            2. Jodi
            --------------
            [2] CS1010
            1. Ryan
            2. Nicole
            3. Leon

## 5. Possible Console Messages and Reasons:  
If event list is empty    
        
    PAC:    The event list is empty
If event is not found in the list
           
    PAC:    Event is not found in the list.
If students' name list is empty
        
    PAC:    There is no existing student list.
           
## 6. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: It is not possible to save data locally and transfer another computer at this version. 
Future patches will allow users to save their data and use it on another computer.

## 7. Command Summary

{Give a 'cheat sheet' of commands here}

* Add to-do `todo n/TODO_NAME d/DEADLINE`

## 8. Contact Us
If you have further queries or feedback on PAC, please contact us at [contact_us@pac.com](contact_us@PAC.com)