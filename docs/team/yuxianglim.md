# Lim Yu Xiang(@yuxianglim) - Project Portfolio Page

## Content
1. [Overview](#overview)
2. [Code Contributed](#code-contributed)
3. [Enhancements implemented](#code-contributed)
4. [Contributions to documentation](#contributions-to-documentation)
5. [Contributions to the DG](#contributions-to-the-dg)
6. [Contributions to team based tasks](#contributions-to-team-based-tasks)
7. [Contributions to the User Guide](#contributions-to-the-user-guide)


## Overview
Diet Manager is an personalized chat-bot application used for managing an individual's diet.

Diet Manager is capable of tracking daily food intake and providing recommendations based on a user's specified
health data and weight goal.

## Code Contributed
**__Diet Manager__**:<br>
Link to code on tP Code Dashboard 
[**__here__**](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=yuxianglim&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos).

## Enhancements implemented
1. Created the CheckWeightProgress class which allows users to check their past weight records from the beginning till present.
    * The enhancement to this function is the ability to show the difference in weight of the user from the initial record to present and output message from the message bank in relative to the progress.
    
    * There might be further enhancement such as showing the % gain or loss in weight of the user.
    
2. Created/ updated set-weight and update-weight(removed) to allow user to store their weight in an Arraylist that is tied to their profile.

3. Added(Not yet done) Calculate BMI function of the user and comparing it to the BMI database to check if user is in healthy or unhealthy range.

4. Added delete weight functions to enable user to delete the stored weight based on the index of the weight stored.

5. Added help functions to enable user to check for help while using the program.
    * There was some slight hiccup in the process as gradle test allows for only 120 character per line. However i wished to implement a full table with sample inputs for user as it is more intuitive when using the program.
    * Also added the help table to show at the launch of the program as this will be more user-friendly to all users as they do not have to refer to the user guide that often.
    * Below is the original table which contains the sample input for user
    
    No. | Functions: | Descriptions: | Example: 
    ---| --- | --- | ---
    1  | `set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL  ` | set user's profile data |  (eg. set-profile John 20 male 180 80 75 )
    2  | `profile` | View user profile details | (eg. profile)
    3  | `record-meal DATE TIME_PERIOD /FOOD_NAME -- CALORIE` | Record meal info | (eg. record-meal Monday morning /prata -- 10.0 /milk -- 6.00 )
    4  | `check-meal DATE TIME_PERIOD` |Check meals eaten | (eg. check-meal Monday morning )
    5  | `calculate DATE` | Calculate Calorie intake for the day | (eg.calculate Monday)
    6  | `calculate DATE1->DATE2` | Calculate Calorie intake from DATE1 to DATE2 | (eg. calculate Monday->Wednesday )
    7  | `list-food` | List all foods recorded in the database  | (eg. list-food )
    8  | `addf FOOD_NAME --CALORIES` | Add a new food into database | (eg. addf beef noodles -- 7.0 )
    9  | `delf FOOD_NAME` | Delete a food from the database | (eg. delf beef noodles )
    10 | `new-recipe MAXIMUM_FOOD_TYPES ACTIVITY_LEVEL` | Randomly recommend recipe from database | (eg. new-recipe 2 low )
    11 | `show-recipe` | Show the recipe recommended for user | (eg. show-recipe ) 
    12 | `set-weight ` | Update user's weight changes | (eg. set-weight 70 )
    13 | `check-weight-progress NAME` | Check user's weight progress | (eg. check-weight-progress John )
    14 | `delete-weight` | Delete user's weight from progress | (eg. delete-weight 2 ) 
    15 | `help` | Show the help function table |  (eg. help )
    16 | `exit` | Exit application | (eg. exit )


## Contributions to documentation
I contributed to the User Guide under the section of check weight progress. 
* Provided an introduction to the features of the method
* Provided the format of using the command
* Provided the 3 different sample output that will be shown to user based on weight difference

I contributed to the User Guide under the section of set-weight. 
* Provided an introduction to the features of the method
* Provided the format of using the command
* Provided the sample output that will be shown to user upon set-weight command

I contributed to the User Guide under the section of delete-weight. 
* Provided an introduction to the features of the method
* Provided the format of using the command
* Provided the different sample output that will be shown to user when user deletes the weight stored based on index

I contributed to the User Guide under the section of help.
* Provided an introduction to the features of the method
* Provided the format of using the command
* Provided the sample output which contains the help table that will be shown to user when the help command is entered

I also contributed to the User Guide under the section of command summary
* Updated the table of functions available currently available

## Contributions to the DG
I contributed to the Developer Guide under the section of user-stories component
* Provided the table to the user stories

## Contributions to team based tasks
I have contributed to my team based tasks in:
* initial brainstorming for type of program and user stories 
* implementing enhancements for my team project such as
    * Weight editing
    * Deletion of weight
    * Weight Tracking
    * Help functions
    * Streamlined some of the print line process
    * BMI calculations
    * BMI table
* Reviewing codes written my by group mates prior to approval.
* Resolving some bugs regarding weight functions.
* My entire team helped during the setting up of project repo by going through the steps together.
* Opening some issues in github for implementation of functions or enhancements.
* Updating the user guide
* Update some minor parts of developer guide and proofread it

## Contributions to the User Guide
* Added functions guides such as
    * set-weight
    * delete-weight
    * check-weight-progress
    * help
    * check-bmi
* Updated list of summary of commands
* Updated content table