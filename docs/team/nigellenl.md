# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that  aims to help manage tasks and projects.  Users can set tags and goals for their entries,  ultimately being able to keep track of what's left to do and maintain an overview of how time was spent.  
  
## Summary of contributions  
### Code contributed  
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=nigellenl)

### Enhancements implemented  
* Edit activities
    * Ensures that the activity record is accurate by giving the user the ability to update certain parameters of the activities that have been recorded.
    * Allow the user to edit the name of existing activities in the activity list.
    * Allow the user to edit the allocated time of existing activities in the activity list.
    * `edit ACTIVITY_NAME /en NEW_NAME` allows the user to edit the activity name.
    * `edit ACTIVITY_NAME /ea NEW_ALLOCATED_TIME` allows the user to edit the allocated time.
    
* Goal setting
    * Allow the user to set and delete goals based on existing tags.
    * `goal TAG_NAME /g GOAL_TIME` allows the user to set the goal for that tag.
    * `goal TAG_NAME /d` allows the user to delete the goal set for that tag.
  
* View goals 
    * Allow the user to view the goals easily in a table format.
    * `goal` displays all the goals that have been set together with the actual time spent on the activities under the tags and the amount of time left to meet the goal. 

### Contributions to documentation  
* Provided syntax and usage examples for some commands (edit, set goal, view goal).
* Added command summary with command name and syntax in the User Guide.

### Contributions to the DG  
* Added setting up instructions.
* Explained the implementation of the `edit` command (under section 3.4) using Sequence Diagram. 
* Gave instructions for manual testing and included sample test cases for `goal` command.
* Added the user stories for the program.
* Added the non-functional requirements for the program. 

### Contributions to team-based tasks  
* Made extensive use of the issue tracker to manage enhancements and bugs.
* Did refactoring for commands by creating command classes.
* Generate table of content for user guide.

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

### 3.5 Edit feature
The edit feature allows the user to make changes to activities that have been saved in the activity list. This is to allow the user to rectify any mistakes that may have been made during the initial recording of the activity. 

#### 3.5.1 Current Implementation
The following sequence diagram shows how the edit feature works.
The current implementation of the edit feature allows the user to edit the activity name as well as its allocated time.
The following sequence diagram shows how the edit feature works for editing the activity name. The diagram for the editing of allocated time is omitted as the sequence is relatively similar.  

_[The sequence diagram has been omitted in this section]_

The current implementation of the edit feature allows the user to edit only the name and allocated time parameter of the activity. When the user wants to edit an activity using the edit command, a new EditCommand object is created. The `executeCommand()` method of the EditCommand object is called and the specified parameters are updated accordingly.

The order of method calls to edit the activity details is as follows if the specified activity exists (meaning `index != -1`) else an exception is thrown:
1. The `updateName()` method of the ActivityList class is called, with the user-specified parameters of the activity index and new activity name
2. The `get()` method is self-invoked by the ActivityList class to obtain the activity at the given index 
3. The `setName()` method of the Activity class is called to edit the activity name to the user-specified name
4. The activity is updated with its new name in the activityList.
5. The `fieldChangeUpdateFile()` method of the StorageHandler class is called to update the data file with the new activity name.


#### 3.5.2 Additional Implementations
The current implementation of the edit feature only allows the user to edit the activity name and allocated time. Hence, additional implementations of the edit feature could allow the user to edit other parameters of the activity such as the tags and the start and end dates. 

This will require the implementation of more update methods in the ActivityList class to allow for the changes to be updated in the activityList after it has been edited. Additionally, there may be more updates required if the tags were to be edited due to the tag goals feature.

The flowchart below shows the flow of activities if the feature of editing tags were to be implemented.   

_[The flowchart diagram has been omitted in this section]_


#### 3.5.3 Design Considerations
##### Current Design
The user is able to edit only the name and allocated time of the activity, which are user input data.     

**Pros:**
* The user is able to correct any mistake made during the recording of the activity.
* The user is able to adjust their allocated time for the activity based on their needs.
* Ensures that the record of activities is accurate and consistent in order for more efficient analysis of the time spent.

**Cons:** 
* The user is only able to edit 2 parameters of the activity, which may be restrictive for them.  

##### Possible Design
The user is able to edit any parameters of the activity, including tags, start and end date/time. 
 
**Pros:**
* The user has more flexibility in modifying the record of activities based on their needs.

**Cons:**  
* By allowing the user to edit the date and time, there may be potential inaccuracies in the record of activities, defeating the purpose of the time tracking program. 
* By allowing the user to edit the tags, the tag goals command may become more complicated due to the need to keep track of the presence of the tags.
