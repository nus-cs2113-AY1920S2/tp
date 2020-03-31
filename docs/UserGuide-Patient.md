# HappyPills - User Guide
By: `Team CS2113T-T12-2` Since `March 2020` License: `MIT`

## Table of Content
* [3.2. General Patient Information Commands](#32-general-patient-information)
    + [3.2.1. Add Patient: `add patient`](#321-add-patient-add-patient)
    + [3.2.2. Edit Patient: `edit patient`](#322-edit-patient-edit-patient)
    + [3.2.3. Delete Patient: `delete patient`](#323-delete-patient-delete-patient)
    + [3.2.4. List All Patients: `list patient`](#324-list-all-patients-list-patient)
    + [3.2.5. Retrieve a Patient's Information: `get patient`](#325-retrieve-a-patients-information-get-patient)

 [Return to Main](/docs/UserGuide-Main.md)

### 3.2. General Patient Information

HappyPills can help users to manage their patients' information easily. 

#### 3.2.1. Add Patient: `add patient`

This feature allows you to add more patients into the exisiting patients' list. <br>  
HappyPills will prompt you if there are any missing fields and ask for confirmation before adding the patient's information into the patients's list. You can also add a new patient with the specified parameters in any order.


###### Usage Example:   

    add patient /ic[NRIC] /n[NAME] /p[PHONE_NUMBER] /d[DOB] /b[BLOOD_TYPE]
    
**Example:**

    add patient /ic S9999999Z /n Bob /d 12-11-98 /b A+
    
>***Expected output:***
>
>![MissingInput](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/MissingAddPatientInput.PNG)
>
> Enter `/p 999` to add missing field.

:information_source: | HappyPills will prompt you for missing details that are important.
---------------------|-------------------------------------------------------------------

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
>
> Enter `y` to confirm.

:information_source: | Entering `n` will not save the patients' information when HappyPills prompt you for confirmation.
---------------------|--------------------------------------------------------------------------------------------------

> ***Expected output:***
>    
>  ![SuccessfulAdd](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/SuccessfullyAddedPatientInformation.PNG)

 [Return to Top](#Table-of-Content)

#### 3.2.2. Edit Patient: `edit patient`

Edit information of the patient with the specified NRIC. 

##### Usage example: 

```
edit patient [NRIC] [Options][editedInput]
  
Options:
    -p edit phone number
    -a edit allergies
    -r edit remarks
```

#### 3.2.3. Delete Patient: `delete patient`

Delete a patient as specified by the NRIC. 

##### Usage example: 

    delete patient [NRIC]
    
**Example:**

    delete patient S1234567F

> ***Expected output:***
>
> ![confirmDelete](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/ConfirmationDeletion.PNG)

:information_source: | HappyPills will prompt for confirmation before deleting patient in the patient list.  
---------------------|-------------------------------------------------------------------

> ***Expected output:***
>    
>  ![SuccessfulDelete](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/DeleteSuccessful.PNG)

:heavy_exclamation_mark: | Upon successful deletion, patient's information will not be able to be retrieved again. 
-------------------------|-------------------------------------------------------------------

 [Return to Top](#Table-of-Content)

#### 3.2.4. List All Patients: `list patient`

Displays all the patients in alphabetical order.

##### Usage example: 

    list patient
    
> ***Expected output:***
>
> ![ListPatient](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/ListPatientOutput.PNG)  

 [Return to Top](#Table-of-Content)

#### 3.2.5. Retrieve a Patient's Information: `get patient`

Retrieve details of the patient with the specified NRIC

##### Usage example: 

    get patient [NRIC] 
    
**Example:**

    get patient S9876543Z
    
> ***Expected output:***
>
> ![getpatient](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/GetPatientOutput.PNG)

 [Return to Top](#Table-of-Content)