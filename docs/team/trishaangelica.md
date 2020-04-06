<!-- @@author trishaangelica -->
# Trisha Labi - Project Portfolio
# PROJECT: SHOCO 

## Overview

SHOCO is a desktop application used for the managing and planning of shopping lists and budget. Users interact with it through the use of a command-line interface (CLI) and the program is written in Java.

### Summary of Contributions
* **Major enhancement 1:** Included the **ability to edit items** in the shopping list.
    * What it does: Allows the user to update values of the items in the shopping list. Such values are the description, price and quantity of the item.
    * Justification: This feature improves the product significantly because a user can make mistakes when adding an item (e.g typos made) and the app should provide a convenient way to rectify these mistake.
    * Highlights: This enhancement allows the user to update any value. The edit command does not require any order in it's input (e.g alphabetical ordering of delimiters).
* **Minor enhancement 1:** Negative values entered for price and quantity are not accepted. This is for a realistic approach as items will never have negative prices and/or quantities in real life.

* **Major enhancement 2:** Added the **help function** for the SHOCO application.
    * What it does: Lists all acceptable commands, their various purpose, valid parameters and examples of usage.   
    * Justification: This feature improves the product significantly because a user may forget how certain functions work and so the app should provide a convenient way for users to check the accepted format, instead of making them go through the SHOCO user guide.
    * Highlights: This enhancement includes examples of command usages which makes it much more comprehensible to users.  

* **Code contributed:** [[Functional code]](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=trishaangelica&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=trishaangelica&tabRepo=AY1920S2-CS2113T-T13-1%2Ftp%5Bmaster%5D)


