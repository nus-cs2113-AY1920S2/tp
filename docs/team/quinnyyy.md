# Quinn Meurer - Project Portfolio Page

## PROJECT: History Flashcard

## Overview

History Flashcard (HF) is an app for history students to create flashcards which summarize the most important
 information for historical events, figures and artifacts. 
HF is optimized for those who prefer to use a simple Command Line Interface (CLI), and includes features that help
 with memory retention and make information convenient to review.

## Summary of Contributions
- **Code Contributed**: [Link](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=quinnyyy&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false&tabOpen=false) to
 my code on tP Code Dashboard
 
- **Enhancements implemented**:
    1. Feature: Basic Flashcard Structure
        - [Link to the pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/14)
        - What it does: Allows the user to create event, person, and other flashcards.
        - Comments: I used the **factory** design pattern here. I thought this would be a good  
        place to use this pattern because the Flashcards are all instantiated differently.
    2. Feature: Implement the Timeline Command
        - [Link to the pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/31)
        - What it does: Allows the user to display a list of flashcards sorted by their start date.
        - Comments: This feature got me exposed to some of the APIs in the Java Standard Library 
        (not sure if that's what it's called). I made extensive use of
        [DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
        which was used to parse the date the user entered. This was especially interesting because
        the API allows you to supply patterns so the user can enter dates in multiple formats. 
        In order to compare two flashcards I made the flashcards implement the
        [Comparable](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html) interface.
        This makes it so you can use the sorting capabilities of the standard containers. These tasks
        really helped me get familiar with how to use built-in Java APIs. 
    3. Feature: Add saving
        - [Link to the pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/101)
        - What it does: Automatically save flashcards and groups as json files.
        - Comments: To achieve this I decided to use the Gson serialization library. 
        I thought it would be best to use a 3rd party solution because our objects can become  
        quite confusing. When I was implementing the `Storage` class for this feature I used
        the **Singleton** design pattern. This task helped me see how using the Singleton pattern
        can make code much cleaner. In our code we do **not** use the Singleton pattern for the Ui class
        and it resulted in Ui objects being passed around in our code everywhere. I could see perfectly
        how the Singleton pattern could be used to solve that problem.
    4. Feature: Add loading
        - [Link to the pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/189)
        - What it does: Load flashcards, groups from disk into memory on startup
        - Comments: This task was actually surprisingly difficult. The hardship came from the
        fact that some information is lost when the `Flashcards` are serialized: we saved the data about
        `Flashcards` but we didn't save what **type** of `Flashcard` it was. So when we were trying to read
        it back in we didn't know if it was an `EventFlashcard`, `PersonFlashcard`, or `OtherFlashcard`.
        All we knew was that it was an abstract `Flashcard`. There was an interesting solution to this. 
        We could specify that when we are saving a `Flashcard` we can add an additional field to the json to specify 
        which class it represents. Then when we read it back in we can construct the corresponding `Flashcard`.
    5. Bug fix: Delete flashcards from group as well when they are deleted
        - [Link to the pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/197)
        - What's the problem: There is a global list of flashcards. When a flashcard was deleted from the global list
        it was not being deleted from groups that contained that flashcard.
        - Comments: This task seemed like a good fit for the **Observer** pattern. I made the Flashcard the subject
        and made the groups the observers. That way when a flashcard was going to be deleted from the global list
        I was able to notify all observers to delete this flashcard as well. This presented some problems with serialization
        because there was a circular relationship between Flashcards and Groups, but I was able to use a hacky solution
        to avoid this.
    6. Bug fix: User can add the same flashcard to a group twice
        - [Link to the pull request](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/63)
        - What's the problem: same as title
        - Comments: To solve this I implemented equals methods in flashcards. This way I could check
        if the flashcard was equal to a flashcard in the group before adding it. This exposed me to
        the best practices of writing an equals method.
        
- **Contributions to user guide and developer guide**:
    - I contributed the Storage section in the user and developer guides.
- **Contributions to team-based tasks**:
    - I established and improved a lot of the coding patterns we used. Some examples:
        * [Wrote first tests in the project](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/27).
        I was the first person in the team to tackle the challenge of writing tests. In the linked pull request
        Shiyue left a comment saying that it was a helpful reference to base her tests off of. This code was
        especially difficult to test because it involved input from the user and I was able to tackle that challenge.
        * [Improved our logging setup pattern](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/62).
        I improved the logging setup pattern that we were using. In the linked pull request Zhilin
        left a comment saying that this pattern was an improvement in terms of code quality.
        * [I refactored Commands to improve code quality](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/101).
        I refactored our Commands so that they were cleaner and more testable. This is a change that has no
        functional changes but improves the developer life by making code more readable and more testable.
        The changes can be seen in the latter half of the linked pull request.
    - I tried to solve tasks that others weren't eager to take on. For example the first bug fix I 
    highlighted above was left unclaimed on our issue tracker for 14 days before I took it on.
    - I actively maintained the issue tracker
- **Reviewing/mentoring contributions**:
    - I was thorough in PR reviews and tried to really examine code quality and suggest places for possible improvement.
    - Examples of PR reviews:
        * [#21](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/21)
        * [#25](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/25)
        * [#36](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/36)
        * [#45](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/45)
        * [#59](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/59)
        * [#74](https://github.com/AY1920S2-CS2113-T14-1/tp/pull/74)
