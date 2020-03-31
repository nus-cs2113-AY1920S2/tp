# HappyPills - User Guide
By: `Team CS2113T-T12-2` Since `March 2020` License: `MIT`

## Table of Content
* [3.3. Patient Medical Records Commands](#33-patient-medical-information-commands)
    + [3.3.1. Add Patient Records : `add pr`](#331-add-patient-records-add-pr)
    + [3.3.4. List Patient Records : `list pr`](#332-list-patient-records-list-pr)
    + [3.3.2. Find Patient Records : `find pr`](#333-find-patient-records-find-pr)    
    + [3.3.2. Edit Patient Records : `edit pr`](#334-edit-patient-records-edit-pr)
    + [3.3.3. Delete Patient Records : `delete pr`](#335-delete-patient-records-delete-pr)
    
<div align="left"><a href="https://ay1920s2-cs2113t-t12-2.github.io/tp/UserGuide-Main.html"> &#8592; Back to Main </a></div>
<div align="right"><a href="https://ay1920s2-cs2113t-t12-2.github.io/tp/UserGuide-Appointment.html"> &#8594; Go to Appointments </a></div>

### 3.3. Patient Medical Records 

#### 3.3.1. Add Patient Records: `add pr`

Add patient's medical records to the database, to support the diagnosis and to justify the treatment.

##### Usage example:

    add pr /ic NRIC /sym SYMPTOMS /diag DIAGNOSIS /d Date /t Time
    
**Example:**

> ***Expected output:***
>
> ![addPR](images/pr/addpr.PNG "addPR")
>
>

> ***Expected output:***
>
> ![cfmAddPR](images/pr/addprcfm.PNG "cfmAddPR")
>

 [&#8593; Return to Top](#Table-of-Content)

#### 3.3.2. List Patient Records: `list pr`

##### Usage example:

    list pr NRIC

**Example:**

> ***Expected output:***
>
> ![listPR](images/pr/listpr.PNG "list PR output")
>

> ***Expected output:***
>
> ![listPR not found](images/pr/listprfail.PNG "list PR not found")
>

 [&#8593; Return to Top](#Table-of-Content)
 
  
#### 3.3.5. Find Patient Records: `find pr`

##### Usage example:

    find pr NRIC INDEX

**Example:**

> ***Expected output:***
>
>
>![findPR](images/pr/findpr.PNG "find PR output")

> ***Expected output:***
>
>
>![PR not found](images/pr/prEmpty.PNG "PR not found")

 [&#8593; Return to Top](#Table-of-Content)
 
#### 3.3.2. Edit Patient Records: `edit pr`

Edit patient's medical records when there is any error in previous inputs.

##### Usage example:

    edit pr NRIC INDEX /sym [SYMPTOMS] /diag [DIAGNOSIS] /d [DATE] /t [TIME]
    
**Example:**

> ***Expected output:***
>
> ![](images/pr/editprsuccess.PNG "help output")

> ***Expected output:***
>
>
>![](images/pr/prEmpty.PNG "help output")

 [&#8593; Return to Top](#Table-of-Content)
 
#### 3.3.3. Delete Patient Records: `delete pr`

Delete patient's medical records based on the given NRIC and the index of records  . 

##### Usage example: 

    delete pr NRIC INDEX

**Example:**

> ***Expected output:***
>
> ![Delete PR](images/pr/deleteprsuccess.PNG "Delete PR output")

> ***Expected output:***
>
>
>![PR not found](images/pr/prEmpty.PNG "Delete PR not found")

 [&#8593; Return to Top](#Table-of-Content)
