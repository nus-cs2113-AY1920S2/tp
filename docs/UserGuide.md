# HappyPills - User Guide
By: `Team CS2113T-T12-2` Since `March 2020` License: `MIT`

## Table of Content
- [1. Introduction](#1-introduction)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
    * [3.1. General Commands](#31-general-commands)
        + [3.1.1. View help: `help`](#311-view-help-help)
        + [3.1.2. Exit program: `exit`](312-exit-program-exit)
    * [3.2. General Patient Information Commands](32-general-patient-information-commands)
        + [3.2.1. Add Patient: `add patient`](#321-add-patient-add-patient)
        + [3.2.2. Edit Patient: `edit patient`](#322-edit-patient-edit-patient)
        + [3.2.3. Delete Patient: `delete patient`](#323-delete-patient-delete-patient)
        + [3.2.4. List All Patients: `list patient`](#324-list-all-patients-list-patient)
        + [3.2.5. Retrieve a Patient's Information: `get patient`](#325-retrieve-a-patients-information-get-patient)
    * [3.3. Patient Medical Information Commands](#33-patient-medical-information-commands)
    * [3.4. Appointment Scheduling Commands](#34-appointment-scheduling-commands)
        + [3.4.1. Add Appointment: `add appt`](#341-add-appointment-add-appt)
        + [3.4.2. Edit Appointment: `edit appt`](#342-edit-appointment-edit-appt)
        + [3.4.3. Delete Appointment: `delete appt`](#343-delete-appointment-delete-appt)
        + [3.4.4. Mark Appointment as Done: `done appt`](#344-mark-appointment-as-done-done-appt)
        + [3.4.5. List All Appointments: `list appt`](#345-list-all-appointments-list-appt)
        + [3.4.6. Find Patient's Appointments: `find appt`](#346-find-patients-appointments-find-appt)
- [4. Command Summary](#4-command-summary)
- [5. Useful Links](#5-useful-links)

## 1. Introduction 

HappyPills is a note-taking application that allows doctors to take down notes using Command Line Interface (CLI). 
The application replaces all physical papers and is highly optimised for fast typing users.
If you can type fast, HappyPills can help to manage patients’ records faster than traditional Graphical User Interface (GUI) applications. 
All notes are neatly organised in HappyPills so that all your important information are safely stored. 
Say goodbye to messy desks, illegible handwriting, time-consuming handwritten notes with HappyPills!

## 2. Quick Start
**Installation**
1. Ensure that you have `Java 11` or later installed in your computer 
2. Click [here](link to be added later?) to download the HappyPills JAR File

**For Window users**
1. Open Command Prompt in the directory where the JAR file is located.
2. Run the command `java -jar happypills.jar`

## 3. Features

### 3.1. General Commands 

#### 3.1.1. View help: `help`

Displays a help message with all commands or specific commands with usage examples. 

##### Usage example:

*For general help*

    help

*For specific help*

    help [Command] 
    
**Example:**

`help get`

> ***Expected output:***
>
>     ===================================================
>     To retrieve a patient's information, run the following command:
>     Note: patient details are within the parenthesis [ ]
>       get [NRIC]
>     Example:
>       get S9999999Z
>     The command above will display information regarding the patient with NRIC S9999999Z.
>     ===================================================
>

#### 3.1.2. Exit program: `exit`

Exits the program and ends the current session. 

##### Usage example:

    exit

### 3.2. General Patient Information Commands 

#### 3.2.1. Add Patient: `add patient`

Add a new patient with the specified parameters in any order.
Any missing field will be prompt.
User will require to confirm the input before add the new patient.

##### Usage example:
*To add a new user*   

    add patient /ic[NRIC] /n[NAME] /p[PHONE_NUMBER] /d[DOB] /b[BLOOD_TYPE]
    
**Example:**

    add patient /ic S9999999Z /n Bob /d 12-11-98 /b A+
    
>***Expected output:***
>       =====================================================
>       Please input your missing detail listed below
>       /p[PHONE] only number
>       =====================================================
>
- `/p 999` to add missing field.
> ***Expected output:***
>
>     =====================================================
>         Name : Bob
>         NRIC : S9999999Z
>         Phone Number : 999
>         DOB : 12-11-98
>         Blood Type : A+
>         Allergies : NIL
>         Remarks : NIL
>                                                    (Y/N)?
>     =====================================================
- Type `y` to confirm.
> ***Expected output:***
>    
>       =====================================================
>       Got it! I've added this patient:
>         Name : Bob
>         NRIC : S9999999Z
>         Phone Number : 999
>         DOB : 12-11-98
>         Blood Type : A+
>         Allergies : NIL
>         Remarks : NIL
>       =====================================================
>

#### 3.2.2. Edit Patient: `edit patient`

Edit information of the patient with the specified NRIC. 

##### Usage example: 

```
edit [NRIC] [Options][editedInput]
  
Options:
    -p edit phone number
    -a edit allergies
    -r edit remarks
```

#### 3.2.3. Delete Patient: `delete patient`

Delete a patient as specified by the NRIC. 

##### Usage example: 

    delete patient [NRIC]
    

#### 3.2.4. List All Patients: `list patient`

Displays all the patients in alphabetical order.

##### Usage example: 

    list patient
    
> ***Expected output:***
>
>         ===================================================
>         Alice | S8888888A 
>         Bob   | S9999999Z   
>         ===================================================    

#### 3.2.5. Retrieve a Patient's Information: `get patient`

Retrieve details of the patient with the specified NRIC

##### Usage example: 

    get patient [NRIC] 
    
**Example::**

    get S9999999Z
    
> ***Expected output:***
>
>        ===================================================
>         Here are the patient's details:
>             Name : Bob
>             NRIC : S9999999Z
>             Phone Number : 999
>             DOB : 12-11-98
>             Blood Type : A+
>             Allergies : School
>             Remarks : Had contact with COVID-19 Case200
>         ===================================================

### 3.3. Patient Medical Information Commands 

### 3.4. Appointment Scheduling Commands 

#### 3.4.1. Add Appointment: `add appt`

#### 3.4.2. Edit Appointment: `edit appt`

#### 3.4.3. Delete Appointment: `delete appt`

#### 3.4.4. Mark Appointment as Done: `done appt` 

#### 3.4.5. List All Appointments: `list appt` 

#### 3.4.6. Find Patient's Appointments: `find appt`

## 4. Command Summary

## 5. Useful links:
* [Developer Guide](DeveloperGuide.md)
* [About Us](AboutUs.md)
