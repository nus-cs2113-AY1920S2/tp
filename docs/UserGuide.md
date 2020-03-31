# HappyPills - User Guide
By: `Team CS2113T-T12-2` Since `March 2020` License: `MIT`

## Table of Content
- [1. Introduction](#1-introduction)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
    * [3.1. General Commands](#31-general-commands)
        + [3.1.1. View help: `help`](#311-view-help-help)
        + [3.1.2. Exit program: `exit`](#312-exit-program-exit)
        
    * [3.2. General Patient Information](#32-general-patient-information)
        + [3.2.1. Add Patient: `add patient`](#321-add-patient-add-patient)
        + [3.2.2. Edit Patient: `edit patient`](#322-edit-patient-edit-patient)
        + [3.2.3. Delete Patient: `delete patient`](#323-delete-patient-delete-patient)
        + [3.2.4. List All Patients: `list patient`](#324-list-all-patients-list-patient)
        + [3.2.5. Retrieve a Patient's Information: `get patient`](#325-retrieve-a-patients-information-get-patient)
        
    * [3.3. Patient Medical Records](#33-patient-medical-information-commands)
        + [3.3.1. Add Patient Records : `add pr`](#331-add-patient-records-add-pr)
        + [3.3.2. Edit Patient Records : `edit pr`](#332-edit-patient-records-edit-pr)
        + [3.3.3. Delete Patient Records : `delete pr`](#333-delete-patient-records-delete-pr)
        + [3.3.4. List Patient Records : `list pr`](#334-list-patient-records-list-pr)
        + [3.3.5. Find Patient Records : `find pr`](#335-find-patient-records-find-pr)
    
    * [3.4. Appointments](#34-appointment-scheduling-commands)
        + [3.4.1. Add Appointment: `add appt`](#341-add-appointment-add-appt)
        + [3.4.2. Edit Appointment: `edit appt`](#342-edit-appointment-edit-appt)
        + [3.4.3. Delete Appointment: `delete appt`](#343-delete-appointment-delete-appt)
        + [3.4.4. Mark Appointment as Done: `done appt`](#344-mark-appointment-as-done-done-appt)
        + [3.4.5. List All Appointments: `list appt`](#345-list-all-appointments-list-appt)
        + [3.4.6. Find Patient's Appointments: `find appt`](#346-find-patients-appointments-find-appt)
      
     * [3.5. Saving Data](#)
- [4. Command Summary](#4-command-summary)
- [5. Useful Links](#5-useful-links)

## 1. Introduction 

HappyPills is a note-taking application that allows doctors to take down notes using Command Line Interface (CLI).
  
The application replaces all physical papers and is highly optimised for fast typing users.    
If you can type fast, HappyPills can help to manage patients’ records **faster** than traditional Graphical User Interface (GUI) applications.  
All notes are **neatly organised** in HappyPills so that all your important information are **safely stored**.  
Say **HELLO** to neater desks and time-saving electronic notes with HappyPills! :relaxed:

## 2. Quick Start
**Installation**
1. Ensure that you have `Java 11` installed in your computer 
2. Click [here](https://github.com/AY1920S2-CS2113T-T12-2/tp/releases) to download the HappyPills JAR File
3. Copy the file to the folder you want to use as your home folder for HappyPills.

**For all users**
1. Open Command Prompt in the directory where the JAR file is located.
2. Run the command `java -jar happypills.jar`.
![Start of Application](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/StartOfApplication.PNG "Start of App")
3. When you start the application for the first time, you can type `help` to check all the available commands.
4. Refer to [here](#3-features) for detailed instructions on how to use each command.

## 3. Features

### Command Format
- Words in `UPPER_CASE` are the parameters to be supplied by you **e.g.** in `add patient \ic NRIC`, `NRIC` is the input required from you.
- In case a tag is used multiple times in a command then its first instance will be taken and the remaining instances will be treated as invalid input.
- Items in square brackets are optional **e.g.** `/ic NRIC /a [ALLERGIES]` can be used as `/ic S1234567F` or as `/ic S1234567F /a Dust`
- Parameters can be in any order **e.g.** if the command requires `/n NAME /ic NRIC` or `/ic NRIC /n NAME `
- All commands are case insensitive **e.g.** `help` or `HELP` or `HeLp` will display all the commands and their usage.

### Legend For Tags
- `/n` → Patient's Name

- `/ic` → Patient's NRIC

- `/p` → Patient's Phone Number

- `/dob` → Patient's Date of birth

- `/b` → Patient's Blood type

- `/a` → Patient's Allergies

- `/rm` → Patient's Remarks

- `/t` → Time of the Patient's appointment

- `/d` → Date of the Patient's appointment

- `/r` → Reason for the Patient's appointment

- `/sym` → Patient's Symptoms

- `/diag` → Patient's Diagnosis

### 3.1. General Commands 

#### 3.1.1. View Help: `help`

Displays the list of commands and their syntax.

###### Usage example:

Format: `help`

>***Expected output:***
> 
> ![help](https://github.com/NyanWunPaing/tp/blob/Nyan-HappyPills/docs/images/HelpOutput.PNG "help output")

:information_source: | The help command is split into four different sections `(highlighted by the boxes)`.
---------------------|--------------------------------------------------------------------------------------------------

#### 3.1.2. Exit program: `exit`

Exits the program and ends the current session. 

##### Usage example:

Format: `exit`

>***Expected output:***
> 
> ![Exit](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/Exit.PNG "Exit Ouput")

### 3.2. General Patient Information

HappyPills can help users to manage their patients' information easily. 

#### 3.2.1. Add Patient: `add patient`

This feature allows you to add more patients into the existing patients' list.
 
HappyPills will prompt you if there are any missing fields and ask for confirmation before adding the patient's information into the patients's list.  
You can also add a new patient with the specified parameters in any order.


###### Usage Example:   

    add patient /ic NRIC /n NAME /p PHONE_NUMBER /dob DOB /b BLOOD_TYPE /a[ALLERGIES] /rm[REMARKS]
    
**Example:**

    add patient /ic S9999999Z /n Bob /dob 12-11-98 /b A+
    
>***Expected output:***
>
>![MissingInput](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/MissingAddPatientInput.PNG "Missing Add Ouput")
>
> Enter `/p 999` to add missing field.

:information_source: | HappyPills will prompt you for missing details that are important.
---------------------|-------------------------------------------------------------------

> ***Expected output:***
>
>   ![CheckConfirmAdd](https://github.com/NyanWunPaing/tp/blob/Nyan-HappyPills/docs/images/addConfirm.PNG "Add Confirmation Ouput")
>
> Enter `y` to confirm.

:information_source: | Entering `n` will not save the patients' information when HappyPills prompt you for confirmation.
---------------------|--------------------------------------------------------------------------------------------------

> ***Expected output:***
>    
>  ![SuccessfulAdd](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/SuccessfullyAddedPatientInformation.PNG "Successfully Added Ouput")

#### 3.2.2. Edit Patient: `edit patient`

Edit information of the patient with the specified NRIC. 

##### Usage example: 
 
    edit patient NRIC /n[NAME] /p[PHONE_NUMBER] /dob[DOB] /b[BLOOD_TYPE] /a[ALLERGIES] /rm[REMARKS]

**Example:**

    edit patient S0618 /p9111

> ***Expected output:***
>
> ![editPatientOutput](https://github.com/NyanWunPaing/tp/blob/Nyan-HappyPills/docs/images/EditCommandOutput.PNG "Edit Patient Ouput")

:information_source: | NRIC cannot be edited because it is what uniquely identifies the patient.
---------------------|--------------------------------------------------------------------------

#### 3.2.3. Delete Patient: `delete patient`

Delete a patient as specified by the NRIC. 

##### Usage example: 

    delete patient NRIC
    
**Example:**

    delete patient S1234567F

> ***Expected output:***
>
> ![confirmDelete](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/ConfirmationDeletion.PNG "Delete Confirmation Ouput")
>
> Enter `y` to confirm.

:information_source: | HappyPills will prompt for confirmation before deleting patient in the patient list.  
---------------------|-------------------------------------------------------------------

> ***Expected output:***
>    
>  ![SuccessfulDelete](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/DeleteSuccessful.PNG "Successfully Deleted Ouput")

:heavy_exclamation_mark: | Upon successful deletion, patient's information will not be able to be retrieved again. 
-------------------------|-------------------------------------------------------------------

#### 3.2.4. List All Patients: `list patient`

Displays all the patients in the patient list. 

##### Usage example: 

    list patient
    
> ***Expected output:***
>
> ![ListPatient](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/ListPatientOutput.PNG "List Ouput")  

#### 3.2.5. Retrieve a Patient's Information: `get patient`

Retrieve details of the patient with the specified NRIC.

##### Usage example: 

    get patient NRIC
    
**Example:**

    get patient S9876543Z
    
> ***Expected output:***
>
> ![getpatient](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/GetPatientOutput.PNG "Get Ouput")

### 3.3. Patient Medical Records 

#### 3.3.1. Add Patient Records: `add pr`

Add patient's medical records to the database, to support the diagnosis and to justify the treatment.

##### Usage example:

    add pr /ic NRIC /sym SYMPTOMS /diag DIAGNOSIS
    
**Example:**

> ***Expected output:***
>
> ![]()
>

#### 3.3.2. Edit Patient Records: `edit pr`

Edit patient's medical records when there is any error in previous inputs.

##### Usage example:

    edit pr NRIC INDEX /sym [SYMPTOMS] /diag [DIAGNOSIS] /d [DATE] /t [TIME]
    
**Example:**

> ***Expected output:***
>
> ![]()
>

#### 3.3.3. Delete Patient Records: `delete pr`

Delete patient's medical records based on the given NRIC and the index of records  . 

##### Usage example: 

    delete pr NRIC INDEX

**Example:**

> ***Expected output:***
>
> ![]()
>
#### 3.3.4. List Patient Records: `list pr`

##### Usage example:

    list pr NRIC

**Example:**

> ***Expected output:***
>
> ![]()
>
#### 3.3.5. Find Patient Records: `find pr`

##### Usage example:

    find pr NRIC INDEX

**Example:**

> ***Expected output:***
>
>
>![]()

### 3.4. Appointment Scheduling Commands 

#### 3.4.1. Add Appointment: `add appt`

#### 3.4.2. Edit Appointment: `edit appt`

#### 3.4.3. Delete Appointment: `delete appt`

#### 3.4.4. Mark Appointment as Done: `done appt` 

#### 3.4.5. List All Appointments: `list appt` 

#### 3.4.6. Find Patient's Appointments: `find appt`

### 3.5. Saving Data


## 4. Command Summary

#### General Patient Information

**Command** | **Format**
--------|----------
Add a patient's information | `add patient` /ic NRIC /n NAME /p PHONE_NUMBER /dob DOB /b BLOOD_TYPE /a [ALLERGIES] /rm [REMARKS]
Edit a patient's information |  `edit patient` NRIC /n[NAME] /p[PHONE_NUMBER] /dob[DOB] /b[BLOOD_TYPE] /a[ALLERGIES] /rm[REMARKS]
list all patients | `list patient`
Retrieve a patient's information | `get patient` NRIC

#### Patient Medical Records

**Command** | **Format**
--------|----------
 Add a Patient Record |`add pr` /ic NRIC /sym SYMPTOMS /diag DIAGNOSIS
 Edit a Patient Record |`edit pr`
 Delete a Patient Record | `delete pr`
 List all Patient Records |`list pr`
 Find a Patient Record|`find pr`

#### Appointments

**Command** | **Format**
--------|----------
Add an appointment | `add appt`
Edit an appointment | `edit appt`
Delete an appointment | `delete appt`
Mark an appointment as done | `done appt`
List all appointments | `list appt`
Find an appointment| `find appt`

## 5. Useful links:
* [Developer Guide](DeveloperGuide.md)
* [About Us](AboutUs.md)
