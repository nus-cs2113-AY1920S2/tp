# Lai Wen Xuan Jeremy - Project Portfolio Page

## PROJECT: Amazing Task and Assignment System (ATAS)

### Overview
ATAS is a cross platform Command Line Interface (CLI) program that allows you to track your assignments and events. 
ATAS is catered for students who want to maximise their productivity and cultivate effective time management habits through a desktop application.

### Summary of Contributions
**Major Enhancement:** Implemented the user command parser using regex
* What it does:
    * Parses commands input by the user, and creates the appropriate `Command` object to execute the user's desired task
    * Utilises regex and error handling methods to catch incorrect commands and formats
* Justification:
    * The regex strictly enforces the format of some parameters such as dates to facilitate easy parsing and reduced chances of bugs.
      However, it still allows for some freedom in input formats, such as optional whitespaces, to make typing commands easier for users.
* Highlights: 
    * This enhancement forms the foundational structure for the logic of **ATAS**, dealing with all user inputs.
    * Comprehensive error handling of the parser makes the implementation of the various `Commands` much easier, 
      as the `Command` classes can expect to receive only valid inputs
    * This enhancement was challenging to implement as it was my first time properly dealing with regex.
      I had to figure out how to use named-capturing groups, how to allow for optional and variable-length expressions, 
      how to explicitly disallow the use of certain special characters, and how to integrate all these things into a regex 
      that represents the user's input command.
* Credits:
    * Credits to [addressbook-level2](https://github.com/se-edu/addressbook-level2/tree/master/src/seedu/addressbook) for the idea to use regex for user commands

**Minor Enhancement:** Implemented the storage functionality to save user's data across sessions in a local file

**Code Contributed:** [View on RepoSense](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lwxymere&tabRepo=AY1920S2-CS2113T-M16-1%2Ftp%5Bmaster%5D)

**Contributions to the User Guide:**
* Updated the *Command Format* section under *Section 3 - Features*: [#105](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/105)
* Added instructions for using the `assignment`, `event`, `list`, and `list today` commands

**Contributions to the Developer Guide:**
* Added the *Setting Up* section
* Wrote the *Design* section
* Wrote *Section 3.7 - Storage*
* Wrote *Section 5 - DevOps*
* Normalize the writing style and format of the various sections: [#142](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/142), [#220](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/220)

**Contributions to Team-based Tasks:**
* Managed the issue tracker
* Managed links in the project GitHub page
* Compiled all comments from the developer guide review
* Compiled all relevant bugs reported in the practical exam dry run

**Community:**
* Fixed minor bugs overlooked by group members: [#46](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/46)
* Fixed careless mistakes of group members in the Developer Guide before final submission: [#220](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/220) 
