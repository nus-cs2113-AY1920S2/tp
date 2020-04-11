## Chan Ee Zheng, Benjamin - Project Portfolio Page
### Project: Professor Assistant Console (PAC)

Pac is a event scheduler used by professors which helps to keep track of events. 
The user interacts with it using a Command Line Interface (CLI).

#### Code contributed: [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=benchan911)

#### Major enhancement 1: added the ability to add to studentListCollection

**What it does**: 
    Allows user to create a `studentList` without a need to tag it to a specific `attendanceList` 
    and/or `performanceList`.
    
**Justification**: 
    To speed up the process when a specific name list is used multiple situation for instance in a repeated event with 
    different attendanceList and performanceList. User will be provided an option to import existing events.

**Highlights**:  
    The benefit of having this enhancement is that users will save time especially when adding the same `studentList` 
    to multiple different events.

#### Major enhancement 2: added basic features of attendanceList to events

**What it does**: 
    Allows user to `add` an `attendanceList` to a specific event, `clear` an existing attendanceList and/or 
    `view` an existing attendanceList in a specific event.

**Justification**: 
    Users will be able to use the basic features to attach an attendanceList to a specific event and be able to view 
    or clear the attendanceList.

**Highlights**: 
    All the various commands are handled by the `AttendanceListCommandInterpreter`. The `add` feature 
    for `attendanceList` gives user the opinion to enter using a single line or multi line interaction when creating 
    a new list. This is suitable for the target audience as they have varying expertise level. 
    
**Future enhancement**
    would be providing user the option to add to existing attendanceList or delete a specific attendance.
    
#### Major enhancement 3: added the ability to `modify` attendanceList

**What it does**: 
    Allows user to modify the contents of the `attendanceList` in a specific event such as `find` `attendance` in a 
    specific event or `sort` `attendanceList` either by the students name or status.

**Justification**: 
    Users will be able to use advance features such as sorting by name, sorting by status or find to quickly process
    the information. 

**Highlights**: 
    This enhancement is to allow user to do some data processing of the `attendanceList` so that the data recorded is
    more meaningful to their work. 
    
**Future enhancement** would be providing a full report on each student so that the 
    professor can quickly take appropriate actions such as emailing a particular student to attendance class.    

#### Minor enhancement 1: added the ability to create new studentList via attendanceList

**What it does**:
    User will automatically create a new studentList when they create a new attendanceList instead of using an 
    existing list.

**Justification**:
    Users need not retype the list when creating multi events with the same attendance list.
    
**Highlights**:
    This enhancement will automatically create a new studentList with the name of the event. If there is an existing
    list with the same name, it will append a number to the list name to ensure no duplicate studentList in 
    studentListCollection.

#### Contributions to Documentation
- For the User Guide, I have written features related to Attendance and StudentList.
- For the Developer Guide, I have written features related to Attendance and advance feature for 
  StudentList such as `findStudentList`, `SortStudentListByList`, `SortStudentListByName`. 

##### Contributions to team-based tasks:
- Added issues pertaining to bugs, feature flaws or features related to features mentioned above. 
- Linked these issues to my PRs promptly with their respective labels.
- Contributed to Release management

#### Contributions beyond the project team
- Added target user profile and user stories
- Added onto the FAQ in the user guide
- Added onto command summary in the user guide

##### Review Contributions 
- PRs reviewed (with non-trivial comments)  
[#116](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/116), 
[#164](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/164), 
[#166](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/166) 