## [Project TechToday](https://github.com/AY1920S2-CS2113-T14-2/tp) - User Guide

By: `Alaukik Nath Pant, Melissa Lopez`      Since: `Feb 2020`      Licence: `MIT`      Course: `CS2113`

1. [Introduction](#1-Introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)
    1. [Command Format](#command-format)
        1. [Asking for help with valid commands : `help`](#help-command)
        2. [Viewing articles or jobs : `view`](#view-command)
        3. [Saving an article or job: `save `](#save-command)
        4. [Creating an `article`, `job`, or `note`: `create`](#create-command)
        5. [Listing saved/created `article`, `job`, `note`: `list `](#list-command)
        5. [Deleting saved/created `article`, `job`, or `note`: `delete `](#delete-command)
        7. [Adding an extract to `article`, `job`, or `note`: `list`](#add-command)
        8. [Exiting the program : `exit`](#exit-command)
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)
6. [Acknowledgement](#6-acknowledgement)

## 1. Introduction

TechToday Information Tracker(TTIT) is for an application for users who *prefer to use a desktop application for **managing** information about the technology field, in the form of news, jobs and/or notes*. TTIT is *optimized for those who prefer to work with a Command Line Interface* (CLI). TTIT utilizes the [Hacker News](https://github.com/HackerNews/API) API to give users access to the latest technology related news through the **view** feature of the application. However, the purpose of TTIT isn't to act as a news/job/note portal, but rather a **manager** of technology related information for a lifelong learner of Computer Science and closely related disciplines. Hence, we help the user to manage metadata such as the title, URL, date, and additional extract of an article/job/note. The user can go to the relavent article, for instance, using the URL if they are interested in the article and even add a short extract to articles that they have saved using the `addinfo` feature. If the user is not connected to the internet while using the app, the user can **view** pre-loaded articles and jobs. The user also has the option to add additional articles, jobs and notes about technology themselves using the **create** functionality. 

## 2. Quick Start

* Ensure you have Java `11` or above installed in your Computer.
* Download the jar file -`CS2113-T14-2.TechToday.jar` [latest release here!](https://github.com/AY1920S2-CS2113-T14-2/tp/releases/tag/v2.1).
* Copy the file to the folder you want to use as the home folder for your Technology Information tracker.
* Open the command prompt and go into the directory where the Jar file is stored and type `java -jar CS2113-T14-2.TechToday.jar`.
* When the application executes, all possible commands that can help you manage your information are shown.
* Some example commands you can try:
    * **`view `**`article` : helps you view articles about technolgy from the internet. If the device is not connected to the internet, it loads an existing set of articles pre-loaded into the program.
    * **`list `**`article`: Lists all the articles you have saved in your database into the Command Line.
    * **`delete `**` article 1` : deletes the 1st article that appears in the article list.
    * **`exit`** : exits the app.
* Expected output when you first run application: 

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


* Note: When the application first runs, it will create new JSON files to store data in the folder where the JAR file is located. 

## 3. Features


### Command Format

* When the user types the help command and sees`[article / job / note]` or `[article / job]` under the command format option, it means that the user is requred to choose one of `article`, `job` or `note`. For example, `view [article / job]` can be intrepreted as `view article` or `view job`.
* The `INDEX_NUMBER` is the interger index of a article/job/note to be saved, deleted, or edited from a saved article list, or a saved note list or a saved job list. It is of format `delete article INDEX_NUMBER`, `save job TASK_NUMBER`.
* `EXTRACT` represents a string that can be composed of multiple words that you wish to add to an existing note, job or article. For example, `addinfo article INDEX_NUMBER EXTRACT`.
* Note that if the input from the user is a valid command followed by arbitrary input, the programme will just ignore the arbitrary input and execute the valid command. For example, a command like `view article ls;dgljfg` will only read `view article` and ignore the additional command that follows the valid command.


#### Help Command
##### `help` : Asking for help with valid commands

* Loads all possible commands that the user can type.
* Format: `help`

Expected outcome: 

        __________________________________________________________________________________________


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
        __________________________________________________________________________________________


****


#### View Command
##### `view` : Viewing `article` or `job` 

* Displays a latest list articles or jobs from the internet. If device is not connected to the internet, pre-loaded articles or jobs is shown. Note that you cannot `view note` as notes are to to created by the user and not loaded from the internet.
* Format: `view [article / job]`

[TIP]
Connect to the internet if you wish to see latest articles. If you use the `view` command more than once in a short period of time, it is highly likely that it will load the same articles/jobs as HackerNews updates information related to articles/jobs once or twice a day only.

Example:

    view article 

Expected Outcome:

            __________________________________________________________________________________________


            Connecting to the internet and loading information... 

               1. Title: A man owns the most advanced private air force after buying 46 F/A-18s
               Date: 2020-Apr-10 Fri 08:33 AM
               Category: default
               Url: https://www.thedrive.com/the-war-zone/32869/this-man-owns-the-worlds-most-advanced-private-air-force-after-buying-46-f-a-18-hornets
               Extract: 


               2. Title: How to Win a Currency War
               Date: 2020-Apr-10 Fri 02:25 PM
               Category: default
               Url: https://www.lynalden.com/currency-war/
               Extract: 


               3. Title: Apple and Google partner on Covid-19 contact tracing technology
               Date: 2020-Apr-10 Fri 10:05 AM
               Category: default
               Url: https://www.apple.com/newsroom/2020/04/apple-and-google-partner-on-covid-19-contact-tracing-technology/
               Extract: 


               4. Title: Senate Stock Watcher
               Date: 2020-Apr-10 Fri 09:20 AM
               Category: default
               Url: https://senatestockwatcher.com
               Extract: 


               5. Title: Show HN: We made a web app so that our parents can keep reading to our daughter
               Date: 2020-Apr-10 Fri 01:17 PM
               Category: default
               Url: http://readastorytome.com/
               Extract: 


               6. Title: FreeRDP 2.0 – A Remote Desktop Protocol implementation
               Date: 2020-Apr-10 Fri 08:54 AM
               Category: default
               Url: https://www.freerdp.com/
               Extract: 


            __________________________________________________________________________________________


* Note: The program will show six articles from the internet/memory. The ones shown above are an example of the format, but articles that appear to user may have different titles, url, ect. 


****

#### Save Command
##### `save ` : Saving an `article` or `job`

* Saves an article/job you see from the internet/database that the view feature displays as a seperate list of saved articles or jobs. The saved articles/jobs get saved into the memory and you can see them again using the `list` functionality when you load the application some other time.
* Format: `save [article / job] INDEX_NUMBER`

[TIP]
You have to view an article/job before being able to save it. Even if you exit the program and immediately reload it, the application is designed such that you will have you view a list of articles/jobs first before saving a particlar one.

Example:

    save article 1

Expected Outcome:

Saves viewed article with index number 1.

    __________________________________________________________________________________________


    Done, saved the article with the following details:
       Title: The Global Dollar Short Squeeze
       Date: 2020-Apr-10 Fri 09:24 AM
       Category: default
       Url: https://www.lynalden.com/global-dollar-short-squeeze/
       Extract: 
    __________________________________________________________________________________________


****

#### Create Command
##### `create` : Creating an `article`, `job`, or `note`

* Helps user manually create  an `article`, `job`, or `note` and add it to the list of saved articles, jobs or notes respectively.
* Note that a created `article`, `job`, or `note` is saved to the list of saved articles/job/notes and not the one the user sees when she invokes the "view articles" command for example. The saved article/job list is composed of a list of individually *saved* articles from the view list and *created* articles.
* Format: `create [article / job / note]`


Example:

    create article
    
Expected Outcome:

Asks user for title, URL, category, and extract input to create an information piece describing an article.


        __________________________________________________________________________________________


        Done, we have added the following article to your list of saved articles
           Title: ArticleForUserGuide
           Date: 2020-Apr-10 Fri 04:11 PM
           Category: user guide
           Url: www.articleforug.com
           Extract: This is an example used in the user guide
        __________________________________________________________________________________________



****

#### List Command
#####  `list ` : Listing saved/created `article`, `job`, `note`

* Lists articles, jobs or notes that you have creaed or saved from the internet/database.
* Format: `list [article / job / note]`

[TIP]
You must have saved or created some articles/jobs/notes in order to list them.

Example:

    list article

Expected Outcome:

        __________________________________________________________________________________________


        _                                   Article List                                   _


           1. Title: ArticleForUserGuide
           Date: 2020-Apr-10 Fri 04:11 PM
           Category: user guide
           Url: www.articleforug.com
           Extract: This is an example used in the user guide


        __________________________________________________________________________________________

Lists all saved articles. 

Example of list has been created for the User Guide. 

****

#### Delete Command
##### `delete ` : Deleting saved/created `article`, `job`, or `note`

* Deletes `article`, `job`, or `note` at the specified `INDEX_NUMBER`. 
* When an `article`, `job`, or `note` is deleted, all subsequent ones in the resepective list will have their index reduced by 1.
* Format: `delete [article / job / note] INDEX_NUMBER`

Example:

    delete article 1

Expected Outcome:

    __________________________________________________________________________________________


    Deleting the following article:
       Title: The Global Dollar Short Squeeze
       Date: 2020-Apr-10 Fri 09:24 AM
       Category: default
       Url: https://www.lynalden.com/global-dollar-short-squeeze/
       Extract: 
    __________________________________________________________________________________________
****

#### Add Command
##### `addinfo`: Adding an extract to `article`, `job`, or `note`

* Adds an extract to an `article`, `job`, or `note`.
* Format: `addinfo [article / job / note] INDEX_NUMBER EXTRACT`

Example:

    addinfo article 1 It turns out that yes, fresh grads and keener interns do complain to senior developers about asymptotic efficiency.

Expected Outcome:

    __________________________________________________________________________________________


    Done, the article description now looks like the following 

       Title: New grad vs senior dev
       Date: 2020-Apr-10 Fri 01:44 PM
       Category: Funny
       Url: https://ericlippert.com/2020/03/27/new-grad-vs-senior-dev/
       Extract: It turns out that yes, fresh grads and keener interns do complain to senior developers about asympto...
    __________________________________________________________________________________________



****

#### Exit Command
##### `exit`: Exiting the program

* Exits the program. 
* Format: `exit`

Expected output: 

        __________________________________________________________________________________________

        _                                Saving your data...                               _
        _                           We are exiting the program...                          _
        _                            Bye. Come back again later.                           _


****

## 4. FAQ

*Q*: How do I transfer my data to another Computer? 

*A*: Copy three text files called "articlelist.json", "notelist.json", 'joblist.json' that has saved your information into the directory from which you will run the application using the jar file in your new computer. You are then set to go!

*Q*: What will happen if I try to use the `view` feature without internet? 

*A*: If you do not have access to the internet while using the application, a set of manually saved articles and jobs will appear to the user. This was created by the developers so that the user can still use the `view` feature if they do not have access to the internet. 


## 5. Command Summary
* *Help*  : `help`; 
e.g. `help`
* *View* : `view [article / job]`;
eg. `view article`
* *Save* : `save [article / job] INDEX_NUMBER"`;
eg. `save article 1`
* *Create* : `create [article / job / note]`
eg. `create article`
* *List* : `list [article / job / note]`;
eg. `list article`
* *Delete* : `delete [article / job / note] INDEX_NUMBER`;
e.g. `delete article 1`
* *Addinfo* : `addinfo [article / job / note] INDEX_NUMBER EXTRACT`;
e.g. `addinfo article 1 It turns out that yes, fresh grads and keener interns do complain to senior developers about asymptotic efficiency.`
* *Exit* : `exit`
e.g. `exit`

## 6. Acknowledgement
Our UserGuide has been heavily inspired by the superb format of the AddressBook user guide that we were given access to for this course.
