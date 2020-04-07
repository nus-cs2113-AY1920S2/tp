# User Guide

## Content
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)
    1. [Set user profile](#31-set-user-profile)
    2. [View user profile](#32-view-user-profile)
    3. [Record meals](#33-record-meals)
    4. [Check meals](#34-check-meals)
    5. [Check required calories](#35-check-required-calories)
    6. [Calculate calories](#36-calculate-calories)
    7. [List food database](#37-list-food-database)
    8. [Add food into database](#38-add-food-into-database)
    9. [Delete food from database](#39-delete-food-from-database)
    10. [Get a recommended recipe](#310-get-a-recommended-recipe)
    11. [View recipe](#311-view-recipe)
    11. [Check Weight Progress](#312-check-weight-progress)
    12. [Update Weight](#313-update-weight-progress)
    13. [Delete weight](#314-delete-weight-progress)
    14. [Help](#315-help)
    15. [Exit application](#316-exit-application)    
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)

## 1 Introduction

Diet Manager is an personal text-based chat-bot application used for managing an individual's diet.

Diet Manager is capable of tracking daily food intake and providing recommendations depending on a user's specified
health data.

## 2 Quick Start

* Ensure that you have Java 11 or above installed in your Computer. <br>
* Download the latest version of DietManager-2.0.0.jar [here](https://github.com/AY1920S2-CS2113-T15-4/tp/releases). <br>
* Open and run the jar file by entering the following command in Windows PowerShell or Git Bash:
      
        java -jar DietManager-2.0.0.jar
* 

## 3 Features 

### 3.1 Set user profile
Creates a new profile

Format: `set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL`

* If profile doesn't exist, the command will generate a new `Profile` object.
* If profile already exist, the command will automatically update profile info.

Attention:
* Height has units in centimetres.
* Weight has units in kilograms.

Example of usage:

`set-profile John 20 male 180 80 75`

Expected Outcome:

```
Your profile has been successfully updated.
```
* If enter `profile` again, you will expect to see:

```
Welcome to Diet Manager! How may I assist you today?
Age:          20 years old
Gender:       Male
Height:       180.00 centimetres
Weight        80.00 kilograms
Weight Goal:  75.00 kilograms
```

### 3.2 View user profile
Views user profile details

Format: `profile`

* If profile information is present, displays it to the user.
* If profile information is absent, prompts user to enter profile information.

Example of usage: 

`profile`

Expected Outcome:
* If `profile` doesn't exist

```
No existing profile found. To create a new profile, enter:
set-profile {name} {age} {gender} {height} {weight} {weight goal}
```

* If `profile` already exists

```
Welcome to Diet Manager! How may I assist you today?
Age:          18 years old
Gender:       Male
Height:       180.00 centimetres
Weight        65.00 kilograms
Weight Goal:  75.00 kilograms
```

### 3.3 Record meals
Records meals for the user(so they can refer record history and calculate calories intake later).

Format: `record-meal DATE TIMEPERIOD /FOOD1 {-- 10.0} /FOOD2 {-- 6.00} /...`

Attention:
* Parameters in `{}` is optional, it denotes the calories content of the `Food`. Also notice that you shouldn't use `{}`
in the actual input!
    * If a `Food` item can be found in our database, then no calories info needs to be 
provided. 
    * If no calories info is provided for a `Food` item not in our database, the `record`
    operation can still go on, but that `Food` item doesn't have calories info.
* You can record as many `Food` items as you want for a meal.
* `DATE` is restricted to the range of `Monday` to `Sunday` and `TIMEPERIOD` is restricted to
`morning/afternoon/night`.
* If a `Food` item is provided with incorrect calories info(like `/egg -- wow`), then this `Food` item
won't be recorded.

Example of usage: 

`record-meal Saturday morning /egg prata -- 5 /noodles -- xxx`

Expected Output:

```
You just record the meal in the morning of: SATURDAY.
Some food/foods are not added due to invalid format.
```

* If enter `check-meal Saturday morning` right now, you expect to see:
```
SATURDAY Morning: 
Food: egg prata, Calories: 5.00cal
For morning, total calculable calories intake: 5.00cal.
```

### 3.4 Check meals
Check what the `profile` had eaten for a meal.

Format: `check-meal DATE TIMEPERIOD`

Attention:
* `DATE` is restricted to the range of `Monday` to `Sunday` and `TIMEPERIOD` is restricted to
  `morning/afternoon/night`.
  
Example of usage:

`check-meal Monday morning`

Expected Output:

* If the record doesn't exist:
    ```
    MONDAY Morning: 
    Oops, you have no record for this meal.
    For morning, there are no calculable calories data.
    ```
* If the record exists:
    ```
    MONDAY Morning: 
    Food: egg prata, Calories: 5.00cal
    Food: noodles, Calories: 2.00cal
    For morning, total calculable calories intake: 7.00cal.
    ```

### 3.5 Check required calories
Check the required calories for the current `profile` based on the activity level for the day.

Format: `check-required-cal DATE ACTIVITYLEVEL`

Attention:
```
`DATE` is restricted to the range of `Monday` to `Sunday`.
`ACTIVITYLEVEL` is restricted to `low`, `moderate` or `high`.
```

Example of usage:

`check-required-cal Monday low`

Expected Output:

* If the profile is trying to gain weight but is having insufficient calories for the day:
    ```
    Calories Intake and Requirement for SATURDAY:
    Total calculable calories intake for the entire day: 5.00cal.
    Calories requirement for high activity level: 2848.41cal.
    Ohh no!!! You have consumed too little calories.
    ```
* If the profile is trying to lose weight but is having excess calories for the day:
    ```
    Calories Intake and Requirement for WEDNESDAY:
    Total calculable calories intake for the entire day: 2805.00cal.
    Calories requirement for moderate activity level: 2559.44cal.
    Ohh no!!! You have consumed too much calories
    ```
* If the profile is working towards the weight goal and is having sufficient calories for the day:
    ```
    Calories Intake and Requirement for MONDAY:
    Total calculable calories intake for the entire day: 2860.00cal.
    Calories requirement for low activity level: 2270.47cal.
    Well done!!! You have consumed sufficient calories.
    ```

### 3.6 Calculate calories
Calculates calories intake on a day or during a time period.

Format: 
* Option 1: `calculate {DATE}`
* Option 2: `calculate {DATE1}->{DATE2}`

Attention:
```
`DATE` is restricted to the range of `Monday` to `Sunday`.
```

Example of usage:
* First let's record some meals
  ```
  record-meal Monday morning /egg -- 2
  You just record the meal in the morning of: MONDAY.
  record-meal Monday night /Apple
  You just record the meal in the night of: MONDAY.
  record-meal Wednesday morning /prata -- 5 /beef -- 10
  You just record the meal in the morning of: WEDNESDAY.
  ```
* Then we calculate calories intake from Monday to Wednesday

  ```
  calculate Monday->Wednesday
  Your Calories intake during the given period is 19.00.
  ```
  * Notice `Apple` is in our database with calories info: 2.00

### 3.7 List food database
Lists all foods and relevant calories info recorded in our database.

Format: `list-food`

Example of usage: `list-food`

Expected Output:
```
These are the foods stored in our database:
Food: Chicken, Calories: 1.00cal
Food: Apple, Calories: 2.00cal
Food: Carrots, Calories: 3.00cal
Food: Rice, Calories: 4.00cal
Food: Oil, Calories: 5.00cal
Food: Tea, Calories: 6.00cal
```

### 3.8 Add food into database
Adds a new food into database.

Format: `addf FOODNAME --CALORIES`

Example of usage: `addf beef noodles -- 7.0`

Expected Output:
```
You have added a new food into the database:
Food: beef noodles, Calories: 7.0
```

Attention: If calories info is incorrect, you expect to see:
```
Sorry, to add new food to database you must input correct calories info.
It has to be positive Integer or Float
```

### 3.9 Delete food from database
Deletes a food from the database

Format: `delf FOODNAME`

Example of usage:
* If `beef noodles` already in database:

    Expected Output:
    ```
    You have just deleted beef noodles from the database.
    ```
* If `beef noodles` not in database:

    Expected Output:
    ```
    No need to delete! Referred Food doesn't exist in database
    ```

### 3.10 Get a recommended recipe
Get a recommend recipe based on user's physical conditions and activity level.

Format: `new-recipe MAXIMUM_FODD_TYPES ACTIVITY_LEVEL`

Explanation: 
```
MAXIMUM_FOOD_TYPES is the maximum number of food types the user want to have in a meal. 
ACTIVITY_LEVEL has three levels : low/moderate/high. Based on different activity levels the app recommends different recipes.
```

Attention:
```
1.  The maximum number of allowed food types in a meal is 4.
2.  The recipe is generated randomly, so the same input is expected to get different recipe.
3.  If user is unsatisfied with the current recipe, just run the command again and get a new one.
```

Example of usage:
* `MAXIMUM_NUM` is 2, `ACTIVITY_LEVEL` is low:

    Input: `new-recipe 2 low`
    
    Expected Output: 
    ```
               morning                                                     afternoon                                                   night
     MONDAY    Carrots(3.00),Tea(6.00)                                     Tea(6.00),Chicken(1.00)                                     Chicken(1.00),Carrots(3.00)
     TUESDAY   Oil(5.00),Rice(4.00)                                        Chicken(1.00),Oil(5.00)                                     Apple(2.00),Rice(4.00)
     WEDNESDAY Rice(4.00),Tea(6.00)                                        Oil(5.00),Carrots(3.00)                                     Oil(5.00),Chicken(1.00)
     THURSDAY  Carrots(3.00),Rice(4.00)                                    Chicken(1.00),Rice(4.00)                                    Oil(5.00),Apple(2.00)
     FRIDAY    Chicken(1.00),Rice(4.00)                                    Carrots(3.00),Oil(5.00)                                     Tea(6.00),Oil(5.00)
     SATURDAY  Carrots(3.00),Chicken(1.00)                                 Oil(5.00),Apple(2.00)                                       Apple(2.00),Tea(6.00)
     SUNDAY    Apple(2.00),Oil(5.00)                                       Rice(4.00),Oil(5.00)                                        Rice(4.00),Carrots(3.00)
    ```
* `MAXIMUM_NUM` is 5, `ACTIVITY_LEVEL` is high:

    Input: `new-recipe 5 high`
    
    Expected Output:
    ```
    We support at most 4 kinds of food in a meal, otherwise it's not good for your health!
    
              morning                                                     afternoon                                                   night
    MONDAY    Tea(6.00),Chicken(1.00),Rice(4.00),Oil(5.00)                Tea(6.00),Oil(5.00),Apple(2.00),Carrots(3.00)               Apple(2.00),Oil(5.00),Rice(4.00),Chicken(1.00)
    TUESDAY   Apple(2.00),Chicken(1.00),Rice(4.00),Carrots(3.00)          Carrots(3.00),Rice(4.00),Oil(5.00),Tea(6.00)                Oil(5.00),Apple(2.00),Chicken(1.00),Tea(6.00)
    WEDNESDAY Carrots(3.00),Tea(6.00),Apple(2.00),Rice(4.00)              Tea(6.00),Carrots(3.00),Oil(5.00),Chicken(1.00)             Tea(6.00),Apple(2.00),Rice(4.00),Oil(5.00)
    THURSDAY  Oil(5.00),Carrots(3.00),Apple(2.00),Tea(6.00)               Carrots(3.00),Rice(4.00),Chicken(1.00),Apple(2.00)          Oil(5.00),Chicken(1.00),Tea(6.00),Rice(4.00)
    FRIDAY    Carrots(3.00),Oil(5.00),Apple(2.00),Chicken(1.00)           Apple(2.00),Oil(5.00),Carrots(3.00),Rice(4.00)              Tea(6.00),Apple(2.00),Rice(4.00),Chicken(1.00)
    SATURDAY  Apple(2.00),Carrots(3.00),Chicken(1.00),Tea(6.00)           Apple(2.00),Rice(4.00),Carrots(3.00),Tea(6.00)              Rice(4.00),Apple(2.00),Carrots(3.00),Chicken(1.00)
    SUNDAY    Chicken(1.00),Rice(4.00),Apple(2.00),Oil(5.00)              Oil(5.00),Rice(4.00),Apple(2.00),Chicken(1.00)              Rice(4.00),Tea(6.00),Chicken(1.00),Carrots(3.00)

    ```

### 3.11 View recipe
Show the recipe recommended for the user.

Format: `show-recipe`

Example of usage:`show-recipe`
* If no recipe exists:

    Expected Output:
    ```
              morning                                                     afternoon                                                   night
    MONDAY                                                                                                                           
    TUESDAY                                                                                                                          
    WEDNESDAY                                                                                                                        
    THURSDAY                                                                                                                         
    FRIDAY                                                                                                                           
    SATURDAY                                                                                                                         
    SUNDAY                                                                                                                           

    ```
* If recipe exists:

    Expected Output:
    ```
              morning                                                     afternoon                                                   night
    MONDAY    Carrots(3.00),Apple(2.00)                                   Apple(2.00),Carrots(3.00)                                   Chicken(1.00),Rice(4.00)
    TUESDAY   Rice(4.00),Chicken(1.00)                                    Chicken(1.00),Rice(4.00)                                    Chicken(1.00),Apple(2.00)
    WEDNESDAY Chicken(1.00),Rice(4.00)                                    Rice(4.00),Chicken(1.00)                                    Chicken(1.00),Rice(4.00)
    THURSDAY  Carrots(3.00),Chicken(1.00)                                 Chicken(1.00),Carrots(3.00)                                 Rice(4.00),Chicken(1.00)
    FRIDAY    Carrots(3.00),Chicken(1.00)                                 Chicken(1.00),Rice(4.00)                                    Chicken(1.00),Rice(4.00)
    SATURDAY  Chicken(1.00),Carrots(3.00)                                 Apple(2.00),Chicken(1.00)                                   Carrots(3.00),Apple(2.00)
    SUNDAY    Chicken(1.00),Apple(2.00)                                   Rice(4.00),Chicken(1.00)                                    Rice(4.00),Chicken(1.00)
    ```

### 3.12 Check Weight Progress
Check current weight progress from the beginning.

Format: `check-weight-progress JOHN`

Example of usage:`check-weight-progress JOHN`
* If there is weight loss from beginning:

    Expected Output:
    ```
         check-weight-progress JOHN
         Here is your weight changes record:
         1. 80.0kg 
         2. 70.0kg 
         3. 50.0kg 
         Great job! You have lost 30.00 kg since the beginning!                                                                                     
    ```
* If weight remains the same from beginning:

    Expected Output:
    ```
         Here is your weight changes record:
         1. 80.0kg 
         No Pain No Gain! You have not lost weight yet! Strive on!
                                                                                  
    ```
* If there is weight gained from beginning:

    Expected Output:
    ```
         check-weight-progress JOHN
         Here is your weight changes record:
         1. 80.0kg 
         2. 90.0kg 
         Maintain your diet! You have gained 10.00 kg since the beginning!
                                                                                  
    ```  

### 3.13 Update Weight Progress

Update weight in profile when there is changes to user's weight after following diet plan.

This will allow the program to keep track of user's weight along the entire period.

Format: `update-weight 70`

* Expected Output:

    ```
        update-weight 70
        Your weight has been changed to 70.00.
                                                                                      
    ```

### 3.14 Delete Weight Progress

Allows user to delete wrong weight input or remove any previous input weights in the profile.

Format: `delete-weight 2`

1. First, use the command `check-weight-progress NAME` to list out the stored weights in the user's profile.

2. Then delete the weight base on the index linked to the weight stored using `delete-weight INDEX` command.


* Example of the process

    Expected Output:

    ```
        check-weight-progress JOHN
        Here is your weight changes record:
        1. 80.0kg 
        2. 70.0kg 
        Great job! You have lost 10.00 kg since the beginning!
        delete-weight 2
        Weight Record: 70.0kg  has been removed successfully!

                                                                                      
    ```

### 3.15 Help
Show the help function table with the list of commands available

Format: `help`

* Shows the help table.

    Example of usage: 

    `help`

    Expected Outcome:

    ```
     _____________________________________________________________________________________________________________
    |                          Functions:                        |                 Descriptions:                  |
    |____________________________________________________________|________________________________________________|
    |   set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL     |  set user's profile data                       |
    |   profile                                                  |  View user profile details                     |
    |   record-meal DATE TIME_PERIOD /FOOD_NAME -- CALORIE       |  Record meal info                              |
    |   check-meal DATE TIME_PERIOD                              |  Check meals eaten                             |
    |   calculate DATE                                           |  Calculate Calorie intake for the day          |
    |   calculate DATE1->DATE2                                   |  Calculate Calorie intake from DATE1 to DATE2  |
    |   list-food                                                |  Lists all foods info in database.             |
    |   addf FOOD_NAME --CALORIES                                |  Add new food info into database               |
    |   delf FOOD_NAME                                           |  Delete food info from database                |
    |   update-weight                                            |  Update weight in user profile                 |
    |   check-weight-progress NAME                               |  List index of weight progress                 |
    |   delete-weight INDEX                                      |  Delete weight from the weight progress list   |
    |   new-recipe MAXIMUM_FOOD_TYPES ACTIVITY_LEVEL             |  Randomly recommend recipe from database       |
    |   show-recipe                                              |  Show recommended recipe to user               |
    |   help                                                     |  Show this function help table                 |
    |   exit                                                     |  Exit the application                          |
    |____________________________________________________________|________________________________________________|
    
    ```


### 3.16 Exit application
Terminates and exits the application

Format: `exit`

* Terminates the application.

    Example of usage: 

    `exit`

    Expected Outcome:

    ```
  
    Thanks for using Diet Manager! See you again soon :)
    
    ```

## 4 FAQ

**Q**: How do I transfer my dietary and health data to another computer? 

**A**: All recorded user-related data will be stored in a folder when running the application. 
Simply copying and moving that specific folder would be sufficient.

## 5 Command Summary

No. | Command | Description
----| ------- | -----------
1  | `set-profile` | Set up user profile
2  | `profile` | View user profile
3  | `record-meal` | Record a meal
4  | `check-meal` | Check a meal 
5  | `check-required-cal` | Check calories required for an activity level
6  | `calculate` | Calculates calories intake on a day or during a time period
7  | `list-food` | List all foods recorded in the database 
8  | `addf` | Add a new food into database
9  | `delf` | Delete a food from the database
10 | `new-recipe` | Create a recommended recipe for user
11 | `show-recipe` | Show the recipe recommended for user
12 | `update-weight` | Update user's weight changes
13 | `check-weight-progress` | Check user's weight progress
14 | `delete-weight` | Delete user's weight from progress
15 | `help` | Show the help function table
16 | `exit` | Exit application
