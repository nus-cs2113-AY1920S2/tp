# SHOCO v1.0 - User Guide

## Introduction

SHOCO is a command-line interface (CLI) application that allows users to 
manage and plan their shopping lists and budget. If you are a fast typer, 
you will find SHOCO to be even more effective than applications with graphical 
user interfaces.

&nbsp;

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `SHOCO` from [here](https://github.com/AY1920S2-CS2113T-T13-1/tp/releases).

&nbsp;

## Features 

### Adding an item: `ADD`
{add your details here}

***

### Editing an item: `EDIT`
Edits the specified item in the shopping list.

Format: `EDIT [index] i/[description] p/[price] q/[quantity]`

* Edits the item at the specified `[index]`. The `[index]` refers to the index number shown in the displayed shopping list.
* The `[index]` and `[quantity]` must be a **positive integer**. *e.g 1, 2, 3 ..*
* The `[price]` must be in **numerical** form (decimal form accepted).
* At least one of three parameters (description/price/quantity) must be present.
* i/, p/, q/ delimeters must be in **alphabetical** order.

Example of usage: 

1. `EDIT 3 i/potato p/5.00 q/3`
    * Edits the description, price and quantity of the 3rd item in the shopping list
    
2. `EDIT 3 i/potato chips p/5.00`  **OR**  `EDIT 3 i/potato chips q/2`  **OR**  `EDIT 3 p/5.00 q/2`
    * Edits the description and price /  description and quantity /  price and quantity of the 3rd item in the shopping list
    
3. `EDIT 3 i/potato chips` **OR** `EDIT 3 p/5.00` **OR** `EDIT 3 q/2`
    * Edits only description / only price / only quantity of the 3rd item in the shopping list

***

### Deleting an item: `DEL`
Removes an item from the list at the specified index.

Format: `DEL [index]`

* The `[index]` should be an integer.
* The `[index]` should not be out of bounds of the shopping list.  

Example of usage: 

`DEL 3`

***

### Marking an item as bought: `MARK`
{add your details here}

***

### Un-marking a marked item: `UNMARK`
{add your details here}

***

### Listing all items: `DISPLAY`
{add your details here}

***

### Setting a budget: `SET`
Sets a budget for the user.

Format: `SET b/[amount]`

* The `[amount]` can be any double that is between 0 to 5000.
* The `b/` substring should be present in the command.  

Example of usage: 

`SET b/3.00`

***

### Finding an item: `FIND`
Filters the shopping list according to a keyword specified by the user.

Format: `FIND [keyword]`

* The `[keyword]` can be any character or string.
* The `[keyword]` field should not be left empty.  

Example of usage: 

`FIND apple`

***

### Viewing help: `HELP`

Format: `HELP`

***

&nbsp;

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

&nbsp;

## Command Summary

* Edit item `EDIT [index] i/[description] p/[price] q/[quantity]`
* Delete item `DEL [index]`
* Set budget `SET b/[amount]`
* Find item `FIND [keyword]`
* Viewing help `HELP`
