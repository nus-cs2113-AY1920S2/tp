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
    - Furthermore,  
    