# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}
### Finding and Filtering Activities

## Finding Activities by Name: `find`
Users can request for a sub-list of activities that has names containing a given keyword to be printed.

Format: `find KEYWORD`

## Filtering Activities by Tags: `filter`
Users can request for a sub-list of activities that has specific tags.

Format: `filter TAGNAME1 TAGNAME2`

## Further Finding/Filtering: `-s`
Users can chain `find` and `filter` commands to generate an even smaller sub-list of activities based on his/her needs. 
This is can be particularly useful when the user wants to generate a `graph`.

This flag applies to `find` and `filter` commands only. 

Format: 
* `filter -s TAGNAME1 TAGNAME2`
* `find -s keyword`


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

* Add to-do `todo n/TODO_NAME d/DEADLINE`
