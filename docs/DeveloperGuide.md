# Developer Guide

By: `CS2113T-T12-2` Since: `March 2020`

![Supported Java versions](https://img.shields.io/badge/Java-11-blue.svg) 
![Supported OS](https://img.shields.io/badge/Supported%20OS-Windows|MacOS|Linux-yellow.svg) 

## Table of Contents


- [1. Introduction](#1-introduction)
    * [1.1. Purpose](#11-purpose)
    * [1.2. Scope](#12-scope)
- [2. Setting up](#2-setting-up)
- [3. Design](#3-design)
    * [3.1. Architecture](#31-architecture)
    * [3.2. TextUi Component](#32-textui-component)
    * [3.3. Logic Component](#33-logic-component)
    * [3.4. Model Component](#34-model-component)
    * [3.5. Storage Component](#35-storage-component)
- [4. Implementation](#4-implementation)
    * [4.1. Patient Details Feature](#41-patient-details-feature)
        + [4.1.1. Add Patient Details](#411-add-patient-details)
        + [4.1.2. Edit Patient Details](#412-edit-patient-details)
        + [4.1.3. Delete Patient Details](#413-delete-patient-details)
        + [4.1.4. List Patients](#414-list-patients)
        + [4.1.5. Retrieve Patient Details](#415-retrieve-patient-details)
    * [4.2. Patient Medical Records Feature](#42-patient-medical-records-feature)
        
    * [4.3. Appointment Scheduling Feature](#43-appointment-scheduling-feature)
        + [4.3.1. Add Appointment](#431-add-appointment)
        + [4.3.2. Edit Appointment](#432-edit-appointment)
        + [4.3.3. Delete Appointment](#433-delete-appointment)
        + [4.3.4. Mark Appointment as Done](#434-mark-appointment-as-done)
        + [4.3.5. List Appointments](#435-list-appointments)
        + [4.3.6. Find Appointments of Patient](#436-find-appointments-of-patient)
    * [4.4 Storage](#44-storage)
    * [4.5 Logging](#45-logging)
- [5. Documentation](#5-documentation)
- [6. Testing](#6-testing)
- [7. Useful Links](#7-useful-links)
- [Appendices](#appendices)
    * [Appendix A: Product Scope](#qppendix-a-product-scope)
    * [Appendix B: User Stories](#appendix-b-user-stories)
    * [Appendix C: Non-functional Requirements](#appendix-c-non-functional-requirements)
    * [Appendix D: Glossary](#appendix-d-glossary)
    * [Appendix E: Instructions for Manual Testing](#appendix-e-instructions-for-manual-testing)


## 1. Introduction

### 1.1. Purpose

This document contains the architecture and software design specifications for the application HappyPills.

### 1.2. Scope

This guide is mainly for developers, designers and software engineers that are working on and using HappyPills.

## 2. Setting Up

1. Ensure that you have `Java 11` or later installed in your computer 
2. Click [here](link to be added later?) to download the latest HappyPills JAR File

## 3. Design 

This section provides a high-level overview of HappyPills.

### 3.1. Architecture

![Architecture diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/Nyan-HappyPills/docs/images/ArchitectureDiagram.PNG "Overview of the Application")

The **Architecture diagram** above explains the high-level design of HappyPills.

Main has one class called `HappyPills`.  
It is responsible for:  
- At app launch: Initializes the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components.

The rest of the App consists of four components.

- `UI`: The UI of the App.

- `Logic`: The command executor.

- `Model`: Holds the data of the App in-memory.

- `Storage`: Reads data from and writes data to the text file.


The architecture of HappyPills is broken down into seven main classes:
* `Ui`: This class handles the User Interface of the application.
* `Parser`: This class handles the parsing and handling of user commands.
* `Command`: This class handles all the commands the application has.
* `Storage`: This class reads and writes data to and from text files for future use.
* `Patient`: This class manages the data of data type Patient in memory. 
* `Appointment`: This class manages the data of data type Appointment in memory.
* `MedicalRecords`: This class manages the data of data type MedicalRecord in memory.

### 3.2. TextUi Component

The `TextUi` component: 
* Executes user commands using the command component.
* Listens for changes and outputs messages accordingly from the Command component.

### 3.3. Logic Component

![Logic Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/LogicDiagram.jpg)

The diagram above shows the logic flow of HappyPills.

1. `HappyPills` uses the `Parser` to parse the commands given by the user.
2. This results in a `Command` object being returned which is then executed by `HappyPills`.
3. The command executed will cause a change in the program (e.g. Patient added into program). 
4. The result of the command is then passed back to the `TextUi` to display the message. 

### 3.4. Model Component

![Model Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/ModelDiagram.jpg)

### 3.5. Storage Component

The `Storage` class is called by the `HappyPills` class to handle the loading and saving of patient general details, 
patient medical details and appointment schedule. 

The `loadPatientsFromFile()`, `loadRecordsfromFile()` and `loadAppointmentsFromFile()` methods are used to read saved data from
local files into the current session of HappyPills. 

The `writePatientsToFile()`, `writeRecordsToFile()` and `writeAppointmentsToFile()` methods write the current state of HappyPills
into local files by calling them in the `ExitCommand` class.

## 4. Implementation

This section describes some of the details on how the features of the program has been implemented. This section has been broken 
down into five main features: `Patient Details`, `Patient Medical Records`, `Appointment Scheduling`, `Storage` and `Logging`.

### 4.1. Patient Details Feature

The commands introduced in this feature include : `add`, `edit`, `list`, `delete`, `get`. 
The commands are implemented with HashMap and use NRIC as key and the Patient class as value.
The patient list feature is facilitated by PatientMap class which implements the following operations: 

    - PatientMap #add(Patient patient) — This command adds the patient object into the patient list using the patient’s nric as key.

    - PatientMap #remove(String nric) — This command removes the patient object from the existing patient list. 

    - PatientMap #hasKey(String nric) — This command checks whether the patient object resides in the existing patient list. 
    
    
** Design Considerations ** 

*Aspect: Data Structure of the Patient List* 

        Alternative 1 (current choice): Hash Map
          Pros: Allow faster lookup of patients’ information using the unique identifier (nric)
          Cons: Implementation is harder and may result in bugs if not implemented accurately.
            
        Alternative 2: Array List
          Pros: This would be easier to implement and retrieve the information.
          Cons: When a patient is deleted, all the patients in the patient list need to be checked. 
                This would cause the deletion to be very slow when there is a large number of patients in the list.

#### 4.1.1 Add Patient Details

The user is able to add patients into the program to keep track of the patient's details. 
The command: 

    add patient /ic S7777777Z /n Alice /p 98765432 /d 01/01/2000 /b B+ /a Peanuts
    
will add a patient with `NRIC` as S7777777Z with the following attributes: 
* name: `Alice`
* phone number: `98765432`
* date of birth: `01/01/2000`
* blood type: `B+`
* allergies: `Peanuts`

**Implementation** 

The activity diagram below summarises the process of executing an `add` command.
![Add Patient Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/AddPatientSequenceDiagram.jpg)

The following steps explains the sequence of events: 

1. The user enters `add patient /ic S7777777Z /n Alice /p 98765432 /d 01/01/2000 /b B+ /a Peanuts`.

2. `HappyPills` calls `Parser#parse()` which then calls `PatientParser#parse()`. 

3. `PatientParser#parse()` will call its own method `PatientParser#parseAddCommand()`.

4. `PatientParser#parseAddCommand()` will first validate the attributes and then create an object `AddPatientCommand` 
if the attributes are valid. 

5. `HappyPills` then calls `AddPatientCommand.execute()` to execute the command. 

6. In `AddPatientCommand.execute()`, a patient is added and the display message is returned.

#### 4.1.2 Edit Patient Details

The user is able to edit the details of a patient in the list of patients currently in the program. The command: 

    edit patient S7777777Z /a School
    
will edit the patient's allergies to `School`. 

**Implementation** 

![Edit Patient Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/EditPatientSequenceDiagram.jpg)

The following steps explains the sequence of events: 

1. The user enters `edit patient S7777777Z /a School` 

2. `HappyPills` calls `Parser#parse()` which then calls `PatientParser#parse()`.

3. `PatientParser#parse()` will then create an object `EditPatientCommand`. 

4. `HappyPills` then calls `EditPatientCommand.execute()` method to execute the command. 

5. In `EditPatientCommand.execute()`, the given attribute of the patient is edited and the display message is returned. 

#### 4.1.3. Delete Patient Details 

The user can delete a patient from the list of patients currently in the program. The command: 

    delete patient S7777777Z 
    
will delete the patient with NRIC `S7777777Z`, if found. 

**Implementation** 

![Delete Patient Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/DeletePatientSequenceDiagram.jpg)

The following steps explains the sequence of events: 

1. The user enters `delete patient S7777777Z`. 

2. `HappyPills` calls `Parser#parse()` which then calls `PatientParser#parse()`.

3. `PatientParser#parse()` will then create an object `DeletePatientCommand`. 

4. `HappyPills` then calls `DeletePatientCommand.execute()` to execute the command. 

5. In `DeletePatientCommand.execute()`, the respective patient is deleted and the display message is returned. 

#### 4.1.4. List Patients

The user is able to get a list of all the patients currently in the program.
The command: 
    
    list patient
    
will list all the appointments in the `PatientMap`. 

**Implementation** 

The following steps explains the sequence of events: 

1. The user enters `list patient`.

2. `HappyPills` calls `Parser#parse()` which then calls `PatientParser#parse()`.

3. `APatientParser#parse()` will create an object `ListPatientCommand`.

4. `HappyPills` then calls `ListPatientCommand.execute()` to execute the method. 

5. In `ListPatientCommand.execute()`, it calls `TextUi.getPatientList()` which is then returned as the display message. 

#### 4.1.5. Retrieve Patient Details 

The user can retrieve a patient's details. The command: 

    get patient S7777777Z
    
will retrieve the details of the patient with NRIC `S7777777Z`. 

**Implementation** 

![Get Patient Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/GetPatientSequenceDiagram.jpg)

The following steps explains the sequence of events: 

1. The user enters `get patient S7777777Z` .

2. `HappyPills` calls `Parser#parse()` which then calls `PatientParser#parse()`. 

3. `PatientParser#parse()` will create an object `GetPatientCommand`.

5. `HappyPills` then calls `GetPatientCommand.execute()` to execute the method. 

6. `GetPatientCommand.execute()` calls `TextUi.getPatientSuccessMessage()` if a patient of NRIC `S7777777Z` can be found 
or `TextUi.patientNotExists()` if no patient with the given NRIC exists in the program. 

7. The respective `TextUi` methods will then display the message to the user accordingly.

### 4.2 Patient Medical Records Features 


### 4.3. Appointment Scheduling Feature 

#### 4.3.1. Add Appointment

The user is able to add appointments into the program to manage the appointment schedule. 
The command: 

    add appt /ic S7777777Z /d 01/04/2020 /t 12:00:00 /r Checkup 
    
will add an appointment with `NRIC` as S7777777Z with the following attributes: 
* appointment date: `01/04/2020`
* appointment time: `12:00:00`
* reason for appointment: `Checkup`

An `appointmentId` will also be given when an appointment is successfully added. 

**Implementation** 

![Add Appointment Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/AddAppointmentSequenceDiagram.jpg)

The following steps explains the sequence of events: 

1. The user enters `add appt /ic S7777777Z /d 01/04/2020 /t 12:00:00 /r Checkup` .

2. `HappyPills` calls `Parser#parse()` which then calls `AppointmentParser#parse()`. 

3. `AppointmentParser#parse()` will create an object `AddAppointmentCommand`.

5. `HappyPills` then calls `AddAppointmentCommand.execute()` to execute the method. 

6. In `AddAppointmentCommand.execute()`, an appointment is added and the display message is returned.

#### 4.3.2. Edit Appointment 

#### 4.3.3. Delete Appointment 

The user can delete an appointment from the list of appointments currently in the program. The command: 

    delete appt 1 
    
will delete the appointment with appointment ID `1`, if found. 

**Implementation** 

The following steps explains the sequence of events: 

1. The user enters `delete appt 1`. 

2. `HappyPills` calls `Parser#parse()` which then calls `AppointmentParser#parse()`.

3. `AppointmentParser#parse()` will then create an object `DeleteAppointmentCommand`. 

4. `HappyPills` then calls `DeleteAppointmentCommand.execute()` to execute the command. 

5. In `DeleteAppointmentCommand.execute()`, the respective patient is deleted and the display message is returned. 

#### 4.3.4. Mark Appointment as Done 

#### 4.3.5. List Appointments 

The user is able to get a list of all the appointments currently in the program.
The command: 
    
    list appt
    
will list all the appointments in the `AppointmentMap`. 

**Implementation** 

![List Appointment Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/ListAppointmentSequenceDiagram.jpg)

The following steps explains the sequence of events: 

1. The user enters `list appt`.

2. `HappyPills` calls `Parser#parse()` which then calls `AppointmentParser#parse()`.

3. `AppointmentParser#parse()` will create an object `ListAppointmentCommand`.

4. `HappyPills` then calls `ListAppointmentCommand.execute()` to execute the method. 

5. In `ListAppointmentCommand.execute()`, it calls `TextUi.getAppointmentList()` which is then returned as the display message. 

#### 4.3.6. Find Appointments of Patient

The user is able to find all the appointments a specific patient has.
The command: 
    
    find appt S7777777Z
    
will list all the appointments that the patient with NRIC S7777777Z has. 

**Implementation**

![Find Appointment Sequence Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/FindAppointmentSequenceDiagram.jpg)


### 4.4. Storage

This is an internal feature of the program, implemented to allow users to recover information even after HappyPills is 
closed in the terminal. This is achieved by storing all relevant information in a text file using a structured format.

**Implementation** 

 The current methods implemented in this class and a brief description of each method:
 - `writeAllToFile` — writes the entire list of item to the specified text file.
 - `addSingleItemToFile` — adds a new item as a single string to the end of specified text file.
 - `loadPatientFromFile` — access the patient file and retrieve all information in the file as strings.
 - `parsePatientFileContent` — process line-by-line to create a patient object and add to the shared patient map.
 - `loadAppointmentFromFile` — access the patient file and retrieve all information in the file as strings.
 - `parseAppointmentFileContent` — process line-by-line to create an appointment object and add to the 
                                   shared appointment map.
 
 The following diagram shows how each command interacts with the other classes.
 
 *writeAllToFile*
  
  This storage method : `writeAllToFile`, gets a single formatted string from TextUi, containing details of all the 
  objects in a map. Each class object would have a class method to construct a toSave() string, and 
  each object is stored as a single line within the text file. Hence, the toSave() string is constructed with  
  '|' as a divider, and a newline to indicate the end of the object. The formatted string is a concatenation of all 
  the toSave() strings of the objects in the list. `writeAllToFile` then writes the entire string into the text file, 
  overwriting any existing strings in the file.
  This is implemented for edit and delete commands as they cannot be appended.
  
  ![writing](/docs/images/StorageWriteAll.png)
 
 *addSingleItemToFile*
 
 Each class object would have a class method to construct a toSave() string, and each object is stored as a single line
 within the text file. Hence, the toSave() string is constructed with  '|' as a divider, and a newline to indicate 
 the end of the object.  This storage method : addSingleItemToFile, uses the toSave() string of the object 
 it needs to save, by appending it to the back of the text file. 
 This provides improved performance as compared to using writeAllToFile().
 
 ![saving](/docs/images/StorageSave.png)
 
 *loading and parsing file content to HappyPills*
 
Loading and parsing methods are separated by class types. Each text file in HappyPills represent a single class.
For example, `loadingPatientsFromFile` retrieves the entire string from the patient file and uses 
`parsePatientFileContent` to convert each line into a patient object and adds it back to the patient map. 
`loadAppointmentFromFile` and `parseAppointmentFileContent` does the same with the appointment file.
 
 ![loading](/docs/images/StorageLoad.png)
 
**Design Considerations**

##### Aspect: Saving method
Alternative 1 was chosen as fewer checks means that the program is less prone to exception, especially
so if the checks are confusing to implement. This would put lesser risk on the user experience for now.

        Alternative 1 (current choice): Single object stored into the same file
          Pros: Fewer checks required to identify class of the string, parsing is easier.
          Cons: Delete and update operation may take a long time if the string is very long

        Alternative 2: Store each patient as an individual text file, along with all its relevant class objects. 
                       A list with all the patient’s NRIC will also be stored for referencing.
          Pros: Delete and edit operation on a patient will only affect his/her file, and the referencing list.
          Cons: More checks are requires to identify class of the string

*Aspect: Updating deletion/edit* 

Alternative 1 was chosen for now as the program is relatively new, and is more likely to be subjected to unexpected exceptions.

        Alternative 1 (current choice): Upon every delete/edit operation, update the relevant text file
          Pros: All deletions are updated in the relevant text files immediately and will not be affected by any unexpected termination of the program.
          Cons: In the event that there is a large amount of deletion, it could be time-consuming for the user and memory-intensive on the machine.

        Alternative 2: Saving the delete/edit operation to a list, then process it before the exit of the program
          Pros: Delay deletion time cost so that the use of the program is faster and smoother during time of use.
          Cons: If the program was to terminate unexpectedly, the deletion may not be reflected in the respective files.


### 4.5 Logging 

## 5. Documentation 

## 6. Testing 

## 7. Useful links

* [User Guide](UserGuide-Main.md)
* [About Us](AboutUs.md)

## Appendices 

### Appendix A: Product Scope 

Our product is targeted at users who:
  
   - has a need to record significant number of patients' information
   
   - want to keep patients' information organised
   
   - prefer desktop apps over other types
   
   - can type fast
   
   - prefer typing over mouse input
   
   - prefer CLI apps over GUI apps
  
Value proposition: Note taking application built for doctors to manage notes faster than a typical mouse/GUI driven app

### Appendix B: User Stories 

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Doctor|add Patient's details|view their information in their subsequent visits|
|v1.0|Doctor|have a quick overview of a list of all patients|check for their detailed information|
|v1.0|Doctor|add Patient's details|view their information in their subsequent patientRecords.|
|v1.0|Doctor|have a quick overview of a list of all patients|check for their detailed information.|
|v1.0|Doctor|retrieve my patient's detailed information|check for his/her allergies and provide a more accurate diagnosis|
|v1.0|Doctor|edit a particular patient's information|the most up-to-date details in our patient records|
|v2.0|Doctor|ensure that Patient's details are accurate before I add into the patient list|so that I can rectify mistakes earlier|
|v2.0|Doctor|schedule appointments with my patients|ensure that I will prescribe enough medicine until the next appointment|
|v2.0|Doctor|note down the prescription that I gave my patients|know what side effects are to be expected (based on the patient's current condition)|
|v2.0|Doctor|record down the symptoms of my patients|check for any persistent health condition|
|v2.0|Doctor|add Patient's medical records|view the previous reasons for doctor's visits|
|v2.1|Doctor|?|?|
|v2.1|Doctor|?|?|
|v2.1|Doctor|?|?|
|v2.1|Doctor|?|?|

### Appendix C: Non-functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.

2. A user with above average typing speed should be able to accomplish most of the tasks faster using commands than using the mouse.

3. Should be able to hold up to 1000 patients' information without a noticeable sluggishness in performance for typical usage.

4. Should be able to display large amount of inforamtion quickly. 

### Appendix D: Glossary 

*Mainstream OS*

Windows, Linux, Unix, OS-X

### Appendix E: Instructions for Manual Testing

Given below are instructions to test the application manually.

:information_source: | These instructions only provide a starting point for tests to work on; testers are expected to do more exploratory testing
---------------------|----------------------------------------------------------------------------------------------


## Useful links:
* [User Guide](UserGuide-Main.md)
* [About Us](AboutUs.md)

------

### 4.3. User Prompting 
    
#### 4.2.1 Description

When the user adds a patient’s details, the input could be missing a few compulsory fields. Instead of prompting the user to re-enter the entire input, HappyPills will only ask the user for the missing details.

The user may choose to abort the command because of a lack of knowledge of the compulsory field or provide the requested details. The add command will only be executed when all the compulsory fields are provided. 

#### 4.2.2 Implementation 

Representing a prompt

The prompting mechanism uses prefix to represent individual 

#### 4.2.3 Design Consideration

##### Representing a prompt

The prompting mechanism uses tag such as `/ic[NRIC]` to represent individual field in patient's information. A list of tags is use to pass to the `Parser` which contains:

        - Parser #addCommandParser(String input) — This method break down user input base on tags such as (/ic, /p)

##### Passing the prompts

Given below is an example scenario where the user command has missing compulsory fields

Step 1: The `HappyPills` pass the user's command to `Parser`, which finds one or more missing compulsory fields.

Step 2: The `Parser` call `Parser#parseAddCommand`, which prompt the corresponding missing field back to the user. And wait for user response

Step 3: The new user input was than check again by `Parser#parseAddCommand` and repeat the process until all the compulsory fields is added correctly.

Step 4: `Parser#parseAddCommand` will ask for conformation before passing the correct input into `AddCommand`.

Step 5: `HappyPills` will execute the command.

##### Aspect: Prompt handling method

        Alternative 1 (current choice): The `HappyPill` functions is unaware of prompting. The `Parser` keeps track of the incomplete command and sends back as `addCommand` objects.
          Pros: Decrease coupling between `HappyPill` and `Parser` components
          Cons: `HappyPill` has no way to know if it is currently handling prompting, so it cannot abort prompts, `Parser` return IncorrectCommand to act as abort.
            
        Alternative 2: The `Parser` componetnt keeps track of the incomplete command and throws an exception containing promts to the `HappyPills`.
          Pros: Greater flexibility for `HappyPill` to handle prompt, e.g. aborting
          Cons: A new class is required to keep track of the command entered, rather than simply acting as a bridge between the `Command` and `Parser` sub-component. Increase number of pontential points of failure and decrease maintainability.
          
        Alternative 1 was chosen as it decrease coupling between components. And reduces major failure during v1.
        P.S subject to change in v2.
