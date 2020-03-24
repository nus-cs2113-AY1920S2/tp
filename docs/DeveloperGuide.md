# Developer Guide

## Design & Implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Implementation

#### `Add to Semester` feature 
The `Add to Semester` mechanism is facilitated by `AddtoSemCommand` which extends from `Command`. 
It allows `ModuleManager` to assign a module to a semester by adding the module to a 
`SemModulesList`, it implements the following operations:

Given below is an example how the `Add to Semester` behaves at each step.

##### Step 1:
The user launches the application for the first time. The `SemesterList` will be initialized with the none 
`SemModulesList` in it.

##### Step 2:
When users enter a add to semester command, e.g `add id/CS2113 s/4 mc/4`, this command will be parsed in `Parser`
and then `Parser` returns a `AddToSemCommand`. `AddToSemCommand` then calls `Command#excuted(SemesterList semesterList,
 AvailableModulesList availableModulesList) `
`(AddToSemCommand#excuted(SemesterList semesterList, AvailableModulesList availableModulesList))`

##### Step 3:
`AddToSemCommand#excuted()` then calls self method `AddToSemCommand#addModule()`.`AddToSemCommand#addModule()`
 then calls `AddToSemCommand#checkModuleExist(semesterList)` to check whether the selected 
module is already in the selected module list (which is`semesterList`). If the module is not in the list, 
`AddToSemCommand#addModule()` will check whether there is a semester list whose name is the module's semester name. If 
the semester list exist, the module will be added to the list. If not, `AddToSemCommand#addModule()` will create a new 
semester list and then add this module to the new list.

#### Step 4:
`AddToSemCommand#excuted()` calls `Ui.showAddedToSemMessage(selectedModule.announceAdded())` to show the result to the 
user

The following sequence diagram shows how the `Add to Semester` operation works:
![Sequence Diagram of Add to Semester](docs/UML img folder/Sequence Diagram of Add to Semester.png)






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
