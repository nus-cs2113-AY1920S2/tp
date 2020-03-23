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
* `bye`				Exits the app

Refer to [“Features”](#Features) for details of each command.

## 4. Features 
### 4.0 SAMPLE FORMAT Insert Header here `Insert format here`
Insert Description here

Format:

`Insert format here`

Command: 

    Insert command here
    
Examples: 

    Insert example here

### 4.1 Feature - Event Tracker
#### 4.1.1 Adding new event: event  `-Event add`
Adds a new event to the event list.

Format:

`Insert format here`

Commands: 

    `event add`
 
Examples: 

    Insert Example here

### 4.2 Attendance Tracker
#### 4.2.1 Adding students’ attendance to event: attendance `attendance add`
Mark a student’s or students’ attendance in the attendance sheet.
Input “yes” or “no” for  p/PRESENT. You may not need to state  p/PRESENT, by default it would be a “yes”.  
You can take multiple students’ attendance at a time, separate the names using “|”.

Format:

`Insert format here`

Command: 

    attendance add
    
Examples: 
    
    Insert Example here




### 4.3 Feature - Performance Tracker
#### 4.3.1 Adding students’ performance: performance `performance add`
Add a student’s result to the performance list.  
This is a step by step command and you may follow the instructions given by the console. 

Command: 

    performance add
    
Step by step guide: 

    user: performance add
    PAC: Please key in the name of event that you wish to access to its student's performance. 
    user: event
If the event is found, you can choose to add the student's result by manually key in each student, or you may choose
the alternative provided by PAC: record using a current name list. Do note that you have to have an existing name list 
before you are using this short cut.  
The following will show a success example of using a current name list to add performance. 

    PAC: Would you like to import an existing student list? If yes, input 'yes'. Else, input anything.
    user: yes
    PAC: Please choose the name list you wish to use. (shows a list of list names)
    user: 1
    PAC: Please key in the result for student (student1 name)
    user: A
    PAC: The result of student (student name) has been added successfully under event (event name)
    PAC: Please key in the result for student (student2 name)
    ...

#### 4.3.2 Deleting students’ performance: performance `performance delete`
Delete a student’s result to the performance list.  
This is a step by step command and you may follow the instructions given by the console. 

Command: 

    performance delete
    
Step by step guide: 

    user: performance delete
    PAC: Please key in the name of event that you wish to access to its student's performance. 
    user: event
    PAC: Please key in the name of student that you wish to delete his/her performance 
    user: name
    PAC: The result of student (name) has been deleted successfully under event name.

#### 4.3.3 Viewing students’ performance: performance `performance list`
View the list of students' result under a certain event.  
This is a step by step command and you may follow the instructions given by the console. 

Command: 

    performance list
    
Step by step guide: 

    user: performance delete
    PAC: Please key in the name of event that you wish to access to its student's performance.
    user: event
    PAC: (example of Table format list is shown below)
    _________________________________________________________________________________________________
    | index     |  Name of Student                    |  Result                                     |
    |___________|_____________________________________|_____________________________________________|
    | 1         |  XX                                 |  A                                          |
    |___________|_____________________________________|_____________________________________________|

### 4.4 - Import Student List
#### 4.4.1 Creating a student list `student add`
Creates a student list and adds it to the collection of student list 
that can be used for adding attendance and performance

Format:

`Insert format here`

Command: 

    student add
    
Examples: 

    Insert example here
    


## Possible Console Messages and Reasons:  
If event list is empty    
        
    PAC: The event list is empty
If event is not found in the list
           
    PAC: Event is not found in the list.
If students' name list is empty
        
    There is no existing student list.
           
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add to-do `todo n/TODO_NAME d/DEADLINE`
