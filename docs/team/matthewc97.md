<H1> matthewc97 - Project Portfolio </H1>
  
# Project: WhenFree

##  Overview

WhenFree is a command-line interface (CLI) application for NUS students to find common free time among them and their friends, using just the [NUSMODS](https://nusmods.com) links to their school timetables. It also offers additional features such as add/delete/edit meetings and saves the data onto hard-disk for future use.

## Summary of Contributions

### Code Contributed
[RepoSense Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=matthewc97)

### Enhancements implemented:
* **Major Enhancement:** Implemented the ability to [schedule a meeting](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#schedule-a-new-meeting-schedule)
    + What it does:
        - Creates a new ```Meeting``` object and adds it to the ```MeetingList```.
    + Justification: 
        - This is one of the basic functions of the application which is to allow the user to schedule a meeting based on the members' schedules.
    + Highlights:
        - Having a ```MeetingList``` allows us to implement further features such as [listing all meetings](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#list-all-meetings-meetings) and [deleting a meeting](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#delete-an-item-delete).
        - Moreover, the information from the ```MeetingList``` can be saved to the hard disk using the ```Storage``` component later.
* **Major Enhancement:** Implemented the ability to [display timetable](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#display-timetable-of-selected-contacts-timetable)
    + What it does:
        - Displays user's timetable using the ```TextUI``` component. 
    + Justification: 
        - During the initial development of the application, the application only provided a list of free slots to the user.
        - This was not user-friendly as the user could not schedule meetings intuitively. 
        - Hence, there was a need to develop a method to display the user's timetable visually.
    + Highlights:
        - This feature allows the user to visualise his/her team's timetable clearly and see which slots are free so that he/she can schedule a meeting easily. 

### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. 
> They showcase my ability to write documentation targeting end-users.

[Features](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#features)  

### Contributions to the Developer Guide
> Given below are sections I contributed to the Developer Guide. 
> They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

* [Model Component](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#24-model-component)
* [Appendix B: User Stories](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#appendix-b-user-stories)  

### Contributions to team-based tasks
* Jointly released V1.0, V2.0 and V2.1 together with teammates, making code enhancements when necessary.
* Look after code quality and ensures adherence to coding standards.
    
### Review/mentoring contributions
* Provided help to my teammates in resolving bugs encountered.
* Review PRs and help resolve merge conflicts caused by working in parallel.

### Contributions beyond the project team
* Reported bugs for [this team](https://github.com/AY1920S2-CS2113-T15-1/tp) during PE-D and found several high severity bugs:
    * [#173](https://github.com/AY1920S2-CS2113-T15-1/tp/issues/173)
    * [#176](https://github.com/AY1920S2-CS2113-T15-1/tp/issues/176)
    * [#179](https://github.com/AY1920S2-CS2113-T15-1/tp/issues/179)
    * [#181](https://github.com/AY1920S2-CS2113-T15-1/tp/issues/181)
