## Yang Anqi - Project Portfolio Page
### PROJECT: Professor Assistant Console (PAC) 

### Overview
Pac is a event scheduler used by professors which helps to keep track of events. 
Attendance and performance of students can be tracked under each event. 
The user interacts with it using a Command Line Interface (CLI).

### Summary of Contributions
#### Major enhancement 1: added all features of performanceList to events
**What it does**: 
A performanceList can only exist under a Event, ie. Event consist of performanceList.  
A performanceList consist of a list of performance, ie. student's name and result.  
There are 5 main features under performanceList, with similar command `performance feature`, 
with `feature` being one of the following.  
 1. `Add`: add a student's performance to the performance list
 1. `Delete`: delete a student's performance from the performance list by his/her name
 1. `Edit`: edit student's name or result in his/her performance
 1. `Sort`: sort performance list by name or result
 1. `View`: view generated table of students' performance under a event  
 
**Justification**: 
Users will be able to use the basic features to attach an performanceList to a specific event. 
In the future, we will increase type of event, e.g. exam, tut. Even in the current version, 
users are allowed to add these type of events under the general category Event. Thus, we 
attach performance list under a Event to allow professors to manage their students' performance.  
**Highlights**: 
All five features are adopting step-by-step command to provide clear instructions to users. 
All the various commands are handled by the `PerformanceCommandInterpreter`  

#### Major enhancement 2: added table view of lists
**What it does**: 
Allows user to view the different list clearly in a self generated table form.
Below shows an example of viewing an attendance list.  
    
    _________________________________________________________________________________________________
    | index     |  Name of Student                    |  Attendance Status                          |
    |___________|_____________________________________|_____________________________________________|
    | 1         |  John Doe                           |  Present                                    |
    |___________|_____________________________________|_____________________________________________|
    | 2         |  Jodi Doe                           |  Absent                                     |
    |___________|_____________________________________|_____________________________________________|
     
Each box in the table has fixed width, they are not allowed to shorten or lengthen according
to element size. Any string longer than the width of the box containing it, will be cut
to a shorter string. The characters hidden are shown by '...' at the end of the string.  
**Justification**:  
- A table shows the data clearer than a list, as heading, column and rows provide a 
quick analysis of which category does the data belong to.   
- Fixed width: This is because in a Command Line Interface, we do expect a maximum size
of table, i.e. the size of screen. To prevent possible distortion of table, we decided to
make the width fixed.  

**Highlights**: 
The table can be reused to represent different type of information, by changing its headings. 

#### Major enhancement 3: added help feature
**What it does**: 
A help function to the users, that consist of a summary of commands for different features
of Pac.  
**Justification**: 
Users will be able to get immediate help from the app itself when he/she is unclear of 
certain command format.  
**Highlights**: 
It is a levelled help command, ie. user can choose which type of command he/she needs at
the first level. The user will only see a short and sweet help message introduction 
when he/she enters `help`.  

##### Minor enhancement 1: tested studentList feature feasibility before actual implementation
##### Minor enhancement 2: added performanceCommandInterpreter and performanceParser
##### Code contributed: [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=anqi-nus)

##### Other contributions:
**Testing**: 
I have written tests for `PerformanceListTest` and `PerformanceTest`

**Necessary general code enhancements**: 
Improved synchronisation of code by checking method and variable
 names with similar use, to make sure they have similar name and
 abide by naming rules.  
Improved readability of code by SLAP hard during and after coding.  
Refactored code structure at initial stage of tp, to ensure
 pac has common structure in all interpreters

**Update document that are not specifc to a feature**: 
Added target user profile in user guide. 
Added to FAQ in the user guide
Added content page to user guide
Added command summary in the user guide
Added possible console messages and reasons in user guide 

**Maintaining issue tracker**: 
Set up issue tracker
Added issues pertaining to bugs, feature flaws or features related to features mentioned above. 
Linked these issues to my PRs promptly. 

**Documentation**: 
I have written in the User Guide and Developer Guide for all the features/enhancements I have mentioned above, as well 
 as Add, Delete, Clear and View functions for Student feature.  
I have drawn all class diagram and sequence diagram for all the features/enhancements I have mentioned above, as well
as Add, Delete, Clear and View functions for Student feature.  I have drawn class diagram for Student feature.  

**Community**: 
PRs reviewed (with non-trivial comments):  
[#161](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/161)  
[#36](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/36)   