# Module Manager - User Guide
By: `CS2113-T15-3` Since: `2020`  

Table of Contents  
1. [Introduction](#1-introduction)  
2. [Quick Start](#2-quick-start)  
3. [Features](#3-features)  
    3.1 [Command Format](#31-command-format)  
    3.2 [Adding modules](#32-adding-modules-add)  
    . . 3.2.1 [Adds a specific module to a specific semester](#321-adds-a-specific-module-to-a-specific-semester)  
    . . 3.2.2 [Based on module code](#322-based-on-module-code)  
    . . 3.2.3 [Based on module name](#323-based-on-module-name)  
    . . 3.2.4 [Based on module code and name](#324-based-on-module-code-and-name)  
    . . 3.2.5 [Adds a specific module to available module list](#325-adds-a-specific-module-to-available-module-list)  
    3.3 [Deleting module](#33--deleting-module-delete)  
    . . 3.3.1 [Delete a specific module from a semester in module plan](#331-delete-a-specific-module-from-a-semester-in-module-plan)  
    . . . 3.3.1.1 [Based on module code](#3311-based-on-module-code)    
    . . . 3.3.1.2 [Based on module name](#3312-based-on-module-name)  
    . . 3.3.2 [Delete a specific module from available module list](#332-delete-a-specific-module-from-available-module-list)  
    . . . 3.3.2.1 [Based on module name](#3321-based-on-module-name)  
    . . . 3.3.2.2 [Based on module code](#3322-based-on-module-code)  
    3.4 [Marking as done](#34-marking-as-done-done)  
    . . 3.4.1 [Based on module code](#341-based-on-module-code)  
    . . 3.4.2 [Based on module name](#342-based-on-module-name)  
    3.5 [Viewing modules](#35-viewing-modules-view)  
    . . 3.5.1 [Viewing all available modules](#351-viewing-all-available-modules)  
    . . 3.5.2 [Viewing done modules](#352-viewing-done-modules)  
    . . 3.5.3 [Viewing module plan](#353-viewing-module-plan)  
    . . 3.5.4 [Viewing completed modules' credit](#354-viewing-completed-modules-credits)  
    . . 3.5.5 [Viewing CAP](#355-viewing-cap)  
    3.6 [Find](#36-find)  
    3.7 [Clear](#37-clear)  
    3.8 [Help](#38-help)  
    3.9 [Exit](#39-exit)  
4. [FAQ](#4-faq)  
5. [Command Summary](#5-command-summary)  

## 1. Introduction

Personal Module Manager is a software that tracks the number of modular credits, modules taken and modules required 
for its user to graduate. The software is for NUS Students who want to plan their modules per semester in an easy way 
and it is optimized for those who want to type fast with CLI.
Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

## 2. Quick Start
1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY1920S2-CS2113-T15-3/tp/releases).

## 3. Features

### 3.1 Command Format

- Words in `[XXX]` are the parameters to be supplied by the user.
- `[module code]` is to be entered in alpha-numeric uppercase characters i.e.`CS2113` （case insensitive)
- `[module name]` is to be entered in alpha-numeric characters i.e.`Software Engineering & Object-Oriented Programming`
- `[semester]` is to be entered in numeric characters between 1-10 i.e.`5`
- `[credit]` is to be entered in numeric characters between 1-10 i.e. `4`
- `[grade]` is to be entered in alpha characters i.e. `A+` （case insensitive）

For example: in `​add id/[module code] s/[semester] mc/[credit]`, `[module code]` is the parameter of a specific 
module’s code which can be used as `add id/CS2113 s/4 mc/4`.

### 3.2 Adding modules: add
You can add a module to a semester or to the list of available modules.

#### 3.2.1 Adds a specific module to a specific semester.
You can do so using the module code or the module name.
The module to be added could be in the existing list of available modules or not.\
If you add a module which is in the list of available modules, then when you input code or name, the output
will show both code and name of this module.\
You can add modules whose grades are `F` or `CU` to a specific semester again.

##### 3.2.2 Based on module code
Format: `add id/[module code] s/[semester] mc/[credit]`

Example:​ `add id/IS4241 s/4 mc/4`

Expected output: 

`Okay! I have added this module to your semester's module list:`

`ID: IS4241 | Module Credit: 4 | Sem: Y2S2`

##### 3.2.3 Based on module name
Format: `add n/[module name] s/[semester] mc/[credit]`

Example:​ `add n/Social Media Network Analysis s/3 mc/4`

Example of expected output:

`Okay! I have added this module to your semester's module list:`

`Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S1`

##### 3.2.4 Based on module code and name
Format: `add id/[module code] n/[module name] s/[semester] mc/[credit]`

Example:​ `add id/IS4241 n/Social Media Network Analysis s/3 mc/4`

Example of expected output:

`Okay! I have added this module to your semester's module list:`

`ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S1`

#### 3.2.5 Adds a specific module to available module list. 
Format: `add id/[module code] n/[module name] mc/[module credit] pre/[pre requisites]`

Example: `add id/IS4241 n/Social Media Network Analysis mc/4 pre/CS1010 IS1103`

Example of expected output:

`Okay! I have added this module to the available modules to choose from:`

`ID: IS4241 Name: Social Media Network Analysis | Modular Credit: 4 | Prerequisites: CS1010`

Example:  `add id/GET1018 n/Mathemetics of Games mc/4 pre/`

Example of expected output:

`Okay! I have added this module to the available modules to choose from:`

`ID: GET1018 Name: Mathemetics of Games | Modular Credit: 4`


### 3.3  Deleting module: delete
You can delete a module from a semester of your module plan or from the list of available modules.

#### 3.3.1 Delete a specific module from a semester in module plan
You can do so by using the module code or the module name.
The module to be deleted must be in your module plan.\
If you delete a module which is done and not failed, then the total complete credits will be changed.
 
#### 3.3.1.1 Based on module code
Format: `delete id/[module code] s/[semester]`

Example:​ `delete id/IS4241 s/4`

Expected output: 

`Okay, this module has been deleted from the corresponding semester`

`Module IS4241 has been deleted from semester Y2S2`


#### 3.3.1.2 Based on module name
Format: `delete n/[module name] s/[semester]`

Example:​ `delete n/Discrete Structure s/4`

Expected output: 

`Okay, this module has been deleted from the corresponding semester`

`Module Discrete Structure has been deleted from semester Y2S2`

#### 3.3.2 Delete a specific module from available module list

#### 3.3.2.1 Based on module name
Format: `delete id/[module code]`

Example:​ `delete id/IS4241`

Expected output: 

`Okay, this module has been deleted from the list of available modules`

`ID: IS4241 Name: Social Media Network Analysis | Modular Credit: 4`

#### 3.3.2.2 Based on module code
Format: `delete n/[module name]`

Example:​ `delete n/Social Media Network Analysis`

Expected output: 

`Okay, this module has been deleted from the list of available modules`

`ID: IS4241 Name: Social Media Network Analysis | Modular Credit: 4`

### 3.4 Marking as done: done
Marks a module as done to show that it has been completed.
This can be done based on a module code or module name.\
If you mark a module as done again, the new grade that has been entered will update your module's grade.

#### 3.4.1 Based on module code
Format:​ `done id/[module name] g/[grade]`

Example:​ `done id/Software Engineering & Object-Oriented Programming g/A+`

Example of expected output:

`Okay, I've marked the module as done!`

#### 3.4.2 Based on module name

Format: `done n/[module code] g/[grade]`

Example:​ `done n/CS2113 g/CU`

Example of expected output:

`Okay, I've marked the module as done!`

### 3.5 Viewing modules: view

#### 3.5.1 Viewing all available modules

Display all the available modules you can choose from.

Format:​ `view`

Example of expected output:

`Okay! Here are your available modules:`

`---------------------------------------------------`

`|  01 | CS1010   | Programming Methodology                                       | 4             | None                    |`

`|  02 | CS2030   | Programming Methodology II                                    | 4             | CS1010                  |`

`|  03 | CS2040   | Data Structures and Algorithms                                | 4             | CS1010                  |`

#### 3.5.2 Viewing done modules

Display all the modules that you have completed.

Format: ​`view /dm`

Example of expected output:

`Okay! Here are your completed modules:`
 `Y2S1`
`1.[✓] ID: CS1010 Name: Programming Methodology | Module Credit: 4 | Sem: Y2S1 | Grade: A+`
 
 `Y2S2`
 `1.[✓] ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2 | Grade: A-`

#### 3.5.3 Viewing module plan
Display your module plan, categorised based on the semester that the module was allocated to.

Format:​ `view /mp`

Example of expected output:

`Okay! Here is your module plan:`


 `Y2S1`
 
 `1.[✓] ID: CS1010 Name: Programming Methodology | Module Credit: 4 | Sem: Y2S1 | Grade: A+`
 
 `2.[✗] ID: CS1231 Name: Discrete Structures | Module Credit: 4 | Sem: Y2S1`
 
 
 `Y2S2`
 
 `1.[✗] ID: CS2113 Name: Software Engineering & Object-Oriented Programming | Module Credit: 4 | Sem: Y2S2`
 
 `2.[✗] ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2`

#### 3.5.4 Viewing completed modules' credits
Display the number of modular credits you have completed.

Format: ​`view /cc`

Example of expected output:

`You have completed this amount of credits:`

 `4`

#### 3.5.5 Viewing cap 
Display your current CAP.

Format: `CAP`

Example of expected output:

`Your current CAP is:`
`4.75`

### 3.6 Find
Looks up the module plan and list of available modules for modules that contain the keyword inputted.

Format: `find [keyword]`

Example: 'find cs'

`Okay, this is the list of related modules:`
 
 `List of selected modules:`
 
 `[✓] ID: CS1010 Name: Programming Methodology | Module Credit: 4 | Sem: Y2S1 | Grade: A+`
 
 `[✗] ID: CS1231 Name: Discrete Structures | Module Credit: 4 | Sem: Y2S1`


 `List of available modules:`
 
 `ID: CS1010 Name: Programming Methodology | Modular Credit: 4`
 
 `ID: CS2030 Name: Programming Methodology II | Modular Credit: 4 | Prerequisites: CS1010`


### 3.7 Clear
Clears the current module plan

Format: `clear`

### 3.8 Help
Display the list of commands that you can input.

Format: `help`

### 3.9 Exit
Exits the app.

Format: `bye`

## 4. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Simply transfer the folder including your jar file and saved data to another computer, and run the jar file there

## 5. Command Summary

### 5.1 Add to module plan

Command: 

`add id/[module code] s/[semester] mc/[credit]` 

or

`add n/[module name] s/[semester] mc/[credit]`

or

`add id/[module code] n/[module name] s/[semester] mc/[credit]`

### 5.2 Add to available module list

Command:

`add id/[module code] n/[module name] mc/[module credit] pre/[pre requisites]`

### 5.3 Delete a specific module from a semester in module plan

Command: 

`delete id/[module code] s/[semester]`

or

`delete n/[module name] s/[semester]`

### 5.4 Delete from available module list

Command: 

`delete id/[module code]`

or

`delete n/[module name]`

### 5.5 Done

Command: 

`done n/[module name] g/[grade]` 

or 

`done id/[module code] g/[grade]`

### 5.6 Find

Command: 

`find [keyword]`

### 5.7 View

Command: 

`view`

or

`view /dm` 

or

`view /mp`

or 

`view /cc`

or

`CAP`

### 5.8 Clear

Command:

`clear`

### 5.9 Help  
Command:  

`help`

### 5.10 Bye

Command:

`bye`
