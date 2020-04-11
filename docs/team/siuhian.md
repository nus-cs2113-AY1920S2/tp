# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that aims to help manage tasks and projects.  
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
    * Added some more test cases to the Junit for StartCommand.

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
    * Did a Junit for CleanCommand to further test and improve the reliability of `clean` command. 

* Graph allocations
    * Provides the user with a graphical representation on the progress of all the activities. 
    * The function is provided by the `graph allocations` command.

### Contributions to documentation
* Provided syntax and usage examples for these commands. (`clean`,`graph allocations` and `start`).
* Edited the command summary and usage section to ensure consistency with the features under my implementation.

### Contributions to the DG
* Explained the implementation of the start feature (Under Section 3.1) using a mixture of sequence diagrams with class diagrams
* Explained the implementation of the clean feature (Under Section 3.2) using a mixture of sequence diagram with class diagrams.

### Contributions to team-based tasks
* Generated ideas with the team on the set of features for the Jikan application.
* Made use of the issue tracker extensively to track enhancement and bugs found.
* Enabled assertions in the build.gradle file.
* Created the project repository and set up the team organization.
* Helped create the Parser and Ui class to make the code more OOP.
* Documented the target user profile for UG and DG.
* Helped out in resolving the text-ui-issues (date sensitive need to update everyday) that caused many commits to fail the checks.
* Fixed the bug that made our PPP links broken.

### Review/mentoring contributions
* Provided feedback to peers on how certain features can be improved (e.g progress message for activities,
 store tag goals in separate data file so as to not overload the main data file for activities)

