# Developer Guide

## Design & Implementation

### Event component

![event](images/event.png "Class diagram of Event component")

*Class diagram of the Event component*

1. When a user enters an event-related command, the command is analysed by `EventCommandInterpreter`. 
1. Once determined, the relevant information (e.g. index, name, time, date, venue) are extracted by 
`EventParser`.
1. Then, the relevant class that corresponds to the command is created, with the information extracted 
from the previous step passed into it. It modifies `Event` or `EventList`.
1. These commands are then returned to `Duke.run()` to `execute()`. 

Note that:
* `datetime` is stored as a single attribute in `Event` class, but it is exposed to user as `date` 
and `time`, which corresponds to `d/` and `t/` flag respectively.
* `editDate` or `editTime` commands are not available. Only `editDateTime` is available to change the 
`date` and/or `time` of an `Event` object.
* `delete(Event)` method is currently not in use, but can be used to implement delete by event name, 
either by complete match, or fuzzy match.
* Any classes (e.g. `Seminar`) that inherit from `Event` class will have similar control flow. 

###Calendar Implementation

*Figure 2: Class diagram of the Calendar component*

1. When a user enters a calendar-related command, the command is analysed by `CalendarCommandInterpreter`.
1. Once determined, the relevant information (eg. semester, academic year) are extracted by `CalendarParser`.
1. Then, either AddFirstSemester or AddSecondSemester class that corresponds the semester number is created. 
1. Subsequently, it separates events by the required month and year in `CalendarList`
1. These commands are then returned to `Duke.run()` to `execute()`. 

Note that:
* `acadamic year` is parsed into corresponding to only one year according to the semester in `EventParser` class.
* Calendar view of the whole year is not available. Only semester 1 or 2 of an academic year can be viewed at a time.
* Event name size must be less than 10 characters to be displayed neatly (current implementation), however
it can be implement to truncate longer names to fit nicely

## Product Scope
### Target user profile

Our target audiences are professors who need help organizing their personal work schedule and need more time.
The professors are pressed for time and they require a simple software to organize their monthly events
and keep track of their students' attendance and performance. 


### Value proposition

Our application will reduce the stress of the professor by allowing them to easily enter and store
their work schedule as well as their students' records. After storing the data, the Professor can have
quick access to the information in either a list or a calendar view. 

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|professor|add new events|creating new events|
|v1.0|professor|delete existing events|delete unnecessary events|
|v1.0|professor|add new attendance list|create new attendance list to be added to events|
|v1.0|professor|delete existing attendance list|delete unnecessary attendance list|
|v1.0|professor|add new performance list|create new performance list to be added to events|
|v1.0|professor|delete existing performance list|delete unnecessary performance list|
|v1.0|professor|add date and time to my events|organize my events|
|v1.0|professor|add venue to my events|locate events|
|v1.0|professor|edit my events|update my existing events|
|v2.0|professor|create a student list|link existing student list to performance list or attendance list|
|v2.0|professor|Create repeatable events without having the need to manually add in|easily create occurring events|
|v2.0|professor|find a to-do item by name|locate a to-do without having to go through the entire list|
|v2.0|professor|view calendar of all my events|to see a overview of them|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *flag* - anything that takes the form of  `?/`, e.g. `n/`, `i/`

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
1. Download the jar file and copy it into an empty folder.
1. View the populated events by typing `event list`
1. View the populated seminars by typing `seminar list`
1. Delete an event by typing `delete i/INDEX`
1. Edit an existing event's name by typing `event editname i/INDEX n/NAME`
1. Edit an existing event's date and time by typing  
`event editdatetime i/INDEX d/DATE t/TIME`
1. Edit an existing event's venue by typing   
`event editvenue i/INDEX v/VENUE`
1. Display calendar by entering  
`calendar s/SEMESTER ay/YEAR_ONE-YEAR_TWO`