* **Other contributions:** 
    * Project Management:
        * Closed milestones v1.0 - v2.0 on GitHub
        * Assigned bugs and PRs to respective team members on GitHub.
    
    * Enhancements to existing features:
        * Included all test cases in input.txt file (Pull Request <ins>[#69](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/69/files#diff-cc5b0804a9a39fce149b1b64e58378db)</ins>)
        * Added SHOCO logo for the application <ins>[#88](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/88/files)</ins>
        * Changed original list display to pretty-printing in a table (*Reused code from logicbig*) <ins>[#54](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/54/files#diff-f5a2a66f3d795321ea951828c239db70)</ins>
        
    * Documentation:
        * Added a clickable table of contents and "back to top" links for both SHOCO user guide and developer guide: <ins>[#111]((https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/111))</ins> , <ins>[#112](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/112/files)</ins>, <ins>[#122](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/122/files)</ins>
        * Formatted the Manual Testing of the DG()
   
    * Community:
        * PRs reviewed (with non-trivial review comments): <ins>[#38](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/38)</ins> , <ins>[#169](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/169)</ins>
       


### Contributions to the User Guide
> *Given below are sections that I have contributed to the User Guide. They showcase my ability to write documentation targeting end-users.*

#### Editing an item: `EDIT`
 Edits the specified item in the shopping list.
 
 Format: `EDIT INDEX [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]`
 
 * Edits the item at the specified `INDEX`. The `INDEX` refers to the index number 
 shown in the displayed shopping list. 
 * You can view an item's `INDEX` number by using the `DISPLAY` command. More info [here](#displaying-list-and-budget-details-display).
 * The `INDEX` and `[QUANTITY]` must be a **positive number**. *e.g 1, 2, 3 ..*
 * The `[PRICE]` must be in **positive numerical** form (decimal form accepted).
 * **At least one** of the three parameters (description/price/quantity) must be present in the command.
 > :information_source: You can rearrange the delimiters i/, p/ , q/ in <em>any</em> order. e.g `i/.. p/.. q/..` or `q/.. i/.. p/..`.
 
 Examples of usage: 
 
 1. `EDIT 3 i/potato p/5.00 q/3`
     * Edits the description, price and quantity of the 3rd item in the shopping list
     
 2. `EDIT 3 i/potato chips p/5.00`  **OR**  `EDIT 3 i/potato chips q/2`  **OR**  `EDIT 3 p/5.00 q/2`
     * Edits the description and price /  description and quantity /  price and quantity of the 
     3rd item in the shopping list
     
 3. `EDIT 3 i/potato chips` **OR** `EDIT 3 p/5.00` **OR** `EDIT 3 q/2`
     * Edits only description / only price / only quantity of the 3rd item in the shopping list
     
 &nbsp;

#### Viewing help: `HELP`
Shows the available commands and how they are to be used.

Format: `HELP`

&nbsp;


---

### Contributions to the Developer Guide
> *Given below are sections that I have contributed to the User Guide. They showcase my ability to write documentation targeting end-users.*
 
&nbsp;

### 3.2 Edit feature
#### 3.2.1 Current implementation

The edit feature is implemented using an <code>EditCommand</code> class. This class extends from the main
<code>Command</code> class. The <code>item</code> object to be edited is identified by the index number provided 
in the user input. In addition to the index number, the user input **must also contain at least one** of these parameters: 
*description*, *price*, *quantity*. 

The process is as follows:

1. <code>Duke</code> class receives user input from the <code>Ui</code> class. 
2. A <code>Parser</code> object is created.
3. <code>Duke</code> calls <code>Parser#parseCommand()</code> method to instantiate an <code>EditCommand</code> object based on the user input.
4. <code>Duke</code> class then calls the <code>EditCommand#execute()</code> method.
5. In the <code>EditCommand#execute()</code> method, it first gets the <code>item</code> object through
<code>ShoppingList#getItem()</code>. The original description / price / quantity of the item is overwritten 
with the new values from the user input. This is done through the use of the <code>Item</code> class setter methods.
6. The <code>item</code> object with its' new values is stored back to the <code>ShoppingList</code> object.

The following sequence diagram below shows how the edit feature works. The details of updating the items' values
have been omitted from the diagram. Those details are shown in a separate sequence diagram below:

![Edit Feature](../images/EditFeatureFinal.png)


 The separate sequence diagram below shows how the item is updated with new values.
 
![Edit Feature SD](../images/EditFeature_SD.png)


##### 3.2.2 Design considerations

###### Aspect: Data structure to support the edit feature

- Alternative 1 (current choice): Only parameters present in user input are treated as values to update.
  
  - Pros: User has the flexibility to choose which variables he/she wishes to update.
  
  - Cons: Might significantly increase the code base as there is a need to check for the 
    presence of the variable in user input.
 
 
- Alternative 2: Require all values of an <code>item</code> object to be updated and parameters must be in alphabetical
order.
  - Pros: Will have less code to deal with having no additional parsing of the input string.
  
  - Cons: Less user flexibility; user must input all parameters even if he/she does not wish to update certain
  variables.


 - Reason for choosing alternative 1: By allowing users to update any values they want, it provides them with greater convenience and freedom as they do not need to follow strict command "rules/order". Furthermore, having greater freedom on input values makes it a hassle-free process for the users.

&nbsp;
 
#### 3.10 View help feature
##### 3.10.1 Current implementation

The help feature is implemented using a <code>HelpCommand</code> class which extends the main
<code>Command</code> class. The <code>HelpCommand</code> class shows the program usage instructions to the user.

The <code>Duke</code> class first receives user input from the <code>Ui</code> class before it creates a 
<code>Parser</code> object and calls its <code>parseCommand</code> function. If the user input fails to match any
of the correct command keywords (<code>ADD</code>, <code>EDIT</code>, <code>DEL</code> etc.), a 
<code>HelpCommand</code> object will be instantiated.

Once instantiated, the <code>Duke</code> then class calls the <code>execute</code> method of the 
<code>HelpCommand</code> object. In this method, accepted command formats are displayed to the user.

The following sequence diagram below shows how the help feature works. Note the <code>Ui</code> class is
omitted in the sequence diagram to emphasise on the other classes:

![Help Feature](images/HelpFeatureFinal.png)

##### 3.10.2 Design considerations

###### Aspect: Data structure to support the help feature


- Alternative 1 (current choice): Object-oriented style with a separate class for <code>HelpCommand</code>
 
  - Pros: Easy to add the help feature without having to change the logic of the code much as each command
  object is treated as a black box
  
  - Cons: Might significantly increase the code base with another class being added


- Alternative 2: Implement help feature in the <code>Duke</code> or <code>Parser</code> class

  - Pros: Will have less code to deal with as a new function is simply created in the <code>Duke</code> class
  
  - Cons: Code becomes less organised since for every other command that we have implemented, <code>Duke</code> class
  simply executes those commands as black boxes, without worrying about their internal details

- Reason for choosing alternative 1: By abstracting out different command types as separate classes, we could work better in parallel and also be able to spot bugs more easily as each class deals with a different functionality
 
&nbsp;

## Appendix D: Instructions for Manual Testing
> :information_source: These instructions only provide a starting point for testers to work on; testers are expected to do more _exploratory_ testing. 

### D.1. Launch and ShutDown
1. Initial launch 
    
      i.    Download the [latest jar file](https://github.com/AY1920S2-CS2113T-T13-1/tp/releases).
        
      ii.   Copy it into an empty folder on your desktop and rename the folder to "SHOCO.
        
      iii.  Open a command prompt window by typing <code>cmd</code> in the start menu.
        
      iv.   Change your directory to where the jar file is located by running the command <code>cd desktop/SHOCO</code>
        
      v.    Run the command <code>java -jar SHOCO.jar</code>
            
        Expected: Shows a welcome message from SHOCO.

    &nbsp;

2. Shut down
        
      i.    Enter the command <code>BYE</code> to exit the SHOCO application.
                
      ii.   Close the command terminal.
        
        Expected: Data is stored to shoppinglist.json and budget.json, the program is terminated.
  

&nbsp;

### D.2. Set and Reset a budget

1. Set a budget
       
    i. Test case: <code>SET b/500.00</code>

       Expected: Budget is set to $500.00
    
     &nbsp;

    ii. Test case: <code>SET b/10000</code>
       
        Expected: Budget is set to $5000.00, which is the maximum budget SHOCO allows.
     
     &nbsp;

    iii. Test case: <code>SET b/-100</code>
     
        Expected: Budget is reset to $0.00, which is the minimum budget SHOCO allows.
        
     &nbsp;

    iv. Other incorrect set budget commands to try: <code>SET b/xxx</code> (where xxx is not a number).
            
        Expected: An error message and the correct usage of the SET command is shown.
     
     &nbsp;
     
2. Reset the budget
       
    i. Test case: <code>RES</code> 

        Expected: Budget has been reset to $0.00
    
     &nbsp;
     
    ii. Other incorrect reset budget commands to try: <code>RES xxx</code> (where xxx is not a number).
                     
        Expected: An error message and the correct usage of the RES command is shown.
        
    
### D.3. Add and Edit an item

1. Add an item 
        
    *Optional:  List all items currently in the shopping list to prevent entering a duplicate item description : <code> DISPLAY </code>*
       
    i. Test case: <code> ADD i/apple p/3.00 q/2 </code>
       
       Expected: An item with the description - "apple", price - "$3.00" and quantity - "2"  is added.
         
     > :bulb: You can run the <code>DISPLAY</code> command to check the newly added item.
                                                           
    &nbsp;

    ii. Test case: <code>ADD p/3.00</code>
     
        Expected: No item is added. Error message and a correct usage of the ADD command is shown.
         
     &nbsp;
       
    iii. Other incorrect ADD commands to try: <code>ADD</code>, <code>ADD p/xxx</code>, <code>ADD q/xxx</code> (where xxx is not a number).
        
        Expected: Similar to previous. 
    
    &nbsp;
    
2. Edit an item
     
    > :bulb: You can run the <code>DISPLAY</code> command to check if the item has been correctly updated.
           
    i. Test case: <code>EDIT 1 i/banana</code>
    
    *Assumption: Valid index and description is provided. (No duplicate description allowed)*
    
        Expected: The description of the first item is updated to "banana". 
        
     &nbsp;
    
    ii. Test case: <code>EDIT 1 p/5.60</code>
           
        Expected: The price of the first item is updated to "$5.60". 
         
     &nbsp;
    
    iii. Test case: <code>EDIT 1 q/3</code>
         
        Expected: The quantity of the first item is updated to "3". 
            
     &nbsp;
    
    iv. Other incorrect edit commands to try: <code>EDIT p/xxx</code> , <code>EDIT q/xxx</code>. (where xxx is not a number).
                
        Expected: An error message and the correct usage of the EDIT command is shown.
         
     &nbsp;
    
### D.5. Find and Delete an item

1. Find an item based on keyword
       
    i. Test case: <code>FIND apple</code> 

       Expected: A list of items that contains "apple" in their description is displayed.
    
     &nbsp;

    ii. Test case: <code>FIND xxx</code> (where xxx is a keyword that is unmatched)
       
        Expected: A message that says "Sorry, no results could be found" is shown.
     
     &nbsp;
 
2. Delete an item

     > :bulb: You can run the <code>DISPLAY</code> command to check the index of the items.
         
    i. Test case: <code>DEL 1</code> 

       Expected: The first item (if it exists), is deleted.
    
     &nbsp;

    ii. Test case: <code>DEL xxx</code> (where xxx is a not a number / the item does not exist yet)
       
        Expected: An error message is shown. 
     
     &nbsp;


### D.6. Display and Clear the shopping list

1. List all items in the shopping list
            
     i. Test case: <code>DISPLAY</code>
           
        Expected: A list containing all the items is displayed in a table.The current amount of the budget is also shown.
                                                                            
      &nbsp;

2. Clear all items in the shopping list
            
     i. Test case: <code>CLEAR</code>
           
        Expected: The shopping list is cleared.
                                                                            
      &nbsp;

    
---
<!-- @@author -->
        