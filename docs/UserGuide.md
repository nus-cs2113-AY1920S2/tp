# SHOCO v1.0 - User Guide

&nbsp;

* **[1. Introduction](#introduction)**
* **[2. Quick Start](#quick-start)**
* **[3. Features](#features)**
  * [3.1 Adding an item: `ADD`](#adding-an-item-add)
  * [3.2 Editing an item: `EDIT`](#editing-an-item-edit)
  * [3.3 Deleting an item: `DEL`](#deleting-an-item-del)
  * [3.4 Clearing the list: `CLEAR`](#clearing-the-list-clear)
  * [3.5 Marking an item as bought: `MARK`](#marking-an-item-as-bought-mark)
  * [3.6 Un-marking a marked item: `UNMARK`](#un-marking-a-marked-item-unmark)
  * [3.7 Display list and budget details: `DISPLAY`](#display-list-and-budget-details-display)
  * [3.8 Setting a budget: `SET`](#setting-a-budget-set)
  * [3.9 Resetting a budget: `RES`](#resetting-a-budget-res)
  * [3.10 Finding an item: `FIND`](#finding-an-item-find)
  * [3.11 Viewing help: `HELP`](#viewing-help-help)
  * [3.12 Exiting the Program: `BYE`](#exiting-the-program-bye)
* **[4. FAQ](#faq)**
* **[5. Command Summary](#command-summary)**


&nbsp;

## Introduction

SHOCO is a command-line interface (CLI) application that allows users to 
manage and plan their shopping lists and budget. If you are a fast typer, 
you will find SHOCO to be even more effective than applications with graphical 
user interfaces.

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `SHOCO` from [here](https://github.com/AY1920S2-CS2113T-T13-1/tp/releases).

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>


## Features 

#### Command Format
* Words in UPPER_CASE are the parameters to be supplied by the user 
  * e.g. in `ADD i/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `ADD i/apple`.

* Items in square brackets are *optional*. 
  * e.g `EDIT 1 [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]` can be used as `EDIT 1 i/apple p/4.00` or as `EDIT 1 i/apple`.

&nbsp;

***

### Adding an item: `ADD`
{add your details here}
Add the specified item in the shopping list.

Format: `ADD i/DESCRIPTION [p/PRICE] [q/QUANTITY]`

* The `DESCRIPTION` must exist.
* The `[QUANTITY]` must be a **positive integer**. *e.g 1, 2, 3 ..*
* The `[PRICE]` must be in **numerical** form (decimal form accepted).
* At least one of two parameters (price/quantity) must be present.
* i/, p/, q/ delimiters must be in **alphabetical** order.
* note that you can rearrange the delimiters (more will shown in the example.)
Example of usage: 

1. `ADD i/potato p/5.00 q/3` **OR** `ADD p/5.00 q/3 i/potato`
    * Add the description, price and quantity of this item in the shopping list
    
2. `ADD i/potato chips p/5.00`  **OR**  `ADD i/potato chips q/2` **OR** `ADD p/5.00 i/potato chips` 
   **OR** `ADD q/5 i/potato chips`
    * Add the description and price /  description and quantity /  of the item in the shopping list
    
3. `ADD i/potato chips` 
    * Add only description of the item in the shopping list

&nbsp;

<b><a href="#shoco-v10---user-guide"> &#129053; back to top</a></b>

***

### Editing an item: `EDIT`
Edits the specified item in the shopping list.

Format: `EDIT INDEX [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]`

* Edits the item at the specified `INDEX`. The `INDEX` refers to the index number 
shown in the displayed shopping list.
* The `INDEX` and `[QUANTITY]` must be a **positive integer**. *e.g 1, 2, 3 ..*
* The `[PRICE]` must be in **numerical** form (decimal form accepted).
* At least one of three parameters (description/price/quantity) must be present.
* i/, p/, q/ delimiters must be in **alphabetical** order.

Example of usage: 

1. `EDIT 3 i/potato p/5.00 q/3`
    * Edits the description, price and quantity of the 3rd item in the shopping list
    
2. `EDIT 3 i/potato chips p/5.00`  **OR**  `EDIT 3 i/potato chips q/2`  **OR**  `EDIT 3 p/5.00 q/2`
    * Edits the description and price /  description and quantity /  price and quantity of the 
    3rd item in the shopping list
    
3. `EDIT 3 i/potato chips` **OR** `EDIT 3 p/5.00` **OR** `EDIT 3 q/2`
    * Edits only description / only price / only quantity of the 3rd item in the shopping list
    
&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Deleting an item: `DEL`
Removes an item from the list at the specified index.

Format: `DEL INDEX`

* The `INDEX` should be an integer.
* The `INDEX` should not be out of bounds of the shopping list.  

Example of usage: 

`DEL 3`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Clearing the list: `CLEAR`
Clears all items in the shopping list. Automatically resets remaining budget to the user’s set budget.

Format: `CLEAR`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Marking an item as bought: `MARK`
Marks an item from the list at the specified index as bought.

Format: `MARK INDEX`

* The `INDEX` should be an integer.
* The `INDEX` should not be out of bounds of the shopping list.

Example of the usage: 

`MARK 5`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Un-marking a marked item: `UNMARK`
Un-marks a marked-as-bought item from the list at the specified index.

Format: `UNMARK INDEX`

* The `INDEX` should be an integer
* The `INDEX` should not be out of bounds of the shopping list.

Example of the usage:

`UNMARK 3`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Display list and budget details: `DISPLAY`
Shows the shopping list, budget, cost of the items and the remaining budget.

Format: `DISPLAY` 

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Setting a budget: `SET`
Sets a budget for the user.

Format: `SET b/AMOUNT`

* The `AMOUNT` can be any double that is between 0 to 5000.
* The `b/` substring should be present in the command.  

Example of usage: 

`SET b/3.00`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Resetting a budget: `RES`
Resets te budget to be 0.00 for the user.

Format: `RES`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Finding an item: `FIND`
Filters the shopping list according to a keyword specified by the user.

Format: `FIND KEYWORD`

* The `KEYWORD` can be any character or string.
* The `KEYWORD` field should not be left empty.  

Example of usage: 

`FIND apple`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Viewing help: `HELP`
Shows the available commands and how they are to be used.

Format: `HELP`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>


***


### Exiting the program: `BYE`
Exits the program.

Format: `BYE`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

&nbsp;

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the app in the other computer and replace the empty data file it creates with the file 
that contains the data of your previous SHOCO shopping list.

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>


&nbsp;

## Command Summary

* Add item `ADD i/DESCRIPTION [p/PRICE] [q/QUANTITY]`
* Edit item `EDIT INDEX [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]`
* Delete item `DEL INDEX`
* Clear list `CLEAR`
* Mark item `MARK INDEX`
* Un-mark item `UNMARK INDEX`
* Display list and budget details `DISPLAY`
* Set budget `SET b/AMOUNT`
* Reset Budget `RES`
* Find item `FIND KEYWORD`
* View help `HELP`
* Exit program `BYE`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

