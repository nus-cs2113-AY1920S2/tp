

# [Project TechToday](https://github.com/AY1920S2-CS2113-T14-2/tp) - Developer Guide

By: `Alaukik Pant`      Since: `Mar 2020`      Licence: `MIT`

## Table Of Contents
- [1. Setting Up](#1-setting-up)
- [2. Design](#2-design)
- [3. Implementation](#3-implementation)
  * [`create` feature](#-create--feature)
- [3. Product Scope](#3-product-scope)
  * [Value Proposition](#value-proposition)
  * [Target user profile](#target-user-profile)
- [User Stories](#user-stories)
- [5. Non-Functional Requirements](#5-non-functional-requirements)
- [6. Glossary](#6-glossary)
- [7. Instructions for Manual Testing](#7-instructions-for-manual-testing)



## 1. Setting Up

You can find the set-up instructions [here](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/README.md).

## 2. Design 

The design of the project from the point of view of the user can be described as follows:

![Image of Design](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/docs/images/design.png)

The above figure demonstrates that once a user inputs a command through the **UI** component, the user response is sent to the **Command** component for processing. Depending on the user response, the command can either i) affect existing lists of jobs, news or articles or ii) give user access to new articles through the **API** component or iii) load pre-existing information pieces (articles, jobs) inputted into the program through the **Creator** component. More commands can be called on information pieces loaded through the **API** component or the **Creator** component. At the end, the lists of articles, jobs and notes are stored through the **Storage** component.

## 3. Implementation

This section will describe the design and implementation of a notable feature in the project.

Note: You will need to create an `article`, `job` or `note` for the feture. It can be done through the following commands.

     1. create article
     2. create job
     3. create note
                        
For more information, please refer to the user guide.

### `create` feature

**Current Implementation:**

The `InformationCreator` is a standalone class inside the `commands` package. The `create` feature helps the user create information objects that can be instances of `article`, `note` and `job` classes and automatically add them to the Article, Note and Job List respectively. 

Given below is an example usage and how the `CreateCommand` mechanism behaves at each step:

* Step 1:
The user launches the app and loads the `SavedNoteList`, `SavedArticleList` and `SavedJobList` from the memory. If the data of these lists do not exist in the memory, the application initializes empty lists.

* Step 2:
The user enters `create article`, for instance, into the command line. Method `TechToday#executeProgram()` from the ProgramExecutor class parses the command provided and takes the first word of the command. Since the first word is `create`, it calls a static  `InformationCreator#execute(userResponse)` method, where `userResponse` is the original command provided by the user.

* Step 3:
The  `InformationCreator#execute(userResponse)` method again parses the user command to check what kind of information object the user is seeking to create. The three possibilities are `article`, `job` and `note`. The following daigram illustrates what steps are taken for different user responses.


![Image of SwitchCase](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/docs/images/switchCase.png)


* Step 4:
As seen from the above diagram, since the user response was `create article`, the static method, `ManualArticleCreator#execute()`, is called.

* Step 5:
The `ManualArticleCreator#execute()` method asks the user for information including **title**, **url**, **category**, and **extract** of the article by calling the `Ui#getCommand()`. The `ManualArticleCreator#execute()` fetches the current time and creates a timestamp of that time. The timestamp is assigned to a String variable called **epochSecond**. The five afformentioned variables(in bold) are used to create an `article` object, which is then added to the `SavedArticleList` eventually.


*The following sequence diagram summarizes how `create` command works:*

![Image of Sequence Diagram](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/docs/images/SD.png)


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







