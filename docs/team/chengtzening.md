# Cheng Tze Ning - Project Portfoliio Page

## Overview
Personal Module Manager is a software that tracks the number of modular credits, modules taken and modules required
for its user to graduate. The software is for NUS Students who want to plan their modules per semester in an easy way
and it is optimized for those who want to type fast with CLI. It is written in Java.

### Summary of Contributions

#### Code contributed
The following link is my [code contribution](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=chengtzening).

#### Enhancements implemented
In this project, I contributed to the list of available modules, the command to search for modules,
and the 2 delete commands.

##### List of available modules


##### Command to search for modules
The `FindCommand` helps users look up specific modules corresponding to any keywords inputted.
It displays any related modules found in both the module plan of the user and the list of available modules.

##### Command to delete module from semester
The `DeleteFromSemCommand` allows the user to delete a module from his module plan. The user just needs to input the
module code or module name, and the semester it was allocated to.

##### Command to delete module from list of available modules
The `DeleteFromAvailableCommand` allows users to delete a module from the list of available modules.
In the process of doing so, 3 checks are done.
- check that the module exists in the list of available modules
- check that the module is not a prerequisite of any other modules
- check if the module is in the module plan. If it is, the module will be deleted from the module plan as well

#### Contributions to documentation
- Added details on possible expected outputs for different commands to the User Guide

- Viewing module plan
`Okay! Here is your module plan:`


 `Y2S1`

 `1.[✓] ID: CS1010 Name: Programming Methodology | Module Credit: 4 | Sem: Y2S1 | Grade: A+`

 `2.[✗] ID: CS1231 Name: Discrete Structures | Module Credit: 4 | Sem: Y2S1`


 `Y2S2`

 `1.[✗] ID: CS2113 Name: Software Engineering & Object-Oriented Programming | Module Credit: 4 | Sem: Y2S2`

 `2.[✗] ID: IS4241 Name: Social Media Network Analysis | Module Credit: 4 | Sem: Y2S2`

 - Viewing number of modular credits completed

 `You have completed this amount of credits:`

  `4`

#### Contributions to the Developer Guide
- Added explanation and diagrams for the `DeleteFromSemCommand` and `DeleteFromAvailableCommand`

#### Contributions beyond the project team
- Reported bugs in releases from other team and gave suggestions to their code: [bugs and suggestions given](https://github.com/chengTzeNing/ped/issues)