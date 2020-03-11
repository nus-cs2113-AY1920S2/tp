Exam Study Companion
User Guide

1. Introduction
The Exam Study Companion (ESC) is for students who want to improve on their grades by quizzing themselves through flashcards. ESC is a command line-based application, giving it a simple, no frills user interface that is easy to use.

2. Quick Start
Ensure that you have JAVA 11 or above installed on the computer

3. Features
Command Format:
Words in UPPERCASE are inputs which will be typed in by the user.
Eg. create n/CATEGORYNAME

3.1. Create category
Creates a category to store the flashcards in.
Format: create n/CATEGORYNAM
Example:
create n/Biology

3.2. List all categories
Displays a list of all categories created in ESC.
Format: list category 

3.3. Select category
Selects a category and gains access to the flashcards stored in that category.
Format: select INDEX
Gain access to a category with the specified INDEX.
The INDEX of all categories is shown after executing the command list category.
The INDEX must be a positive integer.
Example:
select 1

3.4. Delete category
Removes a category and all flashcards stored in that category from the question bank.
Format: delete category INDEX
Deletes a category with the specified INDEX.
The INDEX of all categories is shown after executing the command list category.
The INDEX must be a positive integer.
Example:
list category
delete category 1
    Deletes the 1st category in the list of categories.
    
3.5. Add card
Adds a flashcard to a category.
Format: add q/QUESTION a/ANSWER
To add a card, the user must first enter a category by executing select INDEX
Example:
select 1
Enters the 1st category
add q/Which year was NUS founded? a/1980
Adds a flashcard to the 1st category with the above question and answer.

3.6. List all cards
Displays a list of all questions in the category.
Format: list card

3.7. Delete card
Removes a flashcard from the question bank.
Format: delete INDEX
To delete a card, the user must first enter a category by executing select INDEX
The INDEX of all flashcards is shown after executing the command list card.
Deletes a flashcard in the specified INDEX.
The INDEX must be a positive integer.
Example:
select 1
Enters the 1st category
delete 2
Deletes the 2nd flashcard in the 1st category.

3.8. Display a random question
Display a random question from the question bank.
Format: quiz

3.9 View a selected flashcard
Displays the answer to a question selected from the question list.
Format: view category_INDEX question_INDEX
Shows answer to a flashcard in the specified INDEX.
The INDEX of all flashcards is shown after executing the command list card.
The INDEX must be a positive integer.
Example:
answer 1

3.10. Show help
Displays a list of commands for the program
Format: help

3.10. Exit program
Exits the program
Format: exit

4. FAQ
Q: How do I edit existing flashcards?
A: There is currently no such feature in the current version, but users can simply delete the old flashcard and add a flashcard with the edited question and answer.

5. Command Summary
Create: create n/CATEGORYNAME
E.g. create n/Biology
List Category: list category
Select Category: select INDEX
Eg. select 1
Delete Category: delete category INDEX
Eg. delete 1
Add Card: add q/QUESTION a/ANSWER
Eg. add q/Which year was NUS founded? a/1980
List Card: list card
Delete Card: delete INDEX
Eg. delete 1
Quiz: quiz
Answer: answer / answer INDEX
Eg. answer 1
Help: help
Exit: exit
