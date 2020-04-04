# Developer Guide

# 1. Introduction
## 1.1 Purpose
The document contains the specified architecture and software design specifications for the application, Module Manager.

## 1.2 Scope
This describes the software architecture and software design requirements for Module Manager. 
This guide is mainly for developers, designers and software engineers that are or going to work on Module Manager.

# 2. Setting up
## 2.1 Prerequisites
1. JDK `11`.
2. IntelliJ IDE.

## 2.2 Setting up the project in your computer
1. Fork this repository, and clone the fork repository to your computer
2. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project 
dialog first)
3. Set up the correct JDK version for Gradle
    * Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
    * If `JDK 11` is listed in the drop down, select it. Otherwise, click `New…` and select the directory where you 
    installed `JDK 11`
    * Click `OK`
4. Click `Import Project`
5. Locate the `build.gradle` file and select it. Click `OK`
6. Click `Open as Project`
7. Click `OK` to accept the default settings if prompted

## 2.3 Verifying the setup
1. Run Module Manager to verify and try a few commands. Do refer to the user guide for a list of commands to try out.
2. Run the JUnit Tests/gradlew test command to ensure that all test case passes.

## 2.4 Configurations to do before writing code
**Configuring the coding style**
* Module Manager uses CheckStyle to check for code quality violations.
* To configure your project to use CheckStyle, add `id 'checkstyle'` under plugins for your `build.gradle` file.
* Ensure that your CheckStyle toolVersion is 8.23 by adding `toolVersion = '8.23'` into your `build.gradle` file.
Refer to Module Manager's `build.gradlew` file as a reference to set up CheckStyle correctly.

**Getting started with coding**
When you are ready to start coding, we recommend that you get a sense of the overall design by reading about 
Module Manager's architecture in the next section.

# 3. Design
This section provides a high level overview of our application, Module Manager.
## Design & Implementation


### 3.1 Architecture

