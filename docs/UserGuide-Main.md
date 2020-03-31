# HappyPills - User Guide
By: `Team CS2113T-T12-2` Since `March 2020` License: `MIT`

## Table of Content
- [1. Introduction](#1-introduction)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
    * [3.1. General Commands](#31-general-commands)
        + [3.1.1. View help: `help`](#311-view-help-help)
        + [3.1.2. Exit program: `exit`](#312-exit-program-exit)        
    * [3.2. General Patient Information Commands](/docs/UserGuide-Patient.md) 
    * [3.3. Patient Medical Records Commands](/docs/UserGuide-Records.md)    
    * [3.4. Appointment Scheduling Commands](/docs/UserGuide-Appointment.md)
    * [3.5. Saving Data](#35-saving-data)
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


### 3.5. Saving Data

## 4. Command Summary

#### General Patient Information

**Command** | **Format**
--------|----------
Add a patient's information | `add patient` /ic [NRIC] /n [NAME] /p [PHONE_NUMBER] /dob [DOB] /b [BLOOD_TYPE] /a [ALLERGIES] /rm [REMARKS]
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
