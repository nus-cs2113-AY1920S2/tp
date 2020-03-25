# Developer Guide

## Design & Implementation
### Design

#### Architecture

#### UI component

#### Module parsing logic component
**Structure of the module logic component.**
<br>
The module parsing logic of our application consists of 4 classes: ```TimetableParser ModuleApiParser ModuleHandler LessonsGenerator```.
A detailed implementation would be explained in the implementation section.
<br>
 API: ```modulelogic.LessonsGenerator```
<br>
1. ```LessonsGenerator``` uses the ```TimetableParser``` class to acquire the modules a user is taking, including the timeslots of those modules.
2. ```LessonsGenerator``` also uses ```Modulehandler``` to retrieve a set of information related to a specific module.
3. With both information, ```LessonsGenerator``` is able to dynamically generate the user's time-slots stored in ```ArrayList<String[]>``` via a series of Key-Value pair hashing.
4. ```Arraylist<String[]> ``` contains the start/end time, days and weeks of all modules the user is taking.
<br>



#### Member component

#### Meeting component

#### Exception classes

#### Common classes


### Implementation
This section describes some noteworthy details of how our application works in the backend.
#### Detailed implementation of modulelogic component.
![modulelogic Component](images/TimetableParser.png)<br>
The above figure shows```TimetableParser```, a private class called exclusively by ```LessonsGenerator```. It makes use of regex to sift through timetable link provided by user in the form of ```String``` object and stores
the semester and the user's module information according to the timetable link provided. It depends on the ```common.Messages``` class to provide the exception message when an incorrect link is being parsed.
![modulelogic Component](images/ModuleHandler.jpg)<br>
 From the figure above, ```ModuleApiParser``` instantiates a HTTP GET request object to fetch a Json object from the open-sourced NUSMOD API server, and is called by ```ModuleHandler``` every time a particular module information is requested.
 <br>
```ModuleHandler``` cleans the data provided by ```ModuleApiParser``` and stores an easy to use data structure to be used by ```LessonsGenerator```.
![modulelogic Component](images/LessonsGenerator.jpg)<br>
 Finally, ```LessonsGenerator``` collates the returned data structure from both ```ModuleHandler```(looped for as many modules the user takes) and ```TimetableParser```, calling```.lessonsChecker()``` simultaneously to create a set of information containing the start-time, end-time, day, weeks of the modules that a user is taking.
 <br>
 The information from ```LessonsGenerator``` would then be included in the schedule of a particular ```TeamMember```.




## Product Scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
