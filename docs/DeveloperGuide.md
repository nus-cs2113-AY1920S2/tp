# Developer Guide

## Design & Implementation
### Design

#### Architecture

#### UI component

#### API system component
The The API system of our application consists of 4 classes: ```TimetableParser ModuleApiParser ModuleHandler LessonsGenerator```
<br>
1. ```TimetableParser``` makes use of regex to sift through timetable link given by a ```String``` object and stores
the semester and the user's module information according to the timetable link provided. It depends on the ```common.Messages``` class to provide the exception message when an incorrect link is being parsed.
2. ```ModuleApiParser``` uses a HTTP GET request to fetch a Json object from the open-sourced NUSMOD API server.
It takes in a ```String```object as module name, and returns a Json object of the module information from method ```.parse()```.

3. ```ModuleHandler```  
4. ```LessonsGenerator```

#### Member component

#### Meeting component

#### Exception classes

#### Common classes


### Implementation
This section describes some noteworthy details of how our application works in the backend.




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
