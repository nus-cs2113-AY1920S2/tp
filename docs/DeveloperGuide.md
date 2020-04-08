# Developer Guide
- [Design](#1-Design)
- [Implementation](#2-implementation)
  - [[Proposed] Subject Feature](#21-proposed-subject-feature)
- [Appendix A: Product Scope](#appendix-a-product-scope)
- [Appendix B: User Stories](#appendix-b--user-stories)
- [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
- [Appendix D: Glossary](#appendix-d-glossary)
- [Appendix E: Instructions for Manual Testing](#appendix-e-instructions-for-manual-testing)

## 1. Design
### Architecture
The Duke class is the main class of the product. It is responsible for:
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes storage method where necessary.	

The product also contains the following components:
* Cards: Holds the data in the type of Card and relative operations
* Subjects: Holds the data in the type of Subject and relative operations
* Score: Holds the data in the type of Score and relative operations
* Commands: Deals with user input and communicate CLI to relative methods
* Exceptions: Deals with illegal inputs
* Parser: Convert CLI inputs into command keywords

In these components, cards and subjects have similar structure. Both of them contains a Card/Subject class and CardList/SubjectList class.
Duke, along with all command class and Parser form the logic box of the product.
<br />![](images/logicuml.jpg)

<br />Logic box interacts with Model box, i.e. Card, CardList, Subject, SubjectList, ScoreList.
<br />![](images/modeluml.jpg)

<br />Finally the Storage box, i.e. Storage class will handle reading and writing the content to files.
The Storage component saves the SubjectList objects in Serializable format and loads it back.
<br />![](images/storageuml.jpg)

## 2. Implementation
### 2.1. [Proposed] Subject Feature
#### 2.1.1. Proposed Implementation
The subject feature is an extension to the existing flashcard feature which allows users to categorise their
flashcards. This helps users to search for their flashcards more efficiently and also users to quiz by subject. The list of user's subjects are stored inside the SubjectList. It implements the following operations:

- ``SubjectList#addSubject()`` - Adds a new subject to the subject list.
- ``SubjectList#removeSubject()`` - Removes an existing subject from the subject list.
- ``SubjectList#listSubjects()`` - List the subjects in the subject list.

Step 1. Before the user decides to add a flashcard, he/she can create a subject to store the flashcard using
the command ``addsubject s/SUBJECTNAME``.
The following diagram describes how the add subject operation works:

![](images/addsubject_sequence_uml.jpg)

Step 2. The user executes the command ``listsubjects`` to view the subjects currently stored in the application.

Step 3. Once the user has chosen a subject, he/she can execute the command ``addcard s/SUBJECTINDEX q/QUESTION a/ANSWER``
to add a flashcard into the subject. 
The following diagram describes how the add card operation works:

![](images/addcard_sequence_uml.jpg)

#### 2.1.2. Design Considerations
##### Aspect: How user can add a flashcard into a subject
- **Alternative 1 (current choice)**: Include the subject index in the command.
  - Pros: 
    - Simple implementation without involving states
  - Cons: 
    - The user will have to list the subjects first to determine the subject index.
  
- **Alternative 2**: Select the subject first, then add a flashcard.
  - Pros:
    - The command for adding a card will be shorter.
  - Cons:
    - Multiple states will be involved.
    - The application will become more complex as different states use different commands.
    - The application can also become more difficult to use as users can be unclear about the states.

### 2.x. Quiz Feature
#### 2.x.1. Implementation
The quiz feature now incorporates random testing, builds upon the subject feature to allows users to set how many questions to quiz for a selected subject. 
This helps users to quiz by subject, and get a score at the end of each quiz. It implements the following operations:

- ``Quiz#quizQuestion()`` - Outputs a random question to the user that has not been tested before in this quiz session.
- ``Quiz#quizNext()`` - Retrieves a random question from the available questions for that subject.
- ``Quiz#markCorrectness()`` - Marks user answer as correct or wrong based on user's judgement.

Given below is an example usage scenario and how the quiz mechanism behaves at each step.

Step 1. Before the user decides to start a quiz, he/she has to create a subject using the command
 ``addsubject s/SUBJECTNAME``, and add flashcards to that subject using the command ``addcard s/SUBJECTINDEX q/QUESTION a/ANSWER``.

Step 2. The user can start a quiz by indicating a subject and the number of questions to quiz using the command
``quiz s/SUBJECTINDEX n/NUMBERTOQUIZ``. If the number of questions to quiz is not specified, all the questions stored
in that subject will be quizzed.
The following diagram describes how the quiz operation works:

![](images/quiz_sequence_uml.png)

Step 3: The quiz will end upon completion of the specified number of questions, or by stopping the quiz using the
command ``exitquiz``.

#### 2.x.2. Design Considerations
##### Aspect: How the user can control how many questions to be quizzed
- **Alternative 1**: Always quiz all stored questions for that subject, but allow users to stop the quiz whenever they want.
  - Pros: 
    - Simple implementation.
  - Cons: 
    - The user will have to keep track of how many questions he has already done.
  
- **Alternative 2 (current choice)**: Allow the user to both set the number of questions to quiz and to stop the quiz halfway.
  - Pros:
    - More flexibility for the user.
  - Cons:
    - More complex implementation.
    - The user has to type in a longer command to start a quiz, or they will be quizzed with all questions by default.
    
### 2.x. Score Feature
#### 2.x.1. Implementation
The score feature builds on the quiz feature, storing the score for each quiz session in a ScoreList. 
This allows users to see all past scores and track any improvements. It implements the following operations:

- ``Subject#showScores()`` - Shows all scores and the average score from all quiz sessions for that subject, in chronological order.
- ``ScoreList#getAvg()`` - Calculates the average score for all scores in the ScoreList.
- ``ScoreList#listScores()`` - Lists out all scores in the ScoreList.

Given below is an example usage scenario and how the score mechanism behaves at each step.

Step 1. Before the user can view his scores for a particular subject, he first needs to have done at least one quiz
session for that subject using the command ``quiz s/SUBJECTINDEX n/NUMBERTOQUIZ``.

Step 2. The user can view the score history and average score of a selected subject, if he has done at least one
quiz session for that subject, using the command ``score s/SUBJECTINDEX``.
The following diagram describes how the score operation works:

![](images/score.jpg)

#### 2.x.2. Design Considerations
##### Aspect: How to format the score history shown to the user
- **Alternative 1 (current choice)**: Show all scores in chronological order and in percentages of number of correct answers / number of
 questions asked. Also show the average percentage score of all the scores.
  - Pros: 
    - Users can easily keep track of their progress and if they have been improving.
    - A percentage score allows users to compare quiz results even if a different amount of questions were chosen to be quizzed in the quiz sessions.
  - Cons: 
    - More complex implementation.
    - If the user attempts the quiz many times, showing every quiz score may clutter up the ui, and might be too much
    information for the user to take in.
  
- **Alternative 2**: Show just the average score, and number of attempts taken.
  - Pros:
    - Clean ui, no clutter.
    - Simple implementation.
  - Cons:
    - User will be unable to track his progress.
    
### 2.x. Event Management Feature
#### 2.x.1 Implementation
The event feature builds on the quiz feature by adding another aid to the user when preparing for an exam/event.
This feature allows the user to add and keep track of upcoming events, such as exams/tests. It implements the following operations:

- ``SubjectList#addEvent()`` - Adds a new event.
- ``SubjectList#removeEvent()`` - Removes an existing event.
- ``SubjectList#listEvents()`` - List all events in the order they were added.
- ``SubjectList#showUpcoming()`` - Shows upcoming events in chronological order.
    
### 2.x. Save/Load Feature
#### 2.x.1 Implementation
The save/load process is facilitated with the `java.io.Serializable` interface, which converts the given object to a byte stream and back.
Writing and reading from the file uses the `java.io.FileOutputStream` and `java.io.FileInputStream` classes respectively.

To serialize the object to be written to file via `java.io.FileOutputStream`, it makes use of the `java.io.ObjectOutputStream#writeObject` method.
To deserialize the object after being read from file via `java.io.FileInputStream`, it uses the `java.io.ObjectInputStream#readObject()` method.

The reading and writing functions can be found in the `Storage#loadSubs()` and `Storage#saveSubs()` methods respectively.
![](images/storage_sequence_uml.jpg)

## Appendix A: Product Scope
### Target User Profile
The product is intended for students preparing for exams. Students can store practice question and model answers in the product.
Also, the product offers students to quiz themselves to practice for exams

### Value Proposition
The product aims to provide students with more convenient way of doing revision. By using the product students can categorize questions into different subjects and practice more effectively.

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|add new cards|view cards and answers to revise|
|v1.0|user|delete cards|organize cards better|
|v1.0|user|quiz myself|practice the questions|
|v1.0|user|list cards|organize the cards|
|v1.0|user|save my cards|access them in the future|
|v2.0|user|add cards with subjects|categorize the cards better|
|v2.0|user|list cards by subjects|organize the cards|
|v2.0|user|delete subjects|organize subjects better|
|v2.0|user|view my score|see how I performed|
|v2.0|user|view my test history|see how I performed|

## Appendix C: Non-Functional Requirements
1. The product should be able to run on any platform that has JDK11
2. The product should be able to hold up to 1000 cards

## Appendix D: Glossary

* *glossary item* - Definition

## Appendix E: Instructions for Manual Testing
### Running Tests
There are two ways to run tests.

Method 1: Using IntelliJ JUnit test runner
    
    To run all tests, right-click on the src/test/java folder and choose Run 'All Tests'

Method 2: Using Gradle

    Open a console and run the command gradlew clean test (Mac/Linux: ./gradlew clean test)
See UsingGradle.adoc for more info on how to run tests using Gradle.

### Types of Tests
There have three types of tests:

Unit tests targeting the lowest level methods/classes.
* e.g. seedu.cards.CardTest

Integration tests that are checking the integration of multiple code units (those code units are assumed to be working).
* e.g. seedu.cards.CardListTest
