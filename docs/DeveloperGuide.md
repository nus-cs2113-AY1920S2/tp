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

![ArchitectureDiagram](/docs/images/Architecture.png)

The Architecture Diagram given above explains the high-level design of the Diet Manager Application.

The components involved are given below:

`UI`: The user interface of the application

`Storage`: Reads data from and writes data to the specified data files

`Parser`: Reads the given user input and breaks it down into machine-readable code

`Profile`: Stores and records the user's profile information

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

## Product Scope
### Target user profile

Students who are too busy with work to carefully monitor their eating habits, and are concerned about their health.

### Value proposition

Diet Manager aims to achieve the following:
* Streamline the diet recording process 
* Allow users to track and monitor their eating habits
* Provide personalised information and recommendations for the user

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
