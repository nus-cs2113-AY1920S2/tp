# Nyan Wun Paing - Project Portfolio Page

## `PROJECT: HappyPills`

## About the project

Our team consists of 4 software engineering students were tasked to build a basic command line interface desktop 
application for our Software Engineering project.

Our team chose to develop a note management application called HappyPills. Happy Pills is a fast and intuitive 
note-taking app designed specially for doctors. With Happy Pills, you can safely store patients’ details, past medical 
records as well as their future appointment details in a single place.

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

    
**Code contributed:** [RepoSense](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=nyanwunpaing&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false)

**Other contributions:**
- Project management:
    + Handled releases `v2` (1 release) on GitHub
    + Maintaining the issue tracker on GitHub
    + Used my iP project as base for tP.
    + Handle Input-Ouput Test
    + Debugging UI bugs
- Enhancements to existing features:
    + Refactor messages used to a common Messages class in `PatientParser`
    + Refactor the code into logic, ui, model and storage
    + JUnit tests to test on Patient command.
- Community:
    + Reported bugs and suggestions for other teams in the class:  
    [#99](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/99),
    [#100](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/100), 
    [#101](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/101).
    [#105](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/105),
    [#107](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/107),
    [#108](https://github.com/AY1920S2-CS2113-T15-2/tp/issues/108)
  
    + DG reviewed (with non-trivial review comments)  
    [[CS2113T-T13-2] Nuke](https://github.com/nus-cs2113-AY1920S2/tp/pull/16)

### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

[PatientRecord Commands](https://ay1920s2-cs2113t-t12-2.github.io/tp/UserGuide.html#33-patient-medical-records)  

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

[Architecture](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#31-architecture)  
[Section 4.2](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#42-add-features)  
[Section 4.6](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#46-delete-features)  
[Section 4.9](https://ay1920s2-cs2113t-t12-2.github.io/tp/DeveloperGuide.html#49-user-prompting)  
