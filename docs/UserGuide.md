# User Guide

## Introduction

Jikan is a CLI time management tool that allows you to track the amount of time that you spend on different activities. This user guide will show you how to use the program effectively. 

## Quick Start
1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Jikan` from [here](https://github.com/AY1920S2-CS2113-T15-1/tp/releases).

Features 
=======
## Usage
Jikan lets you record how much time you spend on various activities so that you can easily see what took up the most time today / this week / this month.

To start, record your first activity using the `start ACTIVITY_NAME` command.

Add some tags to your activities to group similar activities together using `/t`.

Add allocated time to your activities using `/a`. Do note that the time allocated for an activity is represent by the target column in the diagram below. (These two words are used interchangeably).
 
When you are done with the activity, or want to move onto something else, tell Jikan to `end` and the Activity time will be recorded and saved to your list.

You can view all your activities using the `list` command. Activities will be shown in this format:
![Continue command sequence diagram](./pictures/list.PNG)

You can also view all your activities over a period of time by using `list` with extra parameters. For example `list week` will return a list of all activities this current week, as shown below.
![Continue command sequence diagram](./pictures/list_week.PNG)

Filter out the activities you want to see using the `find` or `filter` command. This is our list after filtering out all our activities tagged as `core`. 
![Continue command sequence diagram](./pictures/list_core.PNG)

To easily see what took up the most of your time, use the `graph` command to view a chart of your activities. 
![Continue command sequence diagram](./pictures/graph_core.PNG)

Now it's clear that lab 3 ex3 took up the most of your time. 

Not done with an activity and want to continue it? Use the `continue` command to continue recording time for a previously started activity.

Finally, when you're done and want to close the app, simply say `bye` and Jikan will exit.

This is just a quick overview of what Jikan can do for you. For more details on each individual command, read the command guide below.

## Commands  
### Starting an activity: `start` 
**Usage:** Starts recording the time for a new activity.  
  
**Format:** `start ACTIVITY_NAME /a ALLOCATED_TIME /t TAGS`    
 * `ACTIVITY_NAME` can contains spaces and must be less than 25 characters.     
* `ACTIVITY_NAME` must also be unique (should the user start an already existing activity, the option to `continue` will be given).  
* `ALLOCATED_TIME` should be of the format [HH/MM/SS].  
* `TAGS` are separated by spaces.  
* `ALLOCATED_TIME` and `TAGS` are optional.  
    
**Example:** `start CS1010 assignment /a 01/30/00 /t CS1010 core` `start GER1000 quiz /t GER GEmod`  
`start revision`  
  
### Continuing an activity: `continue`  
**Usage:** Continues recording the time of an activity that you have previously started.  
  
**Format:** `continue ACTIVITY_NAME`  
* `ACTIVITY_NAME` must be an existing activity in the activity list.  
  
**Example:**  
`continue CS1010 assignment`  
  
### Ending an activity: `end`  
**Usage:** Stops recording the time for an ongoing activity and stores it into the activity list.  
  
**Format:** `end` * An activity must be started or continued before it can be ended.  
  
### Aborting an activity: `abort`  
**Usage:** Aborts the current activity and does not save it to the activity list.  
  
**Format:** `abort`  
  
### Deleteing an activity: `delete`  
**Usage:** Deletes an activity in the activity list.  
  
**Format:** `delete ACTIVITY_NAME`  
  
### List activities: `list`  
**Usage:** Displays a list of the completed activities.  
  
**Format:** `list TIME_PERIOD`  
* If no `TIME_PERIOD` is given, all activities will be listed.  
* `TIME_PERIOD` should be of the format [dd/MM/yyyy] or [yyyy-MM-dd]  
* `TIME_PERIOD` can either be a specific date or over a range.  
  
**Example:**  
`list` List all activities.  
`list month` or `list monthly` Lists all activities in the current month.  
`list week` or `list weekly` List all activities in the current week.  
`list day` or `list daily` List all activities in the current day.  
`list 01/01/2020` or `list 2020-01-01` List all activities on 1 Jan 2020.  
`list 01/01/2020 20/02/2020` List all activities than fall within 1 Jan 2020 and 20 Feb 2020.  
  
### Graph the duration of your completed activities: `graph`  
**Usage:** Gives a visual representation of the duration of all your past activities so that you can easily see what took up the most of your time.  
  
**Format:** `graph SCALE` or `graph tags`  
  
* Parameters for graph are either `SCALE` or `tags`.  
* `SCALE` is the scale of the graph in minutes (i.e. how many minutes each unit in the graph represents) and should be an integer value.  
* `graph tags` will graph the cumulative duration of individual tags (each unit represents 10 minutes)  
* The graph will be based on the latest list displayed on the command prompt.  
  
**Example:**  
`graph 10`  

## Usage of Automated Cleaning

As Jikan is a time tracker application which works with various data files (data file for activities, log files for execution history
etc.), over time it can be a mess to deal with these data files especially when they get too big. Thus, Jikan provides automated cleaning
services for such situations.

### Activating the automated cleaning: `clean on | clean log on`
At runtime, users can switch on the automated cleaning services. Once the automated cleaning is activated, the application will
do an auto cleanup of files at the start of every execution until this services is switch off. Do note that the cleaning will only start from the next execution.
(i.e no cleaning will be done for the current execution which activated auto cleaning).

Note: \
`clean on` activates the cleaning of data files where activities are stored. \
`clean log on` activates the cleaning of log files where application execution history is stored.

### Deactivating the automated cleaning: `clean off | clean log off`
At runtime, users can switch off the automated cleaning services. Once deactivated, the application will
stop doing an auto cleanup of files at the start of every execution. Similarly, the changes only applies to the next execution.

Note:\
When the application is executed for the first time, the automated cleaning is deactivated by default and will remain so until it is activated by the user.

### Specifying how much data to clean: `clean /n NUMBER | clean log /n NUMBER`
At runtime, the user can manually set the amount of data to clean using these commands where `NUMBER` is an integer based on user input. 
Thus, `clean /n 5` will automatically clean the top 5 oldest activities from the activity list upon every startup (assuming automated cleaning
is activated).

Note:\
Default value for `NUMBER` for data files : 3\
Default value for `NUMBER` for log files : 10

## Graph Functions

### Graph out all targets and their progress: `graph targets`
Using this command, the user will get a graphical representation of all activities with targets and their progress with respect to 
the allocated time. 

![Continue command sequence diagram](./pictures/GraphTargets.png)
  

## Finding and Filtering Activities

### Finding Activities by Name: `find`
Users can request for a sub-list of activities that has names containing a given keyword to be printed.

Format: `find KEYWORD`

### Filtering Activities by Tags: `filter`
Users can request for a sub-list of activities that has specific tags.

Format: `filter TAGNAME1 TAGNAME2`

### Further Finding/Filtering: `-s`
Users can chain `find` and `filter` commands to generate an even smaller sub-list of activities based on their needs. 
This is can be particularly useful when the user wants to generate a `graph`.

This flag applies to `find` and `filter` commands only. 

Format: 
* `filter -s TAGNAME1 TAGNAME2`
* `find -s keyword`


### Adding a to-do: `todo`
Adds a to-do item to the list of to-dos.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ


## Command Guide

* Start an activity: `start ACTVITY_NAME` 
    * optional: `start ACTIVITY_NAME /a ALLOCATED_TIME /t TAGS`
* Abort an activity: `abort`
* Stop an activity: `end`
* Continue an activity: `continue ACTIVITY_NAME`
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
* Edit an activity: `edit ACTIVITY_NAME [flag]`
    * Edit activity name: `edit ACTIVITY_NAME /en NEW_NAME`
    * Edit activity tags: `edit ACTIVITY_NAME /et NEW_TAG1 NEW_TAG2`
    * Edit activity allocated time: `edit ACTIVITY_NAME /ea NEW_ALLOCATED_TIME`
* Delete an activity: `delete ACTIVITY_NAME`
* Find activities with keyword: `find KEYWORD`
    * optional: `find -s KEYWORD` for more specific find
* Filter activities by tags: `filter TAG_NAME`
    * optional: `filter -s TAG1 TAG2` for more specific filter
* Set a goal for tags: `goal TAG_NAME /g DURATION`
* View goals for tags: `goal`
* Display graph by tags: `graph tags`
* Display graph by duration: `graph INTERVAL`
* Display graph by targets: `graph targets`
* Clean data files: `clean [command]`
    * Activate auto data cleaner: `clean on`
    * Activate auto log cleaner: `clean log on`
    * Deactivate auto data cleaner: `clean off`
    * Deactivate auto log cleaner: `clean log off`
    * Specify number of files to clean for data: `clean /n NUMBER`
    * Specify number of files to clean for logs: `clean log /n NUMBER`
* Terminate the program: `bye`
