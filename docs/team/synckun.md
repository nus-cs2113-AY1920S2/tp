<H1> synckun - Project Portfolio </H1>
  
# Project: WhenFree

##  Overview

WhenFree is a command line based application capable of finding common time-slots among team members and schedule meetings accordingly. It also have additional features such as add/delete/edit meetings and can save the data onto hard-disk for future use.

## Summary of Contributions

Code Contribution<br>
Link : [RepoSense Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=synckun)

**1) Capability to differentiate between weeks**

Before this PR, the application can only differentiate the days of the week. The input schedule would be specific to the day of the week, which then repeats every week. After the PR, the app 
* knows the date and time everytime it starts,
* can differentiate between different days of different NUS weeks of different months of different semesters,
* can identify classes each week (since some modules in NUS have classes in selected weeks). Therefore, app can compare and create timetables between people that is accurate to the week.
* App now takes in the dates of interest when scheduling meetings. Thus, I also wrote methods to account for when the start and/or end date crosses into the next month and between the types of NUS weeks which was very tricky.

This enhancement is essential to effectiveness of the app. Since this enhancement made very drastic changes to the way information was stored and handled, code from most classes was analysed and reworked. Click [here](https://github.com/AY1920S2-CS2113T-T12-1/tp/pull/96) to view this PR.

<br/>

**2) Storage Component**

This component allows 
* storing methods to store the list of meetings and each individual's schedule information that includes start and end time of every class, and weeks that the class is held,
* storing and loading to differentiate between main user (the owner of the application) and his/her other contacts
* loading method filters corrupted files,
* capability to create `data` directory, and create and delete meeting and contact .txt files.

This component is essential to application reusability.

<br/>

**3) Extended view of timetable**

Related command: `more`

This command 
* shows the timetable of the current NUS week and the next. This extended view of the timetable will be applied on the most recent variation of the timetable command if the most recent command is a timetable command.
* makes the display of the two weeks consistent by adjusting the truncation of both weeks to match the week that requires the largest space in the timetable display.

This functionality allows schedules to be planned ahead, which increases usability.

<br/>

**4) Truncation of displayed timetable**

Before this enhancement, the timetable shows the week in 30 minute blocks, thus the timetable always has the size of 7(days) by 48(blocks to fill 24 hours).

After this enhancement, if the earliest time that you are occupied in the entire week is at 10am, the week's timetable will only display from 10am. If the latest time that you are occupied in that same week is at 2pm, that week's timetable will also only display until 2pm.

This increases readability and navigability of app.

<br/>

**5) List meetings command**

Related command: `meetings`

This command displays all schedule meetings that are created and stored.

<br/>

**6) Delete contact command**

Related command: `delete <contact name>`

This command deletes the person and their saved schedule from the app and the disk.

<br/>

**Other contributions:**
* Contributions to UG:
	* Created the contents section at the start with hyperlinks
	* Created all the example output pictures
	* Created the "Return to list of Features" and "Return to the top" hyperlinks
	* Wrote the timetable `extended view` section
	* Wrote the `Delete an item` section
	* Wrote some of the FAQ
* **Contributions to DG:**
	* Wrote `2.5. Storage component`
	* Wrote `3.4. Schedule a new meeting`
	* Wrote `3.6 Delete a scheduled meeting`
	* Wrote `3.7 Delete a contact`
	* Wrote `3.8 List all scheduled meetings`
	* Wrote `3.9 [Proposed] Udo/Redo feature`
	* Contributed to `Appendix B: User Stories`
	* Contributed to `Appendix C: Use Cases`
	
* **Contributions to team-based tasks :**
	* Jointly released V1.0, V2.0 and V2.1 together with team mates, taking into account the must have features for each version.
	* Set up issues that needs to be solved for every milestone.
   	* Looked through all the issue feedback from PED dry run. Issues related to user guide are assigned to teammates respectively depending on their roles for this project.
	* Sent the team interest material to value add to what was taught in the week.
    
* **Review/mentoring contributions:**
    * Provided help to team mates facing issues with gradle and checkStyle and how to resolve the errors caused by github CI tool. Ensuring that we abide by the rule of: "Do not merge any pull requests that doesn't pass the continuous integration tool", preventing errors from snowballing.
    * Provided help to teammates that were unsure of UML diagrams and sharing my source code under `/docs/UML source code`.
    * Proof-read the team's documentation and enforced consistency.
    
* **Contributions beyond the project team:**
    * Reported 15 issues during PED by following a methodological approach of Expected Behaviour of application-Current behaviour of application and reproduced detailed description as to how i arrived at the issue with screenshots.
    * Assisted other teams by suggesting useful libraries
