# User Guide - Diet Manager

## Content
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)
    1. [Profile Features](#31-profile-features)
        1. [Set user profile](#set-user-profile)
        2. [View user profile](#view-user-profile)
        3. [Set user name](#set-user-name)
        4. [Set user age](#set-user-age)
        5. [Set user gender](#set-user-gender)
        6. [Set user height](#set-user-height)
        7. [Set user weight-goal](#set-user-weight-goal)
        8. [Set user weight](#set-user-weight)
        9. [Delete user weight record](#delete-user-weight-record)
        10. [Check user weight progress](#check-user-weight-progress)
        11. [Check user BMI](#check-user-bmi)
    2. [Daily Food Record Features](#32-daily-food-record-features)
        1. [Record meals](#record-meals)
        2. [Check meals](#check-meals)
        3. [Clear food records](#clear-all-food-records)
        4. [Check required calories](#check-required-calories)
        5. [Calculate calories](#calculate-calories)
    3. [Food Nutrition Record Features](#33-food-nutrition-record-features)
        1. [List food database](#list-food-database)
        2. [Add food into database](#add-food-into-database)
        3. [Delete food from database](#delete-food-from-database)
    4. [Recipe Features](#34-recipe-features)
        1. [Get a recommended recipe](#get-a-recommended-recipe)
        2. [View recipe](#view-recipe)
    5. [Utility Features](#35-utility-features)
        1. [Help](#help)
        2. [Exit application](#exit-application)    
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)

## 1 Introduction

Diet Manager is an personal text-based chat-bot application used for managing an individual's diet.

Diet Manager is capable of the following functions:
* Profile -uses a user's profile information to personalise diet recommendations.
* Daily Food Record - record and store daily food intake.
* Food Nutrition Record - record and store food caloric information.
* Recipe Generator - generates a recommended recipe depending on a user's profile information.

## 2 Quick Start

* Ensure that you have Java 11 or above installed in your Computer. <br>
* Download the latest version of DietManager-2.1.0.jar [here](https://github.com/AY1920S2-CS2113-T15-4/tp/releases). <br>
* Open and run the jar file by entering the following command in Windows PowerShell or Git Bash:

        java -jar DietManager-2.1.0.jar

* The application will first search for a data directory to store all relevant data files. <br>
* If no `data` directory is found, it will create a new `data` directory.

        INFO: New Directory created: data

* Next there are 4 data files that the application will search for. If any of these files are present,
the application will read and load the data from these files. Otherwise, it will create new data files
to store the relevant information.
    * profile
    
            INFO: No existing Profile found, new file created: profile.txt
            
    * food-nutrition-record
            
            INFO: No existing Food Nutrition Record found, new file created: food-nutrition-record.txt
            
    * recipe
    
            INFO: No existing Recipe file found, new file created: recipe.txt
            
    * daily-food-record
    
            INFO: No existing food record file found, new file created: daily-food-record.txt

* The following text-based user interface should then appear:

        INFO: Starting Diet Manager
          _____   _        _     __  __
         |  __ \ (_)      | |   |  \/  |
         | |  | | _   ___ | |_  | \  / |  __ _  _ __    __ _   __ _   ___  _ __
         | |  | || | / _ \| __| | |\/| | / _` || '_ \  / _` | / _` | / _ \| '__|
         | |__| || ||  __/| |_  | |  | || (_| || | | || (_| || (_| ||  __/| |
         |_____/ |_| \___| \__| |_|  |_| \__,_||_| |_| \__,_| \__, | \___||_|
                                                               __/ |
                                                              |___/
        Welcome to Diet Manager! How may I assist you today?
         _____________________________________________________________________________________________________________
        |                          Functions:                        |                 Descriptions:                  |
        |____________________________________________________________|________________________________________________|
        |   set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL     |  set user's profile data                       |
        |   profile                                                  |  View user profile details                     |
        |   set-name USER_NAME                                       |  Set the user's name                           |
        |   set-age USER_AGE                                         |  Set the user's age                            |
        |   set-height USER_HEIGHT                                   |  Set the user's height                         |
        |   set-weight USER_WEIGHT                                   |  Set/Update weight in user profile             |
        |   check-weight-progress                                    |  List index of weight progress                 |
        |   delete-weight INDEX                                      |  Delete weight from the weight progress list   |
        |   set-weight-goal WEIGHT_GOAL                              |  Set the user's new weight goal                |
        |   check-bmi                                                |  Show user's BMI and BMI table                 |
        |   record-meal DATE TIME_PERIOD /FOOD_NAME -- CALORIE       |  Record meal info                              |
        |   check-meal DATE TIME_PERIOD                              |  Check meals eaten                             |
        |   calculate DATE                                           |  Calculate Calorie intake for the day          |
        |   calculate DATE1->DATE2                                   |  Calculate Calorie intake from DATE1 to DATE2  |
        |   list-food                                                |  Lists all foods info in database.             |
        |   addf FOOD_NAME --CALORIES                                |  Add new food info into database               |
        |   delf FOOD_NAME                                           |  Delete food info from database                |
        |   new-recipe MAXIMUM_FOOD_TYPES ACTIVITY_LEVEL             |  Randomly recommend recipe from database       |
        |   show-recipe                                              |  Show recommended recipe to user               |
        |   check-required-cal                                       |  Check amount of calories required/day         |
        |   clear-records                                            |  Clear the records in the database             |
        |   help                                                     |  Show this function help table                 |
        |   exit                                                     |  Exit the application                          |
        |____________________________________________________________|________________________________________________|
        Please key in your command:

* The application is now ready to be used by entering text-based commands into the command line

*Note that most command features are locked until a valid profile has been created. 
Only the `set-profile`, `help` and `exit` commands are usable upon first start-up*

      
        Please create a profile before using this command. Enter:
        set-profile {name} {age} {gender} {height} {weight} {weight goal}
   
   
## 3 Features 

Note that:
* Name is restricted to strings with no spaces. Spaces in between names can be replaced by a - instead. 
* Age is restricted to whole numbers only.
* Gender is restricted to "male" or "female". Input is case-insensitive.
* Height has units in centimetres.
* Weight has units in kilograms.
* Calories has units in calories.
* Date is restricted to days in a week - SUNDAY to SATURDAY. Input is case-insensitive.
* Time-Period is restricted to MORNING, AFTERNOON, NIGHT. Input is case-insensitive.
* Activity-Level is restricted to LOW, MODERATE, HIGH. Input is case-insensitive.

## 3.1 Profile Features

### Set user profile
Creates a new profile

Format: `set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL`

* If profile doesn't exist, the command will generate a new profile.
* If profile already exist, the command will overwrite the current profile and generate a new profile.

Example of usage:

`set-profile John 20 male 180 80 75`

* Expected Outcome:

    ```
    Your profile has been successfully updated.
    ```

### View user profile
View user profile details

Format: `profile`

* If profile information is present, displays it to the user.

Example of usage: 

`profile`

* Expected Outcome:

    ```
    Your profile information are as follows:
    Name:         John
    Age:          20 years old
    Gender:       male
    Height:       180.00 centimetres
    Weight        80.00 kilograms
    Weight Goal:  75.00 kilograms
    ```

### Set user name

Update name in profile.

Format: `set-name NAME`

Example of usage: 

`set-name Jane`

* Expected Outcome:

    ```
    set-name Jane
    Your username has been changed to Jane.
    ```

### Set user age

Update age in profile.

Format: `set-age AGE`

Example of usage: 

`set-age 18`

* Expected Outcome:

    ```
    set-age 18
    Your age has been changed to 18.
    ```

### Set user gender

Update gender in profile.

Format: `set-age GENDER`

Example of usage: 

`set-gender female`

* Expected Outcome:

    ```
    set-gender female
    Your gender has been changed to female.
    ```

### Set user height

Update height in profile.

Format: `set-height HEIGHT`

Example of usage: 

`set-height 170`

* Expected Outcome:

    ```
    set-height 170
    Your height has been changed to 170.00.
    ```

### Set user weight-goal

Update weight-goal in profile.

Format: `set-weight-goal WEIGHT-GOAL`

Example of usage: 

`set-weight-goal 65`

* Expected Outcome:

    ```
    set-weight-goal 65
    Your weight goal has been changed to 65.00.
    ```

### Set user weight

Update weight in profile when there are changes to user's weight.<br>
Application will keep track and store records of user's weight over time.

Format: `set-weight WEIGHT`

Example of usage: 

`set-weight 70`

* Expected Outcome:

    ```
    set-weight 70
    Your weight has been changed to 70.00.
    ```

### Delete user weight record

Delete a specific weight record.

Format: `delete-weight INDEX`

Example of usage: 

`delete-weight 1`

* Expected Outcome:

   ```
    delete-weight 1
    Weight Record: 80.0kg has been removed successfully!
   ```

### Check user weight progress

Check user weight record progression.

Format: `check-weight-progress`

Example of usage:

`check-weight-progress`

* If there is weight loss from beginning:

    Expected Output:
    
    ```
    check-weight-progress
    Here is your weight changes record:
    1. 70.0kg
    2. 60.0kg
    Overall, you have lost 10.00 kg!
    5.00 kg more to go to meet your dream girl/boy!
    ```
  
* If weight remains the same from beginning:

    Expected Output:
    
  ```
  check-weight-progress
  Here is your weight changes record:
  1. 70.0kg
  There has been no change in your weight!
  -5.00 kg more to go to meet your dream girl/boy!
  ```
  
* If there is weight gained from beginning:

    Expected Output:
    
    ```
    check-weight-progress
    Here is your weight changes record:
    1. 70.0kg
    2. 80.0kg
    Overall, you have gained 10.00 kg!
    -15.00 kg more to go to meet your dream girl/boy!
    ```  
  
### Check user BMI

Check user BMI and BMI classification.

Format: `check-bmi`

Example of usage: 

`check-bmi`

* Expected Outcome:

    ```
    check-bmi
    Your current BMI : 24.22

    You can check your height and weight against this table to see which category you fall into.
    Check weight first then height.

    LEGEND for BMI Table:
    1: UNDERWEIGHT       2: HEALTHY      3: OVERWEIGHT      4: OBESE      5: EXTREMELY OBESE
     ___________________________________________________________________________________________________________________________________
    |        |                                                      WEIGHT in KG                                                        |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        |         | 41 | 45 | 50 | 54 | 59 | 64 | 68 | 73 | 77 | 82 | 86 | 91 | 95 | 100 | 104 | 109 | 113 | 118 | 122 | 127 | 132 |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 142.2   | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  | 5  | 5  | 5  | 5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 144.7   | 2  | 2  | 2  | 3  | 3  | 4  | 4  | 4  | 4  | 4  | 5  | 5  | 5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 147.3   | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  | 5  | 5  | 5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 149.8   | 1  | 2  | 2  | 2  | 3  | 3  | 4  | 4  | 4  | 4  | 4  | 5  | 5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 152.4   | 1  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  | 4  | 5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 154.9   | 1  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  | 4  | 5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 157.4   | 1  | 1  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  | 4  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 160.0   | 1  | 1  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  | 4  |  4  |  5  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 162.5   | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  |  4  |  4  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 165.1   | 1  | 1  | 1  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  | 4  |  4  |  4  |  5  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 167.6   | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  |  4  |  4  |  4  |  5  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 170.1   | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  | 4  |  4  |  4  |  4  |  4  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 172.7   | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  |  4  |  4  |  4  |  4  |  5  |  5  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    | HEIGHT | 175.2   | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 4  | 4  |  4  |  4  |  4  |  4  |  4  |  5  |  5  |  5  |
    |   in   |--------------------------------------------------------------------------------------------------------------------------|
    |   CM   | 177.8   | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 4  |  4  |  4  |  4  |  4  |  4  |  4  |  5  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 180.3   | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  | 3  |  4  |  4  |  4  |  4  |  4  |  4  |  4  |  5  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 182.8   | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  | 3  | 3  | 3  |  4  |  4  |  4  |  4  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 185.4   | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 3  | 3  | 3  |  3  |  4  |  4  |  4  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 187.9   | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  | 3  | 3  |  3  |  4  |  4  |  4  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 190.5   | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  | 3  | 3  |  3  |  3  |  4  |  4  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 193.0   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  | 3  |  3  |  3  |  3  |  4  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 195.5   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  | 3  |  3  |  3  |  3  |  4  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 198.1   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  |  3  |  3  |  3  |  3  |  4  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 200.6   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  |  3  |  3  |  3  |  3  |  3  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 203.2   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  | 2  |  2  |  3  |  3  |  3  |  3  |  4  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 205.7   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  |  2  |  3  |  3  |  3  |  3  |  3  |  4  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 208.2   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  | 2  |  2  |  2  |  3  |  3  |  3  |  3  |  3  |  4  |
    |        |--------------------------------------------------------------------------------------------------------------------------|
    |        | 210.8   | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 1  | 2  | 2  | 2  |  2  |  2  |  3  |  3  |  3  |  3  |  3  |  4  |
    |________|__________________________________________________________________________________________________________________________|
    ```


## 3.2 Daily Food Record Features

### Record meals
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

* Expected Output:

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

### Check meals
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
### Clear all food records
Clear all the existing food records in a week.

Format: `clear-records`

Example of usage:

`clear-records`

* Expected Output:

    ```
    You have just cleared all food records in the week!
    ```

### Check required calories
Check the required calories for the current `profile` based on the activity level for the day.

Format: `check-required-cal DATE ACTIVITYLEVEL`

Attention:
```
`DATE` is restricted to the range of `Monday` to `Sunday`.
`ACTIVITYLEVEL` is restricted to `low`, `moderate` or `high`.
```

Example of usage:

`check-required-cal Monday low`

* Expected Output:

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

### Calculate calories
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


## 3.3 Food Nutrition Record Features

### List food database
Lists all foods and relevant calories info recorded in our database.

Format: `list-food`

Example of usage: `list-food`

*   Expected Output:
    ```
    These are the foods stored in our database:
    Food: Chicken, Calories: 1.00cal
    Food: Apple, Calories: 2.00cal
    Food: Carrots, Calories: 3.00cal
    Food: Rice, Calories: 4.00cal
    Food: Oil, Calories: 5.00cal
    Food: Tea, Calories: 6.00cal
    ```

### Add food into database
Adds a new food into database.

Format: `addf FOODNAME --CALORIES`

Example of usage: `addf beef noodles -- 7.0`

* Expected Output:
    ```
    You have added a new food into the database:
    Food: beef noodles, Calories: 7.0
    ```

*Attention: If calories info is incorrect, you expect to see:*
```
Sorry, to add new food to database you must input correct calories info.
It has to be positive Integer or Float
```

### Delete food from database
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

## 3.4 Recipe Features

### Get a recommended recipe
Get a recommend recipe based on user's physical conditions and activity level.

Format: `new-recipe MAXIMUM_FODD_TYPES ACTIVITY_LEVEL`

Explanation: 
```
MAXIMUM_FOOD_TYPES is the maximum number of food types the user want to have in a meal. 
ACTIVITY_LEVEL has three levels : low/moderate/high. Based on different activity levels the app recommends different recipes.
```

Attention:
```
1.  The maximum number of allowed food types in a meal is 3.
2.  The recipe is generated randomly, so the same input is expected to get different recipe.
3.  If user is unsatisfied with the current recipe, just run the command again and get a new one.
```

Example of usage:
* `MAXIMUM_NUM` is 2, `ACTIVITY_LEVEL` is low:

    Input: `new-recipe 2 low`
    
    * Expected Output: 
        ```
                  morning                                                                         afternoon                                                                       night
        MONDAY    fried-rice(508.00),white-bread(77.00)                                           fresh-milk(163.00),fishball-noodles-soup(330.00)                                fresh-milk(163.00),soft-drink(120.00)
        TUESDAY   white-bread(77.00),orange-juice(80.00)                                          prawn-noodles-dry(459.00),fresh-milk(163.00)                                    white-bread(77.00),prawn-noodles-dry(459.00)
        WEDNESDAY fried-rice(508.00),fresh-milk(163.00)                                           chicken-rice(702.00)                                                            fried-rice(508.00),soft-drink(120.00)
        THURSDAY  white-bread(77.00),fishball-noodles-soup(330.00)                                prawn-noodles-dry(459.00),soft-drink(120.00)                                    orange-juice(80.00),prawn-noodles-dry(459.00)
        FRIDAY    prawn-noodles-dry(459.00),cheeseburger(300.00)                                  soft-drink(120.00),chicken-curry(450.00)                                        soft-drink(120.00),white-bread(77.00)
        SATURDAY  french-fries(450.00),cheeseburger(300.00)                                       white-bread(77.00),orange-juice(80.00)                                          fried-rice(508.00),orange-juice(80.00)
        SUNDAY    chicken-rice(702.00)                                                            fresh-milk(163.00),chicken-curry(450.00)
        ```
* `MAXIMUM_NUM` is 5, `ACTIVITY_LEVEL` is high:

    Input: `new-recipe 5 high`
    
    * Expected Output:
        ```
        We support at most 3 kinds of food in a meal, otherwise it's easy to overtake calories and not good for your health!
     
                  morning                                                                         afternoon                                                                       night
        MONDAY    white-bread(77.00),fishball-noodles-soup(330.00),chicken-curry(450.00)          soft-drink(120.00),prawn-noodles-dry(459.00),fresh-milk(163.00)                 prawn-noodles-dry(459.00),white-bread(77.00),soft-drink(120.00)
        TUESDAY   fishball-noodles-soup(330.00),orange-juice(80.00),french-fries(450.00)          soft-drink(120.00),white-bread(77.00),fishball-noodles-soup(330.00)             prawn-noodles-dry(459.00),orange-juice(80.00),fishball-noodles-soup(330.00)
        WEDNESDAY fried-rice(508.00),orange-juice(80.00),fishball-noodles-soup(330.00)            soft-drink(120.00),fried-rice(508.00),cheeseburger(300.00)                      soft-drink(120.00),orange-juice(80.00),cheeseburger(300.00)
        THURSDAY  prawn-noodles-dry(459.00),fishball-noodles-soup(330.00),white-bread(77.00)      soft-drink(120.00),fishball-noodles-soup(330.00),white-bread(77.00)             fried-rice(508.00),cheeseburger(300.00),soft-drink(120.00)
        FRIDAY    fresh-milk(163.00),white-bread(77.00),chicken-rice(702.00)                      soft-drink(120.00),chicken-curry(450.00),fishball-noodles-soup(330.00)          cheeseburger(300.00),fresh-milk(163.00),orange-juice(80.00)
        SATURDAY  chicken-rice(702.00),white-bread(77.00),orange-juice(80.00)                     prawn-noodles-dry(459.00),soft-drink(120.00),cheeseburger(300.00)               orange-juice(80.00),soft-drink(120.00),white-bread(77.00)
        SUNDAY    cheeseburger(300.00),chicken-curry(450.00),orange-juice(80.00)                  prawn-noodles-dry(459.00),white-bread(77.00),cheeseburger(300.00)               chicken-rice(702.00),orange-juice(80.00),white-bread(77.00)
    
        ```

### View recipe
Show the recipe recommended for the user.

Format: `show-recipe`

Example of usage:`show-recipe`
* If no recipe exists:

    * Expected Output:
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

    * Expected Output:
        ```
                  morning                                                                         afternoon                                                                       night
        MONDAY    fried-rice(508.00),white-bread(77.00)                                           fresh-milk(163.00),fishball-noodles-soup(330.00)                                fresh-milk(163.00),soft-drink(120.00)
        TUESDAY   white-bread(77.00),orange-juice(80.00)                                          prawn-noodles-dry(459.00),fresh-milk(163.00)                                    white-bread(77.00),prawn-noodles-dry(459.00)
        WEDNESDAY fried-rice(508.00),fresh-milk(163.00)                                           chicken-rice(702.00)                                                            fried-rice(508.00),soft-drink(120.00)
        THURSDAY  white-bread(77.00),fishball-noodles-soup(330.00)                                prawn-noodles-dry(459.00),soft-drink(120.00)                                    orange-juice(80.00),prawn-noodles-dry(459.00)
        FRIDAY    prawn-noodles-dry(459.00),cheeseburger(300.00)                                  soft-drink(120.00),chicken-curry(450.00)                                        soft-drink(120.00),white-bread(77.00)
        SATURDAY  french-fries(450.00),cheeseburger(300.00)                                       white-bread(77.00),orange-juice(80.00)                                          fried-rice(508.00),orange-juice(80.00)
        SUNDAY    chicken-rice(702.00)                                                            fresh-milk(163.00),chicken-curry(450.00)
        ```

## 3.5 Utility Features

### Help
Show the help function table with supported commands.

Format: `help`

Example of usage: 

`help`

* Expected Outcome:

    ```
     _____________________________________________________________________________________________________________
    |                          Functions:                        |                 Descriptions:                  |
    |____________________________________________________________|________________________________________________|
    |   set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL     |  set user's profile data                       |
    |   profile                                                  |  View user profile details                     |
    |   set-name USER_NAME                                       |  Set the user's name                           |
    |   set-age USER_AGE                                         |  Set the user's age                            |
    |   set-height USER_HEIGHT                                   |  Set the user's height                         |
    |   set-weight USER_WEIGHT                                   |  Set/Update weight in user profile             |
    |   check-weight-progress                                    |  List index of weight progress                 |
    |   delete-weight INDEX                                      |  Delete weight from the weight progress list   |
    |   set-weight-goal WEIGHT_GOAL                              |  Set the user's new weight goal                |
    |   check-bmi                                                |  Show user's BMI and BMI table                 |
    |   record-meal DATE TIME_PERIOD /FOOD_NAME -- CALORIE       |  Record meal info                              |
    |   check-meal DATE TIME_PERIOD                              |  Check meals eaten                             |
    |   calculate DATE                                           |  Calculate Calorie intake for the day          |
    |   calculate DATE1->DATE2                                   |  Calculate Calorie intake from DATE1 to DATE2  |
    |   list-food                                                |  Lists all foods info in database.             |
    |   addf FOOD_NAME --CALORIES                                |  Add new food info into database               |
    |   delf FOOD_NAME                                           |  Delete food info from database                |
    |   new-recipe MAXIMUM_FOOD_TYPES ACTIVITY_LEVEL             |  Randomly recommend recipe from database       |
    |   show-recipe                                              |  Show recommended recipe to user               |
    |   check-required-cal                                       |  Check amount of calories required/day         |
    |   clear-records                                            |  Clear the records in the database             |
    |   help                                                     |  Show this function help table                 |
    |   exit                                                     |  Exit the application                          |
    |____________________________________________________________|________________________________________________|

    ```

### Exit application
Terminates and exits the application.

Format: `exit`

Example of usage: 

`exit`

* Expected Outcome:

    ```
    Thanks for using Diet Manager! See you again soon :)
    ```

## 4 FAQ

**Q**: What do I do if the application cannot be launched? 

**A**: Ensure that you have Java 11 and above installed on your device, 
and that you have the most updated version of the application.

**Q**: How do I transfer my data to another device? 

**A**: All recorded user-related data will be stored in a folder when running the application. 
Simply copying and moving that specific folder would be sufficient.

**Q**: What if I want to use this application for multiple users on the same device? 

**A**: The application is localised and user data is dependent on the specific data files present.
Simply switch the data files to that of another user, or have multiple folders present for multiple users.

**Q**: Can I directly change the data in the data files? 

**A**: You can, but it is strongly discouraged as it could lead to the data file being corrupt and the application
being forced to delete the data file and create a new one.

## 5 Command Summary

No. | Profile Commands | Description
----| ------- | -----------
1|`set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL`|Creates a new profile
2|`profile`| View user profile details
3|`set-name NAME`| Update name in profile.
4|`set-age AGE`| Update age in profile.
5|`set-age GENDER`| Update gender in profile.
6|`set-height HEIGHT`| Update height in profile.
7|`set-weight-goal WEIGHT-GOAL`| Update weight-goal in profile.
8|`set-weight WEIGHT`| Update weight in profile.
9|`delete-weight INDEX`| Delete a specific weight record.
10|`check-weight-progress`| Check user weight record progression.
11|`check-bmi`| Check user BMI and BMI classification.
12|`record-meal DATE TIMEPERIOD /FOOD1 {-- 10.0} /FOOD2 {-- 6.00} /...`| Record a meal
13|`check-meal DATE TIMEPERIOD`| Check a meal 
14|`clear-records`| Clear all food records
15|`check-required-cal DATE ACTIVITYLEVEL` | Check calories required based on user's activity level
16|`calculate {DATE1}->{DATE2}` | Calculates calories intake on a day or during a time period
17|`list-food` | List all foods recorded in the database 
18|`addf FOODNAME --CALORIES` | Add a new food into database
19|`delf FOODNAME` | Delete a food from the database
20|`new-recipe MAXIMUM_FODD_TYPES ACTIVITY_LEVEL` | Create a recommended recipe for user
21|`show-recipe` | Show the recipe recommended for user
22|`help`| Show the help function table with supported commands.
23|`exit`| Terminates and exits the application.

Click [here](README.md) to go back to the main page.