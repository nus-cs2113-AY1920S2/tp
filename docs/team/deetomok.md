# Mok Kai Sheng Daryl - Project Portfolio Page

## Overview  
Personal Module Manager is a software that tracks the number of modular credits, modules taken and modules required 
for its user to graduate. The software is for NUS Students who want to plan their modules per semester in an easy way 
and it is optimized for those who want to type fast with CLI. It is written in Java.  

### Summary of Contributions  
This section describes a summary of my code and documentation contributions, as well as other contributions to help
other peers.  

#### Code contributed  
For all the code that I have contributed to Module Manager, click here:
[code contribution](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=deetomok&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos).  

#### Enhancements implemented  
In this project, other than helping implement all other classes, I implemented the portion of Controller, Person, 
JUnit test, Logging, some commands (Mark as Done, View, Helping and Clear) and some exception classes. Furthermore,
I adjusted the programme to accept commands from user irrespective of upper or lower case characters.  

##### Mark as Done Command  
* What it does: This command allows users to mark modules that have been added to their module plan as done. In doing so,
it stores the grade of the module. This is essential for the calculation of the user's CAP and amount of completed credits.
* Justification: In a Module Management application, it is important for users to keep track of which modules they have
completed, its respective grades, and which modules in their module plan they have yet to finish. If the user fails
the module, the fail grade is added to cap, but the credit of that module is not added to the completed credit of the 
user. Furthermore, if the user incorrectly enters the grade of the module when using this feature, the user can simply
re-enter this feature with the updated grade to change the grade of the completed module. 
* Highlights: Implementation was challenging because there are multiple different scenarios to consider for when a 
user wants to use this feature, and when implementing this feature, we must be clear of what to change and add into the
user's data. Some scenarios considered include:
    * User fails a mod, we have to allow them to add the same module again, as in reality they must re-take the module. 
    * User marks a module as done, but entered an incorrect grade, and has to update the done module's grade

