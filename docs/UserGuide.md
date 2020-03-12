# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

**Command Format**

* Words in UPPER_CASE are the parameters to be supplied by the user<br/>
e.g. in `delete reservation; r/RESERVATION_ NUMBER;`, RESERVATION_ NUMBER is a parameter which can be used as `delete reservation; r/12;`.<br/>
* Items in square brackets are optional e.g `n/NAME; [i/INGREDRIENT;]` can be used as `n/pizza;i/bacon` or as `n/pizza`.<br/>
* Parameters can be in any order e.g. if the command specifies `n/NAME; i/INGREDIENT;`, `i/INGREDIENT; n/NAME` also acceptable.
* Different types of parameters should be separated by semicolon. Space is optional.<br/>
Parameters inside the same type should be separated by a comma.<br/>


### Adding a reservation: `add reservation`
Adds a reservation to the reservation list.

Format: `add reservation; p/CONTACT_PERSON_NAME; d/DATE; n/NUMBER_OF_GUESTS; c/CONTACT; [m/COMMENTS];`

* The `DATE` must be in **yyyy-mm-dd HH:mm** format.
* The `NUMBER_OF_GUESTS` must be an integer.
* Other parameters are strings which can have punctuations except semicolon.

### Marking as reservation as served: `delete reservation`
Marks a reservation as served.

Format: `delete reservation; r/RESERVATION_NUMBER;

* The `RESERVATION_NUMBER` must be a valid integer which is the index of reservation in the reservation list.

### Listing all reservations: `list reservation`
Shows a list of all reservations.

Format: `list reservation;`

### Adding a to-do: `todo`
Adds a to-do item to the list of to-dos.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add reservation `add reservation; p/CONTACT_PERSON_NAME; d/DATE; n/NUMBER_OF_GUESTS; c/CONTACT; [m/COMMENTS];`
* Mark reservation as served `delete reservation; r/RESERVATION_NUMBER;`  
* List reservations `list reservation;`

* Add to-do `todo n/TODO_NAME d/DEADLINE`
