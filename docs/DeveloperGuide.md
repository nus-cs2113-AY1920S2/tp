# Developer Guide

## Design & Implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

![Architecture Diagram](https://github.com/DeetoMok/tp/raw/d14fb309c9f816a46ecd19ed26f74b8c8ac1ac03/Architecture.png)

The Architecture Diagram given above explains the high-level design of the Module Manager Application.

Module Manager consists of a main class called Duke responsible for
* At app launch: Initializes the components in the correct sequence, and connects them up with each other
* At shut down: Shuts down the components and invokes cleanup method where necessary

The other components involved are:

`UI`: The user interface of the application

`Storage`: Reads data from, and writes data to, the hard disk

#### UI component
The `UI` component consists of a `Ui` class that stores all user interaction output data. 
It displays all user interactions to the user based on the command line inputs received.

The `UI` component,

*   Executes user commands using the `Logic` component

#### Logic component
The `Logic` component 

1. `Logic` uses the `Parser` class to parse the user command.
2. `Parser` then returns a specific command class e.g. `AddCommand`, `FindCommand` etc. 
which is executed by the main class `Duke`.
All these command classes inherits from the abstract `Command` class.
3. The command execution can affect the Model (e.g. adding a module in ModuleList)
4. The result of the command execution is passed back to the Ui.
5. In addition, the command execution can also instruct the `Ui` to perform certain actions, 
such as displaying help to the user.

#### Model component
The `Model` component
* Stores a `Person` object that represents the user's details. (e.g. `totalCap`, `totalModuleCreditCompleted`)
* Stores the `ModuleList` and `SemesterList` Data
* Does not depend on any of the other three components

#### Storage component



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
![Sequence Diagram of Add to Semester](https://raw.githubusercontent.com/RenzoTsai/tp/master/docs/UML%20img%20folder/Sequence%20Diagram%20of%20Add%20to%20Semester.png)

#### `Mark as Done` feature

#### Calculate CAP feature

The Calculate CAP mechanism is executed by `CalculateCapCommand`.  
`CalculateCapCommand` is extended from `Command` and this implementation calculates the CAP using completed 
`SelectedModule` stored in `SemModulesList`.

Given below is the behaviour of the Calculate CAP mechanism at each step:

Step 1: 
User launches the application. `SelectedModules` are added to `SemModuleList` through either of the following methods:
1) Imported from `semesterList.csv` using `StorageSemesterList.load()`
2) Added using `add id/ID s/SEMESTER mc/MODULE_CREDIT` command

Step 2:
User executes `CAP` command to view his own CAP. The `CAP` commands is parsed through `Parser`, which would then return 
`CalculateCapCommand()`. `CalculateCapCommand.execute()` is then called.

Step 3:
`CalculateCapCommand.execute()` will call `CalculateCapCommand.calculateCap(SemesterList semesterList)`, which will
calculate CAP by looking for all the completed `SelectedModules` stored within `SemModuleList`, which are stored within 
`SemesterList`. It will then assign a `double` type ranging from 0.00 to 5.00 to `Person.totalCap`.

Step 4:
After the CAP is assigned to `Person.totalCap`, `Person.totalCap` is then called and formatted using `DecimalFormat` into a `String`
with a pattern of `#.00`. `Ui.showcap(cap)` is called to display the user's cap using the formatted `String`.

The following diagram shows how the Calculate CAP operation works:
![Calculate CAP feature](http/)

#####



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
