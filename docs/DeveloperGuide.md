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
1. Architecture
The Duke class is the main class of the product.It is responsible for,

	At app launch: Initializes the components in the correct sequence, and connects them up with each other.

	At shut down: Shuts down the components and invokes storage method where necessary.	

The product also contains the following components as well:
cards: Holds the data in the type of Card and relative operations
subjects: Holds the data in the type of Subject and relative operations
score: Holds the data in the type of Score and relative operations
commands: Deals with user input and communicate CLI to relative methods
exceptions: Deals with illegal inputs
parser: Convert CLI inputs into command keywords

In these components, cards and subjects have similar structure. Both of them contains a Card/Subject class and CardList/SubjectList class.
Duke, along with all command class and Parser form the logic box of the product
<br />![](images/logicuml.jpg)

<br />Logic box interacts with data box, ie Card, CardList, Subject, SubjectList, ScoreList.
<br />![](images/modeluml.jpg)

<br />Finally the storage box, ie Storage class will handle writing and reading the files.
<br />{insert image here}

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
    
## Appendix A: Product Scope
### Target user profile
The product is intended for students preparing for exams. Students can store practice question and model answers in the product.
Also, the product offers students to quiz themselves to practice for exams

### Value proposition
The product aims to provide students with more convenient way of doing revision. By using the product students can categorize questions into different subjects and practice more effectively.

## Appendix B:  User Stories

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
<br />1.The product should be able to run on any platform that has JDK11
<br />2.The product should be able to hold up to 1000 cards

## Appendix D: Glossary

* *glossary item* - Definition

## Appendix E: Instructions for Manual Testing
<br />1. Running Tests
<br />There are two ways to run tests.

<br />Method 1: Using IntelliJ JUnit test runner

<br />To run all tests, right-click on the src/test/java folder and choose Run 'All Tests'

<br />Method 2: Using Gradle

<br />Open a console and run the command gradlew clean test (Mac/Linux: ./gradlew clean test)

<br />See UsingGradle.adoc for more info on how to run tests using Gradle.

<br />2. Types of tests
<br />We have three types of tests:

<br />Unit tests targeting the lowest level methods/classes.
<br />e.g. seedu.cards.CardTest

<br />Integration tests that are checking the integration of multiple code units (those code units are assumed to be working).
<br />e.g. seedu.cards.CardListTest
