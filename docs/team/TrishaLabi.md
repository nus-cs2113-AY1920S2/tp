# Trisha Labi - Project Portfolio
# PROJECT: SHOCO 

## Overview

SHOCO is a desktop application used for the managing and planning of shopping lists and budget. Users interact with it through the use of a command-line interface (CLI) and the program is written in Java.

### Summary of Contributions
* **Major enhancement 1:** Included the **ability to edit items** in the shopping lists.
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
   
    * Community:
        * PRs reviewed (with non-trivial review comments): <ins>[#38](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/38)</ins> , <ins>[#169](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/169)</ins>
       


### Contributions to the User Guide
> *Given below are sections that I have contributed to the User Guide. They showcase my ability to write documentation targeting end-users.*
#### Editing an item: `EDIT`
Edits the specified item in the shopping list.

Format: `EDIT INDEX [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]`

* Edits the item at the specified `INDEX`. The `INDEX` refers to the index number 
shown in the displayed shopping list.
* The `INDEX` and `[QUANTITY]` must be a **positive integer**. *e.g 1, 2, 3 ..*
* The `[PRICE]` must be in **positive numerical** form (decimal form accepted).
* **At least one** of the three parameters (description/price/quantity) must be present in the command.
* i/, p/, q/ delimiters can be in <em>any</em> order. e.g `i/.. p/.. q/..` or `q/.. i/.. p/..`.

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

### Contributions to the User Guide
> *Given below are sections that I have contributed to the User Guide. They showcase my ability to write documentation targeting end-users.*

#### 3.2 Edit feature
##### 3.2.1 Current implementation

The edit feature is implemented using an <code>EditCommand</code> class. This class extends from the main
<code>Command</code> class. The <code>item</code> object to be edited is identified by the index number provided 
in the user input. In addition to the index no. , the user input **must also contain at least one** of these parameters: 
*description*, *price*, *quantity*. 

The process of object creation is as follows:

1. <code>Duke</code> class receives user input from the <code>Ui</code> class. 
2. A <code>Parser</code> object is created to call its <code>parseCommand</code> method.
   * The <code>Parser</code> object instantiates an <code>EditCommand</code> object based on the user input.
3. The <code>Duke</code> class calls the <code>EditCommand#execute</code> method.
4. In the <code>EditCommand#execute</code> method, it first gets the <code>item</code> object through the <code>ShoppingList#getItem</code>
The original description/price/quantity of the item is overwritten 
with the new values from the user input. This is done through the use of the <code>Item</code> class setter methods.
5. The <code>item</code> object with its' new values is stored back to the <code>ShoppingList</code> object.

The following sequence diagram below shows how the edit feature works. The details of updating the items' values
have been omitted from the diagram. Those details are shown in a separate sequence diagram below:

![Edit Feature](../images/EditFeature.png)

![Edit Feature SD](../images/EditFeature_SD.png)


##### 3.2.2 Design considerations

###### Aspect: Data structure to support the edit feature

- Alternative 1 (current choice): Only parameters present in user input are treated as values to update.
  
  - Pros: User has the flexibility to choose which variables he/she wishes to update.
  
  - Cons: Might significantly increase the code base as there is a need to check for the 
    presence of the variable in user input.
 
 
- Alternative 2: Require all values of an <code>item</code> object to be updated and parameters must be in alphabetical order.
  - Pros: Will have less code to deal with having no additional parsing of the input string.
  
  - Cons: Less user flexibility; user must input all parameters even if he/she does not wish to update certain
  variables.


- Reason for choosing alternative 1: By allowing users to be able to update any values they want, it provides them with greater convenience and freedom as they do not need to follow strict command "rules/order". Futhermore, having greater freedom on input values makes it a hassle-free process for the users.

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

![Help Feature](../images/HelpFeature.png)

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

- Reason for choosing alternative 1: By abstracting out different command types as separate classes, we could 
 work better in parallel and also be able to spot bugs more easily as each class deals with a different functionality.


---

        
        