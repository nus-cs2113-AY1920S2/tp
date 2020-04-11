# Janice - Project Portfolio Page

## `PROJECT: HappyPills`

## About the project
Our team consists of 4 software engineering students were tasked to build a basic command line interface desktop 
application for our Software Engineering project.

Our team chose to develop a note management application called HappyPills. HappyPills is specially designed for 
Doctors to record patient's details, past medical records and future appointments. HappyPills allows users
to store all information in a single place and users can always have quick access to the saved information.

### Summary of Contributions
This section shows a summary of my coding, documentation, and other helpful contributions to the team project.

#### Major enhancement: Implemented `storage` to store all patients' details in a text file.
  + What it does:
    - The storage add function takes a formatted string from the various objects and appends it to the back of the
    corresponding text file, upon every add operation.
    - The storage write function takes a formatted string from TextUi which concatenates all the strings from the objects
    in a given list and overwrites the content of the corresponding text file, upon every delete and edit operation.
    - Return a map with the corresponding objects obtain from the text file at the start of the program, 
    using the load and parse function in Storage.
  + Justification:
    - Using append for add instead overwriting with the entire map of objects improves the time taken for the program to
    process the add operation, especially when there is a large amount of objects.
  + Highlights:
    - Almost all storage related documentation and code are done by me, unless there are minor issues, of which I would
    provide solution which the team member can help to modify quickly.
    
#### Major enhancement: Implemented three of the appointment commands and made significant changes to the delete.
  + What it does:
    - The `Done appt NRIC ApptID` command will mark an appointment with the specified index for the specific
    patient as done.
    - The `edit appt NRIC ApptID` command edits the value of certain variables in the appointment with the specified
    index for the specific patient.
        - The variable is specified by `/d` for editing date, `/t` for editing time and `r` for editing reason.
        - Validation is done for the date to be entered in `DD/MM/YYY` format.
        - Validation is done for the time to be entered as `HH:mm` format.
    - The `find appt NRIC ` command returns a list of all specified appointments that the patient has, according to the
    appointment list in the patient object.
    - As for the `delete appt NRIC ApptID`, I revised the deletion logic so that it removes from both the map
    and the appointment list in the specified patient object.
    - I also revised the id system for assigning an ID to a new appointment object.
  + Justification:
    - In the case of appointments, the appointment ID is allocated by the program and hence the find function
    is important to carry out the basic edit and delete operations.
    - Added extensive checks to identify cause of error message such as invalid nric, invalid date time or 
    patient/appointment does not exist, so as to improve user feedback.
  + Highlights:
    - The assignment of the ID is done by incrementing the count of the appointment object, along with every creation
    of an appointment object. This prevents the appointment object from changing after every delete operation, which 
    user have to find the appointment's ID using the find function repeatedly.

#### Minor enhancement: Implemented get patient command
+ What it does:
    - The `get patient NRIC` command will return a patient's detailed information.
  + Justification:
    - It is a minor implementation as the retrieval process of the patient is trivial.
    - Added a minor check on the NRIC to check if NRIC is invalid or does not exist, for better user feedback. 

        
**Code contributed:** [Reposense Report](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=janicetyy&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false)  

**Other contributions:**
- Project management:
    + Maintaining the issue tracker on GitHub
- Enhancements to existing features:
    + Refactor TextUi to be parent class of other objectTextUi so that they only return strings that 
    are used by or is related to their corresponding object classes.
    + Wrote JUnit tests to test all features implemented by me except storage.
- Community:
    + Reported bugs and suggestions for other teams in the class:  
        [#165](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/165),
        [#163](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/163), 
        [#162](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/162),
        [#160](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/160),
        [#166](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/166), 
        [#158](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/158),
        [#159](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/159), 
        [#167](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/167), 
        [#164](https://github.com/AY1920S2-CS2113T-M16-1/tp/issues/164)
  
    + DG reviewed (with non-trivial review comments)  
   [[CS2113-T15-4] Diet Management Assistant](https://github.com/nus-cs2113-AY1920S2/tp/pull/29)

### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

[Main](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide.md)  
[Appointment Commands](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide-Appointment.md)

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

[Section 3](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#35-storage-component)    
[Storage](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#44-storage)   
[Appendix E.3](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/DeveloperGuide.md#E4-Appointment-Scheduling-Commands)