# Chan Yong An Benny - Project Portfolio Page

## Overview
Personal Module Manager is a software that tracks the number of modular credits, modules taken and modules required 
for its user to graduate. The software is for NUS Students who want to plan their modules per semester in an easy way 
and it is optimized for those who want to type fast with CLI. It is written in Java.  

## Summary of Contributions

#### Code contributed  
The following link contains all the code I have contributed to Module Manager:
[code contribution](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=bennychanya).  

#### Enhancements implemented  
Added and/or edited the following class:  
- SemesterList ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
  - In order to show all the semesters in sequential order, SemesterList was converted into a priority list instead of
  extending from an Array of SelectedModules
- SemModuleList ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- Grading Enum ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- Ui ([#16](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/16))
- Module ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- NewModule ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- SelectedModule ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))

##### Commands  
Added and/or edited the following command classes:  
- Add To Available Command ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- Add To Sem Command ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- Calculate Cap Command  ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))
- Mark as Done Command ([#38](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/38))([139](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/139))
- Exit Command ([#16](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/16))

### Contributions to documentation: 
* Added Marking as done section ([#53](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/146))

### Contributions to the DG: 
* Added diagram and explanation for the implementation of Calculate Cap ([#53](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/53))

### Contributions to team-based tasks  
- Facilitated discussion for optimal productivity during group meeting

### Contributions beyond the project team   
- bugs reported in other team's products: [Bug reports to CS2113T-T12-1](https://github.com/bennychanya/ped/issues)  


## Contribution to the User Guide
_Given below are sections I contributed to the User Guide. They showcase my ability to write 
documentation targeting end-users._

### 3.4 Marking as done: done
Marks a module as done to show that it has been completed.
This can be done based on a module code or module name.\
If your grade of one module is F or CU, this module will be converted to a failed form.\
If you mark a module as done again, the new grade that has been entered will update your module's grade.


The following Table shows the Grades available for input and the category of the Grades.

|Passing Grades|Failing Grades|
|---|---|
A+|F |
A|CU
A-|
B+|
B|
B-|
C+|
C|
D+|
D|
CS|

#### 3.4.1 Marked with Passing Grades

##### 3.4.1.1 Based on module code
Format:​ `done n/[module name] g/[grade]`

Example:​ `done n/Software Engineering & Object-Oriented Programming g/A+`

Example of expected output:

`Okay, I've marked the module as done!`

##### 3.4.1.2 Based on module name
Format:​ `done id/[module name] g/[grade]`

Example:​ `done id/Software Engineering & Object-Oriented Programming g/A+`

Example of expected output:

`Okay, I've marked the module as done!`

#### 3.4.2 Marked with Failing Grades

A placeholder module will be created to replace the current module being marked with a Failing grade.
This will allow you to add the same module into your module plan when you retake the failed module.  
*Note: "Failed" will be added to the Module ID or name of the original module.*

##### 3.4.2.1 Based on module code
Format: `done id/[module code] g/[grade]`

Example:​ `done id/CS2113 g/F`

Example of expected output:

`Okay, I've marked the module as done!`

When your module plan is displayed using `view` function:

`[✓] Name: CS2113 Failed | Module Credit: 4 | Sem: Y2S1 | Grade: F`

##### 3.4.2.2 Based on module name
Format:​ `done n/[module name] g/[grade]`

Example:​ `done n/Software Engineering & Object-Oriented Programming g/F`

Example of expected output:

`Okay, I've marked the module as done!`

When your module plan is displayed using `view` function, there will be one of the 2 outcome:

1. If your module has an Module ID:

`[✓] Name: CS2113 Failed | Module Credit: 4 | Sem: Y2S1 | Grade: F`

*note: Module ID has been converted to Name. use "delete n/CS2113 Failed" when deleting the placeholder module*

1. If your module does not have an Module ID:

`[✓] Name: Software Engineering & Object-Oriented Programming Failed | Module Credit: 4 | Sem: Y2S1 | Grade: F`

## Contribution to the Developer Guide
_Given below are sections I contributed to the Developer Guide.
They showcase my ability to write technical documentation and the technical depth of my contributions to the project._

### 4.3.1 `Calculate CAP` feature

The Calculate CAP mechanism is executed by `CalculateCapCommand`.  
`CalculateCapCommand` is extended from `Command` and this implementation calculates the CAP using completed 
`SelectedModule` stored in `SemModulesList`.

Given below is the behaviour of the Calculate CAP mechanism at each step:

#### Step 1: 
User launches the application. `SelectedModules` are added to `SemModuleList` through either of the following methods:
1) Imported from `semesterList.csv` using `StorageSemesterList.load()`
2) Added using `add id/ID s/SEMESTER mc/MODULE_CREDIT` command

#### Step 2:
User executes `CAP` command to view his own CAP. The `CAP` commands is parsed through `Parser`, which would then return 
`CalculateCapCommand()`. `CalculateCapCommand.execute()` is then called.

#### Step 3:
`CalculateCapCommand.execute()` will call `CalculateCapCommand.calculateCap(SemesterList semesterList)`, which will
calculate CAP by looking for all the completed `SelectedModules` stored within `SemModuleList`, which are stored within 
`SemesterList`. It will then assign a `double` type ranging from 0.00 to 5.00 to `Person.totalCap`.

#### Step 4:
After the CAP is assigned to `Person.totalCap`, `Person.totalCap` is then called and formatted using `DecimalFormat` 
into a `String`
with a pattern of `#.00`. `Ui.showcap(cap)` is called to display the user's cap using the formatted `String`.

The following diagram shows how the Calculate CAP operation works:
![Calculate CAP feature](https://github.com/bennychanya/tp/blob/master/CalculateCap.png?raw=true)