# Chan Ee Zheng Benjamin - Project Portfolio Page
## PROJECT: Professor Assistant Console (PAC)

## Overview
Pac is a event scheduler used by professors which helps to keep track of events.
The user interacts with it using a Command Line Interface (CLI).

## Summary of Contributions

### Major enhancement 1: added the ability to create studentList and store them in studentListCollection to allow user to import existing studentList

**What it does**: 
    Allows user to create a name list for the student without a need to tag it to a specific attendanceList and/or performanceList
    
**Justification**: 
    To speed up the process when a specific name list is used multiple situation for instance in a repeated event with 
    different attendanceList and performanceList

**Highlights**: 


### Major enhancement 2: added basic features of attendanceList to events
**What it does**: 
    Allows user to `add` an attendanceList to a specific event, `clear` an existing attendanceList and/or 
    `view` an existing attendanceList in a specific event.

**Justification**: 
    Users will be able to use the basic features to attach an attendanceList to a specific event 

**Highlights**: 
    All the various commands are handled by the `AttendanceListCommandInterpreter`

### Major enhancement 3: added the ability to `modify` attendanceList
**What it does**: 
    Allows user to modify the contents of the attendanceList in a specific event.

**Justification**: 
    Users will be able to use advance features such as sorting by name, sorting by status or find to quickly process
    the information. 

**Highlights**: 
    All the various commands are handled by the `AttendanceListCommandInterpreter`
    
### Minor enhancement 1: 
### Minor enhancement 2: 
### Minor enhancement 3:
### Code contributed: [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=benchan911)

### Other contributions:

#### Testing :

#### Necessary general code enhancements
- Improved readability of code by having specific naming.

#### Update document that are not specific to a feature 
- Added target user profile and starting up instructions in user guide
- Added to FAQ in the user guide
- Added instructions for manual testing in the developer guide
- Added command summary in the user guide
- Added possible console messages and reasons in user guide 

#### Maintaining issue tracker
- Added issues pertaining to bugs, feature flaws or features related to features mentioned above. 
- Linked these issues to my PRs promptly. 
- 

### Documentation
- I have written in the User Guide and Developer Guide features related to Attendance and advance feature for 
  StudentList. 

### Community 
- PRs reviewed (with non-trivial comments)  
[#116](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/116), 
[#164](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/164), 
[#166](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/166) 