# Yap Kesin - Project Portfolio Page

## `PROJECT: HappyPills`

## About the project
Our team consists of 4 software engineering students were tasked to build a basic command line interface desktop 
application for our Software Engineering project.

Our team chose to develop a note management application called HappyPills. HappyPills is specially designed for 
Doctors to record patient's details, past medical records and future appointments. HappyPills allows users
to store all information in a single place and users can always have quick access to the saved information.

### Summary of Contributions
This section shows a summary of my coding, documentation, and other helpful contributions to the team project.

**Major enhancement:** I implemented `PatientMap` to store all patients' details.
  + What it does:
    - The PatientMap uses HashMap from Java Collection framework to store the patient objects.
    - The PatientMap maps the Patient object to the Patient's NRIC. Each Patient's NRIC (key) is associated with a 
    patient object(value) which consists of the patient's details.
  + Justification:
    - The data structure (HashMap) was used to look up an NRIC quickly, the HashMap has O(1) performance for every NRIC 
    search, it will be faster when there are large number of patients' information.
    - HashMap will also helps to ensure that there will be no duplicated NRICs.
  + Highlights:
    - There were challenging design decisions involved in the selection of the underlying data structure because the 
    enhancement affects all the patients commands.  The implementation was also challenging as required changes 
    to the existing patient commands.
    - To ensure our application maintains its robustness under extremely heavy load conditions, I did in-depth analysis 
    of different design alternatives.
    
**Major enhancement:** I implemented the ability for user to filter Patient Record
  + What it does:
    - The `list pr NRIC` command list all the past patient records with index.
    - The `find pr NRIC INDEX` command find a particular patient record with given index.
    - The `edit pr NRIC INDEX [/sym or /diag or /date or /time]` command edit a particular patient record with given
    index and tag.
    - The `delete pr NRIC INDEX` command delete a particular patient record with given index.
  + Justification:
    - In the case that the Patient Record grows, it will be difficult to find records. The `find pr NRIC INDEX` command 
    allows users to easily find and filter the notes that they require. It also allow to edit wrongly added detail or
    update the existing record.
    
  + Highlights:
    - The `list pr NRIC` will provide all the index of the past records. User are not require to provide their index,
    and can always view the date/time of the patient record with the corresponding index.
    - An in-depth analysis of design of using array indexing instead of hashmap and keyword is because
    the number of time a particular patient visit the clinic are usually low. Arraylist have advantage such as
    `auto indexing` and resizing of the array making the `space of the app low` and manageable.
    
// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> i prefer ^
**Major enhancement:** I implemented `FindPatientRecord` to search for a particular Patient Record using NRIC and index.
  + What it does:
    - `find pr NRIC INDEX` uses NRIC as key to find the ArrayList of past Patient Record as value.
    - `find pr NRIC INDEX` uses INDEX to find the particular Patient Record that the user is searching for.
  + Justification:
    - This feature benefits users because they can easily search for a particular Patient Record in the list of Records.
  + Highlights:
    - The `list pr NRIC` will provide all the index of the past records. User are not require to provide their index,
    and can always view the date/time of the patient record with the corresponding index.
    - An in-depth analysis of design of using array indexing instead of hashmap and keyword is because
    the number of time a particular patient visit the clinic are usually low. 
    Arraylist have advantage such as`auto indexing` and resizing of the array 
    making the `space of the app low` and manageable.
    
    
**Code contributed:** Please click these links to see a sample of my code: 
[PatientMap](), [FindPatientRecord](), [DeletePatientRecord]()  

**Other contributions:**
- Project management:
    + Handled releases `v1.0 - v2.1` (3 releases) on GitHub
    + Created tags on GitHub Issues
    + Maintaining the issue tracker on GitHub
- Enhancements to existing features:
    + Refactor messages used to a common Messages class in `Ui`
    + Wrote more JUnit tests to test new and old components of the UI ??? 
- Community:
    + Reported bugs and suggestions for other teams in the class:  
    [#170](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/170),
    [#173](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/173), 
    [#179](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/179)
  
    + DG reviewed (with non-trivial review comments)  
    [[CS2113-T15-4] Diet Management Assistant](https://github.com/nus-cs2113-AY1920S2/tp/pull/29)

### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

[Main](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide.md)  
[Patient Commands](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide-Patient.md)

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

[Architecture](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#31-architecture)  
[Section 4.1 to 4.1.1](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/DeveloperGuide.md#41-patient-details-feature)  
[Appendices](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/DeveloperGuide.md#43-user-prompting)  