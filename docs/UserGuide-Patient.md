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

 [Return to Top](#Table-of-Content)
 
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

 [Return to Top](#Table-of-Content)
 
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

 [Return to Top](#Table-of-Content)

#### 3.2.4. List All Patients: `list patient`

Displays all the patients in the patient list. 

##### Usage example: 

    list patient
    
> ***Expected output:***
>
> ![ListPatient](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/ListPatientOutput.PNG "List Ouput")  

 [Return to Top](#Table-of-Content)

#### 3.2.5. Retrieve a Patient's Information: `get patient`

Retrieve details of the patient with the specified NRIC.

##### Usage example: 

    get patient NRIC
    
**Example:**

    get patient S9876543Z
    
> ***Expected output:***
>
> ![getpatient](https://github.com/itskesin/tp/blob/kesin-TextUi/docs/images/GetPatientOutput.PNG "Get Ouput")

 [Return to Top](#Table-of-Content)