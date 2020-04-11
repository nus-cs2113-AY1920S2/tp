

# **Project Portfolio Page -- Clement Cheng**
## **Project Nuke** <small><b>v2.1</b></small>

<hr>

## **Overview**
**Nuke v2.1** is a simple yet powerful task management application target towards **NUS students**.  **Nuke** aims to provide a more efficient way to organise the user's modules and tasks. The user interacts with **Nuke** using a _Command Line Interface_ (CLI). In its latest versions, it also has a complementary _Graphical User Interface_ version created with JavaFX, though the GUI is still in its _Beta_ stage. **Nuke** is written in Java, and has about 10, 000 lines of code.

<br>

<hr>

## **Summary of Contributions**
### **Major Enhancements**

#### **Implemented Directory Structure of Nuke**
##### **What it does**
**Nuke** has a directory tree structure consisting of multiple directories and sub-directories. Users can traverse about various directories _(Root, Module, Category, Task and File)_, much like a Linux Shell.

##### **Justification**
As each item _(module, category, task and file)_ appears to be of different hierarchy, implementing a directory tree, with each of the item as its own directory, was a decision our team has decided. Traversing about the directories not only allow users to see the overall structure of their module and task list, but also helps to shorten the command to enter, such as if adding and deleting an item in the current directory.

##### **Highlights**
Implementation of this directory structure was done about midway in the project. As such, it resulted in various changes to the way we manage the module and task entities previously. Each directory has to be attached to a parent directory, so an additional attribute needs to be considered before adding an item to its list. Considerations also need to be made to be able to obtain the current directory's information to fill up missing attributes in the user's commands, which was rather challenging indeed. A sudden change in the structure of the project may also implicate other features made previously, so checking needs to be done to identify any potential errors and bugs, and thereafter remedy accordingly.

#### **Implemented Prompts on Deletion**
##### **What it does**
Prompts the user to confirm the deletion of item(s), instead of deleting the items straightaway.

##### **Justification**
This implementation will greatly benefit the user, especially if they accidentally executed the delete command on the wrong item. By prompting the user to confirm the deletion, the user will have the opportunity to abort the deletion if the deletion was not intended.

##### **Highlights**
While seemingly simple, the implementation proves to be slightly difficult due to the need to manage input from prompts separately from the regular commands given by the user. Considerations need to be made to switch between parsing of regular commands and parsing of prompts. Moreover, the management of multiple deletion of matching items had been also a challenge to implement.

#### **Supported Parsing of User Input via RegEx**
##### **What it does**
Parses the user's input by matching with Regular Expression (RegEx) patterns.

##### **Justification**
As the formats for most of **Nuke**'s commands contain many keywords, with a preceding prefix for most of them, manually finding each keyword via the standard _String_'s _split_ and _substring_ methods may not be ideal. 
Using RegEx will greatly aid the process of extracting the keywords from the user's input by dividing each keyword into groups. RegEx also makes identifying of invalid command format or duplicated prefixes much easier. 

##### **Highlights**
Using RegEx to support the parsing was challenging initially. Most commands needs to have its own unique RegEx, since they have a different command format than that of other commands. The RegEx needs to be correct, otherwise it does not serve its purpose. As such, multiple testing was done with various possible input to ensure its correctness. This had been done for each command.

<br>

<hr>

### **Minor Enhancements**
Improve the feedback messages shown to the user for invalid commands by making them more informative, so the user knows exactly where in their input was wrong.

### **Other Contributions**
#### Implemented a fully functional [GUI version](https://github.com/AY1920S2-CS2113T-T13-2/tp/releases/tag/v2.1) of **Nuke**. 
Though this was not the main focus of the project, having a GUI definitely improves the aesthetics of the application. The implementation of an auto-complete console will certainly aid the user to be able to enter their commands faster. In addition, the syntax highlighter can help user to identify mistakes in their command format at real time before they execute the command.

<br>

<hr>

#### **Project Management**
- Generally oversee the progress of the entire project, and set targets for the team to complete
- Refactor code regularly

#### **Documentation**
##### **User Guide**
- Created User Guide template -- [#86](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/86/commits/4564354abd1dd6bb2cc5cbee7d33a8494d5dbbb6)
- Documented Add modules, categories and tasks Features -- [#86](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/86)
- Documented Command Summary section -- [#198](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/198)
- Documented FAQ section -- [#202](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/202)

##### **Developer Guide**
- Created Developer Guide template -- [#91](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/91)
- Documented Delete features including class and sequence diagrams -- [#93](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/93)  [#94](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/94)  [#95](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/95)  [#104](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/104)
- Various Commands section -- [#205](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/205),[#206](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/206), [#207](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/207), [#209](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/209), [#210](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/210), [#212](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/212)
- Directory section -- [#219](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/219), [#222](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/222), [#224](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/224)

#### **Community**
##### **Bugs**
- Amended bug issues in **Nuke** raised by other course members -- [#126](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/126) [#129](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/129) [#135](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/135) [147](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/147)
- Reviewed other course member's tP -- [#1](https://github.com/iceclementi/ped/issues/1) [#2](https://github.com/iceclementi/ped/issues/2) [#3](https://github.com/iceclementi/ped/issues/3) [#4](https://github.com/iceclementi/ped/issues/4) [#5](https://github.com/iceclementi/ped/issues/5) [#6](https://github.com/iceclementi/ped/issues/6) [#7](https://github.com/iceclementi/ped/issues/7) [#8](https://github.com/iceclementi/ped/issues/8) [#9](https://github.com/iceclementi/ped/issues/9) [#10](https://github.com/iceclementi/ped/issues/10) [#11](https://github.com/iceclementi/ped/issues/11)

<br>

<hr>

### **Code Contributed**
[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=iceclementi&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos) from _GitHub RepoSense_
