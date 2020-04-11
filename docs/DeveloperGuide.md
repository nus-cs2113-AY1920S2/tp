

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
1. [Testing](#6-testing)
1. [Instructions for Manual Testing](#7-instructions-for-manual-testing)
1. [Glossary](#8-glossary)




## 1. Setting Up

You can find the set-up instructions [here](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/README.md).

## 2. Design 

The design of the project from the point of view of the user can be described as follows:

![Image of Design](https://raw.githubusercontent.com/AY1920S2-CS2113-T14-2/tp/master/docs/images/design.png)

The above figure demonstrates that once a user inputs a command through the **UI** component, the user response is sent to the **Commands** component for processing. Depending on the user response, the commands component can either i) affect existing lists of jobs, news or articles in the **Lists** component or ii) give user access to new articles through the **API** component or iii) load pre-existing information pieces (articles, jobs) inputted into the program through the **Creator** component. More commands can be called on information pieces loaded through the **API** component or the **Creator** component using the **Commands** component. When the program terminates, the lists of articles, jobs and notes are stored through the **Storage** component.

## 3. Implementation

This section will describe the design and implementation of a notable feature in the project.

Note: You will need to create an `article`, `job` or `note` for the feature. It can be done through the following commands.

     1. create article
     2. create job
     3. create note
                        
For more information about the `create` feature, please refer to the user guide [here](https://github.com/AY1920S2-CS2113-T14-2/tp/blob/master/docs/UserGuide.md#create-command).

### `create` feature

**Current Implementation:**

The `InformationCreator` is a standalone class inside the `commands` package. The `create` feature helps the user create information objects that can be instances of `article`, `note` and `job` classes and automatically add them an ArrayList of object types Article, Job, or Note, respectively. 

Given below is an example usage and how the `CreateCommand` mechanism behaves at each step:

* Step 1:
The user launches the application and it loads the `SavedNoteList`, `SavedArticleList` and `SavedJobList` from the storage using the corresponding JSON files("articleList.json", "jobList.json" and "noteList.json"). If even one of these JSON files do not exist in the memory, the application creates entirely new JSON files with empty initial ArrayLists to store articles, jobs and notes. 

* Step 2:
The user enters the command, `create article`, for instance, into the command line. The`TechToday#executeProgram()` method from the ProgramExecutor class parses the command provided and takes the first word of the command. Since the first word is `create`, it calls a static  `InformationCreator#execute(userResponse)` method, where `userResponse` is the original command provided by the user.

* Step 3:
The  `InformationCreator#execute(userResponse)` method again parses the user command to check what kind of information object the user is seeking to create. The three possibilities are `article`, `job` and `note`. The following daigram illustrates what steps are taken for different user responses.


![Image of SwitchCase](https://raw.githubusercontent.com/AY1920S2-CS2113-T14-2/tp/master/docs/images/switchCase.png)


* Step 4:
As seen from the above diagram, since the user response was `create article`, the static method, `ManualArticleCreator#execute()`, is called.

* Step 5:
The `ManualArticleCreator#execute()` method asks the user for information including **title**, **url**, **category**, and **extract** of the article by calling `Ui#getArticleTitle()`, `Ui#getArticleUrl()`, `Ui#getArticleCategory()` and `Ui#getArticleExtract()` methods respectively. The `ManualArticleCreator#execute()` fetches the current time and creates a timestamp of that time. The timestamp is assigned to a String variable called **epochSecond**. The five afformentioned variables(in bold) are used to create an `article` object, which is then added to the `SavedArticleList` eventually.


*The following sequence diagram summarizes how `create` command works:*

![Image of Sequence Diagram](https://raw.githubusercontent.com/AY1920S2-CS2113-T14-2/tp/master/docs/images/SD.png)


## 3. Product Scope

### Value Proposition

As _lifelong learners of technology brekthroughs, computer science and closely related disciplines_, we have access to so many news portals, job portals and we create notes ourselves. Given the sheer volume of information we have, it almost becomes hard for one to manage all that information. TechToday Information Tracker (TTIT) is for who want to organise information about technology. In particular, it helps a user **view** new articles/jobs from HackerNews (or pre-loaded articles in the event of no internet), **save** viewed articles/jobs and **create** information describing not only articles/jobs from other sources but also notes. All in all, TTIT's value stems from being an application that helps you manage your information about technology so that you do not have to feel drowned by the sheer volume of publicly available information about technology.

Note that TTIT utilizes [Hacker News API](https://github.com/HackerNews/API) to give the user the option to view new news articles and jobs and also has a feature to save them. 


### Target user profile

TechToday Information Tracker (TTIT) is targeted at _lifelong learners of technology brekthroughs, computer science and closely related disciplines__ who wish to organise the information that they consume.

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


## 5. Non-Functional Requirements

1. This application should be able to work on Linux, Windows, and OS machines as long the user has installed Java 11. 

2. Any user with knowledge of English language and basic typing skills should be able to use this application without any issues. If user is unable to understand commands, please see User Guide for more clarification. 

3. User interface should be seen through the terminal and, depending on command user is using, depict changes the user is making within the application. 

4. The data the user manipulated using the application should be saved once they type `exit` which saves and exits the application. This data is restored when the user uses the application again. 

5. If one wishes to use application from different computer with saved data, they just must transfer their saves JSON files to the new folder where the application will be run. 

6. The application should not crash at any moment. If there is an error within application, error message will appear. 

7. If the application is unable to connect to the internet to retrieve data for `view` feature, the application will still produce data that has been previously saved. 

## 6. Testing

JUnit tests were created for this application to ensure that our code is free of bugs, runs correclty, and outputs the intended data. 

### 6.1 IntelliJ JUnit Tests

1. To run all tests, right click on `src/test/java` and click Run "Tests in tp". 

2. To run using Gradle, open terminal and type `gradlew clean test`

## 7. Instructions for Manual Testing

Follow the following steps to manually test the product:

###  7.1 Setup and Initial Storage Testing

1. Download the latest release [here](ljsnc) and store it in an empty folder. 

2. Open the terminal and go to the folder where the file is stored and type `java -jar tp.jar` to initialize the program. 

3. Once the program starts, you should get a welcome message with possible commands.


4. **Note** Ensure that it outputs `All the required files do not exist. We will create completely new files to save your data.` 

5. Type `list article` and ensure the initial list is empty. You should get the following output: 

            __________________________________________________________________________________________


            _                                   Article List                                   _


            _                      There is nothing in the list currently.                     _
            __________________________________________________________________________________________
           

6. You should get a similar output for `list note` and `list job`: 


7. Type `exit` to exit the program. 


9. Rerun by typing `java -jar tp.jar` to start the program again.

**Note** The message `Loading "articleList.json", "jobList.json" and "notelist.json"...` and `Done loading files, enter your command now:` should appear instead of previous message of missing data. This is to ensure these JSON files were created the first time you run the program. 

### 7.2 Load Storage Testing 

1. Go to the sampleData folder [here](https://github.com/AY1920S2-CS2113-T14-2/tp/tree/master/docs/sampleData) and download 'articleList.json', 'noteList.json', 'jobList.json' and store it in the same folder where you stored the original jar file. If you have already run the program and existing JSON data files exist, replace them with the onces from the sampleData folder. 

2. Open the terminal and go afformentioned folder and type `java -jar tp.jar`.

3. This should load all the data in the folders. Type the following commands:

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

### 7.3 Feature Testing

**Important Note** 

***Please ensure JSON data files are empty for feature testing.*** 

#### `create` 

1. Type `create article` 

2. When asked `Enter the title of article?` enter the following: `ManualTestArticle`

3. When asked `What is the URL of the article (type "No URL")?` enter the following: `www.manualtestarticle.com`

4. When asked `What is the the category (type "default" if you don't know it)` enter the following: `test`

5. When asked `Would you like to add any extract?` type `This is for testing.`

5. You should see the following output: 

            Done, we have added the following article to your list of saved articles
               Title: ManualTestArticle
               Date: 2020-Apr-09 Thu 08:30 PM
               Category: test
               Url: www.manualtestarticle.com
               Extract: This is for testing.
            __________________________________________________________________________________________

#### `list` 

1. Type `list article` 

2. The only article in your list should be the article previously created. You should see the following output: 

            __________________________________________________________________________________________


            _                                   Article List                                   _


               1. Title: ManualTestArticle
               Date: 2020-Apr-09 Thu 08:30 PM
               Category: test
               Url: www.manualtestarticle.com
               Extract: This is for testing.


            __________________________________________________________________________________________
            
            
            
#### `addinfo` 

1. Type `addinfo article 1 Testing the addinfo feature` 

2. You should see the following output: 

            __________________________________________________________________________________________


            Done, the article description now looks like the following 

               Title: ManualTestArticle
               Date: 2020-Apr-09 Thu 08:30 PM
               Category: test
               Url: www.manualtestarticle.com
               Extract: Testing the addinfo feature
            __________________________________________________________________________________________


#### `delete`

1. Type `delete article 1` 

2. You should see the following output: 

            __________________________________________________________________________________________


            Deleting the following article:
               Title: ManualTestArticle
               Date: 2020-Apr-09 Thu 08:30 PM
               Category: test
               Url: www.manualtestarticle.com
               Extract: Testing the addinfo feature
            __________________________________________________________________________________________

            
3. To ensure it was deleted from the article list type `list article` 

4. You should see the following output: 

            __________________________________________________________________________________________


            _                                   Article List                                   _


            _                      There is nothing in the list currently.                     _
            __________________________________________________________________________________________


#### `view` 

1. Type `view article` 

2. You should see the following output: 

            __________________________________________________________________________________________


            Connecting to the internet and loading information... 

               1. Title: The Global Dollar Short Squeeze
               Date: 2020-Apr-09 Thu 06:24 PM
               Category: default
               Url: https://www.lynalden.com/global-dollar-short-squeeze/
               Extract: 


               2. Title: The software industry's greatest sin: hiring
               Date: 2020-Apr-09 Thu 06:13 PM
               Category: default
               Url: https://www.neilwithdata.com/developer-hiring
               Extract: 


               3. Title: Take-home vs. whiteboard coding: The problem is bad interviews
               Date: 2020-Apr-09 Thu 05:48 PM
               Category: default
               Url: https://andrewrondeau.com/blog/2020/04/take-home-vs-whiteboard-coding-the-problem-is-bad-interviews
               Extract: 


               4. Title: Ask HN: Has any progress been made on large format E-ink displays?
               Date: 2020-Apr-09 Thu 03:24 PM
               Category: default
               Url: URL Not provided
               Extract: 


               5. Title: Founder's Field Guide for Navigating This Crisis
               Date: 2020-Apr-09 Thu 12:38 PM
               Category: default
               Url: https://firstround.com/review/the-founders-field-guide-for-navigating-this-crisis-advice-from-recession-                  era-leaders-investors-and-ceos-currently-at-the-helm/
               Extract: 


               6. Title: Ask HN: Recommend me a course on Coursera
               Date: 2020-Apr-09 Thu 01:15 PM
               Category: default
               Url: URL Not provided
               Extract: 


            __________________________________________________________________________________________


#### `save` 

1. Type `save article 1` 

2. You should see the following output: 

        __________________________________________________________________________________________


        Done, saved the article with the following details:
           Title: The Global Dollar Short Squeeze
           Date: 2020-Apr-09 Thu 06:24 PM
           Category: default
           Url: https://www.lynalden.com/global-dollar-short-squeeze/
           Extract: 
        __________________________________________________________________________________________
   
   
3. To ensure it was added to article list type `list article` 

4. You should see the following output: 

        __________________________________________________________________________________________


        _                                   Article List                                   _


           1. Title: The Global Dollar Short Squeeze
           Date: 2020-Apr-09 Thu 06:24 PM
           Category: default
           Url: https://www.lynalden.com/global-dollar-short-squeeze/
           Extract: 


        __________________________________________________________________________________________


**Additional Note: These manual tests were created just for article objects. Similar commands can be replicated for note and job objects as well.** 


## 8. Glossary 

* Article - an object that contains the metadata of a news/website article 
* Job - an object that contains the metadata about opportunities/questions about technology related jobs.
* Note - an object that holds additional information about a certain topic that does not fit under article or job description. 
* Extract - refers to additional information you would like to add to certain article, job, or note object. 

