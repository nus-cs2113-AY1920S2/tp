# Keith Chan Jun Kai - Project Portfolio Page

## PROJECT: Amazing Task and Assignment System (ATAS)

## Overview
ATAS is a cross platform Command Line Interface (CLI) program that allows you to track your assignments and events. 
ATAS is catered for students who want to maximise their productivity and cultivate effective time management habits through a desktop application.


### Summary of Contributions
**Major Enhancement**: Ability to view the month's Task through a traditional Calendar view.  
* What it does: 
    * Allows user to obtain a list of tasks within an user supplied month and year that is represented in a traditional Calendar view 
* Justification: 
    * Improves the product significantly as user are able to take advantage of the quickness of a Command Line interface and still obtain a visually intuitive formatted schedule without a GUI.
* Highlights:
    * With the exception of `java.util.Calendar` which retrieves basic information about the month such as number of days in month, implementation of a Calendar in CLI is almost completely manual without the help of an external API which makes it extremely challenging.
      
    * As RepeatEvents are kept within the application as a single object with fixed date, an in-depth analysis of how to interact with `RepeatEvent` when retrieving a list of tasks that falls within a given time period was required and also sets the foundation for future commands that require such lists.
    
    * Calendar feature was implemented with ANSI escape sequences which colours command line output, that other commands can take advantage of to improve the user interface and experience.          
    
**Minor Enhancement**: Implemented Ui component which handles all user inputs and outputs.

Other Minor Enhancements which might have been refactored by team mates include:
* HelpCommand and the corresponding help messages for all commands.
* ExitCommand
* DoneCommand 

**Code contributed**:[View on RepoSense](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=keith-jk&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Keith-JK&tabRepo=AY1920S2-CS2113T-M16-1%2Ftp%5Bmaster%5D)

**Contributions**
* User Guide:
    * Added section 3.9 Search Task.
    * Added section 3.10 Calendar view.
    
* Developer Guide:
    * Added and wrote section 3.6 Calendar feature.    

* Others:
    * Reviewed and provided suggestions for Clear command, in particular refactored logic of Clear Done tasks. [#72](https://github.com/AY1920S2-CS2113T-M16-1/tp/pull/72)    
    