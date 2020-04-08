# Project Portfolio Page -- Clement Cheng
## Project Nuke <small>v2.1</small>

<hr>

## Overview
**Nuke v2.1** is a simple yet powerful task management application target towards **NUS students**.  **Nuke** aims to provide a more efficient way to organise the user's modules and tasks. The user interacts with **Nuke** using a _Command Line Interface_ (CLI). In its latest versions, it also has a complementary _Graphical User Interface_ version created with JavaFX, though the GUI is still in its _Beta_ stage. **Nuke** is written in Java, and has about 10, 000 lines of code.


## Summary of Contributions
### Major Enhancements
#### Implemented Category Features
##### What it does
Enables users to add, delete, list and edit their categories.

##### Justification
Categories were not implemented initially. However, on further deliberation, a Category directory was inserted between the Module's and the Task's directory. A category serves to further subdivide the user's tasks into various categories depending on the type of tasks. For instance, Tutorial types of tasks can be put into the Tutorial Category. This addition enhances the organisation of the user's tasks, which ultimately makes finding of specific tasks easier. Furthermore, this also allows users to have the same _task description_ for tasks in separate categories.

##### Highlights
As the Category directory had not been implemented initially, there was a change the code significantly to redirect the parent directory to the correct directory. This may also have further implications to other command features such as _list_, which now has an additional layer to filter from.


#### Implemented File Features
##### What it does
Enables users to add, delete, list, edit and open their files.

##### Justification
These features will enable users to be able to access their files through the **Nuke** application, without going through the hassle of finding their files normally. The commands to execute each file feature are short and simple, which further improves the efficiency of file access. This was also one of the team's [user stories](https://ay1920s2-cs2113t-t13-2.github.io/tp/DeveloperGuide.html#user-stories) that we hope to implement.

##### Highlights
The implementation of the file features was initially rather tricky. Research had to be done to learn how to utilise Java's _Files_ and _Paths_ API to copy, delete and open files based on their file name and path. How the application will be able to manage deleted or renamed files are also issues to consider when implementing the file features.

#### Supported Parsing of User Input via RegEx
##### What it does
Parses the user's input by matching with Regular Expression (RegEx) patterns.

##### Justification
As the formats for most of **Nuke**'s commands contain many keywords, with a preceding prefix for most of them, manually finding each keyword via the standard _String_'s _split_ and _substring_ methods may not be ideal. 
Using RegEx will greatly aid the process of extracting the keywords from the user's input by dividing each keyword into groups. RegEx also makes identifying of invalid command format or duplicated prefixes much easier. 

##### Highlights
Using RegEx to support the parsing was challenging initially. Most commands needs to have its own unique RegEx, since they have a different command format than that of other commands. The RegEx needs to be correct, otherwise it does not serve its purpose. As such, multiple testing was done with various possible input to ensure its correctness. This had been done for each command.


### Minor Enhancements
Improve the feedback messages shown to the user for invalid commands by making them more informative, so the user knows exactly where in their input was wrong.

### Other Contributions
#### Implemented a fully functional [GUI version]() of **Nuke**. 
Though this was not the main focus of the project, having a GUI definitely improves the aesthetics of the application. The implementation of an auto-complete console will certainly aid the user to be able to enter their commands faster. In addition, the syntax highlighter can help user to identify mistakes in their command format at real time before they execute the command.

#### Project Management
Generally oversee the progress of the entire project, and set targets for the team to complete.

#### Documentation
##### User Guide
- Created User Guide template -- [#86](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/86/commits/4564354abd1dd6bb2cc5cbee7d33a8494d5dbbb6)
- Documented Add modules, categories and tasks Features -- [#86](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/86)

##### Developer Guide
- Created Developer Guide template -- [#91](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/91)
- Documented Delete features including class and sequence diagrams -- [#93](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/93)  [#94](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/94)  [#95](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/95)  [#104](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/104)

#### Community
##### Bugs
Amended bug issues in **Nuke** raised by other course members -- [#126](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/126) [#129](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/129) [#135](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/135) [147](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/147)
Reviewed other course member's tP -- [#1](https://github.com/iceclementi/ped/issues/1) [#2](https://github.com/iceclementi/ped/issues/2) [#3](https://github.com/iceclementi/ped/issues/3) [#4](https://github.com/iceclementi/ped/issues/4) [#5](https://github.com/iceclementi/ped/issues/5) [#6](https://github.com/iceclementi/ped/issues/6) [#7](https://github.com/iceclementi/ped/issues/7) [#8](https://github.com/iceclementi/ped/issues/8) [#9](https://github.com/iceclementi/ped/issues/9) [#10](https://github.com/iceclementi/ped/issues/10) [#11](https://github.com/iceclementi/ped/issues/11)

### Code Contributed
[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=iceclementi&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos) from _GitHub RepoSense_
