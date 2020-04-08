# Lam Yue Wei - Project Portfolio Page

## Content
1. [Overview](#overview)
2. [Summary of Contributions](#summary-of-contributions)
3. [Contributions to the User Guide](#contributions-to-the-user-guide)
4. [Contributions to the Developer Guide](#contributions-to-the-developer-guide)


## Overview
Diet Manager is an personalized chat-bot application used for managing an individual's diet.

Diet Manager is capable of tracking daily food intake and providing recommendations based on a user's specified
health data and weight goal.

## Summary of Contributions

### Code Contributed
**__Diet Manager__**:<br>
Link to code on tP Code Dashboard 
[**__here__**](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=lamyuewei&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos).

### Enhancements implemented
1. Created the FoodNutritionInfo class which serves as a food bank allowing user to record food into their daily meal record from the food that is stored in the food bank.

2. Enabled users to view all the food stored in the food bank via user input command.

3. Created the Food class which allowed users to create food to store into their daily meal record with or without the Nutrition value.
    * This enhancement is tougher to implement as I needed to break down the user input into several cases in order to record the meal.<br> 
        * If the user keyed in food name and nutrition value, the meal will store the food according to the user input.<br>
        * Else if the user keyed in food name without nutrition value and the food exists in the food bank, the meal will store the food taken from the food bank.<br>
        * Else the meal will store the food name without any nutrition value.

    * There were some difficulties faced during the implementation of the enhancement as I needed to deal with cases where the Nutrition value of a food may or may not exist. <br>
    To overcome this challenge, I had to use the Optional class and Streams Methods provided by the java api. I also had to be mindful constantly to ensure I lay out proper checks of the Optional values before obtaining the values in order to prevent any crashes in our program.

4. Incorporate the nutrition values into the DailyFoodRecord class to enabled user to view their daily meals with the associated food name and nutrition value if it exists.

5. Enabled users to check the calories requirement based on their activity level and their food consumption for the day.
    * The challenge in this enhancement implementation comes with the need to carefully break down the scenario constantly.
        * Firstly, there is a need to calculate the person's BMI using different formula depending on whether the profile belongs to a male or female. <br>
        * Secondly, there is a need to calculate the person's required calories using different formula depending on the profile's activity level for the day (i.e. low, moderate, high). <br>
        * Thirdly, there is a need to compare the required calories to the calories intake of the person, and the result shown to the user then depends on whether the user is trying to gain weight or lose weight.

### Contributions to documentation
I contributed to the User Guide under the section of check required calories. 
* Provided an introduction to the features of the method
* Provided the format of using the command
* Provided some key information that the user has to take note of when using the command
* Provided examples of usages of the command
* Provided some 3 sample outputs that the user will likely see as the result individuals might see differs since some profile might want to gain weight while others might want to lose weight

I also contributed to the User Guide under the section of set user profile.
* Added some key information of the units used for the user to take note of when using the command

### Contributions to the DG
I contributed to the Developer Guide under the section of profile component and food component.
* Stated the responsibility of the 2 component
* Provided a description for the classes that the 2 component consists of

### Contributions to team-based tasks
To be completed...

### Review/mentoring contributions
To be completed...

### Contributions beyond the project team
To be completed...

## Contributions to the User Guide
To be completed...

## Contributions to the Developer Guide
To be completed...
