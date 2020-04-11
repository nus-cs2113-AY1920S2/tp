# Zhilin Huang - Project Portfolio Page

## PROJECT: History Flashcard

## Overview

History Flashcard (HF) is an app for history students to create flashcards which summarize the most important
information for historical events, figures and artifacts. 
HF is optimized for those who prefer to use a simple Command Line Interface (CLI), and includes features that help
with memory retention and make information convenient to review.

## Summary of Contributions

- **Code Contributed**: [Link](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=zhilin-huang) to
 my code on tP Code Dashboard
- **Enhancements implemented**:
    1. Feature: List all flashcards
        - Description: Allows users to input `list` to list all flashcards in the application. This feature is needed
         for users to review all flashcards currently in the application.
        - Highlights: In the [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/21) to implement this
        enhancement, I added the `Parser` class and the `Command` for all commands to inherit from and this roughly
        determines the structure of the code base. The `FlashcardList` class was implemented to handle all flashcards
        in the application.
    2. Feature: Delete a flashcard
        - Description: Allows users to input `delete INDEX` to delete a flashcard. This feature is needed for users
         to remove a flashcard that they no longer need.
        - Highlights: This feature was implemented in the same 
        [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/21) as the 'List all flashcards'
        feature and deletion of the flashcards is handled by the `FlashcardList` class.
    3. Feature: Update study plan
        - Description: Allows users to input `plan` and then prompt users to input the date for the plan and indexes of
        existing flashcards planned to study on that day. This feature is needed for users to set their study plans
         for the flashcards so that they
        can better manage their studies.
        - Highlights: In the [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/94) to implement this
        enhancement, I added the `StudyPlanList` class to handle all study plans in the application. Study plans
        are stored in a `TreeMap` with the date as key so that all entries are sorted by date.
    4. Feature: Display study plan
        - Description: Allows users to input `show-plan` to display all study plans. This feature is needed for users
         to view all the study plans they've added to the application.
        - Highlights: This feature was implemented in the same 
        [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/94) as the 'Update study plan' feature and
        the list of all study plans is retrieved from the `StudyPlanList` class.
    5. Feature: Delete study plan
        - Description: Allows users to input `delete-plan` to delete a study plan for the study plan list. This
         feature is needed for users to delete a study plan that they no longer need.
        - Highlights: In the [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/173) to implement this
         enhancement, I added a method in the `StudyPlanList` class to handle the deletion of study plans.
    6. Feature: Save and load study plans
        - Description: Automatically saves study plans into storage and reloads study plans from storage when
         application restarts. This feature is needed to keep users' study plans across multiple runs of the
          application.
        - Highlights: In the [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/191) to implement this
         feature, I modified the save and reload of `FlashcardList` to retain the order of flashcards so that the
         index of flashcards stored in `StudyPlanList` are still valid upon reload.
    7. Feature: List all reviewed flashcards
        - Description: Allows users to input `list-reviewed` to view all reviewed flashcards in the application. This
         feature is needed for users to keep track of what flashcards have been reviewed.
        - Highlights: In the [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/95) to implement this
        enhancement, a method was added to the `FlashcardList` class to get all reviewed flashcards from the
        flashcard list. The index of each flashcard in the flashcard list is also printed out for the users.
    8. Feature: Search for all flashcards with names containing a specific keyword
        - Description: Allows users to input `find KEYWORD` to display all flashcards with names containing the
        KEYWORD. This feature is needed for users to conveniently search for flashcards when they cannot
        remember the index of the flashcard(s) they are looking for.
        - Highlights: This feature was implemented in the same 
        [pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/95) as the 'List all reviewed flashcards' 
        feature. A method was added to the `FlashcardList` class to get all flashcards with names containing the
        specified keyword from the flashcard list. The index of each flashcard in the flashcard list is also printed
        out for the users.
- **Contributions to User Guide**:
    1. Updated User Guide for all features implemented in v1.0 in the pull request 
    [here](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/45).
    2. Added description for the `find KEYWORD` and `list-reviewed`.
    3. Added description for the Study Plans related features.
