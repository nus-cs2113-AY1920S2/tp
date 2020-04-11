# JeremiasLiew - Project Portfolio Page

## Project - Exam Study Companion (ESC)

## Overview
Exam Study Companion (ESC) is a command-line based app that is designed to help students preparing for upcoming exams. The app is written in Java and requires JDK 11 to run.


## Summary of Contributions
### Major Enhancements
#### Added the Quiz feature
+ What it does: Allows users to quiz themselves using stored flashcards in the selected subject.

+ Justification: This is one of the main features of the ESC. It allows the user to prepare for exams by revising the knowledge
needed for the exam, as practice makes perfect.

+ Highlights: This enhancement quizzes questions in random order, allows the user to select the number of questions to quiz, and allows the user to quit the quiz prematurely.
It works alongside the score feature, in order to further enhance the user's revision (elaborated below).

#### Added the Score feature
+ What it does: Allows users to view their score after each quiz, and also a list of their past quiz scores for the selected subject.

+ Justification: The feature improves on the quiz feature, by allowing the user to view his/her performance after each quiz. It also allows
him/her to check the past score history to track the improvement over time.

+ Highlights: This enhancement works alongside the quiz feature to provide a user-friendly and meaningful score history. It also tracks scores for
quizzes that were ended prematurely, by only taking into account the questions asked before termination.

#### Added the Event Management feature
+ What it does: Allows users to add, delete and view upcoming events.

+ Justification: Being able to keep track of upcoming events/deadlines such as exams/project deadlines is helpful to the user as
it allows him/her to plan his/her revision accordingly.

+ Highlights: This enhancement is not tied to the subject class, meaning that the user has the flexibility to add any events that are
deemed important, even those not related to any of the stored subjects. The event feature also allows the user to select the number of days range
of upcoming events to show.

### Minor Enhancements
+ ##### Added the Card and CardList classes.
+ ##### Added the ability to detect duplicates in questions when adding cards.

<hr>
<h4> Code Contributed: <a href="https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=JeremiasLiew&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos" target="_blank">Code</a>
</h4>
<hr>

### Contributions to Documentation

#### Contributions to the DG
+ Minor updates to the Design section to reflect an updated implementation of the Event Management Feature.
+ All write-ups and diagrams for the [Quiz feature implementation](https://ay1920s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#22-quiz-feature).
+ All write-ups and diagrams for the [Score feature implementation](https://ay1920s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#23-score-feature).
+ All write-ups and diagrams for the [Event Management feature implementation](https://ay1920s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#24-event-management-feature).

#### Contributions to the UG
+ All write-ups and diagrams for the Event feature.
+ Additions to the Quiz feature write-up and FAQ section based on newly implemented Quiz features.

#### Contributions to Team-based Tasks
+ Bug checking and bug fixes.
+ Added user stories in issue tracker for v1.0.