### Contributions beyond the project team
* Provided feedback to the developer guide of another team.
    * [Reviewing of DG on Week 11](https://github.com/nus-cs2113-AY1920S2/tp/pull/7)
* Reported bugs in other team's product in PE dry run.
    * [PED](https://github.com/siuhian/ped/issues)

### Contributions to the User Guide (Extracts)

### Activity allocation graph: `graph allocations`
**Usage:** View the progress of activities to see how much time was spent on the activity relative to the allocated time.

Note: Only activities with an `ALLOCATED_TIME` will be shown.

(Diagram omitted)

For example, if we `graph allocations` for the activity list above, we will get the following graph:

(Diagram omitted)

`activity 3` and `activity 5` does not have an allocated time, thus they do not appear in the graph. 
The percentage shown in the graph represents the activity's progress relative to their allocated time. (`activity 4` have a duration of 2 seconds while its allocated time was 5 seconds, 2/5 * 100% = 40%. Thus the progress of `activity 4` is 40%
as shown in the graph)

**Format:**   
`graph allocations`

## Automated Cleaning

Jikan provides a `clean` command where users can automate the cleaning of activities from the activity list at application startup.

### Activate cleaning: `clean on`
**Usage:** Switch on automated cleaning.

**Format:** `clean on`

### Deactivate cleaning: `clean off`
**Usage:** Switch off automated cleaning. 

**Format:** `clean off`

### Set the number of activities to clean: `clean /n`
**Usage:** Set a number of activities to clean.

**Format:** `clean /n NUMBER`

Note: Once cleaning is switched on, the automated cleaning persists (i.e cleaning will be done at every application startup) until it is switched off.

**Example:**

(Diagram omitted)

Taking a look at this cluttered activity list, we can see that there are some activities which are done (i.e duration > allocation).
Thus, to reduce clutter, we would like to get rid of these done activities. 

However, since the list is so huge, it would be troublesome to use the delete function as users will have to manually navigate through
the list to identify the done activities and delete them.

This is where the `clean` command would be useful. See that activity 6, 7 and 10 are done.

(Diagram omitted)

By using the `clean` command. Users can choose how much of these done activities to clean, for the example here, the number is set to 2.

(Diagram omitted)

Upon the next startup, the automated cleaning will do its work and clean the 2 oldest done activities (i.e oldest here is based on date).

Note that since the user specified to clean only 2 activities, only activity 6 and 7 are cleaned and activity 10 remains in the activity list.

### Automated Cleaning for Logs:

Jikan also provides cleaning for log file which are used to record important information during program execution. This feature will be useful
to users who are running this application on systems with limited hardware (small storage space).

### Activate log cleaning: `clean log on`
**Usage:** Switch on automated cleaning.

**Format:** `clean log on`

### Deactivate log cleaning: `clean log off`
**Usage:** Switch off automated cleaning. 

**Format:** `clean log off`

### Set the number of logs to clean: `clean log /n`
**Usage:** Set number of lines of logs to clean.

**Format:** `clean log /n NUMBER`

### Contributions to the Developer Guide (Extracts)

### 3.1 Start Feature

#### 3.1.1 Current Implementation

(Diagram omitted)

With Jikan as the main entry point for our application, 

1. Jikan will receive user input and pass it to the Parser class to get the corresponding command.
2. The Parser class will initialise and return a Command class object based on the command in user input.
3. In this case, Parser will return a StartCommand class object to Jikan.
4. Then, Jikan will call the StartCommand#executeCommand method to start an activity.

Additionally, StartCommand also implements the following operations:

* **StartCommand#checkActivity** Checks if the activity already exists in the activity list.
* **StartCommand#checkTime** Checks if the allocated time provided is valid.
* **StartCommand#continueActivity** Continue on an existing activity.

**checkActivity**

(Diagram omitted)

The diagram above shows how the StartCommand#checkActivity function works. This function is used to check 
if the activity to be started exists in the activity list. If the activity exists in the list, that activity will be 
continued and this way the user cannot start duplicate activities.

1. When checkActivity() is called, it will make a call to the ActivityList#findActivity method.
2. Once the findActivity() method finishes execution, it will return an integer index back to checkActivity().
3. If the index is not equals to -1, the activity to be started exists in the activity list and continueActivity() will be called.
4. Else, the activity to be started is a brand new activity and addActivityToList() will be called.

**checkTime**

(Diagram omitted)

The diagram above shows how the StartCommand#checkTime function works. This function is used to check the validity of 
the allocated time provided by the user input. If the allocated time is valid, the activity will be added to activity
list.

1. When checkTime() is called, it will initialise two LocalTime objects called endTime and startTime respectively.
2. startTime will be initialised to time 00:00:00 while endTime will be calculated based on the user input to the start
command (i.e `start activity name /a HH:MM:SS /t tags`)
3. Then, the method Duration.between() will be used to get a Duration object that holds the time difference between startTime
and endTime.
4. If this Duration object is non zero (i.e user gave a valid non zero allocated time), then the activity will be added to the activity list
using the addActivity() method.

**continueActivity**

(Diagram omitted)

The diagram above shows how the StartCommand#continueActivity function works. This function is used when the current activity
to be started already exists in the activity list. Thus, this function will check with the user whether to continue on that activity 
and prevent duplicate activities from being started.

1. When continueActivity() is called, it will make a call to the Scanner object to read in the next line of user input.
2. If the user input is "yes", information about the activity (activity name, tags etc.) will be forwarded to parser and the parser
will update the activity list (i.e when continue is used, activity duration is added on and needs to be updated).
3. Else, if the user input is "no", continueActivity() will notify the parser to read in the next line of user input.

#### 3.1.2 Additional Implementation

1. `start` command have the ability to continue an activity if the activity to be started exists in activity list as discussed above. However, the second 
start command's tags and allocated time parameters will not be captured if the activity originally did have tags or allocated time.
    * `start activity 1`
    * `start activity 1 /a HH:MM:SS /t tags` (this command will continue activity 1 but won't add the tags and allocated time to it)

    Thus, it would be best for `start` command to address this issue and allow the second `start` command to not only continue the
activity but also edit the fields of the activity.

2. Allows two activities to start at the same time. As a user, sometimes the activity we are doing may be linked to another activity (i.e activities like 
revising CS2106 and doing CS2106 Labs are similar as doing the labs can serve like a revision too).

    Thus, it would be good if more than one activity can be started at a particular time.

#### 3.1.3 Design Considerations

The current design is centred around the Parser Class as all the relevant activity information (activity startTime, endTime, name, tags,
allocated time) are stored inside Parser.

Since Parser is a public class. There are some benefits to this design.
* All the command classes have access to activity information.
* Makes the classes more lightweight as there is no need for local variables to store activity informations.
* Reduces coupling between the commands as they interact through Parser.

However, there are some drawbacks to this design too.
* Since all the activity information are public, every class in Jikan can access/modify activity information which is
undesirable.
* This creates a lot of dependencies between Commands and Parser which makes unit testing harder to implement.
* As more commands is created to accommodate new features , Parser will be overloaded with new variables and classes.

### 3.2 Clean Feature

#### 3.2.1 Current Implementation

Jikan provides a `clean` command where users can automate the cleaning of done activities (i.e activities with duration > allocation) and logging data
at application startup.

(Diagram omitted)

With Jikan as the main entry for our application,

1. Upon startup, Jikan will initialise a LogCleaner and StorageCleaner object.
2. Jikan will call upon LogCleaner#autoClean() and StorageCleaner#autoClean() functions.
3. These two functions will check if the Storage and Log Cleaner are enabled respectively before cleaning.
4. Thus, by the time the user can interact with Jikan (i.e send commands to Jikan), the activity list and log files would already be cleaned.
5. Using the `clean` command, users would be able to manage the cleaner's behaviour (switching it on/off, set number of done activities/logging data to clean).

The cleanup mechanism is stored internally as a StorageCleaner and LogCleaner class. 

These two classes have access to the data files of activity list and logs respectively and thus they are able to 
directly manipulate the activity list and logging data.

A status.txt file is initialised to keep track of the status (on/off) of the two cleaners and contains information on 
the number of done activities/logging data for cleaning.

Moreover, the CleanCommand also implements the following operation:

* **CleanCommand#setStatus** Switch on/off the two cleaners respectively.
* **CleanCommand#setValue** Set a value for the number of done activities/logging data to be cleaned.
* Note: The two cleaners are independent, setting a value/status for one of the cleaner will not affect the other cleaner.

**setStatus**

(Diagram omitted)

The diagram above shows how CleanCommand#setStatus function works. This function is a generalized function that is used to
switch on or off the cleaners by checking the parameters to the `clean` command. Thus, based on the return value of getStatus() and
getCleaner(), there are four possible scenarios.

1. When setStatus() is called, the method will call its own class method getStatus() to check what is the status to set to.
2. There are two valid return values for getStatus() method which is "on" and "off". The diagram shows the former.
3. Upon receiving a valid return value from getStatus() which is "on" in the diagram, the setStatus() method will self invoke another
of its own class method getCleaner().
4. The return result of the getCleaner() together with getStatus() will then be used to determine which cleaner are we setting and what is
the status to set to. 
5. In other words, result of getCleaner() is used to determine whether are we calling StorageCleaner#setStatus or LogCleaner#setStatus while
the result of getStatus() determines the parameter to setStatus(). (e.g "on" will call setStatus("true") while "off" will call setStatus("false")).

**setValue**

The diagram of setValue is omitted as it is similar to setStatus diagram. This function is a generalized function that is used to 
set a value for the number of done activities or the number of lines of logging data to be cleaned for the two cleaners respectively.

1. When setValue() is called, the method will call its own class method getNumber() that will return an integer value corresponding to the number 
to set to.
2. Upon receiving a valid return value (non negative), the setValue() method will self invoke another of its own class method getCleaner().
3. The return result of the getCleaner() together with getNumber() will then be used to determine which cleaner are we setting and what is 
the value to set to.
4. In other words, result of getCleaner() is used to determine whether are we calling StorageCleaner#setNumberOfActivitiesToClean or LogCleaner#setNumberOfLogsToClean
while the result of getNumber determines the parameter to these two functions.

Note that steps 2-4 of setValue() are similar to steps 3-5 of setStatus().

On the other hand, the Storage/Log Cleaner class implements the following core operation of `clean` command.

* **Cleaner#autoClean** This operation is called whenever Jikan is executed. Cleaning will only be done to the activity list/logging data if
the two cleaners are enabled respectively.

**autoClean**

(Diagram omitted)

The diagram above shows how Cleaner#autoClean function works. This function is called whenever Jikan executes Jikan#main and is used to
perform cleaning of the activity list and logging data if Storage Cleaner and Log Cleaner are enabled respectively. The number of done activities and
lines of logging data to clean is set to 5 at default if user did not specify a value for both cleaners.

1. When main() is called, Jikan will first initialise both the StorageCleaner and LogCleaner object using StorageCleaner() and 
LogCleaner().
2. Once both objects are initialised, Jikan will first call storageAutoClean() method of the StorageCleaner class.
3. This method will invoke another method under the StorageCleaner class called checkStatus() which will return a boolean toClean variable.
4. If toClean == true, the storageAutoClean() method will proceed and clean up the activity list before returning control back to main().
5. Else, the storageAutoClean() will not do any clean up and will immediately return control back to main().
6. Steps 2 to 5 will then be repeated when Jikan call logAutoClean() method of the LogCleaner class. 

#### 3.2.2 Additional Implementation

1. Currently, the data that is cleaned up by this command is sent to a recycled folder similar to how Windows recycle bin works. 

    Thus, it would be good to have a feature to restore the data deleted in the event the user wishes to recover some of the activities/logs.
    
    On a similar note, it would also be good to have a permanent delete feature built into the recycled folder so that items that are too old (> 6 months old) will
    deleted away for good.
    
2. The automated cleaning does not have a lot of flexibility as the current implementation only cleans up done activities starting from the oldest.

    Thus, it would be good if the `clean` command is expanded to allow users more freedom in specifying what activities to clean.
    
    * `clean /n 3 /t CS2113` does cleaning on the 3 oldest done activities with CS2113 tag.
    * `clean /n 5 /i 1/4/2020 3/4/2020` does cleaning on the 5 oldest done activities with dates between 1 April 2020 and 3 April 2020.
    

#### 3.2.3 Design Considerations

The current design uses the abstract cleaner class to create dedicated cleaners (i.e Storage and Log Cleaners) to perform
cleaning for various data files (e.g activity list data file, logging data file).

There are some benefits to this design.
* Creating an abstract class reduces the amount of repetitive code as common methods between cleaners are abstracted out.
* Abstract classes produce a more OOP solution as different cleaners will handle different parts of the data.

However there are drawbacks to this design too.
* There are some very similar methods with key differences that cannot be abstracted out (for e.g different parameters, different printing).
* This causes the CleanCommand class to have similar and repetitive methods to handle this difference. (for e.g setStorageCleanerOn(), setLogCleanerOn() etc).