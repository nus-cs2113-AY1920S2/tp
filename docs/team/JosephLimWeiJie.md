# Joseph Lim Wei Jie - Project Portfolio Page

## Overview

* [1. Restaurant Daily Report](#daily-report)
* [2. Summary of Contributions](#summary-contribution)
    + [2.1 Code contribution](#code-contribution)
    + [2.2 Enhancements implemented](#enhancement-implemented)
    + [2.3 Contributions to documentation](#contribute-documentation)
    + [2.4 Contributions to the Developer Guide](#contribute-developerguide)
    + [2.5 Contributions to team-based tasks](#contribute-team)
    + [2.6 Review/Mentoring contributions](#review-mentoring)
    + [2.7 Contributions beyond the project team](#contribute-team)
* [3. Contributions to the User Guide](#contribute-ug)
* [4. Contributions to the Developer Guide](#contribute-dg)

<a name="design-report"></a>

### Restaurant Daily Report

`Restaurant Daily Report` is a CLI app that generates a whitepaper, summarizing the internals of a restaurant. It’s aim is to provide restaurant owners a quick overview of how their restaurant is performing daily so that restaurant owners can better manage their business operations.

<a name="summary-contribution"></a>

### Summary of Contributions

<a name="code-contribution"></a>

#### Code contribution:
You can view my code contribution for this project [here:](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=josephlimweijie)

<a name="enhancement-implemented"></a>

#### Enhancements implemented:
##### Stock category:

###### Add:
* 1. Basic add functionality.
* 2. Informs user if the user enters a name that is similar to the existing ingredients in the stock.
* 3. Allows flexibility in the ordering of the parameters specified from the user.
    + For example: `add stock; i/tomato; q/10; p/$1.00;` and `add stock; i/tomato; p/$1.00; q/10` are both acceptable.
* 4. Customizes error messages according to the input supplied from the user.
    For example: 
    + `add stock; i/;` will display the message: `"The user's input must specify the ingredient's name!"`
    + `add stock; i/tomato; q/-10; p/$1.00` will display the message: `"Please enter a positive value for the quantity to be added!"`
    
###### Delete:
* 1. Basic delete functionality.
* 2. Allows flexibility in the ordering of the parameters specified from the user.
    + For example: `delete stock; i/tomato; q/10;` and `delete stock; p/$1.00; i/tomato` are both acceptable.
* 3. Allows the user to delete the ingredient by quantity count or remove from the stock entirely.
    For example:
    + `delete stock; i/tomato` removes tomato entirely from the stock.
    + `delete stock; i/tomato; q/1` reduces the quantity of tomatoes in the stock by 1. 
* 4. Customizes error messages according to the input supplied from the user.
    For example: 
    + `delete stock; i/;` will display the message: `"The user's input must specify the ingredient's name!"`
    + `delete stock; i/tomato; q/-10; p/$1.00` will display the message: `"Please enter a positive value for the quantity to be added!"`
    
###### List:
* 1. Basic list functionality.
* 2. Lists ingredients in from the highest quantity to the lowest quantity.

###### Search:
* 1. Basic search functionality.
* 2. Take into account case sensitivity of the keyword supplied from the user when searching against the stock.
    

<a name="contribute-documentation"></a>

#### Contributions to documentations:

###### User guide contribution:
* 1. Anchoring using Table of Content at the top of the user guide.
* 2. Introduction
* 3. Quick Start
* 4. Add: add ingredient into stock
* 5. Delete: delete ingredient from stock
* 6. List: list stock
* 7. Search: search stock
* 8. Command Summary:
    + Add format: `add stock; i/INGREDIENT; q/QUANTITY; P/PRICE;`
    + Delete format: `delete stock; i/INGREDIENT; q/QUANTITY` and `delete stock; i/INGREDIENT;`
    + List format: `list stock;`
    + Search format: `search stock; k/keyword;` 

<a name="contribute-developerguide"></a>

##### Developer guide contribution:
* 1. Anchoring using Table of Content at the top of the developer guide.
* 2. Design & Implementation:
    Sections:
    + 1.1 [Proposed] Search stock feature
    1.2 [Proposed] List stock ingredients in descending quantity

<a name="contribute-team"></a>

#### Contributions to team-based tasks:

<a name="review-mentoring"></a>

#### Review/Mentoring contributions: 

<a name="contribute-team"></a>

#### Contributions beyond the project team:

<a name="contribute-ug"></a>

#### Contributions to the User Guide (Extracts):

# User Guide

* [1. Introduction](#introduction)
* [2. Quick Start](#quick-start)
* [3. Features](#features)
    + [3.1. Add:](#add) `add`
    + [3.2. Delete:](#delete) `delete`
    + [3.3. List:](#list) `list`
    + [3.4. Search:](#search) `search`
    + [3.5. Sales:](#sales) `sales`
    + [3.6. Saving](#save) `bye`
    + [3.7. Loading](#load) 
* [4. Command Summary](#command-summary)

<a name="introduction"></a>

## Introduction

Restaurant Daily Report is a CLI app that generates a whitepaper, 
summarizing the internals of a restaurant. It’s aim is to provide 
restaurant owners a quick overview of how their restaurant is performing 
daily so that restaurant owners can better manage their business operations.

As such, the daily report will include these categories:
* Menu: Information on all menu items
* Reservation: Information on number of reservations a day
* Stock: Information on all food items/quantities/prices

<a name="quick-start"></a>

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Restaurant Report` from [here](https://github.com/AY1920S2-CS2113-T14-4/tp/releases/download/v2.0/restaurant-2.0.jar).

<a name="features"></a>

<a name="add"></a>

### Add
Add menu items, ingredients, and reservations.

#### Add ingredient into stock: add stock
* Format: `add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;`
  + Example: `add stock; i/tomato; q/10; p/$0.50;`<br/>
  
<a name="delete"></a>

### Delete
Delete menu items, ingredients and reservations.

#### Delete stock of ingredient: delete stock
* Format: `delete stock; i/INGREDIENT; [q/QUANTITY;]`
  + Example: `delete stock; i/tomato; q/1;`<br/>
  + Example: `delete stock; i/tomato;`<br/>
  
<a name="list"></a>

### List
List items in the menu, stock and reservations.

* List stock : `list stock`

<a name="search"></a>

### Search
Search items in the menu, stock and reservations.

#### Search for stock: search stock
* Search stock : `search stock; k/KEYWORD;`
  + Example: `search stock; k/tomato;`
  
<a name="command-summary"></a>

## Command Summary

### Add

* Format: `add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;`

### Delete

* Format: `delete stock; i/INGREDIENT; q/QUANTITY;`
* Format: `delete stock; i/INGREDIENT;`

### List

* Format: `list stock`

### Search
* Format: `search stock; k/KEYWORD;`


<a name="contribute-dg"></a>

#### Contributions to the Developer Guide (Extracts):

# Developer Guide

* [1. Design & Implementation](#design-implementation)
    + [1.1. [Proposed] Search stock feature](#search-stock)
    + [1.2. [Proposed] List stock in descending quantities](#list-stock)
    + [1.3. [Proposed] Generate profit for the day](#generate-profit)
    + [1.4. [Proposed] Search reservation](#search-reservation)
    + [1.5. [Proposed] Search dish](#search-dish)
* [2. Product Scope](#product-scope)
* [3. User Stories](#user-stories)
* [4. Non-Functional Requirements](#nonfunctional-requirement)
* [5. Glossary](#glossary)


<a name="design-implementation"></a>

## 1. Design & Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

<a name="search-stock"></a>

### 1.1 [Proposed] Search stock feature
#### 1.1.1 Proposed implementation

In the restaurant daily report, users can search against the stock category by supplying a keyword.

Given below is an example usage scenario and how the search mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `stock` will be initialized.

Step 2. The user executes `add stock; i/tomato; q/10; p/$0.40;` command to add a tomato ingredient into the `stock`. Further, the user may add more ingredients into the current `stock`. Suppose the user executes `add stock; i/potato; q/5; p/$0.40;` and `add stock; i/rice; q/3; p/$0.40;` as well.

Step 3. The user can now search against the current `stock` to see if an ingredient is stored in the `stock`. The user now executes `search stock; k/tomato;`, which will display the following result in the image. 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78652378-3da81380-78f4-11ea-8b7a-63115ead3ca5.PNG">
</p>

Step 4. If the ingredient that the user is searching for does not exist within the stock, a different message will be displayed as shown in the following image.

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78652553-75af5680-78f4-11ea-9df1-5aa85951d216.PNG">
</p>

The following sequence diagram shows how the search operation works:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78655767-0851f480-78f9-11ea-8b29-1e0758084777.png">
</p>

```
The sequence diagram can be interpreted as such:
* 1. CommandParser calls its own CommandParser#parseCommand(...). 
* 2. Assuming the user input the search stock command correctly, SearchStockCommand#SearchStockCommand(...) constructor is called. 
* 3. The newly constructed SearchStockCommand invokes its SearchStockCommand#parseIntoSearchKeyword(...) and does not return anything.
* 4. The CommandParser then invokes SearchStockCommand#execute(...), which then further invokes Stock#searchStock(...) from the Stock object.
* 5. The Stock object then self-invoke Stock#checkIngredientInStock(...) to see if there are ingredients that matches keyword that was passed into earlier.
* 6. If there are search results, Stock#printSearchResult(...) will display all the ingredients that matches the keyword given.
* 7. If there is no ingredient that matches the keyword, the program will display a different message to show the user.
* 8. Next, the time line returns back to CommandParser and the SearchStockCommand object is destroyed here.
* 9. If however, the user input did not input the search stock command correctly, CommandParser will invoke CommandParser#errorCommand() to notify the user.
``` 

#### 1.1.2 Design Considerations
##### Aspect: How search stock executes
* **Alternative 1 (current choice)**: List all ingredients that contains the keyword supplied.
  + Pros: Easy to implement.
  + Cons: All ingredients containing the keyword will be listed. This means that even if the keyword doesn't make sense, such as supplying "to", both potato and tomato will be listed since it contains "to".

* **Alternative 2**: The user supplies the exact ingredient name.
  + Pros: The ingredient that matches exactly with the keyword supplied will be listed.
  + Cons: The user have to input the ingredient name correctly each time he/she uses the search stock feature.

##### Aspect: Data structure to support the search stock feature.
* **Alternative 1 (current choice)**: Use a `List` to store all possible ingredient names that contain the keyword supplied.
  + Pros: Easy to understand.
  + Cons: The `List` is derived by converting the existing stock (which is a `HashMap`) into an ArrayList. In order to print out the ingredient's name, quantity and price, methods such as `getKey()` and `getValue()` have to be used.

* **Alternative 2**: Use the existing `HashMap` data structure employed by the `stock` object.
  + Pros: We do not need to create a new `List` to display the relevant results.
  + Cons: Iterating a HashMap can be done by converting it to an EntrySet or by using an iterator. These, compared to `List`, is much less straightforward.

<a name="list-stock"></a>

### 1.2 [Proposed] List stock ingredients in descending quantity
#### 1.2.1 Proposed implementation

In the restaurant daily report, users can view all the ingredients presently in the stock by supplying the input `list stock`. The ingredients will be ordered in descending quantities, that is, the ingredient that has the highest quantity will be listed first and vice versa.

Given below is an example usage scenario and how the listing mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `stock` will be initialized.

Step 2. The user executes `add stock; i/tomato; q/10; p/$0.40;` command to add a tomato ingredient into the `stock`. Further, the user may add more ingredients into the current `stock`. Suppose the user executes `add stock; i/potato; q/0; p/$0.40;` and `add stock; i/rice; q/55; p/$0.40;` as well.

Step 3. The user can now view the current `stock` to see what ingredients are there in the stock. The user now executes `list stock`, which will display the following result in the image. In this case, `rice` has the highest quantity of `55`, which is listed first as compared to `potato`, which has the lowest quantity of `0`. This can be seen from the image below.

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/77300028-5cb97980-6d28-11ea-9a27-7d118de3431f.PNG">
</p>

The following class diagram shows how the listing operation works:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78657922-0a698280-78fc-11ea-8fd6-13030985ef80.png">
</p>

1. When the user first runs the application, the Main object is initialized. The Main object then initializes the ui and the stock object in its `start()` method. 
2. Through its `runCommandUntilExit()` method, it then instructs the ui object to scan and read for user input. A CommandParser object is then initialized to parse this user input into a command.
3. The CommandParser object detects `list stock` as the user input, in which it will then create a ListStockCommand object.
4. The ListStockCommand object is then executed via its `execute(stock)` method, which takes in the stock object initialized previously and instruct it to list all ingredients through its `listIngredient()` method.
5. Within  the `listIngredient()` method, a temporary `List` data structure is used to convert from the `HashMap` in the stock object. The list is then sorted by supplying a `new Comparator` that compares the ingredient's quantity. Afterwards, the sorted list is then printed to be displayed to the user.

Alternatively, the listing mechanism process can be summarized in the following sequence diagram below:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78656166-975f0c80-78f9-11ea-90f2-a8dd68915b88.png">
</p>

```
The sequence diagram can be interpreted as such:
* 1. CommandParser calls its own CommandParser#parseCommand(...). 
* 2. Assuming the user input the list stock command correctly, ListStockCommand#ListStockCommand() constructor is called. 
* 3. The time line returns back to CommandParser.
* 4. The CommandParser then invokes ListStockCommand#execute(...), which then further invokes Stock#ListStock() from the Stock object.
* 5. The Stock object then self-invoke Stock#printStock() to print the ingredients that are in the stock to display it to the user.
* 6. Note that within the method Stock#printStock(), the hashMap in the stock will be sorted in descending ingredient quantity.
* 7. If there is no ingredient that in the stock, the program will display a different message to show the user.
* 8. Next, the time line returns back to CommandParser and the ListStockCommand object is destroyed here.
* 9. If however, the user input did not input the search stock command correctly, CommandParser will invoke CommandParser#errorCommand() to notify the user.
```


#### 1.2.2 Design Considerations

##### Aspect: How listing stock executes
* **Alternative 1 (current choice)**: List all ingredients in the stock in descending quantities.
  + Pros: The user is able to tell which ingredients are the least/most quickly. This can help to facilitate quicker decision making in terms of what ingredients to re-stock.
  + Cons: The current ingredients are listed in a specific ordering. A change in the ordering requirement would mean that a change in the implementation of the `comparator`.

* **Alternative 2**: List all ingredients in the stock without any ordering.
  + Pros: Easy to implement.
  + Cons: Hard to identity which ingredients that require re-stocking quickly.

##### Aspect: Data structure to support the search stock feature.
* **Alternative 1 (current choice)**: Use a `List` to store all possible ingredient names.
  + Pros: Easy to understand.
  + Cons: The `List` is derived by converting the existing stock (which is a `HashMap`) into an ArrayList. In order to print out the ingredient's name, quantity and price, methods such as `getKey()` and `getValue()` have to be used.

* **Alternative 2**: Use the existing `HashMap` data structure employed by the `stock` object.
  + Pros: We do not need to create a new `List` to display the relevant results.
  + Cons: Iterating a HashMap can be done by converting it to an EntrySet or by using an iterator. Sorting it, however, as compared to `List`, is much less straightforward.