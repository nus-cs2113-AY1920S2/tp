# Joseph Lim Wei Jie - Project Portfolio Page

## Overview
`Restaurant Daily Report` is a CLI app that generates a whitepaper, summarizing the internals of a restaurant. It’s aim is to provide restaurant owners a quick overview of how their restaurant is performing daily so that restaurant owners can better manage their business operations.

### Summary of Contributions

**Code contribution**: You can view my code contribution for this project [here:](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=josephlimweijie)

**Enhancement added:**

**Add**:
1. Basic add functionality.
2. Informs user if the user enters a name that is similar to the existing ingredients in the stock.
3. Allows flexibility in the ordering of the parameters specified from the user. For example: Entering `add stock; i/tomato; q/10; p/1.00;` and `add stock; i/tomato; 
    p/1.00; q/10;` are both acceptable.
4. Customizes error messages according to the input supplied from the user. For example: Entering `add stock; i/;` will display the message: `"The user's input must specify 
    the ingredient's name!"`. Entering `add stock; i/tomato; q/-10; p/1.00;` will display the message: `"Please enter a positive value for the quantity to be added!"`
5. Displays a reminder to the user if the user input a similar ingredient name that exists in the stock. This is inform the user if he/she has made a typo that was undesired.
    For example: Entering `add stock; i/ToMATo; q/10; p/1.00;` will display the following result, if tomato already exists in the stock:   
    <p align="center">
        <img src= "https://user-images.githubusercontent.com/59989652/78984371-a42d6b80-7b58-11ea-8e88-d9fa8adfdec8.png">
    </p>    

**Delete**:
1. Basic delete functionality.
2. Allows flexibility in the ordering of the parameters specified from the user. For example: entering `delete stock; i/tomato; q/10;` and `delete stock; i/tomato;` 
    are both acceptable.
3. Allows the user to delete the ingredient by quantity count or remove from the stock entirely. For example: Entering `delete stock; i/tomato;` removes tomato entirely from the stock. 
   Entering `delete stock; i/tomato; q/1;` reduces the quantity of tomatoes in the stock by 1. 
4. Customizes error messages according to the input supplied from the user. For example: Entering `delete stock; i/;` will display the message: `"The user's input must specify the
 ingredient's name!"` while entering `delete stock; i/tomato; q/-10;` will display the message: `"Please enter a positive value for the quantity to be added!"`
    
**List**
1. Basic list functionality.
2. Lists ingredients in the stock from the highest quantity to the lowest quantity.

**Search**
1. Basic search functionality.
2. Takes into account case-sensitivity of the keyword supplied from the user when searching against the stock.

**Load**
1. Loads the stock-related data from the `report.txt` file.

**Contributions to documentations:**

**User guide contribution:**

The following sections are where the **content** is contributed by me:

1. Anchoring using Table of Content at the top of the user guide.
2. Introduction
3. Quick Start
4. Add: add ingredient into stock
5. Delete: delete ingredient from stock
6. List: list stock
7. Search: search stock
8. Command Summary:
    + All `Stock`-related commands from `Section 4 - 7`.
    
The following sections are where the **content** is contributed by me:

**Developer guide contribution:**
1. Anchoring using Table of Content at the top of the developer guide.
2. Design & Implementation:
    Sections:
    + `1.1 Search stock feature` and `1.2 List stock ingredients in descending quantity`
    + Stock-related in Appendix B
    + Manual testing instructions for dish and stock in Appendix E.

**Team-based Task**
1. Automate testing by incorporating test cases using text-ui-test.
2. Release `Restaurant Daily Report v2.0`.

**Review/Mentoring contributions:** 
1. Troubleshoot failing CI-Test in issue [#74](https://github.com/nus-cs2113-AY1920S2/forum/issues/74)


