# **Nuke Developer Guide** <small>v1.5</small>  

By: `CS2113T-T13-2`      Since: `Feb 2020`    

<br>

## **Table of Contents**  

<big style="color: green">**Introduction** [&#10149;](#introduction)  </big>  
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Purpose [&#10149;](#purpose)   
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Scope [&#10149;](#scope)   
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Design Goals [&#10149;](#design-goals)   
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Definitions [&#10149;](#definitions)   
<big style="color: green"> **Setting Up** [&#10149;](#setting-up)  </big>  
<big style="color: green">  **Design** [&#10149;](#design)  </big>  
<big style="color: green"> **Implementation** [&#10149;](#implementation)  </big>  
&nbsp; &nbsp; &nbsp; &nbsp; 1. Add Feature [&#10149;](#1-add-feature)   
&nbsp; &nbsp; &nbsp; &nbsp; 2. Delete Feature [&#10149;](#2-delete-feature)   
<big style="color: green"> **Appendix** [&#10149;](#appendix)  </big>  
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; User Stories [&#10149;](#user-stories)   
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Non-Functional Requirements [&#10149;](#non-functional-requirements)   
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Glossary [&#10149;](#glossary)   
&nbsp; &nbsp; &nbsp; &nbsp; &#8226; Manual Testing [&#10149;](#manual-testing)    

<br>  
  
## **Introduction**  

### **Purpose**  
<span style="text-align: justify">
This document describes the structure and software design decisions for the <b>Nuke</b> application. The <b>Nuke</b> application is a simple yet powerful task management application that is dedicated to providing <b>NUS students</b> efficient organisation of <i>modules</i> and <i>tasks</i>.  
</span>
    
### **Scope**  
<span style="text-align: justify">
This document will cover the structure and software design decisions for the implementation of <b>Nuke</b>. The intended audience for this document are developers, designers and software testers of <b>Nuke</b> <i>or</i> other similar task management application.
</span>  

### **Design Goals**  

```
	// To be done.
```

### **Definitions**  
```
	// To be done.
```

<br>

## **Setting Up**  
Refer to the guide [here](#) to set up.  

<br>

## **Design**  

<br>

## **Implementation**  
This section will describe the significant details on how certain features in <b>Nuke</b> are being implemented.  

### 1. Add Feature

<br>

### 2. Delete Feature  
#### Overview  
The **delete** feature deletes *modules*, *categories* and *tasks* from the Module, Category and Task List respectively.   
When the user first requests to execute the **delete** command *(assuming the command format given is valid)* to delete a directory by providing its name, the application will first filter for matching directory names. From here, there are **three** possible outcomes:  
1. There are **no** matches --  Nothing is deleted.
2. There is **one** match -- A prompt will be given to the user to confirm the deletion.
3. There are **multiple** matches -- The list of matches will be shown to the user, and the user chooses which ones to delete. A further prompt will be given to confirm the deletion(s).

#### Feature Implementation  

This feature is facilitated by the `DeleteModuleCommand`, `DeleteCategoryCommand` and `DeleteTaskCommand`, classes which deletes the corresponding *modules*, *categories* and *tasks* respectively. <br>  

The above-stated three classes [overrides](#) the `executeInitialDelete()` method which extends from the abstract `DeleteCommand` class. The `executeInitialDelete()` method's role is to prepare the necessary messages or prompts for the user depending on the number of filtered matches. <br>  

The `DeleteCommand` class in turn extends the `FilterCommand` abstract class. The `FilterCommandClass` contains the following vital methods for filtering:  
- `createFilteredModuleList()` -- Creates an `ArrayList` of the filtered *modules*.  
- `createFilteredCategoryist()` -- Creates an `ArrayList` of the filtered *categories*.  
- `createFilteredTaskList()` -- Creates an `ArrayList`of the filtered *tasks*.  

Lastly, the `FilterCommand` class extends the abstract `Command` class that contains the `execute()` method to execute the actual **delete** command.  
<br>  
Other than the commands, two prompts are involved.   
The prompt to request for the list number(s) of the item(s) to delete from the filtered list is managed by the `ListNumberPrompt` class. The prompt to request to confirm the deletion is managed by the `DeleteConfirmationPrompt` class.  
<br>
Below are the class-diagrams for the involved classes:  

![Delete Command Class Diagram](images/Delete%20Command%20Class%20Diagram.png)  
<span style="color:#73c742"><small><i>Figure <b>Delete Command Class Diagram</b></i></small></span>


```
	// To Prompt Class diagrams here 
```

#### Example Usage  
The deletion process for *modules*, *categories* and *tasks* are similar. In this example, the deletion process for *tasks* will be illustrated as a series of steps.  <br>
James is a user and wants to delete some of his *tasks* with *description* "work". Assume that he has the current Task List:  
```
+--------------------------------------------------------------------------------------------------+
 NO |  MODULE  |       CATEGORY       |          TASK          |           DEADLINE           | PTY 
+--------------------------------------------------------------------------------------------------+
 1  |  CS1231  |      Assignment      |       group work       |      28/03/2020 06:00PM      | 20  
 2  |  CS1231  |       Tutorial       |       Tutorial 6       | 24/03/2020 11:59PM [OVER!!]  |  6  
 3  |  CS2100  |       Lecture        |     watch webcast      |            -NIL-             |  1  
 4  |  CS2113  |      Bigger Lab      |      Big Lab Work      |        today 06:00PM         |  7  
 5  |  CS2113  |       Tutorial       | tutorial worksheet 10  |        today 12:00PM         |  5  
+--------------------------------------------------------------------------------------------------+
Total tasks: 5
+--------------------------------------------------------------------------------------------------+

```   

1. James will first enter the command to delete *tasks*:  
	`delt work -a`  
	After the input is parsed as a **delete task** command and executed, the `DeleteTaskCommand#execute()` will call `FilterCommand#createFilteredTaskList()` to create the filtered list of *tasks* containing the *description* "work". `DeleteTaskCommand#execute()` will then call its own method `DeleteTaskCommand#executeInitialDelete(filteredList)` to prepare the prompt to request James to enter the list number of the *tasks* he would like to delete.  

2. James receives the following prompt:
	```  
	Multiple matching tasks found.
	+--------------------------------------------------------------------------------------------------+
	 NO |  MODULE  |       CATEGORY       |          TASK          |           DEADLINE           | PTY 
	+--------------------------------------------------------------------------------------------------+
	 1  |  CS1231  |      Assignment      |       group work       |      28/03/2020 06:00PM      | 20  
	 2  |  CS2113  |      Bigger Lab      |      Big Lab Work      |        today 06:00PM         |  7  
	 3  |  CS2113  |       Tutorial       | tutorial worksheet 10  |        today 12:00PM         |  5  
	+--------------------------------------------------------------------------------------------------+
	Total tasks: 3
	+--------------------------------------------------------------------------------------------------+

	Enter the list number(s) of the tasks to delete.
	```  
	He proceeds to enter list numbers `2 3` as he has already completed both *tasks*.  
	After the list numbers are parsed, it will call `ListNumberPrompt#execute()`, which will prepare the prompt for the delete confirmation, and then calls `ListNumberPrompt#executePromptConfirmation()`.

3. James receives another prompt:  
	```  
	Confirm delete these tasks?
	tutorial worksheet 10
	Big Lab Work
	```  
	He enters `y` to confirm the deletion.
	`DeleteConfirmationPrompt#execute()` will be called, which then calls `DeleteConfirmationPrompt#executeMultipleDelete(filteredList)` to delete James' selected *tasks* from his Task List.

4. James receives the final message:
	```
	SUCCESS!! Task(s) have been deleted.
	```
	Deletion process ends.  

<br>

Below is a *sequence diagram* to illustrate the above example scenario.  
```
	// To do Sequence diagram here
```

<br><br>

### 3. ...
```
	// To do implementaitons for other features
```


<br>

## **Appendix**  

### **User Stories**  

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

|Priority |As a ... |I want to ... |So that I can...|
|---|---|---|---|
|`* * *` |new user |see usage instructions |learn about existing features and how can I use them |
|`* * *` |student |add modules\tasks |I can have track on tasks of different modules |
|`* * *` |student |delete modules\tasks |remove modules and tasks I do not need to keep on track anymore |
|`* * *` |student |edit modules\tasks |I can correct or change some attributes
|`* * *` |student |constansly check the deadline of tasks in ascending order |I can get tasks done on time |
|`* * *` |student |receive a reminder of urgent tasks |I know which tasks should be done first |
|`* * *` |student |sort my tasks in terms of certain criteria |I can view my tasks of highest priorites |
|`* * *` |student |add tags to tasks |I can filter and view tasks with respect to certain tags|
|`* *` |student |receive a reminder of expired tasks |I know I passed the deadline |

_{More to be added}_


### **Non-Functional Requirements**  
```
	// To be done.
```

### **Glossary**  
```
	// To be done.
```

### **Manual Testing**  
```
	// To be done.
```
