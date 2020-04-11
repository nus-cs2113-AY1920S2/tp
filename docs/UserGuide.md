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

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045324-19726c80-7c3d-11ea-8556-b1d00c140d94.png">
</p>

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
2. Download the latest version of `Restaurant Daily Report` from [here](https://github.com/AY1920S2-CS2113-T14-4/tp/releases/download/v2.1/CS2113-T14-4.RestaurantDailyReport.jar).

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
  + Expected: 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045366-4a52a180-7c3d-11ea-9114-6e48a1ca70ae.png">
</p>
  
  + Expected error message if wrong format: 
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043818-38b8cc00-7c34-11ea-9dd4-4890985aeaf3.png">
</p>
  

#### Add ingredient into stock: add stock
* Format: `add stock; i/INGREDIENT1; q/QUANTITY; p/PRICE;`
  + Example: `add stock; i/tomato; q/10; p/0.50;`<br/>
  + Expected:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043850-7d446780-7c34-11ea-8a71-0afcb3422856.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043869-99e09f80-7c34-11ea-95b9-607e0e5119db.png">
</p>
  

#### Add reservation into list: add reservation <br/>
* Format: `add reservation; p/CONTACT_PERSON_NAME; d/DATE; n/NUMBER_OF_GUESTS; c/CONTACT; [m/COMMENTS];`<br/>
  + Example: `add reservation; p/Peter; d/2020-03-12 12:00; n/3; c/98955555;`<br/>
  + Expected:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043908-ce545b80-7c34-11ea-83af-f4ddb30f9674.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043924-e6c47600-7c34-11ea-828a-776c0ac42ec2.png">
</p>


*The `DATE` must be in **yyyy-mm-dd HH:mm** format. The `NUMBER_OF_GUESTS` must be a non-negative integer.*<br/>

<a name="delete"></a>

### Delete
Delete menu items, ingredients and reservations.

#### Delete menu item: delete dish
* Format: `delete dish; n/NAME;`
  + Example: `delete dish; n/bacon pizza;`<br/>
  + Expected:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043945-09568f00-7c35-11ea-80e1-a433245d36d2.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79043969-268b5d80-7c35-11ea-9534-a1606a58aa62.png">
</p>


#### Delete ingredient in stock: delete stock
* Format: `delete stock; i/INGREDIENT; [q/QUANTITY;]`
  + Example: `delete stock; i/tomato; q/1;`<br/>
  + Example: `delete stock; i/tomato;`<br/>
  + Expected:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044133-04dea600-7c36-11ea-9ea7-e31f9932b031.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044056-a1547880-7c35-11ea-9f83-27f105c4237a.png">
</p>


#### Mark reservation as invalid: delete reservation<br/>
* Format: `delete reservation; r/NUMBER_OF_RESERVATION;`<br/>
  + Example: `delete reservation; r/1;`<br/>
  + Expected:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044189-63a41f80-7c36-11ea-8680-7cb0f235d3c4.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044204-820a1b00-7c36-11ea-91e4-1092e55cd248.png">
</p>



#### Mark reservation as served: mark reservation<br/>
Cannot mark the reservation as served if the reservation is originally invalid.<br/>
* Format: `mark reservation; r/NUMBER_OF_RESERVATION;`<br/>
  + Example: `mark reservation; r/4;`<br/>

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044262-b8479a80-7c36-11ea-8b2b-509ce1a497f4.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044282-cdbcc480-7c36-11ea-8887-cc97a3dba885.png">
</p>




<a name="list"></a>

### List
List items in the menu, stock and reservations.

* List menu : `list dish;`
  + Expected: 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045420-96054b00-7c3d-11ea-8a98-87c87e975b00.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044348-2be9a780-7c37-11ea-95e6-5fcaf1298003.png">
</p>

* List stock : `list stock;`

  + Expected: 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044377-69e6cb80-7c37-11ea-9460-7420087127b4.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044392-808d2280-7c37-11ea-80a6-0675ad2b316f.png">
</p>

* List all reservation : `list reservation;`
  + Expected: 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044417-9d295a80-7c37-11ea-8668-d5c447556a79.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044478-e8436d80-7c37-11ea-9d10-5cd99e50a0f8.png">
</p>

* List served reservation : `list served reservation;`
  + Expected:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044438-bcc08300-7c37-11ea-8074-726d28244327.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044495-04470f00-7c38-11ea-9442-a94bd0a63393.png">
</p>


* List unserved reservation : `list unserved reservation;`
  + Expected:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044582-85060b00-7c38-11ea-9ea5-85ca4e926994.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044511-22147400-7c38-11ea-82c8-377ec09dd6fc.png">
</p>

<a name="search"></a>

### Search
Search items in the menu, stock and reservations.

#### Search for dish: search dish
* Search dish : `search dish; k/KEYWORD;`
  + Example: `search dish; k/bacon;`
  + Expected:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044618-b7176d00-7c38-11ea-9321-878a3a2b70db.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044645-d0201e00-7c38-11ea-90b8-d921aa8e2cd7.png">
</p>

#### Search for stock: search stock
* Search stock : `search stock; k/KEYWORD;`
  + Example: `search stock; k/tomato;`
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044717-5c324580-7c39-11ea-9119-9d4664fc178b.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044731-779d5080-7c39-11ea-844b-fb80db15fb75.png">
</p>


#### Search for reservation: search reservation<br/>
* Search reservation : `search reservation; {r/RESERVATION_NUMBER; | d/DATE;}`<br/>
  + Example: `search reservation; r/1;`<br/>
  + Example: `search reservation; d/2020-03-12;`<br/>
  + Example: `search reservation; r/0; d/2020-02-02;`<br/>
  
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044813-f6928900-7c39-11ea-8829-075517c74f96.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044879-846e7400-7c3a-11ea-8371-027d27740516.png">
</p>
  

*The `DATE` must be in **yyyy-mm-dd** format. The `RESERVATION_NUMBER` must be a non-negative integer.*<br/>


<a name="sales"></a>
### Sales
Add daily sales and calculate profit

#### Sell item : sell item
* Format: `sell dish; d/DISH; q/QUANTITY;`
* Example: `sell dish; d/bacon pizza; q/10;`
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044996-3c038600-7c3b-11ea-8adf-576ad1d900cb.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045006-5b9aae80-7c3b-11ea-8884-cbdd031c5a53.png">
</p>


#### Generate Profit : calculate profit
Calculates the net profit from all the sold dishes. **DO ENSURE that the ingredients in the dishes ARE ALSO in the stock as well.**
* Format: `profit`
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045137-2478cd00-7c3c-11ea-96d4-54f6f774936c.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045102-e1b6f500-7c3b-11ea-94f9-9a51e2ec5d03.png">
</p>

#### Find most popular dish : popular
Prints the most popular dish from the sold list.
* Format: `popular`
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045042-a3b9d100-7c3b-11ea-85db-af49f7a46297.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045080-bd5b1880-7c3b-11ea-8525-1f6ff7d74030.png">
</p>


<a name="clear"></a>
### Clear
Clear the stock or reservation list.

#### Clear stock : clear stock
* Format `clear stock;`
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044894-aa941400-7c3a-11ea-82db-b698c4010967.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044914-be3f7a80-7c3a-11ea-9f40-e7c99733eb26.png">
</p>

#### Clear reservation : clear reservation
* Format: `clear reservation;`
  + Expected: 
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045473-e67ca880-7c3d-11ea-81f2-370518f6aa87.png">
</p>
      
  + Expected error message if wrong format:
  
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79044950-ef1faf80-7c3a-11ea-9552-18f441073852.png">
</p>


<a name="saving"></a>

### Saving
All dishes, stock and reservations will be saved to `report.txt` automatically after the program is exited.

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/79045167-496d4000-7c3c-11ea-934d-719d338d2435.png">
</p>

#### Exit Program: bye
* Format: `bye`

<a name="load"></a>

### Loading 
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
