# Samantha Goh - Project Portfolio Page

## Overview
Hospital Administrative Management System (HAMS) is a CLI-based medical facility administration system that is used for 
maintaining medical records. It is written in Java.

### Summary of Contributions
* **Enhancement 1**: added the ability to format date and time 

    * What it does: allows the user input to be formatted into a desired date and time output.
    
    * Justification: This improves the readability of using the application because date and time is now standardised. 
    Furthermore, the user may enter invalid inputs and the application should be able to prompt users with proper 
    error messages.
    
    * Highlights: This implementation was initially planned using the Date format to facilitate the storage of 
    Appointment Date but it meant changing many existing commands/additions so we decided to simplify it.
    
    * Credits: Used SimpleDateFormat to implement the formatting.

* **Enhancement 2**: added the `Appointment` and `Patient` classes.
    
    * What it does: `Appointment` class stores the appointment date, time and patientID while `Patient` class contains
    the the patient's particulars.
    
    * Justification: Allows the encapsulated variables such as name, address, age and contact number to only be
     accessed using setter and getter methods to ensure they are protected. 
     
* **Code contributed**: [[tP Code Dashboard]](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=yukilite&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos)

* **Other contributions**:
    * Project Management: 
        * Fixed team's Gradle errors (Pull requests [#34](https://github.com/AY1920S2-CS2113T-T13-3/tp/pull/34), [#62](https://github.com/AY1920S2-CS2113T-T13-3/tp/pull/62/commits/8b5646490a91ea72e9396ff436ec06945d935afe))
        
    * Enhancements to existing features: 
        * Wrote additional tests for existing features to increase coverage from 60% to 100% (Pull requests [#62](https://github.com/AY1920S2-CS2113T-T13-3/tp/pull/62/commits/8b5646490a91ea72e9396ff436ec06945d935afe), [#176](https://github.com/AY1920S2-CS2113T-T13-3/tp/pull/176)).
    
    * Documentation: 
        * Reorganised content in the User Guide: [#46](https://github.com/AY1920S2-CS2113T-T13-3/tp/pull/46)
    
    * Community:
        * PR Reviews (with non-trivial content): [#1](https://github.com/yukilite/ped/issues/1), [#3](https://github.com/yukilite/ped/issues/3)
        , [#4](https://github.com/yukilite/ped/issues/4)
        * Reported bugs and suggestions for other team in the module: [#5](https://github.com/nus-cs2113-AY1920S2/tp/pull/5)
    
### Contributions to the User Guide

> Provided below are sections I contributed to the User Guide. I took charge of editing the presentation using markdown 
features like tables and bulleting in the first version for submission.

#### 6. FAQ
**Q**: Would my details be captured if I randomize the input order of the keywords?

* `addp \phone 12345678 \address NUS \age 22 \name John Doe`
* `addp \name John Doe \address NUS \ age 22 \ phone 12345678`

**A**: Yes. The system is keyword-sensitive. Regardless of the order given, it stores the information within each command used respectively. Both examples provided above would result in the same information stored. 

**Q**: Why does my patient list does not display any value in the age field?

* `{[Name]: Tommy | [Age]:  | [Address]:  | [Contact Number]: 92331234}`

**A**: The age supplied might not be in the correct format (eg age given is a negative number or as a string).

**Q**: Why am I not able to find anything using finda or findp? 

**A**: Please note that all search values are case-sensitive. For example, `Sam` will not match with `sam`
because of the first letter capitalization. 

**Q**: What is `pid` for in the command `adda`?

**A**: `pid` stands for Patient ID, it is a unique ID tag to each patient. In order to link appointments 
to the patient, the pid tag must be present. 


### Contributions to the Developer Guide


> Provided below are sections I contributed to the Developer Guide. They demonstrate my ability to communicate my 
technical contributions to the project and rationale for technical implementation.


#### 1.2.2. Value Proposition
We are focused on providing a user-friendly application for front-desk administrative support assistants to quickly create, edit
and delete patients' information and appointments. 

|Stakeholders|Value|
|------------|-----|
|Patients|Accelerated waiting process|
|Front-desk administrative staff|Improved organisation in patient and appointment details. Potential automated notification processes |

#### 2.2.1 Record module

The record module consists of 2 classes which represent the patients information and appointment details. 
As a reflection of real world objects, the Patient's class purpose is to store the particulars of a person while the 
Appointment's class is to store the date-time data. 
Thus, the rationale of both classes can be grouped as follows:

>
> As a reflection of real world entities, to create, store, and retrieve relevant information about the object.
>

Following the above purpose, both classes consist of only getter and setter methods. This would ensure a contiguous 
flow in logical executions as these methods can be called whenever necessary.

##### 2.2.1.1 Process of Object Creation
Due to the nature of the above classes containing only getter and setter methods, following how the components interact 
with each other would provide more accuracy in understanding how these classes are called and the role of its 
methods. 
To illustrate, the below example is used:
* editp \index 1 \name Justin \age 69 \Pasir Panjang

![](../images/SD_Patient.PNG)

Upon startup, objects from ui, parser and storage are created. Prompted for user input, Duke receives the "editp"
command which is forwarded to the parser to be interpreted respectively. Once the `EditPatientCommand` object is 
created, it retrieves the patient index to edit the existing patient information from the patientList. 
The `Patient` class is called by its setter method, `setPatientInfo()`, to update the fields as provided by the user. 
This ensures that the encapsulated variables such as age, name, contact number and address are not only enforced but
also protected. 

##### 2.2.1.2 Design Considerations
###### Aspect: Data Type for Appointment's Date and Time

+ Alternative 1 (current choice): Store as a String
    * Pros: 
        - Easier implementation
        - Greater user flexibility
    * Cons:
        -  Cannot sort if needed

+ Alternative 2: Store as Date
    * Pros:
        - Has flexibility to parse or format date using existing methods available for use.
    * Cons:
        - Immutable-value classes mean it is not thread-safe (using Java.util.SimpleDateFormat).

#### 2.2.2 Converter Module
The converter module consists of one class which converts the format of date and time using a custom format defined by
special formatting characters (ie. SimpleDateFormat). This class is primarily used to format a user-input date and time
in the `Appointment` class. As illustrated below, its methods are called during the creation of the `Appointment`
object constructor. 

![](../images/SD_Converter.PNG)

