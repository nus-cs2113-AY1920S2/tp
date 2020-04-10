# HappyPills - User Guide
By: `Team CS2113T-T12-2` Since `March 2020` License: `MIT`

## Table of Content
* [3.4. Appointment Scheduling Commands](#34-appointment-scheduling-commands)
    + [3.4.1. Add Appointment: `add appt`](#341-add-appointment-add-appt)
    + [3.4.2. Edit Appointment: `edit appt`](#342-edit-appointment-edit-appt)
    + [3.4.3. Delete Appointment: `delete appt`](#343-delete-appointment-delete-appt)
    + [3.4.4. Mark Appointment as Done: `done appt`](#344-mark-appointment-as-done-done-appt)
    + [3.4.5. List All Appointments: `list appt`](#345-list-all-appointments-list-appt)
    + [3.4.6. Find Patient's Appointments: `find appt`](#346-find-patients-appointments-find-appt)

<div align="left"><a href="https://ay1920s2-cs2113t-t12-2.github.io/tp/UserGuide.html"> &#8592; Back to Main </a></div>

### 3.4. Appointment Scheduling Commands 

HappyPills helps to store appointment schedules. 

#### 3.4.1. Add Appointment: `add appt`

Add a new appointment for the patient with the specified NRIC. An appointmentID will be allocated to the
patient automatically.
 
##### Format:
 
     add appt /ic NRIC /d DATE /t TIME /r REASON
         
 **Example:**
     
    add appt /ic S1234567F /d 01/02/2020 /t 12:00 /r sick

> ***Expected output:***
>
>![addSuccess](images/UG/APPT/AddAppt.png)
>
>Confirm appointment details are correct by typing "y" or "Y"
>
>![addConfirmSuccess](images/UG/APPT/confirmAddAppt.png)

 [&#8593; Return to Top](#table-of-content)

#### 3.4.2. Edit Appointment: `edit appt`

Edit information of the appointment with the specified appointmentID(apptID).
 The appointment should belong to the patient with the specified NRIC. 
 
##### Format:  
 
     edit appt NRIC apptID [Options][editedInput]
     Options:
         /d edit date
         /t edit time
         /r edit reason
         
 **Example:**
     
    edit appt S1234567F 1 /d 12/02/2020

> ***Expected output:***
>
>![editSuccess](images/UG/APPT/EditAppt.png)

 [&#8593; Return to Top](#table-of-content)

#### 3.4.3. Delete Appointment: `delete appt`

Delete an appointment of a patient as specified by the NRIC and appointmentID(apptID). 

##### Format: 

    delete appt NRIC apptID
    
**Example:**

    delete appt S1234567F 1

> ***Expected output:***
>
> ![deleteSuccess](images/UG/APPT/DeleteAppt.png)

 [&#8593; Return to Top](#table-of-content)

#### 3.4.4. Mark Appointment as Done: `done appt` 

Mark the appointment with the specified appointmentID(apptID) as done if arrived.
 The appointment should belong to the patient with the specified NRIC. 
 
##### Format:
 
     done appt NRIC apptID
         
 **Example:**
     
    done appt S9473069F 4 

> ***Expected output:***
>
>![doneSuccess](images/UG/APPT/DoneAppt.png)

 [&#8593; Return to Top](#table-of-content)

#### 3.4.5. List All Appointments: `list appt` 

List all the existing appointments in the clinic. 
This can be used as a method to check for appointmentID.
 
##### Format: 
 
     list appt
         
 **Example:**

> ***Expected output:***
>
>![ListSuccess](images/UG/APPT/ListAppt.png)

 [&#8593; Return to Top](#table-of-content)

#### 3.4.6. Find Patient's Appointments: `find appt`

Check all appointments that the patient with the specified NRIC has. 
This can be used as a method to check for appointmentID.

##### Format: 
 
     find appt NRIC
         
 **Example:**
     
    find appt S9473069F

> ***Expected output:***
>
>![findSuccess](images/UG/APPT/FindAppt.png)

 [&#8593; Return to Top](#table-of-content)
