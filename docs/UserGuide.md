# SHOCO v2.0 - User Guide

### Table of Contents
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
* **[4. Additional information](#additional-information)**
* **[5. FAQ](#faq)**
* **[6. Command Summary](#command-summary)**

&nbsp;
<!-- @@author kokjoon97 -->
## Introduction

Have you ever encountered the problem of having to make multiple trips to the supermarket
because you forgot to get something important? Have you ever gone to the supermarket just to realise
you do not have enough cash on you?

If these problems sound familiar to you, fret not! With SHOCO, such troubles are now a thing of the
past.

SHOCO is a command-line interface (CLI) application that allows you to 
manage and plan your shopping list and budget. With better organisation and also a
budget tracker, we are here to enhance your grocery-shopping experience and make the woes of
grocery shopping disappear.
&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `SHOCO` from [here](https://github.com/AY1920S2-CS2113T-T13-1/tp/releases).
3. Open the command prompt in the directory of the executable and type in the following command:
   `java -jar SHOCO.jar`
4. You are now all set to plan your shopping list!
&nbsp;
<!-- @@author -->
<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>


## Features 

#### Command Format
* Please note the words in UPPER_CASE are the parameters that you can supply 
  * e.g. in `ADD i/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `ADD i/apple`.

* Items in square brackets are *optional* and you can omit them. 
  * e.g `EDIT 1 [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]` can be used as `EDIT 1 i/apple p/4.00` or as `EDIT 1 i/apple`.
<!-- @@author kokjoon97 -->
* All command words are case-sensitive and you should always use uppercase.
  * e.g `ADD` in `ADD i/DESCRIPTION` consists of only uppercase letters.
  
* You can supply parameters for the `ADD` and `EDIT` command, namely `DESCRIPTION`, `QUANTITY` and `PRICE`, in any order.
  * e.g `ADD i/apple q/5` **AND** `ADD q/5 i/apple` should both produce the same result.
<!-- @@author -->
&nbsp;

***

### Adding an item: `ADD`
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
<!-- @@author kokjoon97 -->
### Deleting an item: `DEL`
Removes an item from the list at the specified index.

Format: `DEL INDEX`

* The `INDEX` should be an integer.
* The `INDEX` should not be out of bounds of the shopping list.  

Example of usage: 

`DEL 3`
<!-- @@author -->
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
<!-- @@author kokjoon97 -->
### Setting a budget: `SET`
Sets a budget for the user.

Format: `SET b/AMOUNT`

* The `AMOUNT` can be any decimal number that is between 0 to 5000.
* The `b/` substring should be present in the command.  

Example of usage: 

`SET b/3.00`
<!-- @@author -->
&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***

### Resetting a budget: `RES`
Resets te budget to be 0.00 for the user.

Format: `RES`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>

***
<!-- @@author kokjoon97 -->
### Finding an item: `FIND`
Filters the shopping list according to a keyword specified by the user.

Format: `FIND KEYWORD`

* The `KEYWORD` can be any character or string.
* The `KEYWORD` field should not be left empty.  

Example of usage: 

`FIND apple`
<!-- @@author -->
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
<!-- @@author kokjoon97 -->
## Additional information

### 1. Loading and saving your shopping list

All your shopping list and budget data are saved to JSON files after you
exit the application. This data is also retrieved from the same JSON files the next time you boot up
Shoco. No further action is required from you as this is an automatic process.

### 2. Automated budget tracker

When the total cost of the items in your shopping list exceeds the stored budget amount, a message will be
displayed which states by how much you have overrun your current budget. This message will only
stop appearing when you increase your budget amount sufficiently or remove enough items from your list to keep within
your budget.
<!-- @@author -->
## FAQ

**Q**: How do I transfer my data to another computer?


**A**: Simply transfer the JSON files that contain your SHOCO data onto the new computer and place them in the
folder that contains the SHOCO app. If the folder already has the JSON files, replace them.

<!-- @@author kokjoon97 -->

**Q**: Is it possible to restore a list that I have deleted?

**A**: Unfortunately, we are still working on this feature and there is no such functionality at this
point in time. It is however, possible to manually backup the `shoppinglist.json` file
from time to time so that if you unintentionally cleared your list, you can always replace the empty
`shoppinglist.json` file with your backed up version.

<!-- @@author -->

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
* Reset budget `RES`
* Find item `FIND KEYWORD`
* View help `HELP`
* Exit program `BYE`

&nbsp;

<b><a href="#shoco-v10---user-guide">&#129053; back to top</a></b>