![Architecture Diagram](https://github.com/DeetoMok/tp/raw/master/docs/images/Architecture.png)

The Architecture Diagram given above explains the high-level design of the Module Manager Application.

Module Manager consists of a main class called Duke responsible for
* At app launch: Initializes the components in the correct sequence, and connects them up with each other
* At shut down: Shuts down the components and invokes cleanup method where necessary

The other components involved are:

`UI`: The user interface of the application

`Parser`: This class mainly handles the parsing and handling of user commands

`Command`: This class handles the type of command

`Person`: This class manages the data of the user in memory

`Controller`: This class determines what to do with the parsed input of the user 

`Storage`: Reads data from, and writes data to, the hard disk


#### 3.2 UI component
![Ui Diagram](https://github.com/DeetoMok/tp/raw/master/docs/images/Ui.png)

The `UI` component consists of a `Ui` class that stores all user interaction output data. 
It displays all user interactions to the user based on the command line inputs received.

The `UI` component,

*   Executes user commands using the `Logic` component

#### 3.3 Logic component

![Object Diagram of Logic Component](https://github.com/DeetoMok/tp/raw/master/docs/images/Object_Diagram_of_Logic_Component.png)

The `Logic` component 
1. `Logic` uses the `Parser` class to parse the user command.
2. The parsed command is passed to `Controller` which then returns a specific command class 
e.g. `AddCommand`, `FindCommand` etc. which is executed by the main class `Duke`.
All these command classes inherits from the abstract `Command` class.
3. The command execution can affect the Model (e.g. adding a module in ModuleList)
4. The result of the command execution is passed back to the Ui.
5. In addition, the command execution can also instruct the `Ui` to perform certain actions, 
such as displaying help to the user.

#### 3.4 Model component

![Class Diagram of Model Component](https://github.com/DeetoMok/tp/raw/master/docs/images/Class_Diagram_of_Model_Component.png)

The `Model` component is responsible for serving as a boundary between the `Controller` component and `Storage` 
component. 

The responsibilities of the `Model` component includes
* Storing the data in-memory during programme runtime. It stores all `SelectedModule` objects in an 
`ArrayList<SelectedModule>` in a `SemModulesList` class. This represents a semester of the user's module plan.
* All `ArrayList<SelectedModule>` is then stored in a `PriorityQueue<SemModulesList>` which contains `SemModulesList`
in an ordered fashion. This class is called `SemesterList`, which represents the entire module plan of the user.


#### 3.5 Storage component


The `Storage` component,
* can save `personInfo` objects in csv format and read it back
* can save the available module list in csv format and read it back
* can save the semester list in csv format and read it back 


### 4. Implementation
This section describes some details on how the features are being implemented. 
All features can be broken down into 4 distinct functionalities - addition, deletion, marking and searching.


#### `Add to Semester` feature 
The `Add to Semester` mechanism is facilitated by `AddtoSemCommand` which extends from an abstract class `Command`. 
It allows `ModuleManager` to assign a module to a semester by adding the module to a 
`SemModulesList`. 

Definition:

`SemModuleList` - An array list of `Module` objects, used to contain modules allocated to a specific semester. 
Signifies a semester in the user's module plan.

`SemesterList` - A priority queue of `SemModuleList`, used to contain `semModuleList` in an sorted order. 
Signifies all semesters of the user's module plan.  

This feature implements the following operations:
Given below is an example how the `Add to Semester` behaves at each step.

##### Step 1:
The user launches the application for the first time. The `SemesterList` will initialize an empty 
`SemModulesList`.

##### Step 2:
When users enter an add to semester command, e.g `add id/CS2113 s/4 mc/4`, the command will be parsed in `Parser`
which will return an `AddToSemCommand`. `AddToSemCommand` then calls `Command#execute(SemesterList semesterList,
 AvailableModulesList availableModulesList) `
`(AddToSemCommand#execute(SemesterList semesterList, AvailableModulesList availableModulesList))`

##### Step 3:
`AddToSemCommand#execute()` then calls self method `AddToSemCommand#addModule()` which calls 
`AddToSemCommand#checkModuleExist(semesterList)` to check whether the selected module is already in the 
user's module plan (i.e:`semesterList`). 
If the module is not in the list, `AddToSemCommand#addModule()` will check whether there is a semester list
(i.e:`semesterModulesList`) whose name is equal to the module's semester name. 
If the semester list exists, the module will be added to the list. 
If not, `AddToSemCommand#addModule()` will create a new semester list and then add this module to the new list. 
Finally, this new semester list will be added to `semesterList`.

#### Step 4:
`AddToSemCommand#execute()` calls `Ui.showAddedToSemMessage(selectedModule.announceAdded())` to tell the user that the
command has been executed.

The following sequence diagram shows how the `Add to Semester` operation works:
![Sequence Diagram of Add to Semester](https://github.com/RenzoTsai/tp/blob/Update_DG/docs/UML%20img%20folder/Sequence%20Diagram%20of%20Add%20to%20Semester.png)


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
After the CAP is assigned to `Person.totalCap`, `Person.totalCap` is then called and formatted using `DecimalFormat` 
into a `String`
with a pattern of `#.00`. `Ui.showcap(cap)` is called to display the user's cap using the formatted `String`.

The following diagram shows how the Calculate CAP operation works:
![Calculate CAP feature](https://github.com/bennychanya/tp/blob/master/CalculateCap.png?raw=true)


`Delete from Semester` feature

The `Delete from Semester` mechanism is facilitated by `DeleteFromSemCommand`, which extends extends from `AddCommand`.
It allows  `ModuleManager` to delete a module from a `SemModulesList`.
By doing so, the following operations are carried out:

##### Step 1:
When a user enters a delete from semester command, e.g `delete id/IS4241 s/4`, this command is being parsed in `Parser`.
`Parser` then returns a `DeleteFromSemCommand`, which calls 
`Command.execute(SemesterList semesterList, AvailableModulesList availableModulesList)`, in this context,
`DeleteFromSemCommand.execute(SemesterList semesterList, AvailableModulesList availableModulesList)`.

##### Step 2:
`DeleteFromSemCommand.execute` then calls its own method `checkModuleExistInCorrectSem(selectedModulesList)`.
`checkModuleExistInCorrectSem(selectedModulesList)` returns a boolean value regarding whether there is such 
a module in the semester stated by the user.
If the module does not exist in that semester, a `RuntimeException` is thrown.
Otherwise, it will run through the `semModulesList` in the `selectedModulesList` to find the semester indicated.
It then calls a method of `SemModulesList`, `getModule(moduleIdentifier)`, which returns the corresponding module,
based on either the `moduleId` or `moduleName`.
Following, it then calls another method of `SemModulesList`, `remove(module)`, which removes the corresponding module.

##### Step 3:
`DeleteFromSemCommand.execute` finally calls `Ui.showDeleteFromSemMessage(String.format(
"Module %s has been deleted from semester %s", moduleIdentifier, yearSemester));`
This tells the user the module that has been deleted from the corresponding semester.

The sequence diagram below shows the mechanics of `DeleteFromSemCommand`:

![Sequence Diagram](https://github.com/chengTzeNing/tp/blob/DG/docs/images/Sequence%20Diagram.png)


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
|**|User|find a module by name or module code|Locate a module and its module code without having to go through all the 
modules|


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


