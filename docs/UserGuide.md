# Module Manager User Guide

## Introduction

Personal Module Manager is a software that tracks the number of modular credits, modules taken and modules required 
for its user to graduate. The software is for NUS Students who want to plan their modules per semester in an easy way 
and it is optimized for those who want to type fast with CLI.
Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY1920S2-CS2113-T15-3/tp/releases).

## Features

### Command Format

- Words in `[XXX]` are the parameters to be supplied by the user.
- `[module code]` is to be entered in alpha-numeric uppercase characters i.e.`CS2113`
- `[module name]` is to be entered in alpha-numeric characters i.e.`Software Engineering & Object-Oriented Programming`
- `[semester]` is to be entered in numeric characters between 1-8 i.e.`5`
- `[credit]` is to be entered in numeric characters i.e. `4`
- `[grade]` is to be entered in alpha **uppercase** characters i.e. `A+`

For example: in `​add id/[module code] s/[semester] mc/[credit]`, `[module code]` is the parameter of a specific 
module’s code which can be used as `add id/CS2113 s/4 mc/4`.

### Adding modules: add
You can add a module to a semester or to the list of available modules.

#### Adds a specific module to a specific semester.
You can do so using the module code or the module name.
The module to be added must be in the existing list of available modules.

##### Based on module code
Format: `add id/[module code] s/[semester] mc/[credit]`

Example:​ `add id/IS4241 s/4 mc/4`

Expected output: 

`Okay! I have added this module to your semester's module list:`

`ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2`

##### Based on module name
Format: `add n/[module name] s/[semester] mc/[credit]`

Example:​ `add n/Social Media Network Analysis s/3 mc/4`

Example of expected output:

`Okay! I have added this module to your semester's module list:`

`ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S1`

#### Adds a specific module to available module list. 
Format: `add id/[module code] n/[name of module] mc/[module credit] pre/[pre requisites]`

Example: `add id/IS4241 n/Social Media Network Analysis mc/4 pre/CS1010 IS1103`

Example of expected output:

`Okay! I have added this module to the available modules to choose from:`

`ID: IS4241 Name: Social Media Network Analysis | Modular Credit: 4 | Prerequisites: CS1010`

Example:  `add id/GET1018 n/Mathemetics of Games mc/4 pre/`

Example of expected output:

`Okay! I have added this module to the available modules to choose from:`

`ID: GET1018 Name: Mathemetics of Games | Modular Credit: 4`


### Deleting module: delete
You can delete a module from a semester of your module plan or from the list of available modules.

#### Delete a specific module from a semester in module plan
You can do so by using the module code or the module name.
The module to be deleted must be in your module plan.
 
#### Based on module code
Format: `delete id/[module code] s/[semester]`

Example:​ `delete id/IS4241 s/4`

Expected output: 

`Okay, this module has been deleted from the corresponding semester`

`Module IS4241 has been deleted from semester Y2S2`


#### Based on module name
Format: `add n/[module name] s/[semester]`

Example:​ `add n/Discrete Structure s/4`

Expected output: 

`Okay, this module has been deleted from the corresponding semester`

`Module  has been deleted from semester Y2S2`

#### Delete a specific module from available module list

#### Based on module name
Format: `add id/[module code] s/[semester] mc/[credit]`

Example:​ `add id/IS4241 s/4 mc/4`

Expected output: 

`Okay! I have added this module to your semester's module list:`

`ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2`

#### Based on module code
Format: `add id/[module code] s/[semester] mc/[credit]`

Example:​ `add id/IS4241 s/4 mc/4`

Expected output: 

`Okay! I have added this module to your semester's module list:`

`ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2`

### Marking as done: done
Marks a module as done to show that it has been completed.
This can be done based on a module code or module name.

#### Based on module code
Format:​ `done id/[module name] g/[grade]`

Example:​ `done id/Software Engineering & Object-Oriented Programming g/A+`

Example of expected output:

`Okay, I've marked the module as done!`

#### Based on module name

Format: `done n/[module code] g/[grade]`

Example:​ `done n/CS2113 g/CU`

Example of expected output:

`Okay, I've marked the module as done!`

### Viewing modules: view

#### Viewing all available modules

Display all the available modules you can choose from.

Format:​ `view`

Example of expected output:

`Okay! Here are your available modules:`

`---------------------------------------------------`

`|  01 | CS1010   | Programming Methodology                                       | 4             | None                    |`

`|  02 | CS2030   | Programming Methodology II                                    | 4             | CS1010                  |`

`|  03 | CS2040   | Data Structures and Algorithms                                | 4             | CS1010                  |`

#### Viewing done modules

Display all the modules that you have completed.

Format: ​`view /dm`

Example of expected output:

`Okay! Here are your completed modules:`
 `Y2S1`
`1.[✓] ID: CS1010 Name: Programming Methodology | Module Credit: 4 | Sem: Y2S1 | Grade: A+`
 
 `Y2S2`
 `1.[✓] ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2 | Grade: A-`

#### Viewing module plan
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

#### Viewing completed modules' credits
Display the number of modular credits you have completed.

Format: ​`view /cc`

Example of expected output:

`You have completed this amount of credits:`

 `4`

#### Viewing cap 
Display your current CAP.

Format: `CAP`

Example of expected output:

`Your current CAP is:`
`4.75`

### Help
Display the list of commands that you can input.

Format: `help`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

### Add to module plan

Command: 

`add id/[module code] s/[semester] mc/[credit]` 

or

`add n/[module name] s/[semester] mc/[credit]`

### Add to available module list

Command:

`add id/[module code] n/[name of module] mc/[module credit] pre/[pre requisites]`

### Done

Command: 

`done n/[module name] g/[grade]` 

or 

`done id/[module code] g/[grade]`

### View

Command: 

`view`

or

`view /cm` 

or

`view /dm` 

or

`view /mp`

or 

`view /cc`

or

`CAP`
