# Joel Chang Zhi Kai - Project Portfolio Page

## PROJECT: Amazing Task and Assignment System (ATAS)

### Overview
ATAS is a cross platform Command Line Interface (CLI) program that allows you to track your assignments and events. 
ATAS is catered for students who want to maximise their productivity and cultivate effective time management habits through a desktop application.

### Summary of Contributions
**Major enhancements:** Implemented *SEARCHING WITH DATE*, *SEARCHING WITHOUT DATE*, *CLEAR COMPLETED TASKS* and *CLEAR ALL TASKS*  functionality
* What it does:
    * *SEARCH* : Allows users to have the option to search for a query, based on a task type with/without date
    * *CLEAR* :  Allows user to have the option of clearing all the tasks on the list or to clear completed tasks(With the exception of repeated events).
* Justification : 
    * *SEARCH* : Users may just want to search for a specific query(with/without a given date), however the current command 
    under `list` only shows all the tasks but does not offer the functionality of searching by keywords which may make the CLI less 
    tedious for users who are finding for tasks based on a specified keyword.
    * *CLEAR* : Users may want to clear all task/completed tasks in their task list. However, the existing implementation(Without 
    `CLEAR` command) only allows users to delete tasks based on their index, which may not be optimized for their use.
* Highlights :
    * The `SEARCHING WITH DATE` enhancement was more difficult and tedious to implement as there is a need to track the original index of 
    the task in the task list, and at the same time, maintaining their original order. Initially, this feature was implemented in a HashMap,
    but it does not support the maintenance of original order. So, I had to figure out how to use a LinkedHashMap to support the maintenance of order of indexes.
    * Another difficulty faced with implementing `SEARCHING WITH DATE` enhancement was the use of `LocalDateTime` api. As this was my first time 
    handling Date and Time with the `LocalDateTime` api, there were many corner cases and exceptions that I had to consider during the implementation. Thus, proper
    planning had to be done before implementing this feature. 
    * The `CLEARING COMPLETED TASKS` enhancement was tedious to implement as there is a need to obtain the original task list and the list of completed tasks.
    Thus, a lot of function calls were required and so, there is a need to consistently keep track of the function calls used during implementation so that the
    implementation does not introduce any potential incorrect behaviour.
    
**Minor Enhancement** : Implemented the *DELETE* command, wrote the code skeleton for `Assignment`,`Event` and `Task`, 
wrote the code skeleton for various JUnit Tests (e.g. `AssignmentTest`,`EventTest`,`MarkAsDoneTest`)

**Code Contributed** : [View on RepoSense](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=joelczk&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos)

**Contributions to User Guide** : 
* Updated numberings of sections for User Guide in md format: [#149](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/149/files)
* Added instructions for using the `list week`, `list upcoming tasks`, `list incomplete assignments` commands
* Updated instructions for using `search`, `searchd`, `clear done` commands, based on changes to their respective functionality.

**Contributions to Developer Guide** :
* Updated numberings of sections for Developer Guide in md format: [#149](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/149/files)
* Wrote *Section 3.1 - Delete Task feature*
* Wrote *Section 3.2 - Search Task Feature*
* Wrote *Section 3.3 - Clear Task Feature*
* Wrote *Appendix E - Documentation*
* Added the *User Stories* Section

**Contributions to team-based tasks** :
* Managed Issue Tracker
* Enabled the use of Jansi in gradle
* Converted UG from ADOC format to MD format: [#147](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/147)
* Converted DG from ADOC format to DG format: [#146](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/147)
* Released Jar file for V1.0 and V2.0

**Community** :
* Fixed overlooked wrong index in UG: [#191](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/191)
* Reviewed and improved other team member's code: [#93](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/93)
* Fixed bug for invalid escape sequence in windows command prompt, overlooked by other team members: [#139](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/139)

