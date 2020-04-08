
# Project Portfolio Page -- Clement Cheng
## Project Nuke <small>v2.1</small>

<hr>

## Overview
**Nuke v2.1** is a desktop task management application target towards **NUS students**.  **Nuke** aims to provide a more efficient way to organise the user's modules and tasks. The user interacts with **Nuke** using a _Command Line Interface_ (CLI). In its latest versions, it also has a complementary _Graphical User Interface_ version created with JavaFX, though the GUI is still in its _Beta_ stage. **Nuke** is written in Java, and has about 10, 000 lines of code.


## Summary of Contributions
### Major Enhancements
#### Implemented File features
##### What it does
Enables users to add, delete, list, edit and open their files.

##### Justification
These features will enable users to be able to access their files through the **Nuke** application, without going through the hassle of finding their files normally. The commands to execute each file feature are short and simple, which further improves the efficiency of file access. This was also one of the team's [user stories](https://ay1920s2-cs2113t-t13-2.github.io/tp/DeveloperGuide.html#user-stories) that we hope to implement.

##### Highlights
The implementation of the file features was initially rather tricky. Research had to be done to learn how to utilise Java's _Files_ and _Paths_ API to copy, delete and open files based on their file name and path. How the application will be able to manage deleted or renamed files are also issues to consider when implementing the file features.

#### Implemented Parsing of User Input via RegEx
##### What it does
Parses the user's input by matching with Regular Expression (RegEx) patterns.

##### Justification
As the formats for most of **Nuke**'s commands contain many keywords, with a preceding prefix for most of them, manually finding each keyword via the standard _String_'s _split_ and _substring_ methods may not be ideal. 
Using RegEx will greatly aid the process of extracting the keywords from the user's input by dividing each keyword into groups. RegEx also makes identifying of invalid command format or duplicated prefixes much easier. 

##### Highlights
Using RegEx to support the parsing was challenging initially. Most commands needs to have its own unique RegEx, since they have a different command format than that of other commands. The RegEx needs to be correct, otherwise it does not serve its purpose. As such, multiple testing was done with various possible input to ensure its correctness. This had been done for each command.

### Minor Enhancements
### Other Contributions
### Code Contributed
[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=iceclementi&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos)
