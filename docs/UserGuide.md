# User Guide

## Introduction


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Jikan` from [here](https://github.com/AY1920S2-CS2113-T15-1/tp/releases).
3. Save the Jikan.jar file into an empty folder
4. Open a command window in that folder
5. Run the command java -jar duke.jar


## Commands
### Starting an activity: `start`  
**Usage:** Starts recording the time for a new activity.

**Format:** `start ACTIVITY_NAME /a ALLOCATED_TIME /t TAGS`  
  
* `ACTIVITY_NAME` can contains spaces and must be less than 25 characters.   
* `ACTIVITY_NAME` must also be unique (should the user start an already existing activity, the option to `continue` will be given).
* `ALLOCATED_TIME` should be of the format [HH/MM/SS].
* `TAGS` are separated by spaces.
* `ALLOCATED_TIME` and `TAGS` are optional.
  
**Example:**   
`start CS1010 assignment /a 01/30/00 /t CS1010 core`  
`start GER1000 quiz /t GER GEmod`
`start revision`

### Continuing an activity: `continue`
**Usage:** Continues recording the time of an activity that you have previously started.

**Format:** `continue ACTIVITY_NAME`
* `ACTIVITY_NAME` must be an existing activity in the activity list.

**Example:**
`continue CS1010 assignment`

### Ending an activity: `end`
**Usage:** Stops recording the time for an ongoing activity and stores it into the activity list.

**Format:** `end`  
* An activity must be started or continued before it can be ended.

### Aborting an activity: `abort`
**Usage:** Aborts the current activity and does not save it to the activity list.

**Format:** `abort`

### Deleteing an activity: `delete`
**Usage:** Deletes an activity in the activity list.

**Format:**   `delete ACTIVITY_NAME`

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

## Command Summary

### Commands at a glance
|Command|Format  |Usage|
|---|---|---|
|start  | `start ACTIVITY_NAME /a ALLOCATED_TIME /t TAGS` |Starts an activity
|continue|`continue ACTIVITY_NAME`|Continues an actvity
|end|`end`|Ends an activity
|abort|`abort`| Aborts an activity
|delete|`delete ACTIVITY_NAME` | Deletes an activity
|edit name|`edit ACTIVITY_NAME /e NEW_NAME`|Edits activity name
|edit tags||Edits activity tags 
|list all|`list`|Lists all completed activities
|list date|`list DD/MM/YYYY` |List all activities on that day
|list range|`list DD/MM/YYYY DD/MM/YYYY`|List activities within the range
|list day|`list day` | List all activities today.
|list week|`list week`|List all activities this week.
|list month|`list month`| List all activities this month.
|filter|`filter TAG_NAME`|List all activities with tag `TAG_NAME`
|find|`find KEYWORD`|List all activities containing `KEYWORD`
|graph|`graph SCALE`|Graphs the duration of the activities
|graph tags|`graph tags`|Graphs the duration of the tags
|clean|

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.


