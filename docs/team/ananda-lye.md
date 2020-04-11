# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that  aims to help manage tasks and projects.  
Users can set tags and goals for their entries,  
ultimately being able to keep track of what's left to do and maintain an overview of how time was spent.  

## Summary of contributions  
### Code contributed  
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=ananda-lye)

### Enhancements implemented
* Find and Filter Activities
    * Developed the `find` and `filter` commands for users to view a sub-list of activities, matching for name and 
    tag keywords respectively.
    * Both commands allow for multiple keywords to be matched with `find` accepting `/` separated keywords and `filter`
    accepting space-separated keywords. Activities which match either keyword will be included in the sub-list.
    * Both commands can be limited to only searching activities in the last shown list by including the `-s` flag.
    This is particularly helpful when used before graphing functions to omit undesired activities.
        * This can also be included as a single-line user input, separating commands by `;` which reduces the
        number of user inputs and printing calls required to achieve the same results.
	
* Last Shown List Implementation
    * Proposed and implemented the last shown list which is used in `list`, `find`, `filter` and `graph` commands.
    * This proposal sets the direction for how the Jikan software is used, as all analysis done by the user revolves
    around this functionality (combining `list` by date range, `find` and `filter` chaining, to allow `graph` to be 
    clutter-free).
    
* Activity Progress and Ui
    * Proposed and implemented the progress percentage based user messages, allowing for the progress bars in `end` and
    `graph` commands.
    

### Contributions to documentation
* Implemented and de-conflicted the high-level flow of the User Guide, distinguishing from Basic to Advanced features for 
improved format standardisation.
* Provided instructions and examples for `find` and `filter` commands and their multiple variations.

### Contributions to the DG
* Drafted write-ups and diagrams in the design section, mainly the overall architecture diagram and class diagram.
* Drafted the `find` and `filter` sections, including the sequence diagram, design considerations, and proposed features.

### Contributions to team-based tasks
* Generated ideas with the team on the set of features for the Jikan application.
* Made use of the issue tracker extensively to track enhancement and bugs found.
* Developed jUnit tests for `find`, `filter` and `list` commands.

### Review/mentoring contributions
* Provided feedback to teammates before and after implementation to ensure that everyone is on the same page.

### Contributions beyond the project team
* Provided feedback to the developer guide of another team.
    * [Reviewing of DG on Week 11](https://github.com/nus-cs2113-AY1920S2/tp/pull/14)
    
* Reported bugs in other team's product in PE dry run.
    * [PED](https://github.com/ananda-lye/ped/issues)

## Contributions to the User Guide (Extracts)

### Finding Activities by Name: `find`
**Usage:** Users can request for a sub-list of activities that has names which contain any of the given keywords. If there are more than one keyword, each keyword should be separated with ` / `.

**Format:**
* `find KEYWORD`
* `find KEYWORD1 / KEYWORD2 / KEYWORD3`

### Filtering Activities by Tags: `filter`
**Usage:** Users can request for a sub-list of activities that has specific tags. Each tag should be space separated.

**Format:**
* `filter TAGNAME`
* `filter TAGNAME1 TAGNAME2`

### Chaining Finds & Filters: `-s`
**Usage:** Users can provide the `find` and `filter` command on the last shown list (also compatible after a `list` 
command) by providing the `-s` flag after each `find` or `filter` command.

**Format:** 
* `find -s KEYWORD`
* `filter -s TAGNAME`
* `filter -s TAGNAME1 TAGNAME2`
* `find -s KEYWORD1 / KEYWORD2 / KEYWORD3`

**Example:**  
If we want to find all CS2106 tutorials, we can first use `filter 2106` to filter out all activities tagged `2106`, then use the find command with the flag, `find -s Tutorial` to get a list of all 2106 Tutorials.

#### Single Input Chaining: `;`
**Usage:** Users can achieve the same outcome as multiple `-s` chaining with a single input. This is done by separating
`find` and `filter` commands with ` ; `.

**Examples:**
* `filter TAGNAME ; find KEYWORD1 ; find KEYWORD2`
* `filter -s TAGNAME ; find KEYWORD1 ; find KEYWORD2`

Note: `-s` is only relevant in the first command of the entire input string, as subsequent commands are automatically chained.

## Contributions to the Developer Guide (Extracts)
## 2. Design
The section provides a high-level explanation of how the Jikan software is designed.

### 2.1 High-Level Architecture
The users interact with the Jikan software which modifies the local storage data file.

Within the Jikan software, there are 5 main components:
* **Parser Component** - Parses the user inputs and calls the relevant `Command` object to execute the desired
command.
* **Ui Component** - Prints to the user the output of the desired `Commands`.
* **Commands Component** - Contains all the `Commands` to be called by the `Parser` based on user inputs.
* **Activities Component** - Maintains the non-permanent state of all `Activities` in the `Activity List` to be accessed
by the executing `Commands`.
* **Storage Component** - Interacts with and modifies the local storage file, which contains the permanent (lasting
even after the program terminates) state of all activities. It retrieves this permanent state and populates the `Activity List` at the start of each session.

### 3.8 Find & Filter Features

#### Find Feature
This command accepts keyword(s) and searches either the entire activity list or the last shown list for activities with 
names containing each keyword.

#### Filter Feature
This feature accepts space-separated keyword(s) to search either the entire list or the last shown list 
for activities with tags matching each keyword. The keywords should be an exact-match with the tag names.


#### 3.8.1 Design Considerations
As the `find` and `filter` commands are important for the user to analyse and eventually graph time-spent on each 
activity. The user should be allowed to query for all useful combinations of activities in the activity list. 
This entails:
* The user should be allowed to match for multiple keywords at once.
* The user should be allowed to exclude certain activities by limiting his search to a previously shown list as 
    opposed to the entire activity list.
    (chaining `list`, `find`, and `filter` commands).


#### 3.8.2a Current Implementation for Find
* This feature is called by the user when the `find` command is entered into the command line. 
The string following the command are the parameters:
    * `-s` flag indicates that the searching should be limited to activities previously shown to the user.
    * The remaining parameters are a string of keywords separated by ` / `. 
* The Parser will create a FindCommand object.
* The FindCommand will invoke its own `executeCommand()` method.
    * The Parser's `lastShownList` will be cleared.
    * Then it will loop through `activityList` to find activities with names that contain the keyword.
    * If one is found, it will be added to `lastShownList`.
    * `printResults()` of the Ui will be called:
        * If `lastShownList` is not empty, it will print the matching activities.
        * Else, it will respond to the user that there are no tasks which match the given keyword.


#### 3.8.2b Current Implementation for Filter 
* This feature is called by the user when the `filter` command is entered into the command line. The space separated strings following the command are the keywords to match activity tags with.
* The Parser will create a FilterCommand object.
* The FindCommand will invoke its own `executeCommand()` method.
* The Parser's `lastShownList` will be cleared.
* For each keyword:
    * Then it will loop through `activityList` to find activities with tags that contain the keyword.
    * If one is found, it will be added to `lastShownList`.
    * `printResults()` method of the Ui will be called
        * If `lastShownList` is not empty, it will print the matching activities.
        * Else, it will respond to the user that there are no tasks which match the given keyword.
	
#### 3.8.4 Additional features
`find` and `filter` command supports the limiting of searches to activities in the last shown list. This
is done in 2 ways:
* The `-s` flag following the command (eg. `find -s keyword`)
* The `;` delimiter for a combination of `find` and `filter` in a single input (eg. `find KEYWORD ; filter TAGNAME`)

