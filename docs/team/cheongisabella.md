# Isabella Cheong Project Portfolio

### Overview - Kitchen Helper
Kitchen Helper is a Command Line application to help users manage their kitchen inventory more conveniently and more efficiently.

### Contributions
+ Responsibility: Documentation

#### Enhancements implemented
+ Addition and Listing of chores
    + What it does: It allows the user to add chores to complete by a certain deadline. The user can then view all the chores in their list.
    + Justification: This feature improves the product slightly because it increases the usability of the application such that the user can keep track of chores in addition to their kitchen inventory. 
    + Highlights: This enhancement allows for the user to specify the deadline by a String or by a Java Date object. This increases user alternatives.
+ Deletion and Marking of chores as done
    + What it does: It allows users to delete chores by specifying the index of the chore in the list. Similarly, users can mark chores as done by specifying the index of the chore in the list.
    + Justification: The ability to mark chores as done is what motivates users and what helps them track their chores. When the chores are no longer relevant, or if the user makes a mistake in addition of chores, they can delete the chore.
    + Highlights: Users can delete chores by their index in the list instead of by their names, which is less troublesome for users to type. By specifying index, it is also certain that the corresponding chore associated with that index is deleted.
+ Chore notification
    + What it does: It notifies users during the startup of the application, if there are overdue chores or chores with deadlines upcoming in the next three days.
    + Justification: This feature improves the product slightly because users can better manage their completion of chores when they can be reminded of more pressing chores.
    + Highlights: This enhancement is possible because of the deadline specified by Date. 
+ Expenditure
    + What it does: It tracks the weekly total expenditure, as well as the amount used in cooking in the week. The value resets every week at Monday midnight.
    + Justification: This feature improves the product slightly because it helps users to manage expenditure. By comparing the amount used in cooking with their expenditure, users can see how much of their purchase they actually made use of and gauge their future spending on ingredients accordingly.
    + Highlights: The expenditure automatically increases whenever user executes the `addingredient` command. The amount used in cooking automatically increases when users execute `cookrecipe` command. Users will also be prompted to edit expenditure values when they execute `deleteingredient`.
    
#### Code Contributions 
The following link contains my code contributions to the project.
[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=cheongisabella&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=cheongisabella&tabRepo=AY1920S2-CS2113T-M16-2%2Ftp%5Bmaster%5D)

#### Other Contributions 
+ Project Management:
    + Tried to ensure consistent structure in team User Guide and Developer Guide. Notified teammates if there are jarring errors spotted in their documentation or code.

+ Documentation:
    + Added sequence diagram for AddChoreCommand
    + Added the documentation for Add, Delete, List Chores, Mark chore as done, Chore notifications and Expenditure for both User Guide and Developer Guide 
    
+ Community
    + Reviewed teammates' Pull Requests(with non-trivial review comments) [#251](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/251), [#250](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/250)
    + Reported bugs and provided suggestions for other teams [#27](https://github.com/nus-cs2113-AY1920S2/tp/pull/27), [#28](https://github.com/nus-cs2113-AY1920S2/duke/pull/28), [#1-7](https://github.com/cheongisabella/ped/issues)
    + Did manual testing and found bugs in teammates' codes
    + Participated in team discussions to plan for the project and identify potential issues

#### Contributions to the User Guide
Shown below are my contributions to User Guide, which showcases my ability to write documentation to target users.
+ Chore: [3.5.1. Adding a chore: `addchore`](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#351-adding-a-chore-addchore-isabella), [3.5.2. List chore: `listchore`](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#352-list-chore-listchore-isabella), [3.5.3. Delete a chore: `deletechore`](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#353-delete-a-chore-deletechore-isabella), [3.5.5. Mark chore as done: `done`](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#355-mark-chore-as-done-done-isabella)
+ [3.6 Expenditure](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#36-expenditure)
+ [4. Command Summary](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#4-command-summary)

#### Contributions to the Developer Guide
Shown below are my contributions to the Developer Guide, which showcase my ability to write technical documentation targeted at technical users.
+ Chore-related Features: [4.3.1. Addition of chore](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#431-addition-of-chore), [4.3.2. List all chores](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#432-list-all-chores), [4.3.3. Delete a specific chore](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#433-delete-a-specific-chore), [4.3.5. Mark chore as done](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#435-mark-chore-as-done), [4.3.6. Notification for chores warning](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#436-notification-for-chores-warning)
+ [4.5. Expenditure](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#45-expenditure)
+ Appendix F: Instructions for Manual Testing: [F.11. Add a chore](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#f11-add-a-chore), [F.12. List chore](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#f12-list-a-chore), [F.13. Delete a chore](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#f13-delete-a-chore), [F.15. Mark a chore as done](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#f15-mark-a-chore-as-done), [F.17. Display expenditure](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide#f17-display-expenditure)
