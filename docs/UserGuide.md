# User Guide

## Content
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
    1. [View user profile](#view-user-profile)
    2. [Exit application](#exit-application)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

## Introduction

Diet Manager is an personal text-based chat-bot application used for managing an individual's diet.

Diet Manager is capable of tracking daily food intake and providing recommendations depending on a user's specified
health data.

## Quick Start

* Ensure that you have Java 11 or above installed in your Computer. <br>
* Download the latest version of DietManager-2.0.0.jar [here](https://github.com/AY1920S2-CS2113-T15-4/tp/releases). <br>
* Open and run the jar file by entering the following command in Windows PowerShell or Git Bash:
      
        java -jar DietManager-2.0.0.jar
* 

## Features 

### View user profile
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

### Exit application
Terminates and exits the application

Format: `exit`

* Terminates the application.

Example of usage: 

`exit`

Expected Outcome:

```
Thanks for using Diet Manager! See you again soon :)
```

### Set user profile
Creates a new profile

Format: `set-profile NAME AGE GENDER HEIGHT WEIGHT WEIGHTGOAL`

* If profile doesn't exist, the command will generate a new `Profile` object.
* If profile already exist, the command will automatically update profile info.

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

### Record meals
Records meals for the user(so they can refer record history and calculate calories intake later).

Format: `record-meal DATE TIMEPERIOD /FOOD1 {-- 10.0} /FOOD2 {-- 6.00} /...`

Attention:
* Parameters in `{}` is optional, it denotes the calories content of the `Food`.
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



## FAQ

**Q**: How do I transfer my dietary and health data to another computer? 

**A**: All recorded user-related data will be stored in a folder when running the application. 
Simply copying and moving that specific folder would be sufficient.

## Command Summary

No. | Command | Description
----| ------- | -----------
1  | `profile` | View user profile
2  | `` | 
3  | `` | 
4  | `` | 
5  | `` | 
6  | `` | 
7  | `` | 
8  | `` | 
9  | `help` | Display help menu
10 | `exit` | Exit application
