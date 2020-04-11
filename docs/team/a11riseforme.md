# Project Portfolio Page -- Yang Chenglong

## Project Nuke <small>v2.1</small>

<hr>

## Overview

**Nuke v2.1** is a simple yet powerful task management application target towards **NUS students**.  **Nuke** aims to provide a more efficient way to organize the user's modules and tasks. The user interacts with **Nuke** using a _Command Line Interface_ (CLI). In its latest versions, it also has a complementary _Graphical User Interface_ version created with JavaFX, though the GUI is still in its _Beta_ stage. **Nuke** is written in Java, and has about 10, 000 lines of code.

## Summary of Contributions

### Code contributed: \[[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=a11riseforme)\]

### Major enhancement:

#### 1. Added the ability to undo/redo previous commands

- What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
- Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
- Highlights: This enhancement does not affects existing commands, but it required an in-depth analysis of the data formation of the current project. The implementation too was challenging as it required to add in a middle step in the main function of the program. I have to make sure that it won't go wrong and adversely affects the main program.

#### 2. Added the ability to preload the information of all modules provided by NUS

- What it does: Preloads the information of all modules provided by NUS, including module codes and module titles.
- Justification: This feature makes our product more targeted at our intended user(NUS students) more, it also enables the auto-complete features when users are adding their modules by entering module codes, which enhances usability.
- Highlights: This enhancement is based on the json file which is retrieved from the [NUSMods](https://api.nusmods.com/v2/) API, and it has to deal with the case when there is no relevant json file on the disk, then the program needs to fetch the content from [NUSMods](https://api.nusmods.com/v2/) API, and if the user has no network connection(which is a very rare case), the program also needs to be compatible with such extreme case and compromises to allow the user to add any modules, even not provided by NUS
- Credits: 
  - [NUSMods](https://api.nusmods.com/v2/) API: For providing the API to retrieve the information of all the modules provided by NUS.
  - [FastJson](https://github.com/alibaba/fastjson) library: For parsing the json file.
  - [Commons IO](http://commons.apache.org/proper/commons-io/) from Apache: For downloading the json file from [NUSMods](https://api.nusmods.com/v2/) API.

#### 3. Implement the generic Linux filesystem associated command `ls`, `mkdir` and `rm`

- What it does: Allow the user to execute the Linux filesystem associated command `ls` to list out directories, `mkdir` to create directories, and `rm` to remove directories.
- Justification: This feature improves the user experience for the user with prior experience on Linux significantly because it reduces a lot of work for the users to remember other commands. 
- Highlights: This enhancement does not affects existing commands, and it is actually based on the implementation of existing commands. The program will retrieve the current directory where the user is, and depending on the directory type(Root, Module, Category, Task), it will distribute to the actual command to handle.

### **Minor enhancement**: 

Improve the feedback messages shown to the users when listing out all the tasks, add one more column showing if the tasks are done, so that the users would know if they need to work on the task as soon as possible.

### **Other contributions**:

#### Project management:

Managed releases `v1.0` ,  `v2.0` (2 releases) on GitHub

#### Documentation:

##### User Guide:

-   Documented List modules/categories/tasks and Delete modules/categories/tasks Features. -- [#122](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/122/commits/cececb821a8e6b54c2abc5da7ed13acf79262c08) 
-   Elaborated Add modules/categories/tasks Features. -- [#117](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/117/commits/f518160a8b8df0a2e4e7b6d481011b083cbeef40)

##### Developer Guide:

-   Documented Add modules/categories/tasks Implementation. -- [#96](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/96/commits/6fe3ee74bec8fdb7c8eccca5871e974220200d9c)
-   Documented List Command Implementation. -- [#208](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/208/commits/796f5c9327141c8aedf2001aa92c3546ce2a47a8)
-   Documented Setting Up section. -- [#221](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/211/commits/71bc5c7c0ee3eaf6e6f8538ebda0635a7267fdfc)
-   Documented Appendix Section. -- [#211](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/211/commits/71bc5c7c0ee3eaf6e6f8538ebda0635a7267fdfc) [#214](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/214/commits/652a3e4b1c05dd7f5f5820a7da0451fdccc0d02f) [#215](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/215/commits) [#220](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/220/commits) [#221](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/221/commits) [#223](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/223/commits) [#226](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/226/commits) 

#### Contributions to team-based tasks :

- Fix the code  to make sure it passes CI on GitHub regularly.
- Handled part of the issues/bugs raised during the PE-Dry run.

#### Community:

-   Reviewed other course member's tP and reported 6 bugs during the PE-Dry run. -- [#1](https://github.com/A11riseforme/ped/issues/1) [#2](https://github.com/A11riseforme/ped/issues/2) [#3](https://github.com/A11riseforme/ped/issues/3) [#4](https://github.com/A11riseforme/ped/issues/4) [#5](https://github.com/A11riseforme/ped/issues/5) [#6](https://github.com/A11riseforme/ped/issues/6) 
-   Reported 8 bugs on the course website. -- [#3](https://github.com/nus-cs2113-AY1920S2/forum/issues/3)
-   Helped other course member on the forum. -- [#10](https://github.com/nus-cs2113-AY1920S2/forum/issues/10#issuecomment-576370349) [#16](https://github.com/nus-cs2113-AY1920S2/forum/issues/16#issuecomment-578772535) [#17](https://github.com/nus-cs2113-AY1920S2/forum/issues/17#issuecomment-579072279) [#24](https://github.com/nus-cs2113-AY1920S2/forum/issues/24#issuecomment-581422244) [#41](https://github.com/nus-cs2113-AY1920S2/forum/issues/41#issuecomment-591348850) [#51](https://github.com/nus-cs2113-AY1920S2/forum/issues/51#issuecomment-592957231) [#106](https://github.com/nus-cs2113-AY1920S2/forum/issues/106#issuecomment-612346406) [#110](https://github.com/nus-cs2113-AY1920S2/forum/issues/110#issuecomment-612439623)
