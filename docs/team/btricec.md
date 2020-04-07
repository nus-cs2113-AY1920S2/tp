
# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that  aims to help manage tasks and projects.  Users can set tags and goals for their entries,  ultimately being able to keep track of what's left to do and maintain an overview of how time was spent.  
  
## Summary of contributions  
### Code contributed  
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=btricec)
  
### Enhancements implemented  
* Starting and ending activities 
  * Implemented the basic `start` and `end` commands.
  * `start` allows the user to start an activity and add tags to that activity.
  * `end` ends the started activity and saves it to the activity list.
  * `abort` stops the current activity and does not save it.
  * Implemented an additional feature that gives the user the option to end an activity when the user exits the app after starting an activity.
  
* Continue
  * Allows the user to continue an activity in the activity list.
  * Implemented an additional feature that gives the user the option to continue an activity if they used the `start` command to start an activity already in the activity list.

* Graph activities and tags
  * `graph activities` displays a graph of the duration of each activity based on the `lastShownList`
  * `graph tags` calculates the duration of activity tags in the `lastShownList` and displays a graph of the cumulative duration for each tag.

* Delete
  * Allows the user to delete an activity from the activity list.
  
### Contributions to documentation  
* Did the *Usage* section to give an overall example of how Jikan can be used in logical flow of commands, providing examples of expected outputs.
* Provided syntax and usage examples for some commands (namely, start, end, continue, abort and delete)
  
### Contributions to the DG  
* Explained the implementation of the `continue` command (under section 3.5) using Sequence Diagrams 
* Explained the implementation of the `graph` command (under section 3.9) using Sequence Diagrams
* Gave instructions for manual testing and included sample test cases for `list`, `continue` and `graph` commands.
  
### Contributions to team-based tasks  
* Set up the issue tracker with relevant labels and milestones.
* Made extensive use of the issue tracker to manage enhancements and bugs.
* Released v1.0 and v2.0 of Jikan, and also provided a set of sample data for v2.0 for testing.
* Did more extensive refactoring for commands, moving the command execution to the command class itself.
  
### Review contributions  
* Contributed to ideation and implementation discussion of new features offline.
* Although I did not explicitly review PRs on github, I actively tried to help with text-ui issues which were a big problem for our group. (text-ui file had to be updated every day to reflect the current date for it to pass)
  
  
## Contributions to the User Guide (extracts)  
  
## Contributions to the Developer Guide (extracts)  
### 3.5 Continue Feature
The continue feature allows the user to continue a previously ended activity.

#### 3.5.1 Current Implementation
(diagrams are omitted) 
**Continuing an activity:**

-   When the user enters the command to continue an activity, a  _ContinueCommand_  object is created in  _Parser_. The method  `executeCommand()`  of the  _ContinueCommand_  object is then called.
-   `executeCommand`  checks if the given activity name exists in the activityList by calling  `findActivity()`  (if it doesn’t an exception is thrown, omitted in the sequence diagram above)
-   It then gets the  `name`  and  `tags`  of the activity to be continued and saves it to a public static variable of  _Parser_  object.
-   It also gets the current time and saves it to a public static variable of  _Parser_  object.

**Ending a continued activity:**

-   When the user wants to end the continued activity, an  _EndCommand_  object is created in  _Parser._  The method  `executeCommand()`  of the  _ContinueCommand_  object is then called and it in turn executes the  `saveActivity()`  method of the  _ActivityList_  class.
-   `saveActivity()`  gets the current time and saves it to a public static variable of  _Parser_  object.
-   Then the elapsed time is calculated using the  `between()`  method of  _Duration_  class.
-   The elapsed time is added with the previous duration of the activity to get the  `newDuration`  using the  `plus()`  method of Duration class.
-   `updateDuration()`  method is called to update the  `duration`  attribute of the continued activity in the  `activityList`  as well as the  `data.csv`  file.

#### 3.5.2 Design Considerations

**Execution:**

-   Continue by activity name (current implementation)
    -   **Cons:**  Activity names have to be unique.
    -   **Pros:**  More versatile, resistant to changes in the activity list
-   Continue by activity index
    -   **Cons:**  need to add an additional index field to the Activity class, index is not fixed, changes when an activity is deleted
    -   **Pros:**  Can reuse activity names.

Although the current implementation of the continue feature disallows users to have multiple activities with the same name, we felt that the versatility of this choice outweighed the cons. Firstly because if the activityList got too big, it would be hard for the user to get the index of the task they wanted to continue. Also, the index would constantly be changing when changes are made to the list.

#### 3.5.3 Additional Features

As users can only have activities with unique names, when a user wants to start an activity which already exists in the activityList, they will be given the option to continue the stated activity.

### 3.9 Graph Feature

This feature gives the user a visual representation of their activity duration and activity goals.  
Graph can be used along with  `list`,  `find`  and  `filter`  to sieve out the data to be graphed.

#### 3.9.1 Current Implementation
(diagrams are omitted) 
-   This feature is called by the user when the  `graph`  command is entered into the command line. The user will then have to specify what he would like to graph (goals progress bar / tag duration / activity duration).
-   The Parser will create a GraphCommand object.
-   The GraphCommand will invoke its own  `executeCommand()`  method.

**Graph targets**  
This displays the progress bar for the duration with respect to allocated time of activities in the  `lastShownList`.

-   If the user indicated  `targets`, Ui calss will be called to execute graphTargets.

**Graph tags**  
This displays a bar graph of the cumulative duration of the tags for each activity in the  `lastShownList`. E.g. if 3 activities in the  `lastshownlist`  are tagged  `CS2113`, the durations of these 3 activities are added up and associated with the tag  `CS2113`  in the graph.

-   If the user indicated  `tags`,  `GraphCommand`  will call it's own  `graphTags`  method.
-   A HashMap (`tags`) of tags to duration is created.
-   `graphTags`  iterates through every activity in  `lastshownlist`  and in each loop,  `extractTags`  is called.
-   `extractTags`  loops through the tags of that activity. Tag is added to the  `tags`  if it is not found. Else, the duration of the activity is added to the corresponding tag in  `tags`.
-   `tags`  and  `interval`  (how many minutes each point in the graph represents) is passed to the method printTagGraphs in Ui to print the graph.

**Graph activities**  
This displays a bar graph of the durations of each activity in the  `lastShownList`.

-   If the user indicated  `activities`,  `GraphCommand`  will call it's own  `graphDuration`  method.
-   `graphDuration`  calls  `printActivityGraph`  of the Ui class and passes the  `interval`  parameter, which is how many minutes each point in the graph represents.

#### 3.9.2 Additional features

As graph gets it's data based on the  `lastShownList`, users can pair the  `graph`  command with  `find`,  `filter`, and  `list`  to sieve out the activities to be graphed.