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
## Ending an Activity: `end`
After an activity has been completed, the user can enter the `end` command for activity to be saved.
If an allocated time was given with the `/a` extension when it was started, the user will get a progress message
stated how he/she fared for the session.

#### Example:
```
end
-------------------------------------------------------------------------------------------
Ended: ger quiz 11
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------
Almost there ! Here's your progress:
Progress for ger quiz 11: |********************************************      |89% completed
-------------------------------------------------------------------------------------------
```
## Finding and Filtering Activities

### Finding Activities by Name: `find`
Users can request for a sub-list of activities that has any of the given keywords separated by ` / `.

#### Format: 
* `find KEYWORD`
* `find KEYWORD1 / KEYWORD2 / KEYWORD3`

#### Example:
```
find lab / tutorial
-------------------------------------------------------------------------------------------
Here are the matching activities in your list:

   | Name                      | Duration   | Target     | Date       | Tags
1  | lab 3 ex1 demo            | 00:00:00   | 02:14:54   | 2020-03-18 | [2106, lab]
2  | lab 3 ex2                 | 00:00:00   | 03:13:14   | 2020-03-19 | [2106, lab]
3  | lab 3 ex3                 | 07:51:12   | 00:00:00   | 2020-03-25 | [core, 2106, lab]
4  | lab 4 ex2                 | 02:40:21   | 00:00:00   | 2020-03-30 | [core, 2106, lab]
5  | lab 4 demo                | 02:13:14   | 00:00:00   | 2020-04-01 | [core, 2106, lab]
6  | 2106 tutorial 7           | 00:00:00   | 01:20:12   | 2020-03-18 | [2106]
7  | 2113 tutorial questions   | 00:00:00   | 00:48:54   | 2020-03-23 | [2113]
8  | 2106 tutorial 8           | 00:00:00   | 01:23:00   | 2020-03-25 | [2106]
-------------------------------------------------------------------------------------------
```

### Filtering Activities by Tags: `filter`
Users can request for a sub-list of activities that has specific tags (separated only by spaces.

#### Format: 
* `filter TAGNAME`
* `filter TAGNAME1 TAGNAME2`

#### Example: 
```
filter 2106 2113
-------------------------------------------------------------------------------------------
Here are the matching activities in your list:

   | Name                      | Duration   | Target     | Date       | Tags
1  | study for 2106 midterm    | 00:00:00   | 06:17:03   | 2020-03-28 | [2106, midterm]
2  | 2106 tutorial 7           | 00:00:00   | 01:20:12   | 2020-03-18 | [2106]
3  | lab 3 ex1 demo            | 00:00:00   | 02:14:54   | 2020-03-18 | [2106, lab]
4  | lab 3 ex2                 | 00:00:00   | 03:13:14   | 2020-03-19 | [2106, lab]
5  | 2106 tutorial 8           | 00:00:00   | 01:23:00   | 2020-03-25 | [2106]
6  | lab 3 ex3                 | 07:51:12   | 00:00:00   | 2020-03-25 | [core, 2106, lab]
7  | study for 2106 midterm    | 06:17:03   | 00:00:00   | 2020-03-28 | [core, 2106, midterm]
8  | lab 4 ex2                 | 02:40:21   | 00:00:00   | 2020-03-30 | [core, 2106, lab]
9  | lab 4 demo                | 02:13:14   | 00:00:00   | 2020-04-01 | [core, 2106, lab]
10 | post-lecture quiz         | 00:00:00   | 00:15:48   | 2020-03-23 | [2113]
11 | 2113 tutorial questions   | 00:00:00   | 00:48:54   | 2020-03-23 | [2113]
12 | debug list feature        | 00:50:12   | 00:00:00   | 2020-03-25 | [core, 2113, tP]
13 | add delete feature        | 01:12:04   | 00:00:00   | 2020-03-26 | [core, 2113, tP]
-------------------------------------------------------------------------------------------
```

### Chaining Finding/Filtering: `-s`
Users can chain `find` and `filter` commands to generate an even more specific sub-list of activities 
based on his/her needs. 
This is can be particularly useful when the user wants to generate a `graph` with minimal clutter.


#### Format: 
* `filter -s TAGNAME1 TAGNAME2 TAGNAME3`
* `find -s KEYWORD1 / KEYWORD2 / KEYWORD3`

#### Example:
```
find study
-------------------------------------------------------------------------------------------
Here are the matching activities in your list:

   | Name                      | Duration   | Target     | Date       | Tags
1  | study for ges quiz        | 00:00:00   | 03:25:34   | 2020-03-28 | [ges, midterm]
2  | study for 2106 midterm    | 00:00:00   | 06:17:03   | 2020-03-28 | [2106, midterm]
3  | study for german test     | 00:00:00   | 03:00:00   | 2020-03-02 | [german, midterm]
4  | study vocab               | 00:00:00   | 04:24:12   | 2020-03-28 | [german, vocab]
5  | study for ges quiz        | 03:25:34   | 00:00:00   | 2020-03-28 | [ges, ge, midterm]
6  | study for 2106 midterm    | 06:17:03   | 00:00:00   | 2020-03-28 | [core, 2106, midterm]
-------------------------------------------------------------------------------------------
filter -s ges
-------------------------------------------------------------------------------------------
Here are the matching activities in your list:

   | Name                      | Duration   | Target     | Date       | Tags
1  | study for ges quiz        | 00:00:00   | 03:25:34   | 2020-03-28 | [ges, midterm]
2  | study for ges quiz        | 03:25:34   | 00:00:00   | 2020-03-28 | [ges, ge, midterm]
-------------------------------------------------------------------------------------------
```

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
