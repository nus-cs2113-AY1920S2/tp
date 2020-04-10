# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that  aims to help manage tasks and projects.  Users can set tags and goals for their entries,  ultimately being able to keep track of what's left to do and maintain an overview of how time was spent.  
  
## Summary of contributions  
### Code contributed  
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=nigellenl)

### Enhancements implemented  
* Edit activities
    * Allow the user to edit the name of existing activities in the activity list.
    * Allow the user to edit the allocated time of existing activities in the activity list.
    * `edit ACTIVITY_NAME /en NEW_NAME` allows the user to edit the activity name.
    * `edit ACTIVITY_NAME /ea NEW_ALLOCATED_TIME` allows the user to edit the allocated time.
    
* Goal setting
    * Allow the user to set and delete goals based on existing tags.
    * `goal TAG_NAME /g GOAL_TIME` allows the user to set the goal for that tag.
    * `goal TAG_NAME /d` allows the user to delete the goal set for that tag.
  
* View goals 
    * Allow the user to view the goals in a table format.
    * `goal` displays all the goals that have been set. 

### Contributions to documentation  
* Provided syntax and usage examples for some commands (edit, set goal, view goal)
* Added command summary with command name and syntax.

### Contributions to the DG  
* Added setting up instructions.
* Explained the implementation of the `edit` command (under section 3.4) using Sequence Diagram. 
* Gave instructions for manual testing and included sample test cases for `goal` command.
* Added the non-functional requirements for the program. 

### Contributions to team-based tasks  
* Made extensive use of the issue tracker to manage enhancements and bugs.
* Did refactoring for commands by creating command classes.

### Review contributions  
* Discussed with team on how to further enhance existing features (e.g. allowing user to edit allocated time)

## Contributions to the User Guide (extracts)  
### Editing an activity: `edit`
**Usage:** Edits the name or allocated time of an activity in the activity list.

**Format** 
* `edit ACTIVITY_NAME /en NEW_NAME`
* `edit ACTIVITY_NAME /ea NEW_ALLOCATED_TIME`
    * `NEW_ALLOCATED_TIME` should be in the format [HH:MM:SS] 
    
**Example:**  
`edit CS1010 assignment /en CS1010 assignment 2` Activity name is edited to `CS1010 assignment 2`  
`edit CS1010 assignment /ea 10:00:00` Allocated time for activity is edited to `10:00:00` 

### Tag Goals

By using the `goal` command, users can set specific goals for how long they would like to spend on activities under a certain tags as well as view the amount of time they have spent in total for those activities as compared to their goal.

### Set goal: `goal TAG_NAME /g DURATION`
**Usage:** Sets a duration goal for a tag

**Format:** `goal TAG_NAME /g DURATION`  
* The duration should be in the format [HH:MM:SS]

**Example:** `goal core /g 24:00:00` a goal of `24:00:00` is added for the tag `core`  

### Delete goal: `goal TAG_NAME /d`
**Usage:** Deletes the duration goal set for the tag.

**Format:** `goal TAG_NAME /d`

### View goals: `goal`
**Usage:** Displays the tags with their goals, actual time spent on activities with these tags and the difference between the 2 timings.

**Format:** `goal`  
  
## Contributions to the Developer Guide (extracts)  

### Edit feature
The current implementation of the edit feature allows the user to edit the activity name as well as its allocated time.
The following sequence diagram shows how the edit feature works for editing the activity name. The diagram for the editing of allocated time is omitted as the sequence is relatively similar.  

*[The sequence diagram has been omitted in this section]*  

The current implementation of the edit feature allows the user to edit only the name parameter of the activity. When the user wants to edit an activity using the edit command, the Parser creates a new EditCommand object. The `executeCommand()` method of the EditCommand object is called and the specified parameters are updated accordingly.

The order of method calls to edit the activity details is as follows if the specified activity exists (meaning `index >= 0`) else an exception is thrown:
1. The `updateName()` method of the ActivityList class is called, with the user-specified parameters of the activity index and new activity name
2. The `get()` method is self-invoked by the ActivityList class to obtain the activity at the given index 
3. The `setName()` method of the Activity class is called to edit the activity name to the user-specified name
4. The activity with the updated name is returned to the activityList  

#### 3.4.2 Additional Implementations
The current implementation of the edit feature only allows the user to edit the activity name and allocated time. Hence, additional implementations of the edit feature could allow the user to edit other parameters of the activity such as the tags and the start and end dates. 

This will require the implementation of more update methods in the ActivityList class to allow for the changes to be updated in the activityList after it has been edited. 

#### 3.4.3 Design Considerations
By letting the user edit the name and allocated time of the activity, it will allow them to correct any mistakes made during the data entry as well as allowing them to update their allocated time for the activity. This ensures that there is an accurate record of activities such as in cases where the user may be trying to record the same activity but has misspelled it, resulting in the program regarding it as a different activity where there would be multiple unnecessary new entries in the activity list, making the analysis of the time spent more tedious and inaccurate.

However, by allowing the user to edit the start date and time, there may be potential inaccuracies in the actual activity recording. This is due to the fact that the time recorded in the program is based on the LocalDateTime. By introducing user input, the dates and time may be recorded incorrectly, defeating the purpose of the time tracking program. 