- **Contributions to the Developer Guide**:
    1. Added description for the Study Plan related features.
    2. Added description for the Parser component.
    3. Added Instructions for Manual Testing.
- **Review/mentoring contributions**: I actively reviewed pull requests from my teammates. For example,
[#14](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/14), 
[#25](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/25), 
[#28](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/28), 
[#31](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/31),
[#36](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/36),
[#43](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/43),
[#62](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/62),
[#70](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/70),
[#71](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/71),
[#74](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/74),
[#98](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/98),
[#105](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/105),
[#109](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/109),
[#113](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/113),
[#136](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/136).
- **Contributions beyond the project team**: I actively initialized or participated in forum discussions. For example,
[#21](https://github.com/nus-cs2113-AY1920S2/forum/issues/21),
[#26](https://github.com/nus-cs2113-AY1920S2/forum/issues/26),
[#31](https://github.com/nus-cs2113-AY1920S2/forum/issues/31),
[#56](https://github.com/nus-cs2113-AY1920S2/forum/issues/56),
[#69](https://github.com/nus-cs2113-AY1920S2/forum/issues/69),
[#83](https://github.com/nus-cs2113-AY1920S2/forum/issues/83).
- **Other contributions**:
    - Managed [releases](https://github.com/AY1920S2-CS2113-T14-1/tp/releases) `v1.0`, `v2.0` on GitHub.
    - Helped maintain the [issue tracker](https://github.com/AY1920S2-CS2113-T14-1/tp/issues).
    - Reported [bugs and suggestions](https://github.com/Zhilin-Huang/ped/issues) for other project teams.

## Contributions to the User Guide (Extracts)

An example of my contribution (study plans section) to the User Guide is extracted as follows:

### 3.7. Study Plans

Note that flashcards are identified by their indexes in study plans, so changing the indexes of flashcards could change
the flashcards associated with the study plans.

#### 3.7.1. Creating a New Study Plan: `plan`

Creates a new study plan by specifying date and indexes of existing flashcards.

Format: `plan`

The application will then prompt the user to enter the following fields:

- `DATE`: the date which the user want to set a study plan for
- `FLASHCARD_INDEXES`: indexes of the flashcards in the study plan

Example of usage:

```
plan
01/01/2020
1 3
```

#### 3.7.2. Displaying All Study Plans: `show-plan`

Lists all study plans.

Format: `show-plan`

#### 3.7.3. Deleting a Study Plan: `delete-plan`

Deletes a specified study plan. 
The user will be prompted to enter the date of the study plan they wish to delete.

Format: `delete-plan`

The application will then prompt the user to enter the following fields:

- `DATE`: the date which the user want to delete study plan

Example of usage:

```
delete-plan
01-01-2020
```

Another example of my contribution (FAQ) to the User Guide is extracted as follows:

## 4. FAQ

**Q**: How are the flashcards, flashcard groups, and study plans saved?

**A**: The application automatically saves these information in `JSON` format for you as you make changes to them. 
There is not a specific command to save these information to storage.

**Q**: Where are the flashcards, flashcard groups, and study plans saved?

**A**: They are saved under the `historyflashcards/` folder.

**Q**: Can I edit the storage files under the `historyflashcards/` folder?

**A**: Please avoid editing the files as changing the file format might cause the application to fail on reloads and
certain commands when you restart.

## Contributions to the Developer Guide (Extracts)

An example of my contribution (Study Plan Feature - Proposed Implementation) to the Developer Guide is extracted as
follows:

#### Study Plan Feature - Proposed Implementation

The Study Plan feature is facilitated by `StudyPlanList`.

Internally, it implements a `TreeMap`, with date as key and the list of flashcard indexes to study as value. 
Key-value pairs in the `TreeMap` are sorted by dates.

It implements the following operations:

- `StudyPlanList#updateStudyPlan()` - Updates the study plan list.
- `StudyPlanList#deleteStudyPlan()` - Deletes a study plan from the study plan list.
- `StudyPlanList#getStudyPlanList()` - Gets the list of study plans.

Given below is an example usage scenario and how the study plan mechanism behaves at each step.

Step 1. The user launches the application and an empty `StudyPlanList` is initialized.

Step 2. The user executes `plan` command and the `updateStudyPlan` operation is invoked. The application prompts the
user for date and the corresponding list of flashcards indexes.

The following sequence diagram shows how the `updateStudyPlan` operation works:

![updateStudyPlanSequenceDiagram](../images/updateStudyPlanSequenceDiagram.png)

Step 3. The user executes `delete-plan` command and the `deleteStudyPlan` operation is invoked. The application
prompts the user for the date for which the study plan is to be deleted.

The following sequence diagram shows how the `deleteStudyPlan` operation works:

![deleteStudyPlanSequenceDiagram](../images/deleteStudyPlanSequenceDiagram.png)

Step 4. The user executes `show-plan` command and the `getStudyPlanList` operation is invoked. The application
displays the user's study plan list.

The following sequence diagram shows how the `getStudyPlanList` operation works:

![getStudyPlanListSequenceDiagram](../images/getStudyPlanListSequenceDiagram.png)

Another example of my contribution (Appendix E: Instructions for Manual Testing) to the Developer Guide is extracted as
follows:

## Appendix E: Instructions for Manual Testing

Given below are instructions to test the app manually. Note that these instructions only provide a starting point for
testers to work on; testers are expected to do more exploratory testing.

### E.1. Launch and Shutdown

1. Launch
    - Download the jar file and copy into an empty folder.
    - Double-click the file to start the application or run the command `java -jar [path-to-history-flashcard-jar-file]` 
    from terminal.

2. Shutdown
    - Type in the `bye` to the application.

### E.2. Getting help

1. Getting help.
    - Test case: `help`
    
      Expected: Application displays a list of commands available.

### E.3. Flashcard Creation

1. Adding an Event Flashcard.
    - Test case: `event`

      Expected: Being prompted to enter multiple fields of the flashcard.
       
      Enter some values for the fields.
       
      Expected: Receive a confirmation message from the application for successful flashcard creation.

2. Adding a Person Flashcard.
    - Test case: `person`

      Expected: Being prompted to enter multiple fields of the flashcard.
       
      Enter some values for the fields.
       
      Expected: Receive a confirmation message from the application for successful flashcard creation.

3. Adding an Other Flashcard which name does not duplicate the names of existing flashcards.
    - Prerequisites: List all flashcards using the `list` command and choose a new name for the new flashcard.
    
    - Test case: `other`
    
      Expected: Being prompted to enter multiple fields of the flashcard.
       
      Enter some values for the fields.
       
      Expected: Receive a confirmation message from the application for successful flashcard creation.
       
    - Test case: `other blabla`
    
      Expected: Same as above because `blabla` should be ignored.

4. Adding an Other Flashcard which name duplicates one of the names of existing flashcards.
    - Prerequisites: List all flashcards using the `list` command and choose a duplicate name for the new flashcard.
    
    - Test case: `other`
    
      Expected: Being prompted to enter multiple fields of the flashcard.
            
      Enter some values for the fields.
       
      Expected: Receive a confirmation message from the application for successful flashcard creation and a message
      saying that the created flashcard is not added due to the duplicate name. 

### E.4. Flashcard Basic Operations

1. Listing all flashcards.
    - Test case: `list` when there are flashcards in the application.
    
      Expected: Application lists all flashcards in the application.
    
    - Test case: `list` when there are no flashcard in the application.
    
      Expected: Receive a message indicating that the user has no flashcard at the moment.

2. Showing timeline.
    - Test case: `timeline`
      
      Expected: Application lists all flashcards in order sorted by start/birth date.

    - Test case: `timeline 1900 2000`
    
      Expected: Application lists all flashcards from the 1900 to 2000 period in order sorted by start/birth date.

3. Deleting a flashcard.
    - Prerequisites: List all flashcards using the `list` command and choose the index of a flashcard to delete
    
    - Test case: `delete 1`
    
       Expected: Receive a confirmation message from the application for successful flashcard deletion.
       
    - Test case: `delete`
    
       Expected: Receive a message from the application stating that the user should use correct input format.
       
    - Test case: `delete -1`
    
       Expected: Receive a message from the application stating that the user input contains invalid flashcard 
       index(es).

4. Showing a flashcard.
    - Test case: `show 1`
      
      Expected: Application shows all fields from the flashcard at index 1.

5. Finding flashcards with names containing a specific keyword.
    - Test case: `find war`
    
      Expected: Application displays all flashcards with names containing `war`.

### E.5. Flashcard Status Operations

1. Assigning priority to a flashcard.
    - Prerequisite: There are at least 2 flashcards in the application.
    
    - Test case: `priority 2 MEDIUM`
    
      Expected: Receive a message from the application stating that priority for flashcard at index 2 is updated to
      MEDIUM.

2. Listing flashcards of a specified priority.
    - Test case: `list-priority MEDIUM`
    
      Expected: Application lists all flashcards which match the MEDIUM priority level.

3. Marking flashcard as reviewed.
    - Prerequisite: There is at least 1 flashcard in the application.

    - Test case: `reviewed 1`
    
      Expected: Receive a message from the application stating that review status for flashcard at index 1 is
      updated to reviewed.

4. Listing reviewed flashcards.
    - Test case: `list-reviewed`
    
      Expected: Application lists all reviewed flashcards.

5. Resetting review status of all flashcards.
    - Test case: `reset-reviewed`
    
      Expected: Application resets status of all flashcards to un-reviewed.

### E.6. Flashcard Grouping

1. Making a flashcard group. 
    - Prerequisite: There is at least 1 flashcard in the application.

    - Test case: `group`
    
      Expected: Being prompted to enter multiple fields of the flashcard group.
       
      Enter some values for the fields.
       
      Expected: Receive a confirmation message from the application for successful flashcard group creation.

2. Adding a flashcard to a flashcard group.
    - Prerequisite: There is at least 1 flashcard in the application.

    - Test case: `add`
      
      Expected: Being prompted to enter multiple fields for adding a flashcard to a flashcard group.
             
      Enter some valid values for the fields.
      
      Expected: Receive a confirmation message from the application for successful addition of flashcard to
      the flashcard group.
      
3. Displaying flashcard groups.
    - Test case: `show-groups`
    
      Expected: Application lists all existing flashcard groups.

4. Listing flashcards in a group.
    - Prerequisite: There is at least 1 flashcard group in the application.

    - Test case: `list-group 1`
      
      Expected: Application lists all flashcards in the flashcard group at index 1.

5. Deleting flashcard group from the group list.
    - Prerequisite: There is at least 1 flashcard group in the application.

    - Test case: `delete-group 1`
    
      Expected: Receive a confirmation message from the application for successful deletion of the flashcard group at
      index 1.

### E.7. Study Plans

1. Creating a new study plan.
    - Prerequisite: There is at least 1 flashcard in the application.

    - Test case: `plan`

      Expected: Being prompted to enter multiple fields for adding a flashcard to a flashcard group.
             
      Enter some valid values for the fields.
   
      Expected: Receive a confirmation message from the application for successful update of study plan.

2. Displaying all study plans.
    - Test case: `show-plan`
    
      Expected: Application lists all study plans.

3. Deleting an existing study plan.
    - Test case: `delete-plan`
        
      Expected: Being prompted to enter the date for which the study plan is to be deleted.
      
      Enter a valid date.
      
      Expected: Receive a confirmation message from the application for successful deletion of study plan.

### E.8. Randomized Review

1. Displaying all flashcards in random order.

    - Test case: `random`
    
      Expected: Application shuffles and displays all the flashcards in a random order and prompts the user to give
      responses during the review.
      
      Enter responses.
      
      Expected: Reviewed finished.

### E.9. Saving and Loading

1. Saving
    - Test case: Add flashcards into the application and exit.
    
      Expected: Storage files generated under the `historyflashcards` directory.

2. Loading

    - Test case: Starts the application again.
    
      Expected: Saved contents under the `historyflashcards` directory are loaded into the application.
 