# Developer Guide - Diet Manager

## Content
1. [Design](#design)
2. [Implementation](#implementation)
2. [Product Scope](#product-scope)
3. [User Stories](#user-stories)
4. [Non-Functional Requirements](#non-functional-requirements)
5. [Glossary](#glossary)
6. [Instructions for Manual Testing](#instructions-for-manual-testing)

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

![ArchitectureDiagram](images/Architecture.png)

The Architecture Diagram given above explains the high-level design of the Diet Manager Application.

The components involved are given below:

`UI`: The user interface of the application

`Storage`: Reads data from and writes data to the specified data files

`Parser`: Reads the given user input and breaks it down into machine-readable code

`Profile`: Stores and records the user's profile information

### Logic component

The `Logic` component is responsible for:
* Arranging the whole workflow
* Deciding how functional components interact with each other. 

The Logic consists of the following class:
* DietManager-Arranges the main workflow of the program.

### UI component

The `UI` component is responsible for:
* Receiving all command line inputs from the user
* Displaying all system outputs to the user

The UI consists of the following classes: 
* `UI` - Reads user input and displays system output
* `MessageBank` - Stores all standard system output messages

### Storage component

The `Storage` component is responsible for:
* Managing the respective data files
* Storing all system logs generated from the application

The Storage consists of the following classes: 
* `Storage` - Stores all user profile information in respective data files
* `AppLogger` - Stores all system logs generated from the application


### Parser component

The `Parser` component is responsible for:
* Reading user input and breaking it down to machine-readable code

The Parser consists of the following classes: 
* `Parser` - Breaks down inputs into machine-readable code

### Profile component

The `Profile` component is responsible for:
* Storing all user profile information

The Profile consists of the following classes: 
* `Profile` - 
* `DailyFoodRecord` - 

### Food component

The `Food` component is responsible for:
* 

The Food consists of the following classes: 
* `Food` - 
* `FoodNutritionInfo` - 


## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### 1. [Proposed] Record Meal Feature
#### 1.1 Proposed implementation
The record feature is facilitated by `RecordMealCommand`. It extends `Command` and overrides `execute()` and `saveResults()`

Given below is an example usage scenario and how the record mechanism behaves at each step.

Step 1.  The `ui` object gets user input and sends it to the `parser`. The `parser` then parses the original input into a standard `command`
and returns it. The command type is decided by the `commandPrompt`. The `RecordMealCommand` has `record-meal` as its commandPrompt. 
During the process a new`RecordMealCommand` object will be generated and returned to the `Logic` component.

### Step1. Generate command
![Step1. Diagram](images/Record_step1.png)

Step 2.  The `Logic` composition calls the `execute()` method of the `RecordMealCommand` object. 

During the process of execution, the command object will generate a `ArrayList<Food> foodList`, which maintains all `Food` items listed in the user input. 
If a certain kind of `Food` can be found in the database of `FoodNuritionInfo`, then this food item will be fetched from the database. 
Else a new food object will be generated simply with `foodName` without all the nutrition info.

During execution, the command object will try to get a `record` object of the class `DailyFoodRecord`. The date of record is specified by the user. 
If no record of that day is found in profile, it will automatically generate a new record of the day. Then with `foodList` and `record`, can call 
the method `record.recordMeals()`.

### Step2. Execute and Save Result
![Step2. Diagram](images/Record_step2.png)

#### 1.2 Design Considerations
#### Aspect: How `RecordMealCommand` executes and save results
* Alternative 1 (current choice): Directly operate on the `Profile` object, more specifically, the `DailyFoodRecord` attribute in `Profile` object.
And save execution `results` in the `RecordMealCommand` object.
    * Pros: Logic is clear and easy to implement.
    * Cons: `Command` object has full access to `Profile` object, which is not safe. 
* Alternative 2: `RecordMealCommand` can only operate on `DailyFoodRecord` of the `Profile`.
    * Pros: Reduce dependency and potential risks. 
    * Cons: Different types of `Command` need different declarations/interface for `command.execute()` method. 

#### Aspect : Data structure to support the command
* Alternative 1 (current choice): Use a list to store daily food record for a profile.
    * Pros: Easy to implement and understand
    * Cons: The list is maintained by a `Profile` object. Can lead to more duties for a `Profile` object.
* Alternative 2: Use a `FoodHistoryManager` to keep record of daily meals.
    * Pros: More OOP.
    * Cons: Makes the execution procedure more complex.
    
## Product Scope
### Target user profile

Students that :
* are too busy with work to carefully monitor their eating habits
* are concerned about their health.
* wished to keep track of their weight

### Value proposition

Diet Manager aims to achieve the following:
* Streamline the diet recording process 
* Allow users to track and monitor their eating habits
* Provide personalised information and recommendations for the user
* Monitor and track user's weight changes to achieve weight goal

## User Stories

|Version| As a ... | I want to ... | So that I ...|
|--------|----------|---------------|------------------|
|v1.0| student|record my calories intake|can keep track of my total calorie intake for the week effectively|
|v1.0| student|see my diet history|can track my diet and maintain a balanced and healthy diet lifestyle|
|v1.0| student|know my intake frequency of certain food types|can reduce the intake of that food|
|v1.0| student|classify foods into specific food groups|can have a good balance of multiple food types|
|v1.0| student|monitor my diet|can save money on unnecessary food while still having sufficient nutrition|
|v1.0| student|select certain food items and retrieve the nutritional value|can retrieve the nutritional values efficiently|
|v1.0| student|receive meal alerts during meal times|do not miss a meal or skip a mea|
|v1.0| student|receive dietary alerts|do not over or under eat and keep to my diet|
|v1.0| student|enter my information|can receive a tailored/recommended food plan for my body type/age/gender|
|v1.0| student|set the diet I am pursuing |have a framework to pursue|
|v1.0| student|export my diet history|have a record of my food intake|
|v1.0| student|import my diet history|have a record of my food intake|
|v1.0| student|generate a high-protein diet plan|can build up my muscles after workouts|
|v1.0| student|receive workout advice based on my excess calorie intake for the day|can maintain my calories for the day|
|v1.0| student|mark some food as ‘dislike’|i wont get the recommendation from the app anymore|
|v1.0| student|record my weight changes|can see if i am doing well towards my expectation|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
