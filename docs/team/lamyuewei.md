# Lam Yue Wei - Project Portfolio Page

## Content
1. [Overview](#overview)
2. [Summary of Contributions](#summary-of-contributions)
    1. [Code Contributed](#Code-contributed)
    2. [Enhancements implemented](#Enhancements-implemented)
    3. [Contributions to documentation](#Contributions-to-documentation)
    4. [Contributions to the DG](#Contributions-to-the-DG)
    5. [Contributions to team based tasks](#Contributions-to-team-based-tasks)
    6. [Review/mentoring contributions](#Review/mentoring-contributions)
    7. [Contributions beyond the project team](#Contributions-beyond-the-project-team)

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

### Contributions to team based tasks
I have contributed to several of my team based tasks. 
* I have helped to brainstorm and note down several user stories for the team project.
* I have to implement several enhancement for my team project including but not limited to listing of food in database, recording food with the nutrition value, listing of food consumed with the nutrition value and check of required calories, etc.
* I have helped to resolved couple of bugs, especially bugs provided by other individuals during the mock PE.
* My entire team helped during the setting up of project repo by going through the steps together.
* I have helped by open issues in github for implementation of functions or features that are useful for my team project.
* I have helped to maintain the issue tracker by helping to keep track of the tasks that has been completed by myself or my teammates and helping to close those issues.
* I have helped to update the user guide.
* I have helped to update the developer guide.
* I have helped to review codes written my by group mates prior to approval.

### Review/mentoring contributions
* I have contributed to the teams especially in helping to review codes prior to approval. <br>
    * While the list is not exhaustive, some of the most recent reviews I have done can be found:
[**__here__**](https://github.com/AY1920S2-CS2113-T15-4/tp/pull/133), 
[**__here__**](https://github.com/AY1920S2-CS2113-T15-4/tp/pull/134), 
[**__here__**](https://github.com/AY1920S2-CS2113-T15-4/tp/pull/136), 
[**__here__**](https://github.com/AY1920S2-CS2113-T15-4/tp/pull/137), 
[**__here__**](https://github.com/AY1920S2-CS2113-T15-4/tp/pull/140) and 
[**__here__**](https://github.com/AY1920S2-CS2113-T15-4/tp/pull/141).

* In addition, I have also provided feedback to my teammates by providing them with insights as to how to go about implementing certain features when they request for help. These helps are essential since it help our team ensure that we are on track with the tasks on hand.<br>
    * For example, I have helped Yu Xiang recently when he asked me for an idea of how to go about creating a class file to monitor weight changes.

### Contributions beyond the project team
I have helped a number of other peers in cs2113 along the way in my cs2113 journey.

* At the start of the semester, I would often offer to coach Yu Xiang in java programming as he is new to java  whereas I had previously learn the basics in cs2040. I would spend time to explain to him the concept of Object Oriented Programming which might seem daunting at the start. I would also help explain to him the idea behind the codes required for him to complete his repl questions.

* Another help that I have offered was to a fellow peer in cs2113 by the name of Tze Ning. I remember she needed help as her Duke cannot be ran in the IntelliJ during the Individual Project. She had posted this issue on the forum but the technical support she received was to no avail. <br>
I then reached out to her and tried to fork her Duke using a second account to run it on my com to ascertain that her Duke code was not the reason for the issue. Next, I skyped her to teach her step by step how to close her project, reimport the project and run her Duke program in the right way. With that, her IntelliJ issue was resolved.
