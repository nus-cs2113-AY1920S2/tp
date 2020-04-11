# Siti Nadiah - Project Portfolio Page

## `PROJECT: HappyPills`

## About the project
Our team consists of 4 software engineering students were tasked to build a basic command line interface desktop 
application for our Software Engineering project. 

Our team chose to develop a note management application called HappyPills. HappyPills is specially designed for 
Doctors to record patient's details, past medical records and future appointments. HappyPills allows users
to store all information in a single place and users can always have quick access to the saved information.

### Summary of Contributions
This section shows a summary of my coding, documentation, and other helpful contributions to the team project.

**Major enhancement:** I implemented one `PatientCommand` and three `AppointmentCommand` classes.

+ What it does:

    - The `PatientCommand` class that I implemented is `EditPatientCommand`. 
    
        - `EditPatientCommand` allows the user to edit the `name`, `date of birth`, `phone number`, `blood type`, `allergies` and `remarks` of a patient.
        
    - The three `AppointmentCommand` classes that I implemented is `AddAppointmentCommand`, `ListAppointmentCommand` and 
    `DeleteAppointmentCommand`.
    
        - `AddAppointmentCommand` allows the user to add a schedule appointment into the program.
        
        - `ListAppointmentCommand` displays all the appointments that the user has added.
        
        - `DeleteAppointmentCommand` deletes an appointment according to the patient's NRIC and appointment ID. 
        
+ Justification:

    - The Command classes that I have implemented are essential to the application as it allows the user to 
    add, modify and delete their data. 
    
**Major enhancement:** I implemented the `Appointment` and `AppointmentMap` class and parts of the Appointment-related commands.

+ What it does:
    
    - The Appointment class stores information regarding appointments that the user may want to schedule. This includes 
    the patient's nric, date and time of appointment and the reason for the appointment. 
        
    - The AppointmentMap class uses a HashMap from Java Collection framework to store the Appointment objects. 
        
    - The AppointmentMap maps the AppointmentID of an Appointment object to the object itself.   
        
+ Justification: 
    
    - The data structure (HashMap) was used to look up an Appointment object. The HashMap has O(1) performance for every AppointmentID 
        search, hence it will be faster when there are large number of appointments.
        
+ Highlights: 

    - It was challenging to decide whether HashMaps or ArrayLists would be a more appropriate data structure to use for 
    the list of appointments. 
    
    - We also had to decide whether to use the AppointmentID or Datetime as a key for the HashMap but as our future plans is to 
    make this application multi-user, we decided that using an AppointmentID would be a better choice as many users may have an 
    appointment schedule at the same Datetime.

**Other contributions:**
- Project management:
    + Maintaining the issue tracker on GitHub
- Enhancements to existing features:
    + Enhancement of Help Command
    + Validation of Date and Time
    + Refactoring of the Parser and Command classes into sub-classes
- Community:
    + Reported bugs and suggestions for other teams in the class:  
    [#89](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/89),
    [#90](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/90), 
    [#91](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/91),
    [#92](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/92),
    [#93](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/93),
    [#94](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/94),
    [#95](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/95),
    [#96](https://github.com/AY1920S2-CS2113-T15-4/tp/issues/96)
  
    + DG reviewed (with non-trivial review comments)  
    [[CS2113T-M16-1] Task Scheduler](https://github.com/nus-cs2113-AY1920S2/tp/pull/7)
    
### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

+ [Main](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide.md)  
+ [Appointment Commands](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide.md#34-appointment-scheduling-commands)
+ Reformat UG to make it more consistent

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

+ [Section 3](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#3-design)  
+ [Section 4](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/DeveloperGuide.md#4-Implementation)  
+ Sequence diagrams for Implementation and Appointment-related explanation & diagrams
+ Class diagrams for Design
