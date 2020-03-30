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

To start, record your first activity using the `start ACTIVITY_NAME` command. Add some tags to your activities to group similar activities together. When you are done with the activity, or want to move onto something else, tell Jikan to `end` and the Activity time will be recorded and saved to your list.

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


## Finding and Filtering Activities

### Finding Activities by Name: `find`
Users can request for a sub-list of activities that has names containing a given keyword to be printed.

Format: `find KEYWORD`

### Filtering Activities by Tags: `filter`
Users can request for a sub-list of activities that has specific tags.

Format: `filter TAGNAME1 TAGNAME2`

### Further Finding/Filtering: `-s`
Users can chain `find` and `filter` commands to generate an even smaller sub-list of activities based on his/her needs. 
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
    * optional: `start ACTIVITY_NAME /t TAGS /a ALLOCATED_TIME`
* Abort an activity: `abort`
* Stop an activity: `end`
* Continue an activity: `continue ACTIVITY_NAME`
* List all activities: `list`
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
* Clean data files: `clean [command]`
    * On auto data cleaner: `clean on`
    * Off auto data cleaner: `clean off`
    * Specify number of files: `clean /n NUMBER`
* Terminate the program: `bye`
