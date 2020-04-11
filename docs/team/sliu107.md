# Shiyue Liu - Project Portfolio Page

## PROJECT: History Flashcard

## Overview

History Flashcard (HF) is an app for history students to create flashcards which summarize the most important
 information for historical events, figures and artifacts. 
HF is optimized for those who prefer to use a simple Command Line Interface (CLI), and includes features that help
 with memory retention and make information convenient to review.
 
## Summary of Contributions

- **Code Contributed**: [Link](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=sliu107) to
 my code on tP Code Dashboard
 
- **Enhancements implemented**:
    1. Feature: Create group using existing flashcards
        - What it does: Allows users to group multiple existing flashcards with some common characteristics.
        - Justification: This feature is needed for users to categorize all the flashcards currently in the application.
        - Highlights:   
             - The feature is implemented in [#28 pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/28).
             - The feature builds up the structure of groups, which is the basic of other group relevant commands.
             - `GroupFactory` is used to create new group and `GroupList` includes multiple operations executed on the 
               groups in the application. The overall flow of group management is quite similar with those of flashcards 
               and flashcardList, which keeps the consistency of implementation.
             - Other classes like `Ui` were also modified to display the group information involving name, description
               and number of flashcards in the group.
               
    2. Feature: Add a flashcard to an existing group
        - What it does: Allows users to add a flashcard to a group.
        - Justification: This feature is needed for users to edit the content of the groups after creation, which makes 
          the group management more easier.
        - Highlights:  
             - The feature is implemented in [#28 pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/28).
             - The feature enhances the practicality of the flashcard group.
             - The feature support user to specify a group by group index of name, which makes the operation more 
               convenient for those who prefer to use a simple Command Line Interface.
               
    3. Feature: Delete a group from the group list
        - What it does: Allows users to delete a group from the group list.
        - Justification: This feature is needed for users to remove a group then no longer need.
        - Highlights:
             - The feature is implemented in [#182 pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/182).
             - The feature is a basic operation to management groups in the list.
             - Similarly, users able to specify a group with its index or name.
             
    4. Random: Randomize all the flashcards in the application
       - What it does: The feature randomly reorders the flashcards in the flashcard list. And then display the
                       detailed information of groups one by one, and updates their reviewed status after received
                       response from users.
       - Justification: This feature is needed for users to shuffle and randomized all the existing flashcards to help 
                        themselves to self check whether they have mastered the points or not.
       - Highlights:
            - The feature is implemented in [#106 pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/106).
            - The feature provides a helpful way to test users' familiarity of the flashcards contents which in some
              content helps users to schedule their future study plans.
            - And during the review process, users can give response to each flashcard to update the status of it. And 
              the number of flashcards just reviewed and total number of unreviewed flashcards will be shown after
              finishing a round of review.
              
    5. Rest-reviewed: Reset all the flashcards in the application as unreviewed.
       - What it does: The feature reset all the flashcards as unreviewed.
       - Justification: This feature enables users to do multiple rounds of review by reset the status when all the
                        flashcards are marked as reviewed but users want to review them again.
       - Highlights:
            - The feature is implemented in [#109 pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/109).
            - The feature is a complementary operation used to cooperate with random review and study plans.
            - The feature makes the whole process of learning and reviewing more complete.
    6. Help : Send help message
       - What it does: The feature send out help message to give information about how to use all the commands.
       - Justification: This feature helps new users to quickly get familiar with the application.
       - Highlights:
            - The feature is implemented in [#28 pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/28).
        
- **Contributions to User Guide**:
    1. Added description for the random review feature.
    2. Update description for group relative feature.
    
- **Contributions to the Developer Guide**:
    1. Added description for the random review features.
    2. Added description for the Ui component.
    
- **Review/mentoring contributions**: I reviewed some pull requests from my teammates. For example,
[#27](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/27),
[#39](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/39),
[#70](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/70),
[#72](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/14),
[#105](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/105),
[#114](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/114),
[#117](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/117),
[#118](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/118),
[#121](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/121),
[#127](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/127),
[#132](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/132),
[#134](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/134),
[#137](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/137),

- **Other contributions**:
    - Help maintain the issue tracker
    - Find and fix bugs for `parse` and some other commands(e.g. `help`, `delete`, `random`)
    - Reported [bugs and suggestions](https://github.com/sliu107/ped/issues) for other project teams.


              
