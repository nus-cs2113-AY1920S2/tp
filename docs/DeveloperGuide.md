# WhenFree - Developer Guide
By: `AY1920S2-CS2113T-T12-1`

## Table of Contents
* [1. Setting up](#1-setting-up)
    * [1.1. Prerequites](#11-Prerequisites)
    * [1.1. Setting up project](#12-Setting-up-project)
    * [1.1. Verifying setup](#13-Verifying-setup)
* [2. Design](#2-design)
	* [2.1. Architecture](#21-architecture)
	* [2.2. UI component](#22-ui-component)
	* [2.3. Logic component](#23-logic-component)
	* [2.4. Model component](#24-model-component)
	* [2.5. Storage component](#25-storage-component)
	* [2.7. Common classes](#26-common-classes)
* [3. Implementation](#3-implementation)
	* [3.1. Add new contact](#31-add-new-contact)
	* [3.2. List all contacts](#32-list-all-contacts)
	* [3.3. Display timetable of selected contacts](#33-display-timetable-of-selected-contacts)
	* [3.4. Schedule a new meeting](#34-schedule-a-new-meeting)
	* [3.5. Delete a scheduled meeting](#35-delete-a-scheduled-meeting)
	* [3.6. List all scheduled meetings](#36-list-all-scheduled-meetings)
* [4. Documentation](#4-documentation)
* [5. Testing](#5-testing)
* [6. Dev Ops](#6-dev-ops)
* [Appendix A: Product Scope](#appendix-a-product-scope)
	* [A.1. Target user profile](#a1-target-user-profile)
	* [A.2. Value proposition](#a2-value-proposition)
* [Appendix B: User Stories](#appendix-b-user-stories)
* [Appendix C: Use Cases](#appendix-c-use-cases)
* [Appendix D: Non-Functional Requirements](#appendix-d-non-functional-requirements)
* [Appendix E: Glossary](#appendix-e-glossary)
* [Appendix F: Product Survey](#appendix-f-product-survey)
* [Appendix G: Instructions for Manual Testing](#appendix-g-instructions-for-manual-testing)
	* [G.1. Launch and Shutdown](#g1-launch-and-shutdown)
	* [G.2. Saving data](#g2-saving-data)

## 1. Setting up
Prerequisites<br>
1. Ensure Java Development Kit(JDK) is 11 or above.
2. IDE of your choice. :+1: We recommend IntelliJ IDE since this project is built upon the features of IntelliJ
3. The setting up of this project would assume that Intellij IDE is your preferred choice of IDE.

Setting up the project<br>
1. Fork this repository and ```git clone``` it onto your computer.
2. Open Intellij and import the ```build.gradle``` from project directory of the cloned repository.
[NOTE] If this your first time opening Intellij, you will be greeted wi4. Open ```Gradle``` from the vertical right tabs and type ```gradle run``` to build the project.th a welcome screen. Click ```import project``` > ```build.gradle``` to open up the project.
3. Set up the correct JDK version for Gradle:
    * Click the ```File``` tab > ```Project Structure``` > ```Platform Settings``` > ```SDK```.
    * Select the path where your JDK(>11) is installed. Click this link if you do not have at least JDK11.

Verifying Setup
1. Open ```Gradle``` from the vertical right tabs and type ```gradle run``` to build the project.
2. Try out a few commands as shown on the CLI menu. Click here to know more about each commands.


## 2. Design

### 2.1. Architecture
![Architecture Diagram](images/architecture.png)<br>

The architecture diagram above shows an overview of the high-level design of MeetingOrganizer. Meeting Organizer
adopts an n-tier style architecture where higher layers make use of the services provided by the lower layers.
Here is a quick overview of each layer and the components residing in it.
* UI: The CLI user interface of the application.
* Commons: A collection of classes containing constants such as messages for ```common.exception```, modules that can't be formatted, etc.
* Logic: The main control unit of the application which handles the business logic of the application.
* Model: Holds the data of the application in memory which is easily accessible by any methods that requires it.
* Storage: Writes data from Model layer to hard disk, as well as reading previously saved data from hard disk and storing it into Model layer.

### 2.2. UI component
[Structure of UI layer]

The UI consists of....
### 2.3. Logic component
![Logic Component](images/logiccomponent.png)<br>

The LogicManager is the brain and backbone of the logic component. It depends on 3 sub-components for it to work.
First, LogicManager instantiates```ScheduleLogic``` and ```ModuleLogic``` sub-components to enable the generation of common time slots from NUSMODS links.
Afterwards, ```Command``` sub-component would be initialized to interpret the user commands. LogicManager forms a whole-part relationship with Model component, where
all the data gathered from user commands would be stored.

### 2.3.1. Logic.modulelogic component

The modulelogic component retrives modules and module information from NUSMODS links.
The modulelogic component consists of 4 classes: ```TimetableParser```, ```ModuleApiParser```, ```ModuleHandler``` and  ```LessonsGenerator```.

1. ```LessonsGenerator``` uses the ```TimetableParser``` class to acquire the modules and the timeslots that a user has.
2. ```LessonsGenerator``` also uses ```Modulehandler``` to retrieve a set of information related to a specific module.
3. With both information, ```LessonsGenerator``` is able to dynamically generate the user's time-slots stored in ```ArrayList<String[]>``` via a series of Key-Value pair hashing.
4. ```Arraylist<String[]> ``` contains the start and end times, days and weeks information of all modules the user is taking.
<br>

**Design of Logic.modulelogic component**
 
![logic.modulelogic Component](images/TimetableParser.png)<br>
The above figure shows```TimetableParser```, a private class called exclusively by ```LessonsGenerator```. It makes use of regex to sift through timetable link provided by user in the form of ```String``` object and stores
the user's module information according to the timetable link provided. It depends on the ```common.Messages``` class to provide the exception message when an incorrect link is being parsed.<br>

![logic.modulelogic Component](images/ModuleHandler.jpg)<br>
From the figure above, ```ModuleApiParser``` instantiates a HTTP GET request object to fetch a Json object from the open-sourced NUSMOD API server, and is called by ```ModuleHandler``` every time a particular module information is requested.
```ModuleApiParser``` would return the moduleInformation in Json.

Finally, ```ModuleHandler``` cleans the data and filter out any blacklisted modules provided by ```ModuleApiParser```, and stores an easy to use data structure to be used by ```LessonsGenerator```.

Blacklisted modules are filtered out based on the data from ```common.BlacklistedModule```. 

[NOTE] Blacklisted modules are modules that doesn't follow the conventional
13 weeks programme and as such, ```ModuleHandler``` is unable to handle the JSON and parse it correctly.
<br>
<br>

![logic.modulelogic Component](images/LessonsGenerator.jpg)<br>

The above figure shows a full overview of the UML sequence of the entire Logic.modulelogic component.<br>

The last step would be for ```LessonsGenerator``` to collate the returned data structure from both ```ModuleHandler``` and ```TimetableParser```, calling```lessonsChecker()``` simultaneously to create a set of information containing the start-time, end-time, day, weeks of the modules that a user is taking.
 <br>
 
 The information returned from ```LessonsGenerator``` would then be used in Logic.schedulelogic component.
 
### 2.3.2. Logic.schedulelogic component

The ```schedulelogic``` component finds common time slots from team members' schedules.
The ```schedulelogic``` consists of the class ```ScheduleHandler```. 

1. ```ScheduleHandler``` retrieves the schedule of selected ```Contact```s in the ```ContactList```, to generate a combined schedule.
2. ```ScheduleHandler``` checks if a time slot fits in the main user's schedule.

**Detailed implementation of logic.schedulelogic component**

### 2.3.4. logic.commands component
The ```commands``` component interprets the user command and call the ```modulelogic``` and ```schedulelogic``` components.
The ```commands``` consists of the class ```CommandHandler```.

### 2.4. model component
The ```model``` component holds data generated in the application in memory. The data can be accessed by methods that require
it when the application is running. The model component contains 2 sub-components: ```meeting```, ```contact```

### 2.4.1. model.meeting component
The ```meeting``` component of our application consists of 2 classes: ```Meeting```, ```MeetingList```
<br>

### 2.4.2. model.contact component
The ```contact``` component of our application consists of 2 classes: ```TeamMember```, ```TeamMemberList```
1. ```TeamMember``` consists of information of a member's name and schedule.
2. ```TeamMemberList``` is a ```Arraylist<TeamMember> ``` which new ```TeamMember``` can be added to.

### 2.5. Storage component

![storage component structure](images/storage_uml.png)

Above image shows the structure of Storage object. It is created by MeetingOrganizer class to handle the loading and saving of scheduled meetings and member schedules.

The `Storage` component,
- can save `Contact` objects in .txt format and read it back.
- can save scheduled meetings in .txt format and read it back.

### 2.6. Exception classes

### 2.7. Common classes


## 3. Implementation
This section describes some noteworthy details of how the main features of our application works in the backend.

There are 6 main features: add new contact, list all contacts, display combined timetable of selected contacts, schedule a new meeting, delete a scheduled meeting, list all scheduled meetings.
### 3.1 Add new contact

### 3.1.1 Design Considerations
**Aspect 1: Fetching of module information**
* Alternative 1(current choice): Instantiate a ```ModuleHandler``` every time there's a request for a module information.
Pros: The classes are intuitively separated and data structures returned is understandable.
Cons: Program runs slower for every extra timetable or extra modules taken since its a new instantiation of a ```ModuleHandler```.
* Alternative 2: Instantiate ```ModuleHandler``` once for every user. 
Pros: Takes up less memory and setup time for every timetable provided compared to alternative 1.
Cons: The data structure returned by ```ModuleHandler``` would be complicated and confusing for new developers.

**Aspect 2: Storing blacklisted module information**
* Alternative 1(current choice): Create a ```common.BlacklistedModule``` and hash every hard-coded blacklisted module as a constant ```HashSet```.
Pros: There is no need for user to download the blacklisted module, and only the JAR file is required to run this entire application.
Also, user do not need to have a one time set-up where they would wait several minutes for the application to dynamically pull the blacklisted modules from Nusmods API server.
Cons: If the blacklisted modules from Nusmods API gets updated to the conventional 13 weeks programme, developers would have to manually delete the information of those modules from
the blacklist, resulting in time wasted everytime there's an update to module information.
* Alternative 2: Dynamically pull the data from Nusmods API server once when user starts the application to retrieve the blacklisted modules, and then periodically update the 
blacklisted modules every semester.
Pros: The blacklisted modules would be up to date and there is no need for developers to manually edit the ```common.BlacklistedModule``` class.
Cons: The one-time set up of pulling the data is very time consuming(~2 minutes waiting time), resulting is bad user experience.
* Alternative 3: Requires user to download the list of blacklisted modules in addition to the JAR file. 
Pros: User do not have to wait for the one-time set up and the file would be up to date as long as the application is not deprecated.
Cons: Developers would still have to run the method to dynamically pull the blacklisted modules, although it would be less prone to mistake caused by editing the hard-coded blacklist as mentioned in
Alternative 1. Furthermore, users are required to download the blacklisted file published by the developers every semester in order for the list to be up-to-date.

### 3.2 List all contacts

### 3.3 Display timetable of selected contacts

### 3.4 Schedule a new meeting

### 3.5 Delete a scheduled meeting

### 3.6 Delete a member

### 3.6 List all scheduled meetings

## 4. Documentation

## 5. Testing

## 6. Dev Ops

## Appendix A: Product Scope
### A.1. Target user profile
Our application, MeetingOrganizer, is for NUS students and teaching assistants looking to save time finding
free-slots for their project meetings, consultations etc. It allow users to add their team members and tutees respectively, and
finding a time-slot where everyone would be free.
Target user profile:
* Our application if for users that are comfortable using CLI apps and prefer desktop applications rather than phone apps.
* Our application targets users in NUS that wish to easily find free-slots amongst their peers studying in NUS as well.
* Our application tracks the meetings the user have and shows them on a timetable generated dynamically in CLI.


### A.2. Value proposition

{Describe the value proposition: what problem does it solve?}

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Appendix C: Use Cases

## Appendix D: Non-Functional Requirements

{Give non-functional requirements}

## Appendix E: Glossary

* *glossary item* - Definition

## Appendix F: Product Survey

## Appendix G: Instructions for Manual Testing

Given below are instructions to test the app manually.

> :information_source: These instructions only provide a starting point for testers to work on; testers are expected to do more _exploratory_ testing. 

### G.1. Launch and Shutdown

### G.2. Saving data
