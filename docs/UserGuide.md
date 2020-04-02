## [Project TechToday](https://github.com/AY1920S2-CS2113-T14-2/tp) - User Guide

By: `Alaukik Nath Pant, Melissa Lopez`      Since: `Feb 2020`      Licence: `MIT`      Course: `CS2113`

1. [Introduction](#Introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
    1. [Command Format](#command-format)
        1. [Asking for help with valid commands : `help`](`help`-command)
        2. [Viewing articles or jobs : `view`](#viewing-articles-or-jobs----view-)
        3. [Saving an article or job: `save `](#saving-an-article-or-job---save--)
        4. [Creating an `article`, `job`, or `note`: `create`](#creating-an--article----job---or--note----create-)
        5. [Listing saved/created `article`, `job`, `note`: `list `](#listing-saved-created--article----job----note----list--)
        5. [Deleting saved/created `article`, `job`, or `note`: `delete `](#deleting-saved-created--article----job---or--note----delete--)
        7. [Adding an extract to `article`, `job`, or `note`: `list`](#adding-an-extract-to--article----job---or--note----list-)
        8. [Exiting the program : `exit`](#exiting-the-program----exit-)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

## Introduction

TechToday Information Tracker(TTIT) is for those who *prefer to use a desktop app for **managing** information about technology including news, jobs or even notes*. TTIT is *optimized for those who prefer to work with a Command Line Interface* (CLI). TTIT utilizes [Hacker News](https://github.com/HackerNews/API) API to give the user the option to **view** new news articles and questions/jobs. The purpose of TTIT isn't to act as a news/job/note portal, but rather a **manager** of technology related information for a lifelong learner of Computer Science and closely related disciplines. Hence, we refer to *viewing* an article, for instance, as referring to the option of viewing its title, URL and date. The user can even add a short extract to the afformentioned article using the `addinfo` functionality. If the device is not connected to the internet, the user can **view** pre-loaded articles and jobs. The user also has the option to add interesting articles, jobs and notes about technology themselves using the **create** functionality. 

## Quick Start

*  Ensure you have Java `11` or above installed in your Computer.
*  Download the latest `tp.jar` [latest release here!](https://github.com/AY1920S2-CS2113-T14-2/tp/releases/tag/v1.0).
*  Copy the file to the folder you want to use as the home folder for your Technology Information tracker.
*  Open command prompt in the directory where the Jar file is stored and type `java -jar tp.jar`.
* When the application executes, all possible commands that can help you manage your information are shown.
*Some example commands you can try:
    * **`view `**`article` : helps you view articles about technolgy from the internet. If the device is not connected to the internet, it loads an existing set of articles pre-loaded into the program.
    * **`list `**`article`: Lists all the articles you have saved in your database into the Command Line.
    * **`delete `**` article 1` : deletes the 1st artile that appears in the article list.
    * **`exit`** : exits the app.


## Features


### Command Format

* When the user types the help command and sees`[article / job / note]` or `[article / job]` under the command format option, it means that the user is requred to choose one of `article`, `job` or `note`. For example, `view [article / job]` can be intrepreted as `view article` or `view job`.
* The `INDEX_NUMBER` is the interger index of a article/job/note to be saved, deleted, or edited from a saved article list, or a saved note list or a saved job list. It is of format `delete article INDEX_NUMBER`, `save job TASK_NUMBER`.
* `EXTRACT` represents a string that can be composed of multiple words that you wish to add to an existing note, job or article. For example, `addinfo article INDEX_NUMBER EXTRACT`.
* Note that if the input from the user is a valid command followed by arbitrary input, the programme will just ignore the arbitrary input and execute the valid command. For example, a command like `view article ls;dgljfg` will only read `view article` and ignore the gibberish command that follows the valid part.


#### `help` command
##### Asking for help with valid commands : `help`

* Loads all possible commands that the user can type.
* Format: `help`

Examples:

    help

Expected Outcome:
   
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



#### Viewing articles or jobs : `view`

* Displays a latest list articles or jobs from the internet. If device is not connected to the internet, pre-loaded articles or jobs is shown. Note that you cannot `view note` as notes are to to created by the user and not loaded from the internet.
* Format: `view [article / job]`

[TIP]
Connect to the internet if you wish to see latest articles. If you use the `view` command more than once in a short period of time, it is highly likely that it will load the same articles/jobs as HackerNews updates information related to articles/jobs once or twice a day only.

Examples:

    view article


Expected Outcome:

    __________________________________________________________________________________________


    Loading information from the internet... 

    1. Title: JITs Are Un-Ergonomic
       Date: 2020-Mar-29 Sun 10:47 AM
       Category: default
       Url: https://abe-winter.github.io/2020/03/28/jitu-brutus.html
       Extract:


    2. Title: Show HN: SpicyPass – A free and open-source minimalist password manager
       Date: 2020-Mar-29 Sun 06:18 AM
       Category: default
       Url: https://github.com/JFreegman/SpicyPass
       Extract:


    3. Title: Building a Raspberry Pi GPS Speedometer
       Date: 2020-Mar-29 Sun 07:48 AM
       Category: default
       Url: https://gleslie.com/rpi/raspberry/pi/dashboard/2020/03/28/building-a-rpi-vehicle-dashboard.html
       Extract:


    4. Title: National Emergency Library – Free 1.4M ebooks
       Date: 2020-Mar-29 Sun 07:12 AM
       Category: default
       Url: https://archive.org/details/nationalemergencylibrary
       Extract:


    5. Title: New Grad vs. Senior Dev
       Date: 2020-Mar-28 Sat 08:20 AM
       Category: default
       Url: https://ericlippert.com/2020/03/27/new-grad-vs-senior-dev/
       Extract:


    6. Title: NYC Subway Turnstile Data
       Date: 2020-Mar-29 Sun 05:49 AM
       Category: default
       Url: https://github.com/toddwschneider/nyc-subway-turnstile-data
       Extract:


    __________________________________________________________________________________________

****

#### Saving an article or job: `save `

* Saves an article/job you see from the internet/database through the view option into a seperate list of saved articles or jobs. The saved articles/jobs get saved into the memory and you can see them again using the `list` functionality when you load the application some other time.
* Format: `save [article / job] INDEX_NUMBER`

[TIP]
You have to view an article/job before being able to save it. Even if you exit the program and immediately reload it, the application is designed such that you will have you view a list of articles/jobs first before saving a particlar one.

Examples:

    save article 1

Expected Outcome

    ________________________________________________________________________________________


    Done, saved the article with the following details:
       Title: JITs Are Un-Ergonomic
       Date: 2020-Mar-29 Sun 10:47 AM
       Category: default
       Url: https://abe-winter.github.io/2020/03/28/jitu-brutus.html
       Extract:
    __________________________________________________________________________________________


****


#### Creating an `article`, `job`, or `note`: `create`

* Helps user manually create  an `article`, `job`, or `note` and add it to the list of saved articles, jobs or notes respectively.
* Note that a created `article`, `job`, or `note` is saved to the list of saved articles/job/notes and not the one the user sees when she invokes the "view articles" command for example. The saved article/job list is composed of a list of individually *saved* articles from the view list and *created* articles.
* Format: `create article`


Examples:

    create article

Expected Outcome:

    Enter the title of the article?
    New grad vs senior dev
    __________________________________________________________________________________________


    What is the URl of the article (type "No URL")?
    https://ericlippert.com/2020/03/27/new-grad-vs-senior-dev/ 
    __________________________________________________________________________________________


    What is the category (type "default" if you done know it)
    Funny
    __________________________________________________________________________________________


    Done, we have added the following job to your list of saved jobs
       Title: New grad vs senior dev
       Date: 2020-Mar-29 Sun 12:25 PM
       Category: Funny
       Url: https://ericlippert.com/2020/03/27/new-grad-vs-senior-dev/
       Extract: 
    __________________________________________________________________________________________

****

#### Listing saved/created `article`, `job`, `note`: `list `

* Lists articles, jobs or notes that you have creaed or saved from the internet/database.
* Format: `list [article / job / note]`

[TIP]
You must have saved or created some articles/jobs/notes in order to list them.

Examples:

    list article

Expected Outcome:
      
      __________________________________________________________________________________________


      _                                   Article List                                   _


      1. Title: JITs Are Un-Ergonomic
         Date: 2020-Mar-29 Sun 10:47 AM
         Category: default
         Url: https://abe-winter.github.io/2020/03/28/jitu-brutus.html
         Extract: 


      2. Title: New grad vs senior dev
         Date: 2020-Mar-29 Sun 12:25 PM
         Category: Funny
         Url: https://ericlippert.com/2020/03/27/new-grad-vs-senior-dev/
         Extract: It turns out that yes, fresh grads and keener interns do complain to senior developers about asympto...
      __________________________________________________________________________________________

****

#### Deleting saved/created `article`, `job`, or `note`: `delete `

* Deletes `article`, `job`, or `note` at the specified `INDEX_NUMBER`. 
* When an `article`, `job`, or `note` is deleted, all subsequent ones in the resepective list will have their index reduced by 1.
* Format: `delete [article / job / note] INDEX_NUMBER`

Examples:

    delete article 1

Expected Outcome

    __________________________________________________________________________________________

    Deleting the following article:
       Title: JITs Are Un-Ergonomic
       Date: 2020-Mar-29 Sun 10:47 AM
       Category: default
       Url: https://abe-winter.github.io/2020/03/28/jitu-brutus.html
       Extract:
    __________________________________________________________________________________________
****

#### Adding an extract to `article`, `job`, or `note`: `addinfo`

* Adds an extract to an `article`, `job`, or `note`.
* Format: `addinfo [article / job / note] INDEX_NUMBER EXTRACT`

Examples:

    addinfo article 1 It turns out that yes, fresh grads and keener interns do complain to senior developers about asymptotic efficiency.

Expected outcome:

      __________________________________________________________________________________________

    Done, the article description now looks like the following 

       Title: New grad vs senior dev
       Date: 2020-Mar-29 Sun 12:25 PM
       Category: Funny
       Url: https://ericlippert.com/2020/03/27/new-grad-vs-senior-dev/
       Extract: It turns out that yes, fresh grads and keener interns do complain to senior developers about asympto...
    __________________________________________________________________________________________


****


#### Exiting the program : `exit`

* Exits the program. 
* Format: `exit`

Examples:

* `exit` 

Expected Outcome:

    __________________________________________________________________________________________


    _                                Saving your data...                               _
    _                           We are exiting the program...                          _
    _                            Bye. Come back again later.                           _

****

## FAQ

*Q*: How do I transfer my data to another Computer? 

*A*: Copy three text files called "articlelist.json", "notelist.json", 'joblist.json' that has saved your information into the directory from which you will run the application in your new computer. You are then set to go!

## Command Summary
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

## Acknowledgement
Our UserGuide has been heavily inspired by the superb format of the AddressBook user guide that we were given access to for this course.
