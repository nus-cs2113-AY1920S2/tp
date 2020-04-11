# Yan Chenghao (DDzuikeai) - Project Portfolio Page

---

## Content
1. [Overview](#overview)
2. [Summary of Contributions](#summary-of-contributions)
3. [Contributions to the User Guide](#contributions-to-the-user-guide)
4. [Contributions to the Developer Guide](#contributions-to-the-developer-guide)
5. [Contributions to team-based tasks ](#contributions-to-team-based-tasks)
6. [Contributions-beyond-the-project-team](#contributions-beyond-the-project-team)

---

## Overview
Diet Manager is an personal text-based chat-bot application used for managing an individual's diet.

Diet Manager is capable of tracking daily food intake and providing recommendations depending on a user's specified
health data.

---

## Summary of Contributions

### Code Contributed

**__Diet Manager__**:<br>
This link is my [code contribution](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=ddzuikeai).

### Enhancements implemented
I implemented most commands and features related to food record, recipe and maintain database.

#### Implemented the `record-meal` and `check-meal` feature
* What it does: It allows user to record and check their meals.
* Justification: These two features improve the utility of the app since now user can use it to track
their daily meals.
* Highlights: The `record-meal` feature allows user to record as many food types as they want in a meal, and give
them the freedom to provide calories info together with foods or not. If the food can be found in database, then the 
app can automatically calculate calories intake for the user. If not in database, the user can provide calories info
so the app still can trace calories intake.

#### Implemented the `addf` and `delf` feature
* What it does: It allows user to add foods and delete foods from our database.
* Justification: These features provide the user the way to customize their personal product. Because
they can change the database easily based on personal tastes and eating habits. It improve the utility
and convenience of the app.
* Highlights: These two features work also influence the `recommend` feature, because the app will customize
the recipe based on foods in database.

#### Implemented the `recommend` feature
* What it does: It allows user to get recommended recipe based on their physical conditions and personal needs.
* Justification: This feature eliminates user's trouble to plan for each meal. They can easily get combinations
of food that can satisfy their calories intake needs.
* Highlights: The command is flexible, can adjust the recipe from two aspects. One is maximum food types in each
meal, the other is one's activity level(low/moderate/high) which indicates different levels of calories needs.

#### Other enhancements
* Implemented the `clear-records` feature to enable user clear all their records.
* Implemented the `calculate` feature which enables user to calculate their calories intake on a day or during a time period.
* Add DailyFoodRecord class and related codes to support all kinds of operations related to food record.
* Contributed to Storage, Exceptions, Parser and UI.
    * For example, for Storage, I implemented the storage of recipe and all food records.

### Contributions to the User Guide

* Which sections did you contribute to the UG?
Documented the details of following features:
    * Set user profile
    * View user profile
    * Record meals
    * Check meals
    * Clear food records
    * Check required calories
    * Calculate calories
    * List food database
    * Add food into database
    * Delete food from database
    * Get a recommended recipe
    * View recipe
    
This is the link to [User Guide](https://ay1920s2-cs2113-t15-4.github.io/tp/UserGuide.html)

### Contributions to the Developer Guide: 

* Which sections did you contribute to the DG? 
    * Helped in building the framework.
    * Helped in the design of Logic component and Model component.
    * Documented the implementation of Record Meal Feature.
    
* Which UML diagrams did you add/updated?
    * The sequence diagrams that explain the workflow of record meal feature.

### Contributions to team-based tasks 

I have Helped to design the architecture and main workflow of our product.

I have helped to review teammates' PR and provide suggestions.

I have fixed most bugs existing in PE Dry Run and some other bugs of new features.

I have helped to manage issue tracking and assignments.

### Contributions beyond the project team

Reported several crucial bugs for other team in the PE Dry Run: [bugs reported](https://github.com/DDzuikeai/ped/issues)

Unofficially helped to test other team's product, for example T15-3's module manager.
