# Ngan Ji Cheng - Project Portfolio Page 

## PROJECT: Amazing Task and Assignment System (ATAS)

### Overview
ATAS is a cross platform Command Line Interface (CLI) program that allows you to track your assignments and events. 
ATAS is catered for students who want to maximise their productivity and cultivate effective time management habits through a desktop application.

### Summary of Contributions 
**Major Enhancement**: Implemented feature to edit tasks

- What it does: 
    - It allows the user to edit a chosen task from the list of tasks.
    - The index of task that user edits does not change after editing. 
    
- Justification:
    - Improves usability of the tracking app as it allows users to edit any changes to their Assignments or Events.
    - Users will not need to delete an Assignment or Event before adding a new Assignment or Event if there are any changes
    to details of the tasks. 
    - For events that are repeated, editing such events will keep the "repeating" indicator of the event. 

- Highlights:
    - This enhancement is especially useful in a tracking app where tasks details are dynamically changing. It allows for quick
    and hassle free changing of the details.
    - Implementing the edit feature was difficult because a separate `Ui` prompt has to be implemented in the command to accept
    new user inputs. 
    - Furthermore, due to implementation limitation of `AssignmentCommand` and `EventCommand` feature, I could not use the existing
    implementations to have instances of `assignment` or `event` class. 
    - Hence, within the `EditCommand` class, I have to implement methods that return `assignment` and `event` task types.
    - We discovered later that there is a bug when editing `repeat event` task types. When `repeat event` task types are edited,
    the event no longer stays as a `repeat event` and reverts back to just being an `event` task type. This was fixed by adding checks to
    indicate whether task to be edited is a `repeat event` and another method to preserve all the `repeat event` attributes. 
    - Refer to Pull Request [#194](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/194) for bug fix. 
    - Another bug was also found when we tried to enter in a different task type when editing a task. i.e. Tried to edit 
    an Assignment task to an event task and vice versa. The program shows a success message but it did not show up in the 
    task list.
    - I subsequently fixed the bug by disallow users to edit a task type to a different type. [#213](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/213)  
 
 - Credits: 
    - Credit to the team for helping me to catch the bugs related to edit command.
    
**Minor Enhancements**:
- Implemented `list today` and `list command` methods to list tasks [#31](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/31)
- Implemented method to return `LocalDateTime` object of the Current Date [#31](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/31)
- Add skeleton code for TaskList class [#25](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/25)
- Wrote JavaDocs for several methods 

**Code Contributed**: [View on Reposense](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=jichngan&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=jichngan&tabRepo=AY1920S2-CS2113T-M16-1%2Ftp%5Bmaster%5D)

**Contributions to the User Guide**:
- Add Commands Summary Section for User Guide [#99](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/99)
- Add instructions for `EditCommand`

**Contributions to the Developer Guide**:
- Add `EditCommand` section for Developer Guide [#137](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/137)
- Add Instructions for Manual Testing for Developer Guide [#208](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/208)

**Contributions to Team-Based Tasks**:
- Add template for User Guide [#99](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/99)
- Add template for Developer Guide [#101](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/101)
- Format User Guide 
- Manage Issue Tracker on GitHub
- Add project README to GitHub
    
    