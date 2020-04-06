# Project Portfolio Page (PPP)
## Project overview
**Jikan** is a CLI time-tracker built in Java that
aims to help manage tasks and projects.
Users can set tags and goals for their entries,
ultimately being able to keep track of what's left to do
and maintain an overview of how time was spent.

## Summary of contributions
### Code contributed
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=rdimaio)

### Enhancements implemented
* Storage
    * Storage handles saving, creating and accessing files.
    This is a necessary feature, given the nature of Jikan as a timetracker
    that needs to retain information across multiple sessions.
* StorageHandler
    * Higher-level interface to Storage objects; 
    StorageHandler provides functions to retrieve and manipulate data
    for the rest of the application.
* List
    * The `list` command allows the user to list all the activities for a specified
    date or range of dates. In many cases, the user does not want to specify
    the exact dates; that is why the user can simply ask to `list day`, `list week`,
    `list month 05` etc. to list entries belonging in the specified time frame.

### Contributions to documentation
* Provided information on how to use the `list` command, including
all the possible parameters (`day`, `week`, `month`)

### Contributions to the DG
* Provided information on the `Storage` object,
including how to use it for multiple concurring data files
* Provided information on the `StorageHandler` object,
specifically which functions can be used to write/edit/remove data
* Provided information on the possibilities of the `list` command

### Contributions to team-based tasks
* Generating tables of content automatically for documentation
* Made extensive use of the issue tracker
    * Labelled each PR and issue with severity and type
    * Linked PRs to close their respective issues automatically

