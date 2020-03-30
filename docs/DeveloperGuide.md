# Developer Guide

## Design & Implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

![Architecture Diagram](https://github.com/DeetoMok/tp/raw/master/docs/images/Architecture.png)

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
2. The parsed command is passed to `Controller` which then returns a specific command class 
e.g. `AddCommand`, `FindCommand` etc. which is executed by the main class `Duke`.
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
The `Storage` component,
* can save `personInfo` objects in csv format and read it back
* can save the available module list in csv format and read it back
* can save the semester list in csv format and read it back 


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
and then `Parser` returns a `AddToSemCommand`. `AddToSemCommand` then calls `Command#execute(SemesterList semesterList,
 AvailableModulesList availableModulesList) `
`(AddToSemCommand#execute(SemesterList semesterList, AvailableModulesList availableModulesList))`

##### Step 3:
`AddToSemCommand#execute()` then calls self method `AddToSemCommand#addModule()`.`AddToSemCommand#addModule()`
 then calls `AddToSemCommand#checkModuleExist(semesterList)` to check whether the selected 
module is already in the selected module list (i.e:`semesterList`, which is a `PriorityQueue<SemModulesList>`). 
If the module is not in the list, `AddToSemCommand#addModule()` will check whether there is a semester list
(i.e:`semesterModulesList`, which is a `ArrayList<SelectedModule>`) whose name is the module's semester name. 
If the semester list exist, the module will be added to the list. 
If not, `AddToSemCommand#addModule()` will create a new semester list and then add this module to the new list. and the
the new semester list will be added to `semesterList` as well.

#### Step 4:
`AddToSemCommand#execute()` calls `Ui.showAddedToSemMessage(selectedModule.announceAdded())` to show the result to the 
user

The following sequence diagram shows how the `Add to Semester` operation works:
![Sequence Diagram of Add to Semester](https://github.com/RenzoTsai/tp/blob/ReviseCode_UpdateDG/docs/UML%20img%20folder/Sequence%20Diagram%20of%20Add%20to%20Semester.png)


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
![Calculate CAP feature](https://github.com/bennychanya/tp/blob/master/CalculateCap.png?raw=true)


#####



## Product Scope
### Target user profile

* A computer science undergraduate of NUS with a need to manage modules
* Prefer desktop apps over other types
* Able to type quickly
* Prefers to control apps with typing rather than a mouse
* Comfortable using Command Line Input apps

### Value proposition

Manage and plan modules quickly with CLI, faster than a mouse or GUI driven app 

## User Stories

|Priority| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|***|User|View my study plan|Keep track of what is my study plan when I forget about it|
|***|User|Add and assign modules to different semesters|Update my study plan|
|***|User|Add modules to available module list|Add the module to my study plan when I plan to in the future|
|***|User|Delete study plans in specific semesters|Update my study plan according to my new plan in mind|
|***|New user|see usage instructions|Refer to instructions when I forgot how to use the App|
|***|User|Mark module as done|Update my study plan according to modules that I have completed|
|**|User|Calculate cap|Check my current cap based on modules I have completed|
|**|User|find a module by name or module code|Locate a module and its module code without having to go through all the modules|


## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 modules in the available module list without a noticeable sluggishness in
 performance for typical usage.
 3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands)
  should be able to accomplish most of the tasks faster using commands than using the mouse.


## Glossary

**Mainstream OS** - Windows, Linux, Unix, OS-X



## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}


