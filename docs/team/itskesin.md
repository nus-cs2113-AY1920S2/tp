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

#### Major enhancement: I implemented `PatientMap` to store all patients' details.
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
    
#### Major enhancement: I implemented the ability for user to `filter` patient records.
  + What it does:
    - The `list pr NRIC` command will display a list of past patient records with index for the searched patient.
    - The `find pr NRIC INDEX` command find a particular patient record with given index.
    - The `edit pr NRIC INDEX [/sym or /diag or /date or /time]` command edit a particular patient record with given
    index and tag.
    - The `delete pr NRIC INDEX` command delete a particular patient record with given index.
  + Justification:
    - In the case that the Patient Record grows, it will be difficult to find records. The `find pr NRIC INDEX` command 
    allows users to easily find and filter the notes that they require. It also allows users to edit wrongly added 
    detail or update the existing records.
  + Highlights:
    - The `list pr NRIC` will provide all the index of the past records. User can always view the date/time of the 
    patient record with the corresponding index.
    - Array indexing was used to store the list of patient record for the particular patient instead of HashMap 
    and Keyword is because patients do not usually visit the doctor unless they are sick. 
    - Arraylist have advantage such as `auto indexing` and resizing of the array making the 
    space of the app low and manageable.
        
**Code contributed:** [Reposense Report](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=itskesin&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false)  

**Other contributions:**
- Project management:
    + Handled `v1.0` and `v2.1` release on GitHub
    + Handled the creation of JAR file
    + Created tags on GitHub Issues
    + Maintaining the issue tracker on GitHub
- Enhancements to existing features:
    + Refactor messages used to a common Messages class in `Ui`
    + Wrote more JUnit tests to test the parser
- Community:
    + Reported bugs and suggestions for other teams in the class:  
    [#170](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/170),
    [#171](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/171),
    [#172](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/172),
    [#173](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/173), 
    [#175](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/175),
    [#178](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/178),
    [#179](https://github.com/AY1920S2-CS2113T-M16-2/tp/issues/179)
  
    + DG reviewed (with non-trivial review comments)  
    [[CS2113-T15-4] Diet Management Assistant](https://github.com/nus-cs2113-AY1920S2/tp/pull/29)

### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

[Main](https://ay1920s2-cs2113t-t12-2.github.io/tp/UserGuide.html#3-features)  
[Patient Commands](https://ay1920s2-cs2113t-t12-2.github.io/tp/UserGuide.html#32-general-patient-information)

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

[Architecture](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#31-architecture)  
[Section 1](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#1-introduction)  
[Section 2](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#2-setting-up)  
[Section 4.1](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#41-data-structure)  
[Section 4.4](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#44-findget-feature)  
[Section 4.5](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#45-edit-features)   
[Appendices](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#appendices)   