### Review contributions
* Provided comments on possible ways to solve issues,
e.g. [#162](https://github.com/AY1920S2-CS2113-T15-1/tp/issues/162)

### Contributions beyond the project team

* Community
    * Reported bugs:
        * ["Submit post-lecture quiz" missing from Week 12 Admin info section](https://github.com/nus-cs2113-AY1920S2/forum/issues/96)
        * [Arrow pointing the wrong way in the new UML video?](https://github.com/nus-cs2113-AY1920S2/forum/issues/75)
    * Helped other students:
        * [Problem when importing contacts project to Intellij](https://github.com/nus-cs2113-AY1920S2/forum/issues/23#issuecomment-581352650)
        * [Markdown syntax](https://github.com/nus-cs2113-AY1920S2/forum/issues/88#issuecomment-603329337)

## Contributions to the User Guide (extracts)

### Listing activities: `list`  
**Usage:** Displays a list of the completed activities.  
  
**Format:** `list TIME_PERIOD` 
* If no `TIME_PERIOD` is given, all activities will be listed.  
* `TIME_PERIOD` can be `day` or `week`
* To list activities in a specific month of the current year, use `list month MONTH_NAME` where `MONTH_NAME` must be spelled out in full (i.e. January and not Jan).
* Otherwise, `TIME_PERIOD` should be of the format [dd/MM/yyyy] or [yyyy-MM-dd]  
* `TIME_PERIOD` can either be a specific date or over a range.  
  
**Example:**  
`list` List all activities.    
`list month april` Lists all activities in April.  
`list week` or `list weekly` List all activities in the current week.  
`list day` or `list daily` List all activities in the current day.  
`list 01/01/2020` or `list 2020-01-01` List all activities on 1 Jan 2020.  
`list 01/01/2020 20/02/2020` List all activities than fall within 1 Jan 2020 and 20 Feb 2020.  
  

### Command Guide

* List all activities: `list`
    * List today's activities: `list day` or `list daily`
    * List this week's activities: `list week` or `list weekly`
        * List a specific week's activities by day: `list week DATE` or `list weekly DATE`, 
        where `DATE` is in either `yyyy-MM-dd` or `dd/MM/yyyy` format
    * List this month's activities: `list month` or `list monthly`
        * List a specific month's activities by day: `list month DATE` or `list monthly DATE`, 
        where `DATE` is in either `yyyy-MM-dd` or `dd/MM/yyyy` format
    * List a specific day's activities: `list DATE`, where `DATE` is in either `yyyy-MM-dd` or `dd/MM/yyyy` format
    * List activities within a time frame: `list DATE1 DATE2`, where both `DATE1` and `DATE2` are 
    in either `yyyy-MM-dd` or `dd/MM/yyyy` format 

## Contributions to the Developer Guide (extracts)
### 3.2 Storage feature
The Storage class represents the back-end of Jikan, handling the creation, saving and loading of data. 
Jikan uses a `.csv` file to store its data, formatted in the following way:

`entry-name, start-time, end-time, duration, tags`

All tags are saved in the same cell, separated by a white space; this design decision was taken to make sure that each entry occupies the same number of cells regardless of each entry’s number of tags. The tags are then separately parsed when the data is loaded.

Each Storage objects contains the path to the data file (`Storage.dataFilePath`), the File object representing the data file (`Storage.dataFile`), and an activityList populated with the data from the data file (`Storage.activityList`). Storage optionally supports multiple data files at the same time, allowing implementation of features like multiple sessions and multiple user profiles. 

Storage provides the following functions:
- Constructing a Storage object via `Storage(String dataFilePath)`, which takes in the path to the desired data file (or the path where the user wants to create the data file) as a String object.
- Creating a data file via `createDataFile`.
- Writing to a data file via `writeToFile`. This function takes a single string as parameter and writes it to the data file. It is recommended to only pass single-line strings to keep the file nicely formatted. 
Loading a pre-existing data file via `loadFile`. If a data file already exists for the provided data file path, the function will return `true`; if the specified data file did not previously exist, this function will call the `createDataFile` method and returns `false`. The return value is useful so that the application knows whether or not this is the first session with a specific data file or if data already exists.
- Creating an ActivityList via `createActivityList`. This function calls `loadFile()` to check whether the file already existed or not; if the data file previously existed, it will construct an ActivityList object by passing the data from the data file to it, and return this populated ActivityList object; if the data file did not previously exist, it will return an empty activityList object.

### 3.3 Storage handler
The StorageHandler class functions as a support to the main Storage class, allowing the Jikan application to manipulate the stored data file. Its main provided functions are:
- Removing an entry from the data file via `removeLine`. This function takes in the number of the line to remove.
- Replacing an entry in the data file via `replaceLine`. This function takes in the number of the line to replace, along with the String object that needs to be written to the data file in place of the replaced line.

### 3.6 List feature
This feature is used to list activities within a range specified by the user.
If no parameter is passed to the `list` command, then all the stored activities will be displayed.
By passing a single date, the command returns all activities within that date.
By passing two dates, the command returns all activities that took place within the two dates.
(for an activity to be included in the range, both its start and end time must be within the specified time range).
The user can also provide a verbal command, such as `day`, `week`, or `month`, which
will return all the activities for that day, week or month respectively.
Additionally, the user can specify a specific week of month by including a date
(e.g. `list month 2020-03-01` returns all the activities in March 2020.)

#### 3.6.1 Current implementation
* List all activities: `list`
    * List today's activities: `list day` or `list daily`
    * List this week's activities: `list week` or `list weekly`
        * List a specific week's activities by day: `list week DATE` or `list weekly DATE`, 
        where `DATE` is in either `yyyy-MM-dd` or `dd/MM/yyyy` format
    * List this month's activities: `list month` or `list monthly`
        * List a specific month's activities by day: `list month DATE` or `list monthly DATE`, 
        where `DATE` is in either `yyyy-MM-dd` or `dd/MM/yyyy` format
    * List a specific day's activities: `list DATE`, where `DATE` is in either `yyyy-MM-dd` or `dd/MM/yyyy` format
    * List activities within a time frame: `list DATE1 DATE2`, where both `DATE1` and `DATE2` are 
    in either `yyyy-MM-dd` or `dd/MM/yyyy` format
