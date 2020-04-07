# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that  aims to help manage tasks and projects.  
Users can set tags and goals for their entries,  
ultimately being able to keep track of what's left to do and maintain an overview of how time was spent.  

## Summary of contributions  
### Code contributed  
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=siuhian)

### Enhancements implemented
* Start activities
    * Added new feature on top of the existing `start` command.
    * `start` command now allows users to allocate a certain amount of time for that activity using the /a flag.
    * Fix bugs in the `start` command with inputs from the peer testing results.
    * Added more flexibility to the `start` command, `start cs2113 /a 10:30:00 /t tP` now works the same way as 
    `start cs2113 /t tP /a 10:30:00` meaning the positions of /a and /t flags can now be used interchangeably.

* User interface
    * Sourced and implemented the existing Jikan logo that greets the user upon execution
    * Created a standard template to print lines to stdout. This feature is used to print error, acknowledgement and 
    reply messages from Jikan to the user.
    * Implemented the `bye` command which makes use of Ui to exit from the application.

* Automated cleaning
    * Built a automated cleaner that performs a batch delete on data and log files upon application execution.
    * This batch delete on data works by deleting a number of completed activities (specified by user) starting from the oldest
    completed activity (e.g completed activities are activities that have met its allocated time).
    * Implemented `clean` command which allows the user to switch on/off the automated cleaner.
    * Allows the user to specify how much completed activities/logs to delete for each round of automated cleaning. This
    is done through the /n flag in the clean command.

* Graph allocations
    * Provides the user with a graphical representation on the progress of all the activities. 
    * The function is provided by the `graph allocations` command.

### Contributions to documentation
* Provided syntax and usage examples for these commands. (`clean`,`graph allocations` and `start`).
* Edited the command summary and usage section to ensure consistency with the features under my implementation.

### Contributions to the DG
* Explained the implementation of the automated cleaning feature (Under Section 3.1) using a mixture of sequence diagram
with flowcharts.

### Contributions to team-based tasks
* Generated ideas with the team on the set of features for the Jikan application.
* Made use of the issue tracker extensively to track enhancement and bugs found.
* Enabled assertions in the build.gradle file.
* Created the project repository and set up the team organization.
* Helped create the Parser and Ui class to make the code more OOP.
* Documenting the target user profile.
* Helped out in resolving the text-ui-issues (date sensitive need to update everyday) that caused many commits to fail the checks.

### Review/mentoring contributions
* Provided feedback to peers on how certain features can be improved (e.g progress message for activities,
 store tag goals in separate data file so as to not overload the main data file for activities)

### Contributions beyond the project team
* Provided feedback to the developer guide of another team.
    * [Reviewing of DG on Week 11](https://github.com/nus-cs2113-AY1920S2/tp/pull/7)
* Reported bugs in other team's product in PE dry run.
    * [PED](https://github.com/siuhian/ped/issues)

### Contributions to the User Guide (Extracts)

#### Usage of Automated Cleaning

As Jikan is a time tracker application which works with various data files (data file for activities, log files for execution history
etc.), over time it can be a mess to deal with these data files especially when they get too big. Thus, Jikan provides automated cleaning
services for such situations.

#### Activating the automated cleaning: `clean on | clean log on`
At runtime, users can switch on the automated cleaning services. Once the automated cleaning is activated, the application will
do an auto cleanup of files at the start of every execution until this services is switch off. Do note that the cleaning will only start from the next execution.
(i.e no cleaning will be done for the current execution which activated auto cleaning).

Note: \
`clean on` activates the cleaning of data files where activities are stored. \
`clean log on` activates the cleaning of log files where application execution history is stored.

#### Deactivating the automated cleaning: `clean off | clean log off`
At runtime, users can switch off the automated cleaning services. Once deactivated, the application will
stop doing an auto cleanup of files at the start of every execution. Similarly, the changes only applies to the next execution.

Note:\
When the application is executed for the first time, the automated cleaning is deactivated by default and will remain so until it is activated by the user.

#### Specifying how much data to clean: `clean /n NUMBER | clean log /n NUMBER`
At runtime, the user can manually set the amount of data to clean using these commands where `NUMBER` is an integer based on user input. 
Thus, `clean /n 5` will automatically clean the top 5 oldest activities from the activity list upon every startup (assuming automated cleaning
is activated).

Note:\
Default `NUMBER` value for data files : 3\
Default `NUMBER` value for log files : 10

### Contributions to the Developer Guide (Extracts)

#### 3.1 Automated Storage Cleanup feature

##### 3.1.1 Current Implementation

The storage cleanup mechanism is stored internally as a StorageCleaner class. The StorageCleaner class has an association with the Storage class and thus it is able to access and edit the datafile which contains the list of activities. 

Additionally, when the StorageCleaner class is first initialised, it will create two files, namely a status file and a data file in the “recycled” folder under the “data” folder. The status file keeps track of the activation status of the storage cleaner while the data file serves as a recycle bin for deleted data. 

Moreover, the class also implements the following operations:

`StorageCleaner#initialiseCleaner - Loads up the status file to decide whether to activate/deactivate the automated cleaner.`

`StorageCleaner#setStatus - Gives the user the freedom to activate/de-activate the cleaner using commands during application runtime.`

`StorageCleaner#autoClean - This operation is called whenever the application is executed. Storage cleaning will only be done if the cleaner is activated.`

Given below is the example scenario of how the operations work.

###### initialiseCleaner

Step 1. Loads up the status file for reading. If the status file is not found, create a new status file and write the character ‘0’ to the first line of the status file.

Step 2. Read the first line of the status file, if a character ‘0’ is found, deactivate the automated cleanup. Else if a character ‘1’ is found, activate the automated cleanup.

###### setStatus

Step 1. Read the boolean parameter `status`.

Step 2a. If `status` is equal to true, activate the automated cleanup and write the character ‘1’ to the first line of the status file. (overwrite any existing value at the first line).

Step 2b. If `status` is equal to false, deactivate the automated cleanup and write the character ‘0’ to the first line of the status file.

###### autoClean

Step 1. Check activation status of StorageCleaner through the class level attribute boolean `toClean`.

Step 2a. If the attribute `toClean` is equal to `false`, return the operation and give control back to the caller.

Step 2b. If the attribute `toClean` is equal to `true`, access the storage data file and remove some of the activities starting from the oldest. Put these deleted activities into the data file under the ‘recycled’ folder.

##### 3.1.2 Additional Implementation (proposed)

`StorageCleaner#setDeleteQuota - Allows the user to manipulate how much activity is deleted when auto cleanup is activated. Currently only delete the oldest 3 activities.`

`StorageCleaner#setTimeFrame - Set a particular time frame for auto cleanup to activate. (i.e auto cleanup after every 2 weeks etc).`

`StorageCleaner#initialiseLogCleaner - Gives the storage cleaner an added functionality of being able to clear log files too.`
