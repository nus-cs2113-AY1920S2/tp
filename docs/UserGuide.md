# User Guide

* [1. Introduction](#introduction)
* [2. Quick Start](#quick-start)
* [3. Features](#features)
    + [3.1. Add:](#add) `add`
    + [3.2. Delete:](#delete) `delete`
    + [3.3. List:](#list) `list`
    + [3.4. Search:](#search) `search`
    + [3.5. Sales:](#sales) `sales`
    + [3.6. Clear:](#clear) `clear`
    + [3.7. Saving:](#saving) `bye`
    + [3.8. Loading](#load) 
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
2. Download the latest version of `Restaurant Daily Report` from [here](https://github.com/AY1920S2-CS2113-T14-4/tp/releases/download/v2.0/restaurant-2.0.jar).

<a name="features"></a>

## Features 
``` javascript
Add menu items, ingredients and reservations.

* words in UPPERCASE: parameters
* parameters in [ ]: optional parameters
* parameters in { | }: at least one of them
* sequence of the parameters can be changed
```

<a name="add"></a>

### Add
Add menu items, ingredients, and reservations.

#### Add menu item: add dish
* Format: `add dish; n/NAME; [i/INGREDIENT1, INGREDIENT2, ...]; p/PRICE;`
  + Example: `add dish; n/bacon pizza; i/cheese, bacon; p/7.00;`<br/>
  

#### Add ingredient into stock: add stock
* Format: `add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;`
  + Example: `add stock; i/tomato; q/10; p/0.50;`<br/>


#### Add reservation into list: add reservation <br/>
* Format: `add reservation; p/CONTACT_PERSON_NAME; d/DATE; n/NUMBER_OF_GUESTS; c/CONTACT; [m/COMMENTS];`<br/>
  + Example: `add reservation; p/Peter; d/2020-03-12 12:00; n/3; c/98955555;`<br/>

*The `DATE` must be in **yyyy-mm-dd HH:mm** format. The `NUMBER_OF_GUESTS` must be a non-negative integer.*<br/>

<a name="delete"></a>

### Delete
Delete menu items, ingredients and reservations.

#### Delete menu item: delete dish
* Format: `delete dish; n/NAME;`
  + Example: `delete dish; n/bacon pizza;`<br/>


#### Delete ingredient in stock: delete stock
* Format: `delete stock; i/INGREDIENT; [q/QUANTITY;]`
  + Example: `delete stock; i/tomato; q/1;`<br/>
  + Example: `delete stock; i/tomato;`<br/>


#### Mark reservation as invalid: delete reservation<br/>
* Format: `delete reservation; r/NUMBER_OF_RESERVATION;`<br/>
  + Example: `delete reservation; r/12;`<br/>


#### Mark reservation as served: mark reservation<br/>
Cannot mark the reservation as served if the reservation is originally invalid.<br/>
* Format: `mark reservation; r/NUMBER_OF_RESERVATION;`<br/>
  + Example: `mark reservation; r/4;`<br/>


<a name="list"></a>

### List
List items in the menu, stock and reservations.

* List menu : `list dish;`
* List stock : `list stock;`
* List all reservation : `list reservation;`
* List served reservation : `list served reservation;`
* List unserved reservation : `list unserved reservation;`

<a name="search"></a>

### Search
Search items in the menu, stock and reservations.

#### Search for dish: search dish
* Search dish : `search dish; k/KEYWORD;`
  + Example: `search dish; k/bacon;`


#### Search for stock: search stock
* Search stock : `search stock; k/KEYWORD;`
  + Example: `search stock; k/tomato;`


#### Search for reservation: search reservation<br/>
* Search reservation : `search reservation; {r/RESERVATION_NUMBER; | d/DATE;}`<br/>
  + Example: `search reservation; r/1;`<br/>
  + Example: `search reservation; d/2020-02-02;`<br/>
  + Example: `search reservation; r/0; d/2020-02-02;`<br/>

*The `DATE` must be in **yyyy-mm-dd** format. The `RESERVATION_NUMBER` must be a non-negative integer.*<br/>


<a name="sales"></a>
### Sales
Add daily sales and calculate profit

#### Sell item : sell item
* Format: `sell dish; d/DISH; q/QUANTITY;`
* Example: `sell dish; d/pasta; q/10;`

#### Generate Profit : calculate profit
Calculates the net profit from all the sold dishes
* Format: `profit`

#### Find most popular dish : popular
Prints the most popular dish from the sold list.
* Format: `popular`

<a name="clear"></a>
### Clear
Clear the stock, dish, or reservation list

#### Clear stock : clear stock
* Format `clear stock;`

#### Clear reservation : clear reservation
* Format: `clear reservation;`

<a name="saving"></a>

### Saving
All dishes, stock and reservations will be saved to `report.txt` automatically after the program is exited.

#### Exit Program: bye
* Format: `bye`

<a name="load"></a>

### Loading (for v2.1)
Dishes, stock and reservations will be automatically loaded when the program is started.

<a name="command-summary"></a>

## Command Summary

### Add
* Format: `add dish; n/NAME; [i/INGREDIENT1, INGREDIENT2, ...]; p/PRICE;`
* Format: `add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;`
* Format: `add reservation; r/NUMBER_OF_RESERVATIONS;`

### Delete
* Format: `delete dish; n/NAME;`
* Format: `delete stock; i/INGREDIENT; q/QUANTITY;`
* Format: `delete stock; i/INGREDIENT;`
* Format: `delete reservation; r/NUMBER_OF_RESERVATIONS;`

### Mark
* Format: `mark reservation; r/NUMBER_OF_RESERVATIONS;`

### List
* Format: `list dish;`
* Format: `list stock;`
* Format: `list reservation;`
* Format: `list served reservation;`
* Format: `list unserved reservation;`

### Search
* Format: `search dish; k/KEYWORD;`
* Format: `search stock; k/KEYWORD;`
* Format: `search reservation; {r/RESERVATION_NUMBER; | d/DATE;}`

### Sales
* Format: `sell dish; d/DISH; q/QUANTITY;`
* Format: `profit`
* Format: `popular`

### Clear
* Format: `clear stock;`
* Format: `clear reservation;`