### Contributions to the User Guide: 
* Added table of contents for the User Guide ([#133](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/133/files))
* Added Command Format, Mark as Done feature, Deleting features, FAQ, Command summary 
([#62](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/62), [#121](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/121))

### Contributions to the Developer Guide: 
* Added table of contents for the Developer Guide ([#133](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/133/files))
* Added Introduction Section and Setting up section. ([#118](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/118))
* Added Architecture, UI component, Logic component and Model component under Design section. ([#118](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/118))
* Wrote implementation document for Delete from Available, Add to Available and Marking as Done
([#121](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/121),)
* Added Architecture Diagram, Sequence Diagram for MarkAsDone feature and Object Diagrams for 
Ui component, Logic component and Model component ([#119](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/119), 
[#118](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/118), 
[#135](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/135))  
* Implemented Product Scope section, User Stories section, Non-Functional Requirements section, 
Glossary section and Instructions for Manual Testing section.([#133](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/133))  

### Contributions to team-based tasks  
- configured Build Gradle windows to make sure runtest.bat works ([#28](https://github.com/AY1920S2-CS2113-T15-3/tp/commit/f2767e597522e0aa84c1a99dcf087354bddfda62))  
- Maintaining the issue tracker.  
- Ensuring group meetings and consolidation of group discussions  

### Review/mentoring contributions:
- Reviewed pull requests in my team: [Review of #8](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/8), 
[Review of #19](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/19), [Review of #35](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/35),
[Review of #51](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/51) and 65 others.  
- Made PRs to teammate's PR to suggest code revision 
([#51](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/51), 
[#4](https://github.com/chengTzeNing/tp/pull/4),
[#5](https://github.com/chengTzeNing/tp/pull/5))  
- Helped team mates using windows who had trouble setting up runtest.bat on their personal computers  

### Contributions beyond the project team
- Contributed to CS2113/CS2113T forums discussions: ([#30](https://github.com/nus-cs2113-AY1920S2/forum/issues/30), [#93](https://github.com/nus-cs2113-AY1920S2/forum/issues/93))   
- bugs reported in other team's products: [Bug reports to CS2113T-T12-3](https://github.com/DeetoMok/ped/issues)  


### Contributions to the User Guide
The user guide is updated with relevant instructions and their correct format. I was responsible for the 
Command Format, Mark as Done feature, Deleting features, FAQ, Command summary, Table of contents.

Below is a small portion from the User Guide, showing my documentation for the Deleting feature.

A grey highlight as `such` indicates a command which can be typed into the command line and executed by Module Manager.
Words in [square brackets] denotes parameters that have to be specified by the user.

## Project Feature
### 3.3  Deleting module: delete
You can delete a module from a semester of your module plan or from the list of available modules.

#### 3.3.1 Delete a specific module from a semester in module plan
You can do so by using the module code or the module name.
The module to be deleted must be in your module plan.\
If you delete a module which is done and not failed, then the total complete credits will be changed.
 
#### 3.3.1.1 Based on module code
Format: `delete id/[module code] s/[semester]`

Example:​ `delete id/IS4241 s/4`

Expected output: 

`Okay, this module has been deleted from the corresponding semester`

`Module IS4241 has been deleted from semester Y2S2`


#### 3.3.1.2 Based on module name
Format: `delete n/[module name] s/[semester]`

Example:​ `delete n/Discrete Structure s/4`

Expected output: 

`Okay, this module has been deleted from the corresponding semester`

`Module Discrete Structure has been deleted from semester Y2S2`

#### 3.3.2 Delete a specific module from available module list
If the module you delete also in module plan, it will also be removed in module plan.

#### 3.3.2.1 Based on module name
Format: `delete id/[module code]`

Example:​ `delete id/IS4241`

Expected output: 

`Okay, this module has been deleted from the list of available modules`

`ID: IS4241 Name: Social Media Network Analysis | Modular Credit: 4`

#### 3.3.2.2 Based on module code
Format: `delete n/[module name]`

Example:​ `delete n/Social Media Network Analysis`

Expected output: 

`Okay, this module has been deleted from the list of available modules`

`ID: IS4241 Name: Social Media Network Analysis | Modular Credit: 4`


### Contributions to the Developer Guide (Which sections did you contribute to the DH? Which UML Diagrams did u add/update)
This section describes my contributions to the Developer Guide of Module Manager. I implemented the Table of Contents, 
Introduction section and Setting up section. In addition, I implemented the Design section excluding Storage. 
I designed the entire Architecture Design for Module Manager and wrote the implementation document for Addition  
(Add to Semester and Add to available) and Marking as Done. Lastly, I implemented Product Scope section, User Stories
section, Non-Functional Requirements section, Glossary section and Instructions for Manual Testing section.

Below contains an excerpt of my documentation regarding the Architecture Design of Module Manager.

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
Given below is the structure of the Ui component:
![Ui Diagram](https://github.com/DeetoMok/tp/raw/master/docs/images/Ui.png)

The `UI` component consists of a `Ui` class that stores all user interaction output data. 
It displays all user interactions to the user based on the command line inputs received.

The `UI` component,

*   Executes user commands using the `Logic` component

#### 3.3 Logic component
Given below is the object diagram of the Logic Component
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
Given below is the class diagram of the Model Component:
![Class Diagram of Model Component](https://github.com/DeetoMok/tp/raw/master/docs/images/Class_Diagram_of_Model_Component(1).png)

The `Model` component is responsible for serving as a boundary between the `Controller` component and `Storage` 
component. 

The responsibilities of the `Model` component includes
* Storing the data in-memory during programme runtime. It stores all `SelectedModule` objects in an 
`ArrayList<SelectedModule>` in a `SemModulesList` class. This represents a semester of the user's module plan.
* All `ArrayList<SelectedModule>` is then stored in a `PriorityQueue<SemModulesList>` which contains `SemModulesList`
in an ordered fashion. This class is called `SemesterList`, which represents the entire module plan of the user.

### 4.3.2 Marking module as done

The Marking as done mechanism is executed by `MarkAsDoneCommand`.
`MarkAsDoneCommand` is extended from the abstract class `Command`, and this implementation marks the module that has
been added to a `SemModuleList` in the `SemesterList` as done, and updates the respective grade to the `Module` object.  
 
The sequence diagram below shows the mechanics of `MarkAsDoneCommand`:
![Mark As Done Sequence Diagram](https://github.com/DeetoMok/tp/raw/master/docs/images/Mark_As_Done_Sequence_Diagram.png)