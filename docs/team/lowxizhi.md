# Low Xi Zhi - Project Portfolio Page

## Overview
WhenFree is a Command Line scheduler chatbot for NUS students to find common free time among them and their friends, using just the NUSMODS links to their school timetable.

## Summary of Contributions
* [**Code contributed**](https://github.com/AY1920S2-CS2113T-T12-1/tp/pulls?q=is%3Apr+author%3Alowxizhi)
* **Enhancement 1**: added feature **Display timetable of selected contacts**
    * What it does: Generates and displays a combined timetable from several contacts’ timetable. 
    * Justification: This feature is key to the functionality of WhenFree as it allows the user to see his/her common free slots with other contacts.
* **Enhancement 2**: added feature **Edit a contact's timetable**
    * What it does: Overwrites a contact’s timetable to “busy” or “free” for a time slot.
    * Justification: This feature enhances the usability of WhenFree as it allows the user to make amendments on top of 
    their NUSMODS timetable (eg. empty tutorial slots, additional consultation sessions), allowing them to find free time slots more accurately.
    * Highlights: This feature prevents overwriting any meetings scheduled.
* **UG documentation**:
    * Contributed to the [Introduction](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#introduction) and [Quick Start](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#quick-start) sections. 
    * Wrote the main description as well as detailed notes for these features: [List all contacts](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#list-all-contacts-contacts), 
    [Display timetable of selected contacts](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#display-timetable-of-selected-contacts-timetable),
    [Schedule a new meeting](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#schedule-a-new-meeting-schedule),
    [List all meetings](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#list-all-meetings-meetings),
    [Edit a contact's timetable](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#edit-a-contacts-timetable-edit)
    * Contributed to [FAQ](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#faq) for obtaining NUSMODS link from NUSMODS website.
    * Contributed to [Command Summary](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/UserGuide.md#command-summary). 
* **DG documentation**:
    * Framework: Made the framework for the [Design](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#2-design) and [Implementation](3. Implementation) sections.
    * Design Section: Wrote the [UI component](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#22-ui-component) and the logic.schedulelogic component(of [Logic component](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#23-logic-component)), including class diagram Fig 5. 
    * Implementation Section: Wrote the [Display timetable of selected contacts](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#33-display-timetable-of-selected-contacts) and [Edit a contact's timetable features](https://github.com/AY1920S2-CS2113T-T12-1/tp/blob/master/docs/DeveloperGuide.md#35-edit-a-contacts-timetable), including sequence diagrams  Fig 12. Fig 14. Fig 15. 
    * Edited architecture diagram and class diagrams to be neat and consistent (labelling, colour-coding, aligning), captioned all diagrams, edited text referring to the diagrams to be consistent and edited the sentence structure to ensure flow of the Design and Implementation sections.
* **Team-based tasks**:
    * Refactor all commands into a single CommandHandler class.
    * Refactor overlapping code in “Schedule a new meeting” and “Edit a contact's timetable” features.
    * Raised issues to bugs found in team members’ code.
    * Allocating sections of UG and DG to be written by team members.
* **Review/mentoring**:
    * Suggested to a team member a better implementation of his "Delete a contact" feature, which he eventually used.
    * Pointed a team member to resources to write assertions.
    * Pointed out errors in team members’ class diagrams.
* **Contributions beyond the project team**:
    * Asked a [question in the forum](https://github.com/nus-cs2113-AY1920S2/forum/issues/78) clarifying a module’s concept 
    * Raised issues to bugs in other team’s project. [Example 1](https://github.com/lowxizhi/ped/issues/10) [Example 2](https://github.com/lowxizhi/ped/issues/5)

## Example Contributions to the User Guide:
### Edit a contact's timetable: `edit`
There are 2 use cases illustrated below: to edit a contact's timetable to **"busy"** for a specified time slot, and
to edit a contact's timetable to **"free"** for a specified time slot.
* You can edit the contact's timetable for only the current week and the next week, for which your timetable can be displayed 
with the [`timetable <Contact Index>`](#display-timetable-of-selected-contacts-timetable) and `more` commands
* Hence, `<Start Date>` and `<End Date>` represents the date number you wish to edit the timetable for, within the current 
week and the next week, from the current date onwards. For example, if the date today is 27 April:
    * You can edit the timetable on 29 April this week with `<Start Date>` and `<End Date>` as `29`. 
    * You can edit the timetable on 6 May next week with `<Start Date>` and `<End Date>` as `6`.
    * You will not be able to edit the timetable with `<Start Date>` and `<End Date>` as `26`. Even though 26 April is within
    this week, the date has passed. The future date 26 May is also not within the current week and the next week.
* `<Start Time>` and `<End Time>` should be in the 24-hour HH:MM format. For example, the time 3pm should be represented
as `15:00`.
> :bulb: You can check `<Contact Index>` of the contact whose timetable you wish to edit, by listing all contacts using 
[`contacts`](#list-all-contacts-contacts).


**Use case 1:** Edits a contact's timetable to be **"busy"** for a specified time slot. You need to key in `<Contact Index>`
of the contact, and specify the time slot to be marked **"busy"** with `<Start Date>` `<Start Time>` `<End Date>` `<End Time>`.

Format: 
    
    edit busy <Contact Index> <Start Date> <Start Time> <End Date> <End Time>
    
Example of usage:
    
    edit busy 0 11 09:00 11 10:00
        
Example output:

![edit busy 0 11 09:00 11 10:00](images/edit_busy_0_11_0900_11_1000_op.png)

<br/>

**Use case 2:** Edits a contact's timetable to be **"free"** for a specified time slot. You need to key in `<Contact Index>`
                of the contact, and specify the time slot to be marked **"free"** with `<Start Date>` `<Start Time>` `<End Date>` `<End Time>`.
    
Format: 
    
    edit free <Contact Index> <Start Date> <Start Time> <End Date> <End Time>
    
Example of usage:
 
    edit free 0 11 09:00 11 10:00
        
Example output:

![edit free 0 11 09:00 11 10:00](images/edit_free_0_11_0900_11_1000_op.png)

[&#8593; Return to list of Features](#features)
    
<br/>

## Example Contributions to the Developer Guide:
### 3.5 Edit a contact's timetable
![EditContact](images/EditContact.png)<br>
*Fig 14. Sequence diagram of the implementation of the `Edit a contact's timetable` feature*

Fig 14. shows the sequence diagram of editing the schedule (timetable) of a selected contact at a given time slot. 
It consists of 3 classes:```LogicManager``` ```Commandhandler``` ```Contact```.

Given below is an example usage scenario of the ```Edit a contact's timetable``` feature.

1. The user invokes the LogicManager by entering ```edit <contact index> <time slot>```.

    >:information_source: The original user input formats for ```EditCommand``` are: 
    <br>```edit busy <contact index> <start day> <start time> <end day> <end time>``` and 
    <br>```edit free <contact index> <start day> <start time> <end day> <end time>```, 
    <br>for editing the ```Contact```'s schedule to "busy" and "free" at the given time slot respectively. 
    We generalise ```edit busy``` and ```edit free``` as ```edit``` in the sequence diagram as their execution are similar. 
    We also represent ```<start day> <start time> <end day> <end time>``` as ```<time slot>``` in the sequence diagram to
    keep it concise.
    
2. The ```LogicManager``` requests to edit a contact's schedule via ```CommandHandler```.
3. The ```CommandHandler``` retrieves ```Contact``` from ```ContactList``` using the contact's index passed into the 
command.

    >:information_source: This step is omitted in the sequence diagram to keep it concise.
4. The ```CommandHandler``` calls ```editSchedule(time slot)``` of ```Contact```.
If ```edit busy```, the schedule of `Contact` will be marked as "busy" for the given time slot. If ```edit free```, the 
schedule of `Contact` will be marked as "free" for the given time slot.

The schedule of the `Contact` is edited and saved in the application.

### 3.5.1 Design Considerations
**Aspect 1: Clash of ```Meeting```'s time slot and ```EditContact```'s time slot when editing main user's schedule**

The implementation described above would allow the overwrite of any time blocks in the ```Contact```'s schedule. This  
would be problematic when editing the main user's schedule, which contains ```Meeting```s' time slots. A possible problematic 
scenario is if we edit over a ```Meeting```'s time slot and set the time slot to "free", we would subsequently be able to schedule 
another meeting at the same time slot. This results in multiple ```Meeting```s occupying the same time slot.

* Alternative 1(current choice): Disallow the overwrite of ```Meeting```'s time slot. If ```EditContact```'s time slot 
clashes with any ```Meeting```'s time slot, throw error to discontinue edit of that time slot.
* Alternative 2: Allow the overwrite of ```Meeting```'s time slot. If ```EditContact```'s time slot clashes with any 
```Meeting```'s time slot, remove the ```Meeting``` from ```MeetingList```. 

We chose Alternative 1 for these reasons:
1. The intended purpose of the edit function is to make amendments to schedules pulled
 from NUSMODS so that additional "busy" slots or "free" slots can be visualized in the timetable, not to edit over meetings. 
2. Editing of a ```Meeting```'s time slot to be "free" will be equivalent to deleting the ```Meeting```, for 
which there is a dedicated feature implemented. This causes unnecessary overhead in functionality.
3. Alternative 1 is easier to implement. When a clash is detected, Alternative 1 requires only the throwing of exception, 
whereas Alternative 2 requires the removal of ```Meeting``` from ```MeetingList```.<br><br>

![EditContact](images/EditContact_checkvalid.png)<br>
*Fig 15. Sequence diagram of checking if an edit is valid in the `Edit a contact's timetable` feature*

Fig 15. shows the sequence diagram illustrating the implementation of Alternative 1. Checking validity of 
edit is done before editSchedule() of `Contact` is called, as shown in Fig 14. 
1. This path is optional, and is only implemented if `Contact` the main user.
2. `CommandHandler` calls isValidEdit(time slot) of the `Contact` class.

3. isValidEdit(time slot) uses the `Contact`'s schedule to check if any of the time blocks within the time slot is a
    "meeting" time block. 
4. If no  "meeting" time block is detected within the time slot, the edit is valid. The program continues running and 
    editSchedule() will be called.
5. If a "meeting" time block is detected within the time slot, the edit is invalid. An invalid exception is thrown by 
    `Contact`, and the user will be informed that the edit is invalid.
    
    >:information_source: `TextUI` and `Exception` classes which are involved in generating the exception, and displaying the exception message
    to the user are omitted to keep the sequence diagram concise.
<br/>  
  
