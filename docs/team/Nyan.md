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

*Major enhancement:* I added `User Prompting` for commands in HappyPills.
>   + What it does: HappyPills can detect if a command entered by the user is missing some information or the 
>   information given is in incorrect format. For example, a user may forget to indicate the phone number of a patient
>   which is deemed as a necessary detail. In this case, HappyPills will prompt the user for the missing/incorrect 
>   details.
>
>   + Justification: This feature benefits inexperienced users by allowing them to easily fix mistakes without having to
>   re-enter the entire command. It also ensure that all users are able to know exactly which part of the entered input 
>   is incorrect.
>
>   + Highlights: This feature increase user-friendliness, as I included messages to tell the user the reason 
>   behind the prompting, so that users do not have to figure out the problem themselves.
>   For future commands, this feature is easily extensible. 
   
*Major enhancement:* I added `Add Command` feature.
>   + What it does: 
>       - The `add patient` command stores the patient's details in HappyPills.
>       - The `add pr` command stores the patient's medical records in HappyPills.
>
>   + Justification: This feature allows the user to add information into HappyPills. This features solves the user need
>     to manage all his information in a single location.
>
>   + Highlights: This feature will prompt for User Confirmation before adding the information into HappyPills. 
>     Information that is going to be added will be listed down for the user. The `add` command is simple to use and
>     user-friendly.

*Major enhancement:* I implemented parser.
>   + What it does: 
>       - The parser makes sense of input to execute the correct command.
>
>   + Justification: This feature allows the to user input to be broken down into sub command.
>
>   + Highlights: This feature allow the user to input tags, such as "/d,/t,etc", to come in different order
>   allowing flexibility to the user

    
**Code contributed:** Please click these links to see a sample of my code: 
[addPatient](),[addPatientRecord](), [PatientParser](), [PatientRecordParser]()  

**Other contributions:**
- Project management:
    + Handled releases `v1.0 - v2.1` (3 releases) on GitHub
    + Created tags on GitHub Issues
    + Maintaining the issue tracker on GitHub
- Enhancements to existing features:
    + Refactor messages used to a common Messages class in `PatientParser`
    + JUnit tests to test on Patient command.
- Community:
    + Reported bugs and suggestions for other teams in the class:  
    [#99](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/99),
    [#100](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/100), 
    [#105](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/105)
  
    + DG reviewed (with non-trivial review comments)  
    [[CS2113T-T13-2] Nuke](https://github.com/nus-cs2113-AY1920S2/tp/pull/16)

### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

[Main](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide.md)  
[Patient Commands](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/UserGuide-Patient.md)

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

[Architecture](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#31-architecture)  
[Section 4.5 to 4.5.3](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/DeveloperGuide.md#41-patient-details-feature)  
[Appendices](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/DeveloperGuide.md#43-user-prompting)  