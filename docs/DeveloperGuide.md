

## [Project TechToday](https://github.com/AY1920S2-CS2113-T14-2/tp) - Developer Guide

By: `Alaukik Pant`      Since: `Mar 2020`      Licence: `MIT`


## 1. Setting Up

Prerequisites: JDK 11, update Intellij to the most recent version

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Click `Import Project`
1. **IMPORTANT: Locate the `build.gradle` file** in the project directory, select it, and click `OK`
1. If there are any further prompts, accept the defaults.
1. After the set up is complete, you can locate the `src/main/java/seedu/techtoday/TechToday.java` file, right-click it, and choose `Run TechToday.main()`. If the setup is correct, you should see something like the below:

        _ **_____________________________________________________________________________**_
        _                                                                                  _
        _                             Hello! Here's TechToday.                             _
        _            Let me show you some technology news to refresh your mind!            _
        _ **_____________________________________________________________________________**_
        _                    Your queries can be of the following forms:                   _
        _                                      1. help                                     _
        _                              2. view [article / job]                             _
        _                       3. save [article / job] INDEX_NUMBER                       _
        _                         4. create [article / job / note]                         _
        _                          5. list [article / job / note]                          _
        _                   6. delete [article / job / note] INDEX_NUMBER                  _
        _              7. addinfo [article / job / note] INDEX_NUMBER EXTRACT              _
        _                                      8. exit                                     _
        _                                                                                  _
        _ **_____________________________________________________________________________**_
             What can I do for you?

        No files with your data exits, we will create new files to save your data.
        __________________________________________________________________________________________

Type exit and press enter to let the execution proceed to the end. Also note how Intellij is now using Gradle to run your code (you can make Intellij run the code without Gradle [this way](tutorials/assets/RunUsingIntellij.png)).

* Acknowledgement- The set-up portion is a direct adaptation of the set-up instructions given to us for this project.

## 2. Design and Implementation

This section will describe the design and implementation of a notable feature in the project.

### * `create` feature

#### Overview

The `create` feature helps the user create information objects that can be instances of `article`, `note` and `job` classes and automatically add them to the Article, Note and Job List respectively.

The InformationCreator class is a standalone class with a static method called `execute`

The mechanism in which the `InformationCreator.execute()` method works is given below:

* Step 1:
The user launches the app and loads the `SavedNoteList`, `SavedArticleList` and `SavedJobList` from the memory. 

* Step 2:
The user enters `create article`, for instance, into the command line. Method executeProgram() from the ProgramExecutor class parses the command provided and takes the first word of the command. Since the first word is `create`, it calls a static  `InformationCreator.execute(userResponse)` method, where "userResponse" is the original command provided by the user.

* Step 3:
The execute method of the InformationCreator class again parses the user command to check what kind of information the user is seeking to create. The following daigram illustrates the different possibilites of creatig different kinds of information peices.


![Image of SwitchCase](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/docs/images/switchCase.png)


Warning: If IndexOutOfBoundsException is caught, a "wrong command" message is shown to the user.

* Step 4:
Since our command was called `create article`, a static method called `ManualArticleCreator.execute()` is called from the standalone `ManualArticleCreator` class.

* Step 5:
The `ManualArticleCreator.execute()` method asks for variables including `title`, `url` and `category` from the user and fetches the current time and creates a timestamp of that time. The timestamp is assigned to a String variable called `epochSecond`. The four afformentioned variables are used to create an `article` object, which is then added to the `SavedArticleList` eventually.


* Diagram representing the Process:

The following sequence diagram summarizes how create command operation works:

"Sequence Diagram in Progress"



## 3. Product Scope

### Value Proposition

TechToday Information Tracker (TTIT) is for those who want to organise information about technology. It help one add details of articles, questions about jobs, and personal technology notes. TTIT also lets you view latest jobs/articles from HackerNews when you have access to the internet so that you can instantly save interesting ones without having to type details about the viewed job/article. It is important to note that TTIT isn't a news portal but simply an information organiser that helps you organise technology infomration into three forms: article, jobs and notes(miscellaneous). 

### Target user profile

TechToday Information Tracker (TTIT) is targeted to lifelong followers of technology brekthroughs who wish to organise the information that they consume.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student of technology|view latest articles|get updated about the latest heppenings in the tech space.|
|v1.0|student of technology|save an article| can generate a summary/list of all the articles I have read.|
|v1.0|student of technology|view latest information related to job|get updated about the latest heppenings in the job market.|
|v1.0|student of technology|see the details of the article before I save it|see the details before I decide to archive it|
|v1.0|student of technology|delete an article from a list of articles|only have the essential articles I need in my manager.|
|v1.0|student of technology|create my own note|save information about technology that is not an article necessarily.|
|v1.0|student of technology|list all my notes|keep track all the notes I have written so far.|
|v1.0|student of technology|list all my articles|keep track all the articles I have saved so far.|
|v1.0|student of technology|list all my jobs|keep track all the jobs I have saved so far.|
|v1.0|student of technology|add an extract to a note|add more ideas to an exiting note.|
|v2.0|student of technology|add an extract to an article|also add a short opinion I have about it.|
|v2.0|student of technology|add an extract to a job|also skills I require to get the job.|
|v2.0|student of technology|add an extract to an article|also add a short opinion I have about it.|
|v2.0|student of technology|create my own custom article|save interesting articles I find from various sources.|
|v2.0|student of technology|create my own custom job|save interesting job I find from various sources.|
|v2.0|student of technology|save the time of the article I create|know when I saved this particular article.|
|v2.0|student of technology|save the time of the note I create|know when I saved this particular note.|
|v2.0|student of technology|save the time of the job I create|know when I saved this particular job.|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## 5. Non-Functional Requirements

{Give non-functional requirements}

## 6. Glossary

* *glossary item* - Definition

## 7. Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}







