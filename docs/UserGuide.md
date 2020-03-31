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
- `[module name` is to be entered in alpha-numeric characters i.e.`Software Engineering & Object-Oriented Programming`
- '[semester]' is to be entered in numeric characters between 1-8 i.e.`5`
- `[credit]` is to be entered in numeric characters i.e. `4`
- `[grade` is to be entered in alpha **uppercase** characters i.e. `A+`


For example: in `​add id/[module code] s/[semester] mc/[credit]`, `[module code]` is the parameter of a specific 
module’s code which can be used as `add id/CS2113 s/4 mc/4`.

### Adding modules: add
Adds a specific module to a specific semester.

Format:​ 
`add id/[module code] s/[semester] mc/[credit]` or `add n/[module name] s/[semester] mc/[credit]`

Example:​ `add n/Software Engineering & Object-Oriented Programming s/4 mc/4`

Example:​ `add id/CS2113 s/4 mc/4`

Adds a specific module to available module list. 

Format:
`add id/[module code] n/[name of module] mc/[module credit] pre/[pre requisites]`

Example: `add id/CS2113 n/Software Engineering & Object-Oriented Programming mc/4 pre/CS2040C`

### Marking as done: done

Marks a module as done to show that it has been completed. 

Format:​ `done n/[module name] g/[grade]` or `done id/[module code] g/[grade]`

Example:​ done n/Software Engineering & Object-Oriented Programming g/A+

Example:​ done id/CS2113 g/SU

### Viewing modules: view

#### Viewing all available modules

Display all available modules.

Format:​ `view`

#### Viewing done modules

Display all finished modules.

Format: ​`view /dm`

#### Viewing module plan
Display user's module plan.

Format:​ `view /mp`

#### Viewing completed modules' credits
Display modules' credits the user completed.

Format: ​`view /cc`

#### Viewing cap 
Display the user's current CAP

Format: `CAP`

### Help
Display the help list.

Format: `help`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

### Add

Command: 

`add id/[module code] s/[semester] mc/[credit]` 

or

`add n/[module name] s/[semester] mc/[credit]`

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
