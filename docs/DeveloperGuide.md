

# [Project TechToday](https://github.com/AY1920S2-CS2113-T14-2/tp) - Developer Guide

By: `Alaukik Pant, Melissa Lopez`      Since: `Mar 2020`      Licence: `MIT`

## Table Of Contents
1. [Setting Up](#1-setting-up)
1. [Design](#2-design)
1. [Implementation](#3-implementation)
     * [`create` feature](#-create--feature)
1. [Product Scope](#3-product-scope)
     * [Value Proposition](#value-proposition)
     * [Target user profile](#target-user-profile)
     * [User Stories](#user-stories)
1. [Non-Functional Requirements](#5-non-functional-requirements)
1. [Glossary](#6-glossary)
1. [Instructions for Manual Testing](#7-instructions-for-manual-testing)



## 1. Setting Up

You can find the set-up instructions [here](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/README.md).

## 2. Design 

The design of the project from the point of view of the user can be described as follows:

![Image of Design](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/docs/images/design.png)

The above figure demonstrates that once a user inputs a command through the **UI** component, the user response is sent to the **Command** component for processing. Depending on the user response, the command can either i) affect existing lists of jobs, news or articles or ii) give user access to new articles through the **API** component or iii) load pre-existing information pieces (articles, jobs) inputted into the program through the **Creator** component. More commands can be called on information pieces loaded through the **API** component or the **Creator** component. At the end, the lists of articles, jobs and notes are stored through the **Storage** component.

## 3. Implementation

This section will describe the design and implementation of a notable feature in the project.

Note: You will need to create an `article`, `job` or `note` for the feature. It can be done through the following commands.

     1. create article
     2. create job
     3. create note
                        
For more information, please refer to the user guide.

### `create` feature

**Current Implementation:**

The `InformationCreator` is a standalone class inside the `commands` package. The `create` feature helps the user create information objects that can be instances of `article`, `note` and `job` classes and automatically add them an ArrayList of object types Article, List, or Note, respectively. 

Given below is an example usage and how the `CreateCommand` mechanism behaves at each step:

* Step 1:
The user launches the application and it loads the `SavedNoteList`, `SavedArticleList` and `SavedJobList` from the stored, corresponding JSON files. If the data of these JSON files do not exist in the memory, the application creates new JSON files to store into memory and makes the initial ArrayLists in the application empty. 

* Step 2:
The user enters a command, `create article`, for instance, into the command line. The`TechToday#executeProgram()` method from the ProgramExecutor class parses the command provided and takes the first word of the command. Since the first word is `create`, it calls a static  `InformationCreator#execute(userResponse)` method, where `userResponse` is the original command provided by the user.

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

### User Stories

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

Follow the following steps to manually test the product:

#  7.1 Setup and Initial Storage Testing

1. Download the latest release [here](ljsnc) and store it in an empty folder. 

1. Go to the sampleData folder [here](https://github.com/AY1920S2-CS2113-T14-2/tp/tree/master/docs/sampleData) and download 'articleList.json', 'noteList.json', 'jobList.json' and store it in the same folder where you stored the jar file mentioned above.

1. Open the terminal and go afformentioned folder and type `java -jar tp.jar`.

1. This should load all the data in the folders. Type the following commands:

  * `list article`
        
    You will get the following output:

            __________________________________________________________________________________________


            _                                   Article List                                   _


               1. Title: A music discovery site used in over 1M videos and games
               Date: 2020-Apr-08 Wed 02:22 PM
               Category: default
               Url: http://dig.ccmixter.org/
               Extract: 


               2. Title: HPE sets end date for hobbyist licenses for OpenVMS
               Date: 2020-Apr-08 Wed 06:21 PM
               Category: default
               Url: https://legacyos.org/hpe-sets-end-date-for-hobbyist-licenses-for-openvms/
               Extract: 


            __________________________________________________________________________________________
       

  * `list job`
        
     You will get the following output:

            __________________________________________________________________________________________


            _                                     Job List                                     _


               1. Title: Ask HN: What most annoying software problem you've recently faced?
               Date: 2020-Apr-08 Wed 09:50 PM
               Category: default
               Text: I&#x27;ll start with myself. Recently, I have tried to use Forestry.io to set up a personal blog, bu...
               Extract: 


               2. Title: Ask HN: What is your blog and why should I read it?
               Date: 2020-Apr-07 Tue 11:33 AM
               Category: default
               Text: Looking for awesome new places to read things written by actual human beings.<p>So where can I find ...
               Extract: 


            __________________________________________________________________________________________
           


  * `list note`
           
      You will get the following output:
          
              __________________________________________________________________________________________


                _                                     Note List                                    _


                   1. Title: How to work with Github?
                   Date: 2020-Apr-08 Wed 10:06 PM
                   Category: default
                   URL: https://guides.github.com
                   Extract: This is a guide to githubs official tutorial.


                __________________________________________________________________________________________